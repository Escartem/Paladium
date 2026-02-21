package fr.paladium.achievement.server.commands;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.achievement.AchievementMod;
import fr.paladium.achievement.core.AchievementRegistry;
import fr.paladium.achievement.core.data.ExtendedAchievementPlayerData;
import fr.paladium.achievement.core.packets.server.ui.SCAchievementHome;
import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class CommandAchievement extends CommandBase {
  public String func_71517_b() {
    return "achievements";
  }
  
  public String func_71518_a(ICommandSender p_71518_1_) {
    return "achievements";
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (!(sender instanceof EntityPlayer))
      return; 
    EntityPlayer p = (EntityPlayer)sender;
    if (AchievementManager.hasBukkitPermission(p.func_110124_au(), "achievement.player")) {
      if (p.field_70170_p.field_72995_K)
        return; 
      if (args.length == 0) {
        ExtendedAchievementPlayerData eep = ExtendedAchievementPlayerData.get((Entity)p);
        if (eep != null)
          eep.dataChanged((Entity)p); 
        AchievementMod.getProxy().getNetwork().sendTo((IMessage)new SCAchievementHome(), (EntityPlayerMP)p);
      } 
      if (args.length == 1 && 
        AchievementManager.hasBukkitPermission(p.func_110124_au(), "achievement.admin")) {
        String arg = args[0];
        resetSelf(arg, p);
        if ("list".equalsIgnoreCase(arg))
          for (AchievementCategory category : AchievementCategory.values()) {
            p.func_146105_b((IChatComponent)new ChatComponentText(category.getName()));
            AchievementManager.getInstance().getAchievementsByCategory(category)
              .stream().forEach(c -> {
                  if (c.getSubAchievements().isEmpty()) {
                    p.func_146105_b((IChatComponent)new ChatComponentText(" > " + c.getName() + " (" + c.getId() + ") | max: " + c.getNbToValidate()));
                  } else {
                    p.func_146105_b((IChatComponent)new ChatComponentText(" " + c.getId()));
                    c.getSubAchievements().stream().forEach(());
                  } 
                });
          }  
      } 
      if (args.length >= 3 && 
        AchievementManager.hasBukkitPermission(p.func_110124_au(), "achievement.admin")) {
        String arg = args[0];
        int amount = Integer.parseInt(args[1]);
        String name = String.join(" ", Arrays.<CharSequence>copyOfRange((CharSequence[])args, 2, args.length));
        if ("set".equalsIgnoreCase(arg)) {
          ExtendedAchievementPlayerData eep = ExtendedAchievementPlayerData.get((Entity)p);
          Achievement achievement = null;
          for (Achievement a : AchievementRegistry.getInstance().getAchievements()) {
            if (a.getName().equalsIgnoreCase(name)) {
              achievement = a;
              break;
            } 
          } 
          eep.achievementStats.put(achievement.getId(), Integer.valueOf(amount));
          p.func_146105_b((IChatComponent)new ChatComponentText("§aVous avez bien mis §a" + achievement.getName() + " (" + achievement.getId() + ") §aà §7" + amount + "§a."));
        } 
      } 
    } 
  }
  
  private void resetSelf(String arg, EntityPlayer player) {
    if (arg == null)
      return; 
    ExtendedAchievementPlayerData eep = ExtendedAchievementPlayerData.get((Entity)player);
    if ("reset".equalsIgnoreCase(arg)) {
      eep.getAchievementStats().forEach((k, v) -> eep.getAchievementStats().put(k, Integer.valueOf(0)));
      player.func_146105_b((IChatComponent)new ChatComponentText("§aVous avez bien reset vos achievements."));
    } 
  }
  
  public List<?> func_71514_a() {
    List<String> alias = new ArrayList<>();
    alias.add("achvmts");
    return alias;
  }
  
  public int func_82362_a() {
    return 0;
  }
  
  public boolean func_71519_b(ICommandSender sender) {
    return sender instanceof EntityPlayer;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\server\commands\CommandAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */