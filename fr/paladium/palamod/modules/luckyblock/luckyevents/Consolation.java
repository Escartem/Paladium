package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class Consolation extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    int nb = player.field_70170_p.field_73012_v.nextInt(94) + 30;
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.PALADIUM_INGOT, nb));
    PPalaDynamique.instance.addGenerated("LUCKYBLOCK", nb);
    PlayerLuckyBlockProperties.get((EntityPlayer)player).setCurrentChance(1);
    PlayerUtils.sendActionBar(player, "Voilà de quoi te consoler");
  }
  
  public String getName() {
    return "Consolation";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Consolation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */