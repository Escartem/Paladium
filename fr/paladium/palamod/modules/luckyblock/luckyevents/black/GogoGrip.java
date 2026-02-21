package fr.paladium.palamod.modules.luckyblock.luckyevents.black;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class GogoGrip extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.field_70170_p.func_72838_d((Entity)new EntityItem(player.field_70170_p, x, y, z, new ItemStack(ItemsRegister.GADGETO)));
  }
  
  public String getName() {
    return "Gogo Gadgeto Grip";
  }
  
  public int getRarity() {
    return 250;
  }
  
  public String getTexture() {
    return "gogo_grip";
  }
  
  public boolean isBad() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\black\GogoGrip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */