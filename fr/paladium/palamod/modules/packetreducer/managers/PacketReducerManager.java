package fr.paladium.palamod.modules.packetreducer.managers;

public class PacketReducerManager {
  private static PacketReducerManager instance;
  
  public static PacketReducerManager getInstance() {
    return instance;
  }
  
  public PacketReducerManager() throws Exception {
    instance = this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\packetreducer\managers\PacketReducerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */