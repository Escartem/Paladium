package fr.paladium.palamod.modules.luckyblock.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.PacketLuckyEventBypass;
import fr.paladium.palamod.modules.spellsv2.gui.buttons.GuiTexturedButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiVoyant extends GuiScreen {
  private final GuiVoyant instance;
  
  private final EntityPlayer player;
  
  public GuiVoyant(EntityPlayer player) {
    this.instance = this;
    this.player = player;
  }
  
  public void func_73866_w_() {
    this.field_146292_n.clear();
    this.field_146292_n.add(new GuiTexturedButton(0, width(19.0F), height(38.0F), width(9.0F), height(16.0F), new ResourceLocation("palamod", "textures/gui/voyant/1.png"), new ResourceLocation("palamod", "textures/gui/voyant/1_hover.png")));
    this.field_146292_n.add(new GuiTexturedButton(1, width(28.5F), height(38.0F), width(9.0F), height(16.0F), new ResourceLocation("palamod", "textures/gui/voyant/2.png"), new ResourceLocation("palamod", "textures/gui/voyant/2_hover.png")));
    this.field_146292_n.add(new GuiTexturedButton(3, width(38.0F), height(38.0F), width(9.0F), height(16.0F), new ResourceLocation("palamod", "textures/gui/voyant/3.png"), new ResourceLocation("palamod", "textures/gui/voyant/3_hover.png")));
    this.field_146292_n.add(new GuiTexturedButton(4, width(47.5F), height(38.0F), width(9.0F), height(16.0F), new ResourceLocation("palamod", "textures/gui/voyant/4.png"), new ResourceLocation("palamod", "textures/gui/voyant/4_hover.png")));
    this.field_146292_n.add(new GuiTexturedButton(5, width(57.0F), height(38.0F), width(9.0F), height(16.0F), new ResourceLocation("palamod", "textures/gui/voyant/5.png"), new ResourceLocation("palamod", "textures/gui/voyant/5_hover.png")));
    this.field_146292_n.add(new GuiTexturedButton(6, width(66.5F), height(38.0F), width(9.0F), height(16.0F), new ResourceLocation("palamod", "textures/gui/voyant/6.png"), new ResourceLocation("palamod", "textures/gui/voyant/6_hover.png")));
    this.field_146292_n.add(new GuiTexturedButton(7, width(19.0F), height(58.0F), width(9.0F), height(16.0F), new ResourceLocation("palamod", "textures/gui/voyant/7.png"), new ResourceLocation("palamod", "textures/gui/voyant/7_hover.png")));
    this.field_146292_n.add(new GuiTexturedButton(8, width(28.5F), height(58.0F), width(9.0F), height(16.0F), new ResourceLocation("palamod", "textures/gui/voyant/8.png"), new ResourceLocation("palamod", "textures/gui/voyant/8_hover.png")));
    this.field_146292_n.add(new GuiTexturedButton(9, width(38.0F), height(58.0F), width(9.0F), height(16.0F), new ResourceLocation("palamod", "textures/gui/voyant/9.png"), new ResourceLocation("palamod", "textures/gui/voyant/9_hover.png")));
    this.field_146292_n.add(new GuiTexturedButton(10, width(47.5F), height(58.0F), width(9.0F), height(16.0F), new ResourceLocation("palamod", "textures/gui/voyant/10.png"), new ResourceLocation("palamod", "textures/gui/voyant/10_hover.png")));
    this.field_146292_n.add(new GuiTexturedButton(11, width(57.0F), height(58.0F), width(9.0F), height(16.0F), new ResourceLocation("palamod", "textures/gui/voyant/11.png"), new ResourceLocation("palamod", "textures/gui/voyant/11_hover.png")));
    this.field_146292_n.add(new GuiTexturedButton(12, width(66.5F), height(58.0F), width(9.0F), height(16.0F), new ResourceLocation("palamod", "textures/gui/voyant/12.png"), new ResourceLocation("palamod", "textures/gui/voyant/12_hover.png")));
  }
  
  protected void func_146284_a(GuiButton button) {
    if (!this.field_146297_k.field_71439_g.field_70170_p.field_72995_K || this.field_146297_k.field_71462_r != this.instance)
      return; 
    if (button.field_146127_k >= 0 && button.field_146127_k <= 12) {
      PalaMod.getNetwork().sendToServer((IMessage)new PacketLuckyEventBypass());
    } else if (button.field_146127_k == 13) {
      this.field_146297_k.func_147108_a(null);
    } 
    super.func_146284_a(button);
  }
  
  public void func_73863_a(int mouseX, int mouseY, float ticks) {
    func_73866_w_();
    GuiUtils.drawImageTransparent(width(0.0F), height(0.0F), new ResourceLocation("palamod", "textures/gui/voyant/background.png"), 
        width(100.0F), 
        height(100.0F));
    super.func_73863_a(mouseX, mouseY, ticks);
  }
  
  private int width(float value) {
    int v = (int)(this.field_146294_l / 100.0F * value);
    return v;
  }
  
  private int height(float value) {
    int v = (int)(this.field_146295_m / 100.0F * value);
    return v;
  }
  
  public boolean func_73868_f() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\GuiVoyant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */