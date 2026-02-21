package fr.paladium.palamod.modules.paladium.dummy;

import fr.paladium.palamod.common.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemDummy extends Item {
  public ItemDummy() {
    func_77655_b("dummy");
    func_111206_d("palamod:dummy");
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float fx, float fy, float fz) {
    if (!world.field_72995_K) {
      switch (side) {
        case 0:
          y--;
          y--;
          break;
        case 1:
          y++;
          break;
        case 2:
          z--;
          break;
        case 3:
          z++;
          break;
        case 4:
          x--;
          break;
        case 5:
          x++;
          break;
      } 
      Vec3 foo = player.func_70040_Z();
      EntityDummy entity = new EntityDummy(world);
      entity.func_70107_b(x + 0.5D, y, z + 0.5D);
      entity.setPlacementRotation(foo, side);
      world.func_72838_d((Entity)entity);
      stack.field_77994_a--;
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\dummy\ItemDummy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */