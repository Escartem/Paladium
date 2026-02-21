package fr.paladium.palamod.modules.trixium.manager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.trixium.PTrixium;
import fr.paladium.palamod.modules.trixium.api.PlayerTrixiumDeposit;
import fr.paladium.palamod.modules.trixium.api.TrixiumAPI;
import fr.paladium.palamod.modules.trixium.api.TrixiumProfile;
import fr.paladium.palamod.modules.trixium.config.TrixiumRewardConfig;
import fr.paladium.palamod.modules.trixium.utils.TrixiumReward;
import fr.paladium.palamod.util.FastUUID;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import retrofit2.Response;

public class TrixiumManager {
  public static void getTrixum(EntityPlayer player, Consumer<TrixiumProfile> callback) {
    TrixiumAPI.getExecutor().submit(() -> {
          PlayerTrixiumDeposit deposit = new PlayerTrixiumDeposit(player.func_70005_c_());
          try {
            Response<TrixiumProfile> response = ApiServices.Http.getTrixiumService().getPlayerTrixium(FastUUID.toString((Entity)player), deposit).execute();
            if (response.isSuccessful()) {
              callback.accept(response.body());
            } else {
              callback.accept(null);
            } 
          } catch (IOException e) {
            e.printStackTrace();
            callback.accept(null);
          } 
        });
  }
  
  public static List<TrixiumReward> getRewards(EntityPlayer player, HashSet<String> claimed) {
    List<TrixiumReward> rewards = new ArrayList<>();
    for (TrixiumRewardConfig configReward : (PTrixium.getInstance().getConfig()).rewards) {
      TrixiumReward reward = new TrixiumReward(configReward, claimed.contains(configReward.getUuid()) ? TrixiumReward.State.PICKUP : TrixiumReward.State.NOT_AVAILABLE);
      rewards.add(reward);
    } 
    return rewards;
  }
  
  public static TrixiumRewardConfig getReward(String uuid) {
    Optional<TrixiumRewardConfig> reward = (PTrixium.getInstance().getConfig()).rewards.stream().filter(r -> r.getUuid().equals(uuid)).findFirst();
    return reward.isPresent() ? reward.get() : null;
  }
  
  @SideOnly(Side.SERVER)
  public static boolean performReward(TrixiumRewardConfig reward, EntityPlayer player) {
    try {
      ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
      for (String command : reward.getCommands())
        Bukkit.dispatchCommand((CommandSender)console, command.replace("{PLAYERNAME}", player.func_70005_c_())); 
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\manager\TrixiumManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */