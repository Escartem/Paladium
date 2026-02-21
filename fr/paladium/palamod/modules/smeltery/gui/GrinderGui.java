package fr.paladium.palamod.modules.smeltery.gui;

import fr.paladium.palamod.modules.smeltery.inventory.GrinderContainer;
import fr.paladium.palamod.modules.smeltery.logics.GrinderLogic;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@Deprecated
public class GrinderGui extends GuiContainer {
  private GrinderLogic tile;
  
  public static final ResourceLocation background = new ResourceLocation("palamod:textures/gui/grinder.png");
  
  public GrinderGui(InventoryPlayer inventory, GrinderLogic tile) {
    super((Container)new GrinderContainer(inventory, tile));
    this.tile = tile;
    this.field_147000_g = 172;
    this.field_146999_f = 210;
  }
  
  protected void func_146976_a(float f, int mouseX, int mouseY) {
    GL11.glColor4f(1.0F, 1.5F, 1.0F, 1.0F);
    if (this.tile == null) {
      this.field_146297_k.func_147108_a(null);
      return;
    } 
    this.field_146297_k.func_110434_K().func_110577_a(background);
    int k = (this.field_146294_l - this.field_146999_f) / 2;
    int l = (this.field_146295_m - this.field_147000_g) / 2;
    func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
    func_73729_b(k + 146, l + 12, 51, 172, 53, 54 - 
        (int)this.tile.getScaledPaladium(54));
    GL11.glPushMatrix();
    GL11.glDisable(2896);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    func_73729_b(k + 147, l + 13, 0, 172, 51, 50);
    GL11.glPopMatrix();
    func_73729_b(k + 43, l + 27, 210, 0, this.tile.getScaledTool(31), 17);
    func_73729_b(k + 43, l + 67, 210, 0, this.tile.getScaledUpgrade(31), 17);
    func_73729_b(k + 124, l + 29, 210, 18, this.tile.getScaledProgress(19), 11);
    this.field_146289_q.func_78276_b(StatCollector.func_74838_a("crafters.Grinder"), k + 7, l + 6, 4210752);
    this.field_146289_q.func_78276_b(this.tile.getPaladium() + "/" + this.tile.getMaxPaladium(), k + 163, l + 67, 4210752);
  }
  
  protected void func_146979_b(int x, int y) {
    int k = (this.field_146294_l - this.field_146999_f) / 2;
    int l = (this.field_146295_m - this.field_147000_g) / 2;
    if (x >= k + 147 && y >= l + 12 && x <= k + 198 && y <= l + 63) {
      List<String> list = new ArrayList<>();
      list.add(EnumChatFormatting.GOLD + ((this.tile.getPaladium() == 1) ? "1 Paladium" : (this.tile
          .getPaladium() + " Paladiums")));
      list.add("Max: 100");
      drawHoveringText(list, x - k, y - l, this.field_146289_q);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\gui\GrinderGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */