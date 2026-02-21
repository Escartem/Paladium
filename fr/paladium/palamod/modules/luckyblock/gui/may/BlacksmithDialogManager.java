package fr.paladium.palamod.modules.luckyblock.gui.may;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.ariane.Ariane;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.network.may.PacketOpenCustomDialogGui;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

public class BlacksmithDialogManager {
  public final int WAIT_DURATION = 86400000;
  
  public void startDialog(EntityPlayerMP player) {
    String title = "Armildir";
    CustomDialog startDialog = null;
    PlayerLuckyBlockProperties playerProp = PlayerLuckyBlockProperties.get((EntityPlayer)player);
    long time = System.currentTimeMillis() - playerProp.getBlacksmithDate();
    if (time < 86400000L) {
      startDialog = new CustomDialog(title, "Reviens me voir demain pour que je te pose une autre question.");
      startDialog.setCloseGui();
    } 
    if (startDialog == null && playerProp.getBlacksmithStep() == 5 && player.field_71071_by.func_70447_i() == -1) {
      startDialog = new CustomDialog(title, "Tu n'as pas assez de place dans ton inventaire.");
      startDialog.setCloseGui();
    } 
    if (startDialog == null && !hasTicket(player)) {
      startDialog = new CustomDialog(title, "Tu n'as pas de ticket dans ton inventaire.");
      startDialog.setCloseGui();
    } 
    if (startDialog == null)
      if (playerProp.getBlacksmithStep() == 1) {
        if (hasMetal(player)) {
          CustomDialog question = new CustomDialog(title, "La première question : préfères-tu une arme tranchante ou une arme plus originale ?");
          question.addOption("Je préfèrerais une arme tranchante");
          question.addOption("Je préférerais une arme originale");
          question.setCallback(ret -> {
                if (ret.intValue() == 1) {
                  addPoint(playerProp, ItemsRegister.RUNIC_AXE);
                  addPoint(playerProp, ItemsRegister.SWORD_TOO_BIG);
                  addPoint(playerProp, ItemsRegister.SITAR);
                  addPoint(playerProp, ItemsRegister.SWORD_PISTOL);
                } else if (ret.intValue() == 2) {
                  addPoint(playerProp, ItemsRegister.ENERGETIC_BOW_SWORD);
                  addPoint(playerProp, ItemsRegister.IRON_SKULL_HAMMER);
                  addPoint(playerProp, ItemsRegister.SWORD_GUITAR_OF_THE_APOCALYPSE);
                  addPoint(playerProp, ItemsRegister.BLUNDERBUSS);
                } 
                playerProp.setBlacksmithStep(2);
                playerProp.setBlacksmithDate(System.currentTimeMillis());
                CustomDialog questionOk = (new CustomDialog(title, "Intéressant, cela m'en dit déjà beaucoup sur toi. Reviens me voir demain pour que je te pose une autre question.")).setCloseGui();
                sendDialog(questionOk, player);
              });
          CustomDialog yesDialog = new CustomDialog(title, "Parfait, je suis heureux de pouvoir utiliser mon art pour un aventurier tel que toi ! Je vais te poser des questions pour savoir quel objet te correspond le mieux.");
          yesDialog.setCallback(ret -> sendDialog(question, player));
          CustomDialog noDialog = new CustomDialog(title, "Reviens me voir avec tes pièces de métal si tu veux que je crée un objet spécial pour toi !");
          noDialog.setCloseGui();
          startDialog = new CustomDialog(title, "Bienvenue chez Armildir, le meilleur forgeron de Paladium ! Je vois que tu as sur toi 10 pièces de métal, si tu me les donnes je pourrai réaliser pour toi un objet unique. Veux-tu tenter l'expérience ?");
          startDialog.addOption("Oui");
          startDialog.addOption("Non");
          startDialog.setCallback(ret -> {
                if (ret.intValue() == 1) {
                  if (playerProp.getBlacksmithEnergeticBowSwordPoint() > 0 || playerProp.getBlacksmithIronSkullHammerPoint() > 0 || playerProp.getBlacksmithRunicAxePoint() > 0 || playerProp.getBlacksmithSwordTooBigPoint() > 0)
                    saveMayReward(playerProp); 
                  removeMetal(player);
                  sendDialog(yesDialog, player);
                } else {
                  sendDialog(noDialog, player);
                } 
              });
        } else {
          startDialog = new CustomDialog(title, "Bienvenue chez Armildir, le meilleur forgeron de Paladium ! Je peux créer un objet pour toi , mais pour cela j'aurai besoin de 10 pièces de métal étrange. Amène-les-moi et je te créerai une arme à ton image !");
          startDialog.setCloseGui();
        } 
      } else if (playerProp.getBlacksmithStep() == 2) {
        startDialog = new CustomDialog(title, "Nouvelle question pour toi : penses-tu qu'une arme doit être belle ou vises-tu seulement la puissance ?");
        startDialog.addOption("Une arme doit aussi être belle");
        startDialog.addOption("Une arme doit seulement être puissante");
        startDialog.setCallback(ret -> {
              if (ret.intValue() == 1) {
                addPoint(playerProp, ItemsRegister.ENERGETIC_BOW_SWORD);
                addPoint(playerProp, ItemsRegister.RUNIC_AXE);
                addPoint(playerProp, ItemsRegister.SITAR);
                addPoint(playerProp, ItemsRegister.SWORD_PISTOL);
              } else if (ret.intValue() == 2) {
                addPoint(playerProp, ItemsRegister.SWORD_TOO_BIG);
                addPoint(playerProp, ItemsRegister.IRON_SKULL_HAMMER);
                addPoint(playerProp, ItemsRegister.SWORD_GUITAR_OF_THE_APOCALYPSE);
                addPoint(playerProp, ItemsRegister.BLUNDERBUSS);
              } 
              playerProp.setBlacksmithStep(3);
              playerProp.setBlacksmithDate(System.currentTimeMillis());
              CustomDialog questionOk = (new CustomDialog(title, "En te voyant, j'aurais parié que tu répondrais cela ! Je vais travailler un peu sur ton arme. Reviens me voir dans 24 heures pour la suite.")).setCloseGui();
              sendDialog(questionOk, player);
            });
      } else if (playerProp.getBlacksmithStep() == 3) {
        startDialog = new CustomDialog(title, "Je vois encore plusieurs possibilités d'armes pour toi. Cette question devrait m'aider à faire le bon choix : veux-tu que j'utilise des matériaux naturels ou un alliage de fer te suffit ?");
        startDialog.addOption("Je voudrais que tu utilises des matériaux naturels");
        startDialog.addOption("Je voudrais que tu utilises principalement un alliage de fer");
        startDialog.setCallback(ret -> {
              if (ret.intValue() == 1) {
                addPoint(playerProp, ItemsRegister.RUNIC_AXE);
                addPoint(playerProp, ItemsRegister.IRON_SKULL_HAMMER);
                addPoint(playerProp, ItemsRegister.SWORD_GUITAR_OF_THE_APOCALYPSE);
                addPoint(playerProp, ItemsRegister.BLUNDERBUSS);
              } else if (ret.intValue() == 2) {
                addPoint(playerProp, ItemsRegister.ENERGETIC_BOW_SWORD);
                addPoint(playerProp, ItemsRegister.SWORD_TOO_BIG);
                addPoint(playerProp, ItemsRegister.SITAR);
                addPoint(playerProp, ItemsRegister.SWORD_PISTOL);
              } 
              playerProp.setBlacksmithStep(4);
              playerProp.setBlacksmithDate(System.currentTimeMillis());
              CustomDialog questionOk = (new CustomDialog(title, "Tu es un client très surprenant. Malgré tout, je pense déjà pouvoir forger la base de l'arme. Reviens me voir demain. Je te poserai une dernière question qui m'aidera à savoir quelle finition je dois faire sur ton arme !")).setCloseGui();
              sendDialog(questionOk, player);
            });
      } else if (playerProp.getBlacksmithStep() == 4) {
        startDialog = new CustomDialog(title, "Avant-dernière question : pense tu qu’une arme doit exister seulement pour la guerre ou qu’elle doit être aussi utile ?");
        startDialog.addOption("Arme pour la guerre");
        startDialog.addOption("Arme utile");
        startDialog.setCallback(ret -> {
              if (ret.intValue() == 1) {
                addPoint(playerProp, ItemsRegister.SWORD_TOO_BIG);
                addPoint(playerProp, ItemsRegister.IRON_SKULL_HAMMER);
                addPoint(playerProp, ItemsRegister.ENERGETIC_BOW_SWORD);
                addPoint(playerProp, ItemsRegister.BLUNDERBUSS);
                addPoint(playerProp, ItemsRegister.SWORD_PISTOL);
              } else if (ret.intValue() == 2) {
                addPoint(playerProp, ItemsRegister.RUNIC_AXE);
                addPoint(playerProp, ItemsRegister.SITAR);
                addPoint(playerProp, ItemsRegister.SWORD_GUITAR_OF_THE_APOCALYPSE);
              } 
              playerProp.setBlacksmithStep(5);
              playerProp.setBlacksmithDate(System.currentTimeMillis());
              CustomDialog questionOk = (new CustomDialog(title, "C’est une réponse très pertinente. Plus qu’une question à répondre et tu auras ta nouvelle merveille")).setCloseGui();
              sendDialog(questionOk, player);
            });
      } else if (playerProp.getBlacksmithStep() == 5) {
        startDialog = new CustomDialog(title, "Voici ma dernière question. Que penses-tu de la technologie dans les armes, est-ce nécessaire ou est-ce que le talent du guerrier est ce qui rend une arme puissante ?");
        startDialog.addOption("La technologie peut être utile");
        startDialog.addOption("Le talent du guerrier est tout ce qui compte");
        startDialog.setCallback(ret -> {
              if (ret.intValue() == 1) {
                addPoint(playerProp, ItemsRegister.ENERGETIC_BOW_SWORD);
                addPoint(playerProp, ItemsRegister.SITAR);
                addPoint(playerProp, ItemsRegister.SWORD_PISTOL);
              } else if (ret.intValue() == 2) {
                addPoint(playerProp, ItemsRegister.RUNIC_AXE);
                addPoint(playerProp, ItemsRegister.IRON_SKULL_HAMMER);
                addPoint(playerProp, ItemsRegister.SWORD_TOO_BIG);
                addPoint(playerProp, ItemsRegister.SWORD_GUITAR_OF_THE_APOCALYPSE);
                addPoint(playerProp, ItemsRegister.BLUNDERBUSS);
              } 
              playerProp.setBlacksmithStep(1);
              playerProp.setBlacksmithDate(System.currentTimeMillis());
              Item reward = getRewardByPoint(playerProp);
              CustomDialog questionOk = (new CustomDialog(title, "Très bien, j'ai tous les éléments. Cela n'a pas été facile mais je pense que l'arme qui te conviendra le mieux est " + StatCollector.func_74838_a(reward.func_77658_a() + ".name") + ". La voici. Fais-en bon usage !")).setCloseGui();
              questionOk.setCallback(());
              sendDialog(questionOk, player);
            });
      }  
    Ariane.getServer().addDialog((EntityPlayer)player, startDialog);
    PalaMod.getNetwork().sendTo((IMessage)new PacketOpenCustomDialogGui(startDialog), player);
  }
  
  private void sendDialog(CustomDialog dialog, EntityPlayerMP player) {
    Ariane.getServer().addDialog((EntityPlayer)player, dialog);
    PalaMod.getNetwork().sendTo((IMessage)new PacketOpenCustomDialogGui(dialog), player);
  }
  
  private boolean hasMetal(EntityPlayerMP player) {
    int count = 0;
    for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
      ItemStack stack = player.field_71071_by.func_70301_a(i);
      if (stack != null && stack.func_77973_b() == ItemsRegister.STRANGE_METAL_PIECE)
        count += stack.field_77994_a; 
    } 
    if (count >= 10)
      return true; 
    return false;
  }
  
  private boolean hasTicket(EntityPlayerMP player) {
    for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
      ItemStack stack = player.field_71071_by.func_70301_a(i);
      if (stack != null && (stack
        .func_77973_b() == ItemsRegister.MAY_TICKET || stack
        .func_77973_b() == ItemsRegister.JUNE_TICKET || stack
        .func_77973_b() == ItemsRegister.JULY_TICKET)) {
        NBTTagCompound tag = stack.func_77978_p();
        if (tag != null && tag.func_74779_i("owner").equals(FastUUID.toString((Entity)player)))
          return true; 
      } 
    } 
    return false;
  }
  
  private void removeTicket(EntityPlayerMP player) {
    for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
      ItemStack stack = player.field_71071_by.func_70301_a(i);
      if (stack != null && (stack
        .func_77973_b() == ItemsRegister.MAY_TICKET || stack
        .func_77973_b() == ItemsRegister.JUNE_TICKET)) {
        player.field_71071_by.func_70299_a(i, null);
        break;
      } 
    } 
  }
  
  private void saveMayReward(PlayerLuckyBlockProperties playerProp) {
    Item reward = getRewardByPoint(playerProp);
    saveReward(playerProp, reward);
  }
  
  private void addPoint(PlayerLuckyBlockProperties playerProp, Item item) {
    if (item == ItemsRegister.ENERGETIC_BOW_SWORD) {
      int value = playerProp.getBlacksmithEnergeticBowSwordPoint();
      if (value != -1)
        playerProp.setBlacksmithEnergeticBowSwordPoint(value + 1); 
    } else if (item == ItemsRegister.RUNIC_AXE) {
      int value = playerProp.getBlacksmithRunicAxePoint();
      if (value != -1)
        playerProp.setBlacksmithRunicAxePoint(value + 1); 
    } else if (item == ItemsRegister.IRON_SKULL_HAMMER) {
      int value = playerProp.getBlacksmithIronSkullHammerPoint();
      if (value != -1)
        playerProp.setBlacksmithIronSkullHammerPoint(value + 1); 
    } else if (item == ItemsRegister.SWORD_TOO_BIG) {
      int value = playerProp.getBlacksmithSwordTooBigPoint();
      if (value != -1)
        playerProp.setBlacksmithSwordTooBigPoint(value + 1); 
    } else if (item == ItemsRegister.SITAR) {
      int value = playerProp.getBlacksmithSitarPoint();
      if (value != -1)
        playerProp.setBlacksmithSitarPoint(value + 1); 
    } else if (item == ItemsRegister.SWORD_GUITAR_OF_THE_APOCALYPSE) {
      int value = playerProp.getBlacksmithSwordGuitarApocalypsePoint();
      if (value != -1)
        playerProp.setBlacksmithSwordGuitarApocalypsePoint(value + 1); 
    } else if (item == ItemsRegister.SWORD_PISTOL) {
      int value = playerProp.getBlacksmithSwordPistolPoint();
      if (value != -1)
        playerProp.setBlacksmithSwordPistolPoint(value + 1); 
    } else if (item == ItemsRegister.BLUNDERBUSS) {
      int value = playerProp.getBlacksmithBlunderbussPoint();
      if (value != -1)
        playerProp.setBlacksmithBlunderbussPoint(value + 1); 
    } 
  }
  
  private void saveReward(PlayerLuckyBlockProperties playerProp, Item item) {
    if (item == ItemsRegister.ENERGETIC_BOW_SWORD) {
      playerProp.setBlacksmithEnergeticBowSwordPoint(-1);
    } else if (item == ItemsRegister.RUNIC_AXE) {
      playerProp.setBlacksmithRunicAxePoint(-1);
    } else if (item == ItemsRegister.IRON_SKULL_HAMMER) {
      playerProp.setBlacksmithIronSkullHammerPoint(-1);
    } else if (item == ItemsRegister.SWORD_TOO_BIG) {
      playerProp.setBlacksmithIronSkullHammerPoint(-1);
    } else if (item == ItemsRegister.SITAR) {
      playerProp.setBlacksmithSitarPoint(-1);
    } else if (item == ItemsRegister.SWORD_GUITAR_OF_THE_APOCALYPSE) {
      playerProp.setBlacksmithSwordGuitarApocalypsePoint(-1);
    } else if (item == ItemsRegister.SWORD_PISTOL) {
      playerProp.setBlacksmithSwordPistolPoint(-1);
    } else if (item == ItemsRegister.BLUNDERBUSS) {
      playerProp.setBlacksmithBlunderbussPoint(-1);
    } 
    if (playerProp.getBlacksmithEnergeticBowSwordPoint() != -1)
      playerProp.setBlacksmithEnergeticBowSwordPoint(0); 
    if (playerProp.getBlacksmithIronSkullHammerPoint() != -1)
      playerProp.setBlacksmithIronSkullHammerPoint(0); 
    if (playerProp.getBlacksmithRunicAxePoint() != -1)
      playerProp.setBlacksmithRunicAxePoint(0); 
    if (playerProp.getBlacksmithSwordTooBigPoint() != -1)
      playerProp.setBlacksmithSwordTooBigPoint(0); 
    if (playerProp.getBlacksmithSitarPoint() != -1)
      playerProp.setBlacksmithSitarPoint(0); 
    if (playerProp.getBlacksmithSwordGuitarApocalypsePoint() != -1)
      playerProp.setBlacksmithSwordGuitarApocalypsePoint(0); 
    if (playerProp.getBlacksmithSwordPistolPoint() != -1)
      playerProp.setBlacksmithSwordPistolPoint(0); 
    if (playerProp.getBlacksmithBlunderbussPoint() != -1)
      playerProp.setBlacksmithBlunderbussPoint(0); 
  }
  
  private Item getRewardByPoint(PlayerLuckyBlockProperties playerProp) {
    Item reward = null;
    int point = 0;
    int pointTest = playerProp.getBlacksmithEnergeticBowSwordPoint();
    if (pointTest > 0 && pointTest > point) {
      reward = ItemsRegister.ENERGETIC_BOW_SWORD;
      point = pointTest;
    } 
    pointTest = playerProp.getBlacksmithRunicAxePoint();
    if (pointTest > 0 && pointTest > point) {
      reward = ItemsRegister.RUNIC_AXE;
      point = pointTest;
    } 
    pointTest = playerProp.getBlacksmithIronSkullHammerPoint();
    if (pointTest > 0 && pointTest > point) {
      reward = ItemsRegister.IRON_SKULL_HAMMER;
      point = pointTest;
    } 
    pointTest = playerProp.getBlacksmithSwordTooBigPoint();
    if (pointTest > 0 && pointTest > point) {
      reward = ItemsRegister.SWORD_TOO_BIG;
      point = pointTest;
    } 
    pointTest = playerProp.getBlacksmithSitarPoint();
    if (pointTest > 0 && pointTest > point) {
      reward = ItemsRegister.SITAR;
      point = pointTest;
    } 
    pointTest = playerProp.getBlacksmithSwordGuitarApocalypsePoint();
    if (pointTest > 0 && pointTest > point) {
      reward = ItemsRegister.SWORD_GUITAR_OF_THE_APOCALYPSE;
      point = pointTest;
    } 
    pointTest = playerProp.getBlacksmithSwordPistolPoint();
    if (pointTest > 0 && pointTest > point) {
      reward = ItemsRegister.SWORD_PISTOL;
      point = pointTest;
    } 
    pointTest = playerProp.getBlacksmithBlunderbussPoint();
    if (pointTest > 0 && pointTest > point) {
      reward = ItemsRegister.BLUNDERBUSS;
      point = pointTest;
    } 
    return reward;
  }
  
  private void removeMetal(EntityPlayerMP player) {
    int countToRemove = 10;
    for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
      ItemStack stack = player.field_71071_by.func_70301_a(i);
      if (stack != null && stack.func_77973_b() == ItemsRegister.STRANGE_METAL_PIECE) {
        int count = stack.field_77994_a;
        if (count <= countToRemove) {
          countToRemove -= count;
          player.field_71071_by.func_70299_a(i, null);
        } else {
          stack.field_77994_a -= countToRemove;
          countToRemove = 0;
          player.field_71071_by.func_70299_a(i, stack);
          break;
        } 
      } 
    } 
  }
  
  private void reward(EntityPlayerMP player, Item reward) {
    if (player.field_71071_by.func_70447_i() != -1) {
      player.field_71071_by.func_70441_a(new ItemStack(reward));
    } else {
      PlayerUtils.sendMessage((EntityPlayer)player, "Vous n'avez pas assez de place dans votre inventaire.");
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\may\BlacksmithDialogManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */