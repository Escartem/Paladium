package fr.paladium.palamod.modules.miner.dimminer.common.entity;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.miner.dimminer.common.entity.ai.EntityAIMinerBossAttackOnCollide;
import fr.paladium.palamod.modules.miner.dimminer.common.entity.ai.EntityAIMinerBossNearestAttackableTarget;
import fr.paladium.palamod.modules.miner.dimminer.common.entity.attack.EntityMinerBossLeftShockWaveAttack;
import fr.paladium.palamod.modules.miner.dimminer.common.entity.attack.EntityMinerBossPunchAttack;
import fr.paladium.palamod.modules.miner.dimminer.common.entity.attack.EntityMinerBossRepulseAttack;
import fr.paladium.palamod.modules.miner.dimminer.common.entity.attack.EntityMinerBossStrikingGroundAttack;
import fr.paladium.palamod.modules.miner.dimminer.common.entity.attack.EntityMinerBossTurnAttack;
import fr.paladium.palamod.modules.miner.dimminer.common.entity.attack.IEntityMinerBossAttack;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMinerBoss extends EntityMob implements IBossDisplayData, IRangedAttackMob, IEntityAdditionalSpawnData {
  private List<IEntityMinerBossAttack> attacks = new ArrayList<>();
  
  private IEntityMinerBossAttack currentAttack;
  
  public List<IEntityMinerBossAttack> getAttacks() {
    return this.attacks;
  }
  
  public IEntityMinerBossAttack getCurrentAttack() {
    return this.currentAttack;
  }
  
  public void setCurrentAttack(IEntityMinerBossAttack currentAttack) {
    this.currentAttack = currentAttack;
  }
  
  public void setStunted(boolean stunted) {
    this.field_70180_af.func_75692_b(16, Boolean.toString(stunted));
  }
  
  public boolean isStunted() {
    return Boolean.valueOf(this.field_70180_af.func_75681_e(16)).booleanValue();
  }
  
  public void setStuntedDelay(int stuntedDelay) {
    this.field_70180_af.func_75692_b(17, Integer.valueOf(stuntedDelay));
  }
  
  public int getStuntedDelay() {
    return this.field_70180_af.func_75679_c(17);
  }
  
  public void setStrikingGround(boolean strikingGround) {
    this.field_70180_af.func_75692_b(18, Boolean.toString(strikingGround));
  }
  
  public boolean isStrikingGround() {
    return Boolean.valueOf(this.field_70180_af.func_75681_e(18)).booleanValue();
  }
  
  public void setStrikeGroundAnimProgress(float strikeGroundAnimProgress) {
    this.field_70180_af.func_75692_b(19, Float.valueOf(strikeGroundAnimProgress));
  }
  
  public float getStrikeGroundAnimProgress() {
    return this.field_70180_af.func_111145_d(19);
  }
  
  public void setStrikeGroundAnimMaxDuration(float strikeGroundAnimProgress) {
    this.field_70180_af.func_75692_b(20, Float.valueOf(strikeGroundAnimProgress));
  }
  
  public float getStrikeGroundAnimMaxDuration() {
    return this.field_70180_af.func_111145_d(20);
  }
  
  public boolean isPunching() {
    return Boolean.valueOf(this.field_70180_af.func_75681_e(21)).booleanValue();
  }
  
  public void setPunching(boolean punching) {
    this.field_70180_af.func_75692_b(21, Boolean.toString(punching));
  }
  
  public float getPunchAnimProgress() {
    return this.field_70180_af.func_111145_d(22);
  }
  
  public void setPunchAnimProgress(float punchAnimProgress) {
    this.field_70180_af.func_75692_b(22, Float.valueOf(punchAnimProgress));
  }
  
  public float getPunchAnimMaxDuration() {
    return this.field_70180_af.func_111145_d(23);
  }
  
  public void setPunchAnimMaxDuration(float punchAnimMaxDuration) {
    this.field_70180_af.func_75692_b(23, Float.valueOf(punchAnimMaxDuration));
  }
  
  public boolean isRaised() {
    return Boolean.valueOf(this.field_70180_af.func_75681_e(24)).booleanValue();
  }
  
  public void setRaised(boolean raised) {
    this.field_70180_af.func_75692_b(24, Boolean.toString(raised));
  }
  
  public boolean isRightArmRaised() {
    return Boolean.valueOf(this.field_70180_af.func_75681_e(25)).booleanValue();
  }
  
  public void setRightArmRaised(boolean rightArmRaised) {
    this.field_70180_af.func_75692_b(25, Boolean.toString(rightArmRaised));
  }
  
  public boolean isLeftArmRaised() {
    return Boolean.valueOf(this.field_70180_af.func_75681_e(26)).booleanValue();
  }
  
  public void setLeftArmRaised(boolean leftArmRaised) {
    this.field_70180_af.func_75692_b(26, Boolean.toString(leftArmRaised));
  }
  
  public boolean isTurnAttack() {
    return Boolean.valueOf(this.field_70180_af.func_75681_e(27)).booleanValue();
  }
  
  public void setTurnAttack(boolean turnAttack) {
    this.field_70180_af.func_75692_b(27, Boolean.toString(turnAttack));
  }
  
  public int getState() {
    return this.field_70180_af.func_75679_c(28);
  }
  
  public void setState(int state) {
    this.field_70180_af.func_75692_b(28, Integer.valueOf(state));
  }
  
  public int getSubState() {
    return this.field_70180_af.func_75679_c(29);
  }
  
  public void setSubState(int subState) {
    this.field_70180_af.func_75692_b(29, Integer.valueOf(subState));
  }
  
  public EntityMinerBoss(World world) {
    super(world);
    func_70105_a(2.0F, 2.0F);
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIMinerBossAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70715_bh.func_75776_a(0, (EntityAIBase)new EntityAIMinerBossNearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    this.attacks.add(new EntityMinerBossPunchAttack());
    this.attacks.add(new EntityMinerBossTurnAttack());
    this.attacks.add(new EntityMinerBossLeftShockWaveAttack());
    this.attacks.add(new EntityMinerBossStrikingGroundAttack());
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(16, Boolean.toString(false));
    this.field_70180_af.func_75682_a(17, Integer.valueOf(0));
    this.field_70180_af.func_75682_a(18, Boolean.toString(false));
    this.field_70180_af.func_75682_a(19, Float.valueOf(0.0F));
    this.field_70180_af.func_75682_a(20, Float.valueOf(0.0F));
    this.field_70180_af.func_75682_a(21, Boolean.toString(false));
    this.field_70180_af.func_75682_a(22, Float.valueOf(0.0F));
    this.field_70180_af.func_75682_a(23, Float.valueOf(0.0F));
    this.field_70180_af.func_75682_a(24, Boolean.toString(false));
    this.field_70180_af.func_75682_a(25, Boolean.toString(false));
    this.field_70180_af.func_75682_a(26, Boolean.toString(false));
    this.field_70180_af.func_75682_a(27, Boolean.toString(false));
    this.field_70180_af.func_75682_a(28, Integer.valueOf(0));
    this.field_70180_af.func_75682_a(29, Integer.valueOf(0));
  }
  
  protected boolean func_70650_aV() {
    return !isStunted();
  }
  
  public void func_70636_d() {
    if (isStunted()) {
      setStuntedDelay(getStuntedDelay() - 1);
      if (getStuntedDelay() <= 0)
        setStunted(false); 
      return;
    } 
    if (this.currentAttack != null && func_70638_az() != null && !isStunted())
      if (!this.currentAttack.isFinished(this)) {
        this.currentAttack.update(this, func_70638_az());
      } else {
        this.currentAttack = null;
      }  
    super.func_70636_d();
    if (isTurnAttack())
      func_70101_b(this.field_70177_z + 60.0F, this.field_70125_A); 
  }
  
  public void func_70015_d(int p_70015_1_) {}
  
  public boolean func_90999_ad() {
    return false;
  }
  
  protected void func_70044_A() {}
  
  public boolean func_70097_a(DamageSource source, float damage) {
    if (isStunted()) {
      if (source.func_76364_f() != null)
        source.func_76364_f().func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), 14.0F); 
      setStunted(false);
      if (getSubState() == 1) {
        setState(getState() + 1);
        setSubState(0);
      } else {
        setSubState(getSubState() + 1);
      } 
      if (getState() >= 6) {
        setState(6);
        super.func_70097_a(source, damage);
      } else if (source.func_76364_f() != null) {
        setCurrentAttack((IEntityMinerBossAttack)new EntityMinerBossRepulseAttack());
        getCurrentAttack().start(this, (EntityLivingBase)source.func_76364_f());
        getCurrentAttack().update(this, (EntityLivingBase)source.func_76364_f());
      } 
      return true;
    } 
    if (getState() >= 6) {
      setState(6);
      return super.func_70097_a(source, damage);
    } 
    return false;
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(50.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2D);
    func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(10000.0D);
  }
  
  public void func_70014_b(NBTTagCompound compound) {
    super.func_70014_b(compound);
  }
  
  public void func_70037_a(NBTTagCompound compound) {
    super.func_70037_a(compound);
  }
  
  public void func_82196_d(EntityLivingBase p_82196_1_, float p_82196_2_) {}
  
  public void writeSpawnData(ByteBuf buffer) {}
  
  public void readSpawnData(ByteBuf additionalData) {}
  
  protected Item func_146068_u() {
    return ItemsRegister.ENDIUM_NUGGET;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\entity\EntityMinerBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */