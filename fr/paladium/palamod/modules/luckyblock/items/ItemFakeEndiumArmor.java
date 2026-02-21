package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.palamod.modules.paladium.common.items.armors.ItemEndiumArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFakeEndiumArmor extends ItemEndiumArmor {
  public ItemFakeEndiumArmor(ItemArmor.ArmorMaterial material, int type, String texture, String model, Item item_repair) {
    super(material, type, texture, model, item_repair);
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {}
  
  public boolean isEndium() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemFakeEndiumArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */