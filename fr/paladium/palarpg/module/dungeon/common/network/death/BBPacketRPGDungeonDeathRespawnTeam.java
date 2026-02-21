package fr.paladium.palarpg.module.dungeon.common.network.death;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonGlobalConfig;
import fr.paladium.palarpg.module.dungeon.server.manager.DungeonManager;
import fr.paladium.palashop.server.pb.PBManager;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class BBPacketRPGDungeonDeathRespawnTeam extends ForgePacket {
  @SideOnly(Side.CLIENT)
  public void processClient() {
    Minecraft.func_71410_x().func_147108_a(null);
    SoundRecord.create("palarpg", "sounds/dungeon/item/respawn.ogg").build(SoundCategory.PLAYERS).play();
    for (int j = 0; j < 20; j++) {
      double offsetX = ((Minecraft.func_71410_x()).field_71439_g.func_70681_au().nextDouble() - 0.5D) * 2.0D;
      double offsetY = (Minecraft.func_71410_x()).field_71439_g.func_70681_au().nextDouble() * 2.0D - 1.0D;
      double offsetZ = ((Minecraft.func_71410_x()).field_71439_g.func_70681_au().nextDouble() - 0.5D) * 2.0D;
      (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_72869_a("happyVillager", (Minecraft.func_71410_x()).field_71439_g.field_70165_t + offsetX, (Minecraft.func_71410_x()).field_71439_g.field_70163_u + offsetY, (Minecraft.func_71410_x()).field_71439_g.field_70161_v + offsetZ, 0.0D, 0.0D, 0.0D);
    } 
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get((EntityPlayer)player);
    if (!optionalDungeonWorld.isPresent()) {
      reply(Boolean.valueOf(false));
      return;
    } 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (dungeonWorld.isRespawning() || !dungeonWorld.getAlivePlayers().isEmpty() || dungeonWorld.getState() != DungeonWorld.DungeonState.STARTED || dungeonWorld.getOnlinePlayers().isEmpty()) {
      reply(Boolean.valueOf(false));
      return;
    } 
    dungeonWorld.setRespawning(true);
    DungeonGlobalConfig config = DungeonManager.getGlobalConfig().join();
    if (ForgeEnv.isIDE()) {
      DungeonRoom room = dungeonWorld.getRoom().get();
      room.reset();
      for (DungeonPlayer dungeonPlayer : dungeonWorld.getPlayers()) {
        dungeonPlayer.getOnlinePlayer().ifPresent(onlinePlayer -> {
              room.teleport(onlinePlayer);
              dungeonPlayer.respawn();
              (new BBPacketRPGDungeonDeathRespawnTeam()).send((EntityPlayerMP)onlinePlayer);
              onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
              onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §d✦ §lVotre équipe a été réanimée !"));
              onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
              onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(" §d⊙ §7Le §7joueur §b" + player.func_70005_c_() + " §7a §7réanimé §7toute §7l'équipe."));
              onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
              onlinePlayer.func_145747_a((IChatComponent)new ChatComponentText(""));
            });
      } 
      dungeonWorld.setRespawning(false);
      reply(Boolean.valueOf(true));
      return;
    } 
    PBManager.buy(UUIDUtils.toString((Entity)player), config.getRespawn().getPrice().getTeam() * dungeonWorld.getOnlinePlayers().size(), "RPG_DUNGEON_RESPAWN_TEAM").thenAccept(result -> {
          if (result.booleanValue()) {
            DungeonRoom room = dungeonWorld.getRoom().get();
            room.reset();
            for (DungeonPlayer dungeonPlayer : dungeonWorld.getPlayers())
              dungeonPlayer.getOnlinePlayer().ifPresent(()); 
          } 
          dungeonWorld.setRespawning(false);
          reply(result);
        }).exceptionally(ex -> {
          reply(Boolean.valueOf(false));
          (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue", "paladium")).send(player);
          ex.printStackTrace();
          return null;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\death\BBPacketRPGDungeonDeathRespawnTeam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */