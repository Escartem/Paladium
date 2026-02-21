package fr.paladium.palaforgeutils.lib.nbt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.jpountz.lz4.LZ4Factory;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

public final class FastNBT {
  private static final LZ4Factory LZ4 = LZ4Factory.fastestInstance();
  
  public static byte[] compress(NBTTagCompound tag) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    CompressedStreamTools.func_74800_a(tag, dos);
    dos.close();
    byte[] uncompressed = baos.toByteArray();
    int maxCompressedLength = LZ4.fastCompressor().maxCompressedLength(uncompressed.length);
    byte[] compressed = new byte[4 + maxCompressedLength];
    compressed[0] = (byte)(uncompressed.length >>> 24);
    compressed[1] = (byte)(uncompressed.length >>> 16);
    compressed[2] = (byte)(uncompressed.length >>> 8);
    compressed[3] = (byte)uncompressed.length;
    int compressedSize = LZ4.fastCompressor().compress(uncompressed, 0, uncompressed.length, compressed, 4, maxCompressedLength);
    byte[] result = new byte[4 + compressedSize];
    System.arraycopy(compressed, 0, result, 0, result.length);
    return result;
  }
  
  public static NBTTagCompound decompress(byte[] compressed) throws IOException {
    int originalLength = (compressed[0] & 0xFF) << 24 | (compressed[1] & 0xFF) << 16 | (compressed[2] & 0xFF) << 8 | compressed[3] & 0xFF;
    byte[] restored = new byte[originalLength];
    LZ4.safeDecompressor().decompress(compressed, 4, compressed.length - 4, restored, 0, originalLength);
    return CompressedStreamTools.func_74794_a(new DataInputStream(new ByteArrayInputStream(restored)));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\nbt\FastNBT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */