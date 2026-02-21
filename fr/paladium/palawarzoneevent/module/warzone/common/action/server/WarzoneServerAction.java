package fr.paladium.palawarzoneevent.module.warzone.common.action.server;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.combattag.CombatTag;
import fr.paladium.factions.api.FactionAPI;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palaforgeutils.lib.sidedaction.ServerActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerActionContext;
import fr.paladium.palawarzoneevent.module.capture.CaptureModule;
import fr.paladium.palawarzoneevent.module.capture.common.util.CapturePoint;
import fr.paladium.palawarzoneevent.module.warzone.common.data.CaptureLeaderboardData;
import fr.paladium.palawarzoneevent.module.warzone.common.network.RBPacketGetCaptureHolder;
import fr.paladium.palawarzoneevent.module.warzone.common.network.RBPacketRequestCaptureClassementPage;
import fr.paladium.palawarzoneevent.module.warzone.common.network.RBPacketRequestCurrentWarzonePhase;
import fr.paladium.palawarzoneevent.module.warzone.common.network.RBPacketRequestPlayerCount;
import fr.paladium.palawarzoneevent.module.warzone.server.phase.WarzonePhaseManager;
import fr.paladium.palawarzoneevent.module.warzone.server.worlddata.WarzoneLeaderboardType;
import fr.paladium.palawarzoneevent.module.warzone.server.worlddata.WarzoneLeaderboardWorldData;
import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;
import net.minecraft.entity.player.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class WarzoneServerAction {
  private static final String WARZONE_SERVER_ID = "dd64e57a-96b9-4f2a-acee-ca251294dec9";
  
  private static final String WARZONE_SERVER_NAME = "WarZone";
  
  private static final Gson GSON = (new GsonBuilder()).create();
  
  @SideOnly(Side.SERVER)
  private static Object pluginInstance;
  
  @ServerAction
  public static CompletableFuture<CaptureLeaderboardData> getLeaderboard(int index) {
    return ServerActionHook.useServer(context -> {
          if (Server.is(new ServerType[] { ServerType.WARZONE }, ))
            return CompletableFuture.completedFuture(new CaptureLeaderboardData(WarzoneLeaderboardWorldData.get().getLeaderboardPage(WarzoneLeaderboardType.CAPTURE, index))); 
          CompletableFuture<CaptureLeaderboardData> future = new CompletableFuture<>();
          (new RBPacketRequestCaptureClassementPage(index)).subscribe(()).send("dd64e57a-96b9-4f2a-acee-ca251294dec9");
          return future;
        }new Object[] { Integer.valueOf(index) });
  }
  
  @ServerAction
  public static CompletableFuture<String> getCaptureHolder() {
    return ServerActionHook.useServer(context -> {
          if (Server.is(new ServerType[] { ServerType.WARZONE }, )) {
            if (CaptureModule.getServer().getConfig().getCapturePoints().isEmpty())
              return CompletableFuture.completedFuture(""); 
            CapturePoint cPoint = CaptureModule.getServer().getConfig().getCapturePoints().get(0);
            if (cPoint.getCapturingProgress() == 100.0D && cPoint.getCapturingFactionUUID() != null) {
              IFaction faction = FactionAPI.getInstance().getFaction(cPoint.getCapturingFactionUUID());
              return CompletableFuture.completedFuture((faction == null) ? "" : faction.getName());
            } 
            return CompletableFuture.completedFuture("");
          } 
          CompletableFuture<String> future = new CompletableFuture<>();
          (new RBPacketGetCaptureHolder()).subscribe(()).send("dd64e57a-96b9-4f2a-acee-ca251294dec9");
          return future;
        }new Object[0]);
  }
  
  @ServerAction
  public static CompletableFuture<Integer> getPlayerCount() {
    return ServerActionHook.useServer(context -> {
          if (Server.is(new ServerType[] { ServerType.WARZONE }, ))
            return CompletableFuture.completedFuture(Integer.valueOf((context.getPlayer().func_130014_f_()).field_73010_i.size())); 
          CompletableFuture<Integer> future = new CompletableFuture<>();
          (new RBPacketRequestPlayerCount()).subscribe(()).send("dd64e57a-96b9-4f2a-acee-ca251294dec9");
          return future;
        }new Object[0]);
  }
  
  @ServerAction
  public static CompletableFuture<String> getCurrentWarzonePhase() {
    return ServerActionHook.useServer(context -> {
          if (Server.is(new ServerType[] { ServerType.WARZONE }, ))
            return CompletableFuture.completedFuture((WarzonePhaseManager.inst().getCurrentWZPhase() != null) ? WarzonePhaseManager.inst().getCurrentWZPhase().getDisplayName() : ""); 
          CompletableFuture<String> future = new CompletableFuture<>();
          (new RBPacketRequestCurrentWarzonePhase()).subscribe(()).send("dd64e57a-96b9-4f2a-acee-ca251294dec9");
          return future;
        }new Object[0]);
  }
  
  @ServerAction
  public static CompletableFuture<Boolean> requestServerWarzoneSwitch() {
    return ServerActionHook.useServer(context -> {
          if (Server.is(new ServerType[] { ServerType.WARZONE }, ))
            return CompletableFuture.completedFuture(Boolean.valueOf(false)); 
          String error = requestSwitch((EntityPlayer)context.getPlayer());
          if (error != null && !error.isEmpty())
            (new Notification(Notification.NotificationType.ERROR, error, "Warzone")).send(context.getPlayer()); 
          return CompletableFuture.completedFuture(Boolean.valueOf((error != null && !error.isEmpty())));
        }new Object[0]);
  }
  
  @SideOnly(Side.SERVER)
  private static String requestSwitch(EntityPlayer player) {
    Player bukkitPlayer = Bukkit.getPlayer(player.func_110124_au());
    if (!CombatTag.getInstance().getManager().isInCombat(bukkitPlayer)) {
      ByteArrayDataOutput out = ByteStreams.newDataOutput();
      out.writeUTF("Connect");
      out.writeUTF("WarZone");
      if (pluginInstance == null)
        try {
          Class<?> clazz = Class.forName("fr.paladium.palacore.PalaCore");
          Method getInstanceMethod = clazz.getDeclaredMethod("getInstance", new Class[0]);
          pluginInstance = getInstanceMethod.invoke(null, new Object[0]);
          if (pluginInstance == null) {
            System.err.println("[WarzoneServerAction] Unable to get PalaCore instance, plugin messaging will not work.");
            return "Une erreur est survenue";
          } 
        } catch (Exception e) {
          e.printStackTrace();
        }  
      if (pluginInstance != null) {
        bukkitPlayer.sendPluginMessage((Plugin)pluginInstance, "BungeeCord", out.toByteArray());
        return null;
      } 
      return "Une erreur est survenue";
    } 
    return "Vous êtes en combat";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\common\action\server\WarzoneServerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */