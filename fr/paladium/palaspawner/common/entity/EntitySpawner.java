package fr.paladium.palaspawner.common.entity;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.blueprint.common.utils.BlockPos;
import fr.paladium.palajobs.api.PalaJobsAPI;
import fr.paladium.palaspawner.common.event.FakeSpawnerEntityKillEvent;
import fr.paladium.palaspawner.common.manager.SpawnerManager;
import fr.paladium.palaspawner.common.spawner.data.ASpawnerEntityData;
import fr.paladium.palaspawner.common.tile.TileEntitySpawnerController;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class EntitySpawner extends EntityMob {
  public static final int TYPE_WATCHER_ID = 12;
  
  public static final int HOLOGRAM_WATCHER_ID = 13;
  
  private BlockPos linkedControllerPosition;
  
  public BlockPos getLinkedControllerPosition() {
    return this.linkedControllerPosition;
  }
  
  public EntitySpawner(World world) {
    super(world);
    func_70105_a(0.6F, 1.8F);
  }
  
  public EntitySpawner(World world, int x, int y, int z, BlockPos linkedControllerPosition) {
    super(world);
    func_70105_a(0.6F, 1.8F);
    func_70107_b(x + 0.5D, y, z + 0.5D);
    this.linkedControllerPosition = linkedControllerPosition;
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    this.field_70159_w = 0.0D;
    this.field_70181_x = 0.0D;
    this.field_70179_y = 0.0D;
    EntityPlayer player = this.field_70170_p.func_72890_a((Entity)this, 10.0D);
    if (player != null) {
      double deltaX = player.field_70165_t - this.field_70165_t;
      double deltaZ = player.field_70161_v - this.field_70161_v;
      double deltaY = player.field_70163_u + player.func_70047_e() - this.field_70163_u + func_70047_e();
      float targetYaw = (float)(Math.atan2(deltaZ, deltaX) * 57.29577951308232D) - 90.0F;
      float targetPitch = (float)-Math.toDegrees(Math.atan2(deltaY, Math.sqrt(deltaX * deltaX + deltaZ * deltaZ)));
      this.field_70177_z = targetYaw;
      this.field_70759_as = targetYaw;
      this.field_70761_aq = targetYaw;
      this.field_70125_A = targetPitch;
      this.field_70127_C = targetPitch;
    } 
    if (this.field_70170_p.field_72995_K)
      return; 
    if (this.field_70173_aa % 20 == 0) {
      TileEntitySpawnerController controller = getLinkedController();
      if (controller == null || distance(controller.field_145851_c, controller.field_145848_d, controller.field_145849_e) > 2.0D)
        func_70106_y(); 
    } 
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(Double.MAX_VALUE);
    func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(0.0D);
  }
  
  public double distance(double x, double y, double z) {
    double d3 = this.field_70165_t - x;
    double d4 = this.field_70163_u - y;
    double d5 = this.field_70161_v - z;
    return MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
  }
  
  protected void func_70785_a(Entity entity, float f) {}
  
  public boolean func_70652_k(Entity entity) {
    return false;
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    if (this.field_70714_bg != null) {
      this.field_70714_bg.field_75782_a.clear();
      this.field_70715_bh.field_75782_a.clear();
    } 
    this.field_70180_af.func_75682_a(12, "");
    this.field_70180_af.func_75682_a(13, "");
  }
  
  protected boolean func_70085_c(EntityPlayer player) {
    if (player.field_70170_p.field_72995_K)
      return false; 
    TileEntitySpawnerController controller = getLinkedController();
    if (controller == null)
      return false; 
    controller.nextOrPreviousMobData(true);
    return false;
  }
  
  public boolean func_70097_a(DamageSource damageSource, float value) {
    boolean damaged = super.func_70097_a(damageSource, value);
    if (damaged && !this.field_70170_p.field_72995_K) {
      TileEntitySpawnerController controller = getLinkedController();
      if (controller == null)
        return false; 
      if (damageSource.func_76346_g() instanceof EntityPlayer) {
        this.field_70172_ad = 11;
        controller.decrementCurrentSpawnedMobs();
        controller.dropOrPutIntoInventory((EntityPlayerMP)damageSource.func_76346_g(), getEntityType());
        ASpawnerEntityData data = SpawnerManager.getInstance().getSpawnerData(controller.getSelectedEntityType());
        if (data == null || data.getExperiencePoints() <= 0)
          return false; 
        double x = this.field_70165_t;
        double y = this.field_70163_u;
        double z = this.field_70161_v;
        Entity entity = damageSource.func_76346_g();
        if (entity != null) {
          x = entity.field_70165_t;
          y = entity.field_70163_u;
          z = entity.field_70161_v;
          MinecraftForge.EVENT_BUS.post((Event)new FakeSpawnerEntityKillEvent(entity, this, data.getEntityClass(), controller));
          if (!(controller.func_145831_w()).field_72995_K && entity instanceof EntityPlayer)
            PalaJobsAPI.inst().performKill((EntityPlayer)entity, data.getEntityClass()); 
        } 
        this.field_70170_p.func_72838_d((Entity)new EntityXPOrb(this.field_70170_p, x, y, z, data.getExperiencePoints()));
        return true;
      } 
    } 
    return false;
  }
  
  public TileEntitySpawnerController getLinkedController() {
    if (this.linkedControllerPosition == null)
      return null; 
    TileEntity tile = this.field_70170_p.func_147438_o(this.linkedControllerPosition.getX(), this.linkedControllerPosition.getY(), this.linkedControllerPosition.getZ());
    if (!(tile instanceof TileEntitySpawnerController))
      return null; 
    return (TileEntitySpawnerController)tile;
  }
  
  protected void func_70665_d(DamageSource source, float f) {}
  
  public void func_70100_b_(EntityPlayer player) {
    super.func_70100_b_(player);
  }
  
  protected boolean func_70650_aV() {
    return false;
  }
  
  public void func_70109_d(NBTTagCompound compound) {
    super.func_70109_d(compound);
  }
  
  public void func_70020_e(NBTTagCompound compound) {
    super.func_70020_e(compound);
  }
  
  public void func_70645_a(DamageSource damageSource) {
    super.func_70645_a(damageSource);
  }
  
  public boolean func_70601_bi() {
    return true;
  }
  
  public boolean func_85032_ar() {
    return false;
  }
  
  public boolean func_70104_M() {
    return false;
  }
  
  public boolean func_82171_bF() {
    return false;
  }
  
  protected void func_82167_n(Entity p_82167_1_) {}
  
  public boolean func_90999_ad() {
    return false;
  }
  
  protected void func_85033_bc() {}
  
  protected boolean func_70780_i() {
    return true;
  }
  
  public ItemStack func_70694_bm() {
    return null;
  }
  
  public ItemStack func_71124_b(int slotID) {
    return null;
  }
  
  public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_) {}
  
  public ItemStack[] func_70035_c() {
    return super.func_70035_c();
  }
  
  public boolean func_94056_bM() {
    return super.func_94056_bM();
  }
  
  public String getEntityType() {
    return this.field_70180_af.func_75681_e(12);
  }
  
  private void setEntityType(String type) {
    this.field_70180_af.func_75692_b(12, type);
  }
  
  public String getHologramText() {
    return this.field_70180_af.func_75681_e(13);
  }
  
  private void setHologramText(String text) {
    this.field_70180_af.func_75692_b(13, text);
  }
  
  public void setTypeAndHologram(String type, int amount) {
    setEntityType(type);
    setHologramText("§e" + type + " §7- §cx" + amount);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\entity\EntitySpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */