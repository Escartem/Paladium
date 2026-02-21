package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.entities.EntityMosquito;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class MosquitoEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Moustique";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 250;
  
  private static final String TEXTURE_PATH = "august/mosquito";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    World world = player.field_70170_p;
    EntityMosquito entity = new EntityMosquito(world);
    entity.setTarget((Entity)player);
    entity.func_70634_a(x, y, z);
    world.func_72838_d((Entity)entity);
  }
  
  public String getName() {
    return "Moustique";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 250;
  }
  
  public String getTexture() {
    return "august/mosquito";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\MosquitoEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */