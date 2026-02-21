package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class KobeBryan extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack stack = new ItemStack(Item.func_150898_a(BlocksRegister.BALL_BASKET), 1);
    PlayerUtils.dropItemStack((Entity)player, x, y, z, stack);
  }
  
  public String getName() {
    return "Kobe Bryan";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 200;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "kobe_bryan";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\KobeBryan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */