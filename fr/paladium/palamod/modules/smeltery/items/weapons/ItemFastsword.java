package fr.paladium.palamod.modules.smeltery.items.weapons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.smeltery.utils.EntityHelper;
import fr.paladium.palamod.modules.smeltery.utils.UpgradeHelper;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFastsword extends Item {
  float damageBase;
  
  public ItemFastsword(float damages, int max) {
    func_77625_d(1);
    func_77637_a((CreativeTabs)Registry.SMELTERY_TAB);
    func_77656_e(max);
    this.damageBase = damages;
  }
  
  public boolean func_77644_a(ItemStack stack, EntityLivingBase base, EntityLivingBase base2) {
    if (WorldGuardUtils.isItemEffectBlocked((Entity)base, stack))
      return false; 
    base.field_70737_aN = 12;
    base.field_70172_ad = 18;
    base.field_70738_aO = 12;
    base.field_70771_an = 18;
    int flame = UpgradeHelper.getModifier(stack, 4);
    int knockback = UpgradeHelper.getModifier(stack, 5);
    if (flame == 1)
      base.func_70015_d(200); 
    if (knockback > 0)
      EntityHelper.knockbackEntity(base, knockback); 
    stack.func_77972_a(1, base2);
    return true;
  }
  
  public float getDamages(ItemStack stack) {
    return (float)(this.damageBase + UpgradeHelper.getModifier(stack, 3) * 1.5D);
  }
  
  public boolean func_150894_a(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_) {
    if (p_150894_3_.func_149712_f(p_150894_2_, p_150894_4_, p_150894_5_, p_150894_6_) != 0.0D)
      p_150894_1_.func_77972_a(2, p_150894_7_); 
    return true;
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_77662_d() {
    return true;
  }
  
  public EnumAction func_77661_b(ItemStack p_77661_1_) {
    return EnumAction.block;
  }
  
  public int func_77626_a(ItemStack p_77626_1_) {
    return 72000;
  }
  
  public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
    p_77659_3_.func_71008_a(p_77659_1_, func_77626_a(p_77659_1_));
    return p_77659_1_;
  }
  
  public boolean func_150897_b(Block p_150897_1_) {
    return (p_150897_1_ == Blocks.field_150321_G);
  }
  
  public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
    if (entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)entity;
      if (player.func_70694_bm() != null && 
        player.func_70694_bm().func_77973_b() instanceof ItemFastsword && !WorldGuardUtils.isItemEffectBlocked(entity, player.func_70694_bm()))
        ((EntityPlayer)entity).func_70690_d(new PotionEffect(Potion.field_76422_e.field_76415_H, 1, 3)); 
    } 
    super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\items\weapons\ItemFastsword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */