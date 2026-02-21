package fr.paladium.palajobs.server.command;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.packets.server.SCOpenUiJobsHome;
import fr.paladium.palajobs.core.quest.AbstractQuest;
import fr.paladium.palajobs.core.quest.QuestPlayerData;
import fr.paladium.palajobs.core.quest.QuestRegistry;
import fr.paladium.palajobs.core.tokens.LvlToken;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palajobs.utils.forge.player.PlayerUtils;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandJobs extends CommandBase {
  public String func_71517_b() {
    return "palajobs";
  }
  
  public String func_71518_a(ICommandSender p_71518_1_) {
    return "palajobs";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (!(sender instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)sender;
    if (args.length == 1 && args[0].equalsIgnoreCase("opengui")) {
      PalaJobs.proxy.getNetwork().sendTo((IMessage)new SCOpenUiJobsHome(), (EntityPlayerMP)player);
      return;
    } 
    if (!JobsManager.hasBukkitPermission(player.func_110124_au(), "jobs.cmd"))
      return; 
    if (args.length == 0) {
      help(player);
      return;
    } 
    if (processJobTarget((ICommandSender)player, args))
      return; 
    if (args.length >= 1 && args[0].equalsIgnoreCase("token")) {
      JobType job = JobType.getByName(args[1]);
      int lvl = Integer.parseInt(args[2]);
      LvlToken lvlToken = new LvlToken(lvl, job);
      JobsManager.getInstance().generateLvlTokenRewards(lvlToken);
      return;
    } 
    if (args.length >= 1 && args[0].equalsIgnoreCase("boss")) {
      if (args.length >= 5 && args[1].equalsIgnoreCase("position")) {
        try {
          int x = Integer.parseInt(args[2]);
          int y = Integer.parseInt(args[3]);
          int z = Integer.parseInt(args[4]);
          Location location = new Location(player.field_70170_p, x, y, z);
          PlayerUtils.sendMessage(player, new String[] { "La position du boss a été définie en " + location.toString() });
        } catch (Exception e) {
          PlayerUtils.sendMessage(player, new String[] { "La commande est invalide" });
        } 
        return;
      } 
      if (args.length >= 3 && args[1].equalsIgnoreCase("life")) {
        try {
          int health = Integer.parseInt(args[2]);
          PlayerUtils.sendMessage(player, new String[] { "La vie du boss a été définie a " + health });
        } catch (Exception e) {
          PlayerUtils.sendMessage(player, new String[] { "La commande est invalide" });
        } 
        return;
      } 
      if (args.length >= 2 && args[1].equalsIgnoreCase("info"))
        return; 
    } 
    PlayerUtils.sendMessage(player, new String[] { "/jobs boss [position x y z] | [life x] | [info]" });
  }
  
  private void help(EntityPlayer player) {
    PlayerUtils.sendMessage(player, new String[] { "§e/jobs target <player> <job> <reset|set|add|decrease|setlevel|setquest> [value]" });
  }
  
  public boolean processJobTarget(ICommandSender sender, String[] args) {
    if (!(sender instanceof EntityPlayer))
      return false; 
    EntityPlayer player = (EntityPlayer)sender;
    if (args.length >= 1 && args[0].equalsIgnoreCase("target")) {
      if (args.length < 2) {
        PlayerUtils.sendMessage(player, new String[] { "§eLa commande est : /jobs target <player> <job> <reset|set|add|decrease|setlevel|setquest|requirement> [value]" });
        return true;
      } 
      EntityPlayer target = PlayerUtils.getPlayer(player.field_70170_p, args[1]);
      if (target == null) {
        PlayerUtils.sendMessage(player, new String[] { "§cLe joueur spécifié est introuvable." });
        return true;
      } 
      JobsPlayer data = JobsPlayer.get((Entity)target);
      if (data == null) {
        PlayerUtils.sendMessage(player, new String[] { "§cLe joueur spécifié n'a pas de données." });
        return true;
      } 
      if (args.length < 3) {
        PlayerUtils.sendMessage(player, new String[] { "§cVeuillez spécifier un métier." });
        return true;
      } 
      JobType job = JobType.getByName(args[2]);
      if (job == null) {
        for (JobType j : JobType.values()) {
          if (j.toString().toLowerCase().equalsIgnoreCase(args[2].toLowerCase()))
            job = j; 
        } 
        if (job == null) {
          PlayerUtils.sendMessage(player, new String[] { "§cLe métier spécifié est introuvable." });
          return true;
        } 
      } 
      if (args.length == 4 && args[3].equalsIgnoreCase("level")) {
        PlayerUtils.sendMessage(player, new String[] { "§eInformations métier de §6" + args[1] + "§e:" });
        for (JobType j : JobType.values()) {
          PlayerUtils.sendMessage(player, new String[] { "§7 - §6" + j.getName() + "§7: Lvl. " + data.getLevel(j) });
        } 
        return true;
      } 
      if (args.length < 4) {
        PlayerUtils.sendMessage(player, new String[] { "§cVeuillez spécifier une action: reset, set, add, decrease, addtoken" });
        return true;
      } 
      if (args[3].equalsIgnoreCase("reset")) {
        data.resetExperience(job);
        PlayerUtils.sendMessage(player, new String[] { "§aLe métier " + job
              .getName() + " a été réinitialisé pour " + target.getDisplayName() });
        return true;
      } 
      long value = -1L;
      if (args[3].equalsIgnoreCase("set") || args[3].equalsIgnoreCase("add") || args[3]
        .equalsIgnoreCase("decrease") || args[3].equalsIgnoreCase("setlevel") || args[3]
        .equalsIgnoreCase("setquest") || args[3].equalsIgnoreCase("addtoken") || args[3].equalsIgnoreCase("requirement")) {
        if (args.length < 5) {
          PlayerUtils.sendMessage(player, new String[] { "§cVeuillez spécifier une valeur." });
          return true;
        } 
        try {
          value = Long.parseLong(args[4]);
        } catch (Exception e) {
          PlayerUtils.sendMessage(player, new String[] { "§cLa valeur spécifiée est invalide." });
          return true;
        } 
      } 
      if (value < 0L) {
        PlayerUtils.sendMessage(player, new String[] { "§cLa valeur spécifiée est invalide" });
        return true;
      } 
      if (args[3].equalsIgnoreCase("set")) {
        data.setExperience(job, value);
        PlayerUtils.sendMessage(player, new String[] { "§aLe métier " + job.getName() + " a été défini a " + value + " pour " + target
              .getDisplayName() });
        return true;
      } 
      if (args[3].equalsIgnoreCase("add")) {
        data.incrementExperience(job, value);
        PlayerUtils.sendMessage(player, new String[] { "§aLe métier " + job.getName() + " a été ajouté de " + value + " pour " + target
              .getDisplayName() });
        return true;
      } 
      if (args[3].equalsIgnoreCase("decrease")) {
        data.decrementExperience(job, value);
        PlayerUtils.sendMessage(player, new String[] { "§aLe métier " + job.getName() + " a été décrémenté de " + value + " pour " + target
              .getDisplayName() });
        return true;
      } 
      if (args[3].equalsIgnoreCase("setlevel")) {
        if (!data.setLevel(job, (int)value)) {
          PlayerUtils.sendMessage(player, new String[] { "§cLe niveau spécifié est invalide." });
          return true;
        } 
        PlayerUtils.sendMessage(player, new String[] { "§aLe métier " + job.getName() + " a été défini au niveau " + value + " pour " + target
              .getDisplayName() });
        return true;
      } 
      if (args[3].equalsIgnoreCase("setquest")) {
        AbstractQuest q = QuestRegistry.getInstance().getQuests().get((int)value);
        data.getDailyQuests().set(0, new QuestPlayerData(q.getId(), 0, false));
        data.sync();
        PlayerUtils.sendMessage(player, new String[] { "§aVous avez bien mis la quête " + job
              .getName() + " au joueur " + target.getDisplayName() });
        return true;
      } 
      if (args[3].equalsIgnoreCase("addtoken")) {
        data.addLvlToken((int)value, job);
        PlayerUtils.sendMessage(player, new String[] { "§aVous avez ajouté 1 token lvl." + value + " à " + job.getName() + " pour " + target
              .getDisplayName() });
        return true;
      } 
      if (args[3].equalsIgnoreCase("requirement")) {
        data.incrementRequirementProgress(job, Long.valueOf(value).intValue());
        PlayerUtils.sendMessage(player, new String[] { "§aLe métier " + job.getName() + " a été ajouté de " + value + " de progression de requirement pour " + target.getDisplayName() });
        return true;
      } 
      PlayerUtils.sendMessage(player, new String[] { "§eLa commande est : /jobs target <player> <job> <reset|set|add|decrease|setlevel|setquest|requirement> [value]" });
      return true;
    } 
    return false;
  }
  
  public int func_82362_a() {
    return 0;
  }
  
  public boolean func_71519_b(ICommandSender sender) {
    return sender instanceof EntityPlayer;
  }
  
  public List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_) {
    return func_71530_a(p_71516_2_, MinecraftServer.func_71276_C().func_71213_z());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\server\command\CommandJobs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */