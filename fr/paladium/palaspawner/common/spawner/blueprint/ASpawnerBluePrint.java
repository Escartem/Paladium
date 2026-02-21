package fr.paladium.palaspawner.common.spawner.blueprint;

import fr.paladium.blueprint.common.blueprint.ABluePrint;
import fr.paladium.blueprint.common.blueprint.floor.Floor;
import fr.paladium.blueprint.common.blueprint.floor.FloorBlock;
import fr.paladium.blueprint.common.utils.BlockPos;
import fr.paladium.helios.utils.Vec3i;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import fr.paladium.palaspawner.common.spawner.blueprint.output.SpawnerBluePrintOutput;
import fr.paladium.palaspawner.common.tile.Tier;
import fr.paladium.palaspawner.common.tile.TileEntityEmptyMobSpawner;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class ASpawnerBluePrint extends ABluePrint {
  private final Tier structureTier;
  
  public Tier getStructureTier() {
    return this.structureTier;
  }
  
  protected ASpawnerBluePrint(String id, Tier tier) {
    super(id);
    this.structureTier = tier;
  }
  
  public List<SpawnerData> getSpawnersFromController(World world, SpawnerBluePrintOutput output) {
    List<SpawnerData> spawners = new ArrayList<>();
    if (!output.isValid())
      return spawners; 
    int count = 0;
    int structureTierOrdinal = this.structureTier.ordinal();
    Block centerBlock = getCenterBlock(output.getBluePrint().getFloors().get(0));
    if (centerBlock == null)
      return spawners; 
    BlockPos centerBlockPos = output.getPosFromBlock(centerBlock).get(0);
    BlockPos controllerBlockPos = output.getPosFromBlock((Block)SpawnerBlockRegistry.SPAWNER_CONTROLLER).get(0);
    Vec3i controllerVec = new Vec3i(controllerBlockPos.getX(), controllerBlockPos.getY(), controllerBlockPos.getZ());
    Vec3i corner = new Vec3i(centerBlockPos.getX() - 3, centerBlockPos.getY() + 1, centerBlockPos.getZ() - 3);
    for (int y = 0; y < 3; y++) {
      for (int x = 0; x <= 6; x++) {
        for (int z = 0; z <= 6; z++) {
          if (corner.getX() + x != controllerBlockPos.getX() || corner.getZ() + z != controllerBlockPos.getZ()) {
            TileEntity tile = world.func_147438_o(corner.getX() + x, corner.getY() + y, corner.getZ() + z);
            if (tile instanceof TileEntityEmptyMobSpawner) {
              if (count >= this.structureTier.getMaxSpawners())
                return spawners; 
              TileEntityEmptyMobSpawner spawner = (TileEntityEmptyMobSpawner)tile;
              if (spawner.getLinkedController().equals(controllerVec) || spawner.getLinkedController().equals(TileEntityEmptyMobSpawner.NULL) || !checkSpawnerControllerExist(world, spawner)) {
                spawner.setLinkedController(controllerVec);
                count++;
                String entityId = spawner.getEntityId();
                Tier spawnerTier = spawner.getTier();
                int spawnerTierOrdinal = spawnerTier.ordinal();
                if (entityId != null && this.structureTier != Tier.ZERO) {
                  if (spawnerTierOrdinal > structureTierOrdinal)
                    spawnerTier = this.structureTier; 
                  addOrIncrementSpawner(spawners, SpawnerData.of(entityId, spawnerTier));
                } 
              } 
            } 
          } 
        } 
      } 
    } 
    return spawners;
  }
  
  private boolean checkSpawnerControllerExist(World world, TileEntityEmptyMobSpawner spawner) {
    TileEntity checkTile = world.func_147438_o(spawner.getLinkedController().getX(), spawner.getLinkedController().getY(), spawner.getLinkedController().getZ());
    return checkTile instanceof fr.paladium.palaspawner.common.tile.TileEntitySpawnerController;
  }
  
  private Block getCenterBlock(Floor floor) {
    for (FloorBlock pos : floor.getBlocks()) {
      if (pos.getPos().getX() == 0 && pos.getPos().getZ() == 0)
        return pos.getBlock(); 
    } 
    return null;
  }
  
  private void addOrIncrementSpawner(List<SpawnerData> spawners, SpawnerData data) {
    for (SpawnerData spawner : spawners) {
      if (spawner.getTier() == data.getTier() && spawner.getEntityId().equals(data.getEntityId())) {
        spawner.increment();
        return;
      } 
    } 
    spawners.add(data);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawner\blueprint\ASpawnerBluePrint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */