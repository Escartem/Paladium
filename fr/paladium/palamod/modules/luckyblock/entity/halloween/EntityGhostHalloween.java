package fr.paladium.palamod.modules.luckyblock.entity.halloween;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.entity.EntityMobPaladium;
import fr.paladium.palamod.modules.luckyblock.network.PacketMobAnimation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityGhostHalloween extends EntityMobPaladium implements IAnimation {
  public int attackCounter;
  
  public EntityGhostHalloween(World world) {
    super(world);
    this.field_70124_G = false;
    func_70105_a(1.0F, 2.0F);
  }
  
  protected void initEntityAI() {
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0D, true));
    this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
    func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(100.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
  }
  
  public int maxFlyingHeight() {
    return 10;
  }
  
  public int minFlyingHeight() {
    return 3;
  }
  
  public boolean isFlyer() {
    return true;
  }
  
  public void func_70057_ab() {}
  
  public void func_70069_a(float f) {}
  
  protected void func_70064_a(double y, boolean onGroundIn) {}
  
  public boolean func_70652_k(Entity entityIn) {
    startArmSwingAttack();
    return super.func_70652_k(entityIn);
  }
  
  public boolean func_70104_M() {
    return false;
  }
  
  private void startArmSwingAttack() {
    if (!this.field_70170_p.field_72995_K) {
      this.attackCounter = 1;
      PalaMod.getNetwork().sendToAllAround((IMessage)new PacketMobAnimation(func_145782_y(), 1), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, this.field_70165_t, this.field_70163_u, this.field_70161_v, 64.0D));
    } 
  }
  
  public void func_70636_d() {
    if (this.attackCounter > 0) {
      this.attackCounter += 2;
      if (this.attackCounter > 10)
        this.attackCounter = 0; 
    } 
    super.func_70636_d();
  }
  
  protected void func_82167_n(Entity par1Entity) {}
  
  public void performAnimation(int animationType) {
    if (animationType == 1)
      this.attackCounter = 1; 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\halloween\EntityGhostHalloween.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */