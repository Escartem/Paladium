package fr.paladium.palaforgeutils.lib.command;

import lombok.NonNull;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.rcon.RConConsoleSource;
import net.minecraft.server.MinecraftServer;

public enum SenderType {
  PLAYER((Class)EntityPlayer.class),
  CONSOLE((Class)MinecraftServer.class),
  COMMAND_BLOCK((Class)CommandBlockLogic.class),
  RCON((Class)RConConsoleSource.class),
  ALL(null),
  NONE(null);
  
  private final Class<? extends ICommandSender> sender;
  
  SenderType(Class<? extends ICommandSender> sender) {
    this.sender = sender;
  }
  
  public boolean isAllowed(@NonNull ICommandSender sender) {
    if (sender == null)
      throw new NullPointerException("sender is marked non-null but is null"); 
    if (this.sender == null)
      return true; 
    return this.sender.isAssignableFrom(sender.getClass());
  }
  
  @NonNull
  public static SenderType get(@NonNull ICommandSender sender) {
    if (sender == null)
      throw new NullPointerException("sender is marked non-null but is null"); 
    for (SenderType type : values()) {
      if (type.isAllowed(sender) && type != ALL)
        return type; 
    } 
    return ALL;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\SenderType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */