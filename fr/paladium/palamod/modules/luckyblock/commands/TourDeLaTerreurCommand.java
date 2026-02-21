package fr.paladium.palamod.modules.luckyblock.commands;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.bukkit.Bukkit;

public class TourDeLaTerreurCommand extends CommandBase {
  public String func_71517_b() {
    return "TDT";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "TDT";
  }
  
  public List func_71514_a() {
    return Arrays.asList(new String[0]);
  }
  
  public void func_71515_b(ICommandSender sender, String[] arguments) {
    if (sender instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)sender;
      try {
        if (Bukkit.getPlayer(player.func_110124_au()).hasPermission("palamod.cmd.tdt")) {
          PLuckyBlock.tourDeLaTerreurConfig.setX((int)player.field_70165_t);
          PLuckyBlock.tourDeLaTerreurConfig.setY((int)player.field_70163_u);
          PLuckyBlock.tourDeLaTerreurConfig.setZ((int)player.field_70161_v);
          ((EntityPlayerMP)sender)
            .func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.GOLD + "[" + EnumChatFormatting.GREEN + "LuckyBlock" + EnumChatFormatting.GOLD + "]" + EnumChatFormatting.GOLD + " Spawn de la TDT en "));
          ((EntityPlayerMP)sender).func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.GOLD + "[" + EnumChatFormatting.GREEN + "LuckyBlock" + EnumChatFormatting.GOLD + "]" + EnumChatFormatting.GOLD + " X:" + PLuckyBlock.tourDeLaTerreurConfig
                
                .getX()));
          ((EntityPlayerMP)sender).func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.GOLD + "[" + EnumChatFormatting.GREEN + "LuckyBlock" + EnumChatFormatting.GOLD + "]" + EnumChatFormatting.GOLD + " Y:" + PLuckyBlock.tourDeLaTerreurConfig
                
                .getY()));
          ((EntityPlayerMP)sender).func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.GOLD + "[" + EnumChatFormatting.GREEN + "LuckyBlock" + EnumChatFormatting.GOLD + "]" + EnumChatFormatting.GOLD + " Z:" + PLuckyBlock.tourDeLaTerreurConfig
                
                .getZ()));
        } 
      } catch (NullPointerException e) {
        PLuckyBlock.tourDeLaTerreurConfig.setX((int)player.field_70165_t);
        PLuckyBlock.tourDeLaTerreurConfig.setY((int)player.field_70163_u);
        PLuckyBlock.tourDeLaTerreurConfig.setZ((int)player.field_70161_v);
        ((EntityPlayerMP)sender)
          .func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.GOLD + "[" + EnumChatFormatting.GREEN + "LuckyBlock" + EnumChatFormatting.GOLD + "]" + EnumChatFormatting.GOLD + " Spawn de la TDT en "));
        ((EntityPlayerMP)sender).func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.GOLD + "[" + EnumChatFormatting.GREEN + "LuckyBlock" + EnumChatFormatting.GOLD + "]" + EnumChatFormatting.GOLD + " X:" + PLuckyBlock.tourDeLaTerreurConfig
              
              .getX()));
        ((EntityPlayerMP)sender).func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.GOLD + "[" + EnumChatFormatting.GREEN + "LuckyBlock" + EnumChatFormatting.GOLD + "]" + EnumChatFormatting.GOLD + " Y:" + PLuckyBlock.tourDeLaTerreurConfig
              
              .getY()));
        ((EntityPlayerMP)sender).func_146105_b((IChatComponent)new ChatComponentText(EnumChatFormatting.GOLD + "[" + EnumChatFormatting.GREEN + "LuckyBlock" + EnumChatFormatting.GOLD + "]" + EnumChatFormatting.GOLD + " Z:" + PLuckyBlock.tourDeLaTerreurConfig
              
              .getZ()));
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\commands\TourDeLaTerreurCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */