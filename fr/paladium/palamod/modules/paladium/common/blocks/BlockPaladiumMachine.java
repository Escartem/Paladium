package fr.paladium.palamod.modules.paladium.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.blocks.BaseBlockContainer;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.paladium.common.logics.PaladiumMachineLogic;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockPaladiumMachine extends BaseBlockContainer implements ITooltipWiki {
  private IIcon topIcon;
  
  public BlockPaladiumMachine(Material material, float hardness, String texture) {
    super(material, hardness, texture);
    func_149752_b(8.0F);
    setHarvestLevel("pickaxe", 2);
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new PaladiumMachineLogic();
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    TileEntity tile = world.func_147438_o(x, y, z);
    if (!(tile instanceof PaladiumMachineLogic)) {
      world.func_147468_f(x, y, z);
      return false;
    } 
    PaladiumMachineLogic logic = (PaladiumMachineLogic)tile;
    logic.setPlayer(player);
    if (!world.field_72995_K)
      player.openGui(PalaMod.instance, PGuiRegistry.GUI_PALADIUM_MACHINE, world, x, y, z); 
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int metadata) {
    if (side == 1)
      return this.topIcon; 
    return this.field_149761_L;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iiconRegister) {
    this.field_149761_L = iiconRegister.func_94245_a("palamod:" + this.texture + "_side");
    this.topIcon = iiconRegister.func_94245_a("palamod:" + this.texture + "_top");
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#14.-palamachine";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockPaladiumMachine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */