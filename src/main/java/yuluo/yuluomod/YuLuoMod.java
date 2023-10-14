package yuluo.yuluomod;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

//Register
import yuluo.yuluomod.event.BreakBlockEvent;
import yuluo.yuluomod.event.OnChatEvent;
import yuluo.yuluomod.commands.testCommand;


@Mod(modid = YuLuoMod.MOD_ID, name = YuLuoMod.MOD_NAME, version = YuLuoMod.VERSION, clientSideOnly = true)
public class YuLuoMod
{
    // YuLuoAddons --- Created By YuLuo ---
    public static final String MOD_ID = "yuluomod";
    public static final String MOD_NAME = "YuLuoMod";
    public static final String VERSION = "1.8.9";
    
    @EventHandler
    public void init(FMLInitializationEvent e)
    {
        MinecraftForge.EVENT_BUS.register(new BreakBlockEvent());
        MinecraftForge.EVENT_BUS.register(new OnChatEvent());
        //Commands Register
        ClientCommandHandler.instance.registerCommand(new testCommand());
    }
}
