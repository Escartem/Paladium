package fr.paladium.palamod.modules.paladium.client.gui;

import fr.paladium.palamod.modules.paladium.common.container.ContainerUnclaimFinderBlue;
import fr.paladium.palamod.modules.paladium.common.inventory.UnclaimFinderBlueInventory;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiUnclaimFinderBlue extends GuiContainer {
  final ResourceLocation resourceLocation = new ResourceLocation("palamod", "textures/gui/UnclaimFinderBlue.png");
  
  UnclaimFinderBlueInventory inventoryUnclaimFinderBlue;
  
  public GuiUnclaimFinderBlue(UnclaimFinderBlueInventory inventoryUnclaimFinderBlue, InventoryPlayer inventory, EntityPlayer player) {
    super((Container)new ContainerUnclaimFinderBlue(inventoryUnclaimFinderBlue, inventory, player));
    this.field_146999_f = 189;
    this.field_147000_g = 190;
    this.inventoryUnclaimFinderBlue = inventoryUnclaimFinderBlue;
  }
  
  protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDisable(2896);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    this.field_146297_k.field_71446_o.func_110577_a(this.resourceLocation);
    int x = (this.field_146294_l - this.field_146999_f) / 2;
    int y = (this.field_146295_m - this.field_147000_g) / 2;
    func_73729_b(x, y, 0, 0, 256, 256);
    double d = (72 * this.inventoryUnclaimFinderBlue.getItemStack().func_77978_p().func_74762_e("findium") / 153600);
    int i = (int)d;
    func_73729_b(x + 38, y + 18, 0, 247, i, 9);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\GuiUnclaimFinderBlue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */