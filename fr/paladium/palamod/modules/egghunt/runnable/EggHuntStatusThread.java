package fr.paladium.palamod.modules.egghunt.runnable;

import fr.paladium.chronos.server.api.async.ChronosCallback;
import fr.paladium.chronos.server.api.pojo.Planned;
import fr.paladium.chronos.server.managers.ChronosManager;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.factions.api.player.IPlayer;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.modules.egghunt.common.helios.ModuleEggHunt;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntLocation;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntPlayerEggInput;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntStatus;
import fr.paladium.palamod.modules.end.PEnd;
import fr.paladium.palamod.modules.end.server.manager.EndManager;
import fr.paladium.palamod.modules.ranking.RankingEvents;
import fr.paladium.palamod.util.DurationConverter;
import fr.paladium.ranking.common.data.RankedPlayerData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.HoverEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import retrofit2.Response;

public class EggHuntStatusThread extends Thread {
  public EggHuntStatusThread() {
    super(() -> {
          int tick = 0;
          while (true) {
            try {
              tick++;
              World world = MinecraftServer.func_71276_C().func_130014_f_();
              Thread.sleep(10000L);
              Response<EggHuntStatus> response = ApiServices.Http.getEggHuntService().getStatus().execute();
              if (response.body() != null) {
                EggHuntStatus status = (EggHuntStatus)response.body();
                PEggHunt.status = status;
                if (PEggHunt.data.isActive() && status.getEggOwner() != null && !status.getEggOwner().isEmpty()) {
                  final EntityPlayer player = world.func_72924_a(status.getEggOwner());
                  if (player != null)
                    if (PEggHunt.data.isEndEvent()) {
                      if (tick % 6 == 0) {
                        BlockPos pos = new BlockPos((Entity)player);
                        ChatComponentText text = new ChatComponentText("§8[§dEnd§8] §eLe porteur de l'oeuf se trouve en §8" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
                        ChatStyle style = new ChatStyle();
                        style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§bPour voir en temps réel la position utilisez un parchemin du dragon.")));
                        text.func_150255_a(style);
                        MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)text);
                      } 
                      RankedPlayerData.get((Entity)player).addValue("end", 0.16666666666666666D);
                      IPlayer iPlayer = PlayerController.getInstance().getExistingSyncPlayer(player.func_110124_au());
                      String factionName = (iPlayer == null) ? "Wilderness" : (iPlayer.hasFaction() ? iPlayer.getFaction().getName() : "Wilderness");
                      RankingEvents.pushRankingData(player, factionName, "end");
                    } else {
                      RankedPlayerData.get((Entity)player).addValue("egghunt", 0.16666666666666666D);
                      IPlayer iPlayer = PlayerController.getInstance().getExistingSyncPlayer(player.func_110124_au());
                      String factionName = (iPlayer == null) ? "Wilderness" : (iPlayer.hasFaction() ? iPlayer.getFaction().getName() : "Wilderness");
                      RankingEvents.pushRankingData(player, factionName, "egghunt");
                    }  
                  if (status.getDragonDeathTime() != -1L && player instanceof EntityPlayerMP) {
                    long diff = System.currentTimeMillis() - PEggHunt.lastReward;
                    if (PEggHunt.lastReward != -1L && diff > 600000L) {
                      ModuleEggHunt.getInstance().sendEggHunt((EntityPlayerMP)player, true, true);
                    } else {
                      ModuleEggHunt.getInstance().sendEggHunt((EntityPlayerMP)player, true, false);
                    } 
                    diff = System.currentTimeMillis() - status.getDragonDeathTime();
                    long duration = PEggHunt.data.isEndEvent() ? 1200000L : 3600000L;
                    long timeRemaining = duration - diff;
                    if (timeRemaining <= 300000L && tick % 6 == 0) {
                      ChatComponentText text = new ChatComponentText("§8[§dEnd§8] §eL'évènement prendra fin dans §c" + DurationConverter.fromMillisToString(timeRemaining));
                      ChatStyle style = new ChatStyle();
                      style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§bSi vous êtes dans l'end, vous serez automatiquement renvoyé dans la warzone.")));
                      text.func_150255_a(style);
                      MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)text);
                    } 
                    if (timeRemaining <= 0L) {
                      if (player instanceof EntityPlayerMP)
                        ModuleEggHunt.getInstance().sendEggHunt((EntityPlayerMP)player, false, false); 
                      if (PEggHunt.data.isEndEvent()) {
                        EndManager.getInstance().stop(player);
                      } else {
                        MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§cLe chasse touche à sa fin sur ce serveur."));
                        ApiServices.Http.getEggHuntService().setOwner(new EggHuntPlayerEggInput(null, null, null)).execute();
                        PEggHunt.status = null;
                        ChronosManager.getInstance().stopEvent(PEggHunt.data.getUuid(), new ChronosCallback<Planned>() {
                              public void onSuccess(Planned event) {
                                PEggHunt.data.setActive(false);
                                MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText(PEggHunt.getPrefix() + "§bFélicitation à §c" + player.func_70005_c_()));
                                CresusManager.getInstance().depositPlayerAsync(player.func_110124_au(), 50000.0D, "EggHunt -> end 2h timer", new CresusCallback<CresusResponse>() {
                                      public void onSuccess(CresusResponse arg0) {}
                                      
                                      public void onFail(CresusResponse arg0, Throwable arg1) {}
                                    });
                              }
                              
                              public void onFail(Planned event, Throwable error) {
                                if (Constants.MOD_ENV != Constants.Environment.DEV)
                                  error.printStackTrace(); 
                              }
                            });
                      } 
                    } 
                  } 
                } 
                if (status.getEggOwner() != null && !status.getEggOwner().isEmpty() && PEggHunt.data.isActive() && world.func_72924_a(status.getEggOwner()) == null) {
                  EggHuntPlayerEggInput playerInput = new EggHuntPlayerEggInput(null, null, PEggHunt.data.isEndEvent() ? new EggHuntLocation((PEnd.getServer().getConfig()).egg.get(0)) : PEggHunt.config.getEggPosition());
                  PEggHunt.setOwner(playerInput, false, ());
                } 
              } 
            } catch (Exception e) {
              if (!ForgeEnv.isDev())
                e.printStackTrace(); 
            } 
          } 
        }"Thread/EggHuntStatus");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\runnable\EggHuntStatusThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */