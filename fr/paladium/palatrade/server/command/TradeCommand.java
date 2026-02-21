package fr.paladium.palatrade.server.command;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palatrade.PalaTrade;
import fr.paladium.palatrade.common.CommonProxy;
import fr.paladium.palatrade.common.network.SCOpenTradePacket;
import fr.paladium.palatrade.common.utils.TradeRequest;
import fr.paladium.palatrade.lib.odin.modules.command.lib.Command;
import fr.paladium.palatrade.lib.odin.modules.packet.OdinPacketModule;
import fr.paladium.palatrade.lib.odin.utils.task.DurationConverter;
import fr.paladium.palatrade.server.manager.TradeManager;
import fr.paladium.vanish.VanishPlugin;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import org.bukkit.Bukkit;

public class TradeCommand extends Command {
  public boolean perform(ICommandSender sender, String[] args) {
    EntityPlayer player = (EntityPlayer)sender;
    TradeManager manager = TradeManager.getInstance();
    if (args.length == 0) {
      sendUsage((ICommandSender)player);
      return true;
    } 
    if (args.length == 1) {
      if ("accept".equalsIgnoreCase(args[0]) && manager.getWaitingTrades(player).size() == 1) {
        TradeRequest request = manager.getWaitingTrades(player).get(0);
        EntityPlayer entityPlayer = null;
        for (WorldServer worldServer : (MinecraftServer.func_71276_C()).field_71305_c) {
          EntityPlayer found = worldServer.func_152378_a(request.getPlayer());
          if (found != null) {
            entityPlayer = found;
            break;
          } 
        } 
        if (entityPlayer == null) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cCe joueur n'est pas en ligne. (Ou dans un monde différent)");
          return true;
        } 
        if (!manager.isWaiting(player, entityPlayer)) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cVous n'avez pas de demande d'échange en cours venant de ce joueur.");
          return true;
        } 
        if (manager.isTrading(player)) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cVous êtes entrain d'effectuer un trade, cette tentative a donc été signalée.");
          return true;
        } 
        if (manager.isTrading(entityPlayer)) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cCe joueur est déjà entrain d'effectuer un échange, veuillez réessayer plus tard.");
          return true;
        } 
        if (!manager.acceptTradeRequest(player, entityPlayer)) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cImpossible d'accepter la demande d'échange de ce joueur.");
          return true;
        } 
        player.openGui(PalaTrade.getInstance(), CommonProxy.TRADE_GUI, player.field_70170_p, 0, 0, 0);
        entityPlayer.openGui(PalaTrade.getInstance(), CommonProxy.TRADE_GUI, entityPlayer.field_70170_p, 0, 0, 0);
        OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCOpenTradePacket(entityPlayer.func_70005_c_()), (EntityPlayerMP)player);
        OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCOpenTradePacket(player.func_70005_c_()), (EntityPlayerMP)entityPlayer);
        return true;
      } 
      if ("deny".equalsIgnoreCase(args[0]) && TradeManager.getInstance().getWaitingTrades(player).size() == 1) {
        TradeRequest request = manager.getWaitingTrades(player).get(0);
        EntityPlayer entityPlayer = null;
        for (WorldServer worldServer : (MinecraftServer.func_71276_C()).field_71305_c) {
          EntityPlayer found = worldServer.func_152378_a(request.getPlayer());
          if (found != null) {
            entityPlayer = found;
            break;
          } 
        } 
        if (!manager.isWaiting(player, entityPlayer)) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cVous n'avez pas de demande d'échange en cours venant de ce joueur.");
          return true;
        } 
        if (manager.isTrading(player)) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cVous êtes entrain d'effectuer un trade, cette tentative a donc été signalée.");
          return true;
        } 
        if (!manager.removeTradeRequest(player, entityPlayer)) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cImpossible de refuser la demande d'échange de ce joueur.");
          return true;
        } 
        reply((ICommandSender)player, "§8[§6Paladium§8] §cLa demande d'échange venant de §e" + entityPlayer.func_70005_c_() + " §ca bien été refusée.");
        return true;
      } 
      String targetArg = args[0];
      EntityPlayer target = getPlayerEntityByName(player.field_70170_p, targetArg);
      if (target == null) {
        reply((ICommandSender)player, "§8[§6Paladium§8] §cCe joueur n'est pas en ligne.");
        return true;
      } 
      try {
        if (VanishPlugin.getInstance().getVanishManager().isVanished(Bukkit.getPlayer(player.func_110124_au()))) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cCe joueur n'est pas en ligne.");
          return true;
        } 
      } catch (Exception|NoClassDefFoundError exception) {}
      if (target.func_110124_au() == player.func_110124_au()) {
        ChatComponentText chatComponentText = new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas faire de demande d'échange avec vous même.");
        ChatStyle chatStyle1 = new ChatStyle();
        chatStyle1.func_150241_a(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://letmegooglethat.com/?q=Comment+trouver+des+amis+sur+minecraft+%3F"));
        chatComponentText.func_150255_a(chatStyle1);
        reply((ICommandSender)player, (IChatComponent)chatComponentText);
        return true;
      } 
      if (!manager.isEnable(target)) {
        reply((ICommandSender)player, "§8[§6Paladium§8] §cCe joueur a désactivé les demandes d'échange.");
        return true;
      } 
      if (manager.isWaiting(target, player)) {
        TradeRequest request = manager.getWaitingTrades(target).stream().filter(r -> r.getPlayer().equals(player.func_110124_au())).findFirst().get();
        reply((ICommandSender)player, "§8[§6Paladium§8] §cVous avez déjà effectué une demande d'échange à ce joueur, elle expirera dans §e" + DurationConverter.fromMillisToString(300000L - System.currentTimeMillis() - request.getTime()));
        return true;
      } 
      if (!manager.sendTradeRequest(player, target)) {
        reply((ICommandSender)player, "§8[§6Paladium§8] §cImpossible d'envoyer une demande d'échange à ce joueur.");
        return true;
      } 
      reply((ICommandSender)player, "§8[§6Paladium§8] §aVotre demande d'échange a bien été envoyée à §e" + target.func_70005_c_());
      reply((ICommandSender)target, "§8§m---------------------------------");
      reply((ICommandSender)target, "§8[§c!§8] §rVous venez de recevoir une §cdemande §rd'échange de la part de §b" + player.func_70005_c_() + "§r.");
      reply((ICommandSender)target, "§8[§c!§8] §rCette demande §cexpirera §rautomatiquement dans 5 minutes.");
      reply((ICommandSender)target, "");
      ChatComponentText message = new ChatComponentText("§8[§2✔§8] §b/trade accept " + player.func_70005_c_());
      ChatStyle chatStyle = new ChatStyle();
      HoverEvent he = new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§8[§2✔§8] §aAccepter"));
      ClickEvent ce = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/trade accept " + player.func_70005_c_());
      chatStyle.func_150209_a(he);
      chatStyle.func_150241_a(ce);
      message.func_150255_a(chatStyle);
      reply((ICommandSender)target, (IChatComponent)message);
      message = new ChatComponentText("§8[§4✘§8] §b/trade deny " + player.func_70005_c_());
      chatStyle = new ChatStyle();
      he = new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§8[§4✘§8] §cRefuser"));
      ce = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/trade deny " + player.func_70005_c_());
      chatStyle.func_150209_a(he);
      chatStyle.func_150241_a(ce);
      message.func_150255_a(chatStyle);
      reply((ICommandSender)target, (IChatComponent)message);
      reply((ICommandSender)target, "§8§m---------------------------------");
      return true;
    } 
    if (args.length == 2) {
      if ("accept".equalsIgnoreCase(args[0])) {
        String targetArg = args[1];
        EntityPlayer target = getPlayerEntityByName(player.field_70170_p, targetArg);
        if (target == null) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cCe joueur n'est pas en ligne.");
          return true;
        } 
        if (!manager.isWaiting(player, target)) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cVous n'avez pas de demande d'échange en cours venant de ce joueur.");
          return true;
        } 
        if (manager.isTrading(player)) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cVous êtes entrain d'effectuer un trade, cette tentative a donc été signalée.");
          return true;
        } 
        if (manager.isTrading(target)) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cCe joueur est déjà entrain d'effectuer un échange, veuillez réessayer plus tard.");
          return true;
        } 
        if (!manager.acceptTradeRequest(player, target)) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cImpossible d'accepter la demande d'échange de ce joueur.");
          return true;
        } 
        player.openGui(PalaTrade.getInstance(), CommonProxy.TRADE_GUI, player.field_70170_p, 0, 0, 0);
        target.openGui(PalaTrade.getInstance(), CommonProxy.TRADE_GUI, target.field_70170_p, 0, 0, 0);
        OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCOpenTradePacket(target.func_70005_c_()), (EntityPlayerMP)player);
        OdinPacketModule.getInstance().getNetwork().sendTo((IMessage)new SCOpenTradePacket(player.func_70005_c_()), (EntityPlayerMP)target);
        return true;
      } 
      if ("deny".equalsIgnoreCase(args[0])) {
        String targetArg = args[1];
        EntityPlayer target = getPlayerEntityByName(player.field_70170_p, targetArg);
        if (target == null) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cCe joueur n'est pas en ligne.");
          return true;
        } 
        if (!manager.isWaiting(player, target)) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cVous n'avez pas de demande d'échange en cours venant de ce joueur.");
          return true;
        } 
        if (manager.isTrading(player)) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cVous êtes entrain d'effectuer un trade, cette tentative a donc été signalée.");
          return true;
        } 
        if (!manager.removeTradeRequest(player, target)) {
          reply((ICommandSender)player, "§8[§6Paladium§8] §cImpossible de refuser la demande d'échange de ce joueur.");
          return true;
        } 
        reply((ICommandSender)player, "§8[§6Paladium§8] §cLa demande d'échange venant de §e" + target.func_70005_c_() + " §ca bien été refusée.");
        return true;
      } 
    } 
    sendUsage((ICommandSender)player);
    return true;
  }
  
  public String getCommandUsage(ICommandSender sender, String[] args) {
    return null;
  }
  
  private EntityPlayer getPlayerEntityByName(World world, String playerName) {
    for (int i = 0; i < world.field_73010_i.size(); i++) {
      EntityPlayer entityplayer = world.field_73010_i.get(i);
      if (playerName.equalsIgnoreCase(entityplayer.func_70005_c_()))
        return entityplayer; 
    } 
    return null;
  }
  
  private void sendUsage(ICommandSender sender) {
    reply(sender, "§8§m---------------------------------");
    reply(sender, "§8[§6Paladium§8] §cMauvaise utilisation de la commande.");
    reply(sender, "");
    reply(sender, "§e/" + func_71517_b() + " <player>");
    reply(sender, "§e/" + func_71517_b() + " accept <player>");
    reply(sender, "§e/" + func_71517_b() + " deny <player>");
    reply(sender, "§8§m---------------------------------");
  }
  
  public List<String> func_71516_a(ICommandSender sender, String[] args) {
    return CommandBase.func_71530_a(args, MinecraftServer.func_71276_C().func_71213_z());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\server\command\TradeCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */