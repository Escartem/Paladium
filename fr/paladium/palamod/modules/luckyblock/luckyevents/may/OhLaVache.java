package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import fr.paladium.palamod.modules.luckyblock.entity.christmas.EntityChristmasCow;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.entity.EntityCowSuit;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class OhLaVache extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    World world = player.field_70170_p;
    for (int i = 0; i < 2; i++) {
      EntityCowSuit suitCow = new EntityCowSuit(world, x, y, z);
      EntityChristmasCow christmasCow = new EntityChristmasCow(world);
      EntityMooshroom cow = new EntityMooshroom(world);
      cow.func_70107_b(x, y, z);
      suitCow.func_70107_b(x, y, z);
      christmasCow.func_70107_b(x, y, z);
      cow.func_94058_c("[NS]" + cow.func_70005_c_());
      suitCow.func_94058_c("[NS]" + suitCow.func_70005_c_());
      christmasCow.func_94058_c("[NS]" + christmasCow.func_70005_c_());
      world.func_72838_d((Entity)cow);
      world.func_72838_d((Entity)suitCow);
      world.func_72838_d((Entity)christmasCow);
    } 
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eOh la vache ! Regarde toutes ces vaches !" });
  }
  
  public String getName() {
    return "Oh la vache !";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 130;
  }
  
  public String getTexture() {
    return "may/oh_la_vache";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\OhLaVache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */