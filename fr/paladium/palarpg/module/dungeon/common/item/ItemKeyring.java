package fr.paladium.palarpg.module.dungeon.common.item;

import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import fr.paladium.palarpg.module.equipment.common.item.RPGStatApplyType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemKeyring extends Item implements IRPGItem {
  public void register() {}
  
  public String getId() {
    return "";
  }
  
  public RPGItemRarity getRPGRarity() {
    return RPGItemRarity.EPIC;
  }
  
  public <T extends fr.paladium.palarpg.module.equipment.common.loader.data.RPGItemData> T getItemData() {
    return null;
  }
  
  public IIcon getIcon() {
    return func_77617_a(0);
  }
  
  public void applyStats(ItemStack stack, EntityLivingBase entity, RPGStatApplyType type) {}
  
  public void removeStats(ItemStack stack, EntityLivingBase entity, RPGStatApplyType type) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\item\ItemKeyring.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */