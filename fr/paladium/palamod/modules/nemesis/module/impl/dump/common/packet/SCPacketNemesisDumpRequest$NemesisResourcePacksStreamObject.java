package fr.paladium.palamod.modules.nemesis.module.impl.dump.common.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.util.List;

public class NemesisResourcePacksStreamObject implements BBPacketNemesisDumpStreamData.NemesisStreamObject {
  private List<String> packList;
  
  public NemesisResourcePacksStreamObject() {}
  
  public NemesisResourcePacksStreamObject(List<String> packList) {
    this.packList = packList;
  }
  
  public List<String> getPackList() {
    return this.packList;
  }
  
  public void write(ByteBuf buf) {
    buf.writeInt(this.packList.size());
    for (String p : this.packList)
      ByteBufUtils.writeUTF8String(buf, p); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\impl\dump\common\packet\SCPacketNemesisDumpRequest$NemesisResourcePacksStreamObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */