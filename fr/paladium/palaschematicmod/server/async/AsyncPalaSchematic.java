package fr.paladium.palaschematicmod.server.async;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palaforgeutils.lib.java.hash.LongHash;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaschematicmod.common.pojo.data.BlockData;
import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import fr.paladium.palaschematicmod.common.pojo.data.EntityData;
import fr.paladium.palaschematicmod.common.pojo.schematic.Schematic;
import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedBlockData;
import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedSchematic;
import fr.paladium.palaschematicmod.server.manager.PalaSchematicManager;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

public class AsyncPalaSchematic {
  private static final int CHUNK_HEIGHT = 16;
  
  private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();
  
  public static void pasteSchematicAsync(World world, EntityPlayer player, BlockPos origin, Schematic schematic, boolean pasteAir, boolean pasteEntities, boolean pasteData, Map<Map.Entry<String, Byte>, Map.Entry<String, Byte>> replaceMap, long expirationDelay, PasteCallback callback, BiConsumer<BlockData, Block> blockConsumer) {
    EXECUTOR.submit(() -> {
          transform(world, origin, schematic, pasteAir, replaceMap);
          TimedSchematic timedSchematic = new TimedSchematic();
          timedSchematic.setName(schematic.getName());
          timedSchematic.setOriginalBlocks(new ArrayList());
          timedSchematic.setExpiration(System.currentTimeMillis() + expirationDelay);
          int minChunkX = schematic.getMinPoint().getX() >> 4;
          int minChunkZ = schematic.getMinPoint().getZ() >> 4;
          int maxChunkX = schematic.getMaxPoint().getX() >> 4;
          int maxChunkZ = schematic.getMaxPoint().getZ() >> 4;
          int minY = schematic.getMinPoint().getY();
          int maxY = schematic.getMaxPoint().getY();
          FMLServerScheduler.getInstance().add(new Runnable[] { () });
        });
  }
  
  private static void transform(World world, BlockPos origin, Schematic schematic, boolean pasteAir, Map<Map.Entry<String, Byte>, Map.Entry<String, Byte>> replaceMap) {
    Map<Long, List<BlockData>> chunkMap = new HashMap<>();
    for (BlockData blockData : schematic.getBlocks()) {
      int worldX = origin.getX() + blockData.getPos().getX();
      int worldY = origin.getY() + blockData.getPos().getY();
      int worldZ = origin.getZ() + blockData.getPos().getZ();
      blockData.getPos().setX(worldX);
      blockData.getPos().setY(worldY);
      blockData.getPos().setZ(worldZ);
      if (replaceMap != null && !replaceMap.isEmpty()) {
        Map.Entry<String, Byte> replacement = replaceMap.get(new AbstractMap.SimpleEntry<>(blockData.getInfo().getMaterial(), Byte.valueOf(blockData.getInfo().getData())));
        if (replacement != null) {
          blockData.getInfo().setMaterial(replacement.getKey());
          blockData.getInfo().setData(((Byte)replacement.getValue()).byteValue());
        } 
      } 
      if (!pasteAir && "minecraft:air".equalsIgnoreCase(blockData.getInfo().getMaterial()))
        continue; 
      ((List<BlockData>)chunkMap.computeIfAbsent(Long.valueOf(LongHash.toLong(worldX >> 4, worldZ >> 4)), k -> new ArrayList())).add(blockData);
    } 
    schematic.updateMinPoint().updateMaxPoint().updateCenter();
    schematic.setChunkedBlocks(chunkMap);
    schematic.getBlocks().clear();
  }
  
  private static CompletableFuture<SchematicChunkData> getChunkData(World world, Schematic schematic, int xIndex, int yIndex, int zIndex) {
    CompletableFuture<SchematicChunkData> future = new CompletableFuture<>();
    EXECUTOR.submit(() -> {
          int worldX = schematic.getMinPoint().getX() + (xIndex << 4);
          int worldY = schematic.getMinPoint().getY() + yIndex * 16;
          int worldZ = schematic.getMinPoint().getZ() + (zIndex << 4);
          Chunk chunk = world.func_72938_d(worldX, worldZ);
          long chunkKey = LongHash.toLong(chunk.field_76635_g, chunk.field_76647_h);
          if (schematic.getChunkedBlocks() == null) {
            future.complete(new SchematicChunkData(chunk));
            return;
          } 
          List<BlockData> chunkBlocks = (List<BlockData>)schematic.getChunkedBlocks().get(Long.valueOf(chunkKey));
          if (chunkBlocks == null || chunkBlocks.isEmpty()) {
            future.complete(new SchematicChunkData(chunk));
            return;
          } 
          SchematicChunkData schematicChunkData = new SchematicChunkData(chunk);
          for (BlockData blockData : chunkBlocks) {
            if (blockData == null || blockData.getPos() == null)
              continue; 
            if (blockData.getPos().getY() >= worldY && blockData.getPos().getY() < worldY + 16)
              schematicChunkData.add(blockData); 
          } 
          future.complete(schematicChunkData);
        }"AsyncPalaSchematic/Paste-" + schematic
        
        .getName());
    return future;
  }
  
  private static void paste(World world, EntityPlayer player, Schematic schematic, TimedSchematic timedSchematic, long expirationDelay, boolean pasteEntities, boolean pasteData, BlockPos origin, PasteCallback callback, BiConsumer<BlockData, Block> blockConsumer, int minChunkX, int minChunkZ, int maxChunkX, int maxChunkZ, int minY, int maxY, int xIndex, int yIndex, int zIndex) {
    if (xIndex > maxChunkX - minChunkX) {
      if (pasteEntities && !schematic.getEntities().isEmpty())
        for (EntityData entityData : schematic.getEntities()) {
          Entity entity = entityData.getEntity(world);
          if (entity == null)
            continue; 
          entity.func_70107_b(origin.getX() + entityData.getX(), origin.getY() + entityData.getY(), origin.getZ() + entityData.getZ());
          world.func_72838_d(entity);
        }  
      if (expirationDelay > 0L)
        PalaSchematicManager.addTimedSchematic(world, timedSchematic); 
      if (callback != null)
        callback.onPasteComplete(timedSchematic); 
      schematic.getBlocks().clear();
      schematic.getEntities().clear();
      schematic.getChunkedBlocks().clear();
      schematic.setBlocks(null);
      schematic.setEntities(null);
      schematic.setChunkedBlocks(null);
      System.gc();
      return;
    } 
    getChunkData(world, schematic, xIndex, yIndex, zIndex).whenComplete((chunkData, error) -> {
          if (callback != null) {
            int total = (maxChunkX - minChunkX + 1) * (maxChunkZ - minChunkZ + 1) * ((maxY - minY) / 16 + 1);
            int current = xIndex * (maxChunkZ - minChunkZ + 1) * ((maxY - minY) / 16 + 1) + zIndex * ((maxY - minY) / 16 + 1) + yIndex + 1;
            callback.onPasteProgress(timedSchematic, current, total);
          } 
          if (error != null || chunkData == null || chunkData.getBlocks().isEmpty()) {
            if (error != null)
              error.printStackTrace(); 
            if (yIndex < (maxY - minY) / 16) {
              paste(world, player, schematic, timedSchematic, expirationDelay, pasteEntities, pasteData, origin, callback, blockConsumer, minChunkX, minChunkZ, maxChunkX, maxChunkZ, minY, maxY, xIndex, yIndex + 1, zIndex);
            } else if (zIndex < maxChunkZ - minChunkZ) {
              if (chunkData != null)
                schematic.getChunkedBlocks().remove(Long.valueOf(LongHash.toLong(chunkData.chunk.field_76635_g, chunkData.chunk.field_76647_h))); 
              paste(world, player, schematic, timedSchematic, expirationDelay, pasteEntities, pasteData, origin, callback, blockConsumer, minChunkX, minChunkZ, maxChunkX, maxChunkZ, minY, maxY, xIndex, 0, zIndex + 1);
            } else {
              if (chunkData != null)
                schematic.getChunkedBlocks().remove(Long.valueOf(LongHash.toLong(chunkData.chunk.field_76635_g, chunkData.chunk.field_76647_h))); 
              paste(world, player, schematic, timedSchematic, expirationDelay, pasteEntities, pasteData, origin, callback, blockConsumer, minChunkX, minChunkZ, maxChunkX, maxChunkZ, minY, maxY, xIndex + 1, 0, 0);
            } 
            return;
          } 
          PalaSchematicManager.SCHEMATIC_QUEUE.addTask((), ());
        });
  }
  
  public static class SchematicChunkData {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\server\async\AsyncPalaSchematic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */