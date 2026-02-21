package fr.paladium.asgard.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.asgard.AsgardNetUtils;
import fr.paladium.asgard.packet.handler.TitleHandler;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IChatComponent;

public class S45PacketTitle extends Packet implements IMessage {
  private Type type;
  
  private IChatComponent message;
  
  private int fadeInTime;
  
  private int displayTime;
  
  private int fadeOutTime;
  
  public S45PacketTitle() {}
  
  public S45PacketTitle(Type type, IChatComponent message) {
    this(type, message, -1, -1, -1);
  }
  
  public S45PacketTitle(int fadeInTime, int displayTime, int fadeOutTime) {
    this(Type.TIMES, (IChatComponent)null, fadeInTime, displayTime, fadeOutTime);
  }
  
  public S45PacketTitle(Type type, IChatComponent message, int fadeInTime, int displayTime, int fadeOutTime) {
    this.type = type;
    this.message = message;
    this.fadeInTime = fadeInTime;
    this.displayTime = displayTime;
    this.fadeOutTime = fadeOutTime;
  }
  
  public void fromBytes(ByteBuf buf) {
    try {
      func_148837_a(new PacketBuffer(buf));
    } catch (IOException iOException) {}
  }
  
  public void toBytes(ByteBuf buf) {
    try {
      func_148840_b(new PacketBuffer(buf));
    } catch (IOException iOException) {}
  }
  
  public void func_148837_a(PacketBuffer buf) throws IOException {
    this.type = (Type)AsgardNetUtils.readEnumValue(buf, Type.class);
    if (this.type == Type.TITLE || this.type == Type.SUBTITLE || this.type == Type.ACTIONBAR)
      this.message = AsgardNetUtils.readChatComponent(buf); 
    if (this.type == Type.TIMES) {
      this.fadeInTime = buf.readInt();
      this.displayTime = buf.readInt();
      this.fadeOutTime = buf.readInt();
    } 
  }
  
  public void func_148840_b(PacketBuffer buf) throws IOException {
    AsgardNetUtils.writeEnumValue(buf, this.type);
    if (this.type == Type.TITLE || this.type == Type.SUBTITLE || this.type == Type.ACTIONBAR)
      AsgardNetUtils.writeChatComponent(buf, this.message); 
    if (this.type == Type.TIMES) {
      buf.writeInt(this.fadeInTime);
      buf.writeInt(this.displayTime);
      buf.writeInt(this.fadeOutTime);
    } 
  }
  
  public void func_148833_a(INetHandler handler) {
    TitleHandler.handleTitle(this);
  }
  
  @SideOnly(Side.CLIENT)
  public Type getType() {
    return this.type;
  }
  
  @SideOnly(Side.CLIENT)
  public IChatComponent getMessage() {
    return this.message;
  }
  
  @SideOnly(Side.CLIENT)
  public int getFadeInTime() {
    return this.fadeInTime;
  }
  
  @SideOnly(Side.CLIENT)
  public int getDisplayTime() {
    return this.displayTime;
  }
  
  @SideOnly(Side.CLIENT)
  public int getFadeOutTime() {
    return this.fadeOutTime;
  }
  
  public enum Type {
    TITLE, SUBTITLE, ACTIONBAR, TIMES, CLEAR, RESET;
    
    public static Type byName(String name) {
      for (Type s45packettitle$type : values()) {
        if (s45packettitle$type.name().equalsIgnoreCase(name))
          return s45packettitle$type; 
      } 
      return TITLE;
    }
    
    public static String[] getNames() {
      String[] astring = new String[(values()).length];
      int i = 0;
      for (Type s45packettitle$type : values())
        astring[i++] = s45packettitle$type.name().toLowerCase(); 
      return astring;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\packet\S45PacketTitle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */