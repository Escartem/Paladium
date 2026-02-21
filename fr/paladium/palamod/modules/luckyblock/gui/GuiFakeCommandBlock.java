package fr.paladium.palamod.modules.luckyblock.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.server.CommandBlockLogic;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class GuiFakeCommandBlock extends GuiScreen {
  private GuiTextField command;
  
  private final CommandBlockLogic logic;
  
  private GuiButton doneBtn;
  
  private GuiButton cancelBtn;
  
  public GuiFakeCommandBlock(CommandBlockLogic logic) {
    this.logic = logic;
  }
  
  public void func_73876_c() {
    this.command.func_146178_a();
  }
  
  public void func_73866_w_() {
    Keyboard.enableRepeatEvents(true);
    this.field_146292_n.clear();
    this.field_146292_n.add(this
        .doneBtn = new GuiButton(0, this.field_146294_l / 2 - 4 - 150, this.field_146295_m / 4 + 120 + 12, 150, 20, I18n.func_135052_a("gui.done", new Object[0])));
    this.field_146292_n.add(this
        .cancelBtn = new GuiButton(1, this.field_146294_l / 2 + 4, this.field_146295_m / 4 + 120 + 12, 150, 20, I18n.func_135052_a("gui.cancel", new Object[0])));
    this.command = new GuiTextField(this.field_146289_q, this.field_146294_l / 2 - 150, 50, 300, 20);
    this.command.func_146203_f(32767);
    this.command.func_146195_b(true);
    this.command.func_146180_a(this.logic.func_145753_i());
    this.command.func_146184_c(false);
    this.doneBtn.field_146124_l = (this.command.func_146179_b().trim().length() > 0);
  }
  
  public void func_146281_b() {
    Keyboard.enableRepeatEvents(false);
  }
  
  protected void func_146284_a(GuiButton p_146284_1_) {
    if (p_146284_1_.field_146124_l)
      if (p_146284_1_.field_146127_k == 1) {
        this.field_146297_k.func_147108_a((GuiScreen)null);
      } else if (p_146284_1_.field_146127_k == 0) {
        this.field_146297_k.func_147108_a((GuiScreen)null);
      }  
  }
  
  protected void func_73869_a(char c, int id) {
    this.doneBtn.field_146124_l = (this.command.func_146179_b().trim().length() > 0);
    if (id != 28 && id != 156) {
      if (id == 1)
        func_146284_a(this.cancelBtn); 
    } else {
      func_146284_a(this.doneBtn);
    } 
  }
  
  protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
    super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
    this.command.func_146192_a(p_73864_1_, p_73864_2_, p_73864_3_);
  }
  
  public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
    func_146276_q_();
    func_73732_a(this.field_146289_q, I18n.func_135052_a("advMode.setCommand", new Object[0]), this.field_146294_l / 2, 20, 16777215);
    func_73731_b(this.field_146289_q, I18n.func_135052_a("advMode.command", new Object[0]), this.field_146294_l / 2 - 150, 37, 10526880);
    this.command.func_146194_f();
    byte b0 = 75;
    byte b1 = 0;
    FontRenderer fontrenderer = this.field_146289_q;
    String s = "Tu ne peux pas changer cette commande. :)";
    int i1 = this.field_146294_l / 2 - 150;
    func_73731_b(fontrenderer, s, i1, b0 + b1 * this.field_146289_q.field_78288_b, 10526880);
    super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\GuiFakeCommandBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */