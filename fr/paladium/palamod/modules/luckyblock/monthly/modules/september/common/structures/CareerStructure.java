package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.structures;

import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.monthly.managers.StructureManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.CareerData;
import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

public class CareerStructure extends AbstractStructure {
  public static final Set<Location> STONE_LOCATIONS = new HashSet<>();
  
  public CareerStructure(EntityPlayer player, Location location, CareerData data) {
    super(9, 9, 4, new Location(location
          
          .getWorld(), location.getX(), location.getY() - 4.0D, location.getZ()), data
        .getExpirationMillis(), false, true, player
        .func_110124_au());
    setDestroyable(true);
  }
  
  public void init() {
    fill(Blocks.field_150348_b, getCuboid().getLocations(), true);
  }
  
  public boolean spawn() {
    boolean ret = super.spawn();
    if (!ret)
      return false; 
    if (!StructureManager.getInstance().getStructures().contains(this))
      StructureManager.getInstance().getStructures().add(this); 
    STONE_LOCATIONS.addAll(getCuboid().getLocations());
    return true;
  }
  
  public void cleanArray() {
    getCuboid().getLocations().forEach(location -> STONE_LOCATIONS.removeIf(()));
  }
  
  public void onExpire() {
    super.onExpire();
    cleanArray();
  }
  
  public boolean canSpawn(Location location) {
    return EventUtils.canInteract(getPlayer(), location);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\structures\CareerStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */