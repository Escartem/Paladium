package fr.paladium.palamod.modules.luckyblock.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySuperCraftingTable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSuperCraftingTable extends Block {
  @SideOnly(Side.CLIENT)
  private IIcon field_150035_a;
  
  @SideOnly(Side.CLIENT)
  private IIcon field_150034_b;
  
  private static final String __OBFID = "CL_00000221";
  
  private final int durability = 0;
  
  public BlockSuperCraftingTable() {
    super(Material.field_151575_d);
    func_149711_c(100.0F);
    func_149663_c("supercraftingtable");
    func_149658_d("supercraftingtable");
    func_149647_a(PLuckyBlock.TAB);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
    return (p_149691_1_ == 1) ? this.field_150035_a : ((p_149691_1_ == 0) ? Blocks.field_150344_f
      .func_149733_h(p_149691_1_) : ((p_149691_1_ != 2 && p_149691_1_ != 4) ? this.field_149761_L : this.field_150034_b));
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister p_149651_1_) {
    this
      .field_149761_L = p_149651_1_.func_94245_a("palamod:supercraftingtable/super_crafting_table_side");
    this
      .field_150035_a = p_149651_1_.func_94245_a("palamod:supercraftingtable/super_crafting_table_top");
    this
      .field_150034_b = p_149651_1_.func_94245_a("palamod:supercraftingtable/super_crafting_table_bottom");
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntitySuperCraftingTable(0);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
    super.func_149689_a(world, x, y, z, player, item);
    TileEntitySuperCraftingTable tile = (TileEntitySuperCraftingTable)world.func_147438_o(x, y, z);
    if (item.func_77942_o() && 
      item.func_77978_p().func_74764_b("durability"))
      tile.setDurability(item.func_77978_p().func_74762_e("durability")); 
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public boolean func_149727_a(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (p_149727_1_.field_72995_K)
      return true; 
    p_149727_5_.openGui(PalaMod.instance, PGuiRegistry.GUI_SUPER_CRAFTING_TABLE, p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockSuperCraftingTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */