package fr.paladium.palamod.modules.luckyblock.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.PacketSetPotionEffect;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class NightVision extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.GREEN.field_76415_H, 99999999, 0));
    PalaMod.getNetwork().sendToAll((IMessage)new PacketSetPotionEffect(FastUUID.toString((Entity)player), PLuckyBlock.GREEN.field_76415_H, 99999999, 0, true, true));
    PlayerUtils.sendActionBar(player, "ça va ton jeu ?");
  }
  
  public String getName() {
    return "Vision nocturne";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 500;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "vision_nocturne";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\NightVision.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */