package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.structures.CareerStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.CareerData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

public class CareerEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Faire carrière";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 550;
  
  private static final String TEXTURE_PATH = "september/career";
  
  public static final String NPC_SKIN_PATH = "palamod:textures/entity/npc/career.png";
  
  public static final long DURATION = TimeUnit.MINUTES.toMillis(10L);
  
  public static final String FAIL_MESSAGE = "&cVous n’avez pas réussi à faire carrière";
  
  public static CareerEvent INSTANCE;
  
  private final Map<UUID, CareerData> datas;
  
  public CareerEvent() {
    INSTANCE = this;
    this.datas = new HashMap<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    CareerData data = new CareerData(player, new Location(player.field_70170_p, x, y, z));
    this.datas.put(player.func_110124_au(), data);
  }
  
  @SubscribeEvent
  public void onBreak(BlockEvent.BreakEvent event) {
    EntityPlayerMP player = (EntityPlayerMP)event.getPlayer();
    if (event.isCanceled())
      return; 
    CareerData data = get(player);
    if (data == null || !data.isStarted())
      return; 
    Location location = new Location(event.world, event.x, event.y, event.z);
    for (Location loc : CareerStructure.STONE_LOCATIONS) {
      if (loc.getBlockX() == location.getBlockX() && loc.getBlockY() == location.getBlockY() && loc.getBlockZ() == location.getBlockZ()) {
        CareerStructure.STONE_LOCATIONS.remove(loc);
        data.incrementBreakCount(player);
        break;
      } 
    } 
  }
  
  public void removeExpiredDatas(World world, List<EntityPlayerMP> players, long now) {
    List<UUID> expireds = new ArrayList<>();
    this.datas.entrySet().forEach(entry -> {
          UUID uuid = (UUID)entry.getKey();
          CareerData data = (CareerData)entry.getValue();
          if (!data.isStarted())
            return; 
          if (data.isExpired(now)) {
            expireds.add(uuid);
            data.removeStructure();
            data.removeEntity(world);
            EntityPlayerMP player = MonthlyUtils.getPlayer(data.getPlayerUniqueId(), players);
            if (player == null)
              return; 
            MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous n’avez pas réussi à faire carrière" });
          } 
        });
    expireds.forEach(this.datas::remove);
  }
  
  public CareerData get(EntityPlayerMP player) {
    return this.datas.get(player.func_110124_au());
  }
  
  public void remove(EntityPlayerMP player) {
    this.datas.remove(player.func_110124_au());
  }
  
  public String getName() {
    return "Faire carrière";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 550;
  }
  
  public String getTexture() {
    return "september/career";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\CareerEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */