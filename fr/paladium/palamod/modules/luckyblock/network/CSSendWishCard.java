package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.gui.containers.ContainerWishCard;
import fr.paladium.palamod.modules.luckyblock.utils.wishcard.WishCardManager;
import fr.paladium.palamod.modules.paladium.utils.Packet;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import fr.paladium.palamod.util.DiscordWebhook;
import io.netty.buffer.ByteBuf;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

@Packet(side = Side.SERVER)
public class CSSendWishCard implements IMessage {
  public String receiver;
  
  public CSSendWishCard() {}
  
  public CSSendWishCard(String receiver) {
    this.receiver = receiver;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.receiver = ByteBufUtils.readUTF8String(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.receiver);
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<CSSendWishCard, IMessage> {
    public IMessage onMessage(CSSendWishCard message, MessageContext ctx) {
      EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
      if (!(((EntityPlayer)entityPlayerMP).field_71070_bA instanceof ContainerWishCard)) {
        DiscordWebhook webhook = new DiscordWebhook(PLuckyBlock.wishCardConfig.getWebhook());
        webhook.setAvatarUrl("https://cdn.discordapp.com/emojis/878229207233232936.gif");
        webhook.setTts(true);
        webhook.setUsername("Guardian de noël");
        webhook.setContent("<@253546267064401921>");
        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
        embed.setColor(Color.RED);
        embed.setTitle("Carte de voeux");
        embed.addField("Joueur", entityPlayerMP.func_70005_c_(), false);
        embed.addField("Flag", "Packet sans être dans un container", false);
        webhook.addEmbed(embed);
        try {
          webhook.execute();
        } catch (Exception e) {
          e.printStackTrace();
        } 
        return null;
      } 
      ContainerWishCard container = (ContainerWishCard)((EntityPlayer)entityPlayerMP).field_71070_bA;
      if (!container.func_75139_a(0).func_75216_d()) {
        DiscordWebhook webhook = new DiscordWebhook(PLuckyBlock.wishCardConfig.getWebhook());
        webhook.setAvatarUrl("https://cdn.discordapp.com/emojis/878229207233232936.gif");
        webhook.setTts(true);
        webhook.setUsername("Guardian de noël");
        webhook.setContent("<@253546267064401921>");
        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
        embed.setColor(Color.RED);
        embed.setTitle("Carte de voeux");
        embed.addField("Joueur", entityPlayerMP.func_70005_c_(), false);
        embed.addField("Flag", "Item vide", false);
        webhook.addEmbed(embed);
        try {
          webhook.execute();
        } catch (Exception e) {
          e.printStackTrace();
        } 
        return null;
      } 
      ((EntityPlayer)entityPlayerMP).field_71071_by.func_146026_a(ItemsRegister.WISH_CARD);
      ItemStack item = container.func_75139_a(0).func_75211_c().func_77946_l();
      container.func_75141_a(0, null);
      container.func_75134_a((EntityPlayer)entityPlayerMP);
      WishCardManager.add((EntityPlayer)entityPlayerMP, item, message.receiver);
      entityPlayerMP.func_145747_a((IChatComponent)new ChatComponentText("§aVous avez envoyé une carte de voeux à §e" + message.receiver));
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\CSSendWishCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */