package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import fr.paladium.palamod.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Bol extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    UsersManager.getBol().add(player);
    ItemStack bol = new ItemStack(Items.field_151009_A);
    bol.func_151001_c("§cOn ne voit pas le fond du §ebol");
    PlayerUtil.addItemStackToInventory(bol, player.field_71071_by);
    PlayerUtils.sendActionBar(player, "§bSi tu as faim, tu es servi");
  }
  
  public String getName() {
    return "On voit pas le fond du bol";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 80;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Bol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */