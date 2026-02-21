package fr.paladium.palamod.modules.troll.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class BaguetteCommand extends CommandBase {
  public static boolean active;
  
  public static ItemStack firework;
  
  public String func_71517_b() {
    return "baguette";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "baguette";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (!(sender instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)sender;
    if (player.func_70694_bm() == null || !(player.func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemFirework))
      return; 
    firework = player.func_70694_bm();
    active = !active;
    if (active) {
      sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§cPaladium§8] §bJoyeuse fête nationale !"));
    } else {
      sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§cPaladium§8] §cLa fête est finie, au travail !"));
    } 
  }
  
  public int func_82362_a() {
    return 0;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\troll\commands\BaguetteCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */