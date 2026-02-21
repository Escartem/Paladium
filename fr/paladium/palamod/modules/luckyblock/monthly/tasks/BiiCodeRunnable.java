package fr.paladium.palamod.modules.luckyblock.monthly.tasks;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.structures.impl.BiicodeStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.ChestUtils;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.structures.utils.LocatedBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityDigicode;
import java.util.Optional;
import java.util.TimerTask;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;

public class BiiCodeRunnable extends TimerTask {
  private final BiicodeStructure structure;
  
  private boolean filled;
  
  public BiiCodeRunnable(BiicodeStructure structure) {
    this.structure = structure;
    this.filled = false;
  }
  
  public void run() {
    EntityPlayer player = this.structure.getPlayer();
    if (this.structure == null || this.structure.isExpired() || player == null) {
      cancel();
      return;
    } 
    Optional<TileEntityDigicode> result = this.structure.getMaster();
    if (!result.isPresent())
      return; 
    TileEntityDigicode tile = result.get();
    if (!tile.generatePower())
      return; 
    if (!this.filled) {
      replaceBiiCodes();
      addBlocks();
      Optional<TileEntityChest> chestResult = this.structure.getChest();
      if (!chestResult.isPresent())
        return; 
      TileEntityChest chest = chestResult.get();
      ChestUtils.fillChest(chest, this.structure.getRewards());
      this.filled = true;
    } 
  }
  
  public void replaceBiiCodes() {
    this.structure.getBiiCodeLocations().forEach(location -> {
          LocatedBlock located = LocatedBlock.builder().block(BlocksRegister.EXPIRABLE_BEDROCK).location(location).build();
          located.updateExpirationMillis(this.structure.getExpirationMillis(), (AbstractStructure)this.structure);
          this.structure.spawnBlock(located);
        });
  }
  
  public void addBlocks() {
    this.structure.spawnBlock(
        LocatedBlock.builder()
        .block((Block)Blocks.field_150486_ae)
        .location(this.structure.getChestLocation())
        .build());
    this.structure.getDoorBlocks().forEach(door -> this.structure.spawnBlock(LocatedBlock.builder().block(Blocks.field_150350_a).location(door).build()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\tasks\BiiCodeRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */