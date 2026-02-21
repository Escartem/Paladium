package fr.paladium.palamod.modules.paladium.commands;

import fr.paladium.palamod.api.BlocksRegister;
import java.util.regex.Pattern;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class StatueCommand extends CommandBase {
  private static final Pattern UUID_REGEX_PATTERN = Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");
  
  public String func_71517_b() {
    return "statue";
  }
  
  public String func_71518_a(ICommandSender p_71518_1_) {
    return "statue [UUID]";
  }
  
  public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) {
    if (p_71515_2_.length >= 1 && 
      isValidUUID(p_71515_2_[0])) {
      EntityPlayer player = (EntityPlayer)p_71515_1_;
      NBTTagCompound compound = new NBTTagCompound();
      compound.func_74778_a("playerUUID", p_71515_2_[0]);
      ItemStack stack = new ItemStack(BlocksRegister.STATUE, 1);
      stack.func_77982_d(compound);
      player.field_71071_by.func_70441_a(stack);
    } 
  }
  
  public static boolean isValidUUID(String str) {
    if (str == null)
      return false; 
    return UUID_REGEX_PATTERN.matcher(str).matches();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\commands\StatueCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */