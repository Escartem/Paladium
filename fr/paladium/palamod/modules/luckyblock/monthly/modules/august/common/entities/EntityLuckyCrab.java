package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.entities;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.consumable.ItemCrabEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityCrabCobbleBreaker;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EntityLuckyCrab extends EntityMob {
  public static final String ENTITY_ID = "entityLuckyCrab";
  
  private long spawnMillis;
  
  public EntityLuckyCrab(World world) {
    super(world);
    func_70105_a(0.9F, 0.4F);
    this.field_70145_X = false;
    this.field_70178_ae = true;
    this.spawnMillis = System.currentTimeMillis();
  }
  
  public boolean spawn(EntityPlayer player, World world, double x, double y, double z) {
    if (!spawnCobbleBreaker((EntityPlayerMP)player, new DoubleLocation((Entity)this), player.func_110124_au()))
      return false; 
    func_70107_b(x, y, z);
    world.func_72838_d((Entity)this);
    return true;
  }
  
  protected boolean func_70085_c(EntityPlayer player) {
    return true;
  }
  
  public void func_70636_d() {
    super.func_70636_d();
    if (this.field_70170_p.field_72995_K)
      return; 
    this.field_70181_x *= 0.0D;
    this.field_70159_w *= 0.0D;
    this.field_70179_y *= 0.0D;
    long now = System.currentTimeMillis();
    if (isExpired(now)) {
      func_70106_y();
      return;
    } 
  }
  
  public void func_70109_d(NBTTagCompound tagCompound) {
    super.func_70109_d(tagCompound);
    tagCompound.func_74772_a("spawnMillis", this.spawnMillis);
  }
  
  public void func_70020_e(NBTTagCompound tagCompound) {
    super.func_70020_e(tagCompound);
    this.spawnMillis = tagCompound.func_74763_f("spawnMillis");
  }
  
  private boolean hasCobbleBreaker(DoubleLocation location) {
    int blockX = location.getBlockX();
    int blockY = location.getBlockY() - 1;
    int blockZ = location.getBlockZ();
    Block block = this.field_70170_p.func_147439_a(blockX, blockY, blockZ);
    if (!(block instanceof fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks.BlockCrabCobbleBreaker))
      return false; 
    TileEntity tileEntity = this.field_70170_p.func_147438_o(blockX, blockY, blockZ);
    if (!(tileEntity instanceof TileEntityCrabCobbleBreaker))
      return false; 
    TileEntityCrabCobbleBreaker cobbleBreaker = (TileEntityCrabCobbleBreaker)tileEntity;
    if (cobbleBreaker.getEntityUniqueId() == null || !cobbleBreaker.getEntityUniqueId().equals(func_110124_au()))
      return false; 
    return true;
  }
  
  private boolean spawnCobbleBreaker(EntityPlayerMP player, DoubleLocation location, UUID ownerUniqueId) {
    int blockX = location.getBlockX();
    int blockY = location.getBlockY() - 1;
    int blockZ = location.getBlockZ();
    if (this.field_70170_p.func_147439_a(blockX, blockY, blockZ) == Blocks.field_150357_h)
      return false; 
    if (!EventUtils.canInteract((EntityPlayer)player, blockX, blockY, blockZ))
      return false; 
    this.field_70170_p.func_147449_b(blockX, blockY, blockZ, BlocksRegister.CRAB_COBBLEBREAKER);
    TileEntity tileEntity = this.field_70170_p.func_147438_o(blockX, blockY, blockZ);
    if (!(tileEntity instanceof TileEntityCrabCobbleBreaker))
      return false; 
    TileEntityCrabCobbleBreaker cobbleBreaker = (TileEntityCrabCobbleBreaker)tileEntity;
    cobbleBreaker.setEntityUniqueId(func_110124_au());
    cobbleBreaker.setSpawnMillis(this.spawnMillis);
    cobbleBreaker.setOwnerUniqueId(ownerUniqueId);
    return true;
  }
  
  private boolean isExpired(long now) {
    return (now - this.spawnMillis > ItemCrabEgg.COOLDOWN_DURATION.longValue());
  }
  
  public void func_70612_e(float strafe, float forward) {}
  
  public boolean func_70617_f_() {
    return false;
  }
  
  public boolean func_70067_L() {
    return false;
  }
  
  protected boolean func_70610_aX() {
    return true;
  }
  
  public void func_70024_g(double x, double y, double z) {}
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\entities\EntityLuckyCrab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */