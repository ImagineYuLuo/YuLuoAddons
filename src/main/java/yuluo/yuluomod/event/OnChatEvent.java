package yuluo.yuluomod.event;


import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnChatEvent {

    static int frequency = 0;

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event){
        String msg = event.message.getUnformattedText();
        if(msg.contains("You Broke A Block !")){
            frequency++;
            if(frequency == 5){
                Minecraft.getMinecraft().ingameGUI.displayTitle(EnumChatFormatting.RED + "5 Times", "", 0, 0, 0);
                frequency = 0;
            }
        }
    }
}
