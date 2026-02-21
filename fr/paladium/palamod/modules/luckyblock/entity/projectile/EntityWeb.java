package fr.paladium.palamod.modules.luckyblock.entity.projectile;

import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityWeb extends EntityThrowable {
  public EntityWeb(World p_i1773_1_) {
    super(p_i1773_1_);
  }
  
  public EntityWeb(World p_i1774_1_, EntityLivingBase p_i1774_2_) {
    super(p_i1774_1_, p_i1774_2_);
  }
  
  public EntityWeb(World p_i1774_1_, EntityLivingBase p_i1774_2_, float power) {
    super(p_i1774_1_, p_i1774_2_);
    func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, power * 1.5F, 1.0F);
  }
  
  public EntityWeb(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_) {
    super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
  }
  
  protected void func_70184_a(MovingObjectPosition p_70184_1_) {
    if (p_70184_1_.field_72308_g != null && func_85052_h() != null) {
      if (!PlayerUtil.canPlace((EntityPlayerMP)func_85052_h(), 
          MathHelper.func_76128_c(p_70184_1_.field_72308_g.field_70165_t), 
          MathHelper.func_76128_c(p_70184_1_.field_72308_g.field_70163_u), 
          MathHelper.func_76128_c(p_70184_1_.field_72308_g.field_70161_v), 0)) {
        if (p_70184_1_.field_72308_g.field_70170_p.func_147437_c(
            MathHelper.func_76128_c(p_70184_1_.field_72308_g.field_70165_t), 
            MathHelper.func_76128_c(p_70184_1_.field_72308_g.field_70163_u), 
            MathHelper.func_76128_c(p_70184_1_.field_72308_g.field_70161_v)))
          p_70184_1_.field_72308_g.field_70170_p.func_147449_b(MathHelper.func_76128_c(p_70184_1_.field_72308_g.field_70165_t), 
              MathHelper.func_76128_c(p_70184_1_.field_72308_g.field_70163_u), 
              MathHelper.func_76128_c(p_70184_1_.field_72308_g.field_70161_v), Blocks.field_150321_G); 
        if (p_70184_1_.field_72308_g.field_70170_p.func_147437_c(
            MathHelper.func_76128_c(p_70184_1_.field_72308_g.field_70165_t), 
            MathHelper.func_76128_c(p_70184_1_.field_72308_g.field_70163_u + 1.0D), 
            MathHelper.func_76128_c(p_70184_1_.field_72308_g.field_70161_v)))
          p_70184_1_.field_72308_g.field_70170_p.func_147449_b(MathHelper.func_76128_c(p_70184_1_.field_72308_g.field_70165_t), 
              MathHelper.func_76128_c(p_70184_1_.field_72308_g.field_70163_u + 1.0D), 
              MathHelper.func_76128_c(p_70184_1_.field_72308_g.field_70161_v), Blocks.field_150321_G); 
      } 
    } else if (MovingObjectPosition.MovingObjectType.BLOCK == p_70184_1_.field_72313_a && 
      func_85052_h() != null) {
      if (!PlayerUtil.canPlace((EntityPlayerMP)func_85052_h(), p_70184_1_.field_72311_b, p_70184_1_.field_72312_c + 1, p_70184_1_.field_72309_d, 0))
        if (this.field_70170_p.func_147437_c(p_70184_1_.field_72311_b, p_70184_1_.field_72312_c + 1, p_70184_1_.field_72309_d))
          this.field_70170_p.func_147449_b(p_70184_1_.field_72311_b, p_70184_1_.field_72312_c + 1, p_70184_1_.field_72309_d, Blocks.field_150321_G);  
    } 
    for (int i = 0; i < 8; i++)
      this.field_70170_p.func_72869_a("snowballpoof", this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0D, 0.0D, 0.0D); 
    if (!this.field_70170_p.field_72995_K)
      func_70106_y(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\projectile\EntityWeb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */