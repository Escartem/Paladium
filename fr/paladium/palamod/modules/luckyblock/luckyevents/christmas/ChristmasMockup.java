package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.util.PlayerUtil;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ChristmasMockup extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    Random rand = new Random(System.currentTimeMillis());
    int random = rand.nextInt(8);
    ItemStack buffer = new ItemStack(BlocksRegister.CHRISTMAS_MOCKUP, 1, random);
    buffer.func_77964_b(random);
    PlayerUtil.addItemStackToInventory(buffer, player.field_71071_by);
  }
  
  public String getName() {
    return "Maquettiste";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 50;
  }
  
  public String getTexture() {
    return "christmas_mockup";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\ChristmasMockup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */