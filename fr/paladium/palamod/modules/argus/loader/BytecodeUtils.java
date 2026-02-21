package fr.paladium.palamod.modules.argus.loader;

import com.allatori.annotations.ControlFlowObfuscation;
import com.allatori.annotations.ExtensiveFlowObfuscation;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.HashMap;
import java.util.Map;

@ControlFlowObfuscation("enable")
@ExtensiveFlowObfuscation("maximum")
public class BytecodeUtils {
  public static byte[] duplicate(byte[] a, byte[] b) {
    ByteArrayDataOutput output = ByteStreams.newDataOutput();
    output.write(a);
    output.write(b);
    return output.toByteArray();
  }
  
  public static Object loadBytecode(byte[] finalBytecode) throws Exception {
    try {
      byte[] data = LoaderKeys.decrypt(finalBytecode);
      assert data != null;
      ByteArrayDataInput input = ByteStreams.newDataInput(data);
      int fileCount = input.readInt();
      Map<String, byte[]> classes = (Map)new HashMap<>();
      for (int i = 0; i < fileCount; i++) {
        Map.Entry<String, byte[]> clazzData = ClassReaderUtils.readClass(input);
        classes.put(clazzData.getKey(), clazzData.getValue());
      } 
      ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
      ByteClassLoader classLoader = new ByteClassLoader(currentClassLoader, classes);
      classes.keySet().forEach(classLoader::findClass);
      Object r = classLoader.findClass("u").getDeclaredMethod("a", new Class[0]).invoke(null, new Object[0]);
      BytecodeChunkReceiver.loader = classLoader;
      return r;
    } catch (ClassFormatError ignored) {
      return null;
    } 
  }
  
  public static void unloadBytecode() {
    try {
      ((ByteClassLoader)BytecodeChunkReceiver.loader).findClass("u").getDeclaredMethod("z", new Class[0])
        .invoke(null, new Object[0]);
    } catch (Exception error) {
      error.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\loader\BytecodeUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */