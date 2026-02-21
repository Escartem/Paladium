package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.enchantments.EnchantmentExperience;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class EnchantTooltipHandler {
  private static final String EMPTY_STRING = "";
  
  private static final String ENCHANT_NBT_TAG = "StoredEnchantments";
  
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
    if (!(item instanceof net.minecraft.item.ItemEnchantedBook))
      return; 
    if (!compound.func_150297_b("StoredEnchantments", 9))
      return; 
    NBTTagList enchantmentList = compound.func_150295_c("StoredEnchantments", 10);
    if (enchantmentList == null)
      return; 
    for (int i = 0; i < enchantmentList.func_74745_c(); ) {
      NBTTagCompound enchantTag = enchantmentList.func_150305_b(i);
      short id = enchantTag.func_74765_d("id");
      short level = enchantTag.func_74765_d("lvl");
      if (id != PLuckyBlock.EXPERIENCE.field_77352_x) {
        i++;
        continue;
      } 
      tooltip.add("");
      tooltip.addAll(getDescriptionByLevel(level));
    } 
  }
  
  public List<String> getDescriptionByLevel(int level) {
    List<String> array = new ArrayList<>();
    if (level <= 0 || level > EnchantmentExperience.MULTIPLIERS.length)
      return array; 
    String percent = String.valueOf(EnchantmentExperience.MULTIPLIERS[level - 1]);
    array = Arrays.asList(new String[] { "Peut être combiné à une épée.", "Permet de gagner " + percent + "%", "d'expérience en plus en tuant un monstre." });
    return array;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\events\EnchantTooltipHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */