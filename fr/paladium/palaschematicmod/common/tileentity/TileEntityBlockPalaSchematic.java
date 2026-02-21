package fr.paladium.palaschematicmod.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import fr.paladium.palaschematicmod.common.pojo.schematic.Schematic;
import fr.paladium.palaschematicmod.common.pojo.schematic.SchematicCopySession;
import fr.paladium.palaschematicmod.common.utils.SchematicUtils;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import lombok.NonNull;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBlockPalaSchematic extends TileEntity {
  private String schematicName;
  
  private int relativeX;
  
  private int relativeY;
  
  private int relativeZ;
  
  private int sizeX;
  
  private int sizeY;
  
  private int sizeZ;
  
  public String getSchematicName() {
    return this.schematicName;
  }
  
  public int getRelativeX() {
    return this.relativeX;
  }
  
  public int getRelativeY() {
    return this.relativeY;
  }
  
  public int getRelativeZ() {
    return this.relativeZ;
  }
  
  public int getSizeX() {
    return this.sizeX;
  }
  
  public int getSizeY() {
    return this.sizeY;
  }
  
  public int getSizeZ() {
    return this.sizeZ;
  }
  
  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    readAdditionalFromNBT(nbt);
  }
  
  public void readAdditionalFromNBT(NBTTagCompound nbt) {
    if (nbt.func_74764_b("schematicName"))
      this.schematicName = nbt.func_74779_i("schematicName"); 
    this.relativeX = nbt.func_74762_e("relativeX");
    this.relativeY = nbt.func_74762_e("relativeY");
    this.relativeZ = nbt.func_74762_e("relativeZ");
    this.sizeX = nbt.func_74762_e("sizeX");
    this.sizeY = nbt.func_74762_e("sizeY");
    this.sizeZ = nbt.func_74762_e("sizeZ");
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    writeAdditionalToNBT(nbt);
  }
  
  public void writeAdditionalToNBT(NBTTagCompound nbt) {
    if (this.schematicName != null)
      nbt.func_74778_a("schematicName", this.schematicName); 
    nbt.func_74768_a("relativeX", this.relativeX);
    nbt.func_74768_a("relativeY", this.relativeY);
    nbt.func_74768_a("relativeZ", this.relativeZ);
    nbt.func_74768_a("sizeX", this.sizeX);
    nbt.func_74768_a("sizeY", this.sizeY);
    nbt.func_74768_a("sizeZ", this.sizeZ);
  }
  
  @SideOnly(Side.SERVER)
  public void update(String schematicName, int relativeX, int relativeY, int relativeZ, int sizeX, int sizeY, int sizeZ) {
    this.schematicName = schematicName;
    this.relativeX = relativeX;
    this.relativeY = relativeY;
    this.relativeZ = relativeZ;
    this.sizeX = sizeX;
    this.sizeY = sizeY;
    this.sizeZ = sizeZ;
    sync();
  }
  
  @SideOnly(Side.SERVER)
  public void sync() {
    if (func_145831_w() == null)
      return; 
    func_70296_d();
    func_145831_w().func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
  
  @SideOnly(Side.SERVER)
  public void export(@NonNull String author) {
    if (author == null)
      throw new NullPointerException("author is marked non-null but is null"); 
    if (func_145831_w() == null || this.schematicName == null || this.schematicName.isEmpty())
      return; 
    if (this.sizeX <= 0 || this.sizeY <= 0 || this.sizeZ <= 0)
      return; 
    BlockPos center = new BlockPos(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    BlockPos firstPos = new BlockPos(center.getX() + this.relativeX, center.getY() + this.relativeY, center.getZ() + this.relativeZ);
    BlockPos secondPos = new BlockPos(firstPos.getX() + this.sizeX, firstPos.getY() + this.sizeY, firstPos.getZ() + this.sizeZ);
    SchematicCopySession session = new SchematicCopySession();
    session.firstPos = firstPos;
    session.secondPos = secondPos;
    Schematic schematic = new Schematic(this.schematicName, author, 1, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
    SchematicUtils.generateSchematicData(func_145831_w(), center, schematic, session);
    SchematicUtils.saveSchematic(schematic);
  }
  
  public Packet func_145844_m() {
    NBTTagCompound compound = new NBTTagCompound();
    func_145841_b(compound);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, compound);
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
    this.field_145850_b.func_147458_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\common\tileentity\TileEntityBlockPalaSchematic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */