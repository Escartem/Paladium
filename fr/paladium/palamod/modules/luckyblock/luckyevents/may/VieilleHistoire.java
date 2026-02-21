package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.ariane.Ariane;
import fr.paladium.ariane.lib.dialog.ArianeDialog;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.gui.may.CustomDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.may.PacketOpenBookSeller;
import fr.paladium.palamod.modules.luckyblock.network.may.PacketOpenCustomDialogGui;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.util.FastUUID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class VieilleHistoire extends ALuckyEvent {
  public static final String intro = "J'ai une histoire pour toi !";
  
  public static final String[] stories = new String[] { "Un porteur d'eau transportait deux grandes jarres aux extrémités de sa planche. L'une des jarres était fêlée et perdait presque la moitié de son eau au cours de chaque voyage, alors que l'autre conservait toute son eau de source jusqu'à la maison du maître. La situation dura ainsi pendant deux ans. Deux ans au cours desquels le porteur d'eau ne livra qu'une jarre et demie d'eau, chaque jour, à son maître. Bien sûr, la jarre sans défaut était fière de sa performance : elle parvenait à remplir sa fonction du début à la fin sans faillir. Mais la jarre abîmée, elle, avait honte de son imperfection. Et se sentait démoralisée de ne pouvoir accomplir que la moitié de sa tâche. Au bout de ces deux ans, qu'elle considérait comme un échec complet, la jarre abîmée dit au porteur d'eau, un jour qu'il la remplissait à la source : Je me sens coupable et je te prie de m'excuser… – Pourquoi ? demanda le porteur d'eau. De quoi as-tu honte ? – Depuis deux ans, je n'ai réussi à porter que la moitié de ma charge à notre maître à cause de cette brèche qui fait fuir l'eau. Tu n'obtiens pas la reconnaissance complète de tes efforts », lui expliqua la jarre abîmée. Touché par cet aveu et plein de compassion pour la jarre, le porteur d'eau lui répondit : « Je vais te demander quelque chose. Tout à l'heure, quand nous reprendrons le chemin du retour vers la maison du maître, je veux que tu observes les fleurs qui poussent sur le bord du sentier… » Au fur et à mesure que le porteur d'eau avançait le long de la colline, la vieille jarre apercevait le bord du chemin couvert de fleurs baignées de soleil. Le porteur d'eau dit alors à la jarre : « Ne t'es-tu pas aperçue que toutes ces belles fleurs, elles poussent de ton côté du chemin, alors qu'on n'en voit à peine du côté de la jarre en bon état ? J'ai toujours su que tu perdais de l'eau et j'en ai tiré parti. J'ai planté des semences de ton côté du chemin. Et chaque jour, tu les as arrosées de ton précieux contenu. Grâce à toi, j'ai pu pendant ces deux ans cueillir de magnifiques fleurs qui ont décoré la table du maître. Sans toi, jamais je n'aurais trouvé de fleurs aussi fraîches, aussi gracieuses, aussi colorées. »", "Un jour, apparut un petit trou dans un cocon. Un homme, qui passait là par hasard, s'arrêta, et durant de longues heures, observa le papillon qui s'efforçait de sortir par le petit trou. Après un long moment, le papillon semblait avoir abandonné, et le trou demeurait toujours aussi petit. On aurait dit que le papillon avait fait tout ce qu'il pouvait, et ne pouvait plus rien tenter d'autre. Alors l'homme décida d'aider le papillon. Il prit un canif et ouvrit le cocon. Le papillon sortit aussitôt. Mais son corps était maigre et engourdi ; ses ailes étaient peu développées et bougeaient à peine. L'homme continua à observer le papillon, pensant que, d'un moment à l'autre, ses ailes s'ouvriraient et qu'elles seraient capables de supporter son corps pour qu'il puisse enfin s'envoler. Hélas, il n'en fut rien ! Le papillon passa le reste de son existence à se traîner par terre avec son maigre corps et ses ailes rabougries. Jamais il ne put voler. Ce que l'homme, avec son geste de gentillesse et son intention d'aider, ne comprenait pas, c'est que le passage par le trou étroit du cocon, était l'effort nécessaire pour que le papillon puisse transmettre le liquide de son corps à ses ailes, de manière à pouvoir voler. C'était le moule à travers lequel la vie le faisait passer pour grandir et se développer.", "Un jeune ange tenait un jour une conversation avec le gardien des cieux. Il lui dit : \"J'aimerais savoir comment est le paradis et comment est l'enfer ?\"... Le jeune ange fut conduit vers deux portes. Le gardien des cieux ouvrit l'une d'entre elles et permit ainsi à l'ange de regarder à l'intérieur. Au milieu de la pièce, il y avait une immense table ronde. Et, au milieu de la table, il y avait une grosse marmite contenant un ragoût à l'arôme délicieux… Le jeune ange saliva d'envie. Les personnes assises autour de la table étaient maigres et livides. Elles avaient, toutes, l'air affamé. Elles tenaient des cuillères aux très longs manches, attachées à leurs bras. Toutes pouvaient atteindre le plat de ragoût et remplir une cuillerée. Mais, comme le manche de la cuillère était plus long que leurs  bras, elles ne pouvaient ramener les cuillères à leur bouche. Le jeune ange frissonna à la vue de leur misère et de leurs souffrances. \"Alors, c'est cela, l'enfer ?\" se demanda-t-il. Tous deux se dirigèrent alors vers la seconde porte. La porte s'ouvrit, et, surprise, la scène était identique à la précédente. Il y avait la grande table ronde, la marmite de délicieux ragoût, qui fit encore saliver le jeune ange. Les personnes autour de la table étaient également équipées de cuillères aux longs manches. Mais, cette fois, les gens étaient bien nourris, replets, souriants et se parlaient en riant. Le jeune ange s'exclama : \"Je ne comprends pas !\" \"Eh bien, c'est simple\" fut la réponse. \"C'est juste une question d'habileté. Ils ont appris à se nourrir les uns les autres." };
  
  private static final String conclusion = "Quelle est la morale de cette histoire ?";
  
  public static final String[][] answers = new String[][] { { "On fait les meilleures soupes dans les vieilles casseroles", "Les imperfections peuvent aussi être des forces", "Être brisé cela ne fait pas si mal" }, { "L'effort apporte le réconfort", "Les efforts nous rendent plus fort", "Être gentil avec les autres peut les aider à s'envoler", "Les efforts nous rendent plus fort", "Être gentil avec les autres peut les aider à s'envoler" }, { "L'enfer c'est les autres", "Il ne faut pas manger de trop car le trop tue le bien", "L'individualisme est-ce qui rapproche le plus les humains de l'enfer" } };
  
  public static final int[] solutions = new int[] { 2, 2, 3 };
  
  private static final String win = "Félicitations, c'est la bonne réponse ! Voici le livre de cette histoire en récompense.";
  
  private static final String lost = "Perdu, ce n'est pas la bonne réponse !";
  
  public static final HashSet<UUID> WAITING_PLAYERS = new HashSet<>();
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    final int id = ThreadLocalRandom.current().nextInt(0, 3);
    final String playerId = FastUUID.toString((Entity)player);
    EntityNPC npc = new EntityNPC(player.field_70170_p, "palamod:textures/entity/npc/old_story_" + (id + 1) + ".png", x, y, z, 30000L, 600000L) {
        protected boolean func_70085_c(EntityPlayer player) {
          if (playerId.equals(FastUUID.toString((Entity)player))) {
            this.interact = true;
            CustomDialog mainDiag = new CustomDialog("CONTEUR", "J'ai une histoire pour toi !");
            List<String> pages = VieilleHistoire.splitStory(id);
            List<CustomDialog> dialogs = new ArrayList<>();
            for (int i = 0; i < pages.size(); i++) {
              CustomDialog dialog = new CustomDialog("CONTEUR", pages.get(i));
              if (i == 0) {
                mainDiag.setCallback(v1 -> VieilleHistoire.this.sendDialog(dialog, (EntityPlayerMP)player));
              } else {
                ((CustomDialog)dialogs.get(i - 1)).setCallback(v1 -> VieilleHistoire.this.sendDialog(dialog, (EntityPlayerMP)player));
              } 
              dialogs.add(dialog);
            } 
            CustomDialog winDialog = new CustomDialog("CONTEUR", "Félicitations, c'est la bonne réponse ! Voici le livre de cette histoire en récompense.");
            winDialog.setCloseGui();
            winDialog.setCallback(v1 -> {
                  VieilleHistoire.WAITING_PLAYERS.add(player.func_110124_au());
                  PalaMod.network.sendTo((IMessage)new PacketOpenBookSeller(), (EntityPlayerMP)player);
                  func_70106_y();
                });
            CustomDialog loseDialog = new CustomDialog("CONTEUR", "Perdu, ce n'est pas la bonne réponse !");
            loseDialog.setCloseGui();
            loseDialog.setCallback(v1 -> func_70106_y());
            CustomDialog questionDialog = new CustomDialog("CONTEUR", "Quelle est la morale de cette histoire ?");
            questionDialog.addOption(VieilleHistoire.answers[id][0]);
            questionDialog.addOption(VieilleHistoire.answers[id][1]);
            questionDialog.addOption(VieilleHistoire.answers[id][2]);
            questionDialog.setCallback(v1 -> {
                  if (v1.intValue() == VieilleHistoire.solutions[id]) {
                    VieilleHistoire.this.sendDialog(winDialog, (EntityPlayerMP)player);
                  } else {
                    VieilleHistoire.this.sendDialog(loseDialog, (EntityPlayerMP)player);
                  } 
                });
            ((CustomDialog)dialogs.get(dialogs.size() - 1)).setCallback(v1 -> VieilleHistoire.this.sendDialog(questionDialog, (EntityPlayerMP)player));
            VieilleHistoire.this.sendDialog(mainDiag, (EntityPlayerMP)player);
            return true;
          } 
          PlayerUtils.sendMessage(player, "Ce conteur n'a pas été activé par vous.");
          return false;
        }
      };
    player.field_70170_p.func_72838_d((Entity)npc);
  }
  
  public void sendDialog(CustomDialog dialog, EntityPlayerMP player) {
    Ariane.getServer().addDialog((EntityPlayer)player, (ArianeDialog)dialog);
    PalaMod.getNetwork().sendTo((IMessage)new PacketOpenCustomDialogGui(dialog), player);
  }
  
  public static boolean canBeRewarded(EntityPlayerMP player) {
    return WAITING_PLAYERS.contains(player.func_110124_au());
  }
  
  public static ItemStack getReward(int rewardId) {
    ItemStack stack = new ItemStack((Item)Items.field_151134_bR);
    Map<Integer, Integer> enchants = new HashMap<>();
    switch (rewardId) {
      case 0:
        enchants.put(Integer.valueOf(Enchantment.field_77332_c.field_77352_x), Integer.valueOf(4));
        enchants.put(Integer.valueOf(Enchantment.field_77347_r.field_77352_x), Integer.valueOf(3));
        EnchantmentHelper.func_82782_a(enchants, stack);
        break;
      case 1:
        enchants.put(Integer.valueOf(Enchantment.field_77338_j.field_77352_x), Integer.valueOf(5));
        enchants.put(Integer.valueOf(Enchantment.field_77347_r.field_77352_x), Integer.valueOf(3));
        break;
      case 2:
        enchants.put(Integer.valueOf(Enchantment.field_77349_p.field_77352_x), Integer.valueOf(5));
        enchants.put(Integer.valueOf(Enchantment.field_77347_r.field_77352_x), Integer.valueOf(3));
        break;
      case 3:
        enchants.put(Integer.valueOf(Enchantment.field_77345_t.field_77352_x), Integer.valueOf(5));
        enchants.put(Integer.valueOf(Enchantment.field_77347_r.field_77352_x), Integer.valueOf(3));
        break;
    } 
    EnchantmentHelper.func_82782_a(enchants, stack);
    return stack;
  }
  
  public static void reward(EntityPlayerMP player, int rewardId) {
    if (canBeRewarded(player)) {
      ItemUtils.spawnItemAtEntity((Entity)player, getReward(rewardId));
      WAITING_PLAYERS.remove(player.func_110124_au());
    } else {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Vous n'avez pas de récompense en attente." });
    } 
  }
  
  public static List<String> splitStory(int story) {
    List<String> result = new ArrayList<>();
    String[] words = stories[story].split(" ");
    StringBuilder currentString = new StringBuilder();
    for (String word : words) {
      if (currentString.length() + word.length() > 256) {
        result.add(currentString.toString());
        currentString = new StringBuilder();
      } 
      currentString.append(word).append(" ");
    } 
    if (currentString.length() > 0)
      result.add(currentString.toString()); 
    return result;
  }
  
  public String getName() {
    return "Une vieille histoire";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 110;
  }
  
  public String getTexture() {
    return "may/vieille_histoire";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\VieilleHistoire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */