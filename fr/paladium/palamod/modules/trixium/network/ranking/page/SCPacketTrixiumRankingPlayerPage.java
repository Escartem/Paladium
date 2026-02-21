package fr.paladium.palamod.modules.trixium.network.ranking.page;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.lib.apollon.nodes.buttons.utils.DynamicScrollableArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollableArea;
import fr.paladium.palamod.modules.trixium.api.TrixiumProfile;
import fr.paladium.palamod.modules.trixium.gui.UITrixiumRanking;
import fr.paladium.palamod.modules.trixium.gui.node.ranking.TrixiumPlayerRankingNode;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;

public class SCPacketTrixiumRankingPlayerPage implements IMessage {
  private int page;
  
  private List<TrixiumProfile> players;
  
  public SCPacketTrixiumRankingPlayerPage() {}
  
  public SCPacketTrixiumRankingPlayerPage(int page, List<TrixiumProfile> players) {
    this.page = page;
    this.players = players;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.page = buf.readInt();
    this.players = new ArrayList<>();
    int size = buf.readInt();
    for (int i = 0; i < size; i++) {
      String playerUUID = ByteBufUtils.readUTF8String(buf);
      String playerName = ByteBufUtils.readUTF8String(buf);
      long amountTrixium = buf.readLong();
      TrixiumProfile profile = new TrixiumProfile();
      profile.playerUUID = playerUUID;
      profile.playerName = playerName;
      profile.amountTrixium = amountTrixium;
      this.players.add(profile);
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.page);
    buf.writeInt(this.players.size());
    for (TrixiumProfile profile : this.players) {
      ByteBufUtils.writeUTF8String(buf, profile.playerUUID);
      ByteBufUtils.writeUTF8String(buf, profile.playerName);
      buf.writeLong(profile.amountTrixium);
    } 
  }
  
  public static class Handler implements IMessageHandler<SCPacketTrixiumRankingPlayerPage, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketTrixiumRankingPlayerPage message, MessageContext ctx) {
      if (!((Minecraft.func_71410_x()).field_71462_r instanceof UITrixiumRanking))
        return null; 
      UITrixiumRanking ui = (UITrixiumRanking)(Minecraft.func_71410_x()).field_71462_r;
      DynamicScrollableArea area = ui.getPlayersArea();
      int i = message.page * 20;
      for (TrixiumProfile profile : message.players) {
        ui.addNode((new TrixiumPlayerRankingNode(ui.width(2.86F), ui.height(27.59F + 5.0F * i), ui.width(33.9F), ui.height(5.0F), i, profile.playerName, (int)profile.amountTrixium)).setArea((ScrollableArea)area));
        i++;
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\network\ranking\page\SCPacketTrixiumRankingPlayerPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */