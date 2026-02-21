package fr.paladium.palakeybind.client.ui;

import fr.paladium.palakeybind.common.key.impl.IKeyBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class GuiNewControls extends GuiScreen {
  private static final GameSettings.Options[] defaultOptions = new GameSettings.Options[] { GameSettings.Options.INVERT_MOUSE, GameSettings.Options.SENSITIVITY };
  
  private final GuiScreen parentScreen;
  
  private final GameSettings options;
  
  public long lastKey;
  
  public KeyBinding buttonId = null;
  
  private boolean editing;
  
  private GuiButton resetAllButton;
  
  private GuiNewKeyBindingList keyBindingList;
  
  private String title = "Controls";
  
  public List<String> conflicts;
  
  public GuiNewControls(GuiScreen parent, GameSettings options) {
    this.parentScreen = parent;
    this.options = options;
    this.conflicts = new ArrayList<>();
  }
  
  public void func_73866_w_() {
    this.field_146292_n.clear();
    this.field_146292_n.add(new GuiButton(200, this.field_146294_l / 2 - 155, this.field_146295_m - 29, 150, 20, I18n.func_135052_a("gui.done", new Object[0])));
    this.field_146292_n.add(this.resetAllButton = new GuiButton(201, this.field_146294_l / 2 - 155 + 160, this.field_146295_m - 29, 150, 20, I18n.func_135052_a("controls.resetAll", new Object[0])));
    this.title = I18n.func_135052_a("controls.title", new Object[0]);
    this.keyBindingList = new GuiNewKeyBindingList(this, this.field_146297_k);
    int i = 0;
    GameSettings.Options[] aoptions = defaultOptions;
    int j = aoptions.length;
    for (int k = 0; k < j; k++) {
      GameSettings.Options opt = aoptions[k];
      if (opt.func_74380_a()) {
        this.field_146292_n.add(new GuiOptionSlider(opt.func_74381_c(), this.field_146294_l / 2 - 155 + i % 2 * 160, 18 + 24 * (i >> 1), opt));
      } else {
        this.field_146292_n.add(new GuiOptionButton(opt.func_74381_c(), this.field_146294_l / 2 - 155 + i % 2 * 160, 18 + 24 * (i >> 1), opt, this.options.func_74297_c(opt)));
      } 
      i++;
    } 
  }
  
  protected void func_146284_a(GuiButton button) {
    if (button.field_146127_k == 200) {
      this.field_146297_k.func_147108_a(this.parentScreen);
    } else if (button.field_146127_k == 201) {
      KeyBinding[] akeybinding = this.field_146297_k.field_71474_y.field_74324_K;
      int i = akeybinding.length;
      for (int j = 0; j < i; j++) {
        KeyBinding keybinding = akeybinding[j];
        keybinding.func_151462_b(keybinding.func_151469_h());
        if (keybinding instanceof IKeyBinding) {
          IKeyBinding k = (IKeyBinding)keybinding;
          k.setAlt(k.isDefaultAlt());
          k.setCtrl(k.isDefaultCtrl());
          k.setShift(k.isDefaultShift());
          k.setRightAlt(k.isDefaultRightAlt());
          k.setRightCtrl(k.isDefaultRightCtrl());
          k.setRightShift(k.isDefaultRightShift());
        } 
      } 
      KeyBinding.func_74508_b();
    } else if (button.field_146127_k == 202) {
      func_73866_w_();
    } else if (button.field_146127_k < 100 && button instanceof GuiOptionButton) {
      this.options.func_74306_a(((GuiOptionButton)button).func_146136_c(), 1);
      button.field_146126_j = this.options.func_74297_c(GameSettings.Options.func_74379_a(button.field_146127_k));
    } 
  }
  
  protected void func_73864_a(int mouseX, int mouseY, int mouseButton) {
    if (this.buttonId != null) {
      this.options.func_151440_a(this.buttonId, -100 + mouseButton);
      this.buttonId = null;
      KeyBinding.func_74508_b();
    } else if (mouseButton != 0 || !this.keyBindingList.func_148179_a(mouseX, mouseY, mouseButton)) {
      super.func_73864_a(mouseX, mouseY, mouseButton);
    } 
  }
  
  protected void func_146286_b(int mouseX, int mouseY, int which) {
    if (which != 0 || !this.keyBindingList.func_148181_b(mouseX, mouseY, which))
      super.func_146286_b(mouseX, mouseY, which); 
  }
  
  protected void func_73869_a(char c, int key) {
    if (this.buttonId != null) {
      this.editing = true;
      if (key == 1) {
        this.options.func_151440_a(this.buttonId, 0);
      } else {
        this.options.func_151440_a(this.buttonId, key);
      } 
      if (key != 29 && key != 157 && key != 42 && key != 54 && key != 56 && key != 184) {
        this.editing = false;
        this.buttonId = null;
        this.lastKey = Minecraft.func_71386_F();
        KeyBinding.func_74508_b();
      } 
    } else {
      super.func_73869_a(c, key);
    } 
  }
  
  public void func_73876_c() {
    if (this.buttonId != null && this.editing) {
      int keyCode = this.buttonId.func_151463_i();
      if ((keyCode == 29 && !Keyboard.isKeyDown(29)) || (keyCode == 157 && !Keyboard.isKeyDown(157))) {
        this.editing = false;
        this.buttonId = null;
        this.lastKey = Minecraft.func_71386_F();
        KeyBinding.func_74508_b();
      } else if ((keyCode == 42 && !Keyboard.isKeyDown(42)) || (keyCode == 54 && !Keyboard.isKeyDown(54))) {
        this.editing = false;
        this.buttonId = null;
        this.lastKey = Minecraft.func_71386_F();
        KeyBinding.func_74508_b();
      } else if ((keyCode == 56 && !Keyboard.isKeyDown(56)) || (keyCode == 184 && !Keyboard.isKeyDown(184))) {
        this.editing = false;
        this.buttonId = null;
        this.lastKey = Minecraft.func_71386_F();
        KeyBinding.func_74508_b();
      } 
    } 
    super.func_73876_c();
  }
  
  public void func_73863_a(int mouseX, int mouseY, float ticks) {
    this.conflicts.clear();
    func_146276_q_();
    this.keyBindingList.func_148128_a(mouseX, mouseY, ticks);
    func_73732_a(this.field_146289_q, this.title, this.field_146294_l / 2, 8, 16777215);
    boolean flag = true;
    KeyBinding[] akeybinding = this.options.field_74324_K;
    int k = akeybinding.length;
    for (int i = 0; i < k; i++) {
      KeyBinding keybinding = akeybinding[i];
      if (keybinding.func_151463_i() != keybinding.func_151469_h()) {
        flag = false;
        break;
      } 
    } 
    this.resetAllButton.field_146124_l = !flag;
    super.func_73863_a(mouseX, mouseY, ticks);
    if (this.conflicts != null && !this.conflicts.isEmpty())
      if (this.conflicts.size() == 1) {
        drawHoveringText(Arrays.asList(new String[] { I18n.func_135052_a("keybind.conflict.single", new Object[] { this.conflicts.get(0) }) }), mouseX, mouseY, this.field_146289_q);
      } else {
        List<String> formattedConflicts = new ArrayList<>();
        formattedConflicts.add(I18n.func_135052_a("keybind.conflict", new Object[0]));
        for (String conflict : this.conflicts)
          formattedConflicts.add(" » §8[§c" + conflict + "§8]"); 
        drawHoveringText(formattedConflicts, mouseX, mouseY, this.field_146289_q);
      }  
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palakeybind\clien\\ui\GuiNewControls.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */