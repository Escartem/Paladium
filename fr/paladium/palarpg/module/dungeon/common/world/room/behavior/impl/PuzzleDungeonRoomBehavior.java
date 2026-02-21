package fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palaforgeutils.lib.location.BlockLocation;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palarpg.common.api.BlocksRegister;
import fr.paladium.palarpg.common.network.SCPacketRPGPlaySound;
import fr.paladium.palarpg.module.dungeon.client.ui.transition.UIDungeonTransitionOverlay;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.world.room.behavior.DungeonRoomBehavior;
import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;

public class PuzzleDungeonRoomBehavior extends DungeonRoomBehavior {
  private final List<DoubleLocation> resetLocations;
  
  private final List<BlockLocation> finishLocations;
  
  private boolean resetting;
  
  private boolean teleporting;
  
  public PuzzleDungeonRoomBehavior(@NonNull DungeonRoom room) {
    super(room);
    if (room == null)
      throw new NullPointerException("room is marked non-null but is null"); 
    this.resetLocations = new ArrayList<>();
    this.finishLocations = new ArrayList<>();
  }
  
  public void onPaste(@NonNull Block block, @NonNull BlockPos position) {
    if (block == null)
      throw new NullPointerException("block is marked non-null but is null"); 
    if (position == null)
      throw new NullPointerException("position is marked non-null but is null"); 
    if (block == BlocksRegister.DUNGEON_ROOM_PUZZLE_PLAYER_TRIGGER) {
      BlockLocation blockLocation = new BlockLocation(getRoom().getWorld().getWorld(), position.getX(), position.getY(), position.getZ());
      this.finishLocations.add(blockLocation);
      blockLocation.setBlockToAir();
    } 
  }
  
  public void onLoad() {
    this.resetLocations.clear();
    this.resetLocations.addAll(new ArrayList<>(getRoom().getSpawnLocations()));
  }
  
  public void onReset() {
    this.teleporting = true;
    DungeonRoom room = getRoom();
    FMLServerScheduler.getInstance().add(new Runnable[] { () -> {
            room.cut();
            room.paste(room.getX(), room.getZ()).whenComplete(());
          } });
  }
  
  public void onRemove() {}
  
  public void onJoin() {}
  
  public void onLeave() {}
  
  @SubscribeEvent
  public void onTick(TickEvent.ServerTickEvent event) {
    DungeonRoom room = getRoom();
    List<EntityPlayer> players = room.getWorld().getAlivePlayers();
    if (room.isFinished() || event.phase != TickEvent.Phase.END || players.isEmpty())
      return; 
    if (this.teleporting) {
      int index = 0;
      for (EntityPlayer player : players) {
        ((DoubleLocation)this.resetLocations.get(index % this.resetLocations.size())).teleportServer(player);
        index++;
      } 
      return;
    } 
    label28: for (EntityPlayer player : players) {
      int playerX = MathHelper.func_76128_c(player.field_70165_t);
      int playerY = MathHelper.func_76128_c(player.field_70163_u);
      int playerZ = MathHelper.func_76128_c(player.field_70161_v);
      for (BlockLocation finishLocation : this.finishLocations) {
        if (playerX == finishLocation.getX() && playerY == finishLocation.getY() && playerZ == finishLocation.getZ()) {
          room.setFinished(true);
          break label28;
        } 
      } 
    } 
  }
  
  @SubscribeEvent
  public void onFinish(DungeonRoom.DungeonRoomFinishEvent.Pre event) {
    if (event.isFinished() && this.resetting)
      event.setCanceled(true); 
  }
  
  public void reset() {
    DungeonRoom room = getRoom();
    if (room == null || room.isFinished() || !room.isActive() || this.resetting)
      return; 
    if (MinecraftForge.EVENT_BUS.post(new DungeonPuzzleRoomResetEvent.Pre(room)))
      return; 
    this.resetting = true;
    DungeonWorld world = room.getWorld();
    List<EntityPlayer> players = world.getOnlinePlayers();
    for (EntityPlayer player : players) {
      ZUIServer.open(UIDungeonTransitionOverlay.class, (EntityPlayerMP)player, new Object[] { Boolean.valueOf(true) });
    } 
    (new Thread(() -> {
          try {
            Thread.sleep(650L);
          } catch (InterruptedException interruptedException) {}
          this.teleporting = true;
          FMLServerScheduler.getInstance().add(new Runnable[] { () }, );
        }"DungeonRoom-" + room.getConfig().getId() + "/Reset")).start();
  }
  
  public static class DungeonPuzzleRoomResetEvent extends Event {
    private final DungeonRoom room;
    
    public DungeonPuzzleRoomResetEvent(DungeonRoom room) {
      this.room = room;
    }
    
    public DungeonRoom getRoom() {
      return this.room;
    }
    
    @Cancelable
    public static class Pre extends DungeonPuzzleRoomResetEvent {
      public Pre(DungeonRoom room) {
        super(room);
      }
    }
    
    public static class Post extends DungeonPuzzleRoomResetEvent {
      public Post(DungeonRoom room) {
        super(room);
      }
    }
  }
  
  @Cancelable
  public static class Pre extends DungeonPuzzleRoomResetEvent {
    public Pre(DungeonRoom room) {
      super(room);
    }
  }
  
  public static class Post extends DungeonPuzzleRoomResetEvent {
    public Post(DungeonRoom room) {
      super(room);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\room\behavior\impl\PuzzleDungeonRoomBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */