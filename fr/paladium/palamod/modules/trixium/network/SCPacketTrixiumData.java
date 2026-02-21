package fr.paladium.palamod.modules.trixium.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.trixium.gui.UITrixium;
import fr.paladium.palamod.modules.trixium.utils.TrixiumReward;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class SCPacketTrixiumData implements IMessage {
  private int trixium;
  
  private List<TrixiumReward> rewards;
  
  public SCPacketTrixiumData() {}
  
  public SCPacketTrixiumData(int trixium, List<TrixiumReward> rewards) {
    this.trixium = trixium;
    this.rewards = rewards;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.trixium = buf.readInt();
    this.rewards = new ArrayList<>();
    while (buf.isReadable()) {
      String uuid = ByteBufUtils.readUTF8String(buf);
      int value = buf.readInt();
      boolean isBig = buf.readBoolean();
      String image = ByteBufUtils.readUTF8String(buf);
      List<String> commands = new ArrayList<>();
      int size = buf.readInt();
      for (int i = 0; i < size; i++)
        commands.add(ByteBufUtils.readUTF8String(buf)); 
      int state = buf.readInt();
      TrixiumReward reward = new TrixiumReward(uuid, value, isBig, image, commands, TrixiumReward.State.values()[state]);
      this.rewards.add(reward);
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.trixium);
    for (TrixiumReward reward : this.rewards) {
      ByteBufUtils.writeUTF8String(buf, reward.getUuid());
      buf.writeInt(reward.getValue());
      buf.writeBoolean(reward.isBig());
      ByteBufUtils.writeUTF8String(buf, reward.getImage());
      buf.writeInt(reward.getCommands().size());
      for (String command : reward.getCommands())
        ByteBufUtils.writeUTF8String(buf, command); 
      buf.writeInt(reward.getState().ordinal());
    } 
  }
  
  public static class Handler implements IMessageHandler<SCPacketTrixiumData, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketTrixiumData message, MessageContext ctx) {
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new UITrixium(message.trixium, message.rewards));
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\network\SCPacketTrixiumData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */