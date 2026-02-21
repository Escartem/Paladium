package fr.paladium.palamod.modules.nemesis.module.impl.dump.common.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.security.AccessController;
import java.util.List;

public class NemesisModuleStreamObject implements BBPacketNemesisDumpStreamData.NemesisStreamObject {
  private List<AccessController.LoadedDllInfo> moduleList;
  
  public NemesisModuleStreamObject() {}
  
  public NemesisModuleStreamObject(List<AccessController.LoadedDllInfo> moduleList) {
    this.moduleList = moduleList;
  }
  
  public List<AccessController.LoadedDllInfo> getModuleList() {
    return this.moduleList;
  }
  
  public void write(ByteBuf buf) {
    buf.writeInt(this.moduleList.size());
    for (AccessController.LoadedDllInfo module : this.moduleList) {
      ByteBufUtils.writeUTF8String(buf, module.name);
      ByteBufUtils.writeUTF8String(buf, module.path);
      buf.writeLong(module.size);
      buf.writeLong(module.loadTime);
      ByteBufUtils.writeUTF8String(buf, module.hash);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\impl\dump\common\packet\SCPacketNemesisDumpRequest$NemesisModuleStreamObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */