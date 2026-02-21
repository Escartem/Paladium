package fr.paladium.palamod.modules.nemesis.module.impl.dump.common.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.nemesis.module.base.network.NemesisPacket;
import fr.paladium.palamod.modules.nemesis.module.impl.dump.NemesisDumpModule;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import lombok.NonNull;

public class BBPacketNemesisDumpStreamData extends NemesisPacket {
  public BBPacketNemesisDumpStreamData() {}
  
  private static final Map<Class<? extends NemesisStreamObject>, String> STRING_BY_CLASS = new HashMap<>();
  
  private static final Map<String, Class<? extends NemesisStreamObject>> CLASS_BY_STRING = new HashMap<>();
  
  private static NemesisStream stream;
  
  private byte action;
  
  private int sended;
  
  private int toSend;
  
  private NemesisStreamObject object;
  
  public BBPacketNemesisDumpStreamData(byte action) {
    this.action = action;
  }
  
  public void write(ByteBuf buf) {
    if (this.action == 1) {
      buf.writeByte(this.action);
      return;
    } 
    NemesisStreamObject streamObject = stream.send();
    buf.writeByte(this.action);
    buf.writeInt(stream.getSended());
    buf.writeInt(stream.getToSend());
    if (streamObject != null) {
      ByteBufUtils.writeUTF8String(buf, STRING_BY_CLASS.get(streamObject.getClass()));
      streamObject.write(buf);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    if (stream != null && !stream.isEmpty())
      NemesisDumpModule.getInstance().getNetwork().send(new BBPacketNemesisDumpStreamData((byte)0)); 
  }
  
  public static void setStream(NemesisStream newStream) {
    if (stream != null && stream.isSending())
      throw new IllegalStateException("Cannot set a new stream while the current one is being sent."); 
    stream = newStream;
  }
  
  public static void stopStream() {
    if (stream != null)
      stream = null; 
  }
  
  public static void registerStreamObject(@NonNull String name, @NonNull Class<? extends NemesisStreamObject> clazz) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    if (clazz == null)
      throw new NullPointerException("clazz is marked non-null but is null"); 
    STRING_BY_CLASS.put(clazz, name);
    CLASS_BY_STRING.put(name, clazz);
  }
  
  public static interface NemesisStreamObject {
    void write(ByteBuf param1ByteBuf);
  }
  
  public static class NemesisStream {
    private final transient Queue<BBPacketNemesisDumpStreamData.NemesisStreamObject> objects;
    
    private final int toSend;
    
    private boolean sending;
    
    private int sended;
    
    public void setSending(boolean sending) {
      this.sending = sending;
    }
    
    public void setSended(int sended) {
      this.sended = sended;
    }
    
    public Queue<BBPacketNemesisDumpStreamData.NemesisStreamObject> getObjects() {
      return this.objects;
    }
    
    public int getToSend() {
      return this.toSend;
    }
    
    public boolean isSending() {
      return this.sending;
    }
    
    public int getSended() {
      return this.sended;
    }
    
    public NemesisStream(List<BBPacketNemesisDumpStreamData.NemesisStreamObject> objects) {
      this.objects = new LinkedList<>(objects);
      this.toSend = objects.size();
      this.sending = false;
      this.sended = 0;
    }
    
    public float getProgression() {
      if (this.toSend == 0)
        return 1.0F; 
      return this.sended / this.toSend;
    }
    
    public boolean isEmpty() {
      return this.objects.isEmpty();
    }
    
    public BBPacketNemesisDumpStreamData.NemesisStreamObject send() {
      if (this.objects.isEmpty()) {
        this.sending = false;
        return null;
      } 
      BBPacketNemesisDumpStreamData.NemesisStreamObject object = this.objects.poll();
      if (++this.sended >= this.toSend) {
        this.sending = false;
      } else {
        this.sending = true;
      } 
      return object;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\impl\dump\common\packet\BBPacketNemesisDumpStreamData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */