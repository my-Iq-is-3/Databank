package me.zach.databank.saver;

import me.zach.DesertMC.DesertMain;
import me.zach.DesertMC.Utils.RankUtils.Rank;
import me.zach.databank.DBCore;
import me.zach.databank.Databank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredListener;

import java.util.Arrays;
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
        Bukkit.getScheduler().runTask(DBCore.getInstance(), () -> { //give plugins time to load (runs next tick)
            UUID uuid = UUID.randomUUID();
            try{
                PlayerData data = new PlayerData(uuid);
                databank.set(data);
                load(uuid);
                PlayerData retrieved = getData(uuid);
                boolean matches = data.equals(retrieved);
                dump(uuid);
                databank.remove(uuid);
                if(matches) Bukkit.getLogger().info("Player data test success!");
                else throw new IllegalStateException("Player data saved and player data retrieved not equal!");
            }catch(Exception ex){
                Bukkit.getLogger().log(Level.SEVERE, "***PLAYER DATA STORAGE/ACCESS TEST FAILED***\n" + "UUID: " + uuid + "\n", ex);
                Bukkit.shutdown();
            }
        });
    }

    public void load(UUID uuid){
        Bukkit.getLogger().info("Loading data for player " + uuid);
        if(!data.containsKey(uuid)) {
            Bukkit.getLogger().info("Fetching document " + uuid);
            PlayerData dat = databank.findFromId(Databank.uuidFilter(uuid));
            if(dat == null) dat = new PlayerData(uuid);
            data.put(uuid, dat);
        }
    }

    public void dump(UUID uuid){
        databank.set(data.get(uuid));
        data.remove(uuid);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event){
        load(event.getPlayer().getUniqueId());
    }

    @EventHandler(priority = EventPriority.MONITOR)
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
