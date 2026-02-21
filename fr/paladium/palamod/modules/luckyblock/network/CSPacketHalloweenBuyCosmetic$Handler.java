package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.HalloweenConfig;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import retrofit2.Response;

@PacketHandler
public class Handler implements IMessageHandler<CSPacketHalloweenBuyCosmetic, IMessage> {
  @SideOnly(Side.SERVER)
  public IMessage onMessage(CSPacketHalloweenBuyCosmetic message, MessageContext ctx) {
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    HalloweenConfig config = PLuckyBlock.instance.getHalloweenConfig();
    if (config == null) {
      (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue #1", "halloween")).send(player);
      return null;
    } 
    PlayerLuckyBlockProperties lp = PlayerLuckyBlockProperties.get((EntityPlayer)player);
    if (lp.isHasBuyHalloweenCosmetic()) {
      (new Notification(Notification.NotificationType.ERROR, "Vous avez déjà acheté ce cosmetic", "halloween")).send(player);
      return null;
    } 
    lp.setHasBuyHalloweenCosmetic(true);
    int cost = config.getCosmeticCost();
    try {
      Response<Integer> response = ApiServices.Http.getHalloweenService().get().execute();
      if (!response.isSuccessful()) {
        (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue #2", "halloween")).send(player);
        lp.setHasBuyHalloweenCosmetic(false);
        return null;
      } 
      int remaining = 10 - ((Integer)response.body()).intValue();
      config.setCosmeticRemainings(remaining);
      if (remaining <= 0) {
        (new Notification(Notification.NotificationType.ERROR, "Il n'y a plus de cosmétiques en vente", "halloween")).send(player);
        lp.setHasBuyHalloweenCosmetic(false);
        return null;
      } 
      boolean hasEnough = InventoryUtils.haveRequiredItem((EntityPlayer)player, new ItemStack(ItemsRegister.CANDY_BAG), cost);
      if (!hasEnough) {
        (new Notification(Notification.NotificationType.ERROR, "Vous n'avez pas assez de sacs de bonbon", "halloween")).send(player);
        lp.setHasBuyHalloweenCosmetic(false);
        return null;
      } 
      int consumed = 0;
      for (int i = 0; i < cost; i++) {
        if (!player.field_71071_by.func_146026_a(ItemsRegister.CANDY_BAG)) {
          hasEnough = false;
          break;
        } 
        consumed++;
      } 
      if (!hasEnough) {
        (new Notification(Notification.NotificationType.ERROR, "Vous n'avez pas assez de sacs de bonbon", "halloween")).send(player);
        InventoryUtils.addItem((EntityPlayer)player, new ItemStack(ItemsRegister.CANDY_BAG), consumed);
        lp.setHasBuyHalloweenCosmetic(false);
        return null;
      } 
      try {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        boolean success = Bukkit.dispatchCommand((CommandSender)console, config.getCosmeticCommand().replace("%player_name%", player.func_70005_c_()));
        if (!success) {
          (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue #3", "halloween")).send(player);
          InventoryUtils.addItem((EntityPlayer)player, new ItemStack(ItemsRegister.CANDY_BAG), consumed);
          lp.setHasBuyHalloweenCosmetic(false);
          return null;
        } 
        if (ApiServices.Http.getHalloweenService().increment().execute().isSuccessful())
          config.setCosmeticRemainings(remaining - 1); 
      } catch (Exception e) {
        (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue #4", "halloween")).send(player);
        InventoryUtils.addItem((EntityPlayer)player, new ItemStack(ItemsRegister.CANDY_BAG), consumed);
        lp.setHasBuyHalloweenCosmetic(false);
        e.printStackTrace();
      } 
    } catch (IOException e) {
      (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue #5", "halloween")).send(player);
      lp.setHasBuyHalloweenCosmetic(false);
      e.printStackTrace();
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\CSPacketHalloweenBuyCosmetic$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */