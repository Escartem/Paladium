package fr.paladium.palaforgeutils.lib.localdata.event;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.localdata.storage.LocalDataStorageHandler;
import fr.paladium.palaforgeutils.lib.localdata.world.LocalWorldData;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.ChunkEvent;

public class LocalDataEventHandler {
  @SubscribeEvent
  public void onLoadChunk(ChunkDataEvent.Load e) {
    LocalDataStorageHandler.load(e.getChunk(), e.getData());
  }
  
  @SubscribeEvent
  public void onSaveChunk(ChunkDataEvent.Save e) {
    LocalDataStorageHandler.save(e.getChunk(), e.getData());
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onBreak(BlockEvent.BreakEvent e) {
    if (e.getResult() == Event.Result.DENY || e.block == Blocks.field_150350_a)
      return; 
    LocalWorldData worldData = LocalWorldData.get(e.world);
    worldData.removeData(e.x, e.y, e.z);
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onUnload(ChunkEvent.Unload e) {
    LocalWorldData worldData = LocalWorldData.get(e.world);
    worldData.unload(e.getChunk());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\localdata\event\LocalDataEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */