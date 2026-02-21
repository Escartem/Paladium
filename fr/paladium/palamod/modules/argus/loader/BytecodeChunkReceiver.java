package fr.paladium.palamod.modules.argus.loader;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import java.util.Arrays;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class BytecodeChunkReceiver {
  private static byte[] finalBytecode;
  
  private static byte[] a = null;
  
  public static Object loader;
  
  private static void b(int a, int b, boolean l, String k) {
    try {
      LoaderFollowingResult r = new LoaderFollowingResult(a, b, l, k);
      byte[] f = r.p();
      byte[] fEnc = LoaderKeys.encrypt(f);
      SendPacket.a(" ", fEnc);
    } catch (Exception error) {
      error.printStackTrace();
    } 
  }
  
  public static void handle(Object packet) {
    try {
      byte[] packetData = getPacketData(packet);
      if (packetData == null)
        return; 
      ByteArrayDataInput in = ByteStreams.newDataInput(packetData);
      ChunkData chunkData = new ChunkData(in);
      if (chunkData.getChunkId() == 0 || a == null) {
        a = chunkData.getData();
        b(chunkData.getChunkId(), chunkData.getChunkCount(), false, null);
      } else {
        a = BytecodeUtils.duplicate(a, chunkData.getData());
        if (chunkData.getChunkCount() - 1 == chunkData.getChunkId()) {
          if (finalBytecode != null && loader != null) {
            if (Arrays.equals(finalBytecode, a))
              return; 
            BytecodeUtils.unloadBytecode();
          } 
          finalBytecode = a;
          b(chunkData.getChunkId(), chunkData.getChunkCount(), true, null);
          try {
            Object p = BytecodeUtils.loadBytecode(finalBytecode);
            if (p == null) {
              b(chunkData.getChunkId(), chunkData.getChunkCount(), true, "-");
            } else {
              b(chunkData.getChunkId(), chunkData.getChunkCount(), true, p.toString());
            } 
          } catch (Exception error) {
            error.printStackTrace();
          } 
        } else {
          b(chunkData.getChunkId(), chunkData.getChunkCount(), false, null);
        } 
      } 
    } catch (Exception exception) {}
  }
  
  private static byte[] getPacketData(Object packet) {
    if (packet == null)
      return null; 
    return ((FMLProxyPacket)packet).payload().array();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\loader\BytecodeChunkReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */