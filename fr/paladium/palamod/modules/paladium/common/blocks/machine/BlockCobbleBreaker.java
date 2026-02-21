package fr.paladium.palamod.modules.paladium.common.blocks.machine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.paladium.common.logics.TileCobbleBreaker;
import fr.paladium.palamod.modules.paladium.common.logics.cobblebreaker.CobbleBreakerUpgrade;
import java.util.ArrayList;
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
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCobbleBreaker extends BlockContainer implements ITooltipWiki {
  protected String field_149770_b;
  
  private final IIcon[][] icons = new IIcon[(CobbleBreakerUpgrade.values()).length][3];
  
  public BlockCobbleBreaker(String unlocalizedName) {
    super(Material.field_151573_f);
    this.field_149770_b = unlocalizedName;
    func_149663_c(this.field_149770_b);
    func_149711_c(12.0F);
    func_149752_b(8.0F);
    setHarvestLevel("pickaxe", 1);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileCobbleBreaker();
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
    if (hasTileEntity(metadata) && !(this instanceof BlockContainer))
      world.func_147475_p(x, y, z); 
    world.func_147475_p(x, y, z);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {
      EntityPlayerMP playerMP = (EntityPlayerMP)player;
      TileEntity tileEntity = world.func_147438_o(x, y, z);
      if (tileEntity instanceof TileCobbleBreaker) {
        TileCobbleBreaker tileCobbleBreaker = (TileCobbleBreaker)tileEntity;
        tileCobbleBreaker.setOwner(playerMP);
      } 
      player.openGui(PalaMod.instance, PGuiRegistry.GUI_COBBLEBREAKER, world, x, y, z);
    } 
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iiconRegister) {
    this
      .field_149761_L = iiconRegister.func_94245_a("palamod:machines/cobblebreaker/cobblebreaker_default_side");
    for (CobbleBreakerUpgrade upgrade : CobbleBreakerUpgrade.values()) {
      this.icons[upgrade.ordinal()][0] = iiconRegister.func_94245_a("palamod:machines/cobblebreaker/cobblebreaker_" + upgrade
          .toString().toLowerCase() + "_front");
      this.icons[upgrade.ordinal()][1] = iiconRegister.func_94245_a("palamod:machines/cobblebreaker/cobblebreaker_" + upgrade
          .toString().toLowerCase() + "_top");
      this.icons[upgrade.ordinal()][2] = iiconRegister.func_94245_a("palamod:machines/cobblebreaker/cobblebreaker_" + upgrade
          .toString().toLowerCase() + "_side");
    } 
  }
  
  public IIcon func_149673_e(IBlockAccess iblockaccess, int x, int y, int z, int side) {
    TileEntity tile = iblockaccess.func_147438_o(x, y, z);
    if (!(tile instanceof TileCobbleBreaker))
      return this.icons[0][0]; 
    TileCobbleBreaker cobbleBreaker = (TileCobbleBreaker)tile;
    CobbleBreakerUpgrade upgrade = cobbleBreaker.getUpgrade();
    int meta = iblockaccess.func_72805_g(x, y, z);
    return (side == 1) ? this.icons[upgrade.ordinal()][1] : ((side == 0) ? this.icons[upgrade
        .ordinal()][1] : ((side != meta) ? this.icons[upgrade
        .ordinal()][2] : this.icons[upgrade.ordinal()][0]));
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return null;
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileCobbleBreaker))
      return new ArrayList<>(); 
    TileCobbleBreaker cobbleBreaker = (TileCobbleBreaker)tileEntity;
    ArrayList<ItemStack> drops = new ArrayList<>();
    drops.add(new ItemStack((Block)BlocksRegister.COBBLEBREAKER));
    if (cobbleBreaker.getUpgrade() != null && 
      cobbleBreaker.getUpgrade().getItem() != null)
      drops.add(new ItemStack(cobbleBreaker.getUpgrade().getItem())); 
    return drops;
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
  public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
    return Item.func_150898_a((Block)this);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#4.-cobblebreaker";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\machine\BlockCobbleBreaker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */