package fr.paladium.palamod.modules.paladium.common.blocks.machine;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.paladium.common.logics.AlchemyCreatorLogic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAlchemyCreator extends BlockContainer implements ITooltipWiki {
  protected String field_149770_b;
  
  public BlockAlchemyCreator(String unlocalizedName) {
    super(Material.field_151573_f);
    this.field_149770_b = unlocalizedName;
    func_149663_c(this.field_149770_b);
    func_149658_d("minecraft:stone");
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.8F, 1.0F);
    func_149711_c(1.5F);
    func_149752_b(8.0F);
    setHarvestLevel("pickaxe", 1);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new AlchemyCreatorLogic();
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K)
      player.openGui(PalaMod.instance, PGuiRegistry.GUI_ALCHEMY_CREATOR_POTION, world, x, y, z); 
    return true;
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
    super.func_149689_a(world, x, y, z, entity, stack);
    byte b0 = 3;
    b0 = rotateBlock(b0, entity);
    world.func_72921_c(x, y, z, b0, 2);
  }
  
  public byte rotateBlock(byte b0, EntityLivingBase entity) {
    if ((entity.field_70177_z >= 135.0F && entity.field_70177_z <= 181.0F) || (entity.field_70177_z <= -135.0F && entity.field_70177_z >= -181.0F)) {
      b0 = 3;
    } else if (entity.field_70177_z > -135.0F && entity.field_70177_z < -45.0F) {
      b0 = 4;
    } else if (entity.field_70177_z >= -45.0F && entity.field_70177_z <= 45.0F) {
      b0 = 2;
    } else if (entity.field_70177_z > 45.0F && entity.field_70177_z < 135.0F) {
      b0 = 5;
    } else if (entity.field_70177_z >= 181.0F) {
      entity.field_70177_z -= 360.0F;
      b0 = rotateBlock(b0, entity);
    } else if (entity.field_70177_z <= -181.0F) {
      entity.field_70177_z += 360.0F;
      b0 = rotateBlock(b0, entity);
    } 
    return b0;
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
    TileEntity tileentity = world.func_147438_o(x, y, z);
    if (tileentity instanceof IInventory) {
      IInventory inv = (IInventory)tileentity;
      for (int i1 = 0; i1 < inv.func_70302_i_(); i1++) {
        ItemStack itemstack = inv.func_70301_a(i1);
        if (itemstack != null) {
          float f = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
          float f1 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
          for (float f2 = world.field_73012_v.nextFloat() * 0.8F + 0.1F; itemstack.field_77994_a > 0; world
            .func_72838_d((Entity)entityitem)) {
            int j1 = world.field_73012_v.nextInt(21) + 10;
            if (j1 > itemstack.field_77994_a)
              j1 = itemstack.field_77994_a; 
            itemstack.field_77994_a -= j1;
            EntityItem entityitem = new EntityItem(world, (x + f), (y + f1), (z + f2), new ItemStack(itemstack.func_77973_b(), j1, itemstack.func_77960_j()));
            float f3 = 0.05F;
            entityitem.field_70159_w = ((float)world.field_73012_v.nextGaussian() * 0.05F);
            entityitem.field_70181_x = ((float)world.field_73012_v.nextGaussian() * 0.05F + 0.2F);
            entityitem.field_70179_y = ((float)world.field_73012_v.nextGaussian() * 0.05F);
            if (itemstack.func_77942_o())
              entityitem.func_92059_d()
                .func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b()); 
          } 
        } 
      } 
      world.func_147453_f(x, y, z, block);
    } 
    super.func_149749_a(world, x, y, z, block, metadata);
  }
  
  public int func_149645_b() {
    return -1;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#1.-alchemy-creator";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\machine\BlockAlchemyCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */