package fr.paladium.palajobs.core.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.util.IIcon;

public class ItemCustomFishingRod extends ItemFishingRod {
  @SideOnly(Side.CLIENT)
  public IIcon uncastIcon;
  
  @SideOnly(Side.CLIENT)
  public IIcon castIcon;
  
  public ItemCustomFishingRod(String unlocalizedName) {
    func_77656_e(128);
    func_77655_b(unlocalizedName);
    func_111206_d("palajobs:" + unlocalizedName);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_94581_a(IIconRegister iconRegister) {
    this.uncastIcon = iconRegister.func_94245_a(func_111208_A() + "_uncast");
    this.castIcon = iconRegister.func_94245_a(func_111208_A() + "_cast");
  }
  
  public IIcon func_77617_a(int damage) {
    return ((Minecraft.func_71410_x()).field_71439_g.field_71104_cf != null) ? this.castIcon : this.uncastIcon;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\item\ItemCustomFishingRod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */