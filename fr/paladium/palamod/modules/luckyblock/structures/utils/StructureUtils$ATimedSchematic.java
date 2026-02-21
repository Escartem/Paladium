package fr.paladium.palamod.modules.luckyblock.structures.utils;

import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedSchematic;

public abstract class ATimedSchematic {
  private final TimedSchematic timedSchematic;
  
  private final StructureUtils.ITimedCallBack<TimedSchematic> callback;
  
  public ATimedSchematic(TimedSchematic timedSchematic, StructureUtils.ITimedCallBack<TimedSchematic> callback) {
    this.timedSchematic = timedSchematic;
    this.callback = callback;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structure\\utils\StructureUtils$ATimedSchematic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */