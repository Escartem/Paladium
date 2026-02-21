package fr.paladium.palatrade.lib.odin.modules.command.lib;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public enum SenderType {
  PLAYER((Class)EntityPlayer.class),
  CONSOLE((Class)MinecraftServer.class);
  
  private Class<? extends ICommandSender> sender;
  
  SenderType(Class<? extends ICommandSender> sender) {
    this.sender = sender;
  }
  
  public boolean isAllowed(ICommandSender sender) {
    return this.sender.isAssignableFrom(sender.getClass());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\command\lib\SenderType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */