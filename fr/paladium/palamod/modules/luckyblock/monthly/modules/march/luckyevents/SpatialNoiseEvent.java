package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.dialog.SpatialNoiseDialogManager;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;

public class SpatialNoiseEvent extends ATickable<HashSet<UUID>> {
  private static final String EVENT_NAME = "Bruit Spatial";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 300;
  
  private static final String TEXTURE_PATH = "march/spatial_noise";
  
  private static SpatialNoiseEvent instance;
  
  public static SpatialNoiseEvent getInstance() {
    return instance;
  }
  
  public SpatialNoiseEvent() {
    super(new HashSet(), 500L);
    instance = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    SpatialNoiseDialogManager.PLAYER_STEP.put(player.func_110124_au(), Integer.valueOf(0));
    SpatialNoiseDialogManager.getInstance().sendDialog(player, null);
  }
  
  protected void onTick(long now) {}
  
  public String getName() {
    return "Bruit Spatial";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "march/spatial_noise";
  }
  
  public boolean hasWished(UUID uuid) {
    return ((HashSet)getData()).contains(uuid);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\luckyevents\SpatialNoiseEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */