package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.tiles.TileEntityChristmasWreath;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChristmasWreath extends Block implements ITileEntityProvider, ITooltipInformations {
  private static final String NAME = "christmas_wreath";
  
  private static final String DESCRIPTION = "";
  
  public BlockChristmasWreath() {
    super(Material.field_151575_d);
    func_149663_c("christmas_wreath");
    func_149658_d("palamod:christmas_wreath");
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(1.0F);
    float f = 0.25F;
    float f1 = 1.0F;
    func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (world.field_72995_K)
      return; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof TileEntityChristmasWreath))
      return; 
    TileEntityChristmasWreath wreath = (TileEntityChristmasWreath)tile;
    ItemStack stack = new ItemStack(BlocksRegister.CHRISTMAS_WREATH);
    NBTTagCompound tag = new NBTTagCompound();
    tag.func_74768_a("durability", wreath.getDurability());
    tag.func_74772_a("nextUseMillis", wreath.getNextUseMillis());
    stack.func_77982_d(tag);
    PlayerUtils.dropItemStack((Entity)player, x, y, z, stack);
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    MonthlyUtils.setDefaultDirection(world, x, y, z);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase living, ItemStack item) {
    super.func_149689_a(world, x, y, z, living, item);
    MonthlyUtils.onBlockPlacedBy(world, x, y, z, living);
    NBTTagCompound compound = item.func_77978_p();
    TileEntity tile = world.func_147438_o(x, y, z);
    if (compound == null || !(tile instanceof TileEntityChristmasWreath))
      return; 
    TileEntityChristmasWreath wreath = (TileEntityChristmasWreath)tile;
    if (compound.func_74764_b("durability"))
      wreath.setDurability(compound.func_74762_e("durability")); 
    if (compound.func_74764_b("nextUseMillis"))
      wreath.setNextUseMillis(compound.func_74763_f("nextUseMillis")); 
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public boolean func_149716_u() {
    return true;
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityChristmasWreath();
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("");
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }
  
  public void func_149719_a(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
    int l = p_149719_1_.func_72805_g(p_149719_2_, p_149719_3_, p_149719_4_);
    float f = 0.28125F;
    float f1 = 0.78125F;
    float f2 = 0.0F;
    float f3 = 1.0F;
    float f4 = 0.125F;
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    if (l == 2)
      func_149676_a(f2, f, 1.0F - f4, f3, f1, 1.0F); 
    if (l == 3)
      func_149676_a(f2, f, 0.0F, f3, f1, f4); 
    if (l == 4)
      func_149676_a(1.0F - f4, f, f2, 1.0F, f1, f3); 
    if (l == 5)
      func_149676_a(0.0F, f, f2, f4, f1, f3); 
  }
  
  @SideOnly(Side.CLIENT)
  public AxisAlignedBB func_149633_g(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_) {
    func_149719_a((IBlockAccess)p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
    return super.func_149633_g(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderWreath;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\common\blocks\BlockChristmasWreath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */