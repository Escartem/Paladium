package fr.paladium.palamod.modules.nemesis.module.impl.dump.common.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;

public class NemesisCommandLineStreamObject implements BBPacketNemesisDumpStreamData.NemesisStreamObject {
  private String commandLine;
  
  public NemesisCommandLineStreamObject() {}
  
  public NemesisCommandLineStreamObject(String commandLine) {
    this.commandLine = commandLine;
  }
  
  public String getCommandLine() {
    return this.commandLine;
  }
  
  public void write(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.commandLine);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\impl\dump\common\packet\SCPacketNemesisDumpRequest$NemesisCommandLineStreamObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */