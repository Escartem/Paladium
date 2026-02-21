package fr.paladium.palamod.modules.factions.utils;

import com.google.gson.Gson;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.IOUtils;

public class JsonUtils {
  private static final Gson gson = new Gson();
  
  public static Object getFileObject(String folder, String fileName, Type clazz) throws Exception {
    String content = new String(Files.readAllBytes(Paths.get(folder + File.separator + fileName, new String[0])));
    return gson.fromJson(content, clazz);
  }
  
  public static Object getObjectFromInputStream(InputStream inputStream, Type clazz) throws Exception {
    return gson.fromJson(IOUtils.toString(inputStream), clazz);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\faction\\utils\JsonUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */