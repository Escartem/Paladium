package fr.paladium.palaautomation.common.tile;

import fr.paladium.palaautomation.common.tile.pipe.ATileEntityPipe;
import fr.paladium.palaautomation.common.tile.util.PipeItemData;
import fr.paladium.palaautomation.common.util.PipeError;
import fr.paladium.palaautomation.common.util.PipeNetwork;
import fr.paladium.palaautomation.common.util.PipeUtils;
import net.minecraft.tileentity.TileEntity;

public interface IPipeMachine extends ITileFacing {
  boolean setItemData(PipeItemData paramPipeItemData, boolean paramBoolean);
  
  PipeItemData getItemData();
  
  EnumPipeFacing getFacing();
  
  boolean receiveFromPipe(PipeItemData paramPipeItemData);
  
  long networkCheckIntervalMs();
  
  long lastNetWorkCheckMs();
  
  void setLastNetWorkCheckMs(long paramLong);
  
  ATileEntityPipe getCheckPipe();
  
  boolean canGiveToPipe();
  
  default boolean shouldCheckSideFromPipe() {
    return true;
  }
  
  default void checkNetWork() {
    if (!canGiveToPipe())
      return; 
    long currentMs = System.currentTimeMillis();
    if (currentMs - lastNetWorkCheckMs() < networkCheckIntervalMs())
      return; 
    setLastNetWorkCheckMs(currentMs);
    ATileEntityPipe pipe = getCheckPipe();
    if (pipe == null)
      return; 
    PipeNetwork network = PipeNetwork.of(this, pipe);
    if (network == null)
      return; 
    if (network.getError() == PipeError.REACHED_MAX_NETWORK_SIZE)
      return; 
    IPipeMachine receiver = network.getReceiver();
    if (receiver == null) {
      network.error(PipeError.NO_RECEIVER);
      return;
    } 
    if (receiver.isFull())
      network.error(PipeError.RECEIVER_FULL); 
  }
  
  default boolean isFull() {
    PipeItemData data = getItemData();
    if (data == null)
      return false; 
    return (data.getCount() >= PipeItemData.MAX_DATA_SIZE);
  }
  
  default void onPipeItemDataChanged() {
    TileEntity tile = getTileEntity();
    if (tile == null)
      return; 
    tile.func_70296_d();
    tile.func_145831_w().func_147471_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    updateOnRadius();
  }
  
  default void decrement() {
    PipeItemData data = getItemData();
    if (data == null)
      return; 
    if (!data.decrement())
      setItemData((PipeItemData)null, false); 
  }
  
  default ATileEntityPipe findClosestPipeByIterating() {
    TileEntity tile = getTileEntity();
    if (tile == null)
      return null; 
    return PipeUtils.findClosestPipeByIterating(tile, new EnumPipeFacing[0]);
  }
  
  default ATileEntityPipe findClosestPipe() {
    EnumPipeFacing facing = getFacing();
    if (facing == null)
      return null; 
    TileEntity tile = getTileEntity();
    if (tile == null)
      return null; 
    TileEntity targetTile = tile.func_145831_w().func_147438_o(tile.field_145851_c + facing
        .getFrontOffsetX(), tile.field_145848_d + facing
        .getFrontOffsetY(), tile.field_145849_e + facing
        .getFrontOffsetZ());
    if (!(targetTile instanceof ATileEntityPipe))
      return null; 
    return (ATileEntityPipe)targetTile;
  }
  
  default boolean increment(PipeItemData data) {
    PipeItemData currentData = getItemData();
    if (currentData == null)
      return false; 
    return currentData.increment(data, data.getCount());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\tile\IPipeMachine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */