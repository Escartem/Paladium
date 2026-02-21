package fr.paladium.palakeybind.client.ui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;

@SideOnly(Side.CLIENT)
public class CategoryEntry implements GuiListExtended.IGuiListEntry {
  private final String title;
  
  private final int stringWidth;
  
  public CategoryEntry(String title) {
    this.title = I18n.func_135052_a(title, new Object[0]);
    this.stringWidth = (GuiNewKeyBindingList.access$100(GuiNewKeyBindingList.this)).field_71466_p.func_78256_a(this.title);
  }
  
  public void func_148279_a(int p_148279_1_, int p_148279_2_, int p_148279_3_, int p_148279_4_, int p_148279_5_, Tessellator p_148279_6_, int p_148279_7_, int p_148279_8_, boolean p_148279_9_) {
    (GuiNewKeyBindingList.access$100(GuiNewKeyBindingList.this)).field_71466_p.func_78276_b(this.title, (GuiNewKeyBindingList.access$100(GuiNewKeyBindingList.this)).field_71462_r.field_146294_l / 2 - this.stringWidth / 2, p_148279_3_ + p_148279_5_ - (GuiNewKeyBindingList.access$100(GuiNewKeyBindingList.this)).field_71466_p.field_78288_b - 1, 16777215);
  }
  
  public boolean func_148278_a(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
    return false;
  }
  
  public void func_148277_b(int p_148277_1_, int p_148277_2_, int p_148277_3_, int p_148277_4_, int p_148277_5_, int p_148277_6_) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palakeybind\clien\\ui\GuiNewKeyBindingList$CategoryEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */