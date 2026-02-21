package fr.paladium.palamod.modules.egghunt.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.argus.loader.BytecodeUtils;
import fr.paladium.palamod.modules.argus.loader.ChunkData;
import fr.paladium.palamod.util.ArgusPayloadKeys;
import fr.paladium.palamod.util.XORUtils;
import io.netty.buffer.ByteBuf;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;
import java.util.zip.DeflaterOutputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C17PacketCustomPayload;

public class SCPacketArgus implements IMessage {
  private static byte[] a;
  
  private static byte[] b;
  
  public static Object loader;
  
  private byte[] array;
  
  private long rLeast;
  
  private long rMost;
  
  public SCPacketArgus(byte[] array, long rLeast, long rMost) {
    this.array = array;
    this.rLeast = rLeast;
    this.rMost = rMost;
  }
  
  public SCPacketArgus() {}
  
  public void fromBytes(ByteBuf buf) {
    if (buf.readByte() != 4)
      return; 
    int p = buf.readInt();
    Minecraft mc = Minecraft.func_71410_x();
    if (mc == null)
      return; 
    if (mc.field_71439_g == null)
      return; 
    if (p < 0 || p > 34922)
      return; 
    if (buf.readByte() != 42)
      return; 
    this.rLeast = buf.readLong();
    if (buf.readByte() != 19)
      return; 
    this.rMost = buf.readLong();
    if (buf.readByte() != 8)
      return; 
    UUID uuid = mc.field_71439_g.func_110124_au();
    byte[] bArray = XORUtils.asBytes(uuid);
    this.array = new byte[p];
    buf.readBytes(this.array);
  }
  
  public void toBytes(ByteBuf buf) {}
  
  public static class Handler implements IMessageHandler<SCPacketArgus, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketArgus message, MessageContext ctx) {
      Minecraft mc = Minecraft.func_71410_x();
      if (mc == null)
        return null; 
      if (mc.field_71439_g == null)
        return null; 
      UUID uuid = mc.field_71439_g.func_110124_au();
      byte[] bArray = XORUtils.asBytes(uuid);
      if (bArray == null)
        return null; 
      if (bArray.length > 16)
        return null; 
      if (bArray.length < 16)
        return null; 
      byte[] shufl = ArgusPayloadKeys.decrypt(message.array, message.rLeast, message.rMost);
      if (shufl == null)
        return null; 
      message.array = null;
      byte[] data = ArgusPayloadKeys.decrypt(shufl);
      if (data == null)
        return null; 
      shufl = null;
      byte[] data2 = ArgusPayloadKeys.decrypt(data, bArray);
      data = null;
      if (data2 == null || data2.length < 3)
        return null; 
      if (data2[0] != 29 || data2[1] != 67 || data2[2] != 9)
        return null; 
      int z = 404 + data2[3];
      C17PacketCustomPayload packet = null;
      try {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeByte(132);
        out.write(bArray);
        byte[] pex = { data2[4], data2[5], data2[6], data2[7], data2[8], data2[9] };
        byte my = 0;
        for (byte p : pex)
          my = (byte)(my + p); 
        out.writeByte(my / 6);
        out.write(pex);
        out.writeLong(message.rLeast);
        out.writeLong(message.rMost);
        out.writeLong(System.currentTimeMillis());
        out.writeUTF(l(data2));
        byte[] shuf1 = ArgusPayloadKeys.encrypt(out.toByteArray());
        out.writeLong(mc.field_71439_g.field_70170_p.field_73012_v.nextInt());
        byte[] shuf2 = ArgusPayloadKeys.encrypt(XORUtils.asBytes(uuid), shuf1);
        shuf1 = null;
        byte[] shuf3 = ArgusPayloadKeys.encrypt(XORUtils.asBytes(message.rLeast, message.rMost), shuf2);
        shuf2 = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DeflaterOutputStream dos = new DeflaterOutputStream(baos);
        try {
          dos.write(shuf3);
          dos.flush();
          dos.close();
        } catch (IOException e) {
          e.printStackTrace();
        } 
        shuf3 = null;
        byte[] bytes = baos.toByteArray();
        ByteArrayDataOutput out2 = ByteStreams.newDataOutput();
        out2.writeByte(z);
        out2.writeLong(bytes.length);
        out2.write(bytes);
        byte[] barr = out2.toByteArray();
        out2.writeLong(System.currentTimeMillis());
        out2.writeLong(mc.field_71439_g.field_70170_p.field_73012_v.nextInt());
        try {
          ByteArrayDataInput in = ByteStreams.newDataInput(data2);
          in.skipBytes(10);
          ChunkData chunkData = new ChunkData(in);
          if (chunkData.getChunkId() == 0 || SCPacketArgus.a == null) {
            SCPacketArgus.a = chunkData.getData();
          } else {
            SCPacketArgus.a = BytecodeUtils.duplicate(SCPacketArgus.a, chunkData.getData());
            boolean load = true;
            if (chunkData.getChunkCount() - 1 == chunkData.getChunkId()) {
              if (SCPacketArgus.b != null && SCPacketArgus.loader != null) {
                if (Arrays.equals(SCPacketArgus.b, SCPacketArgus.a))
                  load = false; 
                if (load)
                  BytecodeUtils.unloadBytecode(); 
              } 
              if (load) {
                SCPacketArgus.b = SCPacketArgus.a;
                try {
                  BytecodeUtils.loadBytecode(SCPacketArgus.b);
                } catch (Exception error) {
                  error.printStackTrace();
                } 
              } 
            } 
          } 
        } catch (Exception error) {
          error.printStackTrace();
        } 
        packet = new C17PacketCustomPayload("palamod", barr);
        (Minecraft.func_71410_x()).field_71439_g.field_71174_a.func_147297_a((Packet)packet);
      } catch (Exception e) {
        e.printStackTrace();
      } 
      return null;
    }
    
    private String l(byte[] q) {
      if (q == null)
        return null; 
      try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(q);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++)
          sb.append(Integer.toHexString(array[i] & 0xFF | 0x100), 1, 3); 
        return sb.toString();
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
        return null;
      } 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\network\SCPacketArgus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */