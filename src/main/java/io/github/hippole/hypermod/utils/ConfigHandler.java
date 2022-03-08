package io.github.hippole.hypermod.utils;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    public static Configuration config;

    public static Boolean zombiesEnabled;
    public static String requeValue;
    public static String wordFilePath;
    public static String hypixelApiKey;
    public static Boolean randomHippoInBottomLeftCornerEnabled;

    public static void init (File file) {
        config = new Configuration(file);
        syncConfig();
    }

    public static void syncConfig() {
        String category;

        category = "Hypermod";
        zombiesEnabled = config.getBoolean("enabled", category, true, "Enable Hypixel zombies custom sounds.");
        requeValue = config.getString("requeValue", category, "SOLO_INSANE", "The game you are to be sent to upon executing the /rq command.");
        wordFilePath = config.getString("wordFilePath", category, "", "File path for commands that require a custom filepath.");
        hypixelApiKey = config.getString("hypixelApiKey", category, "", "Your Hypixel Api-Key, run /api new in game to obtain your apikey. Beware: While putting anything into this field will work it may break other commands!");
        randomHippoInBottomLeftCornerEnabled = config.getBoolean("randomHippoInBottomLeftCornerEnabled", category, true, "Whether or not the gray \"hippo\" in the bottom left corner of your screen is enabled.");
        config.save();
    }
}
