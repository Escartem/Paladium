package fr.paladium.palamod.modules.palapass;

import fr.paladium.palapass.common.quest.misc.FishingQuest;
import fr.paladium.palapass.common.quest.misc.FurnaceCraftQuest;
import fr.paladium.palapass.common.quest.misc.ItemCraftQuest;
import fr.paladium.palapass.common.quest.misc.ItemEnchantQuest;
import fr.paladium.palapass.common.quest.misc.ItemUseQuest;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Palapass {
  public static ConcurrentHashMap<UUID, Long> lastFurnaceEvent = new ConcurrentHashMap<>();
  
  public static ConcurrentHashMap<UUID, Long> lastCraftEvent = new ConcurrentHashMap<>();
  
  public static ConcurrentHashMap<UUID, Long> lastBreakEvent = new ConcurrentHashMap<>();
  
  public static void processFishing(EntityPlayer field_146042_b, ItemStack entityItem) {
    FishingQuest.trigger(field_146042_b, entityItem, 1);
  }
  
  public static void processItemEnchant(EntityPlayer p_75140_1_, ItemStack is) {
    ItemEnchantQuest.trigger(p_75140_1_, is, 1);
  }
  
  public static void processItemUse(EntityPlayer p_77654_3_, ItemStack p_77654_1_, int i) {
    ItemUseQuest.trigger(p_77654_3_, p_77654_1_, i);
  }
  
  public static void processFurnaceCraft(EntityPlayer player, ItemStack smelting) {
    FurnaceCraftQuest.trigger(player, smelting, 1);
  }
  
  public static void processFurnaceCraft(EntityPlayer player, ItemStack smelting, int quantity) {
    FurnaceCraftQuest.trigger(player, smelting, quantity);
  }
  
  public static void processItemCraft(EntityPlayer player, ItemStack craft, int quantity) {
    ItemCraftQuest.trigger(player, craft, quantity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palapass\Palapass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */