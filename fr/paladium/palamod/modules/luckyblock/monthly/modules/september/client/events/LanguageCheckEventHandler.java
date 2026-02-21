package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.SortedSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.LanguageManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class LanguageCheckEventHandler {
  private static final String EN_US = "en_US";
  
  public static LanguageCheckEventHandler INSTANCE;
  
  private long languageExpirationMillis;
  
  public void setLanguageExpirationMillis(long languageExpirationMillis) {
    this.languageExpirationMillis = languageExpirationMillis;
  }
  
  public long getLanguageExpirationMillis() {
    return this.languageExpirationMillis;
  }
  
  public LanguageCheckEventHandler() {
    INSTANCE = this;
    this.languageExpirationMillis = 0L;
  }
  
  @SubscribeEvent
  public void onRender(RenderGameOverlayEvent event) {
    Minecraft minecraft = Minecraft.func_71410_x();
    long now = System.currentTimeMillis();
    if (now > this.languageExpirationMillis)
      return; 
    LanguageManager languageManager = minecraft.func_135016_M();
    String currentLanguage = languageManager.func_135041_c().func_135034_a();
    if (!currentLanguage.equals("en_US")) {
      SortedSet<Language> languages = languageManager.func_135040_d();
      for (Language language : languages) {
        if (language.func_135034_a().equals("en_US")) {
          languageManager.func_135045_a(language);
          minecraft.func_110436_a();
          break;
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\events\LanguageCheckEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */