package fr.paladium.palamod.modules.nemesis.module.impl.autoclick.common.packet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.nemesis.module.base.network.NemesisPacket;
import fr.paladium.palamod.modules.nemesis.module.impl.autoclick.client.listener.NemesisAutoclickClientListener;
import io.netty.buffer.ByteBuf;

public class SCPacketNemesisAutoclickToggle extends NemesisPacket {
  private boolean enabled;
  
  public SCPacketNemesisAutoclickToggle() {}
  
  public SCPacketNemesisAutoclickToggle(boolean enabled) {
    this.enabled = enabled;
  }
  
  public void read(ByteBuf buf) {
    this.enabled = buf.readBoolean();
  }
  
  public void write(ByteBuf buf) {
    buf.writeBoolean(this.enabled);
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    NemesisAutoclickClientListener.setWatched(this.enabled);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\impl\autoclick\common\packet\SCPacketNemesisAutoclickToggle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */