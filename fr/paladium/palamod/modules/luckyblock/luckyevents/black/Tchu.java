package fr.paladium.palamod.modules.luckyblock.luckyevents.black;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class Tchu extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.field_70170_p.func_72838_d((Entity)new EntityItem(player.field_70170_p, x, y, z, new ItemStack(BlocksRegister.SIFFLET)));
  }
  
  public String getName() {
    return "Tchu Tchu";
  }
  
  public int getRarity() {
    return 45;
  }
  
  public String getTexture() {
    return "tchutchu";
  }
  
  public boolean isBad() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\black\Tchu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */