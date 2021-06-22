package me.zach.databank;

import me.zach.databank.saver.SaveManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class DBCore extends JavaPlugin {
    public static final String HOST = "127.0.0.1";
    public static final String PORT = "3306";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static final String DATABASE = "testdb";
    public static final boolean USE_SSL = true;
    public static DBCore instance;
    public void onEnable(){
        instance = this;
        Bukkit.getPluginManager().registerEvents(new SaveManager(),this);
    }

    public void onDisable(){
        SaveManager.onDisable();
    }

}
