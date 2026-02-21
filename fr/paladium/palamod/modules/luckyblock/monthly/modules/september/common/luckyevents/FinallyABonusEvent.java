package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class FinallyABonusEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Enfin une prime";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 400;
  
  private static final String TEXTURE_PATH = "september/finally_a_bonus";
  
  private static final double PRIME_AMOUNT = 3000.0D;
  
  private static final String PRIME_MESSAGE = "&eIl y a une prime sur votre tête, le prochain joueur qui vous tuera obtiendra &63000.0 &e$ !";
  
  private static final String PRIME_SUCCESS_MESSAGE = "&eIl y avait une prime sur la tête de &d%s &e, vous venew de gagner &63000.0 &e$ !";
  
  private static final String PRIME_ERROR_MESSAGE = "&cUne erreur est survenue lors du transfert de la prime.";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerLuckyBlockProperties properties = PlayerLuckyBlockProperties.get((EntityPlayer)player);
    if (properties == null)
      return; 
    properties.setPrime(true);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&eIl y a une prime sur votre tête, le prochain joueur qui vous tuera obtiendra &63000.0 &e$ !" });
  }
  
  @SubscribeEvent
  public void onKill(LivingDeathEvent event) {
    Entity entity = event.entity;
    if (entity.field_70170_p.field_72995_K)
      return; 
    if (!(entity instanceof EntityPlayerMP))
      return; 
    final EntityPlayerMP target = (EntityPlayerMP)entity;
    PlayerLuckyBlockProperties properties = PlayerLuckyBlockProperties.get((EntityPlayer)target);
    if (properties == null)
      return; 
    Entity killer = event.source.func_76346_g();
    if (!(killer instanceof EntityPlayerMP))
      return; 
    if (!hasPrime(properties))
      return; 
    final EntityPlayerMP killerMP = (EntityPlayerMP)killer;
    properties.setPrime(false);
    CresusManager.getInstance().depositPlayerAsync(killerMP
        .func_110124_au(), 3000.0D, "Prime de " + target
        .func_70005_c_(), new CresusCallback<CresusResponse>() {
          public void onSuccess(CresusResponse arg0) {
            MonthlyUtils.sendMessage((EntityPlayer)killerMP, new String[] { String.format("&eIl y avait une prime sur la tête de &d%s &e, vous venew de gagner &63000.0 &e$ !", new Object[] { this.val$target.func_70005_c_() }) });
          }
          
          public void onFail(CresusResponse arg0, Throwable arg1) {
            MonthlyUtils.sendMessage((EntityPlayer)killerMP, new String[] { "&cUne erreur est survenue lors du transfert de la prime." });
          }
        });
  }
  
  private boolean hasPrime(PlayerLuckyBlockProperties properties) {
    return properties.isPrime();
  }
  
  public String getName() {
    return "Enfin une prime";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 400;
  }
  
  public String getTexture() {
    return "september/finally_a_bonus";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\FinallyABonusEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */