package fr.paladium.palamod.modules.end.server.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.factions.api.player.IPlayer;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.palamod.events.EntityPortalEnterEvent;
import fr.paladium.palamod.modules.back2future.ModItems;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.modules.end.PEnd;
import fr.paladium.palamod.modules.end.server.config.EndConfig;
import fr.paladium.palamod.modules.end.server.config.MagicConfig;
import fr.paladium.palamod.modules.end.server.manager.EndManager;
import fr.paladium.palamod.modules.end.server.utils.EndState;
import fr.paladium.palamod.modules.ranking.RankingEvents;
import fr.paladium.palamod.util.BlockLocation;
import fr.paladium.ranking.common.data.RankedPlayerData;
import fr.paladium.worldguardbridge.common.manager.WGManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;

public class ServerEventHandler {
  @SubscribeEvent
  public void onTick(TickEvent.ServerTickEvent event) {
    if (event.phase != TickEvent.Phase.END || !EndManager.getInstance().isActive())
      return; 
    EndManager.getInstance().checkCrystals();
  }
  
  @SubscribeEvent
  public void onBreak(BlockEvent.BreakEvent event) {
    if (!(event.getPlayer()).field_71075_bZ.field_75098_d && !(event.block instanceof fr.paladium.palamod.modules.back2future.blocks.ChorusPlant) && WGManager.inst().getRegionListAt(event.world, event.x, event.y, event.z).hasFlag(PEnd.EVENT_END_FLAG))
      event.setCanceled(true); 
  }
  
  @SubscribeEvent
  public void onInteractWithMagicConfig(PlayerInteractEvent e) {
    EntityPlayer player = e.entityPlayer;
    if (!PEnd.getServer().getConfigurators().containsKey(player.func_110124_au()))
      return; 
    MagicConfig config = (MagicConfig)PEnd.getServer().getConfigurators().get(player.func_110124_au());
    if (e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
      BlockLocation pos = new BlockLocation(e.x, e.y, e.z);
      config.add(pos);
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§dEnd§8] §aAjout du point §e" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ()));
      e.setCanceled(true);
    } else if (e.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
      BlockLocation pos = new BlockLocation(e.x, e.y, e.z);
      config.remove(pos);
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§dEnd§8] §cSuppression du point §e" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ()));
      e.setCanceled(true);
    } 
  }
  
  @SubscribeEvent
  public void onPickupChorus(EntityItemPickupEvent e) {
    EntityPlayer player = e.entityPlayer;
    ItemStack item = e.item.func_92059_d();
    if (item == null || item.func_77973_b() != ModItems.chorus_fruit || (EndManager.getInstance().getState() != EndState.DRAGON && EndManager.getInstance().getState() != EndState.EGGHUNT))
      return; 
    int count = item.field_77994_a;
    int value = EndManager.getInstance().increasePoints(player, count);
    double rankedValue = RankedPlayerData.get((Entity)player).getValue("chorus").getValue();
    if (value > rankedValue) {
      RankedPlayerData.get((Entity)player).setValue("chorus", value);
      IPlayer iPlayer = PlayerController.getInstance().getExistingSyncPlayer(player.func_110124_au());
      String factionName = (iPlayer == null) ? "Wilderness" : (iPlayer.hasFaction() ? iPlayer.getFaction().getName() : "Wilderness");
      RankingEvents.pushRankingData(player, factionName, "chorus");
    } 
    player.func_145747_a((IChatComponent)new ChatComponentText("§8[§dEnd§8] §eVous avez §a" + value + " chorus §8[§a+§f" + count + "§8]"));
    if (player instanceof EntityPlayerMP) {
      EntityPlayerMP playerMP = (EntityPlayerMP)player;
      S29PacketSoundEffect packet = new S29PacketSoundEffect("random.pop", player.field_70165_t, player.field_70163_u, player.field_70161_v, 0.2F, ((player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.7F + 1.0F) * 2.0F);
      playerMP.field_71135_a.func_147359_a((Packet)packet);
    } 
    e.item.func_70106_y();
    e.setCanceled(true);
  }
  
  @SubscribeEvent
  public void onPortalEnter(EntityPortalEnterEvent e) {
    e.setCanceled(true);
    if (PEnd.getServer().getConfig() == null || e.entity.field_70170_p.field_72995_K)
      return; 
    if (PEggHunt.status != null && PEggHunt.status.getEggOwner() != null && PEggHunt.data.isActive() && PEggHunt.status.getEggOwner().equalsIgnoreCase(e.entity.func_70005_c_()))
      return; 
    BlockLocation location = new BlockLocation(e.x, e.y, e.z);
    EndConfig config = PEnd.getServer().getConfig();
    if ((EndManager.getInstance().getState() == EndState.DRAGON || EndManager.getInstance().getState() == EndState.EGGHUNT) && config.portal.contains(location)) {
      EndManager.getInstance().teleportToEnd(e.entity);
      return;
    } 
    if (EndManager.getInstance().getState() == EndState.EGGHUNT && config.endportal.contains(location))
      EndManager.getInstance().teleportToWorld(e.entity); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\server\event\ServerEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */