package fr.paladium.palawither.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.factions.api.events.FactionClaimEvent;
import fr.paladium.factions.api.faction.IFactionPlayer;
import fr.paladium.palawither.common.entity.targetable.EntitySupremeWither;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;

public class ServerFactionClaimListener {
  @SubscribeEvent
  public void onClaim(FactionClaimEvent event) {
    int chunkX = event.getClaim().getX();
    int chunkZ = event.getClaim().getZ();
    for (WorldServer worldServer : (MinecraftServer.func_71276_C()).field_71305_c) {
      for (int ox = -1; ox <= 1; ox++) {
        for (int oz = -1; oz <= 1; oz++) {
          Chunk chunk = worldServer.func_72964_e(chunkX + ox, chunkZ + oz);
          if (chunk != null && chunk.field_76636_d) {
            for (Object tileEntity : chunk.field_150816_i.values()) {
              if (tileEntity instanceof fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle) {
                event.setCanceled(true);
                event.setCancelReason("");
                if (event.getInitiator() instanceof IFactionPlayer) {
                  IFactionPlayer factionPlayer = (IFactionPlayer)event.getInitiator();
                  if (ox == 0 && oz == 0) {
                    factionPlayer.getPlayer().sendMessage("§8[§6Paladium§8] §cVous ne pouvez pas réclamer un territoire contenant une Réceptacle de Wither.");
                  } else {
                    factionPlayer.getPlayer().sendMessage("§8[§6Paladium§8] §cVous ne pouvez pas réclamer un territoire contenant une Réceptacle de Wither dans les environs.");
                  } 
                } 
                return;
              } 
            } 
            for (Object entity : ((World)worldServer).field_72996_f) {
              if (entity instanceof EntitySupremeWither) {
                int entityChunkX = (int)Math.floor(((EntitySupremeWither)entity).field_70165_t / 16.0D);
                int entityChunkZ = (int)Math.floor(((EntitySupremeWither)entity).field_70161_v / 16.0D);
                if (entityChunkX == chunkX + ox && entityChunkZ == chunkZ + oz) {
                  event.setCanceled(true);
                  event.setCancelReason("");
                  if (event.getInitiator() instanceof IFactionPlayer) {
                    IFactionPlayer factionPlayer = (IFactionPlayer)event.getInitiator();
                    if (ox == 0 && oz == 0) {
                      factionPlayer.getPlayer().sendMessage("§8[§6Paladium§8] §cVous ne pouvez pas réclamer un territoire contenant un Wither Suprême.");
                    } else {
                      factionPlayer.getPlayer().sendMessage("§8[§6Paladium§8] §cVous ne pouvez pas réclamer un territoire contenant un Wither Suprême dans les environs.");
                    } 
                  } 
                  return;
                } 
              } 
            } 
          } 
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\server\listener\ServerFactionClaimListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */