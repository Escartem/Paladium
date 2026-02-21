package fr.paladium.palaforgeutils.lib.guihandler;

import cpw.mods.fml.common.network.IGuiHandler;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CustomGuiHandler implements IGuiHandler {
  private List<GHandler> handlers = new LinkedList<>();
  
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    return ((GHandler)this.handlers.get(ID)).getServerGuiElement(player, world, x, y, z);
  }
  
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    return ((GHandler)this.handlers.get(ID)).getClientGuiElement(player, world, x, y, z);
  }
  
  public int registerHandler(GHandler handler) {
    int id = this.handlers.size();
    this.handlers.add(handler);
    return id;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\guihandler\CustomGuiHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */