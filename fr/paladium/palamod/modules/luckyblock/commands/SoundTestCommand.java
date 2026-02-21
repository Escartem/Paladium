package fr.paladium.palamod.modules.luckyblock.commands;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palajobs.utils.forge.player.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.structures.sound.impl.FirstSoundStructure;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class SoundTestCommand extends CommandBase {
  public static final String COMMAND_NAME = "soundtest";
  
  public static final Set<UUID> WAITING_PLAYERS = new HashSet<>();
  
  public static void addWaitingPlayer(EntityPlayer player) {
    WAITING_PLAYERS.add(player.func_110124_au());
  }
  
  public String func_71517_b() {
    return "soundtest";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "soundtest";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (!(sender instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)sender;
    if (player.field_70170_p.field_72995_K)
      return; 
    if (!isWaiting(player)) {
      PlayerUtils.sendMessage(player, new String[] { "§cVous n'êtes pas en attente d'un placement de structure." });
      return;
    } 
    FirstSoundStructure firstSoundStructure = new FirstSoundStructure(-1, new Location((Entity)player), player);
    if (!firstSoundStructure.canSpawn()) {
      PlayerUtils.sendMessage(player, firstSoundStructure.getConditions());
      return;
    } 
    WAITING_PLAYERS.remove(player.func_110124_au());
    firstSoundStructure.spawn();
  }
  
  private boolean isWaiting(EntityPlayer player) {
    return WAITING_PLAYERS.contains(player.func_110124_au());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\commands\SoundTestCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */