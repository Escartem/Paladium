package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class FakeStuffEndium extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    int random = (new Random()).nextInt(5);
    switch (random) {
      case 1:
        PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.ENDIUM_FAKE_HELMET));
        return;
      case 2:
        PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.ENDIUM_FAKE_CHESTPLATE));
        return;
      case 3:
        PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.ENDIUM_FAKE_LEGGINGS));
        return;
      case 4:
        PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.ENDIUM_FAKE_BOOTS));
        return;
    } 
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(ItemsRegister.ENDIUM_FAKE_BOOTS));
  }
  
  public String getName() {
    return "Juste pour flex";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 400;
  }
  
  public String getTexture() {
    return "flex";
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\FakeStuffEndium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */