package yuluo.yuluomod.event;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BreakBlockEvent {

    @SubscribeEvent
    public void sendMessage(BreakEvent event){
        event.getPlayer()
                .addChatComponentMessage(
                        new ChatComponentText(EnumChatFormatting.WHITE + "You Broke A Block !")
                );
    }
}
