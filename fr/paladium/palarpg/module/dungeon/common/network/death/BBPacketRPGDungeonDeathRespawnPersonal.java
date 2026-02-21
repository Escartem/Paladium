package fr.paladium.palarpg.module.dungeon.common.network.death;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonGlobalConfig;
import fr.paladium.palarpg.module.dungeon.server.manager.DungeonManager;
import fr.paladium.palashop.server.pb.PBManager;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class BBPacketRPGDungeonDeathRespawnPersonal extends ForgePacket {
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get((EntityPlayer)player);
    if (!optionalDungeonWorld.isPresent()) {
      reply(Boolean.valueOf(false));
      return;
    } 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    Optional<DungeonPlayer> optionalDungeonPlayer = dungeonWorld.getPlayer(UUIDUtils.toString((Entity)player));
    if (!optionalDungeonPlayer.isPresent()) {
      reply(Boolean.valueOf(false));
      return;
    } 
    DungeonPlayer dungeonPlayer = optionalDungeonPlayer.get();
    if (dungeonPlayer.isAlive() || dungeonPlayer.getRemainingRespawns() <= 0) {
      reply(Boolean.valueOf(false));
      return;
    } 
    DungeonGlobalConfig config = DungeonManager.getGlobalConfig().join();
    if (ForgeEnv.isIDE()) {
      reply(Boolean.valueOf(true));
      ((DungeonRoom)dungeonWorld.getRoom().get()).teleport((EntityPlayer)player);
      dungeonPlayer.setRemainingRespawns(dungeonPlayer.getRemainingRespawns() - 1);
      dungeonPlayer.respawn();
      dungeonWorld.getOnlinePlayers().forEach(onlinePlayer -> {
            onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
            onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §a✔ §a§lLe §a§ljoueur §2§l" + player.func_70005_c_() + " §a§lest §a§lrevenu §a§là §a§lla §a§lvie."));
            onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
          });
      return;
    } 
    PBManager.buy(UUIDUtils.toString((Entity)player), config.getRespawn().getPrice().getPersonal(), "RPG_DUNGEON_RESPAWN_PERSONAL").thenAccept(result -> {
          if (result.booleanValue()) {
            ((DungeonRoom)dungeonWorld.getRoom().get()).teleport((EntityPlayer)player);
            dungeonPlayer.setRemainingRespawns(dungeonPlayer.getRemainingRespawns() - 1);
            dungeonPlayer.respawn();
            dungeonWorld.getOnlinePlayers().forEach(());
          } 
          reply(result);
        }).exceptionally(ex -> {
          reply(Boolean.valueOf(false));
          (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue", "paladium")).send(player);
          ex.printStackTrace();
          return null;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\death\BBPacketRPGDungeonDeathRespawnPersonal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */