package fr.paladium.palamod.modules.luckyblock.commands.luckystats;

import fr.paladium.palamod.api.BlocksRegister;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class TrophyFindiumCommand extends CommandBase {
  public String func_71517_b() {
    return "trophyfindium";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "trophyfindium";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (sender instanceof EntityPlayerMP && args.length == 2) {
      EntityPlayerMP player = (EntityPlayerMP)sender;
      try {
        int level = Integer.valueOf(args[0]).intValue();
        if (level >= 0 && level < 4) {
          ItemStack item = new ItemStack(BlocksRegister.TROPHY_FINDIUM, 1, level);
          NBTTagCompound compound = new NBTTagCompound();
          compound.func_74778_a("owner", args[1]);
          item.func_77982_d(compound);
          player.field_71071_by.func_70441_a(item);
        } else {
          player.func_145747_a((IChatComponent)new ChatComponentText("§e" + args[0] + " §cn'est pas compris entre 0 & 4"));
        } 
      } catch (Exception e) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§e" + args[0] + " §cn'est pas un nombre"));
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\commands\luckystats\TrophyFindiumCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */