package fr.paladium.palamod.util.network.PNetwork;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.UUID;

public class PMessageHeader {
  private int id;
  
  private UUID uuid;
  
  private boolean isInformer;
  
  public PMessageHeader(int id, UUID uuid, boolean isInformer) {
    this.id = id;
    this.uuid = uuid;
    this.isInformer = isInformer;
  }
  
  public int getId() {
    return this.id;
  }
  
  public UUID getUuid() {
    return this.uuid;
  }
  
  public boolean isInformer() {
    return this.isInformer;
  }
  
  public static PMessageHeader fromByteArrays(byte[] data) {
    ByteArrayDataInput in = ByteStreams.newDataInput(data);
    int id = in.readInt();
    UUID uuid = new UUID(in.readLong(), in.readLong());
    boolean isInformer = in.readBoolean();
    return new PMessageHeader(id, uuid, isInformer);
  }
  
  public byte[] toByteArrays() {
    ByteArrayDataOutput out = ByteStreams.newDataOutput();
    out.writeInt(this.id);
    out.writeLong(this.uuid.getMostSignificantBits());
    out.writeLong(this.uuid.getLeastSignificantBits());
    out.writeBoolean(this.isInformer);
    return out.toByteArray();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\network\PNetwork\PMessageHeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */