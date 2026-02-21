package fr.paladium.palamod.modules.luckyblock.gui;

import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiOneMillionPlayer extends GuiScreen {
  private final GuiOneMillionPlayer instance = this;
  
  public void func_73866_w_() {
    this.field_146292_n.clear();
  }
  
  public void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
    this.field_146297_k.field_71439_g.func_71053_j();
  }
  
  public void func_146281_b() {
    if (this.field_146297_k.field_71462_r == this.instance) {
      String prefix = "§e<§2Wilderness§e> §cLuckyMan §7: ";
      PlayerUtils.sendMessage((EntityPlayer)this.field_146297_k.field_71439_g, "§e<§2Wilderness§e> §cLuckyMan §7: Oups… On dirait que vous vous êtes §7fait arnaqué");
    } 
  }
  
  public void func_73863_a(int mouseX, int mouseY, float ticks) {
    GuiUtils.drawImageTransparent(0.0D, 0.0D, new ResourceLocation("palamod", "textures/gui/LuckyBlock/onemillionplayer/background.png"), 
        width(100.0F), height(100.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\GuiOneMillionPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */