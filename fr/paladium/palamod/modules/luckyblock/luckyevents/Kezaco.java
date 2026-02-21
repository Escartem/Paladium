package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;

public class Kezaco extends ALuckyEvent {
  public static List<UUID> kezaco = new ArrayList<>();
  
  public void perform(EntityPlayerMP player, int ax, int ay, int az) {
    kezaco.add(player.func_110124_au());
  }
  
  public String getName() {
    return "Kezaco";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 200;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "kezaco";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Kezaco.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */