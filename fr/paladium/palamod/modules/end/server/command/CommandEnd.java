package fr.paladium.palamod.modules.end.server.command;

import fr.paladium.chronos.server.api.async.ChronosCallback;
import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.chronos.server.managers.ChronosManager;
import fr.paladium.palamod.modules.end.PEnd;
import fr.paladium.palamod.modules.end.server.config.EndConfig;
import fr.paladium.palamod.modules.end.server.config.MagicConfig;
import fr.paladium.palamod.modules.end.server.manager.EndManager;
import fr.paladium.palamod.util.BlockLocation;
import java.lang.reflect.Field;
import java.util.UUID;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class CommandEnd extends CommandBase {
  public String func_71517_b() {
    return "endevent";
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "/endevent";
  }
  
  public int func_82362_a() {
    return 0;
  }
  
  public boolean func_71519_b(ICommandSender sender) {
    return true;
  }
  
  public void func_71515_b(final ICommandSender sender, String[] args) {
    if (sender instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)sender;
      UUID playerUUID = player.func_110124_au();
      if (args.length == 0) {
        reply((ICommandSender)player, "§8/§eendevent start §8[uuid]");
        reply((ICommandSender)player, "§8/§eendevent stop");
        reply((ICommandSender)player, "§8/§eendevent config start §8[hole|portal|endportal|crystals|spawns|dragon|egg]");
        reply((ICommandSender)player, "§8/§eendevent config info");
        reply((ICommandSender)player, "§8/§eendevent config stop");
      } else if ("start".equalsIgnoreCase(args[0]) && args.length == 2) {
        if (EndManager.getInstance().isActive()) {
          reply(sender, "§cCet event est déjà en cours.");
          return;
        } 
        String uuid = args[1];
        ChronosManager.getInstance().startEventAsync(uuid, new ChronosCallback<Planned>() {
              public void onSuccess(Planned planned) {
                if (planned == null) {
                  CommandEnd.this.reply(sender, "§cImpossible de démarrer l'event.");
                  return;
                } 
                try {
                  EndManager.getInstance().start(planned.getUuid());
                } catch (Exception e) {
                  EndManager.getInstance().stop(null);
                  CommandEnd.this.reply(sender, "§cImpossible de démarrer l'event.");
                  e.printStackTrace();
                  return;
                } 
              }
              
              public void onFail(Planned planned, Throwable error) {
                error.printStackTrace();
                CommandEnd.this.reply(sender, "§cImpossible de démarrer l'event.");
              }
            });
      } else if ("stop".equalsIgnoreCase(args[0])) {
        if (!EndManager.getInstance().isActive()) {
          reply(sender, "§cCet event n'est pas en cours.");
          return;
        } 
        ChronosManager.getInstance().stopEventAsync(EndManager.getInstance().getUUID(), new ChronosCallback<Planned>() {
              public void onSuccess(Planned planned) {
                EndManager.getInstance().stop(null);
              }
              
              public void onFail(Planned planned, Throwable error) {
                error.printStackTrace();
                CommandEnd.this.reply(sender, "§cImpossible d'arrêter l'event.");
              }
            });
      } else if ("config".equalsIgnoreCase(args[0]) && args.length >= 2) {
        if (PEnd.getServer().getConfig() == null) {
          reply(sender, "§cImpossible de charger le fichier de config.");
          return;
        } 
        String subCommand = args[1];
        if ("start".equalsIgnoreCase(subCommand) && args.length == 3) {
          String configType = args[2].toLowerCase();
          if (PEnd.getServer().getConfigurators().containsKey(playerUUID)) {
            reply(sender, "§cVous êtes déjà en mode édition.");
            return;
          } 
          try {
            Field field = EndConfig.class.getDeclaredField(configType);
            MagicConfig config = new MagicConfig(c -> {
                  try {
                    field.set(PEnd.getServer().getConfig(), c.getPoints());
                    PEnd.getServer().getConfig().save(PEnd.getServer().getConfigFile());
                    reply(sender, "§aEdition sauvergardée.");
                  } catch (Exception e) {
                    reply(sender, "§cImpossible de sauvegarder la config.");
                    e.printStackTrace();
                  } 
                });
            PEnd.getServer().getConfigurators().put(playerUUID, config);
            reply(sender, "§aVous êtes en mode édition §e" + configType);
          } catch (Exception e) {
            reply(sender, "§cLe mode de config §e" + configType + " §cn'existe pas.");
          } 
          return;
        } 
        if ("info".equalsIgnoreCase(subCommand)) {
          if (!PEnd.getServer().getConfigurators().containsKey(playerUUID)) {
            reply(sender, "§cVous n'êtes pas en mode édition.");
            return;
          } 
          MagicConfig config = (MagicConfig)PEnd.getServer().getConfigurators().get(playerUUID);
          reply(sender, "Voici la liste des blocs en cours de configuration : ");
          for (BlockLocation pos : config.getPoints())
            reply(sender, "§8» §b" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ()); 
          return;
        } 
        if ("stop".equalsIgnoreCase(subCommand)) {
          if (!PEnd.getServer().getConfigurators().containsKey(playerUUID)) {
            reply(sender, "§cVous n'êtes pas en mode édition.");
            return;
          } 
          MagicConfig config = (MagicConfig)PEnd.getServer().getConfigurators().get(playerUUID);
          config.end();
          PEnd.getServer().getConfigurators().remove(playerUUID);
        } else {
          reply((ICommandSender)player, "§8/§eendevent config start §8[hole|portal|endportal|crystals|spawns|dragon|egg]");
          reply((ICommandSender)player, "§8/§eendevent config info");
          reply((ICommandSender)player, "§8/§eendevent config stop");
        } 
      } else {
        reply((ICommandSender)player, "§8/§eendevent start §8[uuid]");
        reply((ICommandSender)player, "§8/§eendevent stop");
        reply((ICommandSender)player, "§8/§eendevent config start §8[hole|portal|endportal|crystals|spawns|dragon|egg]");
        reply((ICommandSender)player, "§8/§eendevent config info");
        reply((ICommandSender)player, "§8/§eendevent config stop");
      } 
    } else if (args.length == 0) {
      reply(sender, "§8/§eendevent start §8[uuid]");
    } else if ("start".equalsIgnoreCase(args[0]) && args.length == 2) {
      if (EndManager.getInstance().isActive()) {
        reply(sender, "§cCet event est déjà en cours.");
        return;
      } 
      String uuid = args[1];
      try {
        EndManager.getInstance().start(uuid);
        reply(sender, "§aL'event a été démarré avec succès.");
      } catch (Exception e) {
        EndManager.getInstance().stop(null);
        reply(sender, "§cImpossible de démarrer l'event.");
        e.printStackTrace();
        return;
      } 
    } else if ("stop".equalsIgnoreCase(args[0])) {
      if (!EndManager.getInstance().isActive()) {
        reply(sender, "§cCet event n'est pas en cours.");
        return;
      } 
      EndManager.getInstance().stop(null);
      reply(sender, "§aL'event a été arrêté avec succès.");
    } else {
      reply(sender, "§cCette commande ne peut être executée que depuis un joueur.");
    } 
  }
  
  private void reply(ICommandSender sender, String message) {
    sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§dEnd§8] §e" + message));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\server\command\CommandEnd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */