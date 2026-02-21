package fr.paladium.palamod.modules.alchimiste.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityWood;
import fr.paladium.palamod.modules.alchimiste.proxies.ClientProxy;
import java.util.Random;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockWood extends BlockLog {
  private String name;
  
  public BlockWood() {
    this.name = "alchimiste.wood";
    func_149711_c(2.0F);
    func_149752_b(3.0F);
    func_149663_c(this.name);
    this.field_149768_d = "palamod:log/dead_wood";
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityWood();
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.woodAlchimist;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  protected IIcon func_150163_b(int p_150163_1_) {
    return this.field_150167_a[0];
  }
  
  @SideOnly(Side.CLIENT)
  protected IIcon func_150161_d(int p_150161_1_) {
    return this.field_150166_b[0];
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {
    this.field_150167_a = new IIcon[1];
    this.field_150166_b = new IIcon[1];
    for (int i = 0; i < this.field_150167_a.length; i++) {
      this.field_150167_a[i] = reg.func_94245_a(func_149641_N());
      this.field_150166_b[i] = reg.func_94245_a(func_149641_N() + "_top");
    } 
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return Item.func_150898_a(BlocksRegister.DEAD_WOOD);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\blocks\BlockWood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */