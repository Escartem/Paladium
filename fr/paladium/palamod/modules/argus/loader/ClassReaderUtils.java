package fr.paladium.palamod.modules.argus.loader;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import com.allatori.annotations.StringEncryption;
import com.allatori.annotations.StringEncryptionType;
import com.google.common.io.ByteArrayDataInput;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.AbstractMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
@StringEncryption("maximum")
@StringEncryptionType("strong")
public class ClassReaderUtils {
  public static Map.Entry<String, byte[]> readClass(ByteArrayDataInput input) throws Exception {
    String fileName = input.readUTF();
    int classLength = input.readInt();
    byte[] encodedBytecode = new byte[classLength];
    input.readFully(encodedBytecode);
    ByteArrayInputStream bis = new ByteArrayInputStream(encodedBytecode);
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    GZIPInputStream gis = new GZIPInputStream(bis);
    byte[] buffer = new byte[1024];
    int readed;
    while ((readed = gis.read(buffer)) > 0)
      bos.write(buffer, 0, readed); 
    byte[] bytecode = bos.toByteArray();
    if (fileName.startsWith("/"))
      fileName = fileName.substring(1); 
    fileName = fileName.replace("/", ".");
    return (Map.Entry)new AbstractMap.SimpleEntry<>(fileName.replace(".class", ""), bytecode);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\loader\ClassReaderUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */