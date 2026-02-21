package fr.paladium.palaschematicmod.server.async;

import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedSchematic;

public interface PasteCallback {
  void onPasteComplete(TimedSchematic paramTimedSchematic);
  
  default void onPasteProgress(TimedSchematic schematic, int progress, int total) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\server\async\PasteCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */