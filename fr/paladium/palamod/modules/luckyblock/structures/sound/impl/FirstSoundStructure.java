package fr.paladium.palamod.modules.luckyblock.structures.sound.impl;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.structures.sound.SoundStructure;
import fr.paladium.palamod.modules.luckyblock.structures.sound.objects.SoundWave;
import fr.paladium.palamod.modules.luckyblock.structures.utils.LocatedBlock;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class FirstSoundStructure extends SoundStructure {
  public static final List<ItemStack> LUCKY_REWARDS = Arrays.asList(new ItemStack[] { new ItemStack(BlocksRegister.BRIGHT_SOUND_TEST, 1, 0), new ItemStack(BlocksRegister.BRIGHT_SOUND_TEST, 1, 1), new ItemStack(BlocksRegister.BRIGHT_SOUND_TEST, 1, 2) });
  
  private final boolean fromEvent;
  
  public FirstSoundStructure(int maxScore, Location spawnLocation, EntityPlayer player) {
    super(maxScore, 0, spawnLocation, player, 6, 6, 6);
    this.fromEvent = (maxScore == -1);
  }
  
  public void initWaves() {
    addWave(new SoundWave(5, 5));
  }
  
  public void giveBack() {
    if (!this.fromEvent) {
      super.giveBack();
      return;
    } 
    Location center = getCuboid().getCenter();
    PlayerUtils.dropItemStack(center.getWorld(), center.getX(), center.getY(), center.getZ(), new ItemStack(BlocksRegister.BRIGHT_SOUND_TEST, 1, 0));
    PlayerUtils.dropItemStack(center.getWorld(), center.getX(), center.getY(), center
        .getZ(), new ItemStack(BlocksRegister.BRIGHT_SOUND_TEST, 1, 1));
    PlayerUtils.dropItemStack(center.getWorld(), center.getX(), center.getY(), center
        .getZ(), new ItemStack(BlocksRegister.BRIGHT_SOUND_TEST, 1, 2));
  }
  
  public void init() {
    super.init();
    Location middle = getMiddle(0);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(1.0D, 1.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(1.0D, -1.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(-1.0D, 1.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(-1.0D, -1.0D, 0.0D)).build(), true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structures\sound\impl\FirstSoundStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */