package fr.paladium.palamod.common.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BaseBlockFlower extends BlockBush implements ITooltipWiki {
  protected String field_149770_b;
  
  protected IIcon[] field_149761_L;
  
  public BaseBlockFlower(String unlocalizedName) {
    super(Material.field_151585_k);
    this.field_149770_b = unlocalizedName;
    func_149663_c(this.field_149770_b);
    func_149658_d("palamod:flowers/" + this.field_149770_b);
    func_149676_a(0.3F, 0.0F, 0.3F, 0.8F, 1.0F, 0.8F);
    func_149711_c(0.0F);
    func_149672_a(Block.field_149779_h);
    func_149647_a((CreativeTabs)Registry.PLANTS_TAB);
    if ("flower_paladium".equals(this.field_149770_b))
      func_149715_a(1.0F); 
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    world.func_147464_a(x, y, z, (Block)this, 50);
    super.func_149726_b(world, x, y, z);
  }
  
  protected boolean func_149700_E() {
    return true;
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return null;
  }
  
  public int func_149745_a(Random p_149745_1_) {
    return 1;
  }
  
  public void func_149674_a(World worldObj, int xCoord, int yCoord, int zCoord, Random random) {
    super.func_149674_a(worldObj, xCoord, yCoord, zCoord, random);
    if (this == BlocksRegister.FLOWER_CLATHRUSARCHERI) {
      AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(xCoord + this.field_149759_B, yCoord + this.field_149760_C, zCoord + this.field_149754_D, xCoord + this.field_149755_E, yCoord + this.field_149756_F, zCoord + this.field_149757_G);
      bounds = bounds.func_72314_b(6.0D, 6.0D, 6.0D);
      List<EntityPlayer> entities = worldObj.func_72872_a(EntityPlayer.class, bounds);
      for (EntityPlayer p : entities)
        p.func_70690_d(new PotionEffect(Potion.field_76428_l.func_76396_c(), 440)); 
    } else if (this == BlocksRegister.FLOWER_ENDIUM) {
      AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(xCoord + this.field_149759_B, yCoord + this.field_149760_C, zCoord + this.field_149754_D, xCoord + this.field_149755_E, yCoord + this.field_149756_F, zCoord + this.field_149757_G);
      bounds = bounds.func_72314_b(20.0D, 20.0D, 20.0D);
      List<?> entities = worldObj.func_72872_a(EntityPlayer.class, bounds);
      if (entities.size() > 0) {
        worldObj.func_72921_c(xCoord, yCoord, zCoord, 1, 2);
      } else {
        worldObj.func_72921_c(xCoord, yCoord, zCoord, 0, 2);
      } 
    } 
    worldObj.func_147464_a(xCoord, yCoord, zCoord, (Block)this, 50);
  }
  
  public void func_149651_a(IIconRegister p_149651_1_) {
    if (this == BlocksRegister.FLOWER_ENDIUM) {
      this.field_149761_L = new IIcon[2];
      this.field_149761_L[0] = p_149651_1_.func_94245_a("palamod:flowers/flower_endium");
      this.field_149761_L[1] = p_149651_1_.func_94245_a("palamod:flowers/flower_endium_detected");
    } else {
      this.field_149761_L = new IIcon[1];
      this.field_149761_L[0] = p_149651_1_.func_94245_a("palamod:flowers/" + this.field_149770_b + "");
    } 
  }
  
  public IIcon func_149691_a(int side, int meta) {
    if (this != BlocksRegister.FLOWER_ENDIUM)
      return this.field_149761_L[0]; 
    if (meta > 1)
      meta = 1; 
    return this.field_149761_L[meta];
  }
  
  public boolean func_149727_a(World w, int x, int y, int z, EntityPlayer p, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (!w.field_72995_K && this == BlocksRegister.FLOWER_MINERAL && p.field_71071_by.func_70448_g() != null && p.field_71071_by
      .func_70448_g().func_77973_b() instanceof net.minecraft.item.ItemDye && p.field_71071_by
      .func_70448_g().func_77960_j() == 15) {
      if (w.func_72805_g(x, y, z) < 6) {
        w.func_72921_c(x, y, z, w.func_72805_g(x, y, z) + 1, 2);
        (p.field_71071_by.func_70448_g()).field_77994_a--;
        return true;
      } 
      if (w.func_72805_g(x, y, z) >= 6) {
        (p.field_71071_by.func_70448_g()).field_77994_a--;
        w.func_147468_f(x, y, z);
        Item[] minerais = { ItemsRegister.TITANE_INGOT, ItemsRegister.PALADIUM_INGOT, ItemsRegister.AMETHYST_INGOT };
        Item selectedItem = minerais[w.field_73012_v.nextInt(minerais.length)];
        int stackSize = 1;
        if (selectedItem == ItemsRegister.TITANE_INGOT) {
          stackSize = w.field_73012_v.nextInt(3) + 2;
        } else if (selectedItem == ItemsRegister.AMETHYST_INGOT) {
          stackSize = w.field_73012_v.nextInt(3) + 3;
        } else if (selectedItem == ItemsRegister.PALADIUM_INGOT) {
          stackSize = w.field_73012_v.nextInt(2) + 1;
          PPalaDynamique.instance.addGenerated("MINERAL_FLOWER", stackSize);
        } 
        ItemStack selected = new ItemStack(selectedItem, stackSize);
        EntityItem entityitem = p.func_71019_a(selected, false);
        entityitem.field_145804_b = 0;
        entityitem.func_145797_a(p.func_70005_c_());
        return true;
      } 
    } 
    return false;
  }
  
  public String getUrl(ItemStack item) {
    if (item
      .func_77973_b() != Item.func_150898_a(BlocksRegister.FLOWER_CLATHRUSARCHERI) && item
      .func_77973_b() != Item.func_150898_a(BlocksRegister.FLOWER_ENDIUM) && item
      .func_77973_b() != Item.func_150898_a(BlocksRegister.FLOWER_PALADIUM) && item
      .func_77973_b() != Item.func_150898_a(BlocksRegister.FLOWER_MINERAL))
      return null; 
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-ressources-naturelles#1.-les-plantes";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\blocks\BaseBlockFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */