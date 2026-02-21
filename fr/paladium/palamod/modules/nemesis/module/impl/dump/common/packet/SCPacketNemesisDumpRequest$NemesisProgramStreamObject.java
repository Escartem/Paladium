package fr.paladium.palamod.modules.nemesis.module.impl.dump.common.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.util.List;

public class NemesisProgramStreamObject implements BBPacketNemesisDumpStreamData.NemesisStreamObject {
  private List<ProgramInfo> programList;
  
  public NemesisProgramStreamObject() {}
  
  public NemesisProgramStreamObject(List<ProgramInfo> programList) {
    this.programList = programList;
  }
  
  public static class ProgramInfo {
    public String name;
    
    public long pid;
    
    public ProgramInfo() {}
    
    public ProgramInfo(String name, long pid) {
      this.name = name;
      this.pid = pid;
    }
  }
  
  public List<ProgramInfo> getProgramList() {
    return this.programList;
  }
  
  public void write(ByteBuf buf) {
    buf.writeInt(this.programList.size());
    for (ProgramInfo p : this.programList) {
      ByteBufUtils.writeUTF8String(buf, p.name);
      buf.writeLong(p.pid);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\impl\dump\common\packet\SCPacketNemesisDumpRequest$NemesisProgramStreamObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */