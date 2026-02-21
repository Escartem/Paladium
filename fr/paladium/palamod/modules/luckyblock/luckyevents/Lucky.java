package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class Lucky extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityItem entityitem = new EntityItem(player.func_130014_f_(), x, y, z, new ItemStack(ItemsRegister.ENDIUM_NUGGET, 1));
    entityitem.field_145804_b = 5;
    player.func_130014_f_().func_72838_d((Entity)entityitem);
  }
  
  public String getName() {
    return "Trop chanceux pour ce monde";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 900;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Lucky.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */