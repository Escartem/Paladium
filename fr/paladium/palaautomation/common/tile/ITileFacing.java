package fr.paladium.palaautomation.common.tile;

import fr.paladium.palaautomation.common.packet.BBCrafterChangeStatePacket;
import fr.paladium.palaautomation.common.tile.impl.TileEntityCrafter;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public interface ITileFacing {
  public static final double UPDATE_RADIUS = 64.0D;
  
  EnumPipeFacing getFacing();
  
  void setFacing(EnumPipeFacing paramEnumPipeFacing);
  
  TileEntity getTileEntity();
  
  default void rotate() {
    setFacing(EnumPipeFacing.next(getFacing()));
  }
  
  default void sendUpdatePacket(EntityPlayerMP playerMP) {
    TileEntity tile = getTileEntity();
    if (tile == null)
      return; 
    Packet packet = tile.func_145844_m();
    if (packet == null)
      return; 
    playerMP.field_71135_a.func_147359_a(packet);
    if (tile instanceof TileEntityCrafter) {
      TileEntityCrafter crafter = (TileEntityCrafter)tile;
      (new BBCrafterChangeStatePacket(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, crafter.isEnabled())).send(playerMP);
    } 
  }
  
  default void updateOnRadius() {
    TileEntity tile = getTileEntity();
    if (tile == null)
      return; 
    tile.func_145831_w().func_72872_a(EntityPlayerMP.class, 
        
        AxisAlignedBB.func_72330_a(tile.field_145851_c - 64.0D, tile.field_145848_d - 64.0D, tile.field_145849_e - 64.0D, tile.field_145851_c + 64.0D, tile.field_145848_d + 64.0D, tile.field_145849_e + 64.0D))
      
      .forEach(player -> sendUpdatePacket((EntityPlayerMP)player));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\tile\ITileFacing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */