package fr.paladium.palajobs.core.packets.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.client.ui.lvltokens.UILvlToken;
import fr.paladium.palajobs.core.tokens.LvlToken;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.client.Minecraft;

public class SCPacketUpdateLvlTokenGui implements IMessage {
  public SCPacketUpdateLvlTokenGui() {}
  
  public SCPacketUpdateLvlTokenGui(List<LvlToken> lvlTokens) {
    this.lvlTokens = lvlTokens;
  }
  
  private static final Gson gson = new Gson();
  
  private List<LvlToken> lvlTokens;
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, gson.toJson(this.lvlTokens));
  }
  
  public void fromBytes(ByteBuf buf) {
    this.lvlTokens = (List<LvlToken>)gson.fromJson(ByteBufUtils.readUTF8String(buf), (new TypeToken<List<LvlToken>>() {
        
        }).getType());
  }
  
  public static class Handler implements IMessageHandler<SCPacketUpdateLvlTokenGui, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketUpdateLvlTokenGui message, MessageContext ctx) {
      if ((Minecraft.func_71410_x()).field_71462_r instanceof UILvlToken) {
        UILvlToken ui = (UILvlToken)(Minecraft.func_71410_x()).field_71462_r;
        ui.setLastdiscovery(System.currentTimeMillis());
        ui.setData(message.lvlTokens);
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\server\SCPacketUpdateLvlTokenGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */