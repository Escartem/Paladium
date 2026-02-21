package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures;

import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.ChestUtils;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.structures.utils.LocatedBlock;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class RedCrossStructure extends AbstractStructure {
  public RedCrossStructure(EntityPlayer player) {
    super(2, 2, -3, (new Location((Entity)player))
        
        .clone(0.0D, -1.0D, 0.0D), -1L, false, true, player
        
        .func_110124_au());
  }
  
  public void init() {
    Location spawnLocation = getSpawnLocation();
    addBlock(
        LocatedBlock.builder()
        .location(spawnLocation)
        .block(Blocks.field_150451_bX)
        .build(), true);
    addBlock(
        LocatedBlock.builder()
        .location(spawnLocation.clone(1.0D, 0.0D, -1.0D))
        .block(Blocks.field_150451_bX)
        .build(), true);
    addBlock(
        LocatedBlock.builder()
        .location(spawnLocation.clone(1.0D, 0.0D, 1.0D))
        .block(Blocks.field_150451_bX)
        .build(), true);
    addBlock(
        LocatedBlock.builder()
        .location(spawnLocation.clone(-1.0D, 0.0D, -1.0D))
        .block(Blocks.field_150451_bX)
        .build(), true);
    addBlock(
        LocatedBlock.builder()
        .location(spawnLocation.clone(-1.0D, 0.0D, 1.0D))
        .block(Blocks.field_150451_bX)
        .build(), true);
    addBlock(
        LocatedBlock.builder()
        .location(spawnLocation.clone(0.0D, -3.0D, 0.0D))
        .block((Block)Blocks.field_150486_ae)
        .build(), true);
  }
  
  public boolean spawn() {
    boolean ret = super.spawn();
    if (!ret)
      return false; 
    Location chestLocation = getPlacedBlocks().stream().filter(locatedBlock -> locatedBlock.getBlock().equals(Blocks.field_150486_ae)).findFirst().map(LocatedBlock::getLocation).orElse(null);
    if (chestLocation == null)
      return false; 
    SoundUtils.CHEST_OPEN.playSound((EntityPlayerMP)
        getPlayer(), chestLocation
        .getX(), chestLocation.getY(), chestLocation.getZ(), 1.0F, 1.0F);
    return ChestUtils.fillChest(chestLocation
        .getWorld(), chestLocation
        .getBlockX(), chestLocation.getBlockY(), chestLocation.getBlockZ(), new ItemStack[] { new ItemStack(ItemsRegister.INVULNERABILITY_POTION, 1) });
  }
  
  public boolean canSpawn(Location location) {
    return EventUtils.canInteract(getPlayer(), location);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\structures\RedCrossStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */