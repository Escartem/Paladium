package fr.paladium.palamod.modules.luckyblock.blocks.christmas;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityChristmasMockup;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockChristmasMockup extends Block {
  public static final String GENERIC_NAME = "christmas_mockup";
  
  public static final int MAX_MOCKUP = 8;
  
  public static final String[] subBlocks = new String[] { "deer0", "deer1", "deer2", "deer3", "deer4", "deer5", "sleigh", "santa_claus" };
  
  public BlockChristmasMockup() {
    super(Material.field_151573_f);
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(3.5F);
    func_149752_b(5.0F);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityChristmasMockup();
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public void func_149681_a(World world, int x, int y, int z, int metadata, EntityPlayer player) {
    if (!world.field_72995_K) {
      ItemStack item = new ItemStack(this, 1, metadata);
      item.func_77964_b(metadata);
      EntityItem entityItem = new EntityItem(world, x, y, z, item);
      entityItem.field_145804_b = 0;
      world.func_72838_d((Entity)entityItem);
    } 
    super.func_149681_a(world, x, y, z, metadata, player);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
    if (!world.field_72995_K)
      super.func_149689_a(world, x, y, z, player, item); 
    TileEntity buffer = world.func_147438_o(x, y, z);
    if (buffer instanceof TileEntityChristmasMockup) {
      TileEntityChristmasMockup tileEntity = (TileEntityChristmasMockup)world.func_147438_o(x, y, z);
      int rotate = Math.abs(Math.round(player.field_70177_z % 360.0F / 90.0F)) % 4;
      tileEntity.setOrientation(rotate * -90);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < 8; i++)
      list.add(new ItemStack(item, 1, i)); 
  }
  
  public int func_149692_a(int metadata) {
    if (metadata >= 8 || metadata < 0)
      return 0; 
    return metadata;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderChristmasMockup;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\christmas\BlockChristmasMockup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */