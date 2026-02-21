package fr.paladium.palamod.modules.paladium.common.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockConnectedDirt extends Block implements ITooltipWiki {
  boolean tilled;
  
  public String blockname;
  
  public BlockConnectedDirt(boolean tilled) {
    super(Material.field_151578_c);
    this.blockname = "connectedDirt" + (tilled ? "_tilled" : "");
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_149663_c(this.blockname);
    func_149658_d("dirt");
    this.tilled = tilled;
    func_149675_a(true);
    func_149711_c(0.6F);
    func_149672_a(field_149767_g);
    if (tilled) {
      func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
      func_149713_g(255);
      func_149647_a(null);
      this.field_149783_u = true;
      func_149658_d("palamod:connectedDirt");
    } 
  }
  
  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
    return null;
  }
  
  public String func_149739_a() {
    return "tile.connectedDirt";
  }
  
  public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
    return AxisAlignedBB.func_72330_a(p_149668_2_, p_149668_3_, p_149668_4_, (p_149668_2_ + 1), (p_149668_3_ + 1), (p_149668_4_ + 1));
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return null;
  }
  
  public boolean func_149662_c() {
    return !this.tilled;
  }
  
  public boolean isFertile(World world, int x, int y, int z) {
    return true;
  }
  
  public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
    Block plant = plantable.getPlant(world, x, y + 1, z);
    EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);
    switch (plantType) {
      case Desert:
        return !this.tilled;
      case Nether:
        return false;
      case Crop:
        return this.tilled;
      case Cave:
        return !this.tilled;
      case Plains:
        return !this.tilled;
      case Water:
        return false;
      case Beach:
        return this.tilled;
    } 
    return false;
  }
  
  public void func_149674_a(World par1World, int posX, int posY, int posZ, Random rng) {
    if (!par1World.field_72995_K) {
      Block toBoost = par1World.func_147439_a(posX, posY + 1, posZ);
      if (toBoost != null && toBoost != Blocks.field_150350_a && toBoost instanceof IPlantable && toBoost instanceof IGrowable) {
        if (((IGrowable)toBoost).func_149851_a(par1World, posX, posY + 1, posZ, par1World.field_72995_K))
          par1World.func_72926_e(2005, posX, posY + 1, posZ, 0); 
        if (((IGrowable)toBoost).func_149851_a(par1World, posX, posY + 1, posZ, par1World.field_72995_K))
          for (int i = 0; i < 2; i++) {
            toBoost = par1World.func_147439_a(posX, posY + 1, posZ);
            if (toBoost != null && toBoost != Blocks.field_150350_a && toBoost instanceof IPlantable && toBoost instanceof IGrowable)
              toBoost.func_149674_a(par1World, posX, posY + 1, posZ, rng); 
          }  
      } 
    } 
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-ressources-naturelles#1.-les-plantes";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockConnectedDirt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */