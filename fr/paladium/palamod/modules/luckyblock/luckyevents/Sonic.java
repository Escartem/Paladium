package fr.paladium.palamod.modules.luckyblock.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.PacketSetPotionEffect;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import org.bukkit.potion.PotionType;

public class Sonic extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player
      .func_70690_d(new PotionEffect(PotionType.SPEED.getEffectType().getId(), 1200, 8));
    player.func_70690_d(new PotionEffect(PLuckyBlock.CANCEL_JUMP.field_76415_H, 1200, 200));
    PalaMod.getNetwork().sendToAll((IMessage)new PacketSetPotionEffect(player.func_110124_au().toString(), PotionType.SPEED
          .getEffectType().getId(), 1200, 0, true, true));
    PalaMod.getNetwork().sendToAll((IMessage)new PacketSetPotionEffect(player.func_110124_au().toString(), PLuckyBlock.CANCEL_JUMP.field_76415_H, 1200, 0, true, true));
  }
  
  public String getName() {
    return "Sonic";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "sonic";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Sonic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */