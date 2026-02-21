package fr.paladium.palamod.modules.paladium.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.blocks.BaseBlockContainer;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import net.minecraft.block.Block;
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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class BlockModdedChest extends BaseBlockContainer implements ITooltipWiki {
  public BlockModdedChest(Material material, float hardness, String texture, float resistance) {
    super(material, hardness, texture);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    func_149752_b(resistance);
    setHarvestLevel("pickaxe", 2);
  }
  
  public abstract TileEntity func_149915_a(World paramWorld, int paramInt);
  
  public boolean func_149716_u() {
    return true;
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
    byte b0 = 0;
    int l = MathHelper.func_76128_c((entity.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    if (l == 0)
      b0 = 2; 
    if (l == 1)
      b0 = 5; 
    if (l == 2)
      b0 = 3; 
    if (l == 3)
      b0 = 4; 
    world.func_72921_c(x, y, z, b0, 2);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.func_147439_a(x, y + 1, z).func_149721_r() && 
      !world.field_72995_K)
      player.openGui(PalaMod.instance, PGuiRegistry.GUI_MODDED_CHEST, world, x, y, z); 
    return true;
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
          for (float f2 = world.field_73012_v.nextFloat() * 0.8F + 0.1F; itemstack.field_77994_a > 0; world
            .func_72838_d((Entity)entityitem)) {
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
              entityitem.func_92059_d()
                .func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b()); 
          } 
        } 
      } 
      world.func_147453_f(x, y, z, block);
    } 
    super.func_149749_a(world, x, y, z, block, metadata);
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public int func_149645_b() {
    return 22;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister icon) {
    this.field_149761_L = icon.func_94245_a("palamod:" + this.texture);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-stockages#1.-les-coffres-moddes";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockModdedChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */