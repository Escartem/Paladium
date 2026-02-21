package fr.paladium.palaschematicmod.server.async;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palaschematicmod.common.pojo.data.BlockData;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

public class SchematicChunkData {
  private final Chunk chunk;
  
  private final List<BlockData> blocks;
  
  public Chunk getChunk() {
    return this.chunk;
  }
  
  public List<BlockData> getBlocks() {
    return this.blocks;
  }
  
  public SchematicChunkData(Chunk chunk) {
    this.chunk = chunk;
    this.blocks = new ArrayList<>();
  }
  
  public SchematicChunkData add(BlockData blockData) {
    this.blocks.add(blockData);
    return this;
  }
  
  public SchematicChunkData paste(World world, EntityPlayer player, boolean pasteData) {
    this.blocks.stream().filter(blockData -> (player == null || canPlace(world, player, blockData))).forEach(blockData -> blockData.apply(world, pasteData));
    return this;
  }
  
  private boolean canPlace(World world, EntityPlayer player, BlockData block) {
    int x = block.getPos().getX();
    int y = block.getPos().getY();
    int z = block.getPos().getZ();
    if (y < 0 || y > 256)
      return false; 
    BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(x, y, z, world, Blocks.field_150350_a, 0, player);
    event.setResult(Event.Result.DENY);
    MinecraftForge.EVENT_BUS.post((Event)event);
    return !event.isCanceled();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\server\async\AsyncPalaSchematic$SchematicChunkData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */