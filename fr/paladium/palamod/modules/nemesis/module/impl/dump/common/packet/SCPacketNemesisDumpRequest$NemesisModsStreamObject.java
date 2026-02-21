package fr.paladium.palamod.modules.nemesis.module.impl.dump.common.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.util.List;

public class NemesisModsStreamObject implements BBPacketNemesisDumpStreamData.NemesisStreamObject {
  private List<ModInfo> modList;
  
  public NemesisModsStreamObject() {}
  
  public NemesisModsStreamObject(List<ModInfo> modList) {
    this.modList = modList;
  }
  
  public static class ModInfo {
    public String name;
    
    public String id;
    
    public String version;
    
    public String source;
    
    public ModInfo() {}
    
    public ModInfo(String name, String id, String version, String source) {
      this.name = name;
      this.id = id;
      this.version = version;
      this.source = source;
    }
  }
  
  public List<ModInfo> getModList() {
    return this.modList;
  }
  
  public void write(ByteBuf buf) {
    buf.writeInt(this.modList.size());
    for (ModInfo m : this.modList) {
      ByteBufUtils.writeUTF8String(buf, m.name);
      ByteBufUtils.writeUTF8String(buf, m.id);
      ByteBufUtils.writeUTF8String(buf, m.version);
      ByteBufUtils.writeUTF8String(buf, m.source);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\impl\dump\common\packet\SCPacketNemesisDumpRequest$NemesisModsStreamObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */