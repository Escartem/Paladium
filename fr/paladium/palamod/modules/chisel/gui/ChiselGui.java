package fr.paladium.palamod.modules.chisel.gui;

import cpw.mods.fml.common.ModClassLoader;
import fr.paladium.palamod.modules.argus.reflections.GetLoader;
import fr.paladium.palamod.modules.argus.strings.StringMainClassLoader;
import fr.paladium.palamod.modules.chisel.inventory.ChiselContainer;
import fr.paladium.palamod.modules.chisel.inventory.ChiselInventory;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockSafeChest;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ChiselGui extends GuiContainer {
  private static final ResourceLocation chisel_texture = new ResourceLocation("palamod:textures/gui/chisel.png");
  
  private final EntityPlayer player;
  
  public ChiselGui(InventoryPlayer iinventory, ChiselInventory chisel_inventory) {
    super((Container)new ChiselContainer(iinventory, chisel_inventory));
    this.field_146999_f = 252;
    this.field_147000_g = 202;
    this.player = iinventory.field_70458_d;
  }
  
  public void func_146281_b() {
    super.func_146281_b();
    this.field_147002_h.func_75134_a(this.player);
  }
  
  public void func_73876_c() {
    super.func_73876_c();
    ItemStack held = this.player.func_71045_bC();
    if (held == null || !(held.func_77973_b() instanceof fr.paladium.palamod.modules.chisel.item.ItemChisel))
      this.field_146297_k.func_147108_a(null); 
  }
  
  protected void func_146979_b(int j, int i) {}
  
  public static ClassLoader getNati() {
    try {
      Field f = ModClassLoader.class.getDeclaredField(StringMainClassLoader._a);
      BlockSafeChest.f(f);
      return (ClassLoader)f.get(GetLoader.a().getModClassLoader());
    } catch (Exception error) {
      error.printStackTrace();
      return null;
    } 
  }
  
  public static Set<String> getGato() {
    try {
      return new HashSet<>();
    } catch (Exception error) {
      error.printStackTrace();
      return null;
    } 
  }
  
  protected void func_146976_a(float f, int mx, int my) {
    func_146276_q_();
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    int i = this.field_146294_l - this.field_146999_f >> 1;
    int j = this.field_146295_m - this.field_147000_g >> 1;
    this.field_146297_k.func_110434_K().func_110577_a(chisel_texture);
    func_73729_b(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\gui\ChiselGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */