package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityStrengthStand;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TestForce extends ALuckyEvent implements IHeliosLuckyEventWidget {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityStrengthStand entity = new EntityStrengthStand(player.field_70170_p);
    entity.setDuration(30);
    entity.addReward(new EntityStrengthStand.StrengthStandReward(250.0D, Double.valueOf(1250.0D), new ItemStack(ItemsRegister.PALADIUM_SWORD)));
    entity.addReward(new EntityStrengthStand.StrengthStandReward(1251.0D, Double.valueOf(2500.0D), new ItemStack(BlocksRegister.BLOCK_PALADIUM)));
    entity.addReward(new EntityStrengthStand.StrengthStandReward(2501.0D, null, new ItemStack((Item)ItemsRegister.STICK_GOD)));
    entity.func_70634_a(x, y, z);
    player.field_70170_p.func_72838_d((Entity)entity);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aUn mannequin de force est apparu devant vous. Infligez lui un maximum de dégâts pour obtenir une récompense. (utilisez une épée)" });
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), TimeUnit.SECONDS.toMillis(30L));
  }
  
  public String getName() {
    return "Test de force";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "may/test_force";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Effectue un maximum de dégâts au mannequin." };
  }
  
  public String getAction() {
    return "Temps restant :";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\TestForce.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */