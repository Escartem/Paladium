package fr.paladium.palamod.modules.nemesis.module.impl.autoclick.common.packet;

import fr.paladium.palamod.modules.nemesis.module.base.network.NemesisPacket;
import io.netty.buffer.ByteBuf;

public class CSPacketNemesisAutoclickData extends NemesisPacket {
  private boolean left;
  
  private long timestamp;
  
  private int clickType;
  
  public CSPacketNemesisAutoclickData() {}
  
  public CSPacketNemesisAutoclickData(boolean left, long timestamp) {
    this(left, timestamp, left ? 0 : 1);
  }
  
  public CSPacketNemesisAutoclickData(boolean left, long timestamp, int clickType) {
    this.left = left;
    this.timestamp = timestamp;
    this.clickType = clickType;
  }
  
  public void read(ByteBuf buf) {
    this.left = buf.readBoolean();
    this.timestamp = buf.readLong();
    this.clickType = buf.readInt();
  }
  
  public void write(ByteBuf buf) {
    buf.writeBoolean(this.left);
    buf.writeLong(this.timestamp);
    buf.writeInt(this.clickType);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\impl\autoclick\common\packet\CSPacketNemesisAutoclickData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */