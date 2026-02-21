package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class CoarseDirt extends Block implements IConfigurable {
  public CoarseDirt() {
    super(Material.field_151578_c);
    func_149711_c(0.5F);
    setHarvestLevel("shovel", 0);
    func_149672_a(field_149767_g);
    func_149658_d("coarse_dirt");
    func_149663_c(Utils.getUnlocalisedName("coarse_dirt"));
    func_149647_a(Back2Future.enableCoarseDirt ? Back2Future.creativeTab : null);
  }
  
  public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
    return Blocks.field_150346_d.canSustainPlant(world, x, y, z, direction, plant);
  }
  
  public boolean isEnabled() {
    return Back2Future.enableCoarseDirt;
  }
  
  public static void onHoeEvent(UseHoeEvent event) {
    if (Back2Future.enableCoarseDirt) {
      World world = event.world;
      if (world.func_147439_a(event.x, event.y, event.z) == ModBlocks.coarse_dirt) {
        world.func_147449_b(event.x, event.y, event.z, Blocks.field_150346_d);
        world.func_72908_a((event.x + 0.5F), (event.y + 0.5F), (event.z + 0.5F), Block.field_149767_g
            .func_150498_e(), 1.0F, 0.8F);
        event.setResult(Event.Result.ALLOW);
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\CoarseDirt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */