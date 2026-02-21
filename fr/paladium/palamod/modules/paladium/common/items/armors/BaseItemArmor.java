package fr.paladium.palamod.modules.paladium.common.items.armors;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class BaseItemArmor extends ItemArmor implements ITooltipWiki {
  private final Item item_repair;
  
  protected String model;
  
  public BaseItemArmor(ItemArmor.ArmorMaterial material, int type, String texture, String model, Item item_repair) {
    super(material, 0, type);
    this.item_repair = item_repair;
    this.model = model;
    func_111206_d("palamod:" + texture);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    if (slot == 2)
      return "palamod:textures/models/" + this.model + "_2.png"; 
    return "palamod:textures/models/" + this.model + "_1.png";
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.func_77973_b() == this.item_repair)
      return true; 
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
    super.func_77624_a(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
    BaseItemArmorEffect.addInfo(p_77624_1_, p_77624_3_);
  }
  
  public String getUrl(ItemStack arg0) {
    if (func_82812_d() == PArmorMaterial.amethyst)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armures#1.-armure-en-amethyste"; 
    if (func_82812_d() == PArmorMaterial.titane)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armures#2.-armure-en-titane"; 
    if (func_82812_d() == PArmorMaterial.paladium)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armures#3.-armure-en-paladium"; 
    if (func_82812_d() == PArmorMaterial.paladiumGreen)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armures#4.-armure-en-paladium-vert"; 
    if (func_82812_d() == PArmorMaterial.endium)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armures#5.-armure-en-endium"; 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\BaseItemArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */