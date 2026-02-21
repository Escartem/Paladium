package fr.paladium.palamod.modules.paladium.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.logics.TileEntityTotem;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockTotem extends BlockContainer {
  private final String name;
  
  private int radius;
  
  public BlockTotem(String name) {
    super(Material.field_151575_d);
    this.name = name;
    func_149663_c(name);
    func_149658_d("palamod:totem");
    func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 2.0F, 0.75F);
    func_149711_c(4.0F);
    func_149672_a(Block.field_149766_f);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int meta, float what, float these, float are) {
    TileEntity target = world.func_147438_o(x, y, z);
    if (!(target instanceof TileEntityTotem))
      return false; 
    TileEntityTotem totem = (TileEntityTotem)target;
    totem.setOwnerId(player.func_110124_au());
    if (player.func_70093_af()) {
      totem.lightZone(player);
    } else if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b().equals(ItemsRegister.PALADIUM_INGOT)) {
      totem.addfuel(player);
    } 
    return true;
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
    super.func_149689_a(world, x, y, z, entity, itemStack);
    world.func_72921_c(x, y, z, MathHelper.func_76128_c((entity.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3, 2);
  }
  
  private void dropItems(World world, int x, int y, int z) {
    Random rand = world.field_73012_v;
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (tileEntity instanceof TileEntityTotem) {
      TileEntityTotem totem = (TileEntityTotem)tileEntity;
      if (totem.getFuel() != null) {
        float rx = rand.nextFloat() * 0.8F + 0.1F;
        float ry = rand.nextFloat() * 0.8F + 0.1F;
        float rz = rand.nextFloat() * 0.8F + 0.1F;
        EntityItem entityItem = new EntityItem(world, (x + rx), (y + ry), (z + rz), new ItemStack(totem.getFuel().func_77973_b(), (totem.getFuel()).field_77994_a - 1, totem.getFuel().func_77960_j()));
        if (totem.getFuel().func_77942_o())
          entityItem.func_92059_d().func_77982_d((NBTTagCompound)totem.getFuel().func_77978_p().func_74737_b()); 
        float factor = 0.05F;
        entityItem.field_70159_w = rand.nextGaussian() * 0.05000000074505806D;
        entityItem.field_70181_x = rand.nextGaussian() * 0.05000000074505806D + 0.20000000298023224D;
        entityItem.field_70179_y = rand.nextGaussian() * 0.05000000074505806D;
        world.func_72838_d((Entity)entityItem);
        (totem.getFuel()).field_77994_a = 0;
      } 
    } 
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int what) {
    dropItems(world, x, y, z);
    super.func_149749_a(world, x, y, z, block, what);
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityTotem();
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public int getRadius() {
    return this.radius;
  }
  
  public String getName() {
    return this.name;
  }
  
  public int func_149645_b() {
    return -1;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public String func_149702_O() {
    return "palamod:" + this.name;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockTotem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */