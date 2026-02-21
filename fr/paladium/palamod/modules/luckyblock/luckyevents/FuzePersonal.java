package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.EntityFakePlayer;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class FuzePersonal extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (!player.field_70170_p.field_72995_K) {
      EntityFakePlayer fakePlayer = new EntityFakePlayer(player.field_70170_p, player.func_110124_au(), "FuzeIII", "palamod:textures/entity/fuze.png", x, y, z);
      fakePlayer.setItemDeath(new ItemStack(ItemsRegister.DISC_FUZEIII));
      player.field_70170_p.func_72838_d((Entity)fakePlayer);
    } 
  }
  
  public String getName() {
    return "Fuze Personnel";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 600;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "fuzepersonnal";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\FuzePersonal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */