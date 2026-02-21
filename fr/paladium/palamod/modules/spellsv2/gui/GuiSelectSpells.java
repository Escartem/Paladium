package fr.paladium.palamod.modules.spellsv2.gui;

import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiSelectSpells extends Gui {
  private int current = 0;
  
  private final int width;
  
  private final int height;
  
  public GuiSelectSpells(Minecraft mc) {
    ScaledResolution res = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
    this.width = res.func_78326_a();
    this.height = res.func_78328_b();
    this.current = ClientManager.getCurrentSpell();
    if (ClientManager.getCurrentSpell() < 0) {
      this.current = (Spells.values()).length / 2;
    } else {
      this.current = ClientManager.getCurrentSpell();
    } 
    if (!ClientManager.getSpell(Spells.values()[this.current]).isUnlock())
      for (Spells spell : Spells.values()) {
        if (ClientManager.getSpell(spell).isUnlock())
          this.current = spell.getSpell().getId(); 
      }  
    Gui.func_73734_a(0, height(75.0F), this.width, height(75.0F) + width(10.0F), (new Color(20, 20, 20, 150)).getRGB());
    int pos = 0;
    int i;
    for (i = this.current; i < (Spells.values()).length; i++) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      Spells spell = Spells.values()[i];
      if (ClientManager.getSpell(spell).isUnlock()) {
        if (this.current != i)
          GL11.glColor4f(0.4F, 0.4F, 0.4F, 0.4F); 
        GuiUtils.drawImageTransparent((width(50.0F) - width(5.0F) + pos * (width(10.0F) + 5)), height(75.0F), new ResourceLocation("palamod", "textures/gui/spells/" + spell
              .getSpell().getName() + ".png"), 
            width(10.0F), width(10.0F));
        pos++;
      } 
    } 
    pos = -1;
    for (i = this.current - 1; i >= 0; i--) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      Spells spell = Spells.values()[i];
      if (ClientManager.getSpell(spell).isUnlock()) {
        if (this.current != i)
          GL11.glColor4f(0.4F, 0.4F, 0.4F, 0.4F); 
        GuiUtils.drawImageTransparent((width(50.0F) - width(5.0F) + pos * (width(10.0F) + 5)), height(75.0F), new ResourceLocation("palamod", "textures/gui/spells/" + spell
              
              .getSpell().getName() + ".png"), 
            width(10.0F), width(10.0F));
        pos--;
      } 
    } 
    if (this.current >= 0) {
      boolean hasSpell = false;
      for (Spells spell : Spells.values()) {
        if (ClientManager.getSpell(spell).isUnlock())
          hasSpell = true; 
      } 
      if (hasSpell) {
        func_73732_a(mc.field_71466_p, Spells.values()[this.current].getSpell().getName(), this.width / 2, 
            height(75.0F) - mc.field_71466_p.field_78288_b, -1);
      } else {
        func_73732_a(mc.field_71466_p, "§cVous ne possédez aucun sorts", this.width / 2, 
            height(75.0F) - mc.field_71466_p.field_78288_b, -1);
      } 
    } 
  }
  
  private int width(float value) {
    return (int)(this.width / 100.0F * value);
  }
  
  private int height(float value) {
    return (int)(this.height / 100.0F * value);
  }
  
  public void keyPress(int key) {
    if (key > 0) {
      for (int spellID = this.current; spellID < (Spells.values()).length; spellID++) {
        if (ClientManager.getSpell(Spells.values()[spellID]).isUnlock() && 
          spellID != this.current) {
          this.current = spellID;
          break;
        } 
      } 
    } else if (key < 0) {
      for (int spellID = this.current; spellID >= 0; spellID--) {
        if (ClientManager.getSpell(Spells.values()[spellID]).isUnlock() && 
          spellID != this.current) {
          this.current = spellID;
          break;
        } 
      } 
    } 
    if (this.current < 0)
      this.current = 0; 
    if (this.current > (Spells.values()).length - 1)
      this.current = (Spells.values()).length - 1; 
    ClientManager.setCurrentSpell(this.current);
  }
  
  public void handleMouseInput() {
    if (Mouse.getEventButtonState())
      ClientManager.setInSelectGui(false); 
    float i = -Math.signum(Mouse.getEventDWheel());
    if (i > 0.0F) {
      for (int spellID = this.current; spellID < (Spells.values()).length; spellID++) {
        if (ClientManager.getSpell(Spells.values()[spellID]).isUnlock() && 
          spellID != this.current) {
          this.current = spellID;
          break;
        } 
      } 
    } else if (i < 0.0F) {
      for (int spellID = this.current; spellID >= 0; spellID--) {
        if (ClientManager.getSpell(Spells.values()[spellID]).isUnlock() && 
          spellID != this.current) {
          this.current = spellID;
          break;
        } 
      } 
    } 
    if (this.current < 0)
      this.current = 0; 
    if (this.current > (Spells.values()).length - 1)
      this.current = (Spells.values()).length - 1; 
    ClientManager.setCurrentSpell(this.current);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\gui\GuiSelectSpells.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */