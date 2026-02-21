package fr.paladium.paladiumui.kit.font;

import fr.paladium.zephyrui.lib.font.FontLoader;
import fr.paladium.zephyrui.lib.font.dto.font.FontInputStream;
import fr.paladium.zephyrui.lib.font.dto.input.MinecraftFontInputStream;
import fr.paladium.zephyrui.lib.font.impl.custom.CustomFont;
import java.io.IOException;
import java.util.function.Consumer;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class PaladiumFont {
  public static CustomFont MONTSERRAT_THIN;
  
  public static CustomFont MONTSERRAT_EXTRA_LIGHT;
  
  public static CustomFont MONTSERRAT_LIGHT;
  
  public static CustomFont MONTSERRAT_REGULAR;
  
  public static CustomFont MONTSERRAT_MEDIUM;
  
  public static CustomFont MONTSERRAT_SEMI_BOLD;
  
  public static CustomFont MONTSERRAT_BOLD;
  
  public static CustomFont MONTSERRAT_EXTRA_BOLD;
  
  public static CustomFont MONTSERRAT_BLACK;
  
  public static CustomFont PIXEL_NES;
  
  public static CustomFont MINECRAFT_DUNGEONS;
  
  public static CustomFont MINECRAFT;
  
  public static void load() {
    loadFont("Montserrat-Thin", "Montserrat-ExtraLight", font -> MONTSERRAT_THIN = font);
    loadFont("Montserrat-ExtraLight", "Montserrat-Light", font -> MONTSERRAT_EXTRA_LIGHT = font);
    loadFont("Montserrat-Light", "Montserrat-Regular", font -> MONTSERRAT_LIGHT = font);
    loadFont("Montserrat-Regular", "Montserrat-Medium", font -> MONTSERRAT_REGULAR = font);
    loadFont("Montserrat-Medium", "Montserrat-SemiBold", font -> MONTSERRAT_MEDIUM = font);
    loadFont("Montserrat-SemiBold", "Montserrat-Bold", font -> MONTSERRAT_SEMI_BOLD = font);
    loadFont("Montserrat-Bold", "Montserrat-ExtraBold", font -> MONTSERRAT_BOLD = font);
    loadFont("Montserrat-ExtraBold", "Montserrat-Black", font -> MONTSERRAT_EXTRA_BOLD = font);
    loadFont("Montserrat-Black", font -> MONTSERRAT_BLACK = font);
    loadFont("Pixel-NES", font -> PIXEL_NES = font);
    loadFont("Minecraft-Dungeons", font -> MINECRAFT_DUNGEONS = font);
    loadFont("Minecraft", font -> MINECRAFT = font);
  }
  
  private static void loadFont(@NonNull String regularName, @NonNull Consumer<CustomFont> consumer) {
    if (regularName == null)
      throw new NullPointerException("regularName is marked non-null but is null"); 
    if (consumer == null)
      throw new NullPointerException("consumer is marked non-null but is null"); 
    try {
      ResourceLocation regularData = new ResourceLocation("paladium-ui", "fonts/" + regularName + "/font.json");
      ResourceLocation regularTexture = new ResourceLocation("paladium-ui", "fonts/" + regularName + "/font.png");
      MinecraftFontInputStream minecraftFontInputStream = new MinecraftFontInputStream(regularData, regularTexture);
      FontLoader.load((FontInputStream)minecraftFontInputStream, consumer);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  private static void loadFont(@NonNull String regularName, @NonNull String boldName, @NonNull Consumer<CustomFont> consumer) {
    if (regularName == null)
      throw new NullPointerException("regularName is marked non-null but is null"); 
    if (boldName == null)
      throw new NullPointerException("boldName is marked non-null but is null"); 
    if (consumer == null)
      throw new NullPointerException("consumer is marked non-null but is null"); 
    try {
      ResourceLocation regularData = new ResourceLocation("paladium-ui", "fonts/" + regularName + "/font.json");
      ResourceLocation regularTexture = new ResourceLocation("paladium-ui", "fonts/" + regularName + "/font.png");
      ResourceLocation boldData = new ResourceLocation("paladium-ui", "fonts/" + boldName + "/font.json");
      ResourceLocation boldTexture = new ResourceLocation("paladium-ui", "fonts/" + boldName + "/font.png");
      MinecraftFontInputStream minecraftFontInputStream1 = new MinecraftFontInputStream(regularData, regularTexture);
      MinecraftFontInputStream minecraftFontInputStream2 = new MinecraftFontInputStream(boldData, boldTexture);
      FontLoader.load((FontInputStream)minecraftFontInputStream1, (FontInputStream)minecraftFontInputStream2, consumer);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\font\PaladiumFont.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */