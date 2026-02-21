package fr.paladium.palamod.modules.paladium.common.items.armors;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.modules.alchimiste.common.init.EnchantMod;
import fr.paladium.palamod.modules.paladium.common.items.tools.IRepairable;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemEndiumArmor extends BaseItemArmor implements IRepairable, ITooltipWiki {
  public ItemEndiumArmor(ItemArmor.ArmorMaterial material, int type, String texture, String model, Item item_repair) {
    super(material, type, texture, model, item_repair);
  }
  
  public int getDamage(ItemStack stack) {
    int damage = super.getDamage(stack);
    if (damage >= func_77612_l())
      return func_77612_l(); 
    return damage;
  }
  
  public void setDamage(ItemStack stack, int damage) {
    if (damage > func_77612_l()) {
      super.setDamage(stack, func_77612_l());
      return;
    } 
    super.setDamage(stack, damage);
  }
  
  public int getRepair(ItemStack item) {
    return 0;
  }
  
  public void repair(ItemStack item, ItemStack ring, EntityLivingBase entity) {
    if (item.func_77960_j() > 0 && ring.func_77960_j() < ring.func_77958_k()) {
      int repairValue = 1;
      if (EnchantmentHelper.func_77506_a(EnchantMod.fakeWaterProtection.field_77352_x, item) > 0) {
        int remainingItemDamage = item.func_77958_k() - item.func_77960_j();
        int remainingRingDamage = ring.func_77958_k() - ring.func_77960_j();
        repairValue = Math.min(4, Math.min(remainingItemDamage, remainingRingDamage));
      } 
      ring.func_77972_a(repairValue, entity);
      item.func_77964_b(item.func_77960_j() - repairValue);
    } 
  }
  
  public void repair(ItemStack item, ItemStack ring) {
    if (item.func_77960_j() > 0 && ring.func_77960_j() < ring.func_77958_k()) {
      damageItem(ring, 1);
      item.func_77964_b(item.func_77960_j() - 1);
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
    int armorSlot = 3 - this.field_77881_a;
    if (stack.func_77960_j() >= stack.func_77958_k() && player.field_71071_by.func_70440_f(armorSlot) == stack) {
      player.field_71071_by.field_70460_b[armorSlot] = null;
      boolean found = false;
      int i;
      for (i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
        int slot = player.field_71071_by.field_70462_a.length - i - 1;
        if (player.field_71071_by.field_70462_a[slot] == null) {
          player.field_71071_by.field_70460_b[armorSlot] = player.field_71071_by.field_70462_a[slot];
          player.field_71071_by.field_70462_a[slot] = stack;
          found = true;
          break;
        } 
      } 
      if (!found) {
        for (i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
          int slot = player.field_71071_by.field_70462_a.length - i - 1;
          if (player.field_71071_by.field_70462_a[slot] == null) {
            player.field_71071_by.field_70460_b[armorSlot] = player.field_71071_by.field_70462_a[slot];
            player.field_71071_by.field_70462_a[slot] = stack;
            found = true;
            break;
          } 
          Block block = Block.func_149634_a(player.field_71071_by.field_70462_a[slot].func_77973_b());
          if (block != Blocks.field_150350_a) {
            player.field_71071_by.field_70460_b[armorSlot] = player.field_71071_by.field_70462_a[slot];
            player.field_71071_by.field_70462_a[slot] = stack;
            found = true;
            break;
          } 
        } 
        if (!found) {
          for (i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
            int slot = player.field_71071_by.field_70462_a.length - i - 1;
            if (player.field_71071_by.field_70462_a[slot] == null || (!(player.field_71071_by.field_70462_a[slot].func_77973_b() instanceof ItemArmor) && !(player.field_71071_by.field_70462_a[slot].func_77973_b() instanceof net.minecraft.item.ItemSword))) {
              player.field_71071_by.field_70460_b[armorSlot] = player.field_71071_by.field_70462_a[slot];
              player.field_71071_by.field_70462_a[slot] = stack;
              found = true;
              break;
            } 
          } 
          if (!found) {
            for (i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
              int slot = player.field_71071_by.field_70462_a.length - i - 1;
              if (player.field_71071_by.field_70462_a[slot] == null) {
                player.field_71071_by.field_70460_b[armorSlot] = player.field_71071_by.field_70462_a[slot];
                player.field_71071_by.field_70462_a[slot] = stack;
                found = true;
                break;
              } 
            } 
            if (!found) {
              InventoryUtils.giveOrDropitems(player, player.field_71071_by.field_70462_a[player.field_71071_by.field_70462_a.length - 1]);
              player.field_71071_by.field_70462_a[player.field_71071_by.field_70462_a.length - 1] = stack;
            } 
          } 
        } 
      } 
      player.field_71069_bz.func_75142_b();
    } 
    switch (this.field_77881_a) {
      case 0:
        player.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, 600, 1, true));
        if (player.func_82169_q(0) != null && player.func_82169_q(1) != null && player
          .func_82169_q(2) != null && player.func_82169_q(3) != null && player
          .func_82169_q(1).func_77973_b() instanceof ItemEndiumArmor && player
          .func_82169_q(2).func_77973_b() instanceof ItemEndiumArmor && player
          .func_82169_q(3).func_77973_b() instanceof ItemEndiumArmor && player
          .func_82169_q(0).func_77973_b() instanceof ItemEndiumArmor)
          player.func_70690_d(new PotionEffect(Potion.field_76441_p.field_76415_H, 2, 0, true)); 
        break;
      case 1:
        player.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 2, 0, true));
        break;
      case 3:
        player.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 2, 1, true));
        break;
    } 
  }
  
  public int getCost() {
    if (func_82812_d().equals(PArmorMaterial.paladium))
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
    return -1;
  }
  
  public boolean isEndium() {
    return true;
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ItemEndiumArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */