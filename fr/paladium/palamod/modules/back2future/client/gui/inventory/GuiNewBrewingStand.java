package fr.paladium.palamod.modules.back2future.client.gui.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.inventory.ContainerNewBrewingStand;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityNewBrewingStand;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiNewBrewingStand extends GuiContainer {
  private static final ResourceLocation TEXTURE = Utils.getResource("palamod:textures/gui/container/brewing_stand.png");
  
  private TileEntityNewBrewingStand tile;
  
  public GuiNewBrewingStand(InventoryPlayer playerInvt, TileEntityNewBrewingStand tile) {
    super((Container)new ContainerNewBrewingStand(playerInvt, tile));
    this.tile = tile;
  }
  
  protected void func_146979_b(int x, int y) {
    String s = this.tile.func_145818_k_() ? this.tile.func_145825_b() : I18n.func_135052_a(this.tile.func_145825_b(), new Object[0]);
    this.field_146289_q.func_78276_b(s, this.field_146999_f / 2 - this.field_146289_q.func_78256_a(s) / 2, 6, 4210752);
    this.field_146289_q.func_78276_b(I18n.func_135052_a("container.inventory", new Object[0]), 8, this.field_147000_g - 96 + 2, 4210752);
  }
  
  protected void func_146976_a(float f0, int x, int y) {
    if (this.tile == null) {
      this.field_146297_k.func_147108_a(null);
      return;
    } 
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    this.field_146297_k.func_110434_K().func_110577_a(TEXTURE);
    int k = (this.field_146294_l - this.field_146999_f) / 2;
    int l = (this.field_146295_m - this.field_147000_g) / 2;
    func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
    int i1 = this.tile.func_145935_i();
    if (i1 > 0) {
      int j1 = (int)(28.0F * (1.0F - i1 / 400.0F));
      if (j1 > 0)
        func_73729_b(k + 97, l + 16, 176, 0, 9, j1); 
      int k1 = i1 / 2 % 7;
      switch (k1) {
        case 0:
          j1 = 29;
          break;
        case 1:
          j1 = 24;
          break;
        case 2:
          j1 = 20;
          break;
        case 3:
          j1 = 16;
          break;
        case 4:
          j1 = 11;
          break;
        case 5:
          j1 = 6;
          break;
        case 6:
          j1 = 0;
          break;
      } 
      if (j1 > 0)
        func_73729_b(k + 65, l + 14 + 29 - j1, 185, 29 - j1, 12, j1); 
    } 
    int fuel = this.tile.getFuel();
    if (fuel > 0) {
      int size = (int)(18.0F * fuel / this.tile.getCurrentFuel());
      func_73729_b(k + 60, l + 44, 176, 29, size, 4);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\gui\inventory\GuiNewBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */