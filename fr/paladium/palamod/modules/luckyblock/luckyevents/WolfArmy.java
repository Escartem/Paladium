package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.hunter.networks.PlayerEndiumNameTagProperties;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class WolfArmy extends ALuckyEvent {
  private final int wolfAmount = 10;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int i = 0; i < 10; i++) {
      EntityWolf entity = new EntityWolf(player.field_70170_p);
      entity.func_70606_j(entity.func_110138_aP());
      entity.func_94061_f(true);
      entity.func_70634_a(x, y, z);
      entity.func_70903_f(true);
      entity.func_152115_b(player.func_110124_au().toString());
      EntityWolf entityWolf1 = entity;
      entityWolf1.getEntityData().func_74778_a("defaultName", entityWolf1.func_70005_c_());
      entityWolf1.func_94058_c("§bWof wof !");
      entityWolf1.func_110163_bv();
      if (!player.field_70170_p.field_72995_K)
        PlayerEndiumNameTagProperties.get((EntityPlayer)player).addEntity((Entity)entity); 
      player.field_70170_p.func_72838_d((Entity)entity);
    } 
  }
  
  public String getName() {
    return "Doggy Army";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 50;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "wolfarmy";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\WolfArmy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */