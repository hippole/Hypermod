package io.github.Hypermnesiaa.hypermod.Utils;

public class Define {

    public int randomWithRange (int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
}