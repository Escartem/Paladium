package fr.paladium.palaschematicmod.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaschematicmod.common.tileentity.TileEntityBlockPalaSchematic;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class CSPacketExportBlockPalaSchematic extends ForgePacket {
  @PacketData
  private int x;
  
  @PacketData
  private int y;
  
  @PacketData
  private int z;
  
  public CSPacketExportBlockPalaSchematic() {}
  
  public CSPacketExportBlockPalaSchematic(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    if (!player.field_71075_bZ.field_75098_d || !player.field_70170_p.func_72899_e(this.x, this.y, this.z))
      return; 
    TileEntity tileEntity = player.field_70170_p.func_147438_o(this.x, this.y, this.z);
    if (!(tileEntity instanceof TileEntityBlockPalaSchematic))
      return; 
    ((TileEntityBlockPalaSchematic)tileEntity).export(player.func_70005_c_());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\common\network\CSPacketExportBlockPalaSchematic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */