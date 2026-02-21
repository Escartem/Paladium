package fr.paladium.palamod.modules.hunter.gui.containers;

import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@Deprecated
public class GuiBackPack extends GuiContainer {
  public static final ResourceLocation texture = new ResourceLocation("textures/gui/container/generic_54.png");
  
  public static final ResourceLocation texture_big = new ResourceLocation("textures/gui/container/generic_54_big.png");
  
  protected InventoryBackPack inv;
  
  protected InventoryPlayer playerInv;
  
  public int rows;
  
  public GuiBackPack(InventoryPlayer playerInv, InventoryBackPack inv) {
    super(new ContainerBackPack(playerInv, inv));
    this.playerInv = playerInv;
    this.inv = inv;
    this.field_146291_p = false;
    this.rows = inv.func_70302_i_() / 9;
    this.field_147000_g = 114 + this.rows * 18;
  }
  
  protected void func_146979_b(int x, int y) {
    this.field_146289_q.func_78276_b(I18n.func_135052_a(this.inv.func_145825_b(), new Object[0]), 8, 6, 4210752);
    this.field_146289_q.func_78276_b(
        this.playerInv.func_145818_k_() ? this.playerInv.func_145825_b() : 
        I18n.func_135052_a(this.playerInv.func_145825_b(), new Object[0]), 8, this.field_147000_g - 96 + 2, 4210752);
  }
  
  protected void func_146976_a(float prt, int x, int y) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    if (this.rows > 6) {
      double w = 176.0D;
      double k = (this.field_146294_l / 2.0F - 88.0F);
      int l = (this.field_146295_m - this.field_147000_g) / 2;
      GuiUtils.drawImageTransparent(k, l, texture_big, 176.0D, this.field_147000_g);
    } else {
      this.field_146297_k.func_110434_K().func_110577_a(texture);
      int k = (this.field_146294_l - this.field_146999_f) / 2;
      int l = (this.field_146295_m - this.field_147000_g) / 2;
      func_73729_b(k, l, 0, 0, this.field_146999_f, this.rows * 18 + 17);
      func_73729_b(k, l + this.rows * 18 + 17, 0, 126, this.field_146999_f, 96);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\gui\containers\GuiBackPack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */