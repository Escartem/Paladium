package fr.paladium.palamod.modules.luckyblock.gui.may;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.ariane.internal.client.gui.UIArianeDialogOptionNode;
import fr.paladium.ariane.internal.common.network.CSPacketArianeCloseDialog;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import fr.paladium.lib.apollon.nodes.label.TextNodeLabel;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.odin.lib.modules.packet.OdinPacketModule;
import java.util.List;
import net.minecraft.util.ResourceLocation;

public class UICustomDialog extends UI {
  private static final ResourceLocation DIALOG_BACKGROUND = new ResourceLocation("palamod", "textures/gui/LuckyBlock/may/dialog_bg.png");
  
  private final CustomDialog dialog;
  
  private int tick;
  
  public UICustomDialog(CustomDialog dialog) {
    super(null, "palamod:ui_oldstory");
    this.dialog = dialog;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    if (this.dialog.hasOptions()) {
      float y = height(89.5F - 3.74F * this.dialog.getAnswerOptions().size() - 1.5F);
      int i = 1;
      for (String option : this.dialog.getAnswerOptions()) {
        double optionWidth = Math.max(width(3.4895F), GuiUtils.getStringWidth(this.field_146297_k, option, Fonts.PIXEL_NES.getFont(), 20) + width(1.0F));
        int retValue = i;
        addNode((new UIArianeDialogOptionNode(width(20.0F), y, optionWidth, height(3.24F), option, new Color(239, 57, 38))).setCallback(node -> {
                OdinPacketModule.getInstance().getNetwork().sendToServer((IMessage)new CSPacketArianeCloseDialog(retValue));
                this.field_146297_k.field_71439_g.func_85030_a("random.click", 0.5F, 3.0F);
                if (this.dialog.isCloseGui())
                  this.field_146297_k.func_147108_a(null); 
              }));
        y += height(3.24F) + width(0.5F);
        i++;
      } 
    } else {
      addNode((ANode)(new TextNodeLabel(width(62.0F), height(85.5F), "appuie sur une touche".toUpperCase(), Fonts.PIXEL_NES.getFont(), 20, Color.WHITE)).setTextAlign(TextAlign.RIGHT));
      addNode((ANode)(new TexturedNodeButton(width(63.0F), height(84.75F), width(1.5F), width(1.5F))).setTexture("ariane:textures/gui/dialog/skip"));
    } 
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    GuiUtils.drawImageTransparent(width(17.421875F), height(58.74F), DIALOG_BACKGROUND, width(65.15625F), height(30.83F), false);
    if (this.dialog.hasExpression())
      GuiUtils.drawImageTransparent(width(this.dialog.getExpressionX().floatValue()), height(this.dialog.getExpressionY().floatValue()), new ResourceLocation("palamod", this.dialog.getExpressionTexture()), width(this.dialog.getExpressionSizeX().floatValue()), height(this.dialog.getExpressionSizeY().floatValue()), false); 
    int fontSize = 180;
    float fontHeight = GuiUtils.getFontHeight(this.field_146297_k, Fonts.PIXEL_NES.getFont(), fontSize);
    float titleX = width(22.421875F);
    float titleY = height(58.74F) + fontHeight / 2.0F;
    double titleWidth = Math.max(width(3.4895F), GuiUtils.getStringWidth(this.field_146297_k, this.dialog.getTitle(), Fonts.PIXEL_NES.getFont(), fontSize) + width(1.0F));
    GuiUtils.drawRect(titleX, (titleY - height(3.24F) / 2.0F), titleX + titleWidth * 1.5D, (titleY + fontHeight + height(3.24F) / 2.0F), new Color(239, 57, 38));
    GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, this.dialog.getTitle(), titleX + titleWidth / 1.5D, titleY, Color.WHITE, Fonts.PIXEL_NES.getFont(), fontSize);
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    float txtWidth = 60.0F;
    if (this.dialog.hasExpression())
      txtWidth = 45.0F; 
    List<String> lines = GuiUtils.getSplittedString(this.field_146297_k, this.dialog.getText().substring(0, this.tick), Fonts.MONTSERRAT_BOLD.getFont(), 80, width(txtWidth));
    double x = width(20.0F);
    double y = height(67.0F);
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      GuiUtils.drawStringWithCustomFont(this.field_146297_k, line, x, y + (height(3.5F) * i), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 80);
    } 
  }
  
  public void fixedUpdate() {
    super.fixedUpdate();
    this.tick++;
    if (this.tick % 3 == 0)
      this.tick++; 
    if (this.tick > this.dialog.getText().length())
      this.tick = this.dialog.getText().length(); 
  }
  
  public void func_73869_a(char c, int keyCode) {
    if (this.tick >= this.dialog.getText().length() && !this.dialog.hasOptions()) {
      OdinPacketModule.getInstance().getNetwork().sendToServer((IMessage)new CSPacketArianeCloseDialog(-1));
      this.field_146297_k.field_71439_g.func_85030_a("random.click", 0.5F, 3.0F);
      if (this.dialog.isCloseGui())
        this.field_146297_k.func_147108_a(null); 
    } else {
      this.tick = this.dialog.getText().length();
    } 
  }
  
  protected void func_73864_a(int mouseX, int mouseY, int buttonID) {
    super.func_73864_a(mouseX, mouseY, buttonID);
    if (this.tick >= this.dialog.getText().length() && !this.dialog.hasOptions()) {
      OdinPacketModule.getInstance().getNetwork().sendToServer((IMessage)new CSPacketArianeCloseDialog(-1));
      this.field_146297_k.field_71439_g.func_85030_a("random.click", 0.5F, 3.0F);
      if (this.dialog.isCloseGui())
        this.field_146297_k.func_147108_a(null); 
    } else {
      this.tick = this.dialog.getText().length();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\may\UICustomDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */