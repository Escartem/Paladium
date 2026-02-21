package fr.paladium.palamod.modules.luckyblock.luckyevents.black;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.items.black.ItemFlasque;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class Alchi extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack stack = new ItemStack(ItemsRegister.FLASQUE, 1, ItemsRegister.FLASQUE.func_77612_l());
    ItemFlasque.setFlasqueEmpty(stack, true);
    player.field_70170_p.func_72838_d((Entity)new EntityItem(player.field_70170_p, x, y, z, stack));
  }
  
  public String getName() {
    return "Alchi rapide";
  }
  
  public int getRarity() {
    return 500;
  }
  
  public String getTexture() {
    return "alchirapide";
  }
  
  public boolean isBad() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\black\Alchi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */