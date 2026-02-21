package fr.paladium.palaforgeutils.lib.monitor.object.primitive;

import fr.paladium.palaforgeutils.lib.monitor.Monitor;

public class StringMonitor extends Monitor<String> {
  public void append(String value) {
    String updatedValue = (String)get() + value;
    set(updatedValue);
    publish();
  }
  
  public void concat(String str) {
    String updatedValue = ((String)get()).concat(str);
    set(updatedValue);
    publish();
  }
  
  public void replace(char oldChar, char newChar) {
    String updatedValue = ((String)get()).replace(oldChar, newChar);
    set(updatedValue);
    publish();
  }
  
  public void replace(CharSequence target, CharSequence replacement) {
    String updatedValue = ((String)get()).replace(target, replacement);
    set(updatedValue);
    publish();
  }
  
  public void toLowerCase() {
    String updatedValue = ((String)get()).toLowerCase();
    set(updatedValue);
    publish();
  }
  
  public void toUpperCase() {
    String updatedValue = ((String)get()).toUpperCase();
    set(updatedValue);
    publish();
  }
  
  public void trim() {
    String updatedValue = ((String)get()).trim();
    set(updatedValue);
    publish();
  }
  
  public void substring(int beginIndex) {
    String updatedValue = ((String)get()).substring(beginIndex);
    set(updatedValue);
    publish();
  }
  
  public void substring(int beginIndex, int endIndex) {
    String updatedValue = ((String)get()).substring(beginIndex, endIndex);
    set(updatedValue);
    publish();
  }
  
  public void intern() {
    String updatedValue = ((String)get()).intern();
    set(updatedValue);
    publish();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\monitor\object\primitive\StringMonitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */