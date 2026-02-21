package fr.paladium.achievement.server.config.additional;

import fr.paladium.achievement.AchievementLogger;
import fr.paladium.palaforgeutils.lib.command.CommandUtils;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.profile.ProfileMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AchievementReward {
  private static final String USERNAME_PLACEHOLDER = "{username}";
  
  private final AchievementRewardType type;
  
  private final String content;
  
  public AchievementReward(AchievementRewardType type, String content) {
    this.type = type;
    this.content = content;
  }
  
  public boolean giveReward(EntityPlayerMP player) {
    switch (this.type) {
      case ITEM:
        return giveItemReward(player);
      case COMMAND:
        return giveCommandReward(player);
      case PROFILE_BADGE:
        return giveBadgeReward(player);
    } 
    return false;
  }
  
  private boolean giveBadgeReward(final EntityPlayerMP player) {
    if (this.content == null || this.content.isEmpty())
      return false; 
    String playerUuid = player.func_110124_au().toString();
    double version = ProfileMod.getServer().getBadgeManager().getVersion();
    ProfileMod.getServer().getApiManager().getPrivateApi().addBadge(playerUuid, this.content, version).enqueue(new Callback<Void>() {
          @EverythingIsNonNull
          public void onResponse(Call<Void> call, Response<Void> response) {}
          
          @EverythingIsNonNull
          public void onFailure(Call<Void> call, Throwable throwable) {
            AchievementLogger.error("Failed to give badge to player " + player.func_70005_c_() + " with badge " + AchievementReward.this.content);
            throwable.printStackTrace();
          }
        });
    return true;
  }
  
  private boolean giveCommandReward(EntityPlayerMP player) {
    if (this.content == null || this.content.isEmpty() || !this.content.contains("{username}"))
      return false; 
    String command = this.content.replace("{username}", player.func_70005_c_());
    CommandUtils.performCommands((EntityPlayer)player, new String[] { command });
    return true;
  }
  
  private boolean giveItemReward(EntityPlayerMP player) {
    ItemStack stack = ItemStackSerializer.read(this.content);
    if (stack == null)
      return false; 
    InventoryUtils.giveOrDropitems((EntityPlayer)player, stack);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\server\config\additional\AchievementReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */