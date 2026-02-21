package fr.paladium.palamod.modules.luckyblock.blocks.june;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityRealJukebox;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockJukebox;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRealJukebox extends BlockJukebox {
  public BlockRealJukebox() {
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("real_jukebox");
    func_149658_d("palamod:real_jukebox_item");
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityRealJukebox();
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderRealJukebox;
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityRealJukebox();
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
    super.func_149689_a(world, x, y, z, player, item);
    TileEntityRealJukebox tile = (TileEntityRealJukebox)world.func_147438_o(x, y, z);
    if (item.func_77942_o() && 
      item.func_77978_p().func_74764_b("palaBestSoundDone"))
      tile.setPalaBestSoundDone(item.func_77978_p().func_74767_n("palaBestSoundDone")); 
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (!world.field_72995_K && EventUtils.canInteract(player, x, y, z)) {
      TileEntityRealJukebox te = (TileEntityRealJukebox)world.func_147438_o(x, y, z);
      if (te != null) {
        ItemStack item = new ItemStack((Block)this);
        item.func_77964_b(meta);
        NBTTagCompound compound = new NBTTagCompound();
        if (item.func_77942_o())
          compound = item.func_77978_p(); 
        compound.func_74757_a("palaBestSoundDone", te.isPalaBestSoundDone());
        item.func_77982_d(compound);
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
  
  public void func_149749_a(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
    func_149925_e(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_);
    super.func_149749_a(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
  }
  
  public void func_149925_e(World world, int x, int y, int z) {
    if (!world.field_72995_K) {
      TileEntityRealJukebox realJukebox = (TileEntityRealJukebox)world.func_147438_o(x, y, z);
      if (realJukebox != null) {
        ItemStack itemstack = realJukebox.func_145856_a();
        if (itemstack != null) {
          world.func_72926_e(1005, x, y, z, 0);
          world.func_72934_a(null, x, y, z);
          realJukebox.func_145857_a(null);
          world.func_72921_c(x, y, z, 0, 2);
          float f = 0.7F;
          double d0 = (world.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
          double d1 = (world.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.2D + 0.6D;
          double d2 = (world.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
          ItemStack copy = itemstack.func_77946_l();
          NBTTagCompound compound = null;
          if (copy.func_77942_o()) {
            compound = copy.func_77978_p();
          } else {
            compound = new NBTTagCompound();
          } 
          compound.func_74757_a("hasGivedItem", realJukebox.isHasGivedItem());
          EntityItem entityitem = new EntityItem(world, x + d0, y + d1, z + d2, copy);
          entityitem.field_145804_b = 10;
          world.func_72838_d((Entity)entityitem);
        } 
      } 
    } 
  }
  
  public void func_149926_b(World world, int x, int y, int z, ItemStack itemStack) {
    if (!world.field_72995_K) {
      TileEntityRealJukebox tileEntity = (TileEntityRealJukebox)world.func_147438_o(x, y, z);
      if (tileEntity != null && 
        itemStack != null) {
        boolean hasGivedItem = false;
        if (itemStack.func_77942_o() && 
          itemStack.func_77978_p().func_74764_b("hasGivedItem"))
          hasGivedItem = itemStack.func_77978_p().func_74767_n("hasGivedItem"); 
        tileEntity.eventPlay(itemStack.func_77977_a(), hasGivedItem);
      } 
    } 
    super.func_149926_b(world, x, y, z, itemStack);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\june\BlockRealJukebox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */