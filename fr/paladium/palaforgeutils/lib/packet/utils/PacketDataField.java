package fr.paladium.palaforgeutils.lib.packet.utils;

import java.lang.reflect.Field;

public class PacketDataField implements Comparable<PacketDataField> {
  private Field field;
  
  private PacketData data;
  
  public PacketDataField(Field field, PacketData data) {
    this.field = field;
    this.data = data;
  }
  
  public Field getField() {
    return this.field;
  }
  
  public PacketData getData() {
    return this.data;
  }
  
  public int compareTo(PacketDataField o) {
    return o.field.getName().compareTo(this.field.getName());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packe\\utils\PacketDataField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */