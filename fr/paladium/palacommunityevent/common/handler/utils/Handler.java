package fr.paladium.palacommunityevent.common.handler.utils;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public abstract class Handler {
  public abstract Container getServerGuiElement(EntityPlayer paramEntityPlayer, World paramWorld, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract GuiScreen getClientGuiElement(EntityPlayer paramEntityPlayer, World paramWorld, int paramInt1, int paramInt2, int paramInt3);
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\common\handle\\utils\Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */