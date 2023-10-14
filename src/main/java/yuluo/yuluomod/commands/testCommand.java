package yuluo.yuluomod.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import java.util.Timer;
import java.util.TimerTask;

public class testCommand extends CommandBase {

    static int Time = 0;

    @Override
    public String getCommandName() {
        return "yla";
        // yla mean YuLuoAddons qwq
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "/yla";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] strings) {

        if(strings.length == 0) {
            sender.addChatMessage(new ChatComponentText("Too few arguments"));
        }else if (strings[0].equalsIgnoreCase("test")) {
            sender.addChatMessage(new ChatComponentText("This is the test."));
        }else if (strings[0].equalsIgnoreCase("ramen")) {
            Minecraft.getMinecraft().ingameGUI.displayTitle(EnumChatFormatting.RED + "Ramen so lao...", "", 0, 0, 0);
        }else{
            sender.addChatMessage(new ChatComponentText("\"" + strings[0] + "\" was a unknown command qwq ! "));
        }
    }

    //Giving Permission To Use Commands
    @Override
    public int getRequiredPermissionLevel(){
        return 0;
    }

}
