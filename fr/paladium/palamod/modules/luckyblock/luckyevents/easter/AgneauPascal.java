package fr.paladium.palamod.modules.luckyblock.luckyevents.easter;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayerMP;

public class AgneauPascal extends ALuckyEvent {
  public static final String CUSTOM_NAME = "§bPascal";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntitySheep entity = new EntitySheep(player.field_70170_p);
    entity.func_70873_a(-1200);
    entity.func_70634_a(x, y, z);
    entity.func_94058_c("§bPascal");
    entity.func_94061_f(true);
    player.field_70170_p.func_72838_d((Entity)entity);
  }
  
  public String getName() {
    return "L’agneau pascal";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 75;
  }
  
  public String getTexture() {
    return "easter/agneau_pascal";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\AgneauPascal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */