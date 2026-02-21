package fr.paladium.palamod.modules.nemesis.module.impl.dump.common.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.security.AccessController;
import java.util.List;

public class NemesisClassStreamObject implements BBPacketNemesisDumpStreamData.NemesisStreamObject {
  private List<AccessController.LoadedClassInfo> classList;
  
  public NemesisClassStreamObject() {}
  
  public NemesisClassStreamObject(List<AccessController.LoadedClassInfo> classList) {
    this.classList = classList;
  }
  
  public List<AccessController.LoadedClassInfo> getClassList() {
    return this.classList;
  }
  
  public void write(ByteBuf buf) {
    buf.writeInt(this.classList.size());
    for (AccessController.LoadedClassInfo clazz : this.classList)
      ByteBufUtils.writeUTF8String(buf, clazz.name); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\impl\dump\common\packet\SCPacketNemesisDumpRequest$NemesisClassStreamObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */