package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.structures;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class QuickSandStructure extends AbstractStructure {
  public QuickSandStructure(EntityPlayer player) {
    super(4, 4, 3, (new Location((Entity)player)).clone(0.0D, -4.0D, 0.0D), -1L, true, true, player.func_110124_au());
  }
  
  public void init() {
    fill(BlocksRegister.QUICKSAND, getCuboid().getLocations(), true);
  }
  
  public boolean canSpawn(Location location) {
    return EventUtils.canInteract(getPlayer(), location);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\structures\QuickSandStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */