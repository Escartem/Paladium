package fr.paladium.palamod.modules.luckyblock.luckyevents;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class JimmyHendyx extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(
          GameRegistry.findItem("customnpcs", "npcGuitar")));
  }
  
  public String getName() {
    return "Jimmy Hendryx";
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\JimmyHendyx.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */