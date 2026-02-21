package fr.paladium.pet.common.pet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PetAdditionalData {
  public void setFish(boolean fish) {
    this.fish = fish;
  }
  
  public void setUiPosition(ElementPosition uiPosition) {
    this.uiPosition = uiPosition;
  }
  
  public void setShoulderPosition(ElementPosition shoulderPosition) {
    this.shoulderPosition = shoulderPosition;
  }
  
  public void setScaleFactor(ElementPosition scaleFactor) {
    this.scaleFactor = scaleFactor;
  }
  
  private static final Gson GSON = (new GsonBuilder()).serializeNulls().setPrettyPrinting().create();
  
  public static final ElementPosition DEFAULT_SCALE_FACTOR = ElementPosition.of(0.6D, 0.6D, 0.6D);
  
  public static final ElementPosition DEFAULT_UI_POSITION = ElementPosition.of(0.0D, 0.0D, 0.0D);
  
  public static final ElementPosition DEFAULT_SHOULDER_POSITION = ElementPosition.of(-3.0D, -1.2D, -0.25D);
  
  private final String name;
  
  private boolean fish;
  
  private ElementPosition uiPosition;
  
  private ElementPosition shoulderPosition;
  
  private ElementPosition scaleFactor;
  
  public String getName() {
    return this.name;
  }
  
  public boolean isFish() {
    return this.fish;
  }
  
  public ElementPosition getUiPosition() {
    return this.uiPosition;
  }
  
  public ElementPosition getShoulderPosition() {
    return this.shoulderPosition;
  }
  
  public ElementPosition getScaleFactor() {
    return this.scaleFactor;
  }
  
  public PetAdditionalData(String name) {
    this.name = name;
    this.uiPosition = DEFAULT_UI_POSITION;
    this.shoulderPosition = DEFAULT_SHOULDER_POSITION;
    this.scaleFactor = DEFAULT_SCALE_FACTOR;
  }
  
  public boolean read(InputStream inputStream) {
    InputStreamReader reader = new InputStreamReader(inputStream);
    PetAdditionalData data = (PetAdditionalData)GSON.fromJson(reader, PetAdditionalData.class);
    if (data == null || !this.name.equalsIgnoreCase(data.getName()))
      return false; 
    this.fish = data.isFish();
    this.uiPosition = (data.getUiPosition() == null) ? DEFAULT_UI_POSITION : data.getUiPosition();
    this.shoulderPosition = (data.getShoulderPosition() == null) ? DEFAULT_SHOULDER_POSITION : data.getShoulderPosition();
    this.scaleFactor = (data.getScaleFactor() == null) ? DEFAULT_SCALE_FACTOR : data.getScaleFactor();
    return true;
  }
  
  public PetAdditionalData(String name, PetAdditionalData fromFile) {
    this.name = name;
    if (fromFile == null) {
      this.uiPosition = DEFAULT_UI_POSITION;
      this.shoulderPosition = DEFAULT_SHOULDER_POSITION;
      this.scaleFactor = DEFAULT_SCALE_FACTOR;
    } else {
      this.uiPosition = (fromFile.getUiPosition() == null) ? DEFAULT_UI_POSITION : fromFile.getUiPosition();
      this.shoulderPosition = (fromFile.getShoulderPosition() == null) ? DEFAULT_SHOULDER_POSITION : fromFile.getShoulderPosition();
      this.scaleFactor = (fromFile.getScaleFactor() == null) ? DEFAULT_SCALE_FACTOR : fromFile.getScaleFactor();
    } 
  }
  
  public static PetAdditionalData of(String name) {
    return new PetAdditionalData(name);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\pet\PetAdditionalData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */