package me.zach.databank.saver;
/**
  A class to store database keys. Do <strong>NOT</strong> change any of these values unless you know EXACTLY what you are doing.
 */
public class Key{
    public static final String UUID = "_id";

    public static final String SELECTED_CLASS = "selected_class";
    public static final String GEMS = "gems";
    public static final String SOULS = "souls";

    public static final String TANK_LVL = "tank_level";
    public static final String SCOUT_LVL = "scout_level";
    public static final String CORRUPTER_LVL = "corrupter_level";
    public static final String WIZARD_LVL = "wizard_level";
    
    public static final String TANK_XP = "tank_xp";
    public static final String TANK_XPR = "tank_xp_required";
    
    public static final String CORRUPTER_XP = "corrupter_xp";
    public static final String CORRUPTER_XPR = "corrupter_xp_required";
    
    public static final String SCOUT_XP = "scout_xp";
    public static final String SCOUT_XPR = "scout_xp_required";
    
    public static final String WIZARD_XP = "wizard_xp";
    public static final String WIZARD_XPR = "wizard_xp_required";

    public static final String WIZARD = "wizard";
    public static final String SCOUT = "scout";
    public static final String CORRUPTER = "corrupter";
    public static final String TANK = "tank";


    public static final String ARTIFACT_DATA = "artifact_data";

    public static final String ARTIFACT_DATA_RAW = "adata_raw";

    public static final String RISEN_DATA = "risen_data";

    public static class RisenKey {
        public static final String WINS_TO_NEXT_SLOT = "wins_to_next_slot";
        public static final String ABILITY_SLOTS = "ability_slots";
        public static final String SELECTED_ABILITIES = "selected_abilities";
        public static final String BOSS_READY = "boss_is_ready";
        public static final String EARNED_REWARDS = "earned_rewards";
    }

    public static final int MAX_XP = 999999;
}
