package fr.paladium.palamod.modules.paladium.client.render;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.tooltip.CustomTooltipNode;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.ItemAncientHammer;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import javax.vecmath.Vector4f;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ItemAncientHammerTooltipNode extends CustomTooltipNode {
  private static final String DESCRIPTION = "Le Marteau antique peut être fusionnée avec une Legendary Stone dans le Grinder. Cette fusion confère un effet unique.";
  
  private static final Map<LegendaryStone.Effect, Map.Entry<String, Color>> NAME_MAP = new HashMap<>();
  
  private static final Map<LegendaryStone.Effect, String> DESCRIPTION_MAP = new HashMap<>();
  
  private static final Map<LegendaryStone.Effect, String> COOLDOWN_MAP = new HashMap<>();
  
  static {
    NAME_MAP.put(LegendaryStone.Effect.JOBS, new AbstractMap.SimpleEntry<>("Legendary stone JOBS", Color.decode("#20E149")));
    NAME_MAP.put(LegendaryStone.Effect.CHAOS, new AbstractMap.SimpleEntry<>("Legendary stone CHAOS", Color.decode("#AE5DDD")));
    NAME_MAP.put(LegendaryStone.Effect.POWER, new AbstractMap.SimpleEntry<>("Legendary stone POWER", Color.decode("#FF535E")));
    NAME_MAP.put(LegendaryStone.Effect.FORTUNE, new AbstractMap.SimpleEntry<>("Legendary stone FORTUNE", Color.decode("#FCD000")));
    NAME_MAP.put(LegendaryStone.Effect.INVISIBILITY, new AbstractMap.SimpleEntry<>("Legendary stone INVISIBILITY", Color.decode("#00A4FC")));
    NAME_MAP.put(LegendaryStone.Effect.TELEPORTATION, new AbstractMap.SimpleEntry<>("Legendary stone TELEPORTATION", Color.decode("#FC9300")));
    DESCRIPTION_MAP.put(LegendaryStone.Effect.CHAOS, "Faites clic droit avec l'arme pour corrompre les blocs dans un rayon de 5 blocs, ceux-ci appliquent les effets Lenteur III et Poison I pendant 10 secondes quand on marche dessus.");
    DESCRIPTION_MAP.put(LegendaryStone.Effect.TELEPORTATION, "Faites clic droit avec l'arme pour intervertir les positions de tous les joueurs dans un rayon de 16 blocs de manière aléatoire.");
    DESCRIPTION_MAP.put(LegendaryStone.Effect.INVISIBILITY, "Faites clic droit avec l'arme pour faire apparaître un clone, possédant la même armure et la même arme, qui attaque les joueurs. Tu deviens invisible jusqu'à la mort du clone.");
    DESCRIPTION_MAP.put(LegendaryStone.Effect.POWER, "Faites clic droit avec l'arme pour charger une onde de choc en fonction des dégâts subis durant les 30 prochaines secondes ou refaite clic droit pour activer l'effet manuellement.");
    DESCRIPTION_MAP.put(LegendaryStone.Effect.FORTUNE, "Faites clic droit avec l'arme pour voler 1 % des dollars des joueurs tués avec le marteau durant les 30 prochaines secondes. Les victimes ont accès à un mini-jeu pour tenter d'éviter la perte.");
    DESCRIPTION_MAP.put(LegendaryStone.Effect.JOBS, "Faites clic droit avec l'arme pour déclencher une vague dans un rayon de 16 blocs : bûches, graines, minerais sont cassés et les monstres sont éliminés. Vous gagnez l'XP métier correspondante.");
    COOLDOWN_MAP.put(LegendaryStone.Effect.CHAOS, "1H");
    COOLDOWN_MAP.put(LegendaryStone.Effect.TELEPORTATION, "1H");
    COOLDOWN_MAP.put(LegendaryStone.Effect.INVISIBILITY, "12H");
    COOLDOWN_MAP.put(LegendaryStone.Effect.POWER, "1H");
    COOLDOWN_MAP.put(LegendaryStone.Effect.FORTUNE, "12H");
    COOLDOWN_MAP.put(LegendaryStone.Effect.JOBS, "1H");
  }
  
  private static final TextInfo NAME_INFO = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 24.0F, Color.decode("#F6F9E8"));
  
  private static final TextInfo DESCRIPTION_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 14.0F, Color.WHITE);
  
  private static final TextInfo DAMAGE_INFO = TextInfo.create((IFont)PaladiumFont.MINECRAFT, 14.0F, Color.decode("#3E3EFD")).shadow().shadow(0.0F, 1.0F);
  
  private static final Color GOLD_COLOR = Color.decode("#F99E37");
  
  private static final Resource NOT_FULL_TEXTURE = Resource.of(new ResourceLocation("palamod", "textures/gui/tooltip/ancient_armor/not_full.png")).linear();
  
  private static final Resource DAMAGE_TEXTURE = Resource.of(new ResourceLocation("palamod", "textures/gui/tooltip/ancient_hammer/damage.png")).nearest();
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    size(getTooltipWidth(getItem()), getTooltipHeight(getItem()));
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    ItemStack itemStack = getItem();
    ItemAncientHammer item = (ItemAncientHammer)itemStack.func_77973_b();
    String itemName = getItemName(itemStack);
    double width = getTooltipWidth(itemStack);
    double height = getTooltipHeight(itemStack);
    drawBackground(mouseX, mouseY, width, height);
    DrawUtils.ITEM.drawItem(mouseX + 26.0D, mouseY + 40.0D, 61.0D, itemStack, false);
    DrawUtils.TEXT.drawText(mouseX + 108.0D - 3.0D, mouseY + 40.0D, Text.create(itemName, NAME_INFO));
    double ox = mouseX + 108.0D;
    float itemDamage = item.func_150931_i() + 4.0F;
    for (int i = 1; i < itemDamage; i++) {
      DrawUtils.RESOURCE.drawImage(ox, mouseY + 40.0D + NAME_INFO.getHeight(), 13.0D, 14.0D, DAMAGE_TEXTURE.textureCoords(0.0D, 0.0D, 13.0D, 14.0D));
      ox += 17.0D;
    } 
    float remainder = itemDamage - (int)Math.floor(itemDamage);
    if (remainder > 0.0F)
      DrawUtils.RESOURCE.drawImage(ox, mouseY + 40.0D + NAME_INFO.getHeight(), 13.0D, 14.0D, DAMAGE_TEXTURE.textureCoords(0.0D, 0.0D, 13.0D * remainder, 14.0D)); 
    ox += 13.0D;
    DrawUtils.TEXT.drawText(ox, mouseY + 40.0D + NAME_INFO.getHeight() + 6.0D, "+11.3", DAMAGE_INFO, Align.START, Align.CENTER);
    double topMargin = 126.0D;
    DrawUtils.TEXT.drawText(mouseX + 26.0D, mouseY + topMargin, width - 52.0D, 0.0D, Text.create("Le Marteau antique peut être fusionnée avec une Legendary Stone dans le Grinder. Cette fusion confère un effet unique.", DESCRIPTION_INFO), TextMode.SPLIT);
    topMargin += getDescriptionHeight(itemStack) + 10.0D;
    boolean hasEffect = (ItemAncientHammer.getEffect(itemStack) != null);
    if (hasEffect) {
      DrawUtils.SHAPE.drawRect(mouseX + 26.0D, mouseY + topMargin, width - 52.0D, 4.0D, Color.decode("#40404D").copyAlpha(0.5F));
      topMargin += 16.0D;
      double sectionHeight = 46.0D;
      ItemStack hammer = itemStack;
      LegendaryStone.Effect effect = ItemAncientHammer.getEffect(itemStack);
      if (hammer != null && hammer.func_77973_b() instanceof ItemAncientHammer)
        sectionHeight += 68.0D; 
      DrawUtils.SHAPE.drawRect(mouseX + 26.0D, mouseY + topMargin, width - 52.0D, sectionHeight, Color.decode("#40404D").copyAlpha(0.5F));
      DrawUtils.TEXT.drawText(mouseX + 26.0D + 17.0D, mouseY + topMargin + 14.0D, Text.create("Légendary Stone équipée", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 14.0F, Color.WHITE)));
      double d1 = mouseX + 26.0D + width - 52.0D - 14.0D - 81.0D;
      double oy = mouseY + topMargin + 11.0D;
      double size = 18.0D;
      d1 = mouseX + 26.0D + 17.0D;
      oy = mouseY + topMargin + 14.0D + 17.0D + 15.0D;
      size = 34.0D;
      DrawUtils.SHAPE.drawRect(d1, oy, size, size, Color.decode("#171717"));
      DrawUtils.ITEM.drawItem(d1 + size / 2.0D - 14.0D, oy + size / 2.0D - 14.0D, 28.0D, effect.getItem());
      Map.Entry<String, Color> nameEntry = NAME_MAP.get(effect);
      DrawUtils.TEXT.drawText(d1 + size + 10.0D, oy, Text.create(nameEntry.getKey(), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 14.0F, nameEntry.getValue()).shadow(((Color)nameEntry.getValue()).darker(0.6F)).shadow(0.0F, 1.0F)));
      DrawUtils.TEXT.drawText(d1 + size + mouseX + 26.0D + width - 52.0D - 13.0D - d1 + size + 10.0D, oy + 7.0D, Text.create("Cooldown " + (String)COOLDOWN_MAP.get(effect), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 9.0F, Color.WHITE).shadow().shadow(0.0F, 1.0F)).align(Align.END, Align.CENTER));
      DrawUtils.TEXT.drawText(d1 + size + 10.0D, oy + 18.0D, mouseX + 26.0D + width - 52.0D - 13.0D - d1 + size + 10.0D, 0.0D, Text.create(DESCRIPTION_MAP.get(effect), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 10.0F, Color.WHITE)), TextMode.SPLIT);
      oy += size;
      topMargin += sectionHeight + 10.0D;
    } 
    Map<Integer, Integer> enchantments = EnchantmentHelper.func_82781_a(itemStack);
    if (!enchantments.isEmpty()) {
      DrawUtils.SHAPE.drawRect(mouseX + 26.0D, mouseY + topMargin, width - 52.0D, 4.0D, Color.decode("#40404D").copyAlpha(0.5F));
      topMargin += 16.0D;
      double margin = 6.0D;
      double borderSize = 2.2D;
      TextInfo enchantInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 14.0F, Color.WHITE).shadow(Color.decode("#666666")).shadow(0.0F, 1.0F);
      double sectionX = mouseX + 26.0D;
      double sectionY = mouseY + topMargin;
      double sectionWidth = width - 52.0D;
      double sectionHeight = 21.0D + enchantments.size() * (enchantInfo.getHeight() + 6.0D) - 6.0D + 21.0D;
      Color sectionColor = Color.decode("#33333B");
      DrawUtils.SHAPE.drawRect(sectionX + 15.400000000000002D, sectionY + 2.2D, sectionWidth - 30.800000000000004D, 2.2D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + 15.400000000000002D, sectionY + sectionHeight - 4.4D, sectionWidth - 30.800000000000004D, 2.2D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + 2.2D, sectionY + 15.400000000000002D, 2.2D, sectionHeight - 30.800000000000004D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + sectionWidth - 4.4D, sectionY + 15.400000000000002D, 2.2D, sectionHeight - 30.800000000000004D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX, sectionY, 11.0D, 2.2D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX, sectionY + 8.8D, 17.6D, 2.2D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + 2.2D, sectionY + 15.400000000000002D, 8.8D, 2.2D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX, sectionY, 2.2D, 11.0D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + 8.8D, sectionY, 2.2D, 17.6D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + 15.400000000000002D, sectionY + 2.2D, 2.2D, 8.8D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + sectionWidth - 11.0D, sectionY, 11.0D, 2.2D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + sectionWidth - 17.6D, sectionY + 8.8D, 17.6D, 2.2D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + sectionWidth - 11.0D, sectionY + 15.400000000000002D, 8.8D, 2.2D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + sectionWidth - 2.2D, sectionY, 2.2D, 11.0D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + sectionWidth - 11.0D, sectionY, 2.2D, 17.6D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + sectionWidth - 17.6D, sectionY + 2.2D, 2.2D, 8.8D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX, sectionY + sectionHeight - 2.2D, 11.0D, 2.2D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX, sectionY + sectionHeight - 11.0D, 17.6D, 2.2D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + 2.2D, sectionY + sectionHeight - 17.6D, 8.8D, 2.2D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX, sectionY + sectionHeight - 11.0D, 2.2D, 11.0D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + 8.8D, sectionY + sectionHeight - 17.6D, 2.2D, 17.6D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + 15.400000000000002D, sectionY + sectionHeight - 11.0D, 2.2D, 8.8D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + sectionWidth - 11.0D, sectionY + sectionHeight - 2.2D, 11.0D, 2.2D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + sectionWidth - 17.6D, sectionY + sectionHeight - 11.0D, 17.6D, 2.2D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + sectionWidth - 11.0D, sectionY + sectionHeight - 17.6D, 8.8D, 2.2D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + sectionWidth - 2.2D, sectionY + sectionHeight - 11.0D, 2.2D, 11.0D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + sectionWidth - 11.0D, sectionY + sectionHeight - 17.6D, 2.2D, 17.6D, sectionColor);
      DrawUtils.SHAPE.drawRect(sectionX + sectionWidth - 17.6D, sectionY + sectionHeight - 11.0D, 2.2D, 8.8D, sectionColor);
      double oy = sectionY + 21.0D;
      for (Map.Entry<Integer, Integer> enchantmentEntry : enchantments.entrySet()) {
        String enchantmentName = Enchantment.field_77331_b[((Integer)enchantmentEntry.getKey()).intValue()].func_77316_c(((Integer)enchantmentEntry.getValue()).intValue());
        DrawUtils.TEXT.drawText(sectionX + 26.0D, oy, Text.create(enchantmentName, enchantInfo));
        oy += enchantInfo.getHeight() + 6.0D;
      } 
      topMargin += sectionHeight + 10.0D;
    } 
  }
  
  private static String getItemName(@NonNull ItemStack itemStack) {
    if (itemStack == null)
      throw new NullPointerException("itemStack is marked non-null but is null"); 
    String itemName = itemStack.func_82833_r().toLowerCase().replace("é", "e").replace("è", "e").replace("ê", "e");
    if ((Minecraft.func_71410_x()).field_71474_y.field_82882_x) {
      String end = "";
      if (itemName.length() > 0) {
        itemName = itemName + " (";
        end = ")";
      } 
      int id = Item.func_150891_b(itemStack.func_77973_b());
      if (itemStack.func_77981_g()) {
        itemName = itemName + String.format("#%04d/%d%s", new Object[] { Integer.valueOf(id), Integer.valueOf(itemStack.func_77960_j()), end });
      } else {
        itemName = itemName + String.format("#%04d%s", new Object[] { Integer.valueOf(id), end });
      } 
    } 
    return itemName;
  }
  
  private static void drawBackground(double x, double y, double width, double height) {
    double borderSize = 8.0D;
    Color borderColor = Color.decode("#5E3B14");
    Color backgroundColor = Color.decode("#0E0E0E");
    GL11.glEnable(2960);
    GL11.glStencilFunc(519, 1, 255);
    GL11.glStencilOp(7681, 7681, 7681);
    GL11.glColorMask(true, true, true, true);
    DrawUtils.SHAPE.drawRect(x, y, width, height, backgroundColor);
    DrawUtils.SHAPE.drawBorder(x, y, width + x, height + y, backgroundColor, 10.0D);
    GL11.glStencilFunc(514, 1, 255);
    GL11.glStencilOp(7680, 7680, 7680);
    DrawUtils.RESOURCE.drawScaledImageWidth(x - 10.0D, y - 10.0D, width + 20.0D, NOT_FULL_TEXTURE);
    GL11.glDisable(2960);
    GL11.glClear(1024);
    DrawUtils.SHAPE.drawRect(x + 8.0D, y, width - 16.0D, 8.0D, GOLD_COLOR);
    DrawUtils.SHAPE.drawRect(x, y + 8.0D, 8.0D, height - 16.0D, borderColor);
    DrawUtils.SHAPE.drawRect(x + width - 8.0D, y + 8.0D, 8.0D, height - 16.0D, borderColor);
    GL11.glTranslated(x, y + 8.0D, 0.0D);
    DrawUtils.SHAPE.drawRect(0.0D, 0.0D, 8.0D, height - 24.0D, GOLD_COLOR.toGradient(borderColor, new Vector4f(0.0F, 0.0F, 0.0F, 1.0F)));
    GL11.glTranslated(-x, -(y + 8.0D), 0.0D);
    GL11.glTranslated(x + width - 8.0D, y + 8.0D, 0.0D);
    DrawUtils.SHAPE.drawRect(0.0D, 0.0D, 8.0D, height - 24.0D, GOLD_COLOR.toGradient(borderColor, new Vector4f(0.0F, 0.0F, 0.0F, 1.0F)));
    GL11.glTranslated(-(x + width - 8.0D), -(y + 8.0D), 0.0D);
    DrawUtils.SHAPE.drawRect(x + 8.0D, y + height - 8.0D, width - 16.0D, 8.0D, borderColor);
    DrawUtils.SHAPE.drawRect(x + 8.0D, y + 8.0D, 8.0D, 8.0D, GOLD_COLOR);
    DrawUtils.SHAPE.drawRect(x + 16.0D, y + 16.0D, 8.0D, 8.0D, GOLD_COLOR);
    DrawUtils.SHAPE.drawRect(x + 24.0D, y + 16.0D, 8.0D, 8.0D, GOLD_COLOR);
    DrawUtils.SHAPE.drawRect(x + 16.0D, y + 24.0D, 8.0D, 8.0D, GOLD_COLOR);
    DrawUtils.SHAPE.drawRect(x + width - 16.0D, y + 8.0D, 8.0D, 8.0D, GOLD_COLOR);
    DrawUtils.SHAPE.drawRect(x + width - 24.0D, y + 16.0D, 8.0D, 8.0D, GOLD_COLOR);
    DrawUtils.SHAPE.drawRect(x + width - 32.0D, y + 16.0D, 8.0D, 8.0D, GOLD_COLOR);
    DrawUtils.SHAPE.drawRect(x + width - 24.0D, y + 24.0D, 8.0D, 8.0D, GOLD_COLOR);
    DrawUtils.SHAPE.drawRect(x + 8.0D, y + height - 16.0D, 8.0D, 8.0D, borderColor);
    DrawUtils.SHAPE.drawRect(x + 16.0D, y + height - 24.0D, 8.0D, 8.0D, borderColor);
    DrawUtils.SHAPE.drawRect(x + 24.0D, y + height - 24.0D, 8.0D, 8.0D, borderColor);
    DrawUtils.SHAPE.drawRect(x + 16.0D, y + height - 32.0D, 8.0D, 8.0D, borderColor);
    DrawUtils.SHAPE.drawRect(x + width - 16.0D, y + height - 16.0D, 8.0D, 8.0D, borderColor);
    DrawUtils.SHAPE.drawRect(x + width - 24.0D, y + height - 24.0D, 8.0D, 8.0D, borderColor);
    DrawUtils.SHAPE.drawRect(x + width - 32.0D, y + height - 24.0D, 8.0D, 8.0D, borderColor);
    DrawUtils.SHAPE.drawRect(x + width - 24.0D, y + height - 32.0D, 8.0D, 8.0D, borderColor);
  }
  
  private static double getDescriptionHeight(@NonNull ItemStack itemStack) {
    if (itemStack == null)
      throw new NullPointerException("itemStack is marked non-null but is null"); 
    return DrawUtils.TEXT.getLines(ItemAncientArmorTooltipNode.getTooltipWidth(itemStack) - 52.0D, "Le Marteau antique peut être fusionnée avec une Legendary Stone dans le Grinder. Cette fusion confère un effet unique.", DESCRIPTION_INFO).size() * DESCRIPTION_INFO.getHeight();
  }
  
  public static double getTooltipWidth(@NonNull ItemStack itemStack) {
    if (itemStack == null)
      throw new NullPointerException("itemStack is marked non-null but is null"); 
    return 108.0D + Math.max(NAME_INFO.getWidth(getItemName(itemStack)), 120.0D) + 150.0D;
  }
  
  public static double getTooltipHeight(@NonNull ItemStack itemStack) {
    if (itemStack == null)
      throw new NullPointerException("itemStack is marked non-null but is null"); 
    double topMargin = 126.0D;
    topMargin += getDescriptionHeight(itemStack) + 10.0D;
    boolean hasEffect = (ItemAncientHammer.getEffect(itemStack) != null);
    if (hasEffect) {
      topMargin += 16.0D;
      double sectionHeight = 46.0D;
      ItemStack hammer = itemStack;
      if (hammer != null && hammer.func_77973_b() instanceof ItemAncientHammer)
        sectionHeight += 68.0D; 
      topMargin += sectionHeight + 10.0D;
    } 
    Map<Integer, Integer> enchantments = EnchantmentHelper.func_82781_a(itemStack);
    if (!enchantments.isEmpty()) {
      topMargin += 16.0D;
      double margin = 6.0D;
      TextInfo enchantInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 14.0F, Color.WHITE).shadow(Color.decode("#666666")).shadow(0.0F, 1.0F);
      double sectionHeight = 21.0D + enchantments.size() * (enchantInfo.getHeight() + 6.0D) - 6.0D + 21.0D;
      topMargin += sectionHeight + 10.0D;
    } 
    return topMargin + 30.0D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\ItemAncientHammerTooltipNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */