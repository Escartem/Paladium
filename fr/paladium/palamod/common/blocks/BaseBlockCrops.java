package fr.paladium.palamod.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BaseBlockCrops extends BlockBush implements IGrowable {
  protected String field_149770_b;
  
  @SideOnly(Side.CLIENT)
  private IIcon[] icons;
  
  private final String[] textureNames;
  
  private int stageMax = 2;
  
  public int getStageMax() {
    return this.stageMax;
  }
  
  public BaseBlockCrops(String unlocalizedName, String[] textureNames, int lvlmin, int stageMax) {
    this.field_149770_b = unlocalizedName;
    func_149663_c(this.field_149770_b);
    func_149658_d("palamod:crops/" + textureNames[0]);
    this.textureNames = textureNames;
    this.stageMax = stageMax;
    func_149675_a(true);
    float f = 0.5F;
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
    func_149711_c(0.0F);
    func_149672_a(Block.field_149779_h);
    func_149647_a((CreativeTabs)null);
    func_149649_H();
  }
  
  protected boolean func_149854_a(Block parBlock) {
    if (parBlock.getClass().equals(BlockFarmland.class))
      return true; 
    return false;
  }
  
  public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
    return world.func_147468_f(x, y, z);
  }
  
  private void incrementGrowStage(World parWorld, Random parRand, int parX, int parY, int parZ) {
    int growStage = parWorld.func_72805_g(parX, parY, parZ) + MathHelper.func_76136_a(parRand, 2, 5);
    if (growStage > this.stageMax - 1)
      growStage = this.stageMax - 1; 
    parWorld.func_72921_c(parX, parY, parZ, growStage, 2);
  }
  
  public int func_149745_a(Random p_149745_1_) {
    return 0;
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);
    if (metadata == this.stageMax - 1)
      if (this == BlocksRegister.CROPS_EGGPLANT) {
        int random = (int)(Math.random() * 10000.0D);
        if (random <= 800) {
          ret.add(new ItemStack(ItemsRegister.AMETHYST_INGOT));
        } else {
          ret.add(new ItemStack(ItemsRegister.FRUIT_EGGPLANT));
        } 
        ret.add(new ItemStack((Item)ItemsRegister.SEED_EGGPLANT));
      } else if (this == BlocksRegister.CROPS_CHERVIL) {
        int random = (int)(Math.random() * 10000.0D);
        if (random <= 500) {
          ret.add(new ItemStack(ItemsRegister.TITANE_INGOT));
        } else {
          ret.add(new ItemStack(ItemsRegister.FRUIT_CHERVIL));
        } 
        ret.add(new ItemStack((Item)ItemsRegister.SEED_CHERVIL));
      } else if (this == BlocksRegister.CROPS_KIWANO) {
        int random = (int)(Math.random() * 10000.0D);
        if (random <= 200) {
          ItemStack itemStack = new ItemStack(ItemsRegister.PALADIUM_INGOT);
          ret.add(itemStack);
          PPalaDynamique.instance.addGenerated("CROPS", itemStack.field_77994_a);
        } else {
          ret.add(new ItemStack(ItemsRegister.FRUIT_KIWANO));
        } 
        ret.add(new ItemStack((Item)ItemsRegister.SEED_KIWANO));
      } else if (this == BlocksRegister.CROPS_ORANGEBLUE) {
        int random = (int)(Math.random() * 10000.0D);
        if (random <= 25) {
          ret.add(new ItemStack(ItemsRegister.ENDIUM_NUGGET));
        } else {
          ret.add(new ItemStack(ItemsRegister.FRUIT_ORANGEBLUE));
        } 
        int r = (int)(Math.random() * 30.0D);
        if (r <= 1)
          ret.add(new ItemStack((Item)ItemsRegister.SEED_ORANGEBLUE)); 
      } else if (this == BlocksRegister.CROPS_ICE) {
        ret.add(new ItemStack((Block)BlocksRegister.CROPS_ICE, 1, metadata));
      }  
    return ret;
  }
  
  public int func_149645_b() {
    return 1;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister) {
    this.icons = new IIcon[this.textureNames.length - 1];
    for (int i = 0; i < this.icons.length; i++)
      this.icons[i] = iconRegister.func_94245_a("palamod:crops/" + this.textureNames[i]); 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int metadata) {
    if (metadata == 0)
      return this.icons[metadata % this.icons.length]; 
    return this.icons[(metadata - 1) % this.icons.length];
  }
  
  public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
    super.func_149674_a(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
    if (p_149674_1_.func_72957_l(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9) {
      int l = p_149674_1_.func_72805_g(p_149674_2_, p_149674_3_, p_149674_4_);
      if (l < this.stageMax - 1) {
        float f = func_149864_n(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);
        if (p_149674_5_.nextInt((int)(25.0F / f) + 1) == 0) {
          l++;
          p_149674_1_.func_72921_c(p_149674_2_, p_149674_3_, p_149674_4_, l, 2);
        } 
      } 
    } 
  }
  
  private float func_149864_n(World p_149864_1_, int p_149864_2_, int p_149864_3_, int p_149864_4_) {
    float f = 1.0F;
    Block block = p_149864_1_.func_147439_a(p_149864_2_, p_149864_3_, p_149864_4_ - 1);
    Block block1 = p_149864_1_.func_147439_a(p_149864_2_, p_149864_3_, p_149864_4_ + 1);
    Block block2 = p_149864_1_.func_147439_a(p_149864_2_ - 1, p_149864_3_, p_149864_4_);
    Block block3 = p_149864_1_.func_147439_a(p_149864_2_ + 1, p_149864_3_, p_149864_4_);
    Block block4 = p_149864_1_.func_147439_a(p_149864_2_ - 1, p_149864_3_, p_149864_4_ - 1);
    Block block5 = p_149864_1_.func_147439_a(p_149864_2_ + 1, p_149864_3_, p_149864_4_ - 1);
    Block block6 = p_149864_1_.func_147439_a(p_149864_2_ + 1, p_149864_3_, p_149864_4_ + 1);
    Block block7 = p_149864_1_.func_147439_a(p_149864_2_ - 1, p_149864_3_, p_149864_4_ + 1);
    boolean flag = (block2 == this || block3 == this);
    boolean flag1 = (block == this || block1 == this);
    boolean flag2 = (block4 == this || block5 == this || block6 == this || block7 == this);
    for (int l = p_149864_2_ - 1; l <= p_149864_2_ + 1; l++) {
      for (int i1 = p_149864_4_ - 1; i1 <= p_149864_4_ + 1; i1++) {
        float f1 = 0.0F;
        if (p_149864_1_.func_147439_a(l, p_149864_3_ - 1, i1).canSustainPlant((IBlockAccess)p_149864_1_, l, p_149864_3_ - 1, i1, ForgeDirection.UP, (IPlantable)this)) {
          f1 = 1.0F;
          if (p_149864_1_.func_147439_a(l, p_149864_3_ - 1, i1).isFertile(p_149864_1_, l, p_149864_3_ - 1, i1))
            f1 = 3.0F; 
        } 
        if (l != p_149864_2_ || i1 != p_149864_4_)
          f1 /= 4.0F; 
        f += f1;
      } 
    } 
    if (flag2 || (flag && flag1))
      f /= 2.0F; 
    return f;
  }
  
  public boolean func_149851_a(World parWorld, int parX, int parY, int parZ, boolean p_149851_5_) {
    return false;
  }
  
  public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
    return true;
  }
  
  public void func_149853_b(World parWorld, Random parRand, int parX, int parY, int parZ) {
    incrementGrowStage(parWorld, parRand, parX, parY, parZ);
  }
  
  public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
    return EnumPlantType.Crop;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\blocks\BaseBlockCrops.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */