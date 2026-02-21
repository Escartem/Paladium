package fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl.boost;

import com.eliotlash.molang.MolangException;
import com.google.gson.JsonElement;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palarpg.common.extended.RPGPlayer;
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
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;

public class BoostFountainDungeonRoomBehavior extends DungeonRoomBehavior {
  private final DungeonRoomDataBoostFountainConfig config;
  
  public BoostFountainDungeonRoomBehavior(@NonNull DungeonRoom room) {
    super(room);
    if (room == null)
      throw new NullPointerException("room is marked non-null but is null"); 
    this.config = (DungeonRoomDataBoostFountainConfig)room.getConfig().getData("fountain", DungeonRoomDataBoostFountainConfig.class);
  }
  
  public void onPaste(@NonNull Block block, @NonNull BlockPos position) {
    if (block == null)
      throw new NullPointerException("block is marked non-null but is null"); 
    if (position == null)
      throw new NullPointerException("position is marked non-null but is null"); 
  }
  
  public void onLoad() {}
  
  public void onReset() {}
  
  public void onRemove() {}
  
  public void onJoin() {
    if (!getRoom().isFinished())
      getRoom().setFinished(true); 
  }
  
  public void onLeave() {}
  
  @SubscribeEvent
  public void onTick(TickEvent.PlayerTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    EntityPlayer player = event.player;
    if (!getRoom().isActive() || !player.func_70090_H() || this.config == null || this.config.boosts == null || this.config.boosts.length == 0)
      return; 
    RPGStatPlayerData playerData = (RPGStatPlayerData)RPGPlayer.getData((Entity)player, "stats");
    if (playerData == null)
      return; 
    for (DungeonRoomDataBoostFountainConfig.DungeonRoomDataBoostFountainConfigElement boost : this.config.boosts) {
      if (boost != null && boost.stats != null && boost.value != null && boost.duration != null && boost.type != null)
        try {
          double value = getRoom().getWorld().getParser().parseJson(boost.value).get();
          int duration = (int)Math.ceil(getRoom().getWorld().getParser().parseJson(boost.duration).get() / 1000.0D * 20.0D);
          playerData.getCapabilityByName(boost.stats).addMutator(TimedStatCapabilityMutator.create().setTick(duration).setId("DUNGEON_BOOST_FOUNTAIN").setValue(Double.valueOf(value)).setMutationType(boost.type).setSaved(false));
          playerData.applyAndSync();
          if (player.field_70173_aa % 5 == 0)
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
        capability.removeMutator(mutator -> (mutator.getId() != null && mutator.getId().startsWith("DUNGEON_BOOST_FOUNTAIN"))); 
      playerData.applyAndSync();
    } 
  }
  
  public class DungeonRoomDataBoostFountainConfig {
    private final DungeonRoomDataBoostFountainConfigElement[] boosts;
    
    public DungeonRoomDataBoostFountainConfig(DungeonRoomDataBoostFountainConfigElement[] boosts) {
      this.boosts = boosts;
    }
    
    public class DungeonRoomDataBoostFountainConfigElement {
      private final EnumStats stats;
      
      private final JsonElement value;
      
      private final JsonElement duration;
      
      private final StatMutationType type;
      
      public DungeonRoomDataBoostFountainConfigElement(EnumStats stats, JsonElement value, JsonElement duration, StatMutationType type) {
        this.stats = stats;
        this.value = value;
        this.duration = duration;
        this.type = type;
      }
    }
  }
  
  public class DungeonRoomDataBoostFountainConfigElement {
    private final EnumStats stats;
    
    private final JsonElement value;
    
    private final JsonElement duration;
    
    private final StatMutationType type;
    
    public DungeonRoomDataBoostFountainConfigElement(EnumStats stats, JsonElement value, JsonElement duration, StatMutationType type) {
      this.stats = stats;
      this.value = value;
      this.duration = duration;
      this.type = type;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\room\behavior\impl\boost\BoostFountainDungeonRoomBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */