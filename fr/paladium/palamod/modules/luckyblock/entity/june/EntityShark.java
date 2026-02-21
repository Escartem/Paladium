package fr.paladium.palamod.modules.luckyblock.entity.june;

import fr.paladium.palamod.modules.luckyblock.entity.EntityCreatureFlying;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityShark extends EntityCreatureFlying {
  private static final int SHOW_ONLY_FIN = 12;
  
  private Entity currentTarget;
  
  public void setCurrentTarget(Entity currentTarget) {
    this.currentTarget = currentTarget;
  }
  
  public Entity getCurrentTarget() {
    return this.currentTarget;
  }
  
  public EntityShark(World world) {
    super(world);
    func_70105_a(2.0F, 0.8F);
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(12, Byte.valueOf((byte)0));
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    this.field_70180_af.func_75692_b(12, Byte.valueOf(nbt.func_74767_n("ShowOnlyFin") ? 1 : 0));
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74757_a("ShowOnlyFin", (this.field_70180_af.func_75683_a(12) == 1));
  }
  
  public boolean isShowOnlyFin() {
    return (this.field_70180_af.func_75683_a(12) == 1);
  }
  
  public void setShowOnlyFin(boolean state) {
    this.field_70180_af.func_75692_b(12, Byte.valueOf(state ? 1 : 0));
  }
  
  public boolean func_70104_M() {
    return false;
  }
  
  public boolean func_82171_bF() {
    return false;
  }
  
  protected void func_70626_be() {
    this.field_70702_br = 0.0F;
    this.field_70701_bs = 0.0F;
    if (this.currentTarget != null)
      func_70625_a(this.currentTarget, 10.0F, func_70646_bf()); 
  }
  
  public boolean func_85032_ar() {
    return true;
  }
  
  protected void func_82164_bB() {}
  
  public void func_82167_n(Entity entity) {}
  
  public void func_85033_bc() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\june\EntityShark.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */