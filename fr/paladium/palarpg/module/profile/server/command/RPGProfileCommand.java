package fr.paladium.palarpg.module.profile.server.command;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.validator.impl.minecraft.EntityValidator.Online;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.number.NumberValidator.Greater;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.profile.client.ui.UIRPGProfile;
import fr.paladium.palarpg.module.profile.common.network.SCPacketSyncSkillTrees;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import fr.paladium.palarpg.module.profile.common.skilltree.manager.RPGSkillTreeManager;
import fr.paladium.palarpg.module.profile.server.manager.RPGLevelManager;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

public class RPGProfileCommand {
  @SubCommand(command = "/rpg profile", description = "Consulter votre profil RPG", sender = {SenderType.PLAYER})
  public void profile(CommandContext context) {
    if (!PalaRPGMod.proxy.isDungeonWorld()) {
      context.error("Vous devez être dans un donjon pour utiliser cette commande.");
      return;
    } 
    RPGStatPlayerData statData = (RPGStatPlayerData)RPGPlayer.getData((Entity)context.getPlayer(), "stats");
    if (statData != null)
      statData.sync(); 
    (new SCPacketSyncSkillTrees()).send(context.getPlayer());
    ZUIServer.open(UIRPGProfile.class, context.getPlayer());
  }
  
  @SubCommand(command = "/rpg profil", description = "Consulter votre profil RPG", sender = {SenderType.PLAYER})
  public void profil(CommandContext context) {
    profile(context);
  }
  
  @SubCommand(command = "/rpg profile reloadSkillTree", description = "Recharger la configuration du skill tree", permission = "rpg.command.admin.use")
  public void reloadSkillTree(CommandContext context) throws Exception {
    RPGSkillTreeManager.load();
  }
  
  @SubCommand(command = "/rpg profile giveSkillPoint <number>", description = "Donner un point de compétence", permission = "rpg.command.admin.use", sender = {SenderType.PLAYER})
  public void giveSkillPoints(CommandContext context, @Greater(0.0D) int skillPoints) throws Exception {
    RPGProfilePlayerData pData = (RPGProfilePlayerData)RPGPlayer.getData((Entity)context.getPlayer(), "profile");
    if (pData == null)
      return; 
    pData.addSkillPoints(skillPoints);
    pData.sync();
  }
  
  @SubCommand(command = "/rpg profile details [<player>]", description = "Afficher les informations du profil", permission = "rpg.command.admin.use")
  public void profileDetails(CommandContext context, @Online Optional<EntityPlayerMP> player) {
    if (!player.isPresent() && context.getType() == SenderType.CONSOLE) {
      context.error("Vous devez spécifier un joueur en ligne lorsque vous utilisez la console");
      return;
    } 
    EntityPlayerMP targetPlayer = player.orElse(context.getPlayer());
    RPGProfilePlayerData playerProfile = (RPGProfilePlayerData)RPGPlayer.getData((Entity)targetPlayer, "profile");
    if (playerProfile == null) {
      context.error("Le joueur " + targetPlayer.func_70005_c_() + " n'a pas de profil");
      return;
    } 
    context.success("totalExp " + playerProfile.getTotalExp() + " level " + playerProfile.getLevel());
  }
  
  @SubCommand(command = "/rpg profile addexp <experience> [<player>]", description = "Donner de l'experience RPG", permission = "rpg.command.admin.use")
  public void addExp(CommandContext context, @Greater(0.0D) double experience, @Online Optional<EntityPlayerMP> player) {
    if (!player.isPresent() && context.getType() == SenderType.CONSOLE) {
      context.error("Vous devez spécifier un joueur en ligne lorsque vous utilisez la console");
      return;
    } 
    EntityPlayerMP targetPlayer = player.orElse(context.getPlayer());
    RPGLevelManager.addExperience((EntityLivingBase)targetPlayer, experience);
    context.success("Vous avez donné " + experience + " d'expérience à " + targetPlayer.func_70005_c_());
  }
  
  @SubCommand(command = "/rpg profile setlevel <level> [<player>]", description = "Définir le niveau RPG", permission = "rpg.command.admin.use")
  public void setLevel(CommandContext context, @Greater(0.0D) int level, @Online Optional<EntityPlayerMP> player) {
    if (!player.isPresent() && context.getType() == SenderType.CONSOLE) {
      context.error("Vous devez spécifier un joueur en ligne lorsque vous utilisez la console");
      return;
    } 
    EntityPlayerMP targetPlayer = player.orElse(context.getPlayer());
    RPGLevelManager.setLevel(targetPlayer, level);
    context.success("Vous avez défini le niveau de " + targetPlayer.func_70005_c_() + " à " + level);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\server\command\RPGProfileCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */