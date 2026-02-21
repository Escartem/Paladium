package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.uis.containers;

import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.containers.ContainerCrabCobbleBreaker;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityCrabCobbleBreaker;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiCrabCobbleBreaker extends GuiContainer {
  private final ResourceLocation background = new ResourceLocation("palamod", "textures/gui/cobbleBreaker.png");
  
  private final TileEntityCrabCobbleBreaker TileEntityCrabCobbleBreaker;
  
  public GuiCrabCobbleBreaker(TileEntityCrabCobbleBreaker tile, InventoryPlayer inventory, EntityPlayer player) {
    super((Container)new ContainerCrabCobbleBreaker(tile, inventory, player));
    this.field_146999_f = 189;
    this.field_147000_g = 178;
    this.field_146291_p = false;
    this.TileEntityCrabCobbleBreaker = tile;
  }
  
  protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
    GL11.glColor4f(1.0F, 1.5F, 1.0F, 1.0F);
    this.field_146297_k.func_110434_K().func_110577_a(this.background);
    int k = (this.field_146294_l - this.field_146999_f) / 2;
    int l = (this.field_146295_m - this.field_147000_g) / 2;
    func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
    double d = (15 * this.TileEntityCrabCobbleBreaker.getWork() / this.TileEntityCrabCobbleBreaker.getUpgrade().getWork());
    int i = (int)d;
    func_73729_b(k + 94, l + 30, 1, 241, 10, i);
    GuiUtils.renderItemStackIntoGUI(new ItemStack(ItemsRegister.PARTICLE_GREEN_PALADIUM), (k + 162), (l + 2));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\clien\\uis\containers\GuiCrabCobbleBreaker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */