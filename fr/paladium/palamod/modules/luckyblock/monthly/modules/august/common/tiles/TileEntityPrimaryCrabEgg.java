package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles;

import fr.paladium.factions.server.utils.PlayerUtils;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.util.FastUUID;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public class TileEntityPrimaryCrabEgg extends TileEntity {
  public static final String TILE_ENTITY_ID = "tileEntityPrimaryCrabEgg";
  
  public static final String NBT_OWNER_ID = "ownerId";
  
  public static final String NBT_TIME_ELAPSED = "timeElapsed";
  
  public void setOwnerId(UUID ownerId) {
    this.ownerId = ownerId;
  }
  
  public void setTimeElapsed(long timeElapsed) {
    this.timeElapsed = timeElapsed;
  }
  
  public void setTick(int tick) {
    this.tick = tick;
  }
  
  public static final long MAX_TIME_ELAPSED = TimeUnit.MINUTES.toSeconds(2L);
  
  public UUID getOwnerId() {
    return this.ownerId;
  }
  
  public long getTimeElapsed() {
    return this.timeElapsed;
  }
  
  public int getTick() {
    return this.tick;
  }
  
  private UUID ownerId = null;
  
  private long timeElapsed = 0L;
  
  private int tick = 0;
  
  public void func_145845_h() {
    super.func_145845_h();
    if (this.field_145850_b.field_72995_K)
      return; 
    if (this.ownerId == null)
      return; 
    this.tick++;
    if (this.tick % 20 != 0)
      return; 
    EntityPlayerMP entityPlayerMP = PlayerUtils.getPlayer(this.ownerId);
    if (entityPlayerMP == null)
      return; 
    if (this.timeElapsed >= MAX_TIME_ELAPSED) {
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      ItemUtils.spawnItemAtEntity((Entity)entityPlayerMP, new ItemStack(ItemsRegister.CRAB_EGG));
      return;
    } 
    if (!isOwnerUpperTile((EntityPlayer)entityPlayerMP))
      return; 
    this.timeElapsed++;
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    if (this.ownerId == null)
      return; 
    compound.func_74778_a("ownerId", FastUUID.toString(this.ownerId));
    compound.func_74772_a("timeElapsed", this.timeElapsed);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    if (!compound.func_74764_b("ownerId") || !compound.func_74764_b("timeElapsed"))
      return; 
    this.ownerId = FastUUID.parseUUID(compound.func_74779_i("ownerId"));
    this.timeElapsed = compound.func_74763_f("timeElapsed");
  }
  
  public long getRemainingTime() {
    return TimeUnit.SECONDS.toMillis(MAX_TIME_ELAPSED - this.timeElapsed);
  }
  
  private boolean isOwnerUpperTile(EntityPlayer player) {
    int blockX = MathHelper.func_76128_c(player.field_70165_t);
    int blockY = MathHelper.func_76128_c(player.field_70163_u);
    int blockZ = MathHelper.func_76128_c(player.field_70161_v);
    return (blockX == this.field_145851_c && blockY == this.field_145848_d + 1 && blockZ == this.field_145849_e);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\tiles\TileEntityPrimaryCrabEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */