package fr.paladium.palamod.modules.argus.loader;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import com.google.common.io.ByteArrayDataInput;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class ChunkData {
  private int chunkId;
  
  private int chunkCount;
  
  private int chunkSize;
  
  private byte[] data;
  
  public int getChunkId() {
    return this.chunkId;
  }
  
  public int getChunkCount() {
    return this.chunkCount;
  }
  
  public int getChunkSize() {
    return this.chunkSize;
  }
  
  public byte[] getData() {
    return this.data;
  }
  
  public ChunkData(ByteArrayDataInput data) {
    unserialize(data);
  }
  
  public ChunkData(int chunkId, int chunkCount, int chunkSize, byte[] data) {
    this.chunkId = chunkId;
    this.chunkCount = chunkCount;
    this.chunkSize = chunkSize;
    this.data = data;
  }
  
  private void unserialize(ByteArrayDataInput input) {
    this.chunkId = input.readInt();
    this.chunkCount = input.readInt();
    this.chunkSize = input.readInt();
    if (this.chunkSize < 0 || this.chunkSize >= 32000)
      return; 
    this.data = new byte[this.chunkSize];
    input.readFully(this.data);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\loader\ChunkData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */