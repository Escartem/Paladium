package fr.paladium.palamod.modules.luckyblock.structures.sound.impl;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.structures.sound.SoundStructure;
import fr.paladium.palamod.modules.luckyblock.structures.sound.objects.SoundWave;
import fr.paladium.palamod.modules.luckyblock.structures.utils.LocatedBlock;
import net.minecraft.entity.player.EntityPlayer;

public class ThirdSoundStructure extends SoundStructure {
  public ThirdSoundStructure(int maxScore, Location spawnLocation, EntityPlayer player) {
    super(maxScore, 2, spawnLocation, player, 10, 10, 8);
  }
  
  public void initWaves() {
    addWave(new SoundWave(7, 5));
    addWave(new SoundWave(7, 3));
    addWave(new SoundWave(7, 3));
  }
  
  public void init() {
    super.init();
    Location middle = getMiddle(0);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(1.0D, 0.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(3.0D, 0.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(-1.0D, 0.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(-3.0D, 0.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(1.0D, 2.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(3.0D, 2.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(-1.0D, 2.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(-3.0D, 2.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(1.0D, -2.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(3.0D, -2.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(-1.0D, -2.0D, 0.0D)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BRIGHT_SOUND).location(middle.clone(-3.0D, -2.0D, 0.0D)).build(), true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structures\sound\impl\ThirdSoundStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */