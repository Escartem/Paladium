package fr.paladium.palajobs.core.packets.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.client.ui.home.UIJobsHome;
import fr.paladium.palajobs.client.ui.lvltokens.UILvlToken;
import fr.paladium.palajobs.core.tokens.LvlToken;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class SCPacketOpenLvlTokenGui implements IMessage {
  public SCPacketOpenLvlTokenGui() {}
  
  public SCPacketOpenLvlTokenGui(List<LvlToken> lvlTokens) {
    this.lvlTokens = lvlTokens;
  }
  
  private static final Gson gson = new Gson();
  
  private List<LvlToken> lvlTokens;
  
  public void toBytes(ByteBuf buf) {
    for (JobType jobType : JobType.values()) {
      String json = gson.toJson(getTokensByJobType(jobType));
      ByteBufUtils.writeUTF8String(buf, json);
    } 
  }
  
  public void fromBytes(ByteBuf buf) {
    this.lvlTokens = new ArrayList<>();
    for (JobType x : JobType.values()) {
      List<LvlToken> tokens = (List<LvlToken>)gson.fromJson(ByteBufUtils.readUTF8String(buf), (new TypeToken<List<LvlToken>>() {
          
          }).getType());
      this.lvlTokens.addAll(tokens);
    } 
  }
  
  private List<LvlToken> getTokensByJobType(JobType jobType) {
    return (List<LvlToken>)this.lvlTokens.stream()
      .filter(lvltoken -> (lvltoken.getJobType() == jobType))
      .collect(Collectors.toList());
  }
  
  public static class Handler implements IMessageHandler<SCPacketOpenLvlTokenGui, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketOpenLvlTokenGui message, MessageContext ctx) {
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new UILvlToken((GuiScreen)new UIJobsHome(), message.lvlTokens));
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\server\SCPacketOpenLvlTokenGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */