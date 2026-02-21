package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ForestKing extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtil.addItemStackToInventory(new ItemStack(ItemsRegister.CHRISTMAS_AXE), player.field_71071_by);
  }
  
  public String getName() {
    return "Roi des forêts";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 70;
  }
  
  public String getTexture() {
    return "forest_king";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\ForestKing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */