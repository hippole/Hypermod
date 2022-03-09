package io.github.hippole.hypermod.commands;

import io.github.hippole.hypermod.utils.ConfigHandler;
import io.github.hippole.hypermod.utils.Misc;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Wordle extends CommandBase {

    ArrayList<String> list = new ArrayList();
    boolean gameStarted = false;
    boolean custom = false;
    int gameTries;
    String gameWord;

    public String getCommandDescription() {
        return "Plays Wordle, in minecraft.";
    }

    public String getCommandAlias() {
        return "";
    }

    @Override
    public String getCommandName() {
        return "wordle";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName() + " [(optional) custom (optional) tries]]";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("custom")) {
                        if (ConfigHandler.wordFilePath.equalsIgnoreCase("")) {
                            Misc.raiseError("There is no file path specified in \"Hypermod.cfg\". Add the file path the config file then try again.");
                            return;
                        } else {
                            custom = true;
                            try {
                                try {
                                    try {
                                        if (gameStarted) {
                                            guessWord(args[0]);
                                        } else {
                                            startGame(getWord(), Integer.parseInt(args[1]));
                                        }
                                    } catch (ArrayIndexOutOfBoundsException e) {
                                        startGame(getWord(), Integer.parseInt(args[1]));
                                    }
                                } catch (NumberFormatException e) {
                                    custom = false;
                                    Misc.raiseError("Argument \"tries\" was not a number.");
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (args.length == 1) {
                                Misc.raiseError("Missing arguments. To make a custom Wordle game use /wordle custom (tries)");
                            }
                        }
                    }
        } else {
                try {
                    try {
                        if (gameStarted) {
                            guessWord(args[0]);
                        } else {
                            startGame(getWord(), 6);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        startGame(getWord(), 6);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    public void startGame(String word, int tries) throws IOException {
        gameStarted = true;
        gameTries = tries;
        gameWord = word;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "Wordle! Guess using /wordle (your guess)\n" +
                EnumChatFormatting.AQUA + "Your word is " + EnumChatFormatting.GOLD + word.length() + EnumChatFormatting.AQUA + " characters long"));
    }


    public void buildBoard(String word, String guess) {
        StringBuilder builder = new StringBuilder();
        ArrayList<String> characterList = new ArrayList<>(Arrays.asList(word.split("")));
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess.charAt(i)) {
                characterList.remove(String.valueOf(guess.charAt(i)));
                builder.append(EnumChatFormatting.GREEN + "[" + guess.charAt(i) + "]");
            } else if (characterList.contains(String.valueOf(guess.charAt(i)))) {
                characterList.remove(String.valueOf(guess.charAt(i)));
                builder.append(EnumChatFormatting.YELLOW + "[" + guess.charAt(i) + "]");
            } else {
                builder.append(EnumChatFormatting.GRAY + "[" + guess.charAt(i) + "]");
            }
        }
        list.add(builder.toString());
    }

    public void printBoard(String word, String guess) {
        buildBoard(word,guess);
        for (String e : list) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(e));
        }
    }

    public String getWord() throws IOException {
        String word;
        RandomAccessFile randomAccessFile;
        if (custom) {
            randomAccessFile = new RandomAccessFile(ConfigHandler.wordFilePath, "r");
            long length = randomAccessFile.length();
            int lines = Misc.randomWithRange(0, (int) length);
            randomAccessFile.seek(lines);
            //idfk why having it like 50000 times fixes it but dont ask questions
            randomAccessFile.readLine();
            randomAccessFile.readLine();
            word = randomAccessFile.readLine();
            return word;
        } else {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classloader.getResourceAsStream("assets/hypermod/wordle.txt");
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            ArrayList<String> words = new ArrayList<>();
            for (String line; (line = reader.readLine()) != null; ) {
                words.add(line);
            }
            int rnd = new Random().nextInt(words.toArray().length);
            return words.get(rnd);
        }
    }

        public void guessWord(String guess) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "====================="));
                if (guess.length() != gameWord.length()) {
                    Misc.raiseError("Guess is not " + gameWord.length() + " characters long");
                } else if (guess.equalsIgnoreCase(gameWord)) {
                    printBoard(gameWord, guess);
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "====================="));
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You win! The correct word was " +
                            EnumChatFormatting.GOLD + gameWord + "!"));
                    list.clear();
                    custom = false;
                    gameStarted = false;
                } else {
                    gameTries--;
                    printBoard(gameWord, guess);
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + String.valueOf(gameTries) + " guesses remaining."));
                    if (gameTries == 0) {
                        Misc.playCustomSound("errorspam");
                        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "====================="));
                        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You ran out of tries! The correct word was: \"" +
                                EnumChatFormatting.GOLD + gameWord + EnumChatFormatting.RED + "\"."));
                        list.clear();
                        custom = false;
                        gameStarted = false;
                    }
                }
        }



}
