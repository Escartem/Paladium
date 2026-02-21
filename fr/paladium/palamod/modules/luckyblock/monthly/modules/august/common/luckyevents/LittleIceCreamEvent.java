package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LittleIceCreamEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Une petite glace";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 300;
  
  private static final String TEXTURE_PATH = "august/little_ice_cream";
  
  public static final String NPC_SKIN_PATH = "palamod:textures/entity/npc/ice_cream_seller.png";
  
  private static final long DURATION = TimeUnit.MINUTES.toMillis(5L);
  
  public static LittleIceCreamEvent INSTANCE;
  
  public LittleIceCreamEvent() {
    INSTANCE = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    perform(player, x, y, z, true);
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z, boolean drop) {
    World world = player.field_70170_p;
    EntityNPC seller = new EntityNPC(world, "Marchand de glace", "palamod:textures/entity/npc/ice_cream_seller.png", x, (y - 2), z, DURATION, DURATION, true);
    world.func_72838_d((Entity)seller);
    if (!drop)
      return; 
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.ICE_CREAM_EGG, 3));
  }
  
  public String getName() {
    return "Une petite glace";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "august/little_ice_cream";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\LittleIceCreamEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */