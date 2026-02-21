package fr.paladium.palamod.modules.paladium.common.blocks.bush;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.client.render.BlockBushRender;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BaseBlockBush extends BlockLeavesBase implements IPlantable {
  private String unlocalizedName;
  
  private IIcon[] fastIcons;
  
  private IIcon[] fancyIcons;
  
  private String[] textureNames;
  
  public BaseBlockBush(String unlocalizedName, String[] textureNames) {
    super(Material.field_151584_j, false);
    this.unlocalizedName = unlocalizedName;
    func_149663_c(this.unlocalizedName);
    func_149658_d("palamod:bush/" + this.unlocalizedName);
    this.textureNames = textureNames;
    func_149675_a(true);
    func_149711_c(0.3F);
    func_149672_a(Block.field_149779_h);
    func_149647_a((CreativeTabs)Registry.PLANTS_TAB);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister) {
    this.fastIcons = new IIcon[this.textureNames.length];
    this.fancyIcons = new IIcon[this.textureNames.length];
    for (int i = 0; i < this.fastIcons.length; i++) {
      if (this.textureNames[i] != "") {
        this.fastIcons[i] = iconRegister
          .func_94245_a("palamod:bush/" + this.textureNames[i] + "_fast");
        this.fancyIcons[i] = iconRegister
          .func_94245_a("palamod:bush/" + this.textureNames[i] + "_fancy");
      } 
    } 
  }
  
  public IIcon func_149691_a(int side, int metadata) {
    setGraphicsLevel((Minecraft.func_71410_x()).field_71474_y.field_74347_j);
    if (this.field_150121_P) {
      if (metadata < 12)
        return this.fancyIcons[metadata % 4]; 
      return this.fancyIcons[metadata % 4 + 4];
    } 
    if (metadata < 12)
      return this.fastIcons[metadata % 4]; 
    return this.fastIcons[metadata % 4 + 4];
  }
  
  public int func_149692_a(int metadata) {
    return metadata % 4;
  }
  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    int l = world.func_72805_g(x, y, z);
    if (l < 4)
      return AxisAlignedBB.func_72330_a(x + 0.25D, y, z + 0.25D, x + 0.75D, y + 0.5D, z + 0.75D); 
    if (l < 8)
      return AxisAlignedBB.func_72330_a(x + 0.125D, y, z + 0.125D, x + 0.875D, y + 0.75D, z + 0.875D); 
    return AxisAlignedBB.func_72330_a(x + 0.0625D, y, z + 0.0625D, x + 0.9375D, y + 0.9375D, z + 0.9375D);
  }
  
  public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
    int l = world.func_72805_g(x, y, z);
    if (l < 4)
      return AxisAlignedBB.func_72330_a(x + 0.25D, y, z + 0.25D, x + 0.75D, y + 0.5D, z + 0.75D); 
    if (l < 8)
      return AxisAlignedBB.func_72330_a(x + 0.125D, y, z + 0.125D, x + 0.875D, y + 0.75D, z + 0.875D); 
    return AxisAlignedBB.func_72330_a(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D);
  }
  
  public void func_149719_a(IBlockAccess iblockaccess, int x, int y, int z) {
    float minX, minZ, maxX, maxY, maxZ;
    int md = iblockaccess.func_72805_g(x, y, z);
    float minY = 0.0F;
    if (md < 4) {
      minX = minZ = 0.25F;
      maxX = maxZ = 0.75F;
      maxY = 0.5F;
    } else if (md < 8) {
      minX = minZ = 0.125F;
      maxX = maxZ = 0.875F;
      maxY = 0.75F;
    } else {
      minX = minZ = 0.0F;
      maxX = maxZ = 1.0F;
      maxY = 1.0F;
    } 
    func_149676_a(minX, minY, minZ, maxX, maxY, maxZ);
  }
  
  public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {
    harvest(world, x, y, z, player);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    return harvest(world, x, y, z, player);
  }
  
  protected boolean harvest(World world, int x, int y, int z, EntityPlayer player) {
    return false;
  }
  
  protected void spawnItemAtPlayer(EntityPlayer player, ItemStack stack) {
    if (!player.field_70170_p.field_72995_K)
      if (player instanceof net.minecraftforge.common.util.FakePlayer || !player.field_71071_by.func_70441_a(stack)) {
        EntityItem entityitem = new EntityItem(player.field_70170_p, player.field_70165_t + 0.5D, player.field_70163_u + 0.5D, player.field_70161_v + 0.5D, stack);
        player.field_70170_p.func_72838_d((Entity)entityitem);
        if (!(player instanceof net.minecraftforge.common.util.FakePlayer))
          entityitem.func_70100_b_(player); 
      } else if (player instanceof net.minecraft.entity.player.EntityPlayerMP) {
        player.field_71069_bz.func_75142_b();
      }  
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  private void setGraphicsLevel(boolean flag) {
    this.field_150121_P = flag;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public int func_149645_b() {
    return BlockBushRender.model;
  }
  
  public boolean func_149646_a(IBlockAccess iblockaccess, int x, int y, int z, int meta) {
    if (meta > 7 || this.field_150121_P)
      return super.func_149646_a(iblockaccess, x, y, z, meta); 
    return true;
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random r) {
    if (world.field_72995_K)
      return; 
    if (r.nextInt(20) == 0) {
      int meta = world.func_72805_g(x, y, z);
      if (meta < 12)
        world.func_147465_d(x, y, z, (Block)this, meta + 4, 3); 
    } 
  }
  
  public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
    return (super.func_149742_c(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_) && 
      World.func_147466_a((IBlockAccess)p_149742_1_, p_149742_2_, p_149742_3_ - 1, p_149742_4_));
  }
  
  public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
    func_150090_e(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_);
  }
  
  private boolean func_150090_e(World p_150090_1_, int p_150090_2_, int p_150090_3_, int p_150090_4_) {
    if (!func_149718_j(p_150090_1_, p_150090_2_, p_150090_3_, p_150090_4_)) {
      func_149697_b(p_150090_1_, p_150090_2_, p_150090_3_, p_150090_4_, p_150090_1_
          .func_72805_g(p_150090_2_, p_150090_3_, p_150090_4_), 0);
      p_150090_1_.func_147468_f(p_150090_2_, p_150090_3_, p_150090_4_);
      return false;
    } 
    return true;
  }
  
  public boolean func_149718_j(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_) {
    return !p_149718_1_.func_147437_c(p_149718_2_, p_149718_3_ - 1, p_149718_4_);
  }
  
  public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
    return EnumPlantType.Cave;
  }
  
  public Block getPlant(IBlockAccess world, int x, int y, int z) {
    return (Block)this;
  }
  
  public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
    return world.func_72805_g(x, y, z) - 4;
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    if (!(entity instanceof EntityItem))
      entity.func_70097_a(DamageSource.field_76367_g, 1.0F); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\bush\BaseBlockBush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */