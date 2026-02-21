package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.player.EntityPlayerMP;

public class PalaFakeOre extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EventUtils.spawnAnimatedStructure(player.field_70170_p, x, y + 1, z, 3, 3, 3, BlocksRegister.FAKE_ORE_PALADIUM, "witchMagic", 150, player);
  }
  
  public String getName() {
    return "Pas là";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "pasla";
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\PalaFakeOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */