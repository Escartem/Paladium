package fr.paladium.palamod.modules.enderchest.commands;

import com.mojang.authlib.GameProfile;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palamod.modules.enderchest.PEnderChest;
import fr.paladium.palamod.modules.enderchest.rabbitmq.packet.SyncRawGetRequestPacket;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palamod.util.FastUUID;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class EnderChestCommand extends CommandBase {
  public String func_71517_b() {
    return "enderchest";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "enderchest";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (!(sender instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)sender;
    if (args.length == 0) {
      player.func_71007_a((IInventory)player.func_71005_bN());
    } else {
      String targetName = args[0];
      try {
        if (BukkitUtils.hasPermission(player.func_110124_au(), "paladiumenderchest.admin")) {
          PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
                UUID targetUuid = targetName.contains("-") ? FastUUID.parseUUID(targetName) : null;
                EntityPlayer target = (targetUuid != null) ? player.field_70170_p.func_152378_a(targetUuid) : player.field_70170_p.func_72924_a(targetName);
                if (target == null) {
                  if (targetUuid == null) {
                    GameProfile targetProfile = MinecraftServer.func_71276_C().func_152358_ax().func_152655_a(targetName);
                    if (targetProfile == null || targetProfile.getId() == null) {
                      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§cEnderChest§8] §cImpossible de trouver le joueur §e" + targetName));
                      return;
                    } 
                    targetUuid = targetProfile.getId();
                  } 
                  if (PEnderChest.instance.getEnderChests().containsKey(targetUuid)) {
                    player.func_145747_a((IChatComponent)new ChatComponentText("§8[§cEnderChest§8] §cLa demande de modification de cet enderchest est déjà en cours par §e" + ((player.field_70170_p.func_152378_a((UUID)PEnderChest.instance.getEnderChests().get(targetUuid)) == null) ? (String)PEnderChest.instance.getEnderChests().get(targetUuid) : player.field_70170_p.func_152378_a((UUID)PEnderChest.instance.getEnderChests().get(targetUuid)).getDisplayName())));
                    return;
                  } 
                  PEnderChest.instance.getEnderChests().put(targetUuid, player.func_110124_au());
                  try {
                    (new SyncRawGetRequestPacket(targetUuid, InetAddress.getLocalHost().getHostName())).send(PEnderChest.instance.getRabbit());
                  } catch (UnknownHostException e) {
                    e.printStackTrace();
                  } 
                  (new Notification(Notification.NotificationType.INFO, "Demande envoyée", "enderchest")).send(player);
                } else {
                  player.func_71007_a((IInventory)target.func_71005_bN());
                } 
              }0L);
        } else {
          player.func_71007_a((IInventory)player.func_71005_bN());
        } 
      } catch (Exception e) {
        player.func_145747_a((IChatComponent)new ChatComponentText("§8[§cEnderChest§8] §cImpossible de charger bukkit."));
      } 
    } 
  }
  
  public List func_71514_a() {
    return Arrays.asList(new String[] { "ec", "pec" });
  }
  
  public int func_82362_a() {
    return 0;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\commands\EnderChestCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */