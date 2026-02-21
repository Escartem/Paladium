package fr.paladium.palarpg.module.dungeon.client.ui.utils;

import fr.paladium.zephyrui.lib.color.Color;

class SynchronizedCursor {
  private final String player;
  
  private final Color color;
  
  private double x;
  
  private double y;
  
  private double targetX;
  
  private double targetY;
  
  public SynchronizedCursor(String player, Color color) {
    this.player = player;
    this.color = color;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\u\\utils\DungeonSynchronizedCursorNode$SynchronizedCursor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */