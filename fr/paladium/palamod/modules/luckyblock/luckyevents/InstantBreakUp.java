package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayerMP;

public class InstantBreakUp extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (player.func_70694_bm() != null && (
      player.func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemTool || player
      .func_70694_bm().func_77973_b() instanceof net.minecraft.item.ItemSword)) {
      player.func_70062_b(0, null);
      PlayerUtils.sendActionBar(player, "§cTu feras gaffe à ce que tu as en main, c'est fragile");
      return;
    } 
    for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
      if (player.field_71071_by.field_70462_a[i] != null && (
        player.field_71071_by.field_70462_a[i].func_77973_b() instanceof net.minecraft.item.ItemTool || player.field_71071_by.field_70462_a[i]
        .func_77973_b() instanceof net.minecraft.item.ItemSword)) {
        PlayerUtils.sendActionBar(player, "§cIl me semble que tu avais §b" + (player.field_71071_by.field_70462_a[i]).field_77994_a + "x" + player.field_71071_by.field_70462_a[i]
            
            .func_77973_b().func_77658_a());
        player.field_71071_by.field_70462_a[i] = null;
        break;
      } 
    } 
  }
  
  public String getName() {
    return "Instant break up";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 40;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\InstantBreakUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */