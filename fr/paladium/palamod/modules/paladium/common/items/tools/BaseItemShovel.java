package fr.paladium.palamod.modules.paladium.common.items.tools;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class BaseItemShovel extends ItemSpade implements IRepairable, ITooltipWiki {
  private final Item item_repair;
  
  public BaseItemShovel(Item.ToolMaterial material, String texture, Item item_repair) {
    super(material);
    this.item_repair = item_repair;
    func_111206_d("palamod:" + texture);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.func_77973_b() == this.item_repair)
      return true; 
    return false;
  }
  
  public int getRepair(ItemStack item) {
    if (!item.func_77942_o() || !item.func_77978_p().func_74764_b("repaired")) {
      NBTTagCompound tag;
      if (!item.func_77942_o()) {
        tag = new NBTTagCompound();
      } else {
        tag = item.func_77978_p();
      } 
      tag.func_74768_a("repaired", getMaxRepair(item));
      item.func_77982_d(tag);
    } 
    return item.func_77978_p().func_74762_e("repaired");
  }
  
  public void repair(ItemStack item, ItemStack ring, EntityLivingBase entity) {
    if (!item.func_77942_o() || !item.func_77978_p().func_74764_b("repaired")) {
      NBTTagCompound tag;
      if (!item.func_77942_o()) {
        tag = new NBTTagCompound();
      } else {
        tag = item.func_77978_p();
      } 
      tag.func_74768_a("repaired", getMaxRepair(item));
      item.func_77982_d(tag);
    } 
    if (item.func_77960_j() > 0 && ring.func_77960_j() < ring.func_77958_k() && 
      getRepair(item) > 0) {
      ring.func_77972_a(1, entity);
      item.func_77964_b(item.func_77960_j() - 1);
      item.func_77978_p().func_74768_a("repaired", item
          .func_77978_p().func_74762_e("repaired") - 1);
    } 
  }
  
  public void repair(ItemStack item, ItemStack ring) {
    if (!item.func_77942_o() || !item.func_77978_p().func_74764_b("repaired")) {
      NBTTagCompound tag;
      if (!item.func_77942_o()) {
        tag = new NBTTagCompound();
      } else {
        tag = item.func_77978_p();
      } 
      tag.func_74768_a("repaired", getMaxRepair(item));
      item.func_77982_d(tag);
    } 
    if (item.func_77960_j() > 0 && ring.func_77960_j() < ring.func_77958_k() && 
      getRepair(item) > 0) {
      damageItem(ring, 1);
      item.func_77964_b(item.func_77960_j() - 1);
      if (item.func_77960_j() <= 0)
        item.func_77978_p().func_74768_a("repaired", item
            .func_77978_p().func_74762_e("repaired") - 1); 
    } 
  }
  
  public void damageItem(ItemStack item, int p_77972_1_) {
    if (item.func_77984_f() && 
      item.func_96631_a(p_77972_1_, new Random())) {
      item.field_77994_a--;
      if (item.field_77994_a < 0)
        item.field_77994_a = 0; 
      item.func_77964_b(0);
    } 
  }
  
  public boolean isInChest() {
    return true;
  }
  
  public int getMaxRepair(ItemStack item) {
    return (this.field_77862_b == PToolMaterial.endium) ? Integer.MAX_VALUE : ((this.field_77862_b == PToolMaterial.paladium) ? 3 : 0);
  }
  
  public boolean isEndium() {
    return (this.field_77862_b == PToolMaterial.endium);
  }
  
  public String getUrl(ItemStack arg0) {
    if (this.field_77862_b == PToolMaterial.amethyst)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#1.-outils-en-amethyste"; 
    if (this.field_77862_b == PToolMaterial.titane)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#2.-outils-en-titane"; 
    if (this.field_77862_b == PToolMaterial.paladium)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#3.-outils-en-paladium"; 
    if (this.field_77862_b == PToolMaterial.paladiumGreen)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#4.-outils-en-paladium-vert"; 
    if (this.field_77862_b == PToolMaterial.endium)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#5.-outils-en-endium"; 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\tools\BaseItemShovel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */