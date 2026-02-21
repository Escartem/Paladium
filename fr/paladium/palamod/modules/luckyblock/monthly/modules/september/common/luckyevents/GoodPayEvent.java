package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.server.commands.LuckyRouletteCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.SeptemberCommonModule;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.server.SCGoodPayPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class GoodPayEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "La bonne paye";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 350;
  
  private static final String TEXTURE_PATH = "september/good_pay";
  
  public static final int REWARDS_AMOUNT = 6;
  
  public static final String[] REWARDS_MESSAGES = new String[] { "Vous avez dépassé la limite de vitesse de 20 km/h. Vous devez payer une amende de 500$", "Vous avez dépensé 1250$ dans de nouveaux vêtements.", "Vous avez misé 1000$ sur le cheval Stewball. Il est tombé après la rivière et il a perdu ! Vous perdez votre mise.", "Vous venez de gagner à la loterie la somme de 4000$ !", "Vous avez fini le mois de septembre. Votre paye pour ce mois est de 5000$.", "Vous avez misé 1000$ sur le cheval Pégase, et il a gagné ! Vous triplez votre mise." };
  
  private static final String DEPOSIT_SUCCESS = "&6%s $ &aont été déposés sur votre compte en banque.";
  
  private static final String WITHDRAW_SUCCESS = "&6%s $ &aont été retirés de votre compte en banque.";
  
  public static GoodPayEvent INSTANCE;
  
  private Map<UUID, Integer> rewards;
  
  public GoodPayEvent() {
    INSTANCE = this;
    this.rewards = new HashMap<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    int rewardId = pickupRandomReward(player);
    SeptemberCommonModule.INSTANCE.getNetwork().sendTo((IMessage)new SCGoodPayPacket(rewardId), player);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { LuckyRouletteCommand.getUsageMessage("pay") });
  }
  
  public int pickupRandomReward(EntityPlayerMP player) {
    Random random = new Random();
    int rewardId = random.nextInt(6);
    this.rewards.put(player.func_110124_au(), Integer.valueOf(rewardId));
    return rewardId;
  }
  
  public int getRewardId(EntityPlayerMP player) {
    if (!this.rewards.containsKey(player.func_110124_au()))
      return 0; 
    return ((Integer)this.rewards.get(player.func_110124_au())).intValue();
  }
  
  public void giveReward(EntityPlayerMP player) {
    giveRewardById(player, ((Integer)this.rewards.get(player.func_110124_au())).intValue());
  }
  
  public boolean isWaitingReward(EntityPlayerMP player) {
    return this.rewards.containsKey(player.func_110124_au());
  }
  
  public void giveRewardById(EntityPlayerMP player, int id) {
    UUID uniqueId = player.func_110124_au();
    if (!this.rewards.containsKey(uniqueId))
      return; 
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { REWARDS_MESSAGES[id] });
    this.rewards.remove(uniqueId);
    switch (id) {
      case 0:
        withdraw(player, 500.0D);
        break;
      case 1:
        withdraw(player, 1250.0D);
        break;
      case 2:
        withdraw(player, 1000.0D);
        break;
      case 3:
        deposit(player, 4000.0D);
        break;
      case 4:
        deposit(player, 5000.0D);
        break;
      case 5:
        deposit(player, 3000.0D);
        break;
    } 
  }
  
  public void deposit(final EntityPlayerMP player, final double amount) {
    CresusManager.getInstance().depositPlayerAsync(player
        .func_110124_au(), amount, "La bonne paye", new CresusCallback<CresusResponse>() {
          public void onSuccess(CresusResponse arg0) {
            MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { String.format("&6%s $ &aont été déposés sur votre compte en banque.", new Object[] { Double.valueOf(this.val$amount) }) });
          }
          
          public void onFail(CresusResponse arg0, Throwable arg1) {}
        });
  }
  
  public void withdraw(final EntityPlayerMP player, final double amount) {
    CresusManager.getInstance().withdrawPlayerAsync(player
        .func_110124_au(), amount, "La bonne paye", new CresusCallback<CresusResponse>() {
          public void onSuccess(CresusResponse arg0) {
            MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { String.format("&6%s $ &aont été retirés de votre compte en banque.", new Object[] { Double.valueOf(this.val$amount) }) });
          }
          
          public void onFail(CresusResponse arg0, Throwable arg1) {}
        });
  }
  
  public String getName() {
    return "La bonne paye";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 350;
  }
  
  public String getTexture() {
    return "september/good_pay";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\GoodPayEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */