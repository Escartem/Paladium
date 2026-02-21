package fr.paladium.palamod.modules.luckyblock.events;

import fr.paladium.palamod.modules.luckyblock.gui.luckypass.UILuckyPass;
import fr.paladium.palamod.modules.spellsv2.gui.buttons.GuiTexturedButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;

class null extends GuiTexturedButton {
  null(int id, int x, int y, int width, int height, ResourceLocation texture, ResourceLocation hoveredTexture, ResourceLocation disabled) {
    super(id, x, y, width, height, texture, hoveredTexture, disabled);
  }
  
  public void func_146118_a(int x, int y) {
    e.gui.field_146297_k.func_147108_a((GuiScreen)new UILuckyPass());
    super.func_146118_a(x, y);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\EventsManager$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */