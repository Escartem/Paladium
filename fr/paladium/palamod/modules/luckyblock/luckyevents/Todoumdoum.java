package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class Todoumdoum extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtil.addItemStackToInventory(new ItemStack(ItemsRegister.DISC_MII, 1), player.field_71071_by);
  }
  
  public String getName() {
    return "To doum doum";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 2000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "to_doum_doum";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Todoumdoum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */