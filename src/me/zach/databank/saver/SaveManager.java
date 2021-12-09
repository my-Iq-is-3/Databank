package me.zach.databank.saver;

import me.zach.databank.DBCore;
import me.zach.databank.Databank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public class SaveManager implements Listener {
    private final Databank databank;
    public final HashMap<UUID, PlayerData> data = new HashMap<>();
    public void load(Player player){
        load(player.getUniqueId());
    }
    public SaveManager(){
        databank = new Databank(DBCore.DATABASE, DBCore.COLLECTION);
        try{
            UUID uuid = UUID.randomUUID();
            PlayerData data = new PlayerData(uuid);
            databank.set(data);
            PlayerData retrieved = databank.findFromId(Databank.uuidFilter(uuid));
            boolean matches = data.equals(retrieved);
            databank.remove(uuid);
            if(matches) Bukkit.getLogger().info("Player data test success!");
            else throw new IllegalStateException("Player data saved and player data retrieved not equal! UUID: " + uuid);
        }catch(Exception ex){
            Bukkit.getLogger().log(Level.SEVERE, "***PLAYER DATA STORAGE/ACCESS TEST FAILED***\n---------------------------------------", ex);
            Bukkit.shutdown();
        }
    }

    public void load(UUID uuid){
        if(!data.containsKey(uuid)) {
            System.out.println("loading data for uuid " + uuid);
            PlayerData dat = databank.findFromId(Databank.uuidFilter(uuid));
            if(dat == null) dat = new PlayerData(uuid);
            data.put(uuid, dat);
        }
    }

    public void dump(UUID uuid){
        System.out.println("dumping " + uuid);
        databank.set(data.get(uuid));
        System.out.println("finished dump");
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event){
        System.out.println("loading " + event.getPlayer().getUniqueId());
        load(event.getPlayer().getUniqueId());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onLeave(PlayerQuitEvent event){
        dump(event.getPlayer().getUniqueId());
    }

    public PlayerData getData(Player player){
        return getData(player.getUniqueId());
    }

    public PlayerData getData(UUID uuid){
        return data.get(uuid);
    }

    public void onDisable(){
        dumpAll();
    }

    public Databank getDatabank(){
        return databank;
    }

    public void dumpAll(){
        for(UUID id : data.keySet()){
            dump(id);
        }
    }
}
