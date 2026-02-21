package fr.paladium.palamod.modules.luckyblock.entity.black;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.PacketWaterPistol;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityWaterDrop extends EntityThrowable {
  public EntityWaterDrop(World p_i1773_1_) {
    super(p_i1773_1_);
  }
  
  public EntityWaterDrop(World p_i1774_1_, EntityLivingBase p_i1774_2_) {
    super(p_i1774_1_, p_i1774_2_);
  }
  
  public EntityWaterDrop(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_) {
    super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    if (!this.field_70128_L)
      this.field_70170_p.func_72869_a("dripWater", this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0D, 0.0D, 0.0D); 
  }
  
  protected void func_70184_a(MovingObjectPosition hit) {
    if (hit.field_72308_g instanceof net.minecraft.entity.player.EntityPlayer) {
      byte damage = 0;
      hit.field_72308_g.func_70097_a(DamageSource.field_76379_h, damage);
      if (!this.field_70170_p.field_72995_K)
        PalaMod.getNetwork().sendTo((IMessage)new PacketWaterPistol(), (EntityPlayerMP)func_85052_h()); 
    } 
    if (!this.field_70170_p.field_72995_K)
      func_70106_y(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\black\EntityWaterDrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */