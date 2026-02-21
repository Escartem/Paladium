package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.EntityMineralRabbit;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class MineralRush extends ALuckyEvent {
  Item[] types = new Item[] { Items.field_151045_i, ItemsRegister.PALADIUM_INGOT, ItemsRegister.TITANE_INGOT, ItemsRegister.AMETHYST_INGOT };
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityMineralRabbit mineralRabbit = new EntityMineralRabbit(player.func_130014_f_(), this.types[player.field_70170_p.field_73012_v.nextInt(this.types.length)]);
    mineralRabbit.func_70634_a(x, (y + 2), z);
    player.func_130014_f_().func_72838_d((Entity)mineralRabbit);
  }
  
  public String getName() {
    return "Ruée vers les minerais";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 30;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "mineral_rush";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\MineralRush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */