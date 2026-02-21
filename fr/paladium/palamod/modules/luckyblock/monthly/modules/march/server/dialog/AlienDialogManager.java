package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.dialog;

import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.data.MarchPlayer;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.EncounterThirdKindEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data.DialogData;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data.EncounterThirdKindData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class AlienDialogManager extends AbstractDialogManager {
  public static final String NPC_NAME = "Alien";
  
  public static final long NPC_DURATION = TimeUnit.MINUTES.toMillis(10L);
  
  public static final String NPC_SKIN_PATH = "palamod:textures/entities/npc/encounter_third_kind.png";
  
  private static final String FAIL_MESSAGE = "§cTu as fait peur à l'alien et il a fui !";
  
  private static final String SOUND_NAME = "alien";
  
  private static final DialogData FIRST_DIALOG = DialogData.builder()
    .dialog(". e 3 8 H7 SH S UF G")
    .answers(new String[] { "Oui", "Non" }).build();
  
  private static final DialogData SECOND_DIALOG = DialogData.builder()
    .dialog("01000001 01101101 01101001")
    .answers(new String[] { "Oui", "Non" }).build();
  
  private static final DialogData THIRD_DIALOG = DialogData.builder()
    .dialog("−·· ·· − · ···  −− −−− ··  ··− −· ·  ·−−· ···· ·−· ·− ··· ·  ·−·· −−− −· −−· ··− ·")
    .answers(new String[] { "Bien sûr", "Bonjour, comment allez-vous", "L'ornithorynque (Ornithorhynchus anatinus) est un animal semi-aquatique.", "Non" }).build();
  
  private static final DialogData FOURTH_DIALOG = DialogData.builder()
    .dialog("Merci ! Grâce à ton aide j'ai réussi à décoder ton langage. Je suis si heureux de découvrir une espèce intelligente ! Voici une récompense intergalactique pour ton aide.")
    
    .answers(new String[] { "Recevoir la récompense" }).build();
  
  private static final DialogData SEE_ALIEN_DIALOG = DialogData.builder()
    .dialog("Content de te retrouver ! Mon espèce est fan de la tienne ! Voici un cadeau qu'un de mes amis m'a transmis pour les humains. Ce n'est pas rare chez nous mais ça à l'air précieux pour vous !")
    
    .answers(new String[] { "Ok" }).build();
  
  public AlienDialogManager() {
    super("Alien");
  }
  
  public CustomDialog getDialog(EntityPlayerMP player, Entity entity) {
    MarchPlayer marchPlayer = MarchPlayer.get((EntityPlayer)player);
    EncounterThirdKindEvent event = EncounterThirdKindEvent.getInstance();
    UUID playerUniqueId = player.func_110124_au();
    EncounterThirdKindData data = event.get(playerUniqueId);
    if (marchPlayer == null || data == null || !entity.func_110124_au().equals(data.getEntityUniqueId()))
      return null; 
    int id = data.getCurrentDialogId();
    if (id < 0 || id > 3)
      return null; 
    if (id == 0 && marchPlayer.isHasSeenAlien())
      return CustomDialog.of(this, SEE_ALIEN_DIALOG.getDialog())
        .addOptions(SEE_ALIEN_DIALOG.getAnswers())
        .setCallback(answerId -> onAlienSeen(player, entity)); 
    if (id == 0)
      return CustomDialog.of(this, FIRST_DIALOG.getDialog())
        .addOptions(FIRST_DIALOG.getAnswers())
        .setCallback(answerId -> {
            marchPlayer.setHasSeenAlien(true);
            if (answerId.intValue() == 1) {
              data.increment();
            } else {
              onFail(data, player, entity);
            } 
          }); 
    if (id == 1)
      return CustomDialog.of(this, SECOND_DIALOG.getDialog())
        .addOptions(SECOND_DIALOG.getAnswers())
        .setCallback(answerId -> {
            if (answerId.intValue() == 1) {
              data.increment();
            } else {
              onFail(data, player, entity);
            } 
          }); 
    if (id == 2)
      return CustomDialog.of(this, THIRD_DIALOG.getDialog())
        .addOptions(THIRD_DIALOG.getAnswers())
        .setCallback(answerId -> {
            if (answerId.intValue() == 3) {
              data.increment();
            } else {
              onFail(data, player, entity);
            } 
          }); 
    if (id == 3)
      return CustomDialog.of(this, FOURTH_DIALOG.getDialog())
        .addOptions(FOURTH_DIALOG.getAnswers())
        .setCallback(answerId -> onSuccess(data, player, entity)); 
    return null;
  }
  
  private void onAlienSeen(EntityPlayerMP player, Entity entity) {
    EncounterThirdKindEvent.getInstance().remove(player.func_110124_au());
    if (entity == null || entity.field_70128_L)
      return; 
    entity.func_70106_y();
    InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(BlocksRegister.COLORED_LAMP, 1));
  }
  
  private void onSuccess(EncounterThirdKindData data, EntityPlayerMP player, Entity entity) {
    EncounterThirdKindEvent.getInstance().remove(player.func_110124_au());
    if (entity == null || entity.field_70128_L)
      return; 
    entity.func_70106_y();
    InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(BlocksRegister.WIRELESS_LEVER, 1));
    InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(BlocksRegister.BLOCK_PALADIUM, 1));
  }
  
  private void onFail(EncounterThirdKindData data, EntityPlayerMP player, Entity entity) {
    EncounterThirdKindEvent.getInstance().remove(player.func_110124_au());
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cTu as fait peur à l'alien et il a fui !" });
    if (entity == null || entity.field_70128_L)
      return; 
    entity.func_70106_y();
    MonthlyUtils.playSound(player, "soft_fail");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\dialog\AlienDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */