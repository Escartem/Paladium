package fr.paladium.palamod.modules.paladium.common.blocks.machine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.paladium.common.logics.ForgeLogic;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockForge extends BlockContainer implements ITooltipWiki {
  protected String field_149770_b;
  
  private IIcon front;
  
  private IIcon top;
  
  private final boolean isActive;
  
  private static boolean isBurning;
  
  public BlockForge(String unlocalizedName, boolean isActive) {
    super(Material.field_151573_f);
    this.field_149770_b = unlocalizedName;
    this.isActive = isActive;
    func_149663_c(this.field_149770_b);
    func_149711_c(12.0F);
    func_149752_b(8.0F);
    setHarvestLevel("pickaxe", 1);
    if (!this.isActive)
      func_149647_a((CreativeTabs)Registry.PALADIUM_TAB); 
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new ForgeLogic();
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K)
      player.openGui(PalaMod.instance, PGuiRegistry.GUI_FORGE, world, x, y, z); 
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iiconRegister) {
    this.field_149761_L = iiconRegister.func_94245_a("palamod:machines/forge_side");
    this
      .front = iiconRegister.func_94245_a(this.isActive ? "palamod:machines/forge_front_on" : "palamod:machines/forge_front_off");
    this.top = iiconRegister.func_94245_a("palamod:machines/forge_top");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int metadata) {
    return (side == 1) ? this.top : ((side == 0) ? this.top : ((side != metadata) ? this.field_149761_L : this.front));
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return Item.func_150898_a((Block)BlocksRegister.FORGE);
  }
  
  public static void updateBlockState(boolean burning, World world, int x, int y, int z) {
    int direction = world.func_72805_g(x, y, z);
    TileEntity tileentity = world.func_147438_o(x, y, z);
    isBurning = true;
    if (burning) {
      world.func_147449_b(x, y, z, (Block)BlocksRegister.LIT_FORGE);
    } else {
      world.func_147449_b(x, y, z, (Block)BlocksRegister.FORGE);
    } 
    isBurning = false;
    world.func_72921_c(x, y, z, direction, 2);
    if (tileentity != null) {
      tileentity.func_145829_t();
      world.func_147455_a(x, y, z, tileentity);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    direction(world, x, y, z);
  }
  
  private void direction(World world, int x, int y, int z) {
    if (!world.field_72995_K) {
      byte byte0 = 3;
      Block direction = world.func_147439_a(x, y, z - 1);
      if (direction.func_149730_j() && direction.func_149730_j())
        byte0 = 3; 
      direction = world.func_147439_a(x, y, z + 1);
      if (direction.func_149730_j() && direction.func_149730_j())
        byte0 = 2; 
      direction = world.func_147439_a(x - 1, y, z);
      if (direction.func_149730_j() && direction.func_149730_j())
        byte0 = 5; 
      direction = world.func_147439_a(x + 1, y, z);
      if (direction.func_149730_j() && direction.func_149730_j())
        byte0 = 3; 
      world.func_72921_c(x, y, z, byte0, 2);
    } 
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
    int direction = MathHelper.func_76128_c((entity.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    if (direction == 0) {
      world.func_72921_c(x, y, z, 2, 2);
    } else if (direction == 1) {
      world.func_72921_c(x, y, z, 5, 2);
    } else if (direction == 2) {
      world.func_72921_c(x, y, z, 3, 2);
    } else if (direction == 3) {
      world.func_72921_c(x, y, z, 4, 2);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
    if (this.isActive) {
      int l = p_149734_1_.func_72805_g(p_149734_2_, p_149734_3_, p_149734_4_);
      float f = p_149734_2_ + 0.5F;
      float f1 = p_149734_3_ + 0.0F + p_149734_5_.nextFloat() * 6.0F / 16.0F;
      float f2 = p_149734_4_ + 0.5F;
      float f3 = 0.52F;
      float f4 = p_149734_5_.nextFloat() * 0.6F - 0.3F;
      if (l == 4) {
        p_149734_1_.func_72869_a("smoke", (f - 0.52F), f1, (f2 + f4), 0.0D, 0.0D, 0.0D);
        p_149734_1_.func_72869_a("flame", (f - 0.52F), f1, (f2 + f4), 0.0D, 0.0D, 0.0D);
      } else if (l == 5) {
        p_149734_1_.func_72869_a("smoke", (f + 0.52F), f1, (f2 + f4), 0.0D, 0.0D, 0.0D);
        p_149734_1_.func_72869_a("flame", (f + 0.52F), f1, (f2 + f4), 0.0D, 0.0D, 0.0D);
      } else if (l == 2) {
        p_149734_1_.func_72869_a("smoke", (f + f4), f1, (f2 - 0.52F), 0.0D, 0.0D, 0.0D);
        p_149734_1_.func_72869_a("flame", (f + f4), f1, (f2 - 0.52F), 0.0D, 0.0D, 0.0D);
      } else if (l == 3) {
        p_149734_1_.func_72869_a("smoke", (f + f4), f1, (f2 + 0.52F), 0.0D, 0.0D, 0.0D);
        p_149734_1_.func_72869_a("flame", (f + f4), f1, (f2 + 0.52F), 0.0D, 0.0D, 0.0D);
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
    return Item.func_150898_a((Block)BlocksRegister.FORGE);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#12.-palaforge";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\machine\BlockForge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */