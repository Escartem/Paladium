package fr.paladium.palajobs.core.packets.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palajobs.core.quest.types.ItemGiveQuest;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class CSPalajobsGiveItemQuest implements IMessage {
  private String questUUID;
  
  public void setQuestUUID(String questUUID) {
    this.questUUID = questUUID;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof CSPalajobsGiveItemQuest))
      return false; 
    CSPalajobsGiveItemQuest other = (CSPalajobsGiveItemQuest)o;
    if (!other.canEqual(this))
      return false; 
    Object this$questUUID = getQuestUUID(), other$questUUID = other.getQuestUUID();
    return !((this$questUUID == null) ? (other$questUUID != null) : !this$questUUID.equals(other$questUUID));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof CSPalajobsGiveItemQuest;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $questUUID = getQuestUUID();
    return result * 59 + (($questUUID == null) ? 43 : $questUUID.hashCode());
  }
  
  public String toString() {
    return "CSPalajobsGiveItemQuest(questUUID=" + getQuestUUID() + ")";
  }
  
  public CSPalajobsGiveItemQuest(String questUUID) {
    this.questUUID = questUUID;
  }
  
  public CSPalajobsGiveItemQuest() {}
  
  public String getQuestUUID() {
    return this.questUUID;
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.questUUID);
  }
  
  public void fromBytes(ByteBuf buf) {
    this.questUUID = ByteBufUtils.readUTF8String(buf);
  }
  
  public static class Handler implements IMessageHandler<CSPalajobsGiveItemQuest, IMessage> {
    public IMessage onMessage(CSPalajobsGiveItemQuest message, MessageContext ctx) {
      if (ItemGiveQuest.performCheck((EntityPlayer)(ctx.getServerHandler()).field_147369_b, message.questUUID))
        (ctx.getServerHandler()).field_147369_b.func_71053_j(); 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\client\CSPalajobsGiveItemQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */