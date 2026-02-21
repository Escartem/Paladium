package fr.paladium.palamod.modules.spellsv2.gui.buttons;

import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.spellsv2.gui.utils.GuiSpellUtils;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiSpellButton extends GuiButton {
  private final Spells spell;
  
  public GuiSpellButton(int id, int x, int y, int width, int height, Spells spell) {
    super(id, x, y, width, height, "");
    this.spell = spell;
  }
  
  public void func_146112_a(Minecraft mc, int x, int y) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    boolean available = true;
    if (!ClientManager.getSpell(this.spell).isUnlock() && (this.spell
      .getSpell().getLevel() > ClientManager.getLevel() || this.spell
      .getSpell().getCost() > ClientManager.getPoints()))
      available = false; 
    this.field_146123_n = isHover(x, y);
    GuiUtils.drawImageTransparent(this.field_146128_h, this.field_146129_i, new ResourceLocation("palamod", this.field_146123_n ? "textures/gui/GuiSpells/infoh.png" : (available ? "textures/gui/GuiSpells/info.png" : "textures/gui/GuiSpells/infod.png")), this.field_146120_f, this.field_146121_g);
    mc.func_110434_K().func_110577_a(new ResourceLocation("palamod", "textures/gui/spells/" + this.spell
          .getSpell().getName() + ".png"));
    Gui.func_146110_a(this.field_146128_h, this.field_146129_i, 0.0F, 0.0F, (int)(this.field_146120_f / 100.0F * 30.0F), this.field_146121_g, (int)(this.field_146120_f / 100.0F * 30.0F), this.field_146121_g);
    GuiSpellUtils.drawStringCustomFont(this.spell.getSpell().getName(), this.field_146128_h + (int)(this.field_146120_f / 100.0F * 30.0F) + 5, this.field_146129_i + 1 + this.field_146121_g / 2 - mc.field_71466_p.field_78288_b, new Color(98, 65, 50), true, "BurbankBigCondensed-Bold");
    GuiSpellUtils.drawStringCustomFont("Niveau " + this.spell.getSpell().getLevel(), this.field_146128_h + (int)(this.field_146120_f / 100.0F * 30.0F) + 5, this.field_146129_i + 1 + this.field_146121_g / 2, new Color(127, 85, 67), true, "BurbankBigCondensed-Bold");
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  }
  
  public boolean isHover(int x, int y) {
    return (x >= this.field_146128_h && y >= this.field_146129_i && x < this.field_146128_h + this.field_146120_f && y < this.field_146129_i + this.field_146121_g);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\gui\buttons\GuiSpellButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */