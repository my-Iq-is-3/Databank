package me.zach.databank;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import me.zach.databank.saver.Key;
import me.zach.databank.saver.PlayerData;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;

import java.util.Collections;
import java.util.Iterator;
import java.util.UUID;
import java.util.logging.Level;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Databank {
    private MongoCollection<PlayerData> collection;
    private MongoDatabase db;
    private MongoClient client;


    public Databank(String database, String collection){
        this("localhost",27017,database,collection);
    }

    public Databank(String ip,int port,String database, String collection){
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        this.client = MongoClients.create(MongoClientSettings.builder().
                applyToClusterSettings(builder -> builder.hosts(Collections.singletonList(new ServerAddress(ip, port))))
                .codecRegistry(codecRegistry)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build());
        this.db = client.getDatabase(database);
        this.collection = db.getCollection(collection, PlayerData.class);
    }

    public void set(PlayerData val) throws NullPointerException, IllegalStateException {
        if(val == null) throw new NullPointerException("PlayerData to save cannot be null!");
        UUID uuid = val.getUuid();
        if(uuid == null) throw new NullPointerException("PlayerData uuid to save cannot be null!");
        Bson filter = uuidFilter(uuid);
        long count = collection.countDocuments(filter);
        if(count > 0){
            collection.findOneAndReplace(filter, val);
            Bukkit.getLogger().info("Updated document " + uuid);
            if(count > 1) Bukkit.getLogger().warning("ALERT: More than one player data entry registered under uuid " + uuid + "!");
        }else{
            InsertOneResult response = collection.insertOne(val);
            if(response.getInsertedId() == null){
                throw new IllegalStateException("Could not insert document " + uuid);
            }else Bukkit.getLogger().info("Inserted document " + uuid);
        }
    }

    public void remove(UUID uuid){
        collection.deleteOne(uuidFilter(uuid));
        Bukkit.getLogger().info("Removed document " + uuid);
    }

    public MongoCollection<PlayerData> getCollection(){
        return collection;
    }

    public static Bson uuidFilter(UUID uuid){
        return Filters.eq(Key.UUID, uuid);
    }

    public PlayerData findFromId(Bson bson){
        Iterator<PlayerData> iterator = collection.find(bson).iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }
}
