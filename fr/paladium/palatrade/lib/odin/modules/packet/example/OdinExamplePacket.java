package fr.paladium.palatrade.lib.odin.modules.packet.example;

import fr.paladium.palatrade.lib.odin.modules.packet.lib.ForgePacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class OdinExamplePacket extends ForgePacket {
  private String data;
  
  public OdinExamplePacket() {}
  
  public OdinExamplePacket(String data) {
    this.data = data;
  }
  
  public void write(ByteBuf buf) {
    writeString(buf, this.data);
  }
  
  public void read(ByteBuf buf) {
    this.data = readString(buf);
  }
  
  public void processServer(EntityPlayerMP player) {
    System.out.println(player.func_70005_c_() + " - " + this.data);
  }
  
  public void processClient() {
    System.out.println(this.data);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\packet\example\OdinExamplePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */