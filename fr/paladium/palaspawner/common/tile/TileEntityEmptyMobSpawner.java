package fr.paladium.palaspawner.common.tile;

import com.google.common.base.Strings;
import fr.paladium.helios.utils.Vec3i;
import java.util.UUID;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEmptyMobSpawner extends TileEntity {
  public static final String TAG_ENTITY = "entityId";
  
  public static final String TAG_SOULS = "souls";
  
  public static final String TAG_SECURITY_UUID = "securityUuid";
  
  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }
  
  public void setSouls(int souls) {
    this.souls = souls;
  }
  
  public void setSecurityUuid(UUID securityUuid) {
    this.securityUuid = securityUuid;
  }
  
  public void setLinkedController(Vec3i linkedController) {
    this.linkedController = linkedController;
  }
  
  public static final Vec3i NULL = new Vec3i(0, 0, 0);
  
  private String entityId;
  
  private int souls;
  
  private UUID securityUuid;
  
  public String getEntityId() {
    return this.entityId;
  }
  
  public int getSouls() {
    return this.souls;
  }
  
  public UUID getSecurityUuid() {
    return this.securityUuid;
  }
  
  private Vec3i linkedController = NULL;
  
  public Vec3i getLinkedController() {
    return this.linkedController;
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    if (this.entityId == null || !this.field_145850_b.field_72995_K)
      return; 
    double d0 = (this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat());
    double d1 = (this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat());
    double d2 = (this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat());
    this.field_145850_b.func_72869_a("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
    this.field_145850_b.func_72869_a("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    if (!compound.func_74764_b("entityId"))
      return; 
    this.entityId = compound.func_74779_i("entityId");
    this.souls = compound.func_74762_e("souls");
    this.securityUuid = UUID.fromString(compound.func_74779_i("securityUuid"));
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    if (Strings.isNullOrEmpty(this.entityId))
      return; 
    compound.func_74778_a("entityId", this.entityId);
    compound.func_74768_a("souls", this.souls);
    compound.func_74778_a("securityUuid", this.securityUuid.toString());
  }
  
  public Tier getTier() {
    return Tier.getTier(this.souls);
  }
  
  public int getIntTier() {
    return Tier.getTier(this.souls).ordinal();
  }
  
  public Packet func_145844_m() {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    func_145841_b(nbttagcompound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbttagcompound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
    func_145839_a(packet.func_148857_g());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\tile\TileEntityEmptyMobSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */