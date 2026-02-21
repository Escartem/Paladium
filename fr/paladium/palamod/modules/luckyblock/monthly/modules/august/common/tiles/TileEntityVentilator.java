package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles;

import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.VentilatorDirection;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.ChunkUtils;
import fr.paladium.palamod.util.FastUUID;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;

public class TileEntityVentilator extends TileEntity {
  public static final String TILE_ENTITY_ID = "tileEntityVentilator";
  
  public static final String NBT_DIRECTION_TAG = "direction";
  
  public static final String NBT_SECOND_TAG = "second";
  
  public static final String NBT_ACTIVE_TAG = "active";
  
  public static final String NBT_OWNER_TAG = "ownerId";
  
  public static final int MAX_DURATION_SECONDS = 600;
  
  public void setDirection(VentilatorDirection direction) {
    this.direction = direction;
  }
  
  public void setTick(int tick) {
    this.tick = tick;
  }
  
  public void setSecond(int second) {
    this.second = second;
  }
  
  public void setActive(boolean active) {
    this.active = active;
  }
  
  public void setOwnerId(UUID ownerId) {
    this.ownerId = ownerId;
  }
  
  public void setCuboid(Cuboid cuboid) {
    this.cuboid = cuboid;
  }
  
  public VentilatorDirection getDirection() {
    return this.direction;
  }
  
  public int getTick() {
    return this.tick;
  }
  
  public int getSecond() {
    return this.second;
  }
  
  public boolean isActive() {
    return this.active;
  }
  
  public UUID getOwnerId() {
    return this.ownerId;
  }
  
  public Cuboid getCuboid() {
    return this.cuboid;
  }
  
  private VentilatorDirection direction = VentilatorDirection.NORTH;
  
  private int tick = 0;
  
  private int second = 0;
  
  private boolean active = false;
  
  private UUID ownerId = null;
  
  private Cuboid cuboid;
  
  public void func_145845_h() {
    super.func_145845_h();
    if (this.field_145850_b.field_72995_K)
      return; 
    if (this.direction == null)
      return; 
    if (!this.active)
      return; 
    this.tick++;
    if (this.tick % 10 != 0)
      return; 
    if (this.tick >= 20) {
      this.tick = 0;
      this.second++;
    } 
    if (this.second >= 600) {
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      return;
    } 
    if (this.cuboid == null)
      this.cuboid = initCuboid(); 
    WorldServer worldServer = (WorldServer)this.field_145850_b;
    for (Location location : this.cuboid.getLocations())
      worldServer.func_147487_a("explode", location.getBlockX(), location.getBlockY(), location.getBlockZ(), 1, 0.5D, 0.5D, 0.5D, 0.1D); 
    for (EntityPlayerMP entity : getEntities(this.cuboid)) {
      double recoilX = this.direction.getXOffset();
      double recoilZ = this.direction.getZOffset();
      double newX = entity.field_70165_t + recoilX;
      double newZ = entity.field_70161_v + recoilZ;
      int x = (int)Math.floor(newX);
      int y = (int)Math.floor(entity.field_70163_u);
      int z = (int)Math.floor(newZ);
      if (this.field_145850_b.func_147439_a(x, y, z).func_149688_o().func_76220_a())
        continue; 
      if (this.field_145850_b.func_147439_a(x, y + 1, z).func_149688_o().func_76220_a())
        continue; 
      TeleportUtils.teleport((EntityPlayer)entity, newX, entity.field_70163_u, newZ, entity.field_70177_z, entity.field_70125_A);
    } 
  }
  
  public void updateCuboid() {
    this.cuboid = initCuboid();
  }
  
  private Cuboid initCuboid() {
    double x = 0.0D;
    double z = 0.0D;
    Cuboid cuboid = null;
    if (this.direction == VentilatorDirection.NORTH || this.direction == VentilatorDirection.SOUTH) {
      x = (this.field_145851_c + 0);
      z = (this.field_145849_e + this.direction.getZOffset() * 7);
      cuboid = new Cuboid(this.field_145850_b, x - 1.0D, (this.field_145848_d + 1), this.field_145849_e, x + 1.0D, (this.field_145848_d - 1), z);
    } 
    if (this.direction == VentilatorDirection.EAST || this.direction == VentilatorDirection.WEST) {
      x = (this.field_145851_c + this.direction.getXOffset() * 7);
      z = (this.field_145849_e + 0);
      cuboid = new Cuboid(this.field_145850_b, this.field_145851_c, (this.field_145848_d + 1), (this.field_145849_e - 1), x, (this.field_145848_d - 1), z + 1.0D);
    } 
    return cuboid;
  }
  
  private List<EntityPlayerMP> getEntities(Cuboid cuboid) {
    return ChunkUtils.getPlayers(cuboid);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    int ordinal = (this.direction == null) ? 0 : this.direction.ordinal();
    compound.func_74768_a("direction", ordinal);
    compound.func_74768_a("second", this.second);
    compound.func_74757_a("active", this.active);
    if (this.ownerId == null)
      return; 
    compound.func_74778_a("ownerId", FastUUID.toString(this.ownerId));
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    if (!compound.func_74764_b("direction") || !compound.func_74764_b("second") || !compound.func_74764_b("active"))
      return; 
    int ordinal = compound.func_74762_e("direction");
    this.direction = VentilatorDirection.values()[ordinal];
    this.second = compound.func_74762_e("second");
    this.active = compound.func_74767_n("active");
    if (!compound.func_74764_b("ownerId"))
      return; 
    this.ownerId = FastUUID.parseUUID(compound.func_74779_i("ownerId"));
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    func_145841_b(nbttagcompound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, nbttagcompound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
    this.field_145850_b.func_147458_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\tiles\TileEntityVentilator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */