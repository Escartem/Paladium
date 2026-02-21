package fr.paladium.palamod.modules.spellsv2.gui;

import fr.paladium.palamod.modules.spellsv2.ClientProxy;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiFrozen extends Gui {
  private ResourceLocation texture = new ResourceLocation("palamod", "textures/gui/frozen.png");
  
  public GuiFrozen(Minecraft mc) {
    ScaledResolution res = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
    int width = res.func_78326_a();
    int height = res.func_78328_b();
    if (ClientProxy.frozen) {
      float p = 1.0F;
      Duration duration = Duration.between(LocalDateTime.ofEpochSecond(
            LocalDateTime.now().toEpochSecond(ZoneOffset.ofTotalSeconds(0)), 0, 
            ZoneOffset.ofTotalSeconds(0)), ClientProxy.frozenEnd);
      Duration maxDuration = Duration.between(ClientProxy.frozenStart, ClientProxy.frozenEnd);
      p = (float)duration.getSeconds() / (float)maxDuration.getSeconds();
      GL11.glEnable(3042);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, p);
      mc.func_110434_K().func_110577_a(this.texture);
      Gui.func_146110_a(0, 0, 1.0F, 1.0F, width, height, width, height);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\gui\GuiFrozen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */