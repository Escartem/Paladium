package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class VraiJukeboxVraieMusique extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(BlocksRegister.REAL_JUKEBOX));
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.DISC_ANTI_FUZE));
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.DISC_CLASH_KUMIZ));
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.DISC_ROULETTE_PALADIENNE));
  }
  
  public String getName() {
    return "Un vrai Jukebox pour de la vraie musique";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 240;
  }
  
  public String getTexture() {
    return "june/vrai_jukebox_vraie_musique";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\VraiJukeboxVraieMusique.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */