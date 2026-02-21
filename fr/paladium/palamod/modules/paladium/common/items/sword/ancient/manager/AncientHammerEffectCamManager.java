package fr.paladium.palamod.modules.paladium.common.items.sword.ancient.manager;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.java.map.player.SessionPlayerMap;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.time.UniversalTimeUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.paladium.common.entities.ancient.EntityAncientHammerEffect;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.data.ItemAncientHammerPlayerData;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.network.SCPacketAncientHammerEffectCam;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class AncientHammerEffectCamManager {
  private static final PlayerCamMap EFFECT_CAM_PLAYERS = new PlayerCamMap();
  
  public static AncientHammerEffectCam start(EntityPlayerMP player, EntityAncientHammerEffect effect) {
    String uuid = UUIDUtils.toString((Entity)player);
    DoubleLocation location = new DoubleLocation((Entity)effect);
    AncientHammerEffectCam cam = AncientHammerEffectCam.of(location);
    EFFECT_CAM_PLAYERS.put(uuid, cam);
    ItemAncientHammerPlayerData.get((EntityPlayer)player).setEffectCamActive(true);
    cam.setStart(UniversalTimeUtils.now());
    PalaMod.getNetHandler().sendTo((IMessage)new SCPacketAncientHammerEffectCam(cam), player);
    player.func_82142_c(true);
    return cam;
  }
  
  public static void stop(EntityPlayerMP player) {
    player.func_82142_c(false);
    ItemAncientHammerPlayerData.get((EntityPlayer)player).setEffectCamActive(false);
    PalaMod.getNetHandler().sendTo((IMessage)new SCPacketAncientHammerEffectCam(null), player);
    EFFECT_CAM_PLAYERS.remove((Entity)player);
  }
  
  public static boolean isPlayerCamEffectActive(EntityPlayerMP player) {
    return EFFECT_CAM_PLAYERS.containsKey((Entity)player);
  }
  
  public static AncientHammerEffectCam getPlayerCamEffect(EntityPlayerMP player) {
    return (AncientHammerEffectCam)EFFECT_CAM_PLAYERS.get((Entity)player);
  }
  
  private static class PlayerCamMap extends SessionPlayerMap<AncientHammerEffectCam> {
    private PlayerCamMap() {}
    
    public AncientHammerEffectCamManager.AncientHammerEffectCam getDefaultValue() {
      return null;
    }
  }
  
  public static class AncientHammerEffectCam {
    private static final long DURATION = 2050L;
    
    private final DoubleLocation location;
    
    private long start;
    
    public void setStart(long start) {
      this.start = start;
    }
    
    private AncientHammerEffectCam(DoubleLocation location) {
      this.location = location;
    }
    
    public static AncientHammerEffectCam of(DoubleLocation location) {
      return new AncientHammerEffectCam(location);
    }
    
    public DoubleLocation getLocation() {
      return this.location;
    }
    
    public long getStart() {
      return this.start;
    }
    
    public long getRemaining() {
      if (!isStarted())
        return 2050L; 
      return this.start + 2050L - UniversalTimeUtils.now();
    }
    
    public boolean isStarted() {
      return (this.start > 0L);
    }
    
    public boolean isFinished() {
      return (getRemaining() <= 0L);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\sword\ancient\manager\AncientHammerEffectCamManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */