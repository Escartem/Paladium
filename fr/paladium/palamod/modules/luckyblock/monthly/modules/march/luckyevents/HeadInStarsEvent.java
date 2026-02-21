package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents;

import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class HeadInStarsEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "La tête dans les étoiles";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 90;
  
  private static final String TEXTURE_PATH = "march/head_in_stars";
  
  private static final String[] ACTIVATE_MESSAGES = new String[] { "§eVous avez la tête dans les étoiles !", "§eNe retirez pas cette potion, vous pourriez le regretter !" };
  
  public static final String REMOVE_MESSAGE = "§eVous êtes revenu sur Terre!";
  
  private static HeadInStarsEvent instance;
  
  private HashSet<UUID> pendingPlayers;
  
  public static HeadInStarsEvent getInstance() {
    return instance;
  }
  
  public HeadInStarsEvent() {
    instance = this;
    this.pendingPlayers = new HashSet<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    this.pendingPlayers.add(player.func_110124_au());
    player.func_70690_d(new PotionEffect(PotionsRegister.STAR.getPotionId(), MonthlyUtils.translateSecondsToTicks(120), 0));
    MonthlyUtils.sendMessage((EntityPlayer)player, ACTIVATE_MESSAGES);
  }
  
  public String getName() {
    return "La tête dans les étoiles";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 90;
  }
  
  public String getTexture() {
    return "march/head_in_stars";
  }
  
  public boolean isPending(EntityPlayer player) {
    return this.pendingPlayers.contains(player.func_110124_au());
  }
  
  public void remove(EntityPlayer player) {
    this.pendingPlayers.remove(player.func_110124_au());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\luckyevents\HeadInStarsEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */