package fr.paladium.palamod.modules.ajobs.common.blocks.thrones;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.ajobs.client.JClientProxy;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.ajobs.common.registerer.BlocksRegistry;
import fr.paladium.palamod.modules.ajobs.common.tiles.TileEntityMinerThrone;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMinerThrone extends BlockThroneBase {
  public BlockMinerThrone() {
    func_149663_c("miner_throne");
    func_149676_a(0.0F, 0.5F, 0.0F, 1.0F, 2.0F, 1.0F);
    func_149658_d("palamod:thrones/miner");
  }
  
  public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
    if (!JobsBridge.canUse(par5EntityPlayer, new ItemStack(BlocksRegistry.MINER_THRONE)))
      return false; 
    return sitOnBlock(par1World, par2, par3, par4, par5EntityPlayer, 0.6D);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
    super.func_149689_a(world, x, y, z, player, item);
    TileEntityMinerThrone tile = (TileEntityMinerThrone)world.func_147438_o(x, y, z);
    if (!item.func_77942_o()) {
      NBTTagCompound nbt = new NBTTagCompound();
      item.func_77982_d(nbt);
    } 
    if (!item.func_77978_p().func_74764_b("owner"))
      item.func_77978_p().func_74778_a("owner", player.func_70005_c_()); 
    if (item.func_77942_o() && 
      item.func_77978_p().func_74764_b("owner"))
      tile.setOwner(item.func_77978_p().func_74779_i("owner")); 
  }
  
  public int func_149692_a(int metadata) {
    return metadata;
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (!world.field_72995_K) {
      TileEntityMinerThrone te = (TileEntityMinerThrone)world.func_147438_o(x, y, z);
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
    return JClientProxy.renderMinerThrone;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityMinerThrone();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\blocks\thrones\BlockMinerThrone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */