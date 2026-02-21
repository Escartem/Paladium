package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.CommonMarch;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.sc.SCOpenShootingStarPacket;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;

public class ShootingStarEvent extends ATickable<HashSet<UUID>> {
  private static final String EVENT_NAME = "Étoile filante";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 120;
  
  private static final String TEXTURE_PATH = "march/shooting_star";
  
  private static ShootingStarEvent instance;
  
  public static ShootingStarEvent getInstance() {
    return instance;
  }
  
  public ShootingStarEvent() {
    super(new HashSet(), 500L);
    instance = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    addWished(player.func_110124_au());
    CommonMarch.getInstance().getNetwork().sendTo((IMessage)new SCOpenShootingStarPacket(), player);
  }
  
  protected void onTick(long now) {}
  
  public String getName() {
    return "Étoile filante";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 120;
  }
  
  public String getTexture() {
    return "march/shooting_star";
  }
  
  public boolean hasWished(UUID uuid) {
    return ((HashSet)getData()).contains(uuid);
  }
  
  public void addWished(UUID uuid) {
    ((HashSet<UUID>)getData()).add(uuid);
  }
  
  public boolean removeWished(UUID uuid) {
    return ((HashSet)getData()).remove(uuid);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\luckyevents\ShootingStarEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */