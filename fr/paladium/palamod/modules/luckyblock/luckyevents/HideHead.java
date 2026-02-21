package fr.paladium.palamod.modules.luckyblock.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.PacketSetPotionEffect;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class HideHead extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.HIDE_HEAD.field_76415_H, 2400, 0));
    PalaMod.getNetwork().sendToAll((IMessage)new PacketSetPotionEffect(FastUUID.toString((Entity)player), PLuckyBlock.HIDE_HEAD.field_76415_H, 2400, 0, true, true));
  }
  
  public String getName() {
    return "Ou donner de la tête";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 4;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "hidehead";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\HideHead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */