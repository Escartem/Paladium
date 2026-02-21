package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MineralShower extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    Random r = player.field_70170_p.field_73012_v;
    (new Thread(() -> {
          for (int i = 0; i < 15; i++) {
            try {
              Thread.sleep(1000L);
              int rdm = r.nextInt(3);
              Item item = (rdm == 0) ? Items.field_151045_i : ((rdm == 1) ? ItemsRegister.PALADIUM_INGOT : ItemsRegister.TITANE_INGOT);
              PlayerUtils.dropItemStack((Entity)player, (int)player.field_70165_t, ((int)player.field_70163_u + 3), (int)player.field_70161_v, new ItemStack(item, 3));
            } catch (Exception e) {
              e.printStackTrace();
            } 
          } 
        })).start();
  }
  
  public String getName() {
    return "Mineral Shower";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 50;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\MineralShower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */