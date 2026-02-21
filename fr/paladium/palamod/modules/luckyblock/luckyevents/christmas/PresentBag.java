package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class PresentBag extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtil.addItemStackToInventory(new ItemStack(ItemsRegister.PRESENT_BAG), player.field_71071_by);
  }
  
  public String getName() {
    return "Sac K-Dos";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 500;
  }
  
  public String getTexture() {
    return "present_bag";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\PresentBag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */