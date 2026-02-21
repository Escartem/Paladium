package fr.paladium.pet.common.network.packet.skill.breakspeed;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.pet.client.data.ClientBreakSpeedData;

public class SCGlobalBreakSpeedPacket extends ForgePacket {
  @PacketData
  double value;
  
  @PacketData
  long duration;
  
  public SCGlobalBreakSpeedPacket() {}
  
  public SCGlobalBreakSpeedPacket(double value, long duration) {
    this.value = value;
    this.duration = duration;
  }
  
  public void processClient() {
    ClientBreakSpeedData.get().updateGlobalBreakSpeed(this.value, this.duration);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\skill\breakspeed\SCGlobalBreakSpeedPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */