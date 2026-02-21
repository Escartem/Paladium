package fr.paladium.palajobs.utils.forge.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public abstract class FlatFileModule<T> {
  private String name;
  
  protected T data;
  
  public String getName() {
    return this.name;
  }
  
  public T getData() {
    return this.data;
  }
  
  public FlatFileModule(String name, T data) {
    this.name = name;
    this.data = data;
  }
  
  public void load() {
    File file = getFile();
    if (!file.exists()) {
      save();
      return;
    } 
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
      this.data = (T)JsonConstants.GSON.fromJson(reader, this.data.getClass());
    } catch (Exception e) {
      throw new RuntimeException("Error while loading " + this.name + " data", e);
    } 
  }
  
  public void save() {
    try (Writer writer = new OutputStreamWriter(new FileOutputStream(getFile()), StandardCharsets.UTF_8)) {
      JsonConstants.GSON.toJson(this.data, writer);
    } catch (Exception e) {
      throw new RuntimeException("Error while saving " + this.name + " data", e);
    } 
  }
  
  public abstract File getFile();
}


/* Location:              E:\Paladium\!\fr\paladium\palajob\\utils\forge\json\FlatFileModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */