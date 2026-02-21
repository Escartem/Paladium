package fr.paladium.palamod.modules.luckyblock.luckyevents.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayCustomSound;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class SuperDanseur extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PalaMod.getNetwork().sendTo((IMessage)new PacketPlayCustomSound("gangnam_style"), player);
    player.func_70690_d(new PotionEffect(PLuckyBlock.GANGNAM_STYLE.field_76415_H, 290));
  }
  
  public String getName() {
    return "Super danseur !";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 80;
  }
  
  public String getTexture() {
    return "june/super_danseur";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\june\SuperDanseur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */