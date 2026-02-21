package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.blocks.BaseBlockContainer;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityDyeingMachine;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockDyeingMachine extends BaseBlockContainer implements ITooltipWiki {
  private final IIcon[] icons = new IIcon[3];
  
  public BlockDyeingMachine() {
    super(Material.field_151576_e, 5.0F, "palamod:dyeing_machine");
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("dyeing_machine");
    func_149658_d("palamod:dyeing_machine");
    func_149711_c(5.0F);
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons[0] = register.func_94245_a(func_149641_N() + "_top");
    this.icons[1] = register.func_94245_a(func_149641_N() + "_side");
    this.icons[2] = register.func_94245_a(func_149641_N() + "_bottom");
    super.func_149651_a(register);
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityDyeingMachine();
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (!world.field_72995_K) {
      TileEntityDyeingMachine tileentity = (TileEntityDyeingMachine)world.func_147438_o(x, y, z);
      if (tileentity != null)
        player.openGui(PalaMod.instance, PGuiRegistry.GUI_DYEING_MACHINE, world, x, y, z); 
    } 
    return true;
  }
  
  public IIcon func_149691_a(int side, int meta) {
    ForgeDirection dir = ForgeDirection.getOrientation(side);
    if (dir == ForgeDirection.UP)
      return this.icons[0]; 
    if (dir == ForgeDirection.DOWN)
      return this.icons[2]; 
    return this.icons[1];
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
    TileEntity tileentity = world.func_147438_o(x, y, z);
    if (tileentity instanceof IInventory) {
      IInventory inv = (IInventory)tileentity;
      for (int i1 = 0; i1 < inv.func_70302_i_() - 1 && this.drop; i1++) {
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
    if (hasTileEntity(metadata) && !(this instanceof net.minecraft.block.BlockContainer))
      world.func_147475_p(x, y, z); 
    world.func_147475_p(x, y, z);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.-lucky-block-en-paladium-et-en-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockDyeingMachine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */