package fr.paladium.palacommunityevent.common.handler.utils;

import cpw.mods.fml.common.network.IGuiHandler;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
  private static List<Handler> handlers = new LinkedList<>();
  
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    return ((Handler)handlers.get(ID)).getServerGuiElement(player, world, x, y, z);
  }
  
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    return ((Handler)handlers.get(ID)).getClientGuiElement(player, world, x, y, z);
  }
  
  public static int registerHandler(Handler handler) {
    int id = handlers.size();
    handlers.add(handler);
    return id;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\handle\\utils\GuiHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */