package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.ButcherData;
import fr.paladium.palamod.modules.luckyblock.monthly.schematics.TimedSchematic;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.ISchematic;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class RealButcherShopEvent extends ALuckyEvent implements ISchematic, IHeliosLuckyEventWidget {
  private static final String EVENT_NAME = "C'est une vraie boucherie";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 400;
  
  private static final String TEXTURE_PATH = "september/real_butcher_shop";
  
  private static final String SCHEMATIC_NAME = "lb09_boucherie.schematic";
  
  public static final int ZOMBIES_COUNT = 60;
  
  private static final String WARNING_MESSAGE = "&aTuez un maximum de zombies en 60 secondes !";
  
  public static final String END_MESSAGE = "&aVous avez tué &c%s &azombies !";
  
  public static final String REWARD_MESSAGE = "&aVous avez gagné une &drécompense &a!";
  
  private final Map<UUID, ButcherData> stats;
  
  public static RealButcherShopEvent INSTANCE;
  
  public RealButcherShopEvent() {
    INSTANCE = this;
    this.stats = new HashMap<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    DoubleLocation location = new DoubleLocation((Entity)player);
    TimedSchematic schematic = new TimedSchematic(TimeUnit.SECONDS.toMillis(60L), location, "lb09_boucherie.schematic");
    schematic.paste(player, false);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aTuez un maximum de zombies en 60 secondes !" });
    spawnZombies(player, player.field_70170_p, location);
  }
  
  public void spawnZombies(EntityPlayerMP player, World world, DoubleLocation location) {
    List<UUID> uniqueIds = new ArrayList<>();
    this.stats.put(player.func_110124_au(), new ButcherData(player, uniqueIds));
    for (int i = 0; i < 60; i++) {
      EntityZombie zombie = new EntityZombie(world);
      zombie.func_82227_f(false);
      zombie.func_70107_b(location.getX(), location.getY(), location.getZ());
      zombie.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 2400, 0));
      world.func_72838_d((Entity)zombie);
      uniqueIds.add(zombie.func_110124_au());
    } 
  }
  
  public ButcherData get(EntityPlayerMP player) {
    return this.stats.get(player.func_110124_au());
  }
  
  public void remove(EntityPlayerMP player) {
    this.stats.remove(player.func_110124_au());
  }
  
  public String getName() {
    return "C'est une vraie boucherie";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 400;
  }
  
  public String getTexture() {
    return "september/real_butcher_shop";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Tue un maximum de monstres pour gagner une récompense !" };
  }
  
  public String getAction() {
    return "temps restant: ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\RealButcherShopEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */