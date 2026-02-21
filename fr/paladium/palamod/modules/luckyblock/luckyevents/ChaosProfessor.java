package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ChaosProfessor extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    Random rand = player.field_70170_p.field_73012_v;
    for (int i = 1; i <= 20; i++) {
      int slotA = rand.nextInt(36);
      int slotB = rand.nextInt(36);
      ItemStack itemA = player.field_71071_by.func_70301_a(slotA);
      ItemStack itemB = player.field_71071_by.func_70301_a(slotB);
      player.field_71071_by.func_70299_a(slotA, itemB);
      player.field_71071_by.func_70299_a(slotB, itemA);
    } 
  }
  
  public String getName() {
    return "Professeur Chaos";
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
    return "professeur_chaos";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\ChaosProfessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */