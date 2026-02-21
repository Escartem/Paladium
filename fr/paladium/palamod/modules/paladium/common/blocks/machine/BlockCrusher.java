package fr.paladium.palamod.modules.paladium.common.blocks.machine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.paladium.common.logics.TileCrusher;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCrusher extends BlockContainer implements ITooltipWiki {
  protected String field_149770_b;
  
  private IIcon front;
  
  private IIcon top;
  
  public BlockCrusher() {
    super(Material.field_151573_f);
    this.field_149770_b = "crusher";
    func_149663_c(this.field_149770_b);
    func_149711_c(12.0F);
    func_149752_b(8.0F);
    setHarvestLevel("pickaxe", 1);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new TileCrusher();
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {
      if (!JobsBridge.canUseCraft(player, new ItemStack((Block)BlocksRegister.CRUSHER), true))
        return true; 
      setOwner(player, world, x, y, z);
      player.openGui(PalaMod.instance, PGuiRegistry.GUI_CRUSHER, world, x, y, z);
    } 
    return true;
  }
  
  public void setOwner(EntityPlayer player, World world, int x, int y, int z) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileCrusher))
      return; 
    TileCrusher tileCrusher = (TileCrusher)tileEntity;
    tileCrusher.setOwnerId(player.func_110124_au());
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iiconRegister) {
    this.field_149761_L = iiconRegister.func_94245_a("palamod:machines/crusher_side");
    this.front = iiconRegister.func_94245_a("palamod:machines/crusher_front");
    this.top = iiconRegister.func_94245_a("palamod:machines/crusher_top_off");
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int metadata) {
    return (side == 1) ? this.top : ((side == 0) ? this.top : ((side != metadata) ? this.field_149761_L : this.front));
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return Item.func_150898_a((Block)this);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    direction(world, x, y, z);
  }
  
  private void direction(World world, int x, int y, int z) {
    if (!world.field_72995_K) {
      byte byte0 = 3;
      Block direction = world.func_147439_a(x, y, z - 1);
      if (direction.func_149730_j() && direction.func_149730_j())
        byte0 = 3; 
      direction = world.func_147439_a(x, y, z + 1);
      if (direction.func_149730_j() && direction.func_149730_j())
        byte0 = 2; 
      direction = world.func_147439_a(x - 1, y, z);
      if (direction.func_149730_j() && direction.func_149730_j())
        byte0 = 5; 
      direction = world.func_147439_a(x + 1, y, z);
      if (direction.func_149730_j() && direction.func_149730_j())
        byte0 = 3; 
      world.func_72921_c(x, y, z, byte0, 2);
    } 
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
    int direction = MathHelper.func_76128_c((entity.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    if (direction == 0) {
      world.func_72921_c(x, y, z, 2, 2);
    } else if (direction == 1) {
      world.func_72921_c(x, y, z, 5, 2);
    } else if (direction == 2) {
      world.func_72921_c(x, y, z, 3, 2);
    } else if (direction == 3) {
      world.func_72921_c(x, y, z, 4, 2);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
    return Item.func_150898_a((Block)this);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#6.-crusher";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\machine\BlockCrusher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */