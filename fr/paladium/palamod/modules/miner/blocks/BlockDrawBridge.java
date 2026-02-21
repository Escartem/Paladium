package fr.paladium.palamod.modules.miner.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.miner.tileentity.TileEntityDrawBridge;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDrawBridge extends BlockContainer implements ITooltipWiki {
  private final IIcon[] icons = new IIcon[3];
  
  public BlockDrawBridge() {
    super(Material.field_151576_e);
    func_149647_a((CreativeTabs)Registry.MINER_TAB);
    func_149711_c(5.0F);
    func_149663_c("drawbridge");
    func_149658_d("palamod:drawbridge");
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a(func_149641_N() + "_front");
    this.icons[1] = register.func_94245_a(func_149641_N() + "_side");
    this.icons[2] = register.func_94245_a(func_149641_N() + "_top");
    this.field_149761_L = this.icons[1];
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack) {
    int direction = MathHelper.func_76128_c((living.field_70177_z * 4.0F / 360.0F) + 2.5D) & 0x3;
    world.func_72921_c(x, y, z, direction, 2);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (world.field_72995_K)
      return true; 
    TileEntityDrawBridge tileEntityDrawBridge = (TileEntityDrawBridge)world.func_147438_o(x, y, z);
    if (tileEntityDrawBridge != null) {
      tileEntityDrawBridge.setLastPlayer(player);
      player.openGui(PalaMod.instance, PGuiRegistry.GUI_DRAWBRIDGE, world, x, y, z);
    } 
    return true;
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block b) {
    TileEntityDrawBridge tileEntityDrawBridge = (TileEntityDrawBridge)world.func_147438_o(x, y, z);
    tileEntityDrawBridge.setAnimation(0);
    super.func_149695_a(world, x, y, z, b);
  }
  
  public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int dir) {
    int meta = ba.func_72805_g(x, y, z);
    if (dir == 0)
      return this.icons[2]; 
    if ((dir == 4 && meta == 1) || (dir == 2 && meta == 2) || (dir == 5 && meta == 3) || (dir == 3 && meta == 0))
      return this.icons[0]; 
    if (dir == 1)
      return this.icons[2]; 
    return this.icons[1];
  }
  
  public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
    return (TileEntity)new TileEntityDrawBridge();
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#7.-drawdridge";
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
    TileEntity tileentity = world.func_147438_o(x, y, z);
    if (tileentity instanceof IInventory) {
      IInventory inv = (IInventory)tileentity;
      for (int i1 = 0; i1 < inv.func_70302_i_(); i1++) {
        ItemStack itemstack = inv.func_70301_a(i1);
        if (itemstack != null) {
          float f = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
          float f1 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
          for (float f2 = world.field_73012_v.nextFloat() * 0.8F + 0.1F; itemstack.field_77994_a > 0; world.func_72838_d((Entity)entityitem)) {
            int j1 = world.field_73012_v.nextInt(21) + 10;
            if (j1 > itemstack.field_77994_a)
              j1 = itemstack.field_77994_a; 
            itemstack.field_77994_a -= j1;
            EntityItem entityitem = new EntityItem(world, (x + f), (y + f1), (z + f2), new ItemStack(itemstack.func_77973_b(), j1, itemstack.func_77960_j()));
            float f3 = 0.05F;
            entityitem.field_70159_w = ((float)world.field_73012_v.nextGaussian() * 0.05F);
            entityitem.field_70181_x = ((float)world.field_73012_v.nextGaussian() * 0.05F + 0.2F);
            entityitem.field_70179_y = ((float)world.field_73012_v.nextGaussian() * 0.05F);
            if (itemstack.func_77942_o())
              entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b()); 
          } 
        } 
      } 
      world.func_147453_f(x, y, z, block);
    } 
    super.func_149749_a(world, x, y, z, block, metadata);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\blocks\BlockDrawBridge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */