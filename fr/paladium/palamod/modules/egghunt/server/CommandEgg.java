package fr.paladium.palamod.modules.egghunt.server;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.modules.egghunt.utils.PlayerEggHuntRanked;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palamod.util.DurationConverter;
import fr.paladium.palamod.util.FastUUID;
import java.io.IOException;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import retrofit2.Response;

public class CommandEgg extends CommandBase {
  public String func_71517_b() {
    return "egg";
  }
  
  public String func_71518_a(ICommandSender p_71518_1_) {
    return "/egg";
  }
  
  public int func_82362_a() {
    return 0;
  }
  
  public boolean func_71519_b(ICommandSender p_71519_1_) {
    return true;
  }
  
  public void func_71515_b(ICommandSender sender, String[] args) {
    if (!(sender instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)sender;
    if (args.length == 0) {
      reply(player, "§8/§eegg reward");
      reply(player, "§8/§eegg drop");
      reply(player, "§8/§eegg classement");
      reply(player, "§8/§eegg me");
    } else if ("reward".equalsIgnoreCase(args[0])) {
      if (PEggHunt.status == null || PEggHunt.data == null || !PEggHunt.data.isActive()) {
        reply(player, "§cRecherche de dragon en cours");
        return;
      } 
      if (PEggHunt.status.getEggOwner() == null || !PEggHunt.status.getEggOwner().equalsIgnoreCase(player.func_70005_c_())) {
        reply(player, "§cVous ne transportez pas d'oeuf sur vous");
        return;
      } 
      long diff = System.currentTimeMillis() - PEggHunt.lastReward;
      if (PEggHunt.lastReward <= -1L || diff < 600000L) {
        reply(player, "§cIl n'est pas encore temps de réclamer cette récompense, il vous reste encore §e" + DurationConverter.fromMillisToString(600000L - diff));
        return;
      } 
      PEggHunt.lastReward = System.currentTimeMillis();
      int rdmJob = player.field_70170_p.field_73012_v.nextInt((JobType.values()).length);
      int rdmXp = 250 + player.field_70170_p.field_73012_v.nextInt(501);
      JobType job = JobType.values()[rdmJob];
      JobsPlayer.get((Entity)player).addXp(job, ObjectiveType.FISH, rdmXp, (EntityPlayer)player);
      reply(player, "Vous venez de gagner §b" + rdmXp + " §een §b" + job.getName());
    } else if ("drop".equalsIgnoreCase(args[0])) {
      if (PEggHunt.status == null || PEggHunt.data == null || !PEggHunt.data.isActive()) {
        reply(player, "§cRecherche de dragon en cours");
        return;
      } 
      if (PEggHunt.status.getEggOwner() == null || !PEggHunt.status.getEggOwner().equalsIgnoreCase(player.func_70005_c_())) {
        reply(player, "§cVous ne transportez pas d'oeuf sur vous");
        return;
      } 
      PEggHunt.dropEgg((EntityPlayer)player, true);
    } else if ("classement".equalsIgnoreCase(args[0])) {
      PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
            try {
              Response<List<PlayerEggHuntRanked>> response = ApiServices.Http.getEggHuntService().getRanking().execute();
              if (!response.isSuccessful()) {
                reply(player, "§cImpossible de récupérer le classement");
                return;
              } 
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m---------§8[§cEggHunt§8]§8§m---------"));
              for (PlayerEggHuntRanked rank : response.body()) {
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(FastUUID.parseUUID(rank.getPlayerUUID()));
                long points = rank.getPoints() * 1000L;
                player.func_145747_a((IChatComponent)new ChatComponentText(" §8» §e[" + rank.getRank() + "] " + ((offlinePlayer == null || "null".equalsIgnoreCase(offlinePlayer.getName())) ? rank.getPlayerUUID() : offlinePlayer.getName()) + " : " + ((points <= 0L) ? "0 seconde" : DurationConverter.fromMillisToString(points))));
              } 
              player.func_145747_a((IChatComponent)new ChatComponentText("§8§m---------§8[§cEggHunt§8]§8§m---------"));
            } catch (IOException e) {
              reply(player, "§cImpossible de récupérer le classement");
              e.printStackTrace();
              return;
            } 
          }0L);
    } else if ("me".equalsIgnoreCase(args[0])) {
      PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
            try {
              Response<PlayerEggHuntRanked> response = ApiServices.Http.getEggHuntService().getPlayerRank(FastUUID.toString(player.func_110124_au())).execute();
              if (!response.isSuccessful()) {
                reply(player, "§cImpossible de récupérer votre position");
                return;
              } 
              PlayerEggHuntRanked rank = (PlayerEggHuntRanked)response.body();
              if (rank == null) {
                reply(player, "§cImpossible de récupérer votre position");
                return;
              } 
              long points = rank.getPoints() * 1000L;
              reply(player, "Vous êtes §b" + rank.getRank() + ((rank.getRank() == 1) ? "er" : "ème") + "§e du classement §8(" + ((points <= 0L) ? "0 seconde" : DurationConverter.fromMillisToString(points)) + ")");
            } catch (IOException e) {
              reply(player, "§cImpossible de récupérer le classement");
              e.printStackTrace();
              return;
            } 
          }0L);
    } 
  }
  
  private void reply(EntityPlayerMP player, String message) {
    player.func_145747_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§e" + message));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\server\CommandEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */