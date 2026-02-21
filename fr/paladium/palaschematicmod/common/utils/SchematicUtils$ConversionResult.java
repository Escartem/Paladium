package fr.paladium.palaschematicmod.common.utils;

import fr.paladium.palaschematicmod.common.pojo.schematic.Schematic;

public class ConversionResult {
  private final boolean success;
  
  private final String message;
  
  private final Schematic schematic;
  
  private final int processedBlocks;
  
  private final int totalBlocks;
  
  public boolean isSuccess() {
    return this.success;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public Schematic getSchematic() {
    return this.schematic;
  }
  
  public int getProcessedBlocks() {
    return this.processedBlocks;
  }
  
  public int getTotalBlocks() {
    return this.totalBlocks;
  }
  
  public ConversionResult(boolean success, String message, Schematic schematic, int processedBlocks, int totalBlocks) {
    this.success = success;
    this.message = message;
    this.schematic = schematic;
    this.processedBlocks = processedBlocks;
    this.totalBlocks = totalBlocks;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\commo\\utils\SchematicUtils$ConversionResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */