package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class PigHelmet extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.field_71071_by.func_70441_a(new ItemStack(ItemsRegister.PIG_HELMET, 1));
    PlayerUtils.sendActionBar(player, "§dGrouik Grouik!");
  }
  
  public String getName() {
    return "Grouik Grouik (Helmet)";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 1000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "grouik_grouik_hat";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\PigHelmet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */