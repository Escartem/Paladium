package fr.paladium.palamod.modules.hunter.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSacrificeHotel extends Block implements ITooltipWiki {
  private IIcon[] off = new IIcon[3];
  
  private IIcon[] on = new IIcon[3];
  
  public BlockSacrificeHotel() {
    super(Material.field_151576_e);
    func_149647_a((CreativeTabs)Registry.HUNTER_TAB);
    func_149663_c("sacrifice_hotel");
    func_149658_d("palamod:sacrifice_hotel");
    func_149711_c(5.0F);
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean isValid(World world, int x, int y, int z) {
    boolean valid = false;
    valid = (getPlayers(world, x, y, z).size() > 0);
    world.func_72921_c(x, y, z, valid ? 1 : 0, 2);
    return valid;
  }
  
  public List<EntityPlayer> getPlayers(World world, int x, int y, int z) {
    List<?> entities = world.func_72872_a(EntityPlayer.class, 
        AxisAlignedBB.func_72330_a(x, y, z, (x + 1), (y + 2), (z + 1)));
    List<EntityPlayer> players = new ArrayList<>();
    for (Object obj : entities) {
      if (obj instanceof EntityPlayer)
        players.add((EntityPlayer)obj); 
    } 
    return players;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister register) {
    this.off[0] = register.func_94245_a(func_149641_N() + "_top");
    this.off[1] = register.func_94245_a(func_149641_N() + "_side");
    this.off[2] = register.func_94245_a(func_149641_N() + "_bottom");
    this.on[0] = register.func_94245_a(func_149641_N() + "_active_top");
    this.on[1] = register.func_94245_a(func_149641_N() + "_active_side");
    this.on[2] = register.func_94245_a(func_149641_N() + "_bottom");
    this.field_149761_L = this.off[1];
  }
  
  public IIcon func_149691_a(int side, int meta) {
    ForgeDirection dir = ForgeDirection.getOrientation(side);
    if (meta == 1) {
      if (dir == ForgeDirection.UP)
        return this.on[0]; 
      if (dir == ForgeDirection.DOWN)
        return this.on[2]; 
      return this.on[1];
    } 
    if (dir == ForgeDirection.UP)
      return this.off[0]; 
    if (dir == ForgeDirection.DOWN)
      return this.off[2]; 
    return this.off[1];
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#10.-les-objets-relatifs-au-metier-dhunter";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\blocks\BlockSacrificeHotel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */