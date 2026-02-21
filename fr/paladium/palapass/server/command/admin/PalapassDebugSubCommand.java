package fr.paladium.palapass.server.command.admin;

import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ISubCallback;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.StringSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.palapass.common.manager.PalapassManager;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.network.data.QuestProgressData;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsDuration;
import fr.paladium.palapass.common.pojo.quest.Quest;
import fr.paladium.palapass.common.pojo.quest.QuestTier;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class PalapassDebugSubCommand extends ASubCommand {
  public static final String NAME = "debug";
  
  public static final String PERMISSION = "palapass.admin";
  
  public PalapassDebugSubCommand() {
    StringSubCommand.create("info", "Obtenir votre Palapass actuel")
      .build(this, info());
    StringSubCommand.create("resetDay", "Reset vos données")
      .build(this, resetDay());
    StringSubCommand.create("resetAll", "Reset vos données")
      .build(this, resetAll());
  }
  
  private ISubCallback info() {
    return (sender, data) -> {
        if (!(sender instanceof EntityPlayer)) {
          sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez être un joueur pour exécuter cette commande."));
          return false;
        } 
        EntityPlayer player = (EntityPlayer)sender;
        PalapassManager manager = PalapassManager.getInstance();
        PalapassPlayer playerData = PalapassPlayer.get(player);
        sender.func_145747_a((IChatComponent)new ChatComponentText(""));
        sender.func_145747_a((IChatComponent)new ChatComponentText(""));
        sender.func_145747_a((IChatComponent)new ChatComponentText("§6Palapass Infos:"));
        sender.func_145747_a((IChatComponent)new ChatComponentText("§eQuêtes journalières:"));
        playerData.getQuestProgresses().stream().filter(()).forEach(());
        sender.func_145747_a((IChatComponent)new ChatComponentText(""));
        sender.func_145747_a((IChatComponent)new ChatComponentText("§6Quêtes de saison:"));
        playerData.getQuestProgresses().stream().filter(()).forEach(());
        return true;
      };
  }
  
  private ISubCallback resetDay() {
    return (sender, data) -> {
        if (!(sender instanceof EntityPlayer)) {
          sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez être un joueur pour exécuter cette commande."));
          return false;
        } 
        PalapassPlayer.get((EntityPlayer)sender).refreshDay();
        sender.func_145747_a((IChatComponent)new ChatComponentText("§aVous avez bien reset votre journée palapass."));
        return true;
      };
  }
  
  private ISubCallback resetAll() {
    return (sender, data) -> {
        if (!(sender instanceof EntityPlayer)) {
          sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez être un joueur pour exécuter cette commande."));
          return false;
        } 
        PalapassPlayer.get((EntityPlayer)sender).refreshSeason();
        sender.func_145747_a((IChatComponent)new ChatComponentText("§aVous avez bien reset votre saison palapass."));
        return true;
      };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\server\command\admin\PalapassDebugSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */