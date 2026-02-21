package fr.paladium.palamod.modules.luckyblock.blocks.black;

import fr.paladium.palamod.modules.luckyblock.blocks.BlockTrophyBase;
import fr.paladium.palamod.modules.luckyblock.tileentity.black.TileEntityBlackTrophy;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBlackTrophy extends BlockTrophyBase {
  public static String[] trophys = new String[] { "trophy1", "trophy2", "trophy3", "trophy4" };
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityBlackTrophy();
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
    super.func_149689_a(world, x, y, z, player, item);
    TileEntityBlackTrophy tile = (TileEntityBlackTrophy)world.func_147438_o(x, y, z);
    if (item.func_77942_o() && 
      item.func_77978_p().func_74764_b("owner"))
      tile.setOwner(item.func_77978_p().func_74779_i("owner")); 
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (!world.field_72995_K) {
      TileEntityBlackTrophy te = (TileEntityBlackTrophy)world.func_147438_o(x, y, z);
      if (te != null) {
        ItemStack item = new ItemStack((Block)this);
        item.func_77964_b(meta);
        if (te.getOwner() != null && !te.getOwner().isEmpty()) {
          NBTTagCompound compound = new NBTTagCompound();
          if (item.func_77942_o())
            compound = item.func_77978_p(); 
          compound.func_74778_a("owner", te.getOwner());
          item.func_77982_d(compound);
        } 
        EntityItem entityItem = new EntityItem(world, x, y, z, item);
        entityItem.field_145804_b = 0;
        world.func_72838_d((Entity)entityItem);
      } 
    } 
    super.func_149681_a(world, x, y, z, meta, player);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> drops = new ArrayList<>();
    return drops;
  }
  
  public void func_149666_a(Item item, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < trophys.length; i++)
      list.add(new ItemStack(item, 1, i)); 
  }
  
  public int func_149692_a(int metadata) {
    return metadata;
  }
  
  public int func_149645_b() {
    return -1;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\black\BlockBlackTrophy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */