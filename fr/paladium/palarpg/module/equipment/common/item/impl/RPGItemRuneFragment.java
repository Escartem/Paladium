package fr.paladium.palarpg.module.equipment.common.item.impl;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palarpg.module.equipment.common.EquipmentCommonProxy;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import fr.paladium.palarpg.module.equipment.common.item.RPGStatApplyType;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGBasicRuneItemData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class RPGItemRuneFragment extends Item implements IRPGItem {
  private final RPGBasicRuneItemData runeItemData;
  
  public RPGItemRuneFragment(RPGBasicRuneItemData data) {
    func_77637_a(EquipmentCommonProxy.getRPGBasicTab());
    func_77625_d(data.getMaxStackSize());
    func_77655_b(data.getId());
    func_111206_d("palarpg:rune/" + data.getId());
    this.runeItemData = data;
  }
  
  public void register() {}
  
  public String getId() {
    return this.runeItemData.getId();
  }
  
  public RPGItemRarity getRPGRarity() {
    return this.runeItemData.getRarity();
  }
  
  public RPGBasicRuneItemData getItemData() {
    return this.runeItemData;
  }
  
  public IIcon getIcon() {
    return null;
  }
  
  public String func_77653_i(ItemStack stack) {
    if ((getItemData().getItemName() != null && !getItemData().getItemName().isEmpty()) || (getItemData().getTranslations() != null && !getItemData().getTranslations().isEmpty()))
      return super.func_77653_i(stack); 
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT && "fr_FR".equals(Minecraft.func_71410_x().func_135016_M().func_135041_c().func_135034_a()))
      return "Fragment de Rune"; 
    return "Rune Fragment";
  }
  
  public void applyStats(ItemStack stack, EntityLivingBase entity, RPGStatApplyType type) {}
  
  public void removeStats(ItemStack stack, EntityLivingBase entity, RPGStatApplyType type) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\item\impl\RPGItemRuneFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */