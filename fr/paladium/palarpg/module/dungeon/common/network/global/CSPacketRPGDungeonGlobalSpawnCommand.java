package fr.paladium.palarpg.module.dungeon.common.network.global;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class CSPacketRPGDungeonGlobalSpawnCommand extends ForgePacket {
  private static final Cache<String, DoubleLocation> TELEPORT_CACHE = CacheBuilder.newBuilder().expireAfterWrite(5L, TimeUnit.SECONDS).build();
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get((EntityPlayer)player);
    if (!optionalDungeonWorld.isPresent())
      return; 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    Optional<DungeonRoom> optionalRoom = dungeonWorld.getRoom();
    if (optionalRoom.isPresent() && (dungeonWorld.getState() == DungeonWorld.DungeonState.STARTING || dungeonWorld.getState() == DungeonWorld.DungeonState.STARTED)) {
      String uuid = UUIDUtils.toString((Entity)player);
      if (TELEPORT_CACHE.getIfPresent(uuid) != null) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cUne §ctéléportation §cest §cdéjà §cen §ccours, §cveuillez §cpatienter."));
        return;
      } 
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §eTéléportation §edans §e5 §esecondes, §ene §ebougez §epas..."));
      DoubleLocation previousLocation = new DoubleLocation((Entity)player);
      TELEPORT_CACHE.put(uuid, previousLocation);
      (new Thread(() -> {
            while (TELEPORT_CACHE.getIfPresent(uuid) != null) {
              try {
                Thread.sleep(100L);
              } catch (InterruptedException interruptedException) {}
            } 
            DoubleLocation currentLocation = new DoubleLocation((Entity)player);
            if (previousLocation.distance(currentLocation) > 1.0D) {
              player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cTéléportation §cannulée §ccar §cvous §cavez §cbougé."));
            } else {
              ((DungeonRoom)optionalRoom.get()).teleport((EntityPlayer)player);
              player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §eVous §eavez §eété §etéléporté §eà §el'entrée §ede §ela §esalle §eactuelle."));
            } 
          })).start();
    } else {
      dungeonWorld.teleport(player);
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §eVous §eavez §eété §etéléporté §eau §espawn."));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\global\CSPacketRPGDungeonGlobalSpawnCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */