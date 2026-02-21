package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import fr.paladium.factions.api.entity.IEconomyLoggedEntity;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.api.faction.IFactionPlayer;
import fr.paladium.factions.core.faction.economy.TransactionNature;
import fr.paladium.factions.core.player.Player;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.factions.core.utils.ControlResult;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class FelonEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Félon !";
  
  private static final String TEXTURE_PATH = "july/felon";
  
  private static final int RARITY = 500;
  
  private static final boolean IS_BAD = true;
  
  private static final int WITHDRAW_AMOUNT = 2500;
  
  private static final String LUCKY_MESSAGE = "&eVous avez eu de la chance, votre argent n'a pas été volé !";
  
  private static final String UNLUCKY_MESSAGE = "&cJ’en avais besoin pour piller le bastion de Salazar ! (2500$)";
  
  private static final String FELON_MESSAGE = "&6Je ne suis pas sûr que ton chef de faction apprécie que tu voles dans son coffre ! ";
  
  public void perform(final EntityPlayerMP forgePlayer, final int x, final int y, final int z) {
    PlayerController controller = PlayerController.getInstance();
    Player player = controller.getLoadedPlayer(forgePlayer.func_70005_c_());
    if (player == null || !player.hasFaction()) {
      MonthlyUtils.sendMessage((EntityPlayer)forgePlayer, new String[] { "&eVous avez eu de la chance, votre argent n'a pas été volé !" });
      return;
    } 
    IFaction faction = player.getFaction();
    IEconomyLoggedEntity iEconomyLoggedEntity = faction.getEconomyEntity();
    if (!iEconomyLoggedEntity.hasMoney(2500.0D)) {
      MonthlyUtils.sendMessage((EntityPlayer)forgePlayer, new String[] { "&eVous avez eu de la chance, votre argent n'a pas été volé !" });
      return;
    } 
    IFactionPlayer factionPlayer = faction.getMemberEntity().getFactionPlayer(player.getUuid());
    if (factionPlayer == null) {
      MonthlyUtils.sendMessage((EntityPlayer)forgePlayer, new String[] { "&eVous avez eu de la chance, votre argent n'a pas été volé !" });
      return;
    } 
    iEconomyLoggedEntity.removeMoney(factionPlayer, 2500.0D, TransactionNature.MONEY, "Félon !", new ControlResult<Boolean>() {
          public void onCallback() {
            MonthlyUtils.sendMessage((EntityPlayer)forgePlayer, new String[] { "&cJ’en avais besoin pour piller le bastion de Salazar ! (2500$)", "&6Je ne suis pas sûr que ton chef de faction apprécie que tu voles dans son coffre ! " });
            SoundUtils.SKELETON_HURT.playSound(forgePlayer, x, y, z, 1.0F, 1.0F);
          }
        });
  }
  
  public String getName() {
    return "Félon !";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 500;
  }
  
  public String getTexture() {
    return "july/felon";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\FelonEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */