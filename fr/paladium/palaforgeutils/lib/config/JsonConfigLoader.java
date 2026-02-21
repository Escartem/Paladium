package fr.paladium.palaforgeutils.lib.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonConfigLoader {
  private static final Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
  
  public static <T> T loadConfig(File file, Type type) throws Exception {
    if (!file.exists()) {
      file.mkdirs();
      file.delete();
    } 
    Path path = file.toPath();
    if (!Files.exists(path, new java.nio.file.LinkOption[0]) && type instanceof Class) {
      T instance = ((Class<T>)type).newInstance();
      Files.write(path, gson.toJson(instance).getBytes(), new java.nio.file.OpenOption[0]);
      return instance;
    } 
    return (T)gson.fromJson(new String(Files.readAllBytes(path)), type);
  }
  
  public static <T> void saveConfig(File file, T config) throws IOException {
    Files.write(file.toPath(), gson.toJson(config).getBytes(), new java.nio.file.OpenOption[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\config\JsonConfigLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */