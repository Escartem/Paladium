package fr.paladium.palamod.modules.luckyblock.commands.luckystats;

import fr.paladium.palamod.api.BlocksRegister;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TrophyHalloweenCommand extends CommandBase {
  public String func_71517_b() {
    return "trophyhalloween";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "trophyhalloween";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (sender instanceof EntityPlayerMP && args.length == 1) {
      EntityPlayerMP player = (EntityPlayerMP)sender;
      ItemStack item = new ItemStack(BlocksRegister.TROPHYHALLOWEEN);
      NBTTagCompound compound = new NBTTagCompound();
      String owner = args[0];
      compound.func_74778_a("owner", owner);
      item.func_77982_d(compound);
      player.field_71071_by.func_70441_a(item);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\commands\luckystats\TrophyHalloweenCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */