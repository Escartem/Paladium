package fr.paladium.palamod.modules.paladium.commands;

import cpw.mods.fml.common.registry.GameData;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

public class DumpDataIds implements ICommand {
  public String func_71517_b() {
    return "dumpdataids";
  }
  
  public String func_71518_a(ICommandSender p_71518_1_) {
    return "dumpdataids";
  }
  
  public List func_71514_a() {
    return null;
  }
  
  public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) {
    GameData.GameDataSnapshot snapshot = GameData.buildItemDataList();
    String ret = "";
    for (Map.Entry<String, Integer> entry : (Iterable<Map.Entry<String, Integer>>)snapshot.idMap.entrySet())
      ret = ret + (String)entry.getKey() + "(:)" + entry.getValue() + "(;)"; 
    File export = new File("dataIds.dump");
    if (export.exists())
      export.delete(); 
    try {
      OutputStream outputStream = new FileOutputStream(export);
      outputStream.write(ret.getBytes());
      outputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    } 
    System.out.println("Exports DataIds success !");
  }
  
  public boolean func_71519_b(ICommandSender p_71519_1_) {
    if (p_71519_1_ instanceof net.minecraft.entity.player.EntityPlayer)
      return false; 
    return true;
  }
  
  public List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_) {
    ArrayList<String> list = new ArrayList<>();
    return list;
  }
  
  public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
    return false;
  }
  
  public int compareTo(Object o) {
    return 0;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\commands\DumpDataIds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */