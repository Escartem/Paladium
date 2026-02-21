package fr.paladium.palamod.modules.addons.network;

public interface IMessageP {
  int getId();
  
  String getValue(String paramString);
  
  void addValue(String paramString1, String paramString2);
  
  void removeValue(String paramString);
  
  static IMessageP fromString(String str) {
    return null;
  }
  
  String toString();
  
  byte[] toBytesArrays() throws Exception;
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\addons\network\IMessageP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */