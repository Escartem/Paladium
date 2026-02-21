package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.blocks.BlockBarrel;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tiles.TileEntityBarrel;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.structures.utils.LocatedBlock;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class BarrelStructure extends AbstractStructure {
  private boolean no;
  
  public BarrelStructure(EntityPlayer player, boolean no) {
    super(20, 20, 0, (new Location((Entity)player))
        
        .clone(0.0D, -1.0D, 0.0D), -1L, true, true, player
        
        .func_110124_au());
    this.no = no;
  }
  
  public void init() {
    Location spawnLocation = getSpawnLocation();
    addBlock(LocatedBlock.builder().block(BlocksRegister.BARREL).location(getMiddle(2)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BARREL).location(getMiddle(0)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BARREL).location(getMiddle(3)).build(), true);
    addBlock(LocatedBlock.builder().block(BlocksRegister.BARREL).location(getMiddle(1)).build(), true);
  }
  
  public boolean spawn() {
    boolean ret = super.spawn();
    if (!ret)
      return false; 
    getBarrels().forEach(barrel -> barrel.setPowderAmount(10));
    explodeLater();
    MonthlyUtils.sendMessage(getPlayer(), new String[] { "&e&l[!] &eVous avez fait parler la poudre ! &cAttention à l'explosion !" });
    return true;
  }
  
  public void explodeLater() {
    getBarrels().forEach(barrel -> {
          Block block = barrel.func_145831_w().func_147439_a(barrel.field_145851_c, barrel.field_145848_d, barrel.field_145849_e);
          if (block instanceof BlockBarrel) {
            BlockBarrel barrelBlock = (BlockBarrel)block;
            barrelBlock.explode(barrel.func_145831_w(), barrel.field_145851_c, barrel.field_145848_d, barrel.field_145849_e, barrel.getPowderAmount());
          } 
        });
    EntityPlayer player = getPlayer();
    if (player != null)
      giveItems(player, this.no); 
  }
  
  private void giveItems(EntityPlayer player, boolean isNegative) {
    Location playerLocation = (new Location((Entity)player)).add(0.0D, 3.0D, 0.0D);
    if (isNegative) {
      ItemUtils.spawnItemInWorld(playerLocation.getWorld(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), new ItemStack(BlocksRegister.BARREL_WOOD, 64));
      ItemUtils.spawnItemInWorld(playerLocation.getWorld(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), new ItemStack(BlocksRegister.BARREL_WOOD, 36));
    } else {
      ItemUtils.spawnItemInWorld(playerLocation.getWorld(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), new ItemStack(BlocksRegister.BARREL, 1));
    } 
  }
  
  private List<TileEntityBarrel> getBarrels() {
    return (List<TileEntityBarrel>)getPlacedBlocks().stream()
      .filter(locatedBlock -> locatedBlock.getBlock().equals(BlocksRegister.BARREL))
      .map(LocatedBlock::getLocation)
      .map(location -> location.getWorld().func_147438_o(location.getBlockX(), location.getBlockY(), location.getBlockZ()))
      .filter(tileEntity -> tileEntity instanceof TileEntityBarrel)
      .map(tileEntity -> (TileEntityBarrel)tileEntity)
      .collect(Collectors.toList());
  }
  
  public boolean canSpawn(Location location) {
    return EventUtils.canInteract(getPlayer(), location);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\structures\BarrelStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */