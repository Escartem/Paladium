package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.blocks.BaseBlockFlower;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityHunterCorruptedPlant;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCorruptedHunterPlant extends BaseBlockFlower implements ITileEntityProvider {
  private static final String NAME = "corrupted_hunter_plant";
  
  private final Random random;
  
  public BlockCorruptedHunterPlant() {
    super("corrupted_hunter_plant");
    this.random = new Random();
  }
  
  public void func_149674_a(World worldObj, int xCoord, int yCoord, int zCoord, Random random) {
    super.func_149674_a(worldObj, xCoord, yCoord, zCoord, random);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> items = new ArrayList<>();
    items.add(new ItemStack(BlocksRegister.CORRUPTED_HUNTER_PLANT, 1));
    return items;
  }
  
  public boolean func_149716_u() {
    return true;
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityHunterCorruptedPlant();
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderHunterCorruptedPlant;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\blocks\BlockCorruptedHunterPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */