package fr.paladium.palamod.modules.smeltery.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.smeltery.logics.GrinderLogic;
import fr.paladium.palamod.modules.smeltery.registerer.PSRegister_Blocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockGrinder extends BlockContainer implements ITooltipWiki {
  private final String unlocalizedName;
  
  @SideOnly(Side.CLIENT)
  private IIcon frontIcon;
  
  public BlockGrinder(String unlocalizedName) {
    super(Material.field_151576_e);
    this.unlocalizedName = unlocalizedName;
    func_149663_c(this.unlocalizedName);
    func_149658_d("palamod:smeltery/" + this.unlocalizedName);
    func_149752_b(20.0F);
    func_149711_c(3.0F);
    setHarvestLevel("pickaxe", 2);
    func_149672_a(Block.field_149769_e);
    func_149647_a((CreativeTabs)Registry.SMELTERY_TAB);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int metadata) {
    if (metadata == 0 && side == 3)
      return this.frontIcon; 
    return (side != metadata) ? this.field_149761_L : this.frontIcon;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister) {
    this
      .field_149761_L = iconRegister.func_94245_a("palamod:smeltery/" + this.unlocalizedName + "_side");
    this
      .frontIcon = iconRegister.func_94245_a("palamod:smeltery/" + this.unlocalizedName + "_front");
  }
  
  public boolean isMultiBlockStructure(World world, int x, int y, int z) {
    if (checkNorth(world, x, y, z) || checkEast(world, x, y, z) || checkSouth(world, x, y, z) || checkWest(world, x, y, z))
      return true; 
    return false;
  }
  
  private static boolean checkNorth(World world, int x, int y, int z) {
    if (world.func_147439_a(x + -1, y + -1, z + 0).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + -1, z + -1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + -1, z + -2).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + 0, z + 0).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + 0, z + -1)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + -1, y + 0, z + -2)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + 1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + 1, z + -1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + 1, z + -2)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + -1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + -1, z + -1)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + 0, y + -1, z + -2)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + 0, z + -1).equals(Blocks.field_150353_l) && 
      world.func_147439_a(x + 0, y + 0, z + -2)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + 0, y + 1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + 1, z + -1)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + 0, y + 1, z + -2)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + -1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + -1, z + -1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + -1, z + -2)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + 0, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + 0, z + -1)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + 1, y + 0, z + -2).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK))
      if (world.func_147439_a(x + 1, y + 1, z + 0).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK))
        if (world.func_147439_a(x + 1, y + 1, z + -1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK))
          if (world.func_147439_a(x + 1, y + 1, z + -2)
            .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK))
            return true;    
    return false;
  }
  
  private static boolean checkEast(World world, int x, int y, int z) {
    if (world.func_147439_a(x + 0, y + -1, z + -1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + -1, z + -1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 2, y + -1, z + -1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + 0, z + -1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + 0, z + -1)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + 2, y + 0, z + -1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + 1, z + -1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + 1, z + -1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 2, y + 1, z + -1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + -1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + -1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + 2, y + -1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + 0, z + 0).equals(Blocks.field_150353_l) && 
      world.func_147439_a(x + 2, y + 0, z + 0)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + 0, y + 1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + 1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + 2, y + 1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + -1, z + 1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + -1, z + 1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 2, y + -1, z + 1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + 0, z + 1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + 0, z + 1)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + 2, y + 0, z + 1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK))
      if (world.func_147439_a(x + 0, y + 1, z + 1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK))
        if (world.func_147439_a(x + 1, y + 1, z + 1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK))
          if (world.func_147439_a(x + 2, y + 1, z + 1)
            .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK))
            return true;    
    return false;
  }
  
  private static boolean checkSouth(World world, int x, int y, int z) {
    if (world.func_147439_a(x + 1, y + -1, z + 0).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + -1, z + 1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + -1, z + 2).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + 0, z + 0).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + 0, z + 1)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + 1, y + 0, z + 2)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + 1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + 1, z + 1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 1, y + 1, z + 2)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + -1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + -1, z + 1)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + 0, y + -1, z + 2)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + 0, z + 1).equals(Blocks.field_150353_l) && 
      world.func_147439_a(x + 0, y + 0, z + 2)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + 0, y + 1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + 1, z + 1)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + 0, y + 1, z + 2)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + -1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + -1, z + 1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + -1, z + 2)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + 0, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + 0, z + 1)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + -1, y + 0, z + 2).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK))
      if (world.func_147439_a(x + -1, y + 1, z + 0).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK))
        if (world.func_147439_a(x + -1, y + 1, z + 1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK))
          if (world.func_147439_a(x + -1, y + 1, z + 2)
            .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK))
            return true;    
    return false;
  }
  
  private static boolean checkWest(World world, int x, int y, int z) {
    if (world.func_147439_a(x + 0, y + -1, z + 1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + -1, z + 1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -2, y + -1, z + 1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + 0, z + 1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + 0, z + 1)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + -2, y + 0, z + 1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + 1, z + 1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + 1, z + 1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -2, y + 1, z + 1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + -1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + -1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + -2, y + -1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + 0, z + 0).equals(Blocks.field_150353_l) && 
      world.func_147439_a(x + -2, y + 0, z + 0)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + 0, y + 1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + 1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + -2, y + 1, z + 0)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + -1, z + -1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + -1, z + -1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -2, y + -1, z + -1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + 0, y + 0, z + -1)
      .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK) && 
      world.func_147439_a(x + -1, y + 0, z + -1)
      .equals(PSRegister_Blocks.GRINDER_CASING_BLOCK) && 
      world.func_147439_a(x + -2, y + 0, z + -1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK))
      if (world.func_147439_a(x + 0, y + 1, z + -1).equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK))
        if (world.func_147439_a(x + -1, y + 1, z + -1)
          .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK))
          if (world.func_147439_a(x + -2, y + 1, z + -1)
            .equals(PSRegister_Blocks.GRINDER_FRAME_BLOCK))
            return true;    
    return false;
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (isMultiBlockStructure(world, x, y, z)) {
      if (!world.field_72995_K) {
        player.openGui(PalaMod.instance, PGuiRegistry.GUI_GRINDER, world, x, y, z);
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile instanceof GrinderLogic) {
          GrinderLogic grinder = (GrinderLogic)tile;
          grinder.setPlayer(player);
        } 
      } 
      return true;
    } 
    return false;
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
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack itemStack) {
    int l = MathHelper.func_76128_c((p_149689_5_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    if (l == 0)
      world.func_72921_c(x, y, z, 2, 2); 
    if (l == 1)
      world.func_72921_c(x, y, z, 5, 2); 
    if (l == 2)
      world.func_72921_c(x, y, z, 3, 2); 
    if (l == 3)
      world.func_72921_c(x, y, z, 4, 2); 
  }
  
  public TileEntity func_149915_a(World world, int p_149915_2_) {
    GrinderLogic grinderLogic = new GrinderLogic();
    return (TileEntity)grinderLogic;
  }
  
  public boolean func_149716_u() {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random random) {
    int l = world.func_72805_g(x, y, z);
    float f = x + 0.5F;
    float f1 = y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
    float f2 = z + 0.5F;
    float f3 = 0.52F;
    float f4 = random.nextFloat() * 0.6F - 0.3F;
    if (isMultiBlockStructure(world, x, y, z))
      if (l == 4) {
        world.func_72869_a("smoke", (f - 0.52F), f1, (f2 + f4), 0.0D, 0.0D, 0.0D);
        world.func_72869_a("flame", (f - 0.52F), f1, (f2 + f4), 0.0D, 0.0D, 0.0D);
      } else if (l == 5) {
        world.func_72869_a("smoke", (f + 0.52F), f1, (f2 + f4), 0.0D, 0.0D, 0.0D);
        world.func_72869_a("flame", (f + 0.52F), f1, (f2 + f4), 0.0D, 0.0D, 0.0D);
      } else if (l == 2) {
        world.func_72869_a("smoke", (f + f4), f1, (f2 - 0.52F), 0.0D, 0.0D, 0.0D);
        world.func_72869_a("flame", (f + f4), f1, (f2 - 0.52F), 0.0D, 0.0D, 0.0D);
      } else if (l == 3) {
        world.func_72869_a("smoke", (f + f4), f1, (f2 + 0.52F), 0.0D, 0.0D, 0.0D);
        world.func_72869_a("flame", (f + f4), f1, (f2 + 0.52F), 0.0D, 0.0D, 0.0D);
      }  
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#8.-grinder";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\blocks\BlockGrinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */