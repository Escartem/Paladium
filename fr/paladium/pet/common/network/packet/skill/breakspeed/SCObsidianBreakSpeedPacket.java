package fr.paladium.pet.common.network.packet.skill.breakspeed;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.pet.client.data.ClientBreakSpeedData;

public class SCObsidianBreakSpeedPacket extends ForgePacket {
  @PacketData
  double value;
  
  @PacketData
  long duration;
  
  public SCObsidianBreakSpeedPacket() {}
  
  public SCObsidianBreakSpeedPacket(double value, long duration) {
    this.value = value;
    this.duration = duration;
  }
  
  public void processClient() {
    ClientBreakSpeedData.get().updateObsidianBreakSpeed(this.value, this.duration);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\skill\breakspeed\SCObsidianBreakSpeedPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */