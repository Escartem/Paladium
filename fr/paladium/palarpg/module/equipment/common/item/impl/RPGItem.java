package fr.paladium.palarpg.module.equipment.common.item.impl;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.lang.CustomLanguageManager;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palarpg.module.equipment.common.EquipmentCommonProxy;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import fr.paladium.palarpg.module.equipment.common.item.RPGStatApplyType;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGBasicItemData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class RPGItem extends Item implements IRPGItem {
  private final RPGBasicItemData itemData;
  
  public RPGItem(RPGItemData itemData) {
    func_77637_a(EquipmentCommonProxy.getRPGBasicTab());
    this.itemData = (RPGBasicItemData)itemData;
    func_77655_b(getId());
    func_77625_d((getItemData().getMaxStackSize() > 0) ? getItemData().getMaxStackSize() : 64);
  }
  
  public void register() {
    RegistryUtils.item(new Item[] { this });
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
      CustomLanguageManager.register("fr_FR", func_77658_a() + ".name", getItemData().getTranslation("fr").trim());
      CustomLanguageManager.register("en_US", func_77658_a() + ".name", getItemData().getTranslation("en").trim());
    } 
  }
  
  public RPGBasicItemData getItemData() {
    return this.itemData;
  }
  
  public String getId() {
    return getItemData().getId().trim();
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon() {
    return getItemData().getIcon();
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int damage) {
    return getIcon();
  }
  
  public void applyStats(ItemStack stack, EntityLivingBase entity, RPGStatApplyType type) {}
  
  public void removeStats(ItemStack stack, EntityLivingBase entity, RPGStatApplyType type) {}
  
  public RPGItemRarity getRPGRarity() {
    return getItemData().getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\item\impl\RPGItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */