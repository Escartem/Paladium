package fr.paladium.palamod.modules.paladium.common.entities.projectiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityPotionGun extends EntityPotion {
  public EntityPotionGun(World world) {
    super(world);
  }
  
  public EntityPotionGun(World world, EntityLivingBase player, ItemStack stack) {
    super(world, player, stack);
  }
  
  @SideOnly(Side.CLIENT)
  public EntityPotionGun(World p_i1791_1_, double p_i1791_2_, double p_i1791_4_, double p_i1791_6_, int p_i1791_8_) {
    super(p_i1791_1_, p_i1791_2_, p_i1791_4_, p_i1791_6_, new ItemStack((Item)Items.field_151068_bn, 1, p_i1791_8_));
  }
  
  protected float func_70185_h() {
    return 0.05F;
  }
  
  protected float func_70182_d() {
    return 1.0F;
  }
  
  protected float func_70183_g() {
    return 0.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\entities\projectiles\EntityPotionGun.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */