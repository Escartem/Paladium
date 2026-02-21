package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.client.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palamod.api.BlocksRegister;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class ItemTooltipHandler {
  private static final String EMPTY_STRING = "";
  
  @SubscribeEvent
  public void onTooltip(ItemTooltipEvent event) {
    EntityPlayer player = event.entityPlayer;
    if (player == null || event.itemStack == null || event.itemStack.func_77973_b() == null || event.toolTip == null)
      return; 
    ItemStack stack = event.itemStack;
    List<String> tooltip = event.toolTip;
    Item item = stack.func_77973_b();
    NBTTagCompound compound = stack.func_77978_p();
    if (compound == null || compound.func_82582_d())
      return; 
    Block block = Block.func_149634_a(item);
    if (block == null)
      return; 
    if (block.equals(BlocksRegister.CHRISTMAS_WREATH)) {
      tooltip.add("");
      if (compound.func_74764_b("durability")) {
        int durability = compound.func_74762_e("durability");
        tooltip.add("§cDurabilité: §e" + durability + " / " + 'd');
      } 
      if (compound.func_74764_b("nextUseMillis")) {
        long nextUseMillis = compound.func_74763_f("nextUseMillis");
        long cooldown = nextUseMillis - System.currentTimeMillis();
        if (cooldown < 0L)
          cooldown = 0L; 
        tooltip.add("§cProchain usage: §e" + DurationConverter.fromMillisToString(cooldown));
      } 
      return;
    } 
    if (block.equals(BlocksRegister.GIFT_CHEST)) {
      tooltip.add("");
      if (compound.func_74764_b("nextUseMillis")) {
        long nextUseMillis = compound.func_74763_f("nextUseMillis");
        long cooldown = nextUseMillis - System.currentTimeMillis();
        if (cooldown < 0L)
          cooldown = 0L; 
        tooltip.add("§cProchain usage: §e" + DurationConverter.fromMillisToString(cooldown));
      } 
      return;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\client\listeners\ItemTooltipHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */