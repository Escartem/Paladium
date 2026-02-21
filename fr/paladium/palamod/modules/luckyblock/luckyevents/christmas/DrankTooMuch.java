package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import fr.paladium.palamod.modules.luckyblock.entity.christmas.EntityChristmasBall;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class DrankTooMuch extends ALuckyEvent implements IHeliosLuckyEventWidget {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityChristmasBall christmasBall = new EntityChristmasBall(player.field_70170_p, player.field_70165_t + 1.0D, player.field_70163_u + 1.0D, player.field_70161_v);
    christmasBall.setOwner((Entity)player);
    player.field_70170_p.func_72838_d((Entity)christmasBall);
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), TimeUnit.SECONDS.toMillis(60L));
  }
  
  public String getName() {
    return "Trop bu";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 70;
  }
  
  public String getTexture() {
    return "drunk_too_much";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Tu as trop bu et tu vois des choses étranges." };
  }
  
  public String getAction() {
    return "temps restant: ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\DrankTooMuch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */