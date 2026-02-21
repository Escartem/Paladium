package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecartEmpty;
import net.minecraft.entity.player.EntityPlayerMP;

public class ItsNotAllowed extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityMinecartEmpty entity = new EntityMinecartEmpty(player.field_70170_p);
    entity.func_70107_b(x, y, z);
    player.field_70170_p.func_72838_d((Entity)entity);
  }
  
  public String getName() {
    return "Je crois que c'est interdit";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 800;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "je_crois_que_c_est_interdit";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\ItsNotAllowed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */