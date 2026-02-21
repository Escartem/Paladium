package fr.paladium.palamod.modules.luckyblock.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.PacketSetPotionEffect;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class FrozenGround extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.ICE_GROUND.field_76415_H, 3600, 0));
    PalaMod.getNetwork().sendToAll((IMessage)new PacketSetPotionEffect(player.func_110124_au().toString(), PLuckyBlock.ICE_GROUND.field_76415_H, 3600, 0, true, true));
  }
  
  public String getName() {
    return "Sol glacé";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 50;
  }
  
  public String getTexture() {
    return "frozenground";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\FrozenGround.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */