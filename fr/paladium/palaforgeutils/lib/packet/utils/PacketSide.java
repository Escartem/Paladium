package fr.paladium.palaforgeutils.lib.packet.utils;

import cpw.mods.fml.relauncher.Side;

public enum PacketSide {
  CLIENT(Side.CLIENT),
  SERVER(Side.SERVER),
  BOTH(null);
  
  private final Side side;
  
  PacketSide(Side side) {
    this.side = side;
  }
  
  public Side getSide() {
    return this.side;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packe\\utils\PacketSide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */