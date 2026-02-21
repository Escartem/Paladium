package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.EntityDove;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class PeaceNLove extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityDove entity = new EntityDove(player.field_70170_p);
    entity.func_70634_a(x, y, z);
    ItemStack item = new ItemStack(ItemsRegister.PALADIUM_SWORD);
    item.func_77966_a(Enchantment.field_77338_j, 4);
    item.func_77966_a(Enchantment.field_77334_n, 2);
    item.func_77966_a(Enchantment.field_77347_r, 3);
    entity.setDropItem(item);
    player.field_70170_p.func_72838_d((Entity)entity);
  }
  
  public String getName() {
    return "Peace ‘N Love";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "peacenlove";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\PeaceNLove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */