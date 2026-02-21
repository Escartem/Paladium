package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.server.data;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.luckyevents.may.MeilleurAmi;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

public class BestFriendData {
  public static final double MAX_DISTANCE = 5.0D;
  
  public static final long START_EXPIRE_DURATION = TimeUnit.SECONDS.toMillis(30L);
  
  public static final long NEAR_TO_DURATION = TimeUnit.MINUTES.toMillis(5L);
  
  private static final String START_MESSAGE = "§aTrouve vite un joueur et reste près de lui pour obtenir une récompense.";
  
  private static final String[] SUCCESS_MESSAGES = new String[] { "§aVous venez de vous lier d'amitié avec §d%s§a.", "§aRestez à moins de 5.0 blocs de lui pendant " + TimeUnit.MILLISECONDS
      
      .toMinutes(NEAR_TO_DURATION) + " minutes", "§apour obtenir une récompense." };
  
  private static final String FAIL_MESSAGE = "§cVous avez échoué à vous lier d'amitié avec un joueur.";
  
  private static final String REWARD_MESSAGE = "§aVous avez obtenu une récompense pour avoir été proche de votre meilleur ami.";
  
  private final UUID playerUniqueId;
  
  private UUID bestFriendUniqueId;
  
  private long bestFriendExpireMillis;
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public UUID getBestFriendUniqueId() {
    return this.bestFriendUniqueId;
  }
  
  public long getBestFriendExpireMillis() {
    return this.bestFriendExpireMillis;
  }
  
  public BestFriendData(EntityPlayerMP player) {
    this.playerUniqueId = player.func_110124_au();
    this.bestFriendUniqueId = null;
    this.bestFriendExpireMillis = START_EXPIRE_DURATION + System.currentTimeMillis();
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aTrouve vite un joueur et reste près de lui pour obtenir une récompense." });
  }
  
  private void handeLoose(EntityPlayerMP player) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cVous avez échoué à vous lier d'amitié avec un joueur." });
    MonthlyUtils.stopHeliosEvent(player, MeilleurAmi.class);
  }
  
  private void handleSuccess(EntityPlayerMP player) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aVous avez obtenu une récompense pour avoir été proche de votre meilleur ami." });
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.PLAYER_SADDLE));
  }
  
  private EntityPlayerMP getClosetPlayer(EntityPlayerMP player) {
    AxisAlignedBB boundingBox = AxisAlignedBB.func_72330_a(player.field_70165_t - 5.0D, player.field_70163_u - 5.0D, player.field_70161_v - 5.0D, player.field_70165_t + 5.0D, player.field_70163_u + 5.0D, player.field_70161_v + 5.0D);
    List<EntityPlayerMP> players = player.field_70170_p.func_72872_a(EntityPlayerMP.class, boundingBox);
    EntityPlayerMP bestFriend = null;
    double bestDistance = Double.MAX_VALUE;
    for (EntityPlayerMP other : players) {
      if (other.equals(player))
        continue; 
      double distance = player.func_70032_d((Entity)other);
      if (distance < bestDistance) {
        bestFriend = other;
        bestDistance = distance;
      } 
    } 
    return bestFriend;
  }
  
  public boolean isExpired(EntityPlayerMP player, long now) {
    if (player == null)
      return true; 
    if (this.bestFriendExpireMillis < now) {
      MonthlyUtils.stopHeliosEvent(player, MeilleurAmi.class);
      if (this.bestFriendUniqueId == null) {
        handeLoose(player);
      } else {
        handleSuccess(player);
      } 
      return true;
    } 
    if (this.bestFriendUniqueId == null) {
      EntityPlayerMP bestFriend = getClosetPlayer(player);
      if (bestFriend != null) {
        MonthlyUtils.startTimedHeliosEvent(player, MeilleurAmi.class, NEAR_TO_DURATION);
        this.bestFriendUniqueId = bestFriend.func_110124_au();
        this.bestFriendExpireMillis = now + NEAR_TO_DURATION;
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { String.format(SUCCESS_MESSAGES[0], new Object[] { bestFriend.getDisplayName() }) });
        for (int i = 1; i < SUCCESS_MESSAGES.length; i++) {
          MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { SUCCESS_MESSAGES[i] });
        } 
      } 
    } 
    if (this.bestFriendUniqueId != null) {
      EntityPlayerMP bestFriend = MonthlyUtils.getPlayer(this.bestFriendUniqueId);
      if (bestFriend == null || player.func_70032_d((Entity)bestFriend) > 5.0D) {
        this.bestFriendUniqueId = null;
        this.bestFriendExpireMillis = -1L;
        handeLoose(player);
        return true;
      } 
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\server\data\BestFriendData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */