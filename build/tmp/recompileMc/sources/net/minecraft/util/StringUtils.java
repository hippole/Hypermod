package net.minecraft.util;

import java.util.regex.Pattern;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StringUtils
{
    private static final Pattern patternControlCode = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

    /**
     * Returns the time elapsed for the given number of ticks, in "mm:ss" format.
     */
    @SideOnly(Side.CLIENT)
    public static String ticksToElapsedTime(int ticks)
    {
        int i = ticks / 20;
        int j = i / 60;
        i = i % 60;
        return i < 10 ? j + ":0" + i : j + ":" + i;
    }

    @SideOnly(Side.CLIENT)
    public static String stripControlCodes(String text)
    {
        return patternControlCode.matcher(text).replaceAll("");
    }

    /**
     * Returns a value indicating whether the given string is null or empty.
     */
    public static boolean isNullOrEmpty(String string)
    {
        return org.apache.commons.lang3.StringUtils.isEmpty(string);
    }
}