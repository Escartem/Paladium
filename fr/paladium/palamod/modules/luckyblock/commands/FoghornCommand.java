package fr.paladium.palamod.modules.luckyblock.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.BungeeCordPlugin;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import fr.paladium.palamod.util.FastUUID;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FoghornCommand extends CommandBase {
  public String func_71517_b() {
    return "foghorn";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "foghorn";
  }
  
  public List func_71514_a() {
    return Arrays.asList(new String[0]);
  }
  
  public void func_71515_b(ICommandSender sender, String[] arguments) {
    if (sender instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)sender;
      String playerId = FastUUID.toString((Entity)player);
      if (UsersManager.getFoghornAllowlist().containsKey(playerId)) {
        BungeeCordPlugin.BungeeCordCommandTPServerCoords bungeeCordCommandTPServerCoords = (BungeeCordPlugin.BungeeCordCommandTPServerCoords)UsersManager.getFoghornAllowlist().get(playerId);
        UsersManager.getFoghornAllowlist().remove(playerId);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(bungeeCordCommandTPServerCoords.getServer());
        Player bukkitPlayer = Bukkit.getPlayer(playerId);
        if (bukkitPlayer != null) {
          bukkitPlayer.sendPluginMessage((Plugin)PLuckyBlock.bungeeCordPlugin, "BungeeCord", out.toByteArray());
          out = ByteStreams.newDataOutput();
          out.writeUTF("Forward");
          out.writeUTF(bungeeCordCommandTPServerCoords.getServer());
          out.writeUTF("TPCoords");
          ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
          DataOutputStream msgout = new DataOutputStream(msgbytes);
          try {
            msgout.writeChars(playerId);
            msgout.writeDouble(bungeeCordCommandTPServerCoords.getX());
            msgout.writeDouble(bungeeCordCommandTPServerCoords.getY());
            msgout.writeDouble(bungeeCordCommandTPServerCoords.getZ());
          } catch (Exception exception) {}
          out.writeShort((msgbytes.toByteArray()).length);
          out.write(msgbytes.toByteArray());
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\commands\FoghornCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */