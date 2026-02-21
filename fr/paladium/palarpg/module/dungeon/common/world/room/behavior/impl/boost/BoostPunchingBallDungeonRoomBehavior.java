package fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl.boost;

import com.eliotlash.molang.MolangException;
import com.google.gson.JsonElement;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palaforgeutils.lib.location.BlockLocation;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.common.api.BlocksRegister;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.entity.room.boost.EntityDungeonPunchingBall;
import fr.paladium.palarpg.module.dungeon.common.event.DungeonWorldUpdateEvent;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.world.room.behavior.DungeonRoomBehavior;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.AStatCapability;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatCapabilityMutator;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatMutationType;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.TimedStatCapabilityMutator;
import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import java.util.Random;
import java.util.UUID;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BoostPunchingBallDungeonRoomBehavior extends DungeonRoomBehavior {
  private final DungeonRoomDataBoostPunchingBallConfig config;
  
  private UUID entity;
  
  private DoubleLocation location;
  
  public BoostPunchingBallDungeonRoomBehavior(@NonNull DungeonRoom room) {
    super(room);
    if (room == null)
      throw new NullPointerException("room is marked non-null but is null"); 
    this.config = (DungeonRoomDataBoostPunchingBallConfig)room.getConfig().getData("punching_ball", DungeonRoomDataBoostPunchingBallConfig.class);
  }
  
  public void onPaste(@NonNull Block block, @NonNull BlockPos position) {
    if (block == null)
      throw new NullPointerException("block is marked non-null but is null"); 
    if (position == null)
      throw new NullPointerException("position is marked non-null but is null"); 
    if (block == BlocksRegister.DUNGEON_ROOM_ENTITY) {
      BlockLocation blockLocation = new BlockLocation(getRoom().getWorld().getWorld(), position.getX(), position.getY(), position.getZ());
      TileEntityDungeonRoom tileEntity = (TileEntityDungeonRoom)blockLocation.getTileEntity();
      this.location = new DoubleLocation(blockLocation.getX() + 0.5D, blockLocation.getY(), blockLocation.getZ() + 0.5D, (tileEntity == null) ? 0.0F : tileEntity.getRotation(), 0.0F);
      blockLocation.setBlockToAir();
    } 
  }
  
  public void onLoad() {}
  
  public void onReset() {}
  
  public void onRemove() {
    if (this.entity == null)
      return; 
    World world = getRoom().getWorld().getWorld();
    for (Object loadedEntityObj : world.field_72996_f) {
      Entity loadedEntity = (Entity)loadedEntityObj;
      if (this.entity.equals(loadedEntity.func_110124_au())) {
        world.func_72900_e(loadedEntity);
        break;
      } 
    } 
  }
  
  public void onJoin() {
    if (this.entity != null)
      return; 
    if (this.location == null) {
      getRoom().setFinished(true);
      return;
    } 
    EntityDungeonPunchingBall punchingBall = new EntityDungeonPunchingBall(getRoom().getWorld().getWorld());
    punchingBall.func_70080_a(this.location.getX(), this.location.getY(), this.location.getZ(), this.location.getYaw(), this.location.getPitch());
    getRoom().getWorld().getWorld().func_72838_d((Entity)punchingBall);
    this.entity = punchingBall.func_110124_au();
  }
  
  public void onLeave() {}
  
  @SubscribeEvent
  public void onTick(TickEvent.ServerTickEvent event) {
    if (this.entity == null || getRoom().isFinished() || !getRoom().isActive())
      return; 
    boolean found = false;
    World world = getRoom().getWorld().getWorld();
    for (Object loadedEntityObj : world.field_72996_f) {
      Entity loadedEntity = (Entity)loadedEntityObj;
      if (this.entity.equals(loadedEntity.func_110124_au())) {
        found = true;
        break;
      } 
    } 
    if (found)
      return; 
    getRoom().setFinished(true);
    if (this.config.boosts == null || this.config.boosts.length == 0)
      return; 
    DungeonRoomDataBoostPunchingBallConfig.DungeonRoomDataBoostPunchingBallConfigElement boost = this.config.boosts[(new Random()).nextInt(this.config.boosts.length)];
    if (boost == null || boost.stats == null || boost.value == null || boost.type == null)
      return; 
    getRoom().getWorld().getWorld().func_72908_a(this.location.getX(), this.location.getY(), this.location.getZ(), "random.levelup", 1.0F, 1.0F);
    for (EntityPlayer player : getRoom().getWorld().getAlivePlayers()) {
      RPGStatPlayerData playerData = (RPGStatPlayerData)RPGPlayer.getData((Entity)player, "stats");
      if (playerData == null)
        return; 
      try {
        double value = getRoom().getWorld().getParser().parseJson(boost.value).get();
        playerData.getCapabilityByName(boost.stats).addMutator(TimedStatCapabilityMutator.create().setTick(2147483647).setId("DUNGEON_BOOST_PUNCHING_BALL_" + UUIDUtils.randomStringUUID()).setValue(Double.valueOf(value)).setMutationType(boost.type).setSaved(false));
        playerData.applyAndSync();
        for (int j = 0; j < 20; j++) {
          double offsetX = (player.func_70681_au().nextDouble() - 0.5D) * 2.0D;
          double offsetY = player.func_70681_au().nextDouble() * 2.0D - 1.0D;
          double offsetZ = (player.func_70681_au().nextDouble() - 0.5D) * 2.0D;
          ((WorldServer)player.field_70170_p).func_147487_a("happyVillager", player.field_70165_t + offsetX, player.field_70163_u + offsetY, player.field_70161_v + offsetZ, 0, 0.0D, 0.0D, 0.0D, 0.0D);
        } 
      } catch (MolangException e) {
        e.printStackTrace();
      } 
    } 
  }
  
  @SubscribeEvent
  public void onDungeonWorldUpdate(DungeonWorldUpdateEvent.Post event) {
    if (event.getWorld().getUniqueId() != getRoom().getWorld().getUniqueId() || (event.getWorld().getState() != DungeonWorld.DungeonState.STARTING && event.getWorld().getState() != DungeonWorld.DungeonState.FINISHED))
      return; 
    for (EntityPlayer player : event.getWorld().getOnlinePlayers()) {
      RPGStatPlayerData playerData = (RPGStatPlayerData)RPGPlayer.getData((Entity)player, "stats");
      if (playerData == null)
        return; 
      for (AStatCapability<?> capability : (Iterable<AStatCapability<?>>)playerData.getCapabilities())
        capability.removeMutator(mutator -> (mutator.getId() != null && mutator.getId().startsWith("DUNGEON_BOOST_PUNCHING_BALL"))); 
      playerData.applyAndSync();
    } 
  }
  
  public class DungeonRoomDataBoostPunchingBallConfig {
    private final DungeonRoomDataBoostPunchingBallConfigElement[] boosts;
    
    public DungeonRoomDataBoostPunchingBallConfig(DungeonRoomDataBoostPunchingBallConfigElement[] boosts) {
      this.boosts = boosts;
    }
    
    public class DungeonRoomDataBoostPunchingBallConfigElement {
      private final EnumStats stats;
      
      private final JsonElement value;
      
      private final StatMutationType type;
      
      public DungeonRoomDataBoostPunchingBallConfigElement(EnumStats stats, JsonElement value, StatMutationType type) {
        this.stats = stats;
        this.value = value;
        this.type = type;
      }
    }
  }
  
  public class DungeonRoomDataBoostPunchingBallConfigElement {
    private final EnumStats stats;
    
    private final JsonElement value;
    
    private final StatMutationType type;
    
    public DungeonRoomDataBoostPunchingBallConfigElement(EnumStats stats, JsonElement value, StatMutationType type) {
      this.stats = stats;
      this.value = value;
      this.type = type;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\room\behavior\impl\boost\BoostPunchingBallDungeonRoomBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */