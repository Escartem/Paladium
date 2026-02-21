package fr.paladium.palaforgeutils.lib.lang;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.resources.Locale;
import net.minecraft.util.StringTranslate;

public class CustomLanguageManager implements IResourceManagerReloadListener {
  public static final String LOCALE_EN = "en_US";
  
  public static final String LOCALE_FR = "fr_FR";
  
  private static final Map<String, Map<String, String>> TRANSLATION_MAP = new HashMap<>();
  
  private static final String[] LOCALES = new String[] { "en_US", "fr_FR" };
  
  public void func_110549_a(IResourceManager resourceManager) {
    LanguageManager languageManager = Minecraft.func_71410_x().func_135016_M();
    String currentLanguage = (String)ObfuscationReflectionHelper.getPrivateValue(LanguageManager.class, languageManager, new String[] { "currentLanguage", "field_135048_c" });
    if (currentLanguage == null)
      currentLanguage = "en_US"; 
    if (!TRANSLATION_MAP.containsKey(currentLanguage))
      return; 
    Locale locale = (Locale)ObfuscationReflectionHelper.getPrivateValue(LanguageManager.class, languageManager, new String[] { "currentLocale", "field_135049_a" });
    if (locale == null)
      return; 
    Map<String, String> localeData = (Map<String, String>)ObfuscationReflectionHelper.getPrivateValue(Locale.class, locale, new String[] { "field_135032_a" });
    localeData.putAll(TRANSLATION_MAP.get(currentLanguage));
    LanguageRegistry.instance().mergeLanguageTable(localeData, currentLanguage);
    StringTranslate.func_135063_a(localeData);
  }
  
  public static void register(@NonNull String locale, @NonNull String key, @NonNull String value) {
    if (locale == null)
      throw new NullPointerException("locale is marked non-null but is null"); 
    if (key == null)
      throw new NullPointerException("key is marked non-null but is null"); 
    if (value == null)
      throw new NullPointerException("value is marked non-null but is null"); 
    ((Map<String, String>)TRANSLATION_MAP.computeIfAbsent(locale, k -> new HashMap<>())).put(key, value);
  }
  
  public static void register(@NonNull String key, @NonNull String value) {
    if (key == null)
      throw new NullPointerException("key is marked non-null but is null"); 
    if (value == null)
      throw new NullPointerException("value is marked non-null but is null"); 
    for (String locale : LOCALES)
      register(locale, key, value); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\lang\CustomLanguageManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */