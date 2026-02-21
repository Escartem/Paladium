package fr.paladium.palamod.modules.paladium.common.items.tools;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import fr.paladium.palamod.modules.smeltery.utils.ToolHandler;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BaseItemPickaxe extends ItemPickaxe implements IRepairable, ITooltipWiki {
  private static final Material[] MATERIALS = new Material[] { 
      Material.field_151579_a, Material.field_151577_b, Material.field_151578_c, Material.field_151575_d, Material.field_151576_e, Material.field_151573_f, Material.field_151574_g, Material.field_151586_h, Material.field_151587_i, Material.field_151584_j, 
      Material.field_151585_k, Material.field_151582_l, Material.field_151589_v, Material.field_151583_m, Material.field_151580_n, Material.field_151581_o, Material.field_151595_p, Material.field_151594_q, Material.field_151592_s, Material.field_151591_t, 
      Material.field_151590_u, Material.field_151589_v, Material.field_151588_w, Material.field_151598_x, Material.field_151597_y, Material.field_151596_z, Material.field_151570_A, Material.field_151571_B, Material.field_151572_C, Material.field_151567_E, 
      Material.field_151568_F, Material.field_151569_G, Material.field_76233_E };
  
  private final Item itemRepair;
  
  private final Item.ToolMaterial toolMaterial;
  
  public Item getItemRepair() {
    return this.itemRepair;
  }
  
  public Item.ToolMaterial getToolMaterial() {
    return this.toolMaterial;
  }
  
  public BaseItemPickaxe(Item.ToolMaterial material, String texture, Item itemRepair) {
    super(material);
    this.itemRepair = itemRepair;
    this.toolMaterial = material;
    func_111206_d("palamod:" + texture);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
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
  
  public boolean func_150894_a(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase player) {
    if (!isEndium() || stack.func_77960_j() >= stack.func_77958_k())
      return super.func_150894_a(stack, world, block, x, y, z, player); 
    MovingObjectPosition raycast = ToolHandler.raytraceFromEntity(player.field_70170_p, (Entity)player, true, 10.0D);
    if (raycast != null && 
      !world.func_147437_c(x, y, z)) {
      ForgeDirection direction = ForgeDirection.getOrientation(raycast.field_72310_e);
      int range = Math.max(0, 1);
      int rangeY = Math.max(1, range);
      boolean doX = (direction.offsetX == 0);
      boolean doY = (direction.offsetY == 0);
      boolean doZ = (direction.offsetZ == 0);
      ToolHandler.removeBlocksInIteration((EntityPlayer)player, stack, world, x, y, z, doX ? -range : 0, doY ? -1 : 0, doZ ? -range : 0, doX ? (range + 1) : 1, doY ? (rangeY * 2) : 1, doZ ? (range + 1) : 1, world
          
          .func_147439_a(x, y, z), MATERIALS, false, 
          
          EnchantmentHelper.func_77517_e(player), true, false);
    } 
    return super.func_150894_a(stack, world, block, x, y, z, player);
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.func_77973_b() == this.itemRepair)
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
  
  public String getUrl(ItemStack arg0) {
    if (this.toolMaterial == PToolMaterial.amethyst)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#1.-outils-en-amethyste"; 
    if (this.toolMaterial == PToolMaterial.titane)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#2.-outils-en-titane"; 
    if (this.toolMaterial == PToolMaterial.paladium)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#3.-outils-en-paladium"; 
    if (this.toolMaterial == PToolMaterial.paladiumGreen)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#4.-outils-en-paladium-vert"; 
    if (this.toolMaterial == PToolMaterial.endium)
      return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#5.-outils-en-endium"; 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\tools\BaseItemPickaxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */