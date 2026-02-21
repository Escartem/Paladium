package fr.paladium.palamod.modules.spellsv2.gui;

import fr.paladium.palamod.modules.spellsv2.ClientProxy;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.Temporal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiSpellTiming extends Gui {
  public GuiSpellTiming(Minecraft mc) {
    ScaledResolution res = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
    int width = res.func_78326_a();
    int height = res.func_78328_b();
    if (ClientManager.getCurrentSpell() >= 0) {
      Spells current = Spells.values()[ClientManager.getCurrentSpell()];
      if (ClientProxy.spellStarting.containsKey(current)) {
        Duration duration = Duration.between(LocalDateTime.ofEpochSecond(
              LocalDateTime.now().toEpochSecond(ZoneOffset.ofTotalSeconds(0)), 0, 
              ZoneOffset.ofTotalSeconds(0)), (Temporal)ClientProxy.spellEnding.get(current));
        Duration maxDuration = Duration.between((Temporal)ClientProxy.spellStarting.get(current), (Temporal)ClientProxy.spellEnding
            .get(current));
        float pourcent = (float)duration.getSeconds() / (float)maxDuration.getSeconds();
        if (duration.getSeconds() > 0L) {
          LocalTime timeOfDay = LocalTime.ofSecondOfDay(duration.getSeconds());
          String time = timeOfDay.toString();
          if (timeOfDay.getHour() == 0)
            time = time.replaceFirst("00:", ""); 
          GL11.glColor3f(1.0F, 1.0F, 1.0F);
          mc.func_110434_K().func_110577_a(new ResourceLocation("palamod", "textures/gui/GuiSpells/cooldown.png"));
          Gui.func_146110_a(width / 2 - 91, height - 29, 0.0F, 0.0F, 182, 5, 182.0F, 10.0F);
          Gui.func_146110_a(width / 2 - 91, height - 29, 0.0F, 5.0F, (int)(182.0F * pourcent), 5, 182.0F, 10.0F);
          func_73732_a(mc.field_71466_p, "§e" + time, width / 2, height - 26 - mc.field_71466_p.field_78288_b / 2, 1);
          GL11.glColor3f(1.0F, 1.0F, 1.0F);
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\gui\GuiSpellTiming.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */