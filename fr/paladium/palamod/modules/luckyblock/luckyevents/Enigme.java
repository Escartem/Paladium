package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class Enigme extends ALuckyEvent {
  public static Map<UUID, Integer> tries = new HashMap<>();
  
  public static String[] replies = new String[] { "la pensée", "pensée", "penser", "pensee", "la pensee", "la penser" };
  
  public static boolean handleChat(EntityPlayerMP player, String message) {
    if (!tries.containsKey(player.func_110124_au()))
      return false; 
    int count = ((Integer)tries.get(player.func_110124_au())).intValue();
    boolean right = false;
    for (String reply : replies) {
      if (message.toLowerCase().contains(reply)) {
        right = true;
        break;
      } 
    } 
    if (right) {
      tries.remove(player.func_110124_au());
      PlayerUtils.sendMessage((EntityPlayer)player, "§dPère Fouras: §aFélicitations mon enfant. Tu as la bonne réponse !");
      PlayerUtils.sendMessage((EntityPlayer)player, "§dPère Fouras §evous a récompensé avec §c10 Paladium.");
      ItemStack itemStack = new ItemStack(ItemsRegister.PALADIUM_INGOT, 10);
      player.field_71071_by.func_70441_a(itemStack);
      PPalaDynamique.instance.addGenerated("LUCKYBLOCK", itemStack.field_77994_a);
      return true;
    } 
    if (count == 2) {
      tries.remove(player.func_110124_au());
      PlayerUtils.sendMessage((EntityPlayer)player, "§dPère Fouras: §cTu as échoué !");
      player.func_70606_j(0.0F);
      player.func_70106_y();
      return true;
    } 
    count++;
    tries.put(player.func_110124_au(), Integer.valueOf(count));
    PlayerUtils.sendMessage((EntityPlayer)player, "§dPère Fouras: §cMauvaise réponse! Il ne te reste plus que " + (3 - count) + " essais.");
    return true;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtils.sendMessage((EntityPlayer)player, "§dPère Fouras: §aRépondez à cette énigme, vous n'avez");
    PlayerUtils.sendMessage((EntityPlayer)player, "§aque 3 essais.");
    PlayerUtils.sendMessage((EntityPlayer)player, "§eElle nous obsède sans arrêt.");
    PlayerUtils.sendMessage((EntityPlayer)player, "§eDonne des réponses ou des idées,");
    PlayerUtils.sendMessage((EntityPlayer)player, "§eSans elle, pas d'indice gagné !");
    PlayerUtils.sendMessage((EntityPlayer)player, "§eQui est-elle ?");
    tries.put(player.func_110124_au(), Integer.valueOf(0));
  }
  
  public String getName() {
    return "Père Fouras";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 800;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "pere_fouras";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Enigme.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */