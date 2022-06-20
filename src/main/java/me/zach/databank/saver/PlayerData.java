package me.zach.databank.saver;

import me.gabriel.Traits.data.TraitsData;
import me.zach.DesertMC.GameMechanics.EXPMilesstones.MilestonesData;
import me.zach.DesertMC.Prefix;
import me.zach.DesertMC.Utils.RankUtils.Rank;
import me.zach.DesertMC.cosmetics.CosmeticData;
import me.zach.artifacts.gui.inv.ArtifactData;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.fallenmc.risenboss.main.data.RisenData;

import java.util.*;

@SuppressWarnings("unused")
public class PlayerData {
    @BsonId
    UUID uuid;
    @BsonProperty(Key.GEMS)
    int gems = 100;
    @BsonProperty(Key.SOULS)
    int souls = 0;
    @BsonProperty(Key.SELECTED_CLASS)
    String currentClass = Key.SCOUT;
    @BsonProperty(Key.TANK_LVL)
    int tankL = 1;
    @BsonProperty(Key.TANK_XPR)
    int tankXPR = 100;
    @BsonProperty(Key.TANK_XP)
    int tankXP = 0;
    @BsonProperty(Key.WIZARD_LVL)
    int wizardL = 1;
    @BsonProperty(Key.WIZARD_XPR)
    int wizardXPR = 100;
    @BsonProperty(Key.WIZARD_XP)
    int wizardXP = 0;
    @BsonProperty(Key.SCOUT_LVL)
    int scoutL = 1;
    @BsonProperty(Key.SCOUT_XPR)
    int scoutXPR = 100;
    @BsonProperty(Key.SCOUT_XP)
    int scoutXP = 0;
    @BsonProperty(Key.CORRUPTER_LVL)
    int corL = 1;
    @BsonProperty(Key.CORRUPTER_XPR)
    int corXPR = 100;
    @BsonProperty(Key.CORRUPTER_XP)
    int corXP = 0;
    @BsonProperty(Key.ARTIFACT_DATA)
    ArtifactData artifactData = new ArtifactData();
    @BsonProperty(Key.RISEN_DATA)
    RisenData risenData = new RisenData();
    @BsonProperty(Key.TRAITS_DATA)
    TraitsData traitsData = new TraitsData();
    @BsonProperty(Key.MILESTONES_DATA)
    MilestonesData milestonesData = new MilestonesData();
    @BsonProperty(Key.RANK)
    Rank rank = null;
    @BsonProperty(Key.COSMETIC_DATA)
    CosmeticData cosmeticData = new CosmeticData();
    @BsonProperty(Key.UNLOCKED_TITLES)
    Set<Prefix> titles = new HashSet<>();
    @BsonProperty(Key.SELECTED_TITLE)
    Prefix title;
    @BsonProperty("souls_spent")
    int soulsSpent = 0;
    @BsonProperty("money_away")
    int gemsGottenWhileAway = 0;
    @BsonProperty("muted")
    boolean muted = false;

    public boolean isMuted(){
        return muted;
    }

    public void setMuted(boolean muted){
        this.muted = muted;
    }

    public int getGemsGottenWhileAway(){
        return gemsGottenWhileAway;
    }

    public void setGemsGottenWhileAway(int gemsGottenWhileAway){
        this.gemsGottenWhileAway = gemsGottenWhileAway;
    }

    public int getSoulsSpent(){
        return soulsSpent;
    }

    public void setSoulsSpent(int soulsSpent){
        this.soulsSpent = soulsSpent;
    }

    public void addSoulsSpent(int toAdd){
        if(soulsSpent > 0 && soulsSpent + toAdd <= 0){
            setSoulsSpent(Integer.MAX_VALUE);
        }else setSoulsSpent(soulsSpent + toAdd);
    }

    public CosmeticData getCosmeticData(){
        return cosmeticData;
    }

    public void setCosmeticData(CosmeticData cosmeticData){
        this.cosmeticData = cosmeticData;
    }

    public Rank getRank(){
        return rank;
    }

    public void setRank(Rank rank){
        this.rank = rank;
    }

    public Set<Prefix> getTitles(){
        return titles;
    }

    public void setTitles(Set<Prefix> titles){
        this.titles = titles;
    }

    public Prefix getTitle(){
        return title;
    }

    public void setTitle(Prefix title){
        this.title = title;
    }


    public ArtifactData getArtifactData() {
        return artifactData;
    }

    public void setArtifactData(ArtifactData artifactData) {
        this.artifactData = artifactData;
    }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid){
        this.uuid = uuid;
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

    public RisenData getRisenData(){
        return risenData;
    }

    public void setRisenData(RisenData risenData){
        this.risenData = risenData;
    }

    public TraitsData getTraitsData(){
        return traitsData;
    }

    public void setTraitsData(TraitsData traitsData){
        this.traitsData = traitsData;
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
        switch(lower){
            case Key.SCOUT:
                return getScoutXPR();
            case Key.CORRUPTER:
                return getCorXPR();
            case Key.WIZARD:
                return getWizardXPR();
            case Key.TANK:
                return getTankXPR();
            default:
                return 0;
        }
    }
    public int getClassLevel(String clazz){
        String lower = clazz.toLowerCase();
        switch(lower){
            case Key.SCOUT:
                return getScoutL();
            case Key.CORRUPTER:
                return getCorL();
            case Key.WIZARD:
                return getWizardL();
            case Key.TANK:
                return getTankL();
            default:
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
    @BsonIgnore
    public Player getPlayer(){
        return Bukkit.getPlayer(uuid);
    }

    public PlayerData(){

    }

    public MilestonesData getMilestonesData(){
        return milestonesData;
    }

    public void setMilestonesData(MilestonesData milestonesData){
        this.milestonesData = milestonesData;
    }

    public PlayerData(UUID uuid){
        this.uuid = uuid;
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof PlayerData)) return false;
        PlayerData that = (PlayerData) o;
        return gems == that.gems && souls == that.souls && tankL == that.tankL && tankXPR == that.tankXPR && tankXP == that.tankXP && wizardL == that.wizardL && wizardXPR == that.wizardXPR && wizardXP == that.wizardXP && scoutL == that.scoutL && scoutXPR == that.scoutXPR && scoutXP == that.scoutXP && corL == that.corL && corXPR == that.corXPR && corXP == that.corXP && Objects.equals(uuid, that.uuid) && Objects.equals(currentClass, that.currentClass) && Objects.equals(risenData, that.risenData) && Objects.equals(artifactData, that.artifactData);
    }
}