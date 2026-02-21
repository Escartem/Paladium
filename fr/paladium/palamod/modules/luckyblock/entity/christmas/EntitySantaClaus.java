package fr.paladium.palamod.modules.luckyblock.entity.christmas;

import java.util.List;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntitySantaClaus extends EntityAnimal {
  public EntitySantaClaus(World world) {
    super(world);
    func_70105_a(0.6F, 1.8F);
    this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
  }
  
  public EntityAgeable func_90011_a(EntityAgeable p_90011_1_) {
    return null;
  }
  
  public void func_70030_z() {
    int offset = 5;
    AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(this.field_70165_t - offset, this.field_70163_u - offset, this.field_70161_v - offset, this.field_70165_t + offset, this.field_70163_u + offset, this.field_70161_v + offset);
    List<EntityPlayer> players = this.field_70170_p.func_72872_a(EntityPlayer.class, scanAbove);
    players
      .forEach(player -> player.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 60, 0)));
    super.func_70030_z();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\christmas\EntitySantaClaus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */