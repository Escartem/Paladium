package fr.paladium.palamod.client.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.settings.GameSettings;

@SideOnly(Side.CLIENT)
public class PGuiLanguage extends GuiScreen {
  protected GuiScreen gui;
  
  private List list;
  
  private final GameSettings gameSettings;
  
  private final LanguageManager languageManager;
  
  private GuiButton field_146452_r;
  
  public PGuiLanguage(GuiScreen gui, GameSettings gameSettings, LanguageManager languageManager) {
    this.gui = gui;
    this.gameSettings = gameSettings;
    this.languageManager = languageManager;
  }
  
  public void func_73866_w_() {
    this.field_146292_n.add(this.field_146452_r = new GuiButton(6, this.field_146294_l / 2 - 100, this.field_146295_m - 38, I18n.func_135052_a("gui.done", new Object[0])));
    this.list = new List();
    this.list.func_148134_d(7, 8);
  }
  
  protected void func_146284_a(GuiButton button) {
    if (button.field_146124_l) {
      switch (button.field_146127_k) {
        case 5:
          return;
        case 6:
          this.field_146297_k.func_147108_a(this.gui);
      } 
      this.list.func_148147_a(button);
    } 
  }
  
  public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
    this.list.func_148128_a(mouseX, mouseY, partialTicks);
    func_73732_a(this.field_146289_q, I18n.func_135052_a("options.language", new Object[0]), this.field_146294_l / 2, 16, 16777215);
    super.func_73863_a(mouseX, mouseY, partialTicks);
  }
  
  @SideOnly(Side.CLIENT)
  class List extends GuiSlot {
    private final java.util.List field_148176_l = Lists.newArrayList();
    
    private final Map field_148177_m = Maps.newHashMap();
    
    public List() {
      super(PGuiLanguage.this.field_146297_k, PGuiLanguage.this.field_146294_l, PGuiLanguage.this.field_146295_m, 32, PGuiLanguage.this.field_146295_m - 60 + 4, 18);
      Iterator<Language> iterator = PGuiLanguage.this.languageManager.func_135040_d().iterator();
      while (iterator.hasNext()) {
        Language language = iterator.next();
        if ("en_US".equals(language.func_135034_a()) || "fr_FR".equals(language.func_135034_a())) {
          this.field_148177_m.put(language.func_135034_a(), language);
          this.field_148176_l.add(language.func_135034_a());
        } 
      } 
    }
    
    protected int func_148127_b() {
      return this.field_148176_l.size();
    }
    
    protected void func_148144_a(int p_148144_1_, boolean p_148144_2_, int p_148144_3_, int p_148144_4_) {
      Language language = (Language)this.field_148177_m.get(this.field_148176_l.get(p_148144_1_));
      PGuiLanguage.this.languageManager.func_135045_a(language);
      PGuiLanguage.this.gameSettings.field_74363_ab = language.func_135034_a();
      PGuiLanguage.this.field_146297_k.func_110436_a();
      PGuiLanguage.this.field_146289_q.func_78264_a((PGuiLanguage.this.languageManager.func_135042_a() || PGuiLanguage.this.gameSettings.field_151455_aw));
      PGuiLanguage.this.field_146289_q.func_78275_b(PGuiLanguage.this.languageManager.func_135044_b());
      PGuiLanguage.this.field_146452_r.field_146126_j = I18n.func_135052_a("gui.done", new Object[0]);
    }
    
    protected boolean func_148131_a(int p_148131_1_) {
      return ((String)this.field_148176_l.get(p_148131_1_)).equals(PGuiLanguage.this.languageManager.func_135041_c().func_135034_a());
    }
    
    protected int func_148138_e() {
      return func_148127_b() * 18;
    }
    
    protected void func_148123_a() {
      PGuiLanguage.this.func_146276_q_();
    }
    
    protected void func_148126_a(int p_148126_1_, int p_148126_2_, int p_148126_3_, int p_148126_4_, Tessellator p_148126_5_, int p_148126_6_, int p_148126_7_) {
      PGuiLanguage.this.field_146289_q.func_78275_b(true);
      PGuiLanguage.this.func_73732_a(PGuiLanguage.this.field_146289_q, ((Language)this.field_148177_m.get(this.field_148176_l.get(p_148126_1_))).toString(), this.field_148155_a / 2, p_148126_3_ + 1, 16777215);
      PGuiLanguage.this.field_146289_q.func_78275_b(PGuiLanguage.this.languageManager.func_135041_c().func_135035_b());
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\client\gui\PGuiLanguage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */