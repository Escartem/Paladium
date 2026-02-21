package fr.paladium.palamod.util.network.PNetwork;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PMController {
  private IMethodP iMethodP;
  
  private IListenerP iListenerP;
  
  private String messageChannel;
  
  public static final int PACKET_MCMAXSIZE = 32767;
  
  public static final int PACKET_HEADERSIZE = 21;
  
  public static final int PACKET_FMAXSIZE = 67041282;
  
  private HashMap<UUID, byte[]> containerList = (HashMap)new HashMap<>();
  
  private HashMap<UUID, UUID> containerIsRequire = new HashMap<>();
  
  private ArrayList<UUID> containerIsWaiting = new ArrayList<>();
  
  public PMController(IMethodP iMethodP, IListenerP iListenerP, String messageChannel) {
    this.iMethodP = iMethodP;
    this.iListenerP = iListenerP;
    this.messageChannel = messageChannel;
  }
  
  public String getMessageChannel() {
    return this.messageChannel;
  }
  
  public void load() {
    this.iMethodP.load(this);
  }
  
  public void receivePacket(byte[] data) {
    PMessageHeader pMessageHeader = PMessageHeader.fromByteArrays(data);
    ByteArrayDataInput in = ByteStreams.newDataInput(data, 21);
    byte[] containerData = new byte[data.length - 21];
    in.readFully(containerData);
    UUID informerUUID = null;
    this.containerList.put(pMessageHeader.getUuid(), containerData);
    if (pMessageHeader.isInformer()) {
      ByteArrayDataInput dataHeader = ByteStreams.newDataInput(containerData);
      int size = dataHeader.readInt();
      for (int i = 1; i <= size; i++) {
        UUID containerUUID = new UUID(dataHeader.readLong(), dataHeader.readLong());
        if (this.containerIsWaiting.contains(containerUUID)) {
          this.containerIsWaiting.remove(containerUUID);
        } else {
          this.containerIsRequire.put(containerUUID, pMessageHeader.getUuid());
        } 
      } 
      informerUUID = pMessageHeader.getUuid();
    } else if (this.containerIsRequire.containsKey(pMessageHeader.getUuid())) {
      informerUUID = this.containerIsRequire.get(pMessageHeader.getUuid());
      this.containerIsRequire.remove(pMessageHeader.getUuid());
    } else {
      this.containerIsWaiting.add(pMessageHeader.getUuid());
    } 
    if (informerUUID != null && 
      !this.containerIsRequire.containsValue(informerUUID)) {
      byte[] informerPacket = this.containerList.get(informerUUID);
      ByteArrayDataInput dataHeader = ByteStreams.newDataInput(informerPacket);
      ByteArrayDataOutput dataFinalMessage = ByteStreams.newDataOutput();
      int size = dataHeader.readInt();
      for (int i = 1; i <= size; i++) {
        UUID containerUUID = new UUID(dataHeader.readLong(), dataHeader.readLong());
        dataFinalMessage.write(this.containerList.get(containerUUID));
        this.containerList.remove(containerUUID);
      } 
      this.containerList.remove(informerPacket);
      int idPacket = pMessageHeader.getId();
      PMessageFinal pMessageFinal = PMessageFinal.fromBytesArrays(dataFinalMessage.toByteArray(), idPacket);
      this.iListenerP.receivePMessage(pMessageFinal);
    } 
  }
  
  public static ClassLoader[] getCl(List<ClassLoader> list) {
    return list.<ClassLoader>toArray(new ClassLoader[0]);
  }
  
  public void sendPacket(PMessageFinal pMessageFinal, Object player) throws Exception {
    int pid = pMessageFinal.getId();
    byte[] data = pMessageFinal.toBytesArrays();
    ByteArrayDataInput dataInput = ByteStreams.newDataInput(data);
    if (data.length > 67041282)
      throw new Exception("PaladiumMessage Packet may not be larger than 67041282 bytes"); 
    ArrayList<UUID> idsPacket = new ArrayList<>();
    int finalSize = 0;
    while (finalSize < data.length) {
      UUID uuid = UUID.randomUUID();
      PMessageHeader pMessageHeader1 = new PMessageHeader(pid, uuid, false);
      int packetSize = 32746;
      if (packetSize > data.length - finalSize)
        packetSize = data.length - finalSize; 
      byte[] containerData = new byte[packetSize];
      System.arraycopy(data, finalSize, containerData, 0, packetSize);
      ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
      byteArrayDataOutput.write(pMessageHeader1.toByteArrays());
      byteArrayDataOutput.write(containerData);
      this.iMethodP.sendPacket(this, byteArrayDataOutput.toByteArray(), player);
      idsPacket.add(uuid);
      finalSize += packetSize;
    } 
    UUID informerUUID = UUID.randomUUID();
    PMessageHeader pMessageHeader = new PMessageHeader(pid, informerUUID, true);
    ByteArrayDataOutput out = ByteStreams.newDataOutput();
    out.write(pMessageHeader.toByteArrays());
    out.writeInt(idsPacket.size());
    for (UUID idp : idsPacket) {
      out.writeLong(idp.getMostSignificantBits());
      out.writeLong(idp.getLeastSignificantBits());
    } 
    out.writeInt(finalSize);
    this.iMethodP.sendPacket(this, out.toByteArray(), player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\network\PNetwork\PMController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */