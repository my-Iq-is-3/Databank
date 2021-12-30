package me.zach.databank;

import me.zach.databank.saver.SaveManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class DBCore extends JavaPlugin {
    public static final String DATABASE = "FallenData";
    public static final String COLLECTION = "PlayerData";
    private SaveManager saveManager;
    private static DBCore instance;

    public static DBCore getInstance(){
        return instance;
    }

    public void onEnable(){
        instance = this;
        saveManager = new SaveManager();
        Bukkit.getPluginManager().registerEvents(saveManager,this);
    }

    public SaveManager getSaveManager(){
        return saveManager;
    }

    public void onDisable(){
        saveManager.onDisable();
    }
}
