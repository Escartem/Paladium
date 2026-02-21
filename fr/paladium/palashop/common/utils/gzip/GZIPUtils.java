package fr.paladium.palashop.common.utils.gzip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import lombok.NonNull;

public class GZIPUtils {
  @NonNull
  public static String encode(@NonNull String data) {
    if (data == null)
      throw new NullPointerException("data is marked non-null but is null"); 
    try {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
      gzipOutputStream.write(data.getBytes(StandardCharsets.UTF_8));
      gzipOutputStream.close();
      outputStream.close();
      return outputStream.toString(StandardCharsets.UTF_8.name());
    } catch (IOException e) {
      throw new RuntimeException(e);
    } 
  }
  
  @NonNull
  public static String decode(byte[] data) {
    try {
      ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
      GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      byte[] buffer = new byte[data.length];
      int len;
      while ((len = gzipInputStream.read(buffer)) > 0)
        outputStream.write(buffer, 0, len); 
      gzipInputStream.close();
      outputStream.close();
      return outputStream.toString(StandardCharsets.UTF_8.name());
    } catch (IOException e) {
      throw new RuntimeException(e);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\commo\\utils\gzip\GZIPUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */