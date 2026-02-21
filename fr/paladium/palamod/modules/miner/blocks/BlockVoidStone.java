package fr.paladium.palamod.modules.miner.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.miner.tileentity.TileEntityVoidStone;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockVoidStone extends Block implements ITooltipWiki {
  public BlockVoidStone() {
    super(Material.field_151576_e);
    func_149647_a((CreativeTabs)Registry.MINER_TAB);
    func_149711_c(5.0F);
    func_149663_c("voidstone");
    func_149658_d("palamod:voidstone");
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityVoidStone();
  }
  
  public boolean canHarvestBlock(EntityPlayer player, int meta) {
    return true;
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (!world.field_72995_K) {
      TileEntityVoidStone te = (TileEntityVoidStone)world.func_147438_o(x, y, z);
      if (te != null) {
        ItemStack item = new ItemStack((Item)ItemsRegister.VOIDSTONE_MINAGE);
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74768_a("stone", te.getStone());
        item.func_77982_d(compound);
        PlayerUtils.dropItemStack((Entity)player, x, y, z, item);
      } 
    } 
    super.func_149681_a(world, x, y, z, meta, player);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#5.-cobblestone-compressor";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\blocks\BlockVoidStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */