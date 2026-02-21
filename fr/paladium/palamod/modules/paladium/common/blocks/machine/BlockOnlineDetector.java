package fr.paladium.palamod.modules.paladium.common.blocks.machine;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.paladium.common.logics.TileEntityOnlineDetector;
import fr.paladium.palamod.modules.paladium.network.OpenGuiPacket;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockOnlineDetector extends BlockContainer implements ITooltipWiki {
  protected String field_149770_b;
  
  private IIcon[] icons;
  
  public BlockOnlineDetector(String unlocalizedName) {
    super(Material.field_151573_f);
    this.field_149770_b = unlocalizedName;
    func_149663_c(this.field_149770_b);
    func_149713_g(255);
    func_149715_a(0.0F);
    func_149711_c(4.0F);
    func_149752_b(4.0F);
    setHarvestLevel("pickaxe", 1);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (!world.field_72995_K)
      PalaMod.getNetwork().sendTo((IMessage)new OpenGuiPacket(PGuiRegistry.GUI_ONLINE_DETECTOR, true, x, y, z), (EntityPlayerMP)player); 
    return true;
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityOnlineDetector();
  }
  
  public void func_149651_a(IIconRegister icon) {
    this.icons = new IIcon[2];
    this.icons[0] = icon.func_94245_a("palamod:machines/onlinedetector_off");
    this.icons[1] = icon.func_94245_a("palamod:machines/onlinedetector_on");
  }
  
  public IIcon func_149691_a(int side, int meta) {
    return this.icons[meta % this.icons.length];
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pillage#7.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\machine\BlockOnlineDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */