package fr.paladium.palamod.modules.luckyblock.events.june.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class ToolTipHandler {
  public static final String LINE = "§7-------------------";
  
  public static final String UNDEFINED = "§cNon défini";
  
  @SideOnly(Side.CLIENT)
  public void onToolTip(ItemTooltipEvent event) {
    EntityPlayer player = event.entityPlayer;
    ItemStack item = event.itemStack;
    List<String> tooltip = event.toolTip;
    if (player == null || event.itemStack == null || event.itemStack.func_77973_b() == null || tooltip == null)
      return; 
    addInformation(item, player, tooltip);
  }
  
  public void addInformation(ItemStack stack, EntityPlayer player, List<String> list) {
    NBTTagCompound nbt = stack.func_77978_p();
    String score = "§eScore: §c";
    String maximalScore = "§eScore maximal: §c";
    if (nbt == null) {
      score = score + "§cNon défini";
      maximalScore = maximalScore + "§cNon défini";
    } else {
      if (nbt.func_74764_b("score")) {
        score = score + nbt.func_74762_e("score");
      } else {
        score = score + "§cNon défini";
      } 
      if (nbt.func_74764_b("maximalScore")) {
        maximalScore = maximalScore + nbt.func_74762_e("maximalScore");
      } else {
        maximalScore = maximalScore + "§cNon défini";
      } 
    } 
    list.add("§7-------------------");
    list.add(score);
    list.add(maximalScore);
    list.add("§7-------------------");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\june\client\ToolTipHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */