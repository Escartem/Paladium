package fr.paladium.palamod.modules.paladium.common.blocks.machine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.paladium.common.logics.BowMachineLogic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockBowMachine extends BlockContainer implements ITooltipWiki {
  protected String field_149770_b;
  
  private final IIcon[] icons;
  
  public BlockBowMachine(String unlocalizedName) {
    super(Material.field_151573_f);
    this.field_149770_b = unlocalizedName;
    this.icons = new IIcon[2];
    func_149663_c(this.field_149770_b);
    func_149713_g(255);
    func_149715_a(0.0F);
    func_149711_c(1.5F);
    func_149752_b(8.0F);
    setHarvestLevel("pickaxe", 1);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new BowMachineLogic();
  }
  
  public boolean func_149716_u() {
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
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K)
      player.openGui(PalaMod.instance, PGuiRegistry.GUI_BOW_MACHINE, world, x, y, z); 
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister icon) {
    this.icons[0] = icon
      .func_94245_a("palamod:machines/" + this.field_149770_b + "_top");
    this.icons[1] = icon
      .func_94245_a("palamod:machines/" + this.field_149770_b + "_side");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int metadata) {
    if (side == 1 || side == 0)
      return this.icons[0]; 
    return this.icons[1];
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#3.-box-machine";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\machine\BlockBowMachine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */