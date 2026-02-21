package fr.paladium.palamod.modules.luckyblock.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityTrophyFindium;
import java.util.ArrayList;
import java.util.List;
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

public class BlockTrophyFindium extends BlockTrophyBase {
  public BlockTrophyFindium() {
    func_149663_c("trophy_findium");
    func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 1.0F, 0.7F);
    func_149658_d("palamod:trophy_findium/TrophyTier0");
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
    super.func_149689_a(world, x, y, z, player, item);
    TileEntityTrophyFindium tile = (TileEntityTrophyFindium)world.func_147438_o(x, y, z);
    if (item.func_77942_o() && 
      item.func_77978_p().func_74764_b("owner"))
      tile.setOwner(item.func_77978_p().func_74779_i("owner")); 
  }
  
  public int func_149692_a(int metadata) {
    return metadata;
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (!world.field_72995_K) {
      TileEntityTrophyFindium te = (TileEntityTrophyFindium)world.func_147438_o(x, y, z);
      if (te != null) {
        ItemStack item = new ItemStack(this);
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
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderTrophyFindium;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityTrophyFindium();
  }
  
  public void func_149666_a(Item item, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < 4; i++)
      list.add(new ItemStack(item, 1, i)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockTrophyFindium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */