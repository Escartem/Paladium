package fr.paladium.palamod.modules.miner.dimminer.common.network;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.modules.miner.dimminer.common.data.DimMinerPlayer;
import fr.paladium.permissionbridge.common.data.PermissibleEntity;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Handler implements IMessageHandler<CSPacketDimMinerJoin, IMessage> {
  public IMessage onMessage(CSPacketDimMinerJoin message, MessageContext ctx) {
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    if ("ptr".equalsIgnoreCase(CommonModule.getInstance().getConfig().getServerName()) || CommonModule.getInstance().getConfig().getServerType() != ServerType.FACTION) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLa dimension mineur n'est pas disponible sur ce serveur."));
      return null;
    } 
    if (JobsPlayer.get((Entity)player).getLevel(JobType.MINER) < 17) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous n'avez pas le niveau requis."));
      return null;
    } 
    if (!PermissionManager.inst().hasPermission(PermissibleEntity.from((Entity)player), "paladium.dimminer")) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLa dimension mineur est temporairement désactivée."));
      return null;
    } 
    DimMinerPlayer data = DimMinerPlayer.get((Entity)player);
    if (data == null || data.getPortalData() == null) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne semblez pas être dans un portail."));
      return null;
    } 
    if (!data.isActive()) {
      data.create();
      (new Notification(Notification.NotificationType.SUCCESS, "Création de votre dimension mineur en cours", "paladium")).send(player);
    } 
    try {
      Class<?> pluginClass = Class.forName("fr.paladium.palacore.PalaCore");
      Object pluginInstance = pluginClass.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
      ByteArrayDataOutput out = ByteStreams.newDataOutput();
      out.writeUTF("Connect");
      out.writeUTF("dimminer");
      Bukkit.getPlayer(player.func_110124_au()).sendPluginMessage((Plugin)pluginInstance, "BungeeCord", out.toByteArray());
      (new Notification(Notification.NotificationType.SUCCESS, "Téléportation dans votre dimension mineur en cours", "paladium")).send(player);
    } catch (Exception e) {
      e.printStackTrace();
      (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue lors de la téléportation", "paladium")).send(player);
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\network\CSPacketDimMinerJoin$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */