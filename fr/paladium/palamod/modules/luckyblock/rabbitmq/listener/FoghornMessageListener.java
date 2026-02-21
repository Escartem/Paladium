package fr.paladium.palamod.modules.luckyblock.rabbitmq.listener;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.faction.IFactionPlayer;
import fr.paladium.factions.core.player.Player;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.rabbitmq.packet.FoghornMessagePacket;
import fr.paladium.palamod.modules.luckyblock.utils.BungeeCordPlugin;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import fr.paladium.palamod.util.FastUUID;
import fr.paladium.palamod.util.rabbitmq.RabbitListener;
import fr.paladium.palamod.util.rabbitmq.RabbitPacketType;
import fr.paladium.palamod.util.rabbitmq.RabbitQueue;
import fr.paladium.palamod.util.rabbitmq.RabbitService;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;

public class FoghornMessageListener extends RabbitListener<FoghornMessagePacket> {
  public FoghornMessageListener(RabbitService service) {
    super(service, RabbitPacketType.PUBLISH, RabbitQueue.LB_FOGHORN_USE.getQueueName(), FoghornMessagePacket.class);
  }
  
  public void onReceive(FoghornMessagePacket packet) {
    if (packet == null)
      return; 
    try {
      PlayerController controller = PlayerController.getInstance();
      Player player = controller.getLoadedPlayer(packet.getSenderUUID());
      if (player != null && player.hasFaction()) {
        IFaction faction = player.getFaction();
        if (faction != null)
          for (IFactionPlayer factionMember : faction.getMemberEntity().getOnlinePlayers()) {
            EntityPlayerMP mpPlayer = factionMember.getPlayer().getFactionPlayer().getPlayer().getPlayer();
            String playerId = FastUUID.toString((Entity)mpPlayer);
            if (!playerId.equals(packet.getSenderUUID())) {
              UsersManager.getFoghornAllowlist().put(playerId, new BungeeCordPlugin.BungeeCordCommandTPServerCoords(playerId, packet.getServer(), packet.getX(), packet.getY(), packet.getZ()));
              PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("foghorn"), mpPlayer);
              ChatComponentText chatComponentText = new ChatComponentText("§7[§bCorne de brume§7] §bUn membre de votre faction a utilisé une corne de brume sur " + packet.getServer() + ", cliquez sur ce message pour être téléporté.");
              chatComponentText.func_150255_a((new ChatStyle()).func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/foghorn")));
              mpPlayer.func_146105_b((IChatComponent)chatComponentText);
            } 
          }  
      } 
    } catch (Exception exception) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\rabbitmq\listener\FoghornMessageListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */