package fr.paladium.palamod.modules.paladium.common.items.armors;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.alchimiste.common.init.EnchantMod;
import fr.paladium.palamod.modules.paladium.common.items.tools.IRepairable;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import fr.paladium.palamod.modules.paladium.registerer.PRegister_Potions;
import java.util.List;
import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemMixedEndiumArmor extends BaseItemArmor implements IRepairable, ITooltipWiki {
  private final int[] maxRepair;
  
  public ItemMixedEndiumArmor(ItemArmor.ArmorMaterial material, int type, String texture, String model, Item item_repair, int[] maxRepair) {
    super(material, type, texture, model, item_repair);
    this.maxRepair = maxRepair;
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
      int repairValue = 1;
      if (EnchantmentHelper.func_77506_a(EnchantMod.fakeWaterProtection.field_77352_x, item) > 0) {
        int remainingItemDamage = item.func_77958_k() - item.func_77960_j();
        int remainingRingDamage = ring.func_77958_k() - ring.func_77960_j();
        repairValue = Math.min(4, Math.min(remainingItemDamage, remainingRingDamage));
      } 
      ring.func_77972_a(repairValue, entity);
      item.func_77964_b(item.func_77960_j() - repairValue);
      item.func_77978_p().func_74768_a("repaired", item.func_77978_p().func_74762_e("repaired") - repairValue);
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
    return false;
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
    switch (this.field_77881_a) {
      case 0:
        player.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, 1200, 0, true));
        player.func_70050_g(300);
        break;
      case 1:
        player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 1200, 0, true));
        break;
      case 2:
        player.func_70690_d(new PotionEffect(Potion.field_76422_e.field_76415_H, 1200, 1, true));
        break;
      case 3:
        player.func_70690_d(new PotionEffect(PRegister_Potions.potionFeatherFalling.getPotionId(), 1200, 0, true));
        player.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 1200, 1, true));
        break;
    } 
  }
  
  public static float getFraction(ItemStack stack) {
    if (!(stack.func_77973_b() instanceof ItemRepairableArmor))
      return 0.0F; 
    return ((ItemRepairableArmor)stack.func_77973_b()).getRepair(stack) / ((ItemRepairableArmor)stack.func_77973_b()).getMaxRepair(stack);
  }
  
  public int getCost() {
    switch (this.field_77881_a) {
      case 0:
        return 5;
      case 1:
        return 8;
      case 2:
        return 7;
      case 3:
        return 4;
    } 
    return 0;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> lores, boolean flag) {
    if (item.func_77942_o() && 
      item.func_77978_p().func_74764_b("amulets")) {
      NBTTagList amulets = item.func_77978_p().func_150295_c("amulets", 8);
      for (int i = 0; i < amulets.func_74745_c(); i++)
        lores.add("§eAmulette de §3" + amulets.func_150307_f(i)); 
    } 
    super.func_77624_a(item, player, lores, flag);
  }
  
  public int getMaxRepair(ItemStack item) {
    return this.maxRepair[this.field_77881_a];
  }
  
  public boolean isEndium() {
    return false;
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ItemMixedEndiumArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */