package fr.paladium.palamod.util.network.PNetwork;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.HashMap;
import java.util.Map;

public class PMessageFinal implements IMessageP {
  private int id;
  
  private HashMap<String, String> arguments = new HashMap<>();
  
  public PMessageFinal(int id) {
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
  
  public HashMap<String, String> getAllData() {
    return this.arguments;
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
  
  public static PMessageFinal fromString(String str) {
    String[] strr = str.split("\\(;\\)");
    PMessageFinal ret = new PMessageFinal(Integer.valueOf(strr[0]).intValue());
    for (int i = 1; i < strr.length; i++) {
      String[] args = strr[i].split("\\(:\\)");
      ret.addValue(args[0], args[1]);
    } 
    return ret;
  }
  
  public String toString() {
    String ret = this.id + "(;)";
    for (Map.Entry<String, String> entry : this.arguments.entrySet())
      ret = ret + (String)entry.getKey() + "(:)" + (String)entry.getValue() + "(;)"; 
    return ret;
  }
  
  public static PMessageFinal fromBytesArrays(byte[] data, int id) {
    ByteArrayDataInput byteArrayDataInput = ByteStreams.newDataInput(data);
    int arguments_size = byteArrayDataInput.readInt();
    PMessageFinal messageP = new PMessageFinal(id);
    for (int i = 1; i <= arguments_size; i++) {
      String key = byteArrayDataInput.readUTF();
      String value = byteArrayDataInput.readUTF();
      messageP.addValue(key, value);
    } 
    return messageP;
  }
  
  public byte[] toBytesArrays() {
    ByteArrayDataOutput out = ByteStreams.newDataOutput();
    out.writeInt(this.arguments.size());
    for (Map.Entry<String, String> entry : this.arguments.entrySet()) {
      out.writeUTF(entry.getKey());
      out.writeUTF(entry.getValue());
    } 
    return out.toByteArray();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\network\PNetwork\PMessageFinal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */