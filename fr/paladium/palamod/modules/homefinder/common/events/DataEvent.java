package fr.paladium.palamod.modules.homefinder.common.events;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.helios.module.title.ModuleTitle;
import fr.paladium.helios.module.title.utils.MinecraftTitle;
import fr.paladium.homemod.server.event.sethome.ASethomeEvent;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palamod.modules.homefinder.common.data.DisconnectionData;
import fr.paladium.palamod.modules.homefinder.common.managers.DisconnectionDataManager;
import fr.paladium.palamod.modules.homefinder.common.utils.HUtils;
import fr.paladium.palamod.modules.homefinder.common.utils.Location;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class DataEvent {
  @SubscribeEvent(priority = EventPriority.HIGH)
  public void onSethome(ASethomeEvent.Pre event) {
    EntityPlayerMP player = event.getPlayerMP();
    String homeName = event.getHomeName();
    if (BukkitUtils.hasPermission((Entity)player, "homefinder.bypass"))
      return; 
    if (!HUtils.canPlaceHome(player.field_70170_p, new Location(player.field_70165_t, player.field_70163_u, player.field_70161_v), (EntityPlayer)player)) {
      event.setCancelReason("§8[§6HomeFinder§8]§r §eUne force magique vous empêche de placer un home ici !");
      event.setCanceled(true);
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.HIGH)
  public void onPlayerDisconnect(PlayerEvent.PlayerLoggedOutEvent event) {
    EntityPlayer player = event.player;
    World world = player.field_70170_p;
    if (world.field_72995_K)
      return; 
    Location location = new Location(player.field_70165_t, player.field_70163_u, player.field_70161_v);
    DisconnectionDataManager.getInstance().addDisconnection(player, !HUtils.canPlaceHome(world, location, player));
  }
  
  @SubscribeEvent(priority = EventPriority.HIGH)
  public void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
    EntityPlayer player = event.player;
    World world = player.field_70170_p;
    if (world.field_72995_K)
      return; 
    Location location = new Location(player.field_70165_t, player.field_70163_u, player.field_70161_v);
    DisconnectionData data = DisconnectionDataManager.getInstance().getDisconnection(player.getDisplayName(), player.func_110124_au(), world, location);
    if (data == null || BukkitUtils.hasPermission((Entity)player, "homefinder.bypass"))
      return; 
    if (data.isCancelled() && data.teleportAtSpawn(player)) {
      HUtils.sendMessage(player, new String[] { "§8[§6HomeFinder§8]§r §eUn home remover vous a empêché(e) de vous reconnecter à l'endroit de votre déconnexion" });
      MinecraftTitle title = new MinecraftTitle("§8[§6HomeFinder§8]", "§8[§6HomeFinder§8]§r §eUn home remover vous a empêché(e) de vous reconnecter à l'endroit de votre déconnexion", 500L, TimeUnit.SECONDS.toMillis(4L), 500L);
      if (player instanceof EntityPlayerMP)
        ModuleTitle.getInstance().sendTitle(title, (EntityPlayerMP)player); 
    } 
    DisconnectionDataManager.getInstance().removeDisconnection(player.getDisplayName(), player.func_110124_au(), world, location);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\common\events\DataEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */