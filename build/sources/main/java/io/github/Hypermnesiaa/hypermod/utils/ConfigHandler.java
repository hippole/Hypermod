package io.github.Hypermnesiaa.hypermod.utils;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    public static Configuration config;

    public static Boolean zombiesEnabled;
    public static String requeValue;
    public static String randomWordFilePath;
    public static String hypixelApiKey;
    public static Boolean randomHippoInBottomLeftCornerEnabled;

    public static void init (File file) {
        config = new Configuration(file);
        syncConfig();
    }

    public static void syncConfig() {
        String category;

        category = "Hypermod";
        zombiesEnabled = config.getBoolean("enabled", category, true, "Enable AngryZombie.");
        requeValue = config.getString("requeValue", category, "SOLO_INSANE", "The game you are to be sent to upon executing the /rq command.");
        randomWordFilePath = config.getString("randomWordFilePath", category, "", "The randomWord command requires a file full of words on their individual line to select a random word, Put the file path for that file here.");
        hypixelApiKey = config.getString("hypixelApiKey", category, "", "Your Hypixel Api-Key, run /api new in game to obtain your apikey. Beware: While putting anything into this field will work it may break other commands!");
        randomHippoInBottomLeftCornerEnabled = config.getBoolean("randomHippoInBottomLeftCornerEnabled", category, true, "Whether or not the gray \"hippo\" in the bottom left corner of your screen is enabled.");
        config.save();
    }
}
