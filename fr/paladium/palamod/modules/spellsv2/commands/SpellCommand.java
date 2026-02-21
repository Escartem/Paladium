package fr.paladium.palamod.modules.spellsv2.commands;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.config.ConfigManager;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientResetCooldown;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUpdateClientManager;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUpgradeSpell;
import fr.paladium.palamod.modules.spellsv2.utils.SpellManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SpellCommand extends CommandBase {
  public String func_71517_b() {
    return "spell";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "spell";
  }
  
  public List func_71514_a() {
    return Arrays.asList(new String[] { "spells" });
  }
  
  public void func_71515_b(ICommandSender sender, String[] arguments) {
    try {
      EntityPlayerMP player = func_71521_c(sender);
      if (Constants.MOD_ENV != Constants.Environment.DEV) {
        if (BukkitUtils.hasPermission(player.func_110124_au(), "palamod.cmd.spell"))
          if (arguments.length == 3) {
            if (arguments[0].equalsIgnoreCase("unlock")) {
              try {
                EntityPlayerMP target = null;
                for (Object pl : player.field_70170_p.field_73010_i) {
                  EntityPlayerMP p = (EntityPlayerMP)pl;
                  if (p.func_70005_c_().equalsIgnoreCase(arguments[1]))
                    target = p; 
                } 
                if (target != null) {
                  int id = Integer.valueOf(arguments[2]).intValue();
                  if (id >= (Spells.values()).length) {
                    sender.func_145747_a((IChatComponent)new ChatComponentText("§cID trop haut : "));
                    int i = 0;
                    for (Spells spell : Spells.values()) {
                      sender.func_145747_a((IChatComponent)new ChatComponentText("§e[" + i + "] §b" + spell
                            .getSpell().getName()));
                      i++;
                    } 
                  } else {
                    SpellManager.setUnlock((EntityPlayer)target, Spells.values()[id], true);
                    SpellManager.setTier((EntityPlayer)target, Spells.values()[id], 1);
                    PSpellsV2.network.sendTo((IMessage)new PacketClientUpgradeSpell(id, 1, true), target);
                    sender.func_145747_a((IChatComponent)new ChatComponentText("Le sort §e" + 
                          Spells.values()[id].getSpell().getName() + " §fà été débloqué pour §e" + target
                          .func_70005_c_()));
                  } 
                } else {
                  sender
                    .func_145747_a((IChatComponent)new ChatComponentText(arguments[1] + " n'est pas en ligne"));
                } 
              } catch (Exception e) {
                sender.func_145747_a((IChatComponent)new ChatComponentText(arguments[2] + " n'est pas un nombre"));
              } 
            } else if (arguments[0].equalsIgnoreCase("lock")) {
              try {
                EntityPlayerMP target = null;
                for (Object pl : player.field_70170_p.field_73010_i) {
                  EntityPlayerMP p = (EntityPlayerMP)pl;
                  if (p.func_70005_c_().equalsIgnoreCase(arguments[1]))
                    target = p; 
                } 
                if (target != null) {
                  int id = Integer.valueOf(arguments[2]).intValue();
                  if (id >= (Spells.values()).length) {
                    sender.func_145747_a((IChatComponent)new ChatComponentText("§cID trop haut : "));
                    int i = 0;
                    for (Spells spell : Spells.values()) {
                      sender.func_145747_a((IChatComponent)new ChatComponentText("§e[" + i + "] §b" + spell
                            .getSpell().getName()));
                      i++;
                    } 
                  } else {
                    SpellManager.setUnlock((EntityPlayer)target, Spells.values()[id], false);
                    SpellManager.setTier((EntityPlayer)target, Spells.values()[id], 0);
                    PSpellsV2.network.sendTo((IMessage)new PacketClientUpgradeSpell(id, 0, false), target);
                    sender.func_145747_a((IChatComponent)new ChatComponentText("Le sort §e" + 
                          Spells.values()[id].getSpell().getName() + " §fà été bloqué pour §e" + target
                          .func_70005_c_()));
                  } 
                } else {
                  sender
                    .func_145747_a((IChatComponent)new ChatComponentText(arguments[1] + " n'est pas en ligne"));
                } 
              } catch (Exception e) {
                sender.func_145747_a((IChatComponent)new ChatComponentText(arguments[2] + " n'est pas un nombre"));
              } 
            } else if (arguments[0].equalsIgnoreCase("pgive")) {
              try {
                EntityPlayerMP target = null;
                for (Object pl : player.field_70170_p.field_73010_i) {
                  EntityPlayerMP p = (EntityPlayerMP)pl;
                  if (p.func_70005_c_().equalsIgnoreCase(arguments[1]))
                    target = p; 
                } 
                if (target != null) {
                  int count = Integer.valueOf(arguments[2]).intValue();
                  double points = SpellManager.getPoints((EntityPlayer)target);
                  points += count;
                  SpellManager.setPoints((EntityPlayer)target, points);
                  int level = 0;
                  level = SpellManager.getLevel((EntityPlayer)player);
                  if (level < 0)
                    level = 0; 
                  PSpellsV2.network.sendTo((IMessage)new PacketClientUpdateClientManager(points, level), target);
                  sender.func_145747_a((IChatComponent)new ChatComponentText("Vous avez donné §e" + arguments[2] + " §fpoints à §e" + arguments[1] + " §fqui en possède désormait §e" + points));
                } else {
                  sender
                    .func_145747_a((IChatComponent)new ChatComponentText(arguments[1] + " n'est pas en ligne"));
                } 
              } catch (Exception e) {
                sender.func_145747_a((IChatComponent)new ChatComponentText(arguments[2] + " n'est pas un nombre"));
              } 
            } else if (arguments[0].equalsIgnoreCase("premove")) {
              try {
                EntityPlayerMP target = null;
                for (Object pl : player.field_70170_p.field_73010_i) {
                  EntityPlayerMP p = (EntityPlayerMP)pl;
                  if (p.func_70005_c_().equalsIgnoreCase(arguments[1]))
                    target = p; 
                } 
                if (target != null) {
                  int count = Integer.valueOf(arguments[2]).intValue();
                  double points = SpellManager.getPoints((EntityPlayer)target);
                  points -= count;
                  if (points < 0.0D)
                    points = 0.0D; 
                  SpellManager.setPoints((EntityPlayer)target, points);
                  int level = 0;
                  level = SpellManager.getLevel((EntityPlayer)player);
                  if (level < 0)
                    level = 0; 
                  PSpellsV2.network.sendTo((IMessage)new PacketClientUpdateClientManager(points, level), target);
                  sender.func_145747_a((IChatComponent)new ChatComponentText("Vous avez retiré §e" + arguments[2] + " §fpoints à §e" + arguments[1] + " §fqui en possède désormait §e" + points));
                } else {
                  sender
                    .func_145747_a((IChatComponent)new ChatComponentText(arguments[1] + " n'est pas en ligne"));
                } 
              } catch (Exception e) {
                sender.func_145747_a((IChatComponent)new ChatComponentText(arguments[2] + " n'est pas un nombre"));
              } 
            } else if (arguments[0].equalsIgnoreCase("server")) {
              if (arguments[1].equalsIgnoreCase("lock")) {
                int id = Integer.valueOf(arguments[2]).intValue();
                if (id >= (Spells.values()).length || id < 0) {
                  sender.func_145747_a((IChatComponent)new ChatComponentText("§cID incorrect : "));
                  int i = 0;
                  for (Spells spell : Spells.values()) {
                    sender.func_145747_a((IChatComponent)new ChatComponentText("§e[" + i + "] §b" + spell
                          .getSpell().getName()));
                    i++;
                  } 
                } else if (ConfigManager.hasKey("server", "lock." + id)) {
                  if (ConfigManager.getBoolean("server", "lock." + id)) {
                    ConfigManager.writeConfig("server", "lock." + id, false);
                    sender.func_145747_a((IChatComponent)new ChatComponentText("§aLe sort §b" + 
                          Spells.values()[id] + " §aà été débloqué"));
                  } else {
                    ConfigManager.writeConfig("server", "lock." + id, true);
                    sender.func_145747_a((IChatComponent)new ChatComponentText("§cLe sort §b" + 
                          Spells.values()[id] + " §cà été bloqué"));
                  } 
                } else {
                  ConfigManager.writeConfig("server", "lock." + id, true);
                  sender.func_145747_a((IChatComponent)new ChatComponentText("§cLe sort §b" + 
                        Spells.values()[id] + " §cà été bloqué"));
                } 
              } else {
                sender.func_145747_a((IChatComponent)new ChatComponentText("/spell server lock <id>"));
              } 
            } 
          } else if (arguments.length == 2) {
            if (arguments[0].equalsIgnoreCase("cooldown") && arguments[1]
              .equalsIgnoreCase("reset")) {
              SpellManager.getSpellDelay((EntityPlayer)sender).clear();
              PalaMod.network.sendTo((IMessage)new PacketClientResetCooldown(), player);
              sender.func_145747_a((IChatComponent)new ChatComponentText("§aCooldown reset"));
            } 
          } else {
            sender.func_145747_a((IChatComponent)new ChatComponentText("/spell unlock <player> <id>"));
            sender.func_145747_a((IChatComponent)new ChatComponentText("/spell lock <player> <id>"));
            sender.func_145747_a((IChatComponent)new ChatComponentText("/spell pgive <player> <count>"));
            sender.func_145747_a((IChatComponent)new ChatComponentText("/spell premove <player> <count>"));
            sender.func_145747_a((IChatComponent)new ChatComponentText("/spell cooldown reset"));
            sender.func_145747_a((IChatComponent)new ChatComponentText("/spell server lock <id>"));
          }  
      } else if (arguments.length == 3) {
        if (arguments[0].equalsIgnoreCase("unlock")) {
          try {
            EntityPlayerMP target = null;
            for (Object pl : player.field_70170_p.field_73010_i) {
              EntityPlayerMP p = (EntityPlayerMP)pl;
              if (p.func_70005_c_().equalsIgnoreCase(arguments[1]))
                target = p; 
            } 
            if (target != null) {
              int id = Integer.valueOf(arguments[2]).intValue();
              if (id >= (Spells.values()).length) {
                sender.func_145747_a((IChatComponent)new ChatComponentText("§cID trop haut : "));
                int i = 0;
                for (Spells spell : Spells.values()) {
                  sender.func_145747_a((IChatComponent)new ChatComponentText("§e[" + i + "] §b" + spell
                        .getSpell().getName()));
                  i++;
                } 
              } else {
                SpellManager.setUnlock((EntityPlayer)target, Spells.values()[id], true);
                SpellManager.setTier((EntityPlayer)target, Spells.values()[id], 1);
                PSpellsV2.network.sendTo((IMessage)new PacketClientUpgradeSpell(id, 1, true), target);
                sender.func_145747_a((IChatComponent)new ChatComponentText("Le sort §e" + 
                      Spells.values()[id].getSpell().getName() + " §fà été débloqué pour §e" + target
                      .func_70005_c_()));
              } 
            } else {
              sender.func_145747_a((IChatComponent)new ChatComponentText(arguments[1] + " n'est pas en ligne"));
            } 
          } catch (Exception e) {
            sender.func_145747_a((IChatComponent)new ChatComponentText(arguments[2] + " n'est pas un nombre"));
          } 
        } else if (arguments[0].equalsIgnoreCase("lock")) {
          try {
            EntityPlayerMP target = null;
            for (Object pl : player.field_70170_p.field_73010_i) {
              EntityPlayerMP p = (EntityPlayerMP)pl;
              if (p.func_70005_c_().equalsIgnoreCase(arguments[1]))
                target = p; 
            } 
            if (target != null) {
              int id = Integer.valueOf(arguments[2]).intValue();
              if (id >= (Spells.values()).length) {
                sender.func_145747_a((IChatComponent)new ChatComponentText("§cID trop haut : "));
                int i = 0;
                for (Spells spell : Spells.values()) {
                  sender.func_145747_a((IChatComponent)new ChatComponentText("§e[" + i + "] §b" + spell
                        .getSpell().getName()));
                  i++;
                } 
              } else {
                SpellManager.setUnlock((EntityPlayer)target, Spells.values()[id], false);
                SpellManager.setTier((EntityPlayer)target, Spells.values()[id], 0);
                PSpellsV2.network.sendTo((IMessage)new PacketClientUpgradeSpell(id, 0, false), target);
                sender.func_145747_a((IChatComponent)new ChatComponentText("Le sort §e" + 
                      Spells.values()[id].getSpell().getName() + " §fà été bloqué pour §e" + target
                      .func_70005_c_()));
              } 
            } else {
              sender.func_145747_a((IChatComponent)new ChatComponentText(arguments[1] + " n'est pas en ligne"));
            } 
          } catch (Exception e) {
            sender.func_145747_a((IChatComponent)new ChatComponentText(arguments[2] + " n'est pas un nombre"));
          } 
        } else if (arguments[0].equalsIgnoreCase("pgive")) {
          try {
            EntityPlayerMP target = null;
            for (Object pl : player.field_70170_p.field_73010_i) {
              EntityPlayerMP p = (EntityPlayerMP)pl;
              if (p.func_70005_c_().equalsIgnoreCase(arguments[1]))
                target = p; 
            } 
            if (target != null) {
              int count = Integer.valueOf(arguments[2]).intValue();
              double points = SpellManager.getPoints((EntityPlayer)target);
              points += count;
              SpellManager.setPoints((EntityPlayer)target, points);
              int level = 0;
              level = SpellManager.getLevel((EntityPlayer)player);
              if (level < 0)
                level = 0; 
              PSpellsV2.network.sendTo((IMessage)new PacketClientUpdateClientManager(points, level), target);
              sender.func_145747_a((IChatComponent)new ChatComponentText("Vous avez donné §e" + arguments[2] + " §fpoints à §e" + arguments[1] + " §fqui en possède désormait §e" + points));
            } else {
              sender.func_145747_a((IChatComponent)new ChatComponentText(arguments[1] + " n'est pas en ligne"));
            } 
          } catch (Exception e) {
            sender.func_145747_a((IChatComponent)new ChatComponentText(arguments[2] + " n'est pas un nombre"));
          } 
        } else if (arguments[0].equalsIgnoreCase("premove")) {
          try {
            EntityPlayerMP target = null;
            for (Object pl : player.field_70170_p.field_73010_i) {
              EntityPlayerMP p = (EntityPlayerMP)pl;
              if (p.func_70005_c_().equalsIgnoreCase(arguments[1]))
                target = p; 
            } 
            if (target != null) {
              int count = Integer.valueOf(arguments[2]).intValue();
              double points = SpellManager.getPoints((EntityPlayer)target);
              points -= count;
              if (points < 0.0D)
                points = 0.0D; 
              SpellManager.setPoints((EntityPlayer)target, points);
              int level = 0;
              level = SpellManager.getLevel((EntityPlayer)player);
              if (level < 0)
                level = 0; 
              PSpellsV2.network.sendTo((IMessage)new PacketClientUpdateClientManager(points, level), target);
              sender.func_145747_a((IChatComponent)new ChatComponentText("Vous avez retiré §e" + arguments[2] + " §fpoints à §e" + arguments[1] + " §fqui en possède désormait §e" + points));
            } else {
              sender.func_145747_a((IChatComponent)new ChatComponentText(arguments[1] + " n'est pas en ligne"));
            } 
          } catch (Exception e) {
            sender.func_145747_a((IChatComponent)new ChatComponentText(arguments[2] + " n'est pas un nombre"));
          } 
        } else if (arguments[0].equalsIgnoreCase("server")) {
          if (arguments[1].equalsIgnoreCase("lock")) {
            int id = Integer.valueOf(arguments[2]).intValue();
            if (id >= (Spells.values()).length || id < 0) {
              sender.func_145747_a((IChatComponent)new ChatComponentText("§cID incorrect : "));
              int i = 0;
              for (Spells spell : Spells.values()) {
                sender.func_145747_a((IChatComponent)new ChatComponentText("§e[" + i + "] §b" + spell
                      .getSpell().getName()));
                i++;
              } 
            } else if (ConfigManager.hasKey("server", "lock." + id)) {
              if (ConfigManager.getBoolean("server", "lock." + id)) {
                ConfigManager.writeConfig("server", "lock." + id, false);
                sender.func_145747_a((IChatComponent)new ChatComponentText("§aLe sort §b" + 
                      Spells.values()[id] + " §aà été débloqué"));
              } else {
                ConfigManager.writeConfig("server", "lock." + id, true);
                sender.func_145747_a((IChatComponent)new ChatComponentText("§cLe sort §b" + 
                      Spells.values()[id] + " §cà été bloqué"));
              } 
            } else {
              ConfigManager.writeConfig("server", "lock." + id, true);
              sender.func_145747_a((IChatComponent)new ChatComponentText("§cLe sort §b" + 
                    Spells.values()[id] + " §cà été bloqué"));
            } 
          } else {
            sender.func_145747_a((IChatComponent)new ChatComponentText("/spell server lock <id>"));
          } 
        } 
      } else if (arguments.length == 2) {
        if (arguments[0].equalsIgnoreCase("cooldown") && arguments[1].equalsIgnoreCase("reset")) {
          SpellManager.getSpellDelay((EntityPlayer)sender).clear();
          PalaMod.network.sendTo((IMessage)new PacketClientResetCooldown(), player);
          sender.func_145747_a((IChatComponent)new ChatComponentText("§aCooldown reset"));
        } 
      } else {
        sender.func_145747_a((IChatComponent)new ChatComponentText("/spell unlock <player> <id>"));
        sender.func_145747_a((IChatComponent)new ChatComponentText("/spell lock <player> <id>"));
        sender.func_145747_a((IChatComponent)new ChatComponentText("/spell pgive <player> <count>"));
        sender.func_145747_a((IChatComponent)new ChatComponentText("/spell premove <player> <count>"));
        sender.func_145747_a((IChatComponent)new ChatComponentText("/spell cooldown reset"));
        sender.func_145747_a((IChatComponent)new ChatComponentText("/spell server lock <id>"));
      } 
    } catch (Exception e) {
      e.printStackTrace();
      sender.func_145747_a((IChatComponent)new ChatComponentText("Vous devez être un joueur."));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\commands\SpellCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */