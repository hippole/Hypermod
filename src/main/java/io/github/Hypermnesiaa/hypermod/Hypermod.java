package io.github.Hypermnesiaa.hypermod;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Hypermod.MODID, version = Hypermod.VERSION, clientSideOnly = true)
public class Hypermod {

    public static int GEXP = 0;

    public static final String MODID = "hypermod";
    public static final String VERSION = "1.5.1-BETA";

    private final RandomWord randomWord = new RandomWord();
    private final Coordinates coordinates = new Coordinates();
    private final TheMoon theMoon = new TheMoon();
    private final HypixelStatus hypixelStatus = new HypixelStatus();
    private final ApiKey apiKey = new ApiKey();
    private final GEXP gexp = new GEXP(this);

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new GEXP(this));
        try {
            ClientCommandHandler.instance.registerCommand(randomWord);
            ClientCommandHandler.instance.registerCommand(coordinates);
            ClientCommandHandler.instance.registerCommand(theMoon);
            ClientCommandHandler.instance.registerCommand(hypixelStatus);
            ClientCommandHandler.instance.registerCommand(apiKey);
            ClientCommandHandler.instance.registerCommand(gexp);

        } catch (Exception e) {
            System.err.println("[Hypermod] I ran into big problem. stacktrace be like:");
            e.printStackTrace();
        }
    }
}
