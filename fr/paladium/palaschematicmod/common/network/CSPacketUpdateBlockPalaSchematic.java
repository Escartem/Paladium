package fr.paladium.palaschematicmod.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaschematicmod.common.tileentity.TileEntityBlockPalaSchematic;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class CSPacketUpdateBlockPalaSchematic extends ForgePacket {
  @PacketData
  private int x;
  
  @PacketData
  private int y;
  
  @PacketData
  private int z;
  
  @PacketData
  private String schematicName;
  
  @PacketData
  private int relativeX;
  
  @PacketData
  private int relativeY;
  
  @PacketData
  private int relativeZ;
  
  @PacketData
  private int sizeX;
  
  @PacketData
  private int sizeY;
  
  @PacketData
  private int sizeZ;
  
  public CSPacketUpdateBlockPalaSchematic() {}
  
  public CSPacketUpdateBlockPalaSchematic(int x, int y, int z, String schematicName, int relativeX, int relativeY, int relativeZ, int sizeX, int sizeY, int sizeZ) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.schematicName = schematicName;
    this.relativeX = relativeX;
    this.relativeY = relativeY;
    this.relativeZ = relativeZ;
    this.sizeX = sizeX;
    this.sizeY = sizeY;
    this.sizeZ = sizeZ;
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    if (!player.field_71075_bZ.field_75098_d || !player.field_70170_p.func_72899_e(this.x, this.y, this.z))
      return; 
    TileEntity tileEntity = player.field_70170_p.func_147438_o(this.x, this.y, this.z);
    if (!(tileEntity instanceof TileEntityBlockPalaSchematic))
      return; 
    ((TileEntityBlockPalaSchematic)tileEntity).update(this.schematicName, this.relativeX, this.relativeY, this.relativeZ, this.sizeX, this.sizeY, this.sizeZ);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\common\network\CSPacketUpdateBlockPalaSchematic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */