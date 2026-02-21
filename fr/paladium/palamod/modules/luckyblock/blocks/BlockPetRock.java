package fr.paladium.palamod.modules.luckyblock.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityPetRock;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPetRock extends Block {
  public BlockPetRock() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("petrock");
    func_149676_a(0.1F, 0.0F, 0.3F, 0.8F, 0.55F, 0.7F);
    func_149658_d("palamod:petrock/petrock");
    func_149711_c(1.0F);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
    super.func_149689_a(world, x, y, z, player, item);
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    super.func_149681_a(world, x, y, z, meta, player);
  }
  
  public boolean func_149727_a(World w, int x, int y, int z, EntityPlayer p, int ix, float fx, float fy, float fz) {
    p.func_85030_a("palamod:records.fuzeiii_chimp", 1.0F, 1.0F);
    for (int i = 0; i < 5; i++)
      w.func_72869_a("heart", x + 0.5D, y + 0.5D + (i / 7.0F), z + 0.5D, 0.0D, w.field_73012_v
          .nextDouble() * 4.0D, 0.0D); 
    return true;
  }
  
  public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {}
  
  public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {}
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderPetRock;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityPetRock();
  }
  
  public void func_149666_a(Item item, CreativeTabs tab, List list) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockPetRock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */