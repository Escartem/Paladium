package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents;

import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents.data.SandStormData;
import fr.paladium.palamod.modules.luckyblock.monthly.tickable.ATickable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class SandStormEvent extends ATickable<HashSet<SandStormData>> {
  private static final String EVENT_NAME = "Tempête de sable";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 500;
  
  private static final String TEXTURE_PATH = "november/sand_storm";
  
  private static final String PERFORM_MESSAGE = "§eAttention à la tête !";
  
  private static SandStormEvent instance;
  
  public static SandStormEvent getInstance() {
    return instance;
  }
  
  public SandStormEvent() {
    super(new HashSet(), TimeUnit.SECONDS.toMillis(1L));
    instance = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eAttention à la tête !" });
    addData(player);
  }
  
  protected void onTick(long now) {
    List<EntityPlayerMP> players = PlayerUtils.getOnlinePlayers();
    ((HashSet)getData()).forEach(data -> {
          EntityPlayerMP player = PlayerUtils.getPlayer(data.getPlayerUniqueId(), players);
          data.perform(player);
        });
    ((HashSet)getData()).removeIf(SandStormData::isExpired);
  }
  
  private void addData(EntityPlayerMP player) {
    SandStormData data = new SandStormData(player.func_110124_au());
    ((HashSet<SandStormData>)getData()).add(data);
  }
  
  public String getName() {
    return "Tempête de sable";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 500;
  }
  
  public String getTexture() {
    return "november/sand_storm";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\luckyevents\SandStormEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */