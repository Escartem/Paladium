package fr.paladium.palaspawner.common.manager;

import fr.paladium.blueprint.common.manager.StructureManager;
import fr.paladium.blueprint.common.utils.BluePrintOutput;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import fr.paladium.palaspawner.common.spawner.blueprint.ASpawnerBluePrint;
import fr.paladium.palaspawner.common.spawner.blueprint.output.SpawnerBluePrintOutput;
import fr.paladium.palaspawner.common.spawner.data.ASpawnerEntityData;
import fr.paladium.palaspawner.common.tile.TileEntitySpawnerController;
import java.util.HashMap;
import java.util.LinkedHashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class SpawnerManager {
  private static SpawnerManager instance;
  
  private final LinkedHashMap<String, ASpawnerBluePrint> bluePrints;
  
  private final HashMap<String, ASpawnerEntityData> spawnerEntities;
  
  public LinkedHashMap<String, ASpawnerBluePrint> getBluePrints() {
    return this.bluePrints;
  }
  
  public HashMap<String, ASpawnerEntityData> getSpawnerEntities() {
    return this.spawnerEntities;
  }
  
  private final HashMap<Class<? extends EntityLiving>, String> spawnerEntitiesClass = new HashMap<>();
  
  public HashMap<Class<? extends EntityLiving>, String> getSpawnerEntitiesClass() {
    return this.spawnerEntitiesClass;
  }
  
  public SpawnerManager() {
    instance = this;
    this.bluePrints = new LinkedHashMap<>();
    this.spawnerEntities = new HashMap<>();
  }
  
  public static SpawnerManager getInstance() {
    if (instance == null)
      instance = new SpawnerManager(); 
    return instance;
  }
  
  public SpawnerBluePrintOutput getBluePrintFromTile(World world, String cachedId, EnumFacing direction, TileEntitySpawnerController tile) {
    if (direction == null)
      return getAndExcludeBluePrint(world, EnumFacing.NORTH, tile); 
    return getAndExcludeBluePrint(world, direction, tile);
  }
  
  public SpawnerBluePrintOutput getAndExcludeBluePrint(World world, EnumFacing direction, TileEntitySpawnerController tile) {
    for (ASpawnerBluePrint bluePrint : this.bluePrints.values()) {
      for (EnumFacing facing : StructureManager.DIRECTIONS) {
        BluePrintOutput output = bluePrint.verifyFromBlockAndCoords(tile.func_145831_w(), (Block)SpawnerBlockRegistry.SPAWNER_CONTROLLER, facing, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
        if (output.isValid()) {
          tile.setLinkedBlueprintId(bluePrint.getId());
          tile.setDirection(facing);
          return SpawnerBluePrintOutput.of(world, bluePrint, output);
        } 
      } 
    } 
    return SpawnerBluePrintOutput.INVALID;
  }
  
  public ASpawnerBluePrint getBluePrint(String id) {
    return this.bluePrints.get(id);
  }
  
  public <T extends ASpawnerBluePrint> T registerBluePrint(Class<T> bluePrint) {
    try {
      ASpawnerBluePrint aSpawnerBluePrint = (ASpawnerBluePrint)bluePrint.newInstance();
      this.bluePrints.put(aSpawnerBluePrint.getId(), aSpawnerBluePrint);
      StructureManager.getInstance().registerBluePrint(bluePrint);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return null;
  }
  
  public <T extends ASpawnerEntityData> T registerSpawnerEntity(Class<T> spawnerEntity) {
    try {
      ASpawnerEntityData aSpawnerEntityData = (ASpawnerEntityData)spawnerEntity.newInstance();
      this.spawnerEntities.put(aSpawnerEntityData.getEntityId(), aSpawnerEntityData);
      this.spawnerEntitiesClass.put(aSpawnerEntityData.getEntityClass(), aSpawnerEntityData.getEntityId());
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to register");
    } 
    return null;
  }
  
  public ASpawnerEntityData getSpawnerData(String entityId) {
    return this.spawnerEntities.get(entityId);
  }
  
  public ASpawnerEntityData getSpawnerData(Class<? extends EntityLiving> entityClass) {
    String entityId = this.spawnerEntitiesClass.get(entityClass);
    if (entityId == null)
      return null; 
    return this.spawnerEntities.get(entityId);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\manager\SpawnerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */