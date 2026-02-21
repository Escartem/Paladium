package fr.paladium.palamod.modules.back2future.blocks;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class IronTrapdoor extends BlockTrapDoor implements IConfigurable {
  public IronTrapdoor() {
    super(Material.field_151573_f);
    func_149711_c(5.0F);
    func_149672_a(field_149777_j);
    func_149658_d("iron_trapdoor");
    func_149663_c(Utils.getUnlocalisedName("iron_trapdoor"));
    func_149647_a(Back2Future.enableIronTrapdoor ? Back2Future.creativeTab : null);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    return false;
  }
  
  public boolean isEnabled() {
    return Back2Future.enableIronTrapdoor;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\IronTrapdoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */