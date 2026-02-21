package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.instruments;

import fr.paladium.palaforgeutils.lib.cooldown.CooldownUtils;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.managers.InstrumentManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects.SoundType;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects.SoundedLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class ItemInstrument extends Item {
  private static final String COOLDOWN_TAG = "instrument";
  
  private static final long COOLDOWN_DURATION_MILLIS = TimeUnit.MINUTES.toMillis(10L);
  
  private static final String SOMEONE_IN_COOLDOWN = "&cImpossible de faire apparaître le pirate, un des joueurs est en cooldown.";
  
  private final int maxSoundId;
  
  private final SoundType soundType;
  
  private final String name;
  
  public int getMaxSoundId() {
    return this.maxSoundId;
  }
  
  public SoundType getSoundType() {
    return this.soundType;
  }
  
  public String getName() {
    return this.name;
  }
  
  public ItemInstrument(String name, int maxSoundId, SoundType soundType) {
    func_77655_b(name);
    func_111206_d("palamod:" + name);
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    this.maxSoundId = maxSoundId;
    this.soundType = soundType;
    this.name = name;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return stack; 
    if (LuckyBlockUtils.isInCombat(player, true))
      return stack; 
    int soundId = world.field_73012_v.nextInt(this.maxSoundId) + 1;
    InstrumentManager manager = InstrumentManager.getInstance();
    Location location = new Location((Entity)player);
    SoundedLocation sounded = new SoundedLocation(player, this.soundType, location);
    playEmote(player);
    MonthlyUtils.playSoundAround(player, this.name + "_0" + soundId, 24);
    return stack;
  }
  
  public boolean isOnCooldown(List<SoundedLocation> soundedLocations) {
    return soundedLocations.stream()
      .anyMatch(sounded -> CooldownUtils.isOnCooldown(sounded.getSenderUniqueId(), "instrument"));
  }
  
  public abstract void playEmote(EntityPlayer paramEntityPlayer);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\instruments\ItemInstrument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */