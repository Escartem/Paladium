package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.EntityFakePoweredCreeper;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class FakePoweredCreeper extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int i = 0; i < 10; i++) {
      EntityFakePoweredCreeper creeper = new EntityFakePoweredCreeper(player.func_130014_f_());
      creeper.func_70634_a(x, (y + 3), z);
      if (player.field_70170_p.field_73012_v.nextInt(20) == 1)
        creeper.func_94058_c("Bobby le creeper"); 
      player.func_130014_f_().func_72838_d((Entity)creeper);
    } 
  }
  
  public String getName() {
    return "Pssssssss";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 30;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\FakePoweredCreeper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */