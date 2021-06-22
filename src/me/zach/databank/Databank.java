package me.zach.databank;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import org.apache.commons.lang3.Validate;

import java.net.UnknownHostException;
import java.util.UUID;

public class Databank {

    private DBCollection col;
    private DB db;
    private MongoClient client;
    private String database;
    private String collection;


    public Databank(String database,String collection){
        this("localhost",27017,database,collection);
    }

    public Databank(String ip,int port,String database, String collection){
        this.database = database;
        this.collection = collection;
        //Connect to the specified ip and port
        //Default is localhost, 27017
        try {
            client = new MongoClient(ip, port);
        } catch (UnknownHostException e) {
            //When you end up here, the server the db is running on could not be found!
            System.out.println("Could not connect to database!");
            e.printStackTrace();
        }
        db = client.getDB(database);
        col = db.getCollection(collection);
    }

    public boolean set(UUID uuid, String key,  Object val){
        if(val == null || uuid == null || key == null) return false;
        DBObject result = new BasicDBObject("uuid", uuid.toString());
        DBObject found = col.findOne(result);

        if(found == null) {
            result.put(key, val);
            col.insert(result);
        }else{
            found.put(key,val);
            col.update(result, found);
        }
        return true;
    }



    public Object get(UUID uuid, String key){

        try {
            DBObject r = new BasicDBObject("uuid", uuid.toString());
            DBObject found = col.findOne(r);
            return found.get(key);
        } catch(NullPointerException ex){
            set(uuid, key, null);
            return null;
        }
    }

    public int getInt(UUID uuid, String key){
        Object found = get(uuid,key);
        return found instanceof Integer ? (int) found  : 0;
    }

    public int getInt(UUID uuid, String key, int def){
        Object found = get(uuid,key);
        return found instanceof Integer ? (int) found  : def;
    }


    public String getString(UUID uuid, String key){
        Object found = get(uuid,key);
        System.out.println("s: " + found);
        return found instanceof String ? (String) found : "";
    }


}
