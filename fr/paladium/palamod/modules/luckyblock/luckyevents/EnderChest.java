package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryEnderChest;

public class EnderChest extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    InventoryEnderChest enderChest = player.func_71005_bN();
    int slot = -1;
    List<Integer> tr = new ArrayList<>();
    while (slot == -1 && tr.size() < enderChest.func_70302_i_()) {
      int i = player.field_70170_p.field_73012_v.nextInt(enderChest.func_70302_i_());
      if (enderChest.func_70301_a(i) != null)
        slot = i; 
      if (!tr.contains(Integer.valueOf(i)))
        tr.add(Integer.valueOf(i)); 
    } 
    if (slot != -1) {
      enderChest.func_70299_a(slot, null);
      enderChest.func_70296_d();
    } 
    PlayerUtils.sendActionBar(player, "§cVoilà, ton enderchest sera plus léger comme ca");
  }
  
  public String getName() {
    return "Le malus le plus rare...";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 30000;
  }
  
  public String getTexture() {
    return "enderchest";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\EnderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */