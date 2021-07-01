package me.zach.databank.saver;

import com.mongodb.BasicDBList;
import me.zach.artifacts.gui.inv.ArtifactData;
import me.zach.databank.DB;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.UUID;

@SuppressWarnings("unused")
public class PlayerData {
    UUID uuid;
    int gems;
    int souls;

    String currentClass;
    int tankL;
    int tankXPR;
    int tankXP;
    int wizardL;
    int wizardXPR;
    int wizardXP;
    int scoutL;
    int scoutXPR;
    int scoutXP;
    int corL;
    int corXPR;
    int corXP;
    ArtifactData ad;

    public PlayerData(UUID uuid){
        System.out.println("loading pdata");

        this.uuid = uuid;
        this.gems = getInt(Key.GEMS);

        this.souls = getInt(Key.SOULS);
        this.currentClass = getString(Key.SELECTED_CLASS);
        this.tankL = getInt(Key.TANK_LVL,1);
        this.wizardL = getInt(Key.WIZARD_LVL,1);
        this.corL = getInt(Key.CORRUPTER_LVL,1);
        this.scoutL = getInt(Key.SCOUT_LVL,1);

        this.tankXPR = getInt(Key.TANK_XPR,100);
        this.tankXP = getInt(Key.TANK_XP);

        this.wizardXP = getInt(Key.WIZARD_XP);
        this.wizardXPR = getInt(Key.WIZARD_XPR,100);

        this.corXP = getInt(Key.CORRUPTER_XP);
        this.corXPR = getInt(Key.CORRUPTER_XPR,100);

        this.scoutXP = getInt(Key.SCOUT_XP);
        this.scoutXPR = getInt(Key.SCOUT_XPR,100);
        this.ad = new ArtifactData(uuid);



    }


    public ArtifactData getAD() {
        return ad;
    }

    public void setAD(ArtifactData ad) {
        this.ad = ad;
    }


    public UUID getUuid() {
        return uuid;
    }

    public int getGems() {
        return gems;
    }

    public void setGems(int gems) {
        this.gems = gems;
    }

    public int getSouls() {
        return souls;
    }

    public void setSouls(int souls) {
        this.souls = souls;
    }

    public String getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(String currentClass) {
        this.currentClass = currentClass;
    }

    public int getTankL() {
        return tankL;
    }

    public void setTankL(int tankL) {
        this.tankL = tankL;
    }

    public int getTankXPR() {
        return tankXPR;
    }

    public void setTankXPR(int tankXPR) {
        this.tankXPR = tankXPR;
    }

    public int getTankXP() {
        return tankXP;
    }

    public void setTankXP(int tankXP) {
        this.tankXP = tankXP;
    }

    public int getWizardL() {
        return wizardL;
    }

    public void setWizardL(int wizardL) {
        this.wizardL = wizardL;
    }

    public int getWizardXPR() {
        return wizardXPR;
    }

    public void setWizardXPR(int wizardXPR) {
        this.wizardXPR = wizardXPR;
    }

    public int getWizardXP() {
        return wizardXP;
    }

    public void setWizardXP(int wizardXP) {
        this.wizardXP = wizardXP;
    }

    public int getScoutL() {
        return scoutL;
    }

    public void setScoutL(int scoutL) {
        this.scoutL = scoutL;
    }

    public int getScoutXPR() {
        return scoutXPR;
    }

    public void setScoutXPR(int scoutXPR) {
        this.scoutXPR = scoutXPR;
    }

    public int getScoutXP() {
        return scoutXP;
    }

    public void setScoutXP(int scoutXP) {
        this.scoutXP = scoutXP;
    }

    public int getCorL() {
        return corL;
    }

    public void setCorL(int corL) {
        this.corL = corL;
    }

    public int getCorXPR() {
        return corXPR;
    }

    public void setCorXPR(int corXPR) {
        this.corXPR = corXPR;
    }

    public int getCorXP() {
        return corXP;
    }

    public void setCorXP(int corXP) {
        this.corXP = corXP;
    }

    public void setClassXP(String clazz, int xp){

        String lower = clazz.toLowerCase();
        if(lower.equals(Key.SCOUT)){
            setScoutXP(xp);
        }else if(lower.equals(Key.CORRUPTER)){
            setCorXP(xp);
        }else if(lower.equals(Key.WIZARD)){
            setWizardXP(xp);
        }else if(lower.equals(Key.TANK)){
            setTankXP(xp);
        }
    }
    public void setClassXPR(String clazz, int xp){
        String lower = clazz.toLowerCase();
        if(lower.equals(Key.SCOUT)){
            setScoutXPR(xp);
        }else if(lower.equals(Key.CORRUPTER)){
            setCorXPR(xp);
        }else if(lower.equals(Key.WIZARD)){
            setWizardXPR(xp);
        }else if(lower.equals(Key.TANK)){
            setTankXPR(xp);
        }
    }

    public int getClassXP(String clazz){
        String lower = clazz.toLowerCase();
        if(lower.equals(Key.SCOUT)){
            return getScoutXP();
        }else if(lower.equals(Key.CORRUPTER)){
            return getCorXP();
        }else if(lower.equals(Key.WIZARD)){
            return getWizardXP();
        }else if(lower.equals(Key.TANK)){
            return getTankXP();
        }else{
            return 0;
        }
    }
    public int getClassXPR(String clazz){
        String lower = clazz.toLowerCase();
        if(lower.equals(Key.SCOUT)){
            return getScoutXPR();
        }else if(lower.equals(Key.CORRUPTER)){
            return getCorXPR();
        }else if(lower.equals(Key.WIZARD)){
            return getWizardXPR();
        }else if(lower.equals(Key.TANK)){
            return getTankXPR();
        }else{
            return 0;
        }
    }
    public int getClassLevel(String clazz){
        String lower = clazz.toLowerCase();
        if(lower.equals(Key.SCOUT)){
            return getScoutL();
        }else if(lower.equals(Key.CORRUPTER)){
            return getCorL();
        }else if(lower.equals(Key.WIZARD)){
            return getWizardL();
        }else if(lower.equals(Key.TANK)){
            return getTankL();
        }else{
            return 0;
        }
    }


    public void setClassLevel(String clazz,int lvl) {
        System.out.println("setting class level " + clazz);
        String lower = clazz.toLowerCase();
        if(lower.equals(Key.SCOUT)){
            setScoutL(lvl);
        }else if(lower.equals(Key.CORRUPTER)){
            setCorL(lvl);
        }else if(lower.equals(Key.WIZARD)){
            setWizardL(lvl);
        }else if(lower.equals(Key.TANK)){
            setTankL(lvl);
        }
    }



    public PlayerData(Player player){
        this(player.getUniqueId());
    }



    public Player getPlayer(){
        return Bukkit.getPlayer(uuid);
    }

    private Object get(String key){
        return DB.PLAYER_DATA.get(this.uuid,key);
    }

    private int getInt(String key){
        System.out.println("getting (int) uuid " + uuid + ", key=" + key + ", obj=" + DB.PLAYER_DATA.getInt(this.uuid,key));
        return DB.PLAYER_DATA.getInt(this.uuid,key);
    }

    private String getString(String key){
        System.out.println("getting (string) uuid " + uuid + ", key=" + key + ", obj=" + DB.PLAYER_DATA.getString(this.uuid,key));
        return DB.PLAYER_DATA.getString(this.uuid,key);
    }

    private int getInt(String key, int def){
        System.out.println("getting (int) uuid " + uuid + ", key=" + key + ", obj=" + DB.PLAYER_DATA.getInt(this.uuid,key) + ", default=" + def);
        return DB.PLAYER_DATA.getInt(uuid,key,def);
    }



}
