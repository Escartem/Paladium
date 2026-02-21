package fr.paladium.palamod.modules.paladium.common.items.weapons;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.items.tools.IRepairable;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import java.util.List;
import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;

public class BaseItemSword extends ItemSword implements IRepairable, ITooltipWiki {
  private final Item itemRepair;
  
  private final Item.ToolMaterial toolMaterial;
  
  private List<String> informationList;
  
  public Item getItemRepair() {
    return this.itemRepair;
  }
  
  public List<String> getInformationList() {
    return this.informationList;
  }
  
  public BaseItemSword(Item.ToolMaterial material, String texture, Item itemRepair) {
    super(material);
    this.itemRepair = itemRepair;
    this.toolMaterial = material;
    func_111206_d("palamod:" + texture);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public BaseItemSword(Item.ToolMaterial material, Item item_repair) {
    super(material);
    this.toolMaterial = material;
    this.itemRepair = item_repair;
  }
  
  public BaseItemSword(Item.ToolMaterial material, String texture, Item item_repair, List<String> informationList) {
    this(material, texture, item_repair);
    this.informationList = informationList;
  }
  
  public int getDamage(ItemStack stack) {
    int damage = super.getDamage(stack);
    if (this.toolMaterial == PToolMaterial.endium && damage >= func_77612_l())
      return func_77612_l(); 
    return damage;
  }
  
  public void setDamage(ItemStack stack, int damage) {
    if (this.toolMaterial == PToolMaterial.endium && damage > func_77612_l()) {
      super.setDamage(stack, func_77612_l());
      return;
    } 
    super.setDamage(stack, damage);
  }
  
  public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
    if (this.toolMaterial == PToolMaterial.endium && stack.func_77960_j() >= func_77612_l())
      return true; 
    return super.onLeftClickEntity(stack, player, entity);
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.func_77973_b() == this.itemRepair)
      return true; 
    return false;
  }
  
  public boolean isItemTool() {
    return true;
  }
  
  public Item.ToolMaterial getToolMaterial() {
    return this.toolMaterial;
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
      item.func_77978_p().func_74768_a("repaired", item.func_77978_p().func_74762_e("repaired") - 1);
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
        item.func_77978_p().func_74768_a("repaired", item.func_77978_p().func_74762_e("repaired") - 1); 
    } 
  }
  
  public void damageItem(ItemStack item, int value) {
    if (item.func_77984_f() && 
      item.func_96631_a(value, new Random())) {
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
    return (this.toolMaterial == PToolMaterial.endium) ? Integer.MAX_VALUE : ((this.toolMaterial == PToolMaterial.paladium) ? 3 : 0);
  }
  
  public boolean isEndium() {
    return (this.toolMaterial == PToolMaterial.endium);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean flag) {
    if (this.informationList != null)
      list.addAll(this.informationList); 
    super.func_77624_a(stack, player, list, flag);
  }
  
  public String getUrl(ItemStack arg0) {
    if (this.toolMaterial == PToolMaterial.amethyst)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armes#1.-les-armes-en-amethyste"; 
    if (this.toolMaterial == PToolMaterial.titane)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armes#2.-les-armes-en-titane"; 
    if (this.toolMaterial == PToolMaterial.paladium)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armes#3.-les-armes-en-paladium"; 
    if (this.toolMaterial == PToolMaterial.paladiumGreen)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armes#4.-les-armes-en-paladium-vert"; 
    if (this.toolMaterial == PToolMaterial.endium)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armes#5.-les-armes-en-endium"; 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\weapons\BaseItemSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */