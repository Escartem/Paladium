package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.tile.TileEntityMarchTrophy;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockMarchTrophy extends Block implements ITileEntityProvider {
  private static final String NAME = "march_trophy";
  
  public BlockMarchTrophy() {
    super(Material.field_151575_d);
    func_149663_c("march_trophy");
    func_149658_d("palamod:march_trophy");
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(1.0F);
  }
  
  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    if (!world.field_72995_K) {
      TileEntityMarchTrophy te = (TileEntityMarchTrophy)world.func_147438_o(x, y, z);
      if (te != null) {
        ItemStack item = new ItemStack(this);
        item.func_77964_b(meta);
        if (te.getOwner() != null && !te.getOwner().isEmpty()) {
          NBTTagCompound compound = new NBTTagCompound();
          if (item.func_77942_o())
            compound = item.func_77978_p(); 
          compound.func_74778_a("owner", te.getOwner());
          item.func_77982_d(compound);
        } 
        EntityItem entityItem = new EntityItem(world, x, y, z, item);
        entityItem.field_145804_b = 0;
        world.func_72838_d((Entity)entityItem);
      } 
    } 
    super.func_149681_a(world, x, y, z, meta, player);
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    MonthlyUtils.setDefaultDirection(world, x, y, z);
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase living, ItemStack item) {
    super.func_149689_a(world, x, y, z, living, item);
    MonthlyUtils.onBlockPlacedBy(world, x, y, z, living);
    TileEntityMarchTrophy tile = (TileEntityMarchTrophy)world.func_147438_o(x, y, z);
    if (!item.func_77942_o()) {
      NBTTagCompound nbt = new NBTTagCompound();
      item.func_77982_d(nbt);
    } 
    if (!item.func_77978_p().func_74764_b("owner"))
      item.func_77978_p().func_74778_a("owner", living.func_70005_c_()); 
    if (item.func_77942_o() && 
      item.func_77978_p().func_74764_b("owner"))
      tile.setOwner(item.func_77978_p().func_74779_i("owner")); 
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public boolean func_149716_u() {
    return true;
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileEntityMarchTrophy();
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderMarchTrophy;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\block\BlockMarchTrophy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */