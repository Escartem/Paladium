package fr.paladium.palamod.modules.ajobs.common.blocks.thrones;

import fr.paladium.palajobs.core.tab.JobsTab;
import fr.paladium.palamod.modules.ajobs.common.entities.EntitySittableBlock;
import fr.paladium.palamod.modules.luckyblock.luckystats.common.utils.reward.ILuckyStatsOwnedReward;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockThroneBase extends Block implements ILuckyStatsOwnedReward {
  protected BlockThroneBase() {
    super(Material.field_151576_e);
    func_149647_a((CreativeTabs)JobsTab.getInstance());
    func_149711_c(1.0F);
  }
  
  public String getValue(EntityPlayer player) {
    return player.func_70005_c_();
  }
  
  public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
    int l = MathHelper.func_76128_c((p_149689_5_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    if (l == 0)
      p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 0, 2); 
    if (l == 1)
      p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 1, 2); 
    if (l == 2)
      p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2); 
    if (l == 3)
      p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2); 
  }
  
  public boolean sitOnBlock(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, double par6) {
    checkForExistingEntity(par1World, par2, par3, par4, par5EntityPlayer);
    EntitySittableBlock nemb = new EntitySittableBlock(par1World, par2, par3, par4, 2.0D);
    par1World.func_72838_d((Entity)nemb);
    par5EntityPlayer.func_70078_a((Entity)nemb);
    nemb.func_70043_V();
    return true;
  }
  
  public boolean sitOnBlockWithRotationOffset(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, double par6, int metadata, double offset) {
    if (!checkForExistingEntity(par1World, par2, par3, par4, par5EntityPlayer)) {
      EntitySittableBlock nemb = new EntitySittableBlock(par1World, par2, par3, par4, par6, metadata, 2.0D);
      par1World.func_72838_d((Entity)nemb);
      par5EntityPlayer.func_70078_a((Entity)nemb);
      nemb.func_70043_V();
    } 
    return true;
  }
  
  public boolean checkForExistingEntity(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
    List<EntitySittableBlock> listEMB = par1World.func_72872_a(EntitySittableBlock.class, AxisAlignedBB.func_72330_a(par2, par3, par4, par2 + 1.0D, par3 + 1.0D, par4 + 1.0D).func_72314_b(1.0D, 1.0D, 1.0D));
    for (EntitySittableBlock mount : listEMB) {
      if (mount.blockPosX == par2 && mount.blockPosY == par3 && mount.blockPosZ == par4) {
        if (mount.field_70153_n == null) {
          par5EntityPlayer.func_70078_a((Entity)mount);
          mount.func_70043_V();
        } 
        return true;
      } 
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\blocks\thrones\BlockThroneBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */