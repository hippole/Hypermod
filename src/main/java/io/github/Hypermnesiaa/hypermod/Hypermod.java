package io.github.Hypermnesiaa.hypermod;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Hypermod.MODID, version = Hypermod.VERSION, clientSideOnly = true)
public class Hypermod {

    public static final String MODID = "hypermod";
    public static final String VERSION = "1.4-BETA";

    private final randomWord randomWord = new randomWord();
    private final coordinates coordinates = new coordinates();
    private final theMoon theMoon = new theMoon();
    private final hypixelStatus hypixelStatus = new hypixelStatus();
    private final apiKey apiKey = new apiKey();


    @EventHandler
    public void init(FMLInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
        try {
            ClientCommandHandler.instance.registerCommand(randomWord);
            ClientCommandHandler.instance.registerCommand(coordinates);
            ClientCommandHandler.instance.registerCommand(theMoon);
            ClientCommandHandler.instance.registerCommand(hypixelStatus);
            ClientCommandHandler.instance.registerCommand(apiKey);

        } catch (Exception e) {
            System.err.println("[Hypermod] I ran into big problem. stacktrace be like:");
            e.printStackTrace();
        }
    }
}
