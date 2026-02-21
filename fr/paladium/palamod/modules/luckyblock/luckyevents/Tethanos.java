package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class Tethanos extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack stack = new ItemStack(ItemsRegister.ENDIUM_GAUNTLET);
    player.field_70170_p.func_72838_d((Entity)new EntityItem(player.field_70170_p, x, y, z, stack));
  }
  
  public String getName() {
    return "Inéluctable";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 5000;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "ineluctable";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Tethanos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */