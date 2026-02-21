package fr.paladium.palakeybind.client.ui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palakeybind.common.key.KeyTranslate;
import fr.paladium.palakeybind.common.key.impl.IKeyBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;

public class GuiNewKeyBindingList extends GuiListExtended {
  private final Minecraft mc;
  
  private final GuiNewControls gui;
  
  private final GuiListExtended.IGuiListEntry[] list;
  
  private int stringWidth = 0;
  
  public GuiNewKeyBindingList(GuiNewControls gui, Minecraft mc) {
    super(mc, gui.field_146294_l, gui.field_146295_m, 63, gui.field_146295_m - 32, 20);
    this.mc = mc;
    this.gui = gui;
    List<KeyBinding> sortedKeyBindings = new ArrayList<>();
    Set<String> categories = new HashSet<>();
    Set<String> subCategories = new HashSet<>();
    for (KeyBinding keyBinding : mc.field_71474_y.field_74324_K) {
      if (keyBinding instanceof IKeyBinding) {
        IKeyBinding iKeyBinding = (IKeyBinding)keyBinding;
        if (!iKeyBinding.getCanUse().test(keyBinding) && mc.field_71439_g != null)
          continue; 
      } 
      sortedKeyBindings.add(keyBinding);
      categories.add(keyBinding.func_151466_e());
      if (keyBinding instanceof IKeyBinding)
        subCategories.add(((IKeyBinding)keyBinding).getSubCategory()); 
      continue;
    } 
    KeyBinding[] keyBindings = sortedKeyBindings.<KeyBinding>toArray(new KeyBinding[0]);
    Arrays.sort((Object[])keyBindings);
    KeyBinding[] keys = keyBindings;
    int count = keyBindings.length;
    String currentCategory = null;
    String currentSubCategory = null;
    List<GuiListExtended.IGuiListEntry> l = new LinkedList<>();
    for (int k = 0; k < count; k++) {
      KeyBinding keybinding = keys[k];
      String category = keybinding.func_151466_e();
      String subCategory = ((IKeyBinding)keybinding).getSubCategory();
      if (!category.equals(currentCategory)) {
        currentCategory = category;
        l.add(new CategoryEntry(category));
      } 
      if (!subCategory.equals(currentSubCategory)) {
        currentSubCategory = subCategory;
        l.add(new SubCategoryEntry(subCategory));
      } 
      int strWidth = mc.field_71466_p.func_78256_a(I18n.func_135052_a(keybinding.func_151464_g(), new Object[0]));
      if (strWidth > this.stringWidth)
        this.stringWidth = strWidth; 
      l.add(new KeyEntry(gui, keybinding));
    } 
    this.list = l.<GuiListExtended.IGuiListEntry>toArray(new GuiListExtended.IGuiListEntry[0]);
  }
  
  protected int func_148127_b() {
    return this.list.length;
  }
  
  public GuiListExtended.IGuiListEntry func_148180_b(int index) {
    return this.list[index];
  }
  
  protected int func_148137_d() {
    return super.func_148137_d() + 15;
  }
  
  public int func_148139_c() {
    return super.func_148139_c() + 32;
  }
  
  @SideOnly(Side.CLIENT)
  public class CategoryEntry implements GuiListExtended.IGuiListEntry {
    private final String title;
    
    private final int stringWidth;
    
    public CategoryEntry(String title) {
      this.title = I18n.func_135052_a(title, new Object[0]);
      this.stringWidth = GuiNewKeyBindingList.this.mc.field_71466_p.func_78256_a(this.title);
    }
    
    public void func_148279_a(int p_148279_1_, int p_148279_2_, int p_148279_3_, int p_148279_4_, int p_148279_5_, Tessellator p_148279_6_, int p_148279_7_, int p_148279_8_, boolean p_148279_9_) {
      GuiNewKeyBindingList.this.mc.field_71466_p.func_78276_b(this.title, GuiNewKeyBindingList.this.mc.field_71462_r.field_146294_l / 2 - this.stringWidth / 2, p_148279_3_ + p_148279_5_ - GuiNewKeyBindingList.this.mc.field_71466_p.field_78288_b - 1, 16777215);
    }
    
    public boolean func_148278_a(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
      return false;
    }
    
    public void func_148277_b(int p_148277_1_, int p_148277_2_, int p_148277_3_, int p_148277_4_, int p_148277_5_, int p_148277_6_) {}
  }
  
  @SideOnly(Side.CLIENT)
  public class SubCategoryEntry implements GuiListExtended.IGuiListEntry {
    private final String title;
    
    public SubCategoryEntry(String title) {
      this.title = I18n.func_135052_a(title, new Object[0]);
    }
    
    public void func_148279_a(int p_148279_1_, int p_148279_2_, int p_148279_3_, int p_148279_4_, int p_148279_5_, Tessellator p_148279_6_, int p_148279_7_, int p_148279_8_, boolean p_148279_9_) {
      GuiNewKeyBindingList.this.mc.field_71466_p.func_78276_b("§8" + this.title, p_148279_2_ + 90 - GuiNewKeyBindingList.this.stringWidth, p_148279_3_ + p_148279_5_ - GuiNewKeyBindingList.this.mc.field_71466_p.field_78288_b - 1, 16777215);
    }
    
    public boolean func_148278_a(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
      return false;
    }
    
    public void func_148277_b(int p_148277_1_, int p_148277_2_, int p_148277_3_, int p_148277_4_, int p_148277_5_, int p_148277_6_) {}
  }
  
  @SideOnly(Side.CLIENT)
  public class KeyEntry implements GuiListExtended.IGuiListEntry {
    private final GuiNewControls gui;
    
    private final KeyBinding keyBinding;
    
    private final String title;
    
    private final GuiButtonControl btnChangeKeyBinding;
    
    private final GuiButton btnReset;
    
    private KeyEntry(GuiNewControls gui, KeyBinding keyBinding) {
      this.gui = gui;
      this.keyBinding = keyBinding;
      this.title = I18n.func_135052_a(keyBinding.func_151464_g(), new Object[0]);
      this.btnChangeKeyBinding = new GuiButtonControl(0, 0, 0, 75, 18, I18n.func_135052_a(keyBinding.func_151464_g(), new Object[0]));
      this.btnReset = new GuiButton(0, 0, 0, 50, 18, I18n.func_135052_a("controls.reset", new Object[0]));
    }
    
    public void func_148279_a(int p_148279_1_, int p_148279_2_, int p_148279_3_, int p_148279_4_, int p_148279_5_, Tessellator p_148279_6_, int p_148279_7_, int p_148279_8_, boolean p_148279_9_) {
      boolean flag1 = (GuiNewKeyBindingList.this.gui.buttonId == this.keyBinding);
      GuiNewKeyBindingList.this.mc.field_71466_p.func_78276_b(this.title, p_148279_2_ + 90 - GuiNewKeyBindingList.this.stringWidth, p_148279_3_ + p_148279_5_ / 2 - GuiNewKeyBindingList.this.mc.field_71466_p.field_78288_b / 2, 16777215);
      this.btnReset.field_146128_h = p_148279_2_ + 190;
      this.btnReset.field_146129_i = p_148279_3_;
      if (this.keyBinding instanceof IKeyBinding) {
        this.btnReset.field_146124_l = !((IKeyBinding)this.keyBinding).isDefault();
      } else {
        this.btnReset.field_146124_l = (this.keyBinding.func_151463_i() == this.keyBinding.func_151469_h());
      } 
      this.btnReset.func_146112_a(GuiNewKeyBindingList.this.mc, p_148279_7_, p_148279_8_);
      this.btnChangeKeyBinding.field_146128_h = p_148279_2_ + 105;
      this.btnChangeKeyBinding.field_146129_i = p_148279_3_;
      this.btnChangeKeyBinding.field_146126_j = KeyTranslate.getKeyDisplayString(this.keyBinding);
      List<String> conflicts = new ArrayList<>();
      if (this.keyBinding.func_151463_i() != 0) {
        KeyBinding[] akeybinding = GuiNewKeyBindingList.this.mc.field_71474_y.field_74324_K;
        int l1 = akeybinding.length;
        for (int i2 = 0; i2 < l1; i2++) {
          KeyBinding keybinding = akeybinding[i2];
          if (keybinding != this.keyBinding && ((IKeyBinding)keybinding).isSame(this.keyBinding) && ((IKeyBinding)keybinding).getCanUse().test(keybinding))
            conflicts.add(I18n.func_135052_a(keybinding.func_151464_g(), new Object[0])); 
        } 
      } 
      if (flag1) {
        this.btnChangeKeyBinding.field_146126_j = EnumChatFormatting.WHITE + "> " + EnumChatFormatting.YELLOW + this.btnChangeKeyBinding.field_146126_j + EnumChatFormatting.WHITE + " <";
      } else if (!conflicts.isEmpty()) {
        this.btnChangeKeyBinding.field_146126_j = EnumChatFormatting.RED + this.btnChangeKeyBinding.field_146126_j;
      } 
      this.btnChangeKeyBinding.func_146112_a(GuiNewKeyBindingList.this.mc, p_148279_7_, p_148279_8_);
      if (this.btnChangeKeyBinding.func_146115_a() && !conflicts.isEmpty())
        this.gui.conflicts.addAll(conflicts); 
    }
    
    public boolean func_148278_a(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
      if (this.btnChangeKeyBinding.func_146116_c(GuiNewKeyBindingList.this.mc, p_148278_2_, p_148278_3_)) {
        GuiNewKeyBindingList.this.gui.buttonId = this.keyBinding;
        return true;
      } 
      if (!this.btnReset.func_146116_c(GuiNewKeyBindingList.this.mc, p_148278_2_, p_148278_3_))
        return false; 
      GuiNewKeyBindingList.this.mc.field_71474_y.func_151440_a(this.keyBinding, this.keyBinding.func_151469_h());
      if (this.keyBinding instanceof IKeyBinding) {
        IKeyBinding k = (IKeyBinding)this.keyBinding;
        k.setAlt(k.isDefaultAlt());
        k.setCtrl(k.isDefaultCtrl());
        k.setShift(k.isDefaultShift());
        k.setRightAlt(k.isDefaultRightAlt());
        k.setRightCtrl(k.isDefaultRightCtrl());
        k.setRightShift(k.isDefaultRightShift());
      } 
      GuiNewKeyBindingList.this.mc.field_71474_y.func_74303_b();
      KeyBinding.func_74508_b();
      return true;
    }
    
    public void func_148277_b(int p_148277_1_, int p_148277_2_, int p_148277_3_, int p_148277_4_, int p_148277_5_, int p_148277_6_) {
      this.btnChangeKeyBinding.func_146118_a(p_148277_2_, p_148277_3_);
      this.btnReset.func_146118_a(p_148277_2_, p_148277_3_);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palakeybind\clien\\ui\GuiNewKeyBindingList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */