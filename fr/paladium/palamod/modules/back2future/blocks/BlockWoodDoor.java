package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.items.block.ItemWoodDoor;
import fr.paladium.palamod.modules.back2future.lib.RenderIDs;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;

public class BlockWoodDoor extends BlockDoor implements ModBlocks.ISubBlocksBlock, IConfigurable {
  public static final String[] names = new String[] { "oak", "spruce", "birch", "jungle", "acacia", "dark_oak" };
  
  public BlockWoodDoor(int meta) {
    super(Material.field_151575_d);
    String name = names[meta];
    func_149649_H();
    func_149711_c(3.0F);
    func_149672_a(field_149766_f);
    func_149658_d("door_" + name);
    func_149663_c(Utils.getUnlocalisedName("door_" + name));
    func_149647_a(Back2Future.enableDoors ? Back2Future.creativeTab : null);
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World world, int x, int y, int z) {
    return Item.func_150898_a((Block)this);
  }
  
  public Item func_149650_a(int meta, Random rand, int fortune) {
    return ((meta & 0x8) != 0) ? null : Item.func_150898_a((Block)this);
  }
  
  public int func_149645_b() {
    return RenderIDs.DOOR;
  }
  
  @SideOnly(Side.CLIENT)
  public String func_149702_O() {
    return func_149641_N();
  }
  
  public Class<? extends ItemBlock> getItemBlockClass() {
    return (Class)ItemWoodDoor.class;
  }
  
  public boolean isEnabled() {
    return Back2Future.enableDoors;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\BlockWoodDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */