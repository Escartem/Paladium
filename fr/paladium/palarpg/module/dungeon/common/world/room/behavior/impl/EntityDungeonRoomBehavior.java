package fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl;

import com.eliotlash.mclib.math.Variable;
import com.eliotlash.molang.MolangParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palaforgeutils.lib.location.BlockLocation;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palarpg.common.api.BlocksRegister;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.world.room.behavior.DungeonRoomBehavior;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelConfig;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelEntitiesConfig;
import fr.paladium.palarpg.module.dungeon.server.config.room.DungeonRoomConfig;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.RPGEntityLoader;
import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EntityDungeonRoomBehavior extends DungeonRoomBehavior {
  private final Set<UUID> entities;
  
  private final List<DoubleLocation> mobLocations;
  
  private boolean spawned;
  
  public Set<UUID> getEntities() {
    return this.entities;
  }
  
  public List<DoubleLocation> getMobLocations() {
    return this.mobLocations;
  }
  
  public boolean isSpawned() {
    return this.spawned;
  }
  
  public EntityDungeonRoomBehavior(@NonNull DungeonRoom room) {
    super(room);
    if (room == null)
      throw new NullPointerException("room is marked non-null but is null"); 
    this.entities = new HashSet<>();
    this.mobLocations = new ArrayList<>();
  }
  
  public void onPaste(@NonNull Block block, @NonNull BlockPos position) {
    if (block == null)
      throw new NullPointerException("block is marked non-null but is null"); 
    if (position == null)
      throw new NullPointerException("position is marked non-null but is null"); 
    if (block == BlocksRegister.DUNGEON_ROOM_ENTITY) {
      BlockLocation blockLocation = new BlockLocation(getRoom().getWorld().getWorld(), position.getX(), position.getY(), position.getZ());
      TileEntityDungeonRoom tileEntity = (TileEntityDungeonRoom)blockLocation.getTileEntity();
      this.mobLocations.add(new DoubleLocation(blockLocation.getX() + 0.5D, blockLocation.getY(), blockLocation.getZ() + 0.5D, (tileEntity == null) ? 0.0F : tileEntity.getRotation(), 0.0F));
      blockLocation.setBlockToAir();
    } 
  }
  
  public void onLoad() {}
  
  public void onReset() {
    World world = getRoom().getWorld().getWorld();
    for (Object loadedEntityObj : world.field_72996_f) {
      Entity loadedEntity = (Entity)loadedEntityObj;
      if (this.entities.remove(loadedEntity.func_110124_au()))
        world.func_72900_e(loadedEntity); 
    } 
    this.spawned = false;
    this.entities.clear();
  }
  
  public void onRemove() {
    World world = getRoom().getWorld().getWorld();
    for (Object loadedEntityObj : world.field_72996_f) {
      Entity loadedEntity = (Entity)loadedEntityObj;
      if (this.entities.remove(loadedEntity.func_110124_au()))
        world.func_72900_e(loadedEntity); 
    } 
  }
  
  public void onJoin() {
    if (this.spawned)
      return; 
    this.spawned = true;
    this.entities.clear();
    DungeonRoom room = getRoom();
    DungeonWorld world = room.getWorld();
    DungeonLevelConfig level = room.getLevel();
    DungeonRoomConfig config = room.getConfig();
    MolangParser parser = room.getWorld().getParser();
    if (this.mobLocations.isEmpty()) {
      room.setFinished(true);
      return;
    } 
    DungeonRoomDataEntityConfig entityConfig = (DungeonRoomDataEntityConfig)room.getConfig().getData("entity", DungeonRoomDataEntityConfig.class);
    if (entityConfig == null) {
      room.setFinished(true);
      return;
    } 
    (new Thread(() -> {
          try {
            Thread.sleep((room.getIndex() == 0) ? 2000L : 1000L);
          } catch (Exception exception) {}
          Collections.shuffle(this.mobLocations);
          FMLServerScheduler.getInstance().add(new Runnable[] { () }, );
        }"ClassicDungeonRoomBehavior/" + room.getConfig().getId())).start();
  }
  
  public void onLeave() {}
  
  @SubscribeEvent
  public void onEntitySpawn(EntityJoinWorldEvent event) {
    if (!this.spawned || getRoom().isFinished() || !getRoom().isActive() || !(event.entity instanceof fr.paladium.palarpg.module.entity.common.entity.IRPGEntity) || event.entity.field_70170_p != getRoom().getWorld().getWorld())
      return; 
    this.entities.add(event.entity.func_110124_au());
  }
  
  @SubscribeEvent
  public void onEntityDeath(LivingDeathEvent event) {
    if (!this.spawned || getRoom().isFinished() || !getRoom().isActive() || !this.entities.remove(event.entityLiving.func_110124_au()))
      return; 
    if (this.entities.isEmpty())
      getRoom().setFinished(true); 
  }
  
  @SubscribeEvent
  public void onServerTick(TickEvent.ServerTickEvent event) {
    if (event.phase != TickEvent.Phase.END || !this.spawned || this.entities.isEmpty() || getRoom().isFinished() || !getRoom().isActive() || System.currentTimeMillis() - getRoom().getStartTime() < 30000L)
      return; 
    Iterator<UUID> iterator = this.entities.iterator();
    while (iterator.hasNext()) {
      UUID entityId = iterator.next();
      boolean found = false;
      World world = getRoom().getWorld().getWorld();
      for (Object loadedEntityObj : world.field_72996_f) {
        Entity loadedEntity = (Entity)loadedEntityObj;
        if (!entityId.equals(loadedEntity.func_110124_au()) || !loadedEntity.func_70089_S() || (
          loadedEntity instanceof EntityLivingBase && ((EntityLivingBase)loadedEntity).func_110143_aJ() <= 0.0F))
          continue; 
        found = true;
      } 
      if (!found)
        iterator.remove(); 
    } 
    if (this.entities.isEmpty())
      getRoom().setFinished(true); 
  }
  
  @SubscribeEvent
  public void onFinish(DungeonRoom.DungeonRoomFinishEvent.Post event) {
    if (event.getRoom() != getRoom() || !this.spawned || this.entities.isEmpty())
      return; 
    World world = getRoom().getWorld().getWorld();
    for (Object loadedEntityObj : world.field_72996_f) {
      Entity loadedEntity = (Entity)loadedEntityObj;
      if (this.entities.remove(loadedEntity.func_110124_au()))
        world.func_72900_e(loadedEntity); 
    } 
  }
  
  public class DungeonRoomDataEntityConfig {
    @SerializedName("entity_count")
    private final JsonElement entityCount;
    
    private final List<DungeonRoomDataEntityElementConfig> entities;
    
    public DungeonRoomDataEntityConfig(JsonElement entityCount, List<DungeonRoomDataEntityElementConfig> entities) {
      this.entityCount = entityCount;
      this.entities = entities;
    }
    
    public JsonElement getEntityCount() {
      return this.entityCount;
    }
    
    public List<DungeonRoomDataEntityElementConfig> getEntities() {
      return this.entities;
    }
    
    public class DungeonRoomDataEntityElementConfig {
      private final String entity;
      
      private final JsonElement weight;
      
      private final JsonElement minCount;
      
      private final JsonElement maxCount;
      
      public DungeonRoomDataEntityElementConfig(String entity, JsonElement weight, JsonElement minCount, JsonElement maxCount) {
        this.entity = entity;
        this.weight = weight;
        this.minCount = minCount;
        this.maxCount = maxCount;
      }
      
      public String getEntity() {
        return this.entity;
      }
      
      public JsonElement getWeight() {
        return this.weight;
      }
      
      public JsonElement getMinCount() {
        return this.minCount;
      }
      
      public JsonElement getMaxCount() {
        return this.maxCount;
      }
    }
  }
  
  public class DungeonRoomDataEntityElementConfig {
    private final String entity;
    
    private final JsonElement weight;
    
    private final JsonElement minCount;
    
    private final JsonElement maxCount;
    
    public DungeonRoomDataEntityElementConfig(String entity, JsonElement weight, JsonElement minCount, JsonElement maxCount) {
      this.entity = entity;
      this.weight = weight;
      this.minCount = minCount;
      this.maxCount = maxCount;
    }
    
    public String getEntity() {
      return this.entity;
    }
    
    public JsonElement getWeight() {
      return this.weight;
    }
    
    public JsonElement getMinCount() {
      return this.minCount;
    }
    
    public JsonElement getMaxCount() {
      return this.maxCount;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\room\behavior\impl\EntityDungeonRoomBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */