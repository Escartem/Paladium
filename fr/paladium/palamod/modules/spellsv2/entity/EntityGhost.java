package fr.paladium.palamod.modules.spellsv2.entity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGhost extends EntityFlying implements IEntityAdditionalSpawnData {
  private EntityPlayerMP player;
  
  public String owner;
  
  public EntityPlayerMP getPlayer() {
    return this.player;
  }
  
  public String getOwner() {
    return this.owner;
  }
  
  public EntityGhost(World world) {
    super(world);
  }
  
  public EntityGhost(World world, EntityPlayerMP player) {
    super(world);
    this.field_70145_X = true;
    this.player = player;
    this.owner = player.func_70005_c_();
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
    func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(100.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
  }
  
  protected boolean func_70650_aV() {
    return false;
  }
  
  public AxisAlignedBB func_70114_g(Entity p_70114_1_) {
    return AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
  }
  
  public void func_70108_f(Entity p_70108_1_) {}
  
  public void func_70057_ab() {}
  
  public boolean func_70097_a(DamageSource source, float p_70097_2_) {
    if (source == DamageSource.field_76368_d || source == DamageSource.field_76379_h || source == DamageSource.field_82729_p || source == DamageSource.field_82728_o || source == DamageSource.field_76369_e || source == DamageSource.field_76372_a || source == DamageSource.field_76370_b || source == DamageSource.field_76371_c)
      return false; 
    return super.func_70097_a(source, p_70097_2_);
  }
  
  protected void func_70665_d(DamageSource source, float p_70665_2_) {
    if (source == DamageSource.field_76368_d || source == DamageSource.field_76379_h || source == DamageSource.field_82729_p || source == DamageSource.field_82728_o || source == DamageSource.field_76369_e || source == DamageSource.field_76372_a || source == DamageSource.field_76370_b || source == DamageSource.field_76371_c)
      return; 
    super.func_70665_d(source, p_70665_2_);
  }
  
  public void func_70037_a(NBTTagCompound compound) {
    if (compound.func_74764_b("owner"))
      this.owner = compound.func_74779_i("owner"); 
    super.func_70037_a(compound);
  }
  
  public void func_70014_b(NBTTagCompound compound) {
    if (this.owner != null)
      compound.func_74778_a("owner", this.owner); 
    super.func_70014_b(compound);
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    if (this.owner != null)
      ByteBufUtils.writeUTF8String(buffer, this.owner.toString()); 
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (additionalData.isReadable())
      this.owner = ByteBufUtils.readUTF8String(additionalData); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\entity\EntityGhost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */