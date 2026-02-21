package fr.paladium.palamod.modules.trixium.network.ranking.page;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.lib.apollon.nodes.buttons.utils.DynamicScrollableArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollableArea;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.palamod.modules.trixium.api.TrixiumFactionProfile;
import fr.paladium.palamod.modules.trixium.gui.UITrixiumRanking;
import fr.paladium.palamod.modules.trixium.gui.node.ranking.TrixiumFactionRankingNode;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;

public class SCPacketTrixiumRankingFactionPage implements IMessage {
  private int page;
  
  private List<TrixiumFactionProfile> factions;
  
  public SCPacketTrixiumRankingFactionPage() {}
  
  public SCPacketTrixiumRankingFactionPage(int page, List<TrixiumFactionProfile> factions) {
    this.page = page;
    this.factions = factions;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.page = buf.readInt();
    this.factions = new ArrayList<>();
    int size = buf.readInt();
    for (int i = 0; i < size; i++) {
      String factionUUID = ByteBufUtils.readUTF8String(buf);
      String factionName = ByteBufUtils.readUTF8String(buf);
      long amountTrixium = buf.readLong();
      TrixiumFactionProfile profile = new TrixiumFactionProfile();
      profile.factionUUID = factionUUID;
      profile.factionName = factionName;
      profile.amountTrixium = amountTrixium;
      boolean exists = buf.readBoolean();
      if (exists) {
        profile.backgroundColor = new Color(buf.readInt());
        profile.foregroundColor = new Color(buf.readInt());
        profile.borderColor = new Color(buf.readInt());
        profile.iconColor = new Color(buf.readInt());
        profile.iconBorderColor = new Color(buf.readInt());
        profile.backgroundId = buf.readInt();
        profile.foregroundId = buf.readInt();
        profile.iconId = buf.readInt();
      } 
      profile.exists = exists;
      this.factions.add(profile);
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.page);
    buf.writeInt(this.factions.size());
    for (TrixiumFactionProfile profile : this.factions) {
      ByteBufUtils.writeUTF8String(buf, profile.factionUUID);
      ByteBufUtils.writeUTF8String(buf, profile.factionName);
      buf.writeLong(profile.amountTrixium);
      buf.writeBoolean(profile.exists);
      if (profile.exists) {
        buf.writeInt(profile.backgroundColor.getRGB());
        buf.writeInt(profile.foregroundColor.getRGB());
        buf.writeInt(profile.borderColor.getRGB());
        buf.writeInt(profile.iconColor.getRGB());
        buf.writeInt(profile.iconBorderColor.getRGB());
        buf.writeInt(profile.backgroundId);
        buf.writeInt(profile.foregroundId);
        buf.writeInt(profile.iconId);
      } 
    } 
  }
  
  public static class Handler implements IMessageHandler<SCPacketTrixiumRankingFactionPage, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketTrixiumRankingFactionPage message, MessageContext ctx) {
      if (!((Minecraft.func_71410_x()).field_71462_r instanceof UITrixiumRanking))
        return null; 
      UITrixiumRanking ui = (UITrixiumRanking)(Minecraft.func_71410_x()).field_71462_r;
      DynamicScrollableArea area = ui.getFactionsArea();
      int i = message.page * 20;
      for (TrixiumFactionProfile profile : message.factions) {
        ui.addNode((new TrixiumFactionRankingNode(ui.width(64.06F), ui.height(27.59F + 11.38F * i), ui.width(33.9F), ui.height(11.38F), i, profile)).setArea((ScrollableArea)area));
        i++;
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\network\ranking\page\SCPacketTrixiumRankingFactionPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */