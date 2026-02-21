package fr.paladium.palamod.modules.luckyblock.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.PacketSetPotionEffect;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class Flou extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.FLOU.field_76415_H, 1200, 0));
    PalaMod.getNetwork().sendToAll((IMessage)new PacketSetPotionEffect(player.func_110124_au().toString(), PLuckyBlock.FLOU.field_76415_H, 1200, 0, true, true));
    PlayerUtils.sendActionBar(player, "Mais ou sont mes lunettes ?");
  }
  
  public String getName() {
    return "Sans lunette";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "flou";
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Flou.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */