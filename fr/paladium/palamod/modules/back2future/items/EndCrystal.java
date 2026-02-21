package fr.paladium.palamod.modules.back2future.items;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.entities.EntityPlacedEndCrystal;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EndCrystal extends ItemSimpleFoiled implements IConfigurable {
  public EndCrystal() {
    func_111206_d("end_crystal");
    func_77655_b(Utils.getUnlocalisedName("end_crystal"));
    func_77637_a(Back2Future.enableDragonRespawn ? Back2Future.creativeTab : null);
  }
  
  public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (side != 1)
      return false; 
    Block block = world.func_147439_a(x, y, z);
    if ((block == Blocks.field_150343_Z || block == Blocks.field_150357_h) && 
      world.func_147437_c(x, y + 1, z)) {
      if (!world.field_72995_K) {
        EntityPlacedEndCrystal endCrystal = new EntityPlacedEndCrystal(world);
        endCrystal.func_70107_b(x + 0.5D, y, z + 0.5D);
        endCrystal.setBlockPos(x, y, z);
        world.func_72838_d((Entity)endCrystal);
        if (!player.field_71075_bZ.field_75098_d)
          stack.field_77994_a--; 
      } 
      return true;
    } 
    return false;
  }
  
  public boolean isEnabled() {
    return Back2Future.enableDragonRespawn;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\EndCrystal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */