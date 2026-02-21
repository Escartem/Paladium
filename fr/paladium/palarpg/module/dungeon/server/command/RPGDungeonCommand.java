package fr.paladium.palarpg.module.dungeon.server.command;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.number.NumberValidator.GreaterOrEquals;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.string.StringValidator.NotEmpty;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.dungeon.client.ui.ranking.UIDungeonRanking;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.dungeon.server.bukkit.DungeonBukkitImpl;
import fr.paladium.palarpg.module.dungeon.server.config.DungeonConfig;
import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelConfig;
import fr.paladium.palarpg.module.dungeon.server.manager.DungeonManager;
import fr.paladium.palarpg.module.dungeon.server.rabbitmq.RBPacketDungeonInvite;
import fr.paladium.palarpg.module.dungeon.server.rabbitmq.RBPacketDungeonJoin;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class RPGDungeonCommand {
  @SubCommand(command = "/rpg dungeon ranking", description = "Afficher le classement des donjons", sender = {SenderType.PLAYER})
  public void ranking(CommandContext context) {
    ZUIServer.open(UIDungeonRanking.class, context.getPlayer());
  }
  
  @SubCommand(command = "/rpg dungeon accept <dungeonId>", description = "Accepter une invitation de donjon", sender = {SenderType.PLAYER})
  public void accept(CommandContext context, String dungeonId) {
    EntityPlayerMP player = context.getPlayer();
    RPGDungeonPlayerData data = (RPGDungeonPlayerData)RPGPlayer.getData((Entity)player, "dungeon");
    if (data == null) {
      context.error("Une erreur est survenue lors de la récupération de vos données de donjon.");
      return;
    } 
    if (!data.getInvitationsMap().containsKey(dungeonId)) {
      context.error("Vous n’avez pas d’invitation pour ce donjon.");
      return;
    } 
    context.send("§bRecherche du donjon en cours...");
    AtomicBoolean expired = new AtomicBoolean(false);
    String targetName = (String)data.getInvitationsMap().remove(dungeonId);
    (new RBPacketDungeonJoin(dungeonId, UUIDUtils.toString((Entity)player))).subscribe((server, result) -> {
          if (expired.get() || result == null || result.getState() == RBPacketDungeonJoin.RBPacketDungeonJoinResult.RBPacketDungeonJoinResultState.NOT_FOUND)
            return; 
          expired.set(true);
          if (result.getState() == RBPacketDungeonJoin.RBPacketDungeonJoinResult.RBPacketDungeonJoinResultState.NOT_INVITED || result.getState() == RBPacketDungeonJoin.RBPacketDungeonJoinResult.RBPacketDungeonJoinResultState.NOT_WAITING) {
            context.error("§cL’invitation pour le donjon de §6" + targetName + " §ca expiré.");
            return;
          } 
          if (result.getState() != RBPacketDungeonJoin.RBPacketDungeonJoinResult.RBPacketDungeonJoinResultState.SUCCESS)
            return; 
          context.success("§aTéléportation en cours vers le donjon de §6" + targetName + " §8(" + result.getServer() + ")");
          if (Server.getHostName().equals(server)) {
            Optional<DungeonWorld> optionalWorld = DungeonWorld.get(dungeonId);
            if (!optionalWorld.isPresent()) {
              context.error("§cLe donjon de §6" + targetName + " §cn’a pas pu être trouvé.");
              return;
            } 
            data.setJoiningDungeon(dungeonId);
            DungeonWorld world = optionalWorld.get();
            world.teleport(player).sync(player);
            data.setJoiningDungeon(null);
          } else {
            data.setJoiningDungeon(dungeonId);
            DungeonBukkitImpl.connect(player, result.getServer());
          } 
        }).broadcast();
    (new Thread(() -> {
          try {
            Thread.sleep(3000L);
          } catch (InterruptedException interruptedException) {}
          if (expired.get())
            return; 
          expired.set(true);
          context.error("§cL’invitation pour le donjon de §6" + targetName + " §ca expiré.");
        })).start();
  }
  
  @SubCommand(command = "/rpg dungeon deny <dungeonId>", description = "Accepter une invitation de donjon", sender = {SenderType.PLAYER})
  public void deny(CommandContext context, String dungeonId) {
    EntityPlayerMP player = context.getPlayer();
    RPGDungeonPlayerData data = (RPGDungeonPlayerData)RPGPlayer.getData((Entity)player, "dungeon");
    if (data == null) {
      context.error("Une erreur est survenue lors de la récupération de vos données de donjon.");
      return;
    } 
    if (!data.getInvitationsMap().containsKey(dungeonId)) {
      context.error("Vous n’avez pas d’invitation pour ce donjon.");
      return;
    } 
    String targetName = (String)data.getInvitationsMap().remove(dungeonId);
    context.success("§aVous avez refusé l’invitation pour le donjon de §6" + targetName + "§a.");
  }
  
  @SubCommand(command = "/rpg dungeon leader <player>", description = "Définir le chef du donjon", sender = {SenderType.PLAYER})
  public void leader(CommandContext context, OfflinePlayer target) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get((EntityPlayer)context.getPlayer());
    if (!optionalDungeonWorld.isPresent()) {
      context.error("Vous devez être dans un donjon pour pouvoir définir le chef.");
      return;
    } 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (!dungeonWorld.isLeader((Entity)context.getPlayer())) {
      context.error("Vous devez être le chef du donjon pour pouvoir choisir un autre chef.");
      return;
    } 
    if (!dungeonWorld.isMember(target.getUuidString())) {
      context.error("§cLe joueur §6" + target.getName() + "§c n’est pas dans votre donjon.");
      return;
    } 
    if (!target.isOnline(dungeonWorld.getWorld())) {
      context.error("§cLe joueur §6" + target.getName() + "§c doit être en ligne.");
      return;
    } 
    if (!((DungeonPlayer)dungeonWorld.getPlayer(target.getUuidString()).get()).isAlive()) {
      context.error("§cLe joueur §6" + target.getName() + "§c doit être en vie pour devenir chef du donjon.");
      return;
    } 
    dungeonWorld.setLeader(dungeonWorld.getPlayer(target.getUuidString()).get());
    context.success("§aLe joueur §6" + target.getName() + "§a a été défini comme chef du donjon.");
  }
  
  @SubCommand(command = "/rpg dungeon start", description = "Démarrer le donjon", permission = "rpg.command.admin.use", sender = {SenderType.PLAYER})
  public void start(CommandContext context) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(context.getPlayer().func_130014_f_());
    if (!optionalDungeonWorld.isPresent()) {
      context.error("Vous devez être dans un donjon pour pouvoir le démarrer.");
      return;
    } 
    try {
      ((DungeonWorld)optionalDungeonWorld.get()).start();
    } catch (Exception e) {
      context.error("Une erreur est survenue lors du démarrage du donjon : " + e.getMessage());
      e.printStackTrace();
    } 
  }
  
  @SubCommand(command = "/rpg dungeon unlock <dungeon> <level> [<player>]", permission = "rpg.command.admin.use", description = "Débloquer le donjon", sender = {SenderType.PLAYER})
  public void unlock(CommandContext context, @NotEmpty String dungeon, @GreaterOrEquals(1.0D) int level, Optional<OfflinePlayer> targetOpt) {
    EntityPlayerMP targetPlayer = context.getPlayer();
    if (targetOpt.isPresent()) {
      OfflinePlayer targetOffline = targetOpt.get();
      EntityPlayer targetEntity = targetOffline.getPlayer().orElse(null);
      if (!(targetEntity instanceof EntityPlayerMP)) {
        context.error("Le joueur " + targetOffline.getName() + " n’est pas en ligne.");
        return;
      } 
      targetPlayer = (EntityPlayerMP)targetEntity;
    } 
    RPGDungeonPlayerData data = (RPGDungeonPlayerData)RPGPlayer.getData((Entity)targetPlayer, "dungeon");
    if (data == null) {
      context.error("Une erreur est survenue lors de la récupération des données de donjon du joueur " + targetPlayer.func_70005_c_() + ".");
      return;
    } 
    Optional<DungeonConfig> optionalDungeonConfig = DungeonManager.getDungeon(dungeon);
    if (!optionalDungeonConfig.isPresent()) {
      context.error("Le donjon " + dungeon + " n’existe pas.");
      return;
    } 
    DungeonConfig dungeonConfig = optionalDungeonConfig.get();
    Optional<DungeonLevelConfig> optionalLevel = (dungeonConfig.isInfinite() && level - 1 > dungeonConfig.getLevels().stream().mapToInt(DungeonLevelConfig::getLevel).max().orElse(2147483647)) ? dungeonConfig.getInfiniteLevel(level - 1) : dungeonConfig.getLevel(level - 1);
    if (!optionalLevel.isPresent()) {
      context.error("Le palier " + level + " du donjon " + dungeon + " n’existe pas.");
      return;
    } 
    data.unlockLevel(dungeonConfig, optionalLevel.get()).sync();
    DungeonWorld.get((EntityPlayer)targetPlayer).ifPresent(world -> world.updateMaxLevels().sync());
    context.success("Le palier " + level + " du donjon " + dungeon + " a été débloqué pour le joueur " + targetPlayer.func_70005_c_() + ".");
  }
  
  @SubCommand(command = "/rpg dungeon set <dungeon> <level>", description = "Définir le donjon", permission = "rpg.command.admin.use", sender = {SenderType.PLAYER})
  public void set(CommandContext context, @NotEmpty String dungeon, @GreaterOrEquals(0.0D) int level) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(context.getPlayer().func_130014_f_());
    if (!optionalDungeonWorld.isPresent()) {
      context.error("Vous devez être dans un donjon pour pouvoir le définir.");
      return;
    } 
    Optional<DungeonConfig> optionalDungeonConfig = DungeonManager.getDungeon(dungeon);
    if (!optionalDungeonConfig.isPresent()) {
      context.error("Le donjon " + dungeon + " n’existe pas.");
      return;
    } 
    ((DungeonWorld)optionalDungeonWorld.get()).setDungeon(optionalDungeonConfig.get());
    ((DungeonWorld)optionalDungeonWorld.get()).setLevel(level);
    context.success("Donjon défini sur " + dungeon + " avec le palier " + level);
  }
  
  @SubCommand(command = "/rpg dungeon kick <player>", description = "Expulser un joueur du donjon", permission = "rpg.command.admin.use", sender = {SenderType.PLAYER})
  public void kick(CommandContext context, OfflinePlayer target) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(context.getPlayer().func_130014_f_());
    if (target.getUuid().equals(context.getPlayer().func_110124_au())) {
      context.error("Vous ne pouvez pas vous expulser vous-même du donjon.");
      return;
    } 
    if (!optionalDungeonWorld.isPresent()) {
      context.error("Vous devez être dans un donjon pour pouvoir expulser un joueur.");
      return;
    } 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (!dungeonWorld.isMember(target.getUuidString())) {
      context.error("§cLe joueur §6" + target.getName() + "§c n’est pas dans votre donjon.");
      return;
    } 
    dungeonWorld.removePlayer(target.getUuidString(), true);
    context.success("§aLe joueur §6" + target.getName() + "§a a été expulsé du donjon.");
  }
  
  @SubCommand(command = "/rpg dungeon invite <player>", description = "Inviter un joueur dans le donjon", permission = "rpg.command.admin.use", sender = {SenderType.PLAYER})
  public void invite(CommandContext context, OfflinePlayer target) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(context.getPlayer().func_130014_f_());
    if (!optionalDungeonWorld.isPresent()) {
      context.error("Vous devez être dans un donjon pour pouvoir inviter un joueur.");
      return;
    } 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (dungeonWorld.getState() != DungeonWorld.DungeonState.WAITING) {
      context.error("Vous devez être dans un donjon en attente de joueurs pour pouvoir inviter quelqu’un.");
      return;
    } 
    if (dungeonWorld.getPlayers().size() >= 4) {
      context.error("Le donjon est plein, vous ne pouvez pas inviter plus de joueurs.");
      return;
    } 
    if (dungeonWorld.isMember(target.getUuidString())) {
      context.error("§cLe joueur §6" + target.getName() + "§c est déjà dans le donjon.");
      return;
    } 
    dungeonWorld.addPlayer(target);
    (new RBPacketDungeonInvite(dungeonWorld.getUniqueId(), OfflinePlayer.of((EntityPlayer)context.getPlayer()), target.getUuid())).broadcast();
    context.success("§cInvitation envoyée à §6" + target.getName());
  }
  
  @SubCommand(command = "/rpg dungeon list", description = "Lister tous les donjons", permission = "rpg.command.admin.use")
  public void list(CommandContext context) {
    context.send("Voici la liste de tous les donjons :");
    for (DungeonWorld dungeonWorld : DungeonWorld.getAll()) {
      context.send(" - " + dungeonWorld.getWorld().func_72912_H().func_76065_j() + " (" + dungeonWorld.getUniqueId() + ")");
      context.send("   - Chef : " + ((dungeonWorld.getLeader() == null) ? "aucun" : dungeonWorld.getLeader().getName()));
      context.send("   - Joueurs : " + String.join(", ", (CharSequence[])dungeonWorld.getPlayers().stream().map(DungeonPlayer::getName).toArray(x$0 -> new String[x$0])));
      context.send("   - Donjon : " + ((dungeonWorld.getDungeon() == null) ? "aucun" : dungeonWorld.getDungeon().getName()));
      context.send("   - Palier : " + dungeonWorld.getLevel());
      context.send("   - État : " + dungeonWorld.getState().name());
    } 
  }
  
  @SubCommand(command = "/rpg dungeon info", description = "Informations sur votre donjon", permission = "rpg.command.admin.use")
  public void info(CommandContext context) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(context.getPlayer().func_130014_f_());
    if (!optionalDungeonWorld.isPresent()) {
      context.error("Vous n’êtes pas dans un donjon.");
      return;
    } 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    context.send("Informations sur le donjon :");
    context.send(" - ID : " + dungeonWorld.getUniqueId());
    context.send(" - Chef : " + ((dungeonWorld.getLeader() == null) ? "aucun" : dungeonWorld.getLeader().getName()));
    context.send(" - Joueurs : " + String.join(", ", (CharSequence[])dungeonWorld.getPlayers().stream().map(DungeonPlayer::getName).toArray(x$0 -> new String[x$0])));
    context.send(" - Donjon : " + ((dungeonWorld.getDungeon() == null) ? "aucun" : dungeonWorld.getDungeon().getName()));
    context.send(" - Palier : " + dungeonWorld.getLevel());
    context.send(" - Salle : " + (dungeonWorld.getRoom().isPresent() ? ((DungeonRoom)dungeonWorld.getRoom().get()).getConfig().getId() : "aucune"));
    context.send(" - État : " + dungeonWorld.getState().name());
  }
  
  @SubCommand(command = "/rpg dungeon reload", description = "Recharger tous les donjons", permission = "rpg.command.admin.use")
  public void reload(CommandContext context) {
    try {
      DungeonManager.load();
      context.success("Tous les donjons ont été rechargés. (" + DungeonManager.getDungeons().size() + ")");
    } catch (Exception e) {
      e.printStackTrace();
      context.error("Une erreur est survenue lors du rechargement des donjons : " + e.getMessage());
    } 
  }
  
  @SubCommand(command = "/rpg dungeon finish", description = "Terminer le donjon", permission = "rpg.command.admin.use")
  public void finish(CommandContext context) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(context.getPlayer().func_130014_f_());
    if (!optionalDungeonWorld.isPresent()) {
      context.error("Vous n’êtes pas dans un donjon.");
      return;
    } 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (dungeonWorld.getState() != DungeonWorld.DungeonState.STARTED) {
      context.error("Le donjon n’a pas encore commencé.");
      return;
    } 
    try {
      dungeonWorld.finish(true);
    } catch (Exception e) {
      dungeonWorld.remove();
      e.printStackTrace();
    } 
    context.success("Le donjon a été terminé.");
  }
  
  @SubCommand(command = "/rpg dungeon room info", description = "Informations sur la salle actuelle du donjon", permission = "rpg.command.admin.use")
  public void roomInfo(CommandContext context) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(context.getPlayer().func_130014_f_());
    if (!optionalDungeonWorld.isPresent()) {
      context.error("Vous n’êtes pas dans un donjon.");
      return;
    } 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (!dungeonWorld.getRoom().isPresent()) {
      context.error("Vous n’êtes pas dans une salle de donjon.");
      return;
    } 
    context.success("Voici les informations sur la salle actuelle du donjon :");
    context.send(((DungeonRoom)dungeonWorld.getRoom().get()).toString());
  }
  
  @SubCommand(command = "/rpg dungeon room debug", description = "Activer le mode debug de la salle actuelle du donjon", permission = "rpg.command.admin.use")
  public void roomDebug(CommandContext context) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(context.getPlayer().func_130014_f_());
    if (!optionalDungeonWorld.isPresent()) {
      context.error("Vous n’êtes pas dans un donjon.");
      return;
    } 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (!dungeonWorld.getRoom().isPresent()) {
      context.error("Vous n’êtes pas dans une salle de donjon.");
      return;
    } 
    ((DungeonRoom)dungeonWorld.getRoom().get()).setDebug(!((DungeonRoom)dungeonWorld.getRoom().get()).isDebug());
    context.success("Le mode debug de la salle de donjon a été " + (((DungeonRoom)dungeonWorld.getRoom().get()).isDebug() ? "activé" : "désactivé") + ".");
  }
  
  @SubCommand(command = "/rpg dungeon room finish", description = "Terminer la salle actuelle du donjon", permission = "rpg.command.admin.use")
  public void roomFinish(CommandContext context) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(context.getPlayer().func_130014_f_());
    if (!optionalDungeonWorld.isPresent()) {
      context.error("Vous n’êtes pas dans un donjon.");
      return;
    } 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (!dungeonWorld.getRoom().isPresent()) {
      context.error("Vous n’êtes pas dans une salle de donjon.");
      return;
    } 
    ((DungeonRoom)dungeonWorld.getRoom().get()).setFinished(true);
    context.success("La salle de donjon a été marquée comme terminée.");
  }
  
  @SubCommand(command = "/rpg dungeon room next", description = "Passer à la salle suivante du donjon", permission = "rpg.command.admin.use")
  public void roomNext(CommandContext context) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(context.getPlayer().func_130014_f_());
    if (!optionalDungeonWorld.isPresent()) {
      context.error("Vous n’êtes pas dans un donjon.");
      return;
    } 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (!dungeonWorld.getRoom().isPresent()) {
      context.error("Vous n’êtes pas dans une salle de donjon.");
      return;
    } 
    DungeonRoom room = dungeonWorld.getRoom().get();
    if (!room.isFinished())
      room.setFinished(true); 
    room.next();
    context.success("La salle de donjon a été terminée et le donjon passe à la salle suivante.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\command\RPGDungeonCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */