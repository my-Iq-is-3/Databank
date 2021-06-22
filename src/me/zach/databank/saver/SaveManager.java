package me.zach.databank.saver;

import me.zach.databank.DB;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class SaveManager implements Listener {
    private static final HashMap<UUID,PlayerData> data = new HashMap<>();


    public static void load(Player player){
        load(player.getUniqueId());
    }

    public static void load(UUID uuid){
        if(!data.containsKey(uuid)) {
            System.out.println("loading...");
            data.put(uuid, new PlayerData(uuid));
            System.out.println("loaded");
        }
    }

    public static void dump(UUID player){
        System.out.println("dumping " + player);
        PlayerData info = getData(player);
        set(player,Key.GEMS,info.gems);
        set(player,Key.SOULS,info.souls);

        set(player,Key.SELECTED_CLASS,info.currentClass);

        set(player,Key.TANK_LVL,info.tankL);
        set(player,Key.TANK_XP,info.tankXP);
        set(player,Key.TANK_XPR,info.tankXPR);

        set(player,Key.SCOUT_LVL,info.scoutL);
        set(player,Key.SCOUT_XP,info.scoutXP);
        set(player,Key.SCOUT_XPR,info.scoutXPR);

        set(player,Key.WIZARD_LVL,info.wizardL);
        set(player,Key.WIZARD_XPR,info.wizardXPR);
        set(player,Key.WIZARD_XP,info.wizardXP);

        set(player,Key.CORRUPTER_LVL,info.corL);
        set(player,Key.CORRUPTER_XPR,info.corXPR);
        set(player,Key.CORRUPTER_XP,info.corXP);
        set(player,Key.ARTIFACT_DATA,info.ad.getData());
        data.remove(player);
        System.out.println("finished dump");
    }

    @EventHandler(priority = EventPriority.HIGH)
    public static void onJoin(PlayerJoinEvent event){
        System.out.println("loading " + event.getPlayer());
        load(event.getPlayer().getUniqueId());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public static void onLeave(PlayerQuitEvent event){
        dump(event.getPlayer().getUniqueId());
    }



    public static void onDisable(){
        dumpAll();
    }

    public static void dumpAll(){
        for(UUID ids : data.keySet()){
            dump(ids);
        }
    }

    private static void set(UUID uuid,String key,Object val){
        DB.PLAYER_DATA.set(uuid,key,val);
        System.out.println("setting uuid " + uuid + ", key=" + key + ", obj=" + val);
    }


    public static PlayerData getData(Player player){
        return getData(player.getUniqueId());
    }

    public static PlayerData getData(UUID uuid){
        load(uuid);
        return data.get(uuid);
    }

}
