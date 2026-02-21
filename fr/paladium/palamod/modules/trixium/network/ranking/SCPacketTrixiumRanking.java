package fr.paladium.palamod.modules.trixium.network.ranking;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.palamod.modules.trixium.api.TrixiumFactionProfile;
import fr.paladium.palamod.modules.trixium.api.TrixiumProfile;
import fr.paladium.palamod.modules.trixium.gui.UITrixiumRanking;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class SCPacketTrixiumRanking implements IMessage {
  private List<TrixiumProfile> players;
  
  private List<TrixiumFactionProfile> factions;
  
  private TrixiumProfile playerProfile;
  
  private TrixiumFactionProfile factionProfile;
  
  public SCPacketTrixiumRanking() {}
  
  public SCPacketTrixiumRanking(List<TrixiumProfile> players, List<TrixiumFactionProfile> factions, TrixiumProfile playerProfile, TrixiumFactionProfile factionProfile) {
    this.players = players;
    this.factions = factions;
    this.playerProfile = playerProfile;
    this.factionProfile = factionProfile;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.players = new ArrayList<>();
    int size = buf.readInt();
    int i;
    for (i = 0; i < size; i++) {
      String str1 = ByteBufUtils.readUTF8String(buf);
      String str2 = ByteBufUtils.readUTF8String(buf);
      long l = buf.readLong();
      TrixiumProfile trixiumProfile = new TrixiumProfile();
      trixiumProfile.playerUUID = str1;
      trixiumProfile.playerName = str2;
      trixiumProfile.amountTrixium = l;
      this.players.add(trixiumProfile);
    } 
    this.factions = new ArrayList<>();
    size = buf.readInt();
    for (i = 0; i < size; i++) {
      String factionUUID = ByteBufUtils.readUTF8String(buf);
      String factionName = ByteBufUtils.readUTF8String(buf);
      long l = buf.readLong();
      TrixiumFactionProfile trixiumFactionProfile = new TrixiumFactionProfile();
      trixiumFactionProfile.factionUUID = factionUUID;
      trixiumFactionProfile.factionName = factionName;
      trixiumFactionProfile.amountTrixium = l;
      boolean exists = buf.readBoolean();
      if (exists) {
        trixiumFactionProfile.backgroundColor = new Color(buf.readInt());
        trixiumFactionProfile.foregroundColor = new Color(buf.readInt());
        trixiumFactionProfile.borderColor = new Color(buf.readInt());
        trixiumFactionProfile.iconColor = new Color(buf.readInt());
        trixiumFactionProfile.iconBorderColor = new Color(buf.readInt());
        trixiumFactionProfile.backgroundId = buf.readInt();
        trixiumFactionProfile.foregroundId = buf.readInt();
        trixiumFactionProfile.iconId = buf.readInt();
      } 
      trixiumFactionProfile.exists = exists;
      this.factions.add(trixiumFactionProfile);
    } 
    String playerUUID = ByteBufUtils.readUTF8String(buf);
    String playerName = ByteBufUtils.readUTF8String(buf);
    long amountTrixium = buf.readLong();
    int rank = buf.readInt();
    TrixiumProfile profile = new TrixiumProfile();
    profile.playerUUID = playerUUID;
    profile.playerName = playerName;
    profile.amountTrixium = amountTrixium;
    profile.rank = rank;
    this.playerProfile = profile;
    if (buf.isReadable()) {
      String factionUUID = ByteBufUtils.readUTF8String(buf);
      String factionName = ByteBufUtils.readUTF8String(buf);
      amountTrixium = buf.readLong();
      rank = buf.readInt();
      TrixiumFactionProfile factionProfile = new TrixiumFactionProfile();
      factionProfile.factionUUID = factionUUID;
      factionProfile.factionName = factionName;
      factionProfile.amountTrixium = amountTrixium;
      factionProfile.rank = rank;
      boolean exists = buf.readBoolean();
      if (exists) {
        factionProfile.backgroundColor = new Color(buf.readInt());
        factionProfile.foregroundColor = new Color(buf.readInt());
        factionProfile.borderColor = new Color(buf.readInt());
        factionProfile.iconColor = new Color(buf.readInt());
        factionProfile.iconBorderColor = new Color(buf.readInt());
        factionProfile.backgroundId = buf.readInt();
        factionProfile.foregroundId = buf.readInt();
        factionProfile.iconId = buf.readInt();
      } 
      factionProfile.exists = exists;
      this.factionProfile = factionProfile;
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.players.size());
    for (TrixiumProfile profile : this.players) {
      ByteBufUtils.writeUTF8String(buf, profile.playerUUID);
      ByteBufUtils.writeUTF8String(buf, profile.playerName);
      buf.writeLong(profile.amountTrixium);
    } 
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
    ByteBufUtils.writeUTF8String(buf, this.playerProfile.playerUUID);
    ByteBufUtils.writeUTF8String(buf, this.playerProfile.playerName);
    buf.writeLong(this.playerProfile.amountTrixium);
    buf.writeInt(this.playerProfile.rank);
    if (this.factionProfile != null) {
      ByteBufUtils.writeUTF8String(buf, this.factionProfile.factionUUID);
      ByteBufUtils.writeUTF8String(buf, this.factionProfile.factionName);
      buf.writeLong(this.factionProfile.amountTrixium);
      buf.writeInt(this.factionProfile.rank);
      buf.writeBoolean(this.factionProfile.exists);
      if (this.factionProfile.exists) {
        buf.writeInt(this.factionProfile.backgroundColor.getRGB());
        buf.writeInt(this.factionProfile.foregroundColor.getRGB());
        buf.writeInt(this.factionProfile.borderColor.getRGB());
        buf.writeInt(this.factionProfile.iconColor.getRGB());
        buf.writeInt(this.factionProfile.iconBorderColor.getRGB());
        buf.writeInt(this.factionProfile.backgroundId);
        buf.writeInt(this.factionProfile.foregroundId);
        buf.writeInt(this.factionProfile.iconId);
      } 
    } 
  }
  
  public static class Handler implements IMessageHandler<SCPacketTrixiumRanking, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketTrixiumRanking message, MessageContext ctx) {
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new UITrixiumRanking(message.players, message.factions, message.playerProfile, message.factionProfile));
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\network\ranking\SCPacketTrixiumRanking.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */