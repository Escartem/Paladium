package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import java.util.Random;
import net.minecraft.block.BlockAnvil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAnvilBlock;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class NewAnvil extends BlockAnvil implements IConfigurable, ModBlocks.ISubBlocksBlock {
  public NewAnvil() {
    func_149711_c(5.0F);
    func_149647_a(null);
    func_149752_b(2000.0F);
    func_149672_a(field_149788_p);
    func_149663_c(Utils.getUnlocalisedName("anvil"));
  }
  
  public Item func_149650_a(int meta, Random rand, int fortune) {
    return Item.func_150898_a(Blocks.field_150467_bQ);
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World world, int x, int y, int z) {
    return Item.func_150898_a(Blocks.field_150467_bQ);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
      return true; 
    player.openGui(PalaMod.instance, -1, world, x, y, z);
    return true;
  }
  
  public boolean isEnabled() {
    return Back2Future.enableAnvil;
  }
  
  public Class<? extends ItemBlock> getItemBlockClass() {
    return (Class)ItemAnvilBlock.class;
  }
  
  public static void onPlayerInteract(PlayerInteractEvent event) {
    if (!Back2Future.enableAnvil)
      return; 
    World world = event.world;
    int x = event.x;
    int y = event.y;
    int z = event.z;
    if (world == null || world.field_72995_K)
      return; 
    if (world.func_147439_a(x, y, z) == Blocks.field_150467_bQ)
      world.func_147465_d(x, y, z, ModBlocks.anvil, world.func_72805_g(x, y, z), 3); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\NewAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */