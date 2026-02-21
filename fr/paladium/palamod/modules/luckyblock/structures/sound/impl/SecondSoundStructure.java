package fr.paladium.palamod.modules.luckyblock.structures.sound.impl;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.structures.sound.SoundStructure;
import fr.paladium.palamod.modules.luckyblock.structures.sound.objects.SoundWave;
import fr.paladium.palamod.modules.luckyblock.structures.utils.LocatedBlock;
import net.minecraft.entity.player.EntityPlayer;

public class SecondSoundStructure extends SoundStructure {
  public SecondSoundStructure(int maxScore, Location spawnLocation, EntityPlayer player) {
    super(maxScore, 1, spawnLocation, player, 8, 8, 6);
  }
  
  public void initWaves() {
    addWave(new SoundWave(5, 5));
    addWave(new SoundWave(5, 3));
    addWave(new SoundWave(5, 3));
  }
  
  public void init() {
    super.init();
    Location middle = getMiddle(0);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(1.0D, 1.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(1.0D, -1.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(3.0D, 1.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(3.0D, -1.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(-1.0D, 1.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(-1.0D, -1.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(-3.0D, 1.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(-3.0D, -1.0D, 0.0D)).build(), true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structures\sound\impl\SecondSoundStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */