package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.smeltery.registerer.PSRegister_Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Paquotille extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack axe = new ItemStack(ItemsRegister.PALADIUM_AXE, 1, 0);
    axe.func_77964_b(axe.func_77958_k());
    PlayerUtils.dropItemStack((Entity)player, x, y, z, axe);
    ItemStack pickaxe = new ItemStack(ItemsRegister.PALADIUM_PICKAXE, 1, 0);
    pickaxe.func_77964_b(pickaxe.func_77958_k());
    PlayerUtils.dropItemStack((Entity)player, x, y, z, pickaxe);
    ItemStack shovel = new ItemStack(ItemsRegister.PALADIUM_SHOVEL, 1, 0);
    shovel.func_77964_b(shovel.func_77958_k());
    PlayerUtils.dropItemStack((Entity)player, x, y, z, shovel);
    ItemStack hammer = new ItemStack((Item)PSRegister_Items.HAMMER_PALADIUM, 1, 0);
    hammer.func_77964_b(hammer.func_77958_k());
    PlayerUtils.dropItemStack((Entity)player, x, y, z, hammer);
  }
  
  public String getName() {
    return "Paquotille";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 200;
  }
  
  public String getTexture() {
    return "easter/paquotille";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\Paquotille.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */