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

public class JudgeDialogManager extends AbstractDialogManager {
  public static final String NPC_NAME = "Juge";
  
  private static final String START_DIALOG = "Votre client est coupable d'avoir mis le feu à la forêt. Allez le voir pour préparer votre défense";
  
  private static final String WRONG_ANSWER = "Ce n'est pas la réponse que j'attend, votre client ira dans une prison qui est l'enfer sur terre !";
  
  private static final String FIRST_DIALOG = "";
  
  private static final String FIRST_WIN_ANSWER = "Nous avons trouvé d'étranges traces de pas. C'est sûrement lui !";
  
  private static final String[] FIRST_ANSWERS = new String[] { "Monsieur le juge, quelles sont vos preuves ?", "Mon client m'a dit être innocent, croyez-le !", "Je ne suis pas avocat, qu'est-ce que je fais ici ?" };
  
  private static final String SECOND_DIALOG = "";
  
  private static final String SECOND_WIN_ANSWER = "Un témoin nous a confirmé que votre client lui aurait dit \"Tu connais mon blaze, et quand tu l'entends ça met le feu !\". C'est une preuve accablante !";
  
  private static final String[] SECOND_ANSWERS = new String[] { "Mon client était en train de planer, il ne souvient plus bien", "Mon client était en planeur, il n'était pas là !", "Mon client plane pour se déplacer" };
  
  private static final String THIRD_DIALOG = "";
  
  private static final String THIRD_WIN_ANSWER = "Votre argumentaire est bon. Votre client est libre, pour le moment…";
  
  private static final String[] THIRD_ANSWERS = new String[] { "Quand il parle de son blaze ça met souvent le feu !", "Quand il parle de son blaze c'est une expression", "Quand il parle de son blaze c'est parce qu'il est blasé" };
  
  private static final String LOOSE_MESSAGE = "&cVous n'avez pas réussi à défendre le diable !";
  
  public JudgeDialogManager() {
    super("Juge", Collections.singletonList("Juge"));
  }
  
  private CustomDialog getFirstDialog(EntityPlayerMP player, JudgeData data) {
    data.incrementJudgeStep();
    return (new CustomDialog("Juge", ""))
      .addOptions(FIRST_ANSWERS)
      .setCallback(callback -> {
          if (callback.intValue() == 1) {
            sendOtherDialog(player, (new CustomDialog("Juge", "Nous avons trouvé d'étranges traces de pas. C'est sûrement lui !")).setCloseGui());
          } else {
            sendOtherDialog(player, getLoseDialog(player, data));
          } 
        }).setCloseGui();
  }
  
  private CustomDialog getSecondDialog(EntityPlayerMP player, JudgeData data) {
    data.incrementJudgeStep();
    return (new CustomDialog("Juge", ""))
      .addOptions(SECOND_ANSWERS)
      .setCallback(callback -> {
          if (callback.intValue() == 3) {
            sendOtherDialog(player, (new CustomDialog("Juge", "Un témoin nous a confirmé que votre client lui aurait dit \"Tu connais mon blaze, et quand tu l'entends ça met le feu !\". C'est une preuve accablante !")).setCloseGui());
          } else {
            sendOtherDialog(player, getLoseDialog(player, data));
          } 
        }).setCloseGui();
  }
  
  private CustomDialog getThirdDialog(EntityPlayerMP player, JudgeData data) {
    data.incrementJudgeStep();
    return (new CustomDialog("Juge", ""))
      .addOptions(THIRD_ANSWERS)
      .setCallback(callback -> {
          if (callback.intValue() == 2) {
            sendOtherDialog(player, (new CustomDialog("Juge", "Votre argumentaire est bon. Votre client est libre, pour le moment…")).setCloseGui());
          } else {
            sendOtherDialog(player, getLoseDialog(player, data));
          } 
        });
  }
  
  private CustomDialog getLoseDialog(EntityPlayerMP player, JudgeData data) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous n'avez pas réussi à défendre le diable !" });
    data.onLose(player);
    return (new CustomDialog("Juge", "Ce n'est pas la réponse que j'attend, votre client ira dans une prison qui est l'enfer sur terre !"))
      .setCloseGui();
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    JudgeData data = DevilsAdvocateEvent.INSTANCE.get(player.func_110124_au(), entity);
    if (data == null)
      return null; 
    if (data.getJudgeStep() == 0 && data.getDevilStep() == 0) {
      data.incrementJudgeStep();
      return (new CustomDialog("Juge", "Votre client est coupable d'avoir mis le feu à la forêt. Allez le voir pour préparer votre défense")).setCloseGui();
    } 
    if (data.getJudgeStep() == 1 && data.getDevilStep() == 1)
      return getFirstDialog(player, data); 
    if (data.getJudgeStep() == 2 && data.getDevilStep() == 2)
      return getSecondDialog(player, data); 
    if (data.getJudgeStep() == 3 && data.getDevilStep() == 3)
      return getThirdDialog(player, data); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\dialogs\JudgeDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */