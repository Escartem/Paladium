package fr.paladium.palamod.modules.addons.network;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.HashMap;
import java.util.Map;

public class MessageP implements IMessageP {
  private final int id;
  
  private final HashMap<String, String> arguments = new HashMap<>();
  
  public MessageP(int id) {
    this.id = id;
  }
  
  public int getId() {
    return this.id;
  }
  
  public String getValue(String identifier) {
    if (this.arguments.containsKey(identifier))
      return this.arguments.get(identifier); 
    return null;
  }
  
  public void addValue(String identifier, String value) {
    if (identifier == null || value == null)
      throw new NullPointerException(); 
    this.arguments.put(identifier, value);
  }
  
  public void removeValue(String identifier) {
    if (identifier == null)
      throw new NullPointerException(); 
    this.arguments.remove(identifier);
  }
  
  public static MessageP fromString(String str) {
    String[] strr = str.split(";");
    MessageP ret = new MessageP(Integer.parseInt(strr[0]));
    for (int i = 1; i < strr.length; i++) {
      String[] args = strr[i].split(":");
      ret.addValue(args[0], args[1]);
    } 
    return ret;
  }
  
  public String toString() {
    String ret = this.id + ";";
    for (Map.Entry<String, String> entry : this.arguments.entrySet())
      ret = ret + (String)entry.getKey() + ":" + (String)entry.getValue() + ";"; 
    return ret;
  }
  
  public byte[] toBytesArrays() throws Exception {
    ByteArrayDataOutput out = ByteStreams.newDataOutput();
    String ret = this.id + ";";
    for (Map.Entry<String, String> entry : this.arguments.entrySet())
      ret = ret + (String)entry.getKey() + ":" + (String)entry.getValue() + ";"; 
    out.writeUTF(ret);
    if ((out.toByteArray()).length >= 767)
      throw new Exception("Message too long max (767 bytes) actualy " + (out.toByteArray()).length); 
    return out.toByteArray();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\addons\network\MessageP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */