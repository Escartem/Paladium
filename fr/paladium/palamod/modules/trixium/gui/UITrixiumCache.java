package fr.paladium.palamod.modules.trixium.gui;

import fr.paladium.palamod.modules.trixium.utils.TrixiumReward;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

public class UITrixiumCache {
  public static int value = 0;
  
  public static int trixium;
  
  public static int trixiumFinal;
  
  public static List<TrixiumReward> rewards;
  
  public static double scroll;
  
  public static double scrollTarget;
  
  public static void increaseTrixium(int value) {
    trixiumFinal += value;
    Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a(new ResourceLocation("random.orb"), 10.0F));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\UITrixiumCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */