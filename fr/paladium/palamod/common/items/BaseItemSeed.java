package fr.paladium.palamod.common.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BaseItemSeed extends BaseItemPlants implements IPlantable {
  private final Block crops;
  
  private final int lvlmin;
  
  public BaseItemSeed(String unlocalizedName, String texture, Block crops, int lvlmin) {
    super("seeds/" + texture);
    this.crops = crops;
    func_77655_b(unlocalizedName);
    this.lvlmin = lvlmin;
  }
  
  public boolean func_77648_a(ItemStack parItemStack, EntityPlayer parPlayer, World parWorld, int parX, int parY, int parZ, int par7, float par8, float par9, float par10) {
    if (!parWorld.field_72995_K) {
      if (par7 != 1)
        return false; 
      if (parPlayer.func_82247_a(parX, parY + 1, parZ, par7, parItemStack) && 
        parWorld.func_147439_a(parX, parY, parZ).canSustainPlant((IBlockAccess)parWorld, parX, parY, parZ, ForgeDirection.UP, this) && parWorld.func_147437_c(parX, parY + 1, parZ)) {
        parWorld.func_147449_b(parX, parY + 1, parZ, this.crops);
        parItemStack.field_77994_a--;
        return true;
      } 
    } 
    return false;
  }
  
  public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
    return EnumPlantType.Crop;
  }
  
  public Block getPlant(IBlockAccess world, int x, int y, int z) {
    return this.crops;
  }
  
  public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
    return 0;
  }
  
  public int getLvlmin() {
    return this.lvlmin;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\items\BaseItemSeed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */