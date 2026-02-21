package fr.paladium.palamod.modules.paladium.common.items.tools;

import com.google.common.collect.Sets;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BaseItemAxe extends ItemTool implements IRepairable, ITooltipWiki {
  private static final Set<Block> field_150917_c = Sets.newHashSet((Object[])new Block[] { Blocks.field_150344_f, Blocks.field_150342_X, Blocks.field_150364_r, Blocks.field_150363_s, (Block)Blocks.field_150486_ae, Blocks.field_150423_aK, Blocks.field_150440_ba, Blocks.field_150428_aP });
  
  private final Item itemRepair;
  
  public BaseItemAxe(Item.ToolMaterial material, String texture, Item itemRepair) {
    super(3.0F, material, field_150917_c);
    this.itemRepair = itemRepair;
    func_111206_d("palamod:" + texture);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public int getDamage(ItemStack stack) {
    int damage = super.getDamage(stack);
    if (this.field_77862_b == PToolMaterial.endium && damage >= func_77612_l())
      return func_77612_l(); 
    return damage;
  }
  
  public void setDamage(ItemStack stack, int damage) {
    if (this.field_77862_b == PToolMaterial.endium && damage > func_77612_l()) {
      super.setDamage(stack, func_77612_l());
      return;
    } 
    super.setDamage(stack, damage);
  }
  
  public boolean func_150894_a(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase player) {
    if (this.field_77862_b == PToolMaterial.endium) {
      if (stack.func_77960_j() >= stack.func_77958_k())
        return super.func_150894_a(stack, world, block, x, y, z, player); 
      if (block.isWood((IBlockAccess)world, x, y, z))
        breakTree(world, x, y, z, 0); 
    } 
    return super.func_150894_a(stack, world, block, x, y, z, player);
  }
  
  private void breakTree(World world, int x, int y, int z, int depth) {
    Block block = world.func_147439_a(x, y, z);
    if (depth > 15)
      return; 
    if (block.isWood((IBlockAccess)world, x, y, z)) {
      world.func_147480_a(x, y, z, true);
      for (int dx = -1; dx <= 1; dx++) {
        for (int dy = -1; dy <= 1; dy++) {
          for (int dz = -1; dz <= 1; dz++) {
            if (dx != 0 || dy != 0 || dz != 0)
              breakTree(world, x + dx, y + dy, z + dz, depth + 1); 
          } 
        } 
      } 
    } 
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.func_77973_b() == this.itemRepair)
      return true; 
    return false;
  }
  
  public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_) {
    return (p_150893_2_.func_149688_o() != Material.field_151575_d && p_150893_2_.func_149688_o() != Material.field_151585_k && p_150893_2_.func_149688_o() != Material.field_151582_l) ? super.func_150893_a(p_150893_1_, p_150893_2_) : this.field_77864_a;
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\tools\BaseItemAxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */