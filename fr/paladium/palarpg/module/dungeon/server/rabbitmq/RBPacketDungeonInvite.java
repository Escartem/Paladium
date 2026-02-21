package fr.paladium.palarpg.module.dungeon.server.rabbitmq;

import com.rabbitmq.client.Delivery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitPacket;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.common.network.SCPacketRPGPlaySound;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import java.util.UUID;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class RBPacketDungeonInvite extends RabbitPacket {
  @PacketData
  private String dungeonId;
  
  @PacketData
  private OfflinePlayer player;
  
  @PacketData
  private UUID targetUUID;
  
  public RBPacketDungeonInvite() {}
  
  public RBPacketDungeonInvite(String dungeonId, OfflinePlayer player, UUID targetUUID) {
    this.dungeonId = dungeonId;
    this.player = player;
    this.targetUUID = targetUUID;
  }
  
  @SideOnly(Side.SERVER)
  public void process(@NonNull Delivery delivery) {
    if (delivery == null)
      throw new NullPointerException("delivery is marked non-null but is null"); 
    EntityPlayerMP target = PlayerUtils.getPlayer(this.targetUUID);
    if (target == null)
      return; 
    RPGDungeonPlayerData data = (RPGDungeonPlayerData)RPGPlayer.getData((Entity)target, "dungeon");
    if (data == null || data.getInvitationsMap().containsKey(this.dungeonId))
      return; 
    data.getInvitationsMap().put(this.dungeonId, this.player.getName());
    data.sync();
    (new SCPacketRPGPlaySound("sounds/dungeon/success.ogg", 2.0F)).send(target);
    target.func_145747_a((IChatComponent)new ChatComponentText(""));
    target.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
    target.func_145747_a((IChatComponent)new ChatComponentText(" §6⭐ §lDemande de participation pour un donjon."));
    target.func_145747_a((IChatComponent)new ChatComponentText(""));
    target.func_145747_a((IChatComponent)new ChatComponentText(" §7Vous §7avez §7reçu §7une §7demande §7de §7participation §7pour §7un §7donjon §7de §7la §7part §7de §b" + this.player.getName() + "§7."));
    target.func_145747_a((IChatComponent)new ChatComponentText(""));
    ChatComponentText component = new ChatComponentText(" §a⊙ §7Accepter la demande");
    ChatStyle style = new ChatStyle();
    style.func_150238_a(EnumChatFormatting.RED);
    style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§8[§2✔§8] §aAccepter la demande de participation de §b" + this.player.getName())));
    style.func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg dungeon accept " + this.dungeonId));
    component.func_150255_a(style);
    target.func_145747_a((IChatComponent)component);
    component = new ChatComponentText(" §c⊙ §7Refuser la demande");
    style = new ChatStyle();
    style.func_150238_a(EnumChatFormatting.RED);
    style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§8[§4✘§8] §cRefuser la demande de participation de §b" + this.player.getName())));
    style.func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg dungeon deny " + this.dungeonId));
    component.func_150255_a(style);
    target.func_145747_a((IChatComponent)component);
    target.func_145747_a((IChatComponent)new ChatComponentText("§8§m--------------------------------------"));
    target.func_145747_a((IChatComponent)new ChatComponentText(""));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\rabbitmq\RBPacketDungeonInvite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */