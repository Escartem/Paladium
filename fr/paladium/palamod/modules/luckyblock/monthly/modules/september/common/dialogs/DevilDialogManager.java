package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.dialogs;

import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.DevilsAdvocateEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.JudgeData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.Collections;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DevilDialogManager extends AbstractDialogManager {
  public static final String NPC_NAME = "Diable";
  
  private static final String WARNING_MESSAGE = "&cVous devriez aller voir le juge avant de parler au diable.";
  
  private static final String START_DIALOG = "Merci de me défendre”. Je n'ai rien fait, on m'accuse juste parce que je suis le diable, c'est injuste !”. Le juge n'a pas de preuve. Demande-lui quelles preuves il a pour que l'on puisse avancer";
  
  private static final String FIRST_ANSWER = "Je me déplace en planant, je ne laisse pas de traces de pas !";
  
  private static final String SECOND_ANSWER = "ça se voit que ce juge est complètement dépassé. Je parlais de mon blase, mon nom, et pas des blazes que l'on trouve en enfer et qui mettent le feu partout ";
  
  private static final String WIN_ANSWER = "Félicitations tu m'as bien défendu, je t'offre quelques cadeaux diaboliques !";
  
  private static final String WIN_MESSAGE = "&aFélicitations, vous avez bien défendu le diable !";
  
  public DevilDialogManager() {
    super("Diable", Collections.singletonList("Diable"));
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    JudgeData data = DevilsAdvocateEvent.INSTANCE.get(player.func_110124_au(), entity);
    if (data == null)
      return null; 
    if (data.getJudgeStep() == 0) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous devriez aller voir le juge avant de parler au diable." });
      return null;
    } 
    if (data.getJudgeStep() == 1 && data.getDevilStep() == 0) {
      data.incrementDevilStep();
      return (new CustomDialog("Diable", "Merci de me défendre”. Je n'ai rien fait, on m'accuse juste parce que je suis le diable, c'est injuste !”. Le juge n'a pas de preuve. Demande-lui quelles preuves il a pour que l'on puisse avancer")).setCloseGui();
    } 
    if (data.getJudgeStep() == 2 && data.getDevilStep() == 1) {
      data.incrementDevilStep();
      return (new CustomDialog("Diable", "Je me déplace en planant, je ne laisse pas de traces de pas !")).setCloseGui();
    } 
    if (data.getJudgeStep() == 3 && data.getDevilStep() == 2) {
      data.incrementDevilStep();
      return (new CustomDialog("Diable", "ça se voit que ce juge est complètement dépassé. Je parlais de mon blase, mon nom, et pas des blazes que l'on trouve en enfer et qui mettent le feu partout ")).setCloseGui();
    } 
    if (data.getJudgeStep() == 4 && data.getDevilStep() == 3) {
      data.incrementDevilStep();
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aFélicitations, vous avez bien défendu le diable !" });
      data.onWin(player);
      return (new CustomDialog("Diable", "Félicitations tu m'as bien défendu, je t'offre quelques cadeaux diaboliques !")).setCloseGui();
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\dialogs\DevilDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */