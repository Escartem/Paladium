package fr.paladium.palaforgeutils.lib.java.shortcut;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Shortcut {
  public static boolean isKeyDown(int key) {
    return (key < 0) ? Mouse.isButtonDown(key + 100) : Keyboard.isKeyDown(key);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\java\shortcut\Shortcut.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */