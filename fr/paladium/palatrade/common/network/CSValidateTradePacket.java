package fr.paladium.palatrade.common.network;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.api.server.pb.dto.PBType;
import fr.paladium.palashop.server.pb.PBManager;
import fr.paladium.palatrade.common.container.ContainerTrade;
import fr.paladium.palatrade.common.utils.Trade;
import fr.paladium.palatrade.lib.odin.modules.packet.OdinPacketModule;
import fr.paladium.palatrade.lib.odin.modules.packet.lib.ForgePacket;
import fr.paladium.palatrade.server.event.TradeSuccesEvent;
import fr.paladium.palatrade.server.manager.TradeManager;
import io.netty.buffer.ByteBuf;
import java.util.Arrays;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class CSValidateTradePacket extends ForgePacket {
  private boolean validated;
  
  public CSValidateTradePacket() {}
  
  public CSValidateTradePacket(boolean validated) {
    this.validated = validated;
  }
  
  public void write(ByteBuf buf) {
    writeBoolean(buf, this.validated);
  }
  
  public void read(ByteBuf buf) {
    this.validated = readBoolean(buf);
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    Trade trade = TradeManager.getInstance().getTrade((EntityPlayer)player);
    EntityPlayer target = TradeManager.getInstance().getTradePlayer((EntityPlayer)player);
    if (trade == null || !(target instanceof EntityPlayerMP))
      return; 
    Trade targetTrade = TradeManager.getInstance().getTrade(target);
    if (targetTrade == null || trade.isPlayerValidated() == this.validated || !(player.field_71070_bA instanceof ContainerTrade) || !(target.field_71070_bA instanceof ContainerTrade))
      return; 
    if (System.currentTimeMillis() - trade.getLastEdit() < 3000L) {
      OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCUnvalidateTradePacket("Le joueur en face a récemment modifié l'échange", 50, true), player);
      return;
    } 
    trade.setPlayerValidated(this.validated);
    ((ContainerTrade)player.field_71070_bA).setValidated(this.validated);
    OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCValidateTradePacket(this.validated), (EntityPlayerMP)target);
    if (trade.isPlayerValidated() && targetTrade.isPlayerValidated() && player.field_71070_bA instanceof ContainerTrade && target.field_71070_bA instanceof ContainerTrade) {
      trade.setProcessing(true);
      targetTrade.setProcessing(true);
      if (trade.getPb() < 10.0D && trade.getPb() > 0.0D) {
        ((ContainerTrade)player.field_71070_bA).setValidated(false);
        ((ContainerTrade)target.field_71070_bA).setValidated(false);
        trade.setProcessing(false);
        targetTrade.setProcessing(false);
        OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCUnvalidateTradePacket("Vous ne pouvez pas échanger moins de 10 PBs", 50, true), player);
        OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCUnvalidateTradePacket(target.func_70005_c_() + " ne peut pas échanger moins de 10 PBs", 50, false), (EntityPlayerMP)target);
        return;
      } 
      if (targetTrade.getPb() < 10.0D && targetTrade.getPb() > 0.0D) {
        ((ContainerTrade)player.field_71070_bA).setValidated(false);
        ((ContainerTrade)target.field_71070_bA).setValidated(false);
        trade.setProcessing(false);
        targetTrade.setProcessing(false);
        OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCUnvalidateTradePacket("Vous ne pouvez pas échanger moins de 10 PBs", 50, true), (EntityPlayerMP)target);
        OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCUnvalidateTradePacket(target.func_70005_c_() + " ne peut pas échanger moins de 10 PBs", 50, false), player);
        return;
      } 
      long t1Space = Arrays.<ItemStack>asList(trade.getItems()).stream().filter(item -> (item != null)).count();
      int i1Space = 0;
      for (ItemStack item : target.field_71071_by.field_70462_a) {
        if (item == null)
          i1Space++; 
      } 
      if (i1Space < t1Space) {
        ((ContainerTrade)player.field_71070_bA).setValidated(false);
        ((ContainerTrade)target.field_71070_bA).setValidated(false);
        trade.setProcessing(false);
        targetTrade.setProcessing(false);
        OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCUnvalidateTradePacket("Vous n'avez pas assez de place pour récupérer tous les items", 50, true), (EntityPlayerMP)target);
        OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCUnvalidateTradePacket(target.func_70005_c_() + " n'a pas assez de place pour récupérer tous les items", 50, false), player);
        return;
      } 
      long t2Space = Arrays.<ItemStack>asList(targetTrade.getItems()).stream().filter(item -> (item != null)).count();
      int i2Space = 0;
      for (ItemStack item : player.field_71071_by.field_70462_a) {
        if (item == null)
          i2Space++; 
      } 
      if (i2Space < t2Space) {
        ((ContainerTrade)player.field_71070_bA).setValidated(false);
        ((ContainerTrade)target.field_71070_bA).setValidated(false);
        trade.setProcessing(false);
        targetTrade.setProcessing(false);
        OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCUnvalidateTradePacket("Vous n'avez pas assez de place pour récupérer tous les items", 50, true), player);
        OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCUnvalidateTradePacket(player.func_70005_c_() + " n'a pas assez de place pour récupérer tous les items", 50, false), (EntityPlayerMP)target);
        return;
      } 
      if (player.field_71070_bA instanceof ContainerTrade)
        ((ContainerTrade)player.field_71070_bA).getTradeInv().setContent(new ItemStack[21]); 
      if (target.field_71070_bA instanceof ContainerTrade)
        ((ContainerTrade)target.field_71070_bA).getTradeInv().setContent(new ItemStack[21]); 
      TradeManager.getInstance().removeTrade((EntityPlayer)player);
      player.func_71053_j();
      target.func_71053_j();
      for (ItemStack item : trade.getItems()) {
        if (item != null)
          target.field_71071_by.func_70441_a(item); 
      } 
      for (ItemStack item : targetTrade.getItems()) {
        if (item != null)
          player.field_71071_by.func_70441_a(item); 
      } 
      PBManager.get(UUIDUtils.toString((Entity)player)).thenAccept(pb -> {
            trade.setMaxXp(TradeManager.getInstance().getTotalXp((EntityPlayer)player));
            trade.setMaxMoney(CresusManager.getInstance().getBalance(player.func_110124_au()));
            trade.setMaxPb((pb.longValue() < 10L) ? 0.0D : pb.longValue());
            trade.setXp(Math.min(trade.getMaxXp(), trade.getXp()));
            trade.setMoney(Math.min(trade.getMaxMoney(), trade.getMoney()));
            trade.setPb(Math.min(trade.getMaxPb(), trade.getPb()));
            PBManager.get(UUIDUtils.toString((Entity)target)).thenAccept(());
          });
      (new Notification(Notification.NotificationType.SUCCESS, "L'échange avec " + target.func_70005_c_() + " a bien été effectué", "trade")).send(player);
      (new Notification(Notification.NotificationType.SUCCESS, "L'échange avec " + player.func_70005_c_() + " a bien été effectué", "trade")).send((EntityPlayerMP)target);
      MinecraftForge.EVENT_BUS.post((Event)TradeSuccesEvent.of(player, (EntityPlayerMP)target, trade, targetTrade));
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\common\network\CSValidateTradePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */