package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.StormData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class StormEvent extends ALuckyEvent implements IHeliosLuckyEventWidget {
  private static final String EVENT_NAME = "De l'orage dans l'air";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 350;
  
  private static final String TEXTURE_PATH = "august/storm";
  
  private static final long STORM_DURATION = TimeUnit.HOURS.toMillis(12L);
  
  public static StormEvent INSTANCE;
  
  private final HashSet<StormData> datas;
  
  public HashSet<StormData> getDatas() {
    return this.datas;
  }
  
  public StormEvent() {
    INSTANCE = this;
    this.datas = new HashSet<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerLuckyBlockProperties properties = PlayerLuckyBlockProperties.get((EntityPlayer)player);
    addData(player, properties);
    MonthlyUtils.startTimedHeliosEvent(player, 
        getClass(), StormData.EXPIRATION_DELAY, 
        System.currentTimeMillis());
  }
  
  public boolean canSpawnParticle(PlayerLuckyBlockProperties properties) {
    return (properties != null && properties.getStormExpirationMillis() > System.currentTimeMillis());
  }
  
  public void addData(EntityPlayerMP player, PlayerLuckyBlockProperties properties) {
    StormData existingData = getData(player);
    if (properties != null)
      properties.setStormExpirationMillis(System.currentTimeMillis() + STORM_DURATION); 
    if (existingData != null)
      return; 
    StormData newData = new StormData(player);
    this.datas.add(newData);
  }
  
  public StormData getData(EntityPlayerMP player) {
    return this.datas.stream()
      .filter(data -> data.getPlayerUniqueId().equals(player.func_110124_au()))
      .findFirst()
      .orElse(null);
  }
  
  public void spawnParticles(WorldServer worldServer, double x, double y, double z) {
    worldServer.func_147487_a("largesmoke", x, y, z, 100, 0.5D, 0.5D, 0.5D, 0.1D);
  }
  
  public void spawnLightning(World world, double x, double y, double z) {
    world.func_72942_c((Entity)new EntityLightningBolt(world, x, y, z));
  }
  
  public String getName() {
    return "De l'orage dans l'air";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 350;
  }
  
  public String getTexture() {
    return "august/storm";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Les prévisions météo nous indiquent que l'orage sera bientôt passé" };
  }
  
  public String getAction() {
    return "temps restant : ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\StormEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */