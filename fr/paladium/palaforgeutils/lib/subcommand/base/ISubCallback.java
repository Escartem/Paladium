package fr.paladium.palaforgeutils.lib.subcommand.base;

import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import net.minecraft.command.ICommandSender;

public interface ISubCallback {
  boolean accept(ICommandSender paramICommandSender, CommandData paramCommandData);
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\subcommand\base\ISubCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */