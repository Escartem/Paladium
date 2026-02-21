package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.PacketSetPotionEffect;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class ExtremeCold extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.EXTREME_COLD.field_76415_H, 6000, 0));
    PalaMod.getNetwork().sendToAll((IMessage)new PacketSetPotionEffect(player.func_110124_au().toString(), PLuckyBlock.EXTREME_COLD.field_76415_H, 6000, 0, true, true));
  }
  
  public String getName() {
    return "Grand froid";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 70;
  }
  
  public String getTexture() {
    return "extreme_cold";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\ExtremeCold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */