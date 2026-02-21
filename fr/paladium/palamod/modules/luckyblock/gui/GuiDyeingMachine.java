package fr.paladium.palamod.modules.luckyblock.gui;

import fr.paladium.palamod.modules.luckyblock.gui.containers.ContainerDyeingMachine;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityDyeingMachine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiDyeingMachine extends GuiContainer {
  private static final ResourceLocation background = new ResourceLocation("palamod:textures/gui/dyeingmachine.png");
  
  private final TileEntityDyeingMachine tile;
  
  public GuiDyeingMachine(InventoryPlayer inv, TileEntityDyeingMachine te) {
    super((Container)new ContainerDyeingMachine(inv, te));
    this.tile = te;
  }
  
  protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    this.field_146297_k.func_110434_K().func_110577_a(background);
    int k = (this.field_146294_l - this.field_146999_f) / 2;
    int l = (this.field_146295_m - this.field_147000_g) / 2;
    func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
  }
  
  protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
    String s = this.tile.func_145825_b();
    this.field_146289_q.func_78276_b(s, this.field_146999_f / 2 - this.field_146289_q.func_78256_a(s) / 2, 6, 4210752);
    this.field_146289_q.func_78276_b(I18n.func_135052_a("container.inventory", new Object[0]), 8, this.field_147000_g - 120 + 2, 4210752);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\GuiDyeingMachine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */