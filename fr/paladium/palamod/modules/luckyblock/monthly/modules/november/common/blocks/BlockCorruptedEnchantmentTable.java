package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityCorruptedEnchantmentTable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCorruptedEnchantmentTable extends BlockContainer implements ITooltipInformations {
  private static final String NAME = "corrupted_enchanting_table";
  
  private static final String DESCRIPTION = "DESC.TXT : Permet de ACTION:DISENCHANT un objet pour récupérer de la VANILLA.EXPERIENCE";
  
  @SideOnly(Side.CLIENT)
  private IIcon icon;
  
  @SideOnly(Side.CLIENT)
  private IIcon side;
  
  public BlockCorruptedEnchantmentTable() {
    super(Material.field_151576_e);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
    func_149713_g(0);
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(5.0F);
    func_149752_b(2000.0F);
    func_149663_c("corrupted_enchanting_table");
    func_149658_d("palamod:corrupted_enchanting_table");
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random random) {
    super.func_149734_b(world, x, y, z, random);
    for (int l = x - 2; l <= x + 2; l++) {
      for (int i1 = z - 2; i1 <= z + 2; i1++) {
        if (l > x - 2 && l < x + 2 && i1 == z - 1)
          i1 = z + 2; 
        if (random.nextInt(16) == 0)
          for (int j1 = y; j1 <= y + 1; j1++) {
            if (world.func_147439_a(l, j1, i1) == Blocks.field_150342_X) {
              if (!world.func_147437_c((l - x) / 2 + x, j1, (i1 - z) / 2 + z))
                break; 
              world.func_72869_a("enchantmenttable", x + 0.5D, y + 2.0D, z + 0.5D, ((l - x) + random.nextFloat()) - 0.5D, ((j1 - y) - random.nextFloat() - 1.0F), ((i1 - z) + random.nextFloat()) - 0.5D);
            } 
          }  
      } 
    } 
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
    return (p_149691_1_ == 0) ? this.side : ((p_149691_1_ == 1) ? this.icon : this.field_149761_L);
  }
  
  public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
    return (TileEntity)new TileEntityCorruptedEnchantmentTable();
  }
  
  public boolean func_149727_a(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (p_149727_1_.field_72995_K)
      return true; 
    p_149727_5_.openGui(PalaMod.instance, PGuiRegistry.GUI_CORRUPTED_ENCHANTMENT_TABLE, p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
    return true;
  }
  
  public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
    super.func_149689_a(p_149689_1_, p_149689_2_, p_149689_3_, p_149689_4_, p_149689_5_, p_149689_6_);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister p_149651_1_) {
    this.field_149761_L = p_149651_1_.func_94245_a(func_149641_N() + "_side");
    this.icon = p_149651_1_.func_94245_a(func_149641_N() + "_top");
    this.side = p_149651_1_.func_94245_a(func_149641_N() + "_bottom");
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("DESC.TXT : Permet de ACTION:DISENCHANT un objet pour récupérer de la VANILLA.EXPERIENCE");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\blocks\BlockCorruptedEnchantmentTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */