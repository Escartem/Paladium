package fr.paladium.palapass.server.command.admin;

import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ISubCallback;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.FreeSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.PlayerSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.StringSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.palapass.common.manager.PalapassManager;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.network.data.QuestProgressData;
import fr.paladium.palapass.common.pojo.quest.Quest;
import java.util.Iterator;
import java.util.Optional;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class PalapassSetQuestSubCommand extends ASubCommand {
  public static final String NAME = "quest";
  
  public static final String PERMISSION = "palapass.admin";
  
  public PalapassSetQuestSubCommand() {
    FreeSubCommand.create("(newUUID)", "UUID de la nouvelle quête")
      .build(
        
        (ASubCommand)FreeSubCommand.create("(oldUUID)", "UUID de la quête à remplacer")
        .build(
          
          (ASubCommand)StringSubCommand.create("set")
          .build(
            
            (ASubCommand)PlayerSubCommand.create("(player)")
            .build(this))), 
        
        set());
  }
  
  private ISubCallback set() {
    return (sender, data) -> {
        Optional<EntityPlayerMP> result = data.getTargetedPlayer();
        if (!result.isPresent()) {
          sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cLe joueur spécifié n'est pas connecté."));
          return false;
        } 
        EntityPlayerMP target = result.get();
        PalapassPlayer targetData = PalapassPlayer.get((EntityPlayer)target);
        String oldUUID = data.getFullArgs()[2];
        String newUUID = data.getFullArgs()[3];
        if (oldUUID == null || newUUID == null) {
          sender.func_145747_a((IChatComponent)new ChatComponentText("§cVous devez spécifier l'UUID de la quête à remplacer et l'UUID de la nouvelle quête."));
          return false;
        } 
        Quest oldQuest = PalapassManager.getInstance().getQuestFromUUID(oldUUID);
        Quest newQuest = PalapassManager.getInstance().getQuestFromUUID(newUUID);
        if (oldQuest == null) {
          sender.func_145747_a((IChatComponent)new ChatComponentText("§cL'UUID de l'ancienne quête n'est pas valide."));
          return false;
        } 
        if (newQuest == null) {
          sender.func_145747_a((IChatComponent)new ChatComponentText("§cL'UUID de la nouvelle quête n'est pas valide."));
          return false;
        } 
        Iterator<QuestProgressData> it = targetData.getQuestProgresses().iterator();
        boolean progressFound = false;
        while (it.hasNext() && !progressFound) {
          QuestProgressData next = it.next();
          if (next.getUuid().equalsIgnoreCase(oldQuest.getQuestUUID())) {
            it.remove();
            progressFound = true;
          } 
        } 
        if (!progressFound) {
          sender.func_145747_a((IChatComponent)new ChatComponentText("§cCette quête n'est pas lié au joueur."));
          return true;
        } 
        QuestProgressData newProgress = new QuestProgressData();
        newProgress.setUuid(newUUID);
        newProgress.setDuration(newQuest.getQuestDuration());
        newProgress.setCompleted(false);
        newProgress.setProgress(0);
        targetData.getQuestProgresses().add(newProgress);
        sender.func_145747_a((IChatComponent)new ChatComponentText("§aVous avez bien remplacé §e" + oldQuest.getText() + " §apar §e" + newQuest.getText() + "§a."));
        return true;
      };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\server\command\admin\PalapassSetQuestSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */