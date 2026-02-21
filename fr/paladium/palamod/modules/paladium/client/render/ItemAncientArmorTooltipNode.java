package fr.paladium.palamod.modules.paladium.client.render;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.tooltip.CustomTooltipNode;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.ItemAncientArmor;
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
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ItemAncientArmorTooltipNode extends CustomTooltipNode {
  private static final String DESCRIPTION = "Chaque pièce d'armure antique peut être fusionnée avec une Legendary Stone dans le Grinder. Cette fusion confère un effet unique, amplifié lorsque l'ensemble de l'armure est complet.";
  
  private static final Map<LegendaryStone.Effect, Map.Entry<String, Color>> NAME_MAP = new HashMap<>();
  
  private static final Map<LegendaryStone.Effect, String[]> DESCRIPTION_MAP = (Map)new HashMap<>();
  
  static {
    NAME_MAP.put(LegendaryStone.Effect.JOBS, new AbstractMap.SimpleEntry<>("Legendary stone JOBS", Color.decode("#20E149")));
    NAME_MAP.put(LegendaryStone.Effect.CHAOS, new AbstractMap.SimpleEntry<>("Legendary stone CHAOS", Color.decode("#AE5DDD")));
    NAME_MAP.put(LegendaryStone.Effect.POWER, new AbstractMap.SimpleEntry<>("Legendary stone POWER", Color.decode("#FF535E")));
    NAME_MAP.put(LegendaryStone.Effect.FORTUNE, new AbstractMap.SimpleEntry<>("Legendary stone FORTUNE", Color.decode("#FCD000")));
    NAME_MAP.put(LegendaryStone.Effect.INVISIBILITY, new AbstractMap.SimpleEntry<>("Legendary stone INVISIBILITY", Color.decode("#00A4FC")));
    NAME_MAP.put(LegendaryStone.Effect.TELEPORTATION, new AbstractMap.SimpleEntry<>("Legendary stone TELEPORTATION", Color.decode("#FC9300")));
    DESCRIPTION_MAP.put(LegendaryStone.Effect.JOBS, new String[] { "§rVous gagnez §l25%§r d'expérience supplémentaire dans tous les métiers.", "§rVous gagnez §l50%§r d'expérience supplémentaire dans tous les métiers." });
    DESCRIPTION_MAP.put(LegendaryStone.Effect.CHAOS, new String[] { "§rLorsque vous subissez des dégâts, vous avez §l15%§r de chance qu'un item aléatoire de l'inventaire de votre attaquant change de place avec un autre item de son inventaire.", "§rLorsque vous subissez des dégâts, vous avez §l30%§r de chance qu'un item aléatoire de l'inventaire de votre attaquant change de place avec un autre item de son inventaire." });
    DESCRIPTION_MAP.put(LegendaryStone.Effect.POWER, new String[] { "§rVous ne subissez pas de dégâts dans la §lsulfuric water§r.", "§rVous ne subissez pas de dégâts dans la §lsulfuric water§r et vous gagnez un effet de §lrégénération§r." });
    DESCRIPTION_MAP.put(LegendaryStone.Effect.FORTUNE, new String[] { "§rVous gagnez §l25$§r toutes les minutes.", "§rVous gagnez §l50$§r toutes les minutes." });
    DESCRIPTION_MAP.put(LegendaryStone.Effect.INVISIBILITY, new String[] { "§rLorsque vous subissez un coup fatal, le coup est annulé et vous devenez §linvisible§r pendant §l10 secondes§r. Cet effet ne peut se produire qu'une fois toutes les heures.", "§rLorsque vous subissez un coup fatal, le coup est annulé et vous devenez §linvisible§r pendant §l20 secondes§r. Cet effet ne peut se produire qu'une fois toutes les heures." });
    DESCRIPTION_MAP.put(LegendaryStone.Effect.TELEPORTATION, new String[] { "§rLorsque vous subissez des dégâts, vous avez §l10%§r de chance d'éviter le coup.", "§rLorsque vous subissez des dégâts, vous avez §l20%§r de chance d'éviter le coup." });
  }
  
  private static final TextInfo NAME_INFO = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 24.0F, Color.decode("#F6F9E8"));
  
  private static final TextInfo DESCRIPTION_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 14.0F, Color.WHITE);
  
  private static final Color GOLD_COLOR = Color.decode("#F99E37");
  
  private static final Resource FULL_TEXTURE = Resource.of(new ResourceLocation("palamod", "textures/gui/tooltip/ancient_armor/full.png")).linear();
  
  private static final Resource NOT_FULL_TEXTURE = Resource.of(new ResourceLocation("palamod", "textures/gui/tooltip/ancient_armor/not_full.png")).linear();
  
  private static final Resource ARMOR_TEXTURE = Resource.of(new ResourceLocation("palamod", "textures/gui/tooltip/ancient_armor/armor.png")).nearest();
  
  private static final Resource SECTION_TEXTURE = Resource.of(new ResourceLocation("palamod", "textures/gui/tooltip/ancient_armor/section.png")).linear();
  
  private static final Resource[] ARMOR_PIECE_TEXTURES = new Resource[] { Resource.of(new ResourceLocation("palamod", "textures/gui/tooltip/ancient_armor/armor/helmet.png")).nearest(), 
      Resource.of(new ResourceLocation("palamod", "textures/gui/tooltip/ancient_armor/armor/chestplate.png")).nearest(), 
      Resource.of(new ResourceLocation("palamod", "textures/gui/tooltip/ancient_armor/armor/leggings.png")).nearest(), 
      Resource.of(new ResourceLocation("palamod", "textures/gui/tooltip/ancient_armor/armor/boots.png")).nearest() };
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    size(getTooltipWidth(getItem()), getTooltipHeight(getItem()));
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    ItemStack itemStack = getItem();
    ItemAncientArmor item = (ItemAncientArmor)itemStack.func_77973_b();
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    String itemName = getItemName(itemStack);
    boolean full = ItemAncientArmor.isFull((EntityPlayer)entityClientPlayerMP);
    double width = getTooltipWidth(itemStack);
    double height = getTooltipHeight(itemStack);
    drawBackground(mouseX, mouseY, width, height);
    DrawUtils.ITEM.drawItem(mouseX + 26.0D, mouseY + 40.0D, 61.0D, itemStack, false);
    DrawUtils.TEXT.drawText(mouseX + 108.0D - 3.0D, mouseY + 40.0D, Text.create(itemName, NAME_INFO));
    double ox = mouseX + 108.0D;
    for (int i = 0; i < item.func_82812_d().func_78044_b(item.field_77881_a); i++) {
      DrawUtils.RESOURCE.drawImage(ox, mouseY + 40.0D + NAME_INFO.getHeight(), 15.0D, 14.0D, ARMOR_TEXTURE.textureCoords((i % 2 == 0) ? 0.0D : 7.5D, 0.0D, 7.5D, 14.0D));
      ox += (i % 2 == 0) ? 7.5D : 12.5D;
    } 
    if (full) {
      DrawUtils.SHAPE.drawRect(mouseX + 26.0D + width - 52.0D - 83.0D, mouseY + 40.0D + 30.5D - 19.5D, 83.0D, 39.0D, GOLD_COLOR);
      DrawUtils.SHAPE.drawRect(mouseX + 26.0D + width - 52.0D - 83.0D, mouseY + 40.0D + 30.5D - 19.5D + 39.0D, 83.0D, 4.0D, Color.decode("#F96B37"));
      DrawUtils.TEXT.drawText(mouseX + 26.0D + width - 52.0D - 41.5D, mouseY + 40.0D + 30.5D, Text.create("FULL", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 15.0F, Color.WHITE)).align(Align.CENTER, Align.CENTER));
    } 
    double topMargin = 126.0D;
    DrawUtils.TEXT.drawText(mouseX + 26.0D, mouseY + topMargin, width - 52.0D, 0.0D, Text.create("Chaque pièce d'armure antique peut être fusionnée avec une Legendary Stone dans le Grinder. Cette fusion confère un effet unique, amplifié lorsque l'ensemble de l'armure est complet.", DESCRIPTION_INFO), TextMode.SPLIT);
    topMargin += getDescriptionHeight(itemStack) + 10.0D;
    boolean hasEffect = false;
    for (int j = 0; j < ((EntityPlayer)entityClientPlayerMP).field_71071_by.field_70460_b.length; j++) {
      ItemStack armor = (3 - j == item.field_77881_a) ? itemStack : ((EntityPlayer)entityClientPlayerMP).field_71071_by.field_70460_b[j];
      if (armor != null && armor.func_77973_b() instanceof ItemAncientArmor && ItemAncientArmor.getEffect(armor) != null) {
        hasEffect = true;
        break;
      } 
    } 
    if (hasEffect) {
      DrawUtils.SHAPE.drawRect(mouseX + 26.0D, mouseY + topMargin, width - 52.0D, 4.0D, Color.decode("#40404D").copyAlpha(0.5F));
      topMargin += 16.0D;
      double sectionHeight = 46.0D;
      LegendaryStone.Effect[] effects = new LegendaryStone.Effect[((EntityPlayer)entityClientPlayerMP).field_71071_by.field_70460_b.length];
      for (int k = 0; k < ((EntityPlayer)entityClientPlayerMP).field_71071_by.field_70460_b.length; k++) {
        ItemStack armor = (3 - k == item.field_77881_a) ? itemStack : ((EntityPlayer)entityClientPlayerMP).field_71071_by.field_70460_b[k];
        if (armor != null && armor.func_77973_b() instanceof ItemAncientArmor) {
          LegendaryStone.Effect effect = ItemAncientArmor.getEffect(armor);
          effects[3 - k] = effect;
          if (effect != null)
            sectionHeight += 68.0D; 
        } else {
          effects[3 - k] = null;
        } 
      } 
      if (full)
        sectionHeight += 48.0D; 
      if (full) {
        DrawUtils.RESOURCE.drawImage(mouseX + 26.0D, mouseY + topMargin, width - 52.0D, sectionHeight, SECTION_TEXTURE);
      } else {
        DrawUtils.SHAPE.drawRect(mouseX + 26.0D, mouseY + topMargin, width - 52.0D, sectionHeight, Color.decode("#40404D").copyAlpha(0.5F));
      } 
      DrawUtils.TEXT.drawText(mouseX + 26.0D + 17.0D, mouseY + topMargin + 14.0D, Text.create(full ? "Armure complète" : "Armure incomplète", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 14.0F, Color.WHITE)));
      double d1 = mouseX + 26.0D + width - 52.0D - 14.0D - 81.0D;
      double oy = mouseY + topMargin + 11.0D;
      double size = 18.0D;
      if (full)
        DrawUtils.SHAPE.drawFilledBorder(d1 - 1.0D, oy - 1.0D, d1 + 81.0D + 1.0D, oy + size + 3.0D, GOLD_COLOR, 1.0D); 
      int m;
      for (m = 0; m < ((EntityPlayer)entityClientPlayerMP).field_71071_by.field_70460_b.length; m++) {
        boolean isEquipped = (((EntityPlayer)entityClientPlayerMP).field_71071_by.field_70460_b[3 - m] != null && ((EntityPlayer)entityClientPlayerMP).field_71071_by.field_70460_b[3 - m].func_77973_b() instanceof ItemAncientArmor);
        DrawUtils.SHAPE.drawRect(d1, oy, size, size, isEquipped ? GOLD_COLOR : Color.decode("#2A292A"));
        DrawUtils.SHAPE.drawRect(d1, oy + size, size, 2.0D, isEquipped ? Color.decode("#F96B37") : Color.decode("#1B1B1B"));
        if (!isEquipped)
          Color.decode("#171717").bind(); 
        DrawUtils.RESOURCE.drawImage(d1 + size / 2.0D - 5.5D, oy + size / 2.0D - 5.5D, 11.0D, 11.0D, ARMOR_PIECE_TEXTURES[m]);
        Color.WHITE.bind();
        d1 += size + 3.0D;
      } 
      d1 = mouseX + 26.0D + 17.0D;
      oy = mouseY + topMargin + 14.0D + 17.0D + 15.0D;
      size = 34.0D;
      for (m = 0; m < effects.length; m++) {
        LegendaryStone.Effect effect = effects[m];
        if (effect != null) {
          DrawUtils.SHAPE.drawRect(d1, oy, size, size, full ? Color.decode("#4A2905") : Color.decode("#171717"));
          DrawUtils.ITEM.drawItem(d1 + size / 2.0D - 14.0D, oy + size / 2.0D - 14.0D, 28.0D, effect.getItem());
          GL11.glTranslated(0.0D, 0.0D, 1.0D);
          DrawUtils.RESOURCE.drawImage(d1 + size - 11.0D - 2.0D, oy + size - 11.0D - 2.0D, 11.0D, 11.0D, ARMOR_PIECE_TEXTURES[m]);
          GL11.glTranslated(0.0D, 0.0D, -1.0D);
          Map.Entry<String, Color> nameEntry = NAME_MAP.get(effect);
          DrawUtils.TEXT.drawText(d1 + size + 10.0D, oy, Text.create(nameEntry.getKey(), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 14.0F, nameEntry.getValue()).shadow(((Color)nameEntry.getValue()).darker(0.6F)).shadow(0.0F, 1.0F)));
          DrawUtils.TEXT.drawText(d1 + size + 10.0D, oy + 18.0D, mouseX + 26.0D + width - 52.0D - 13.0D - d1 + size + 10.0D, 0.0D, Text.create(((String[])DESCRIPTION_MAP.get(effect))[full ? 1 : 0], TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 10.0F, Color.WHITE)), TextMode.SPLIT);
          oy += size * 2.0D;
        } 
      } 
      oy -= size;
      if (full) {
        DrawUtils.SHAPE.drawFilledBorder(d1, oy + 36.0D, mouseX + 26.0D + width - 52.0D - 13.0D, oy + 36.0D + 22.0D, GOLD_COLOR, 1.0D);
        DrawUtils.TEXT.drawText(mouseX + width / 2.0D, oy + 36.0D + 11.0D, Text.create("Vol actif dans ses claims si aucun ennemi n'est présent à moins de 20 blocs.", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 10.0F, Color.WHITE)).align(Align.CENTER, Align.CENTER));
      } 
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
    boolean full = ItemAncientArmor.isFull((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
    GL11.glEnable(2960);
    GL11.glStencilFunc(519, 1, 255);
    GL11.glStencilOp(7681, 7681, 7681);
    GL11.glColorMask(true, true, true, true);
    DrawUtils.SHAPE.drawRect(x, y, width, height, backgroundColor);
    DrawUtils.SHAPE.drawBorder(x, y, width + x, height + y, backgroundColor, 10.0D);
    GL11.glStencilFunc(514, 1, 255);
    GL11.glStencilOp(7680, 7680, 7680);
    DrawUtils.RESOURCE.drawScaledImageWidth(x - 10.0D, y - 10.0D, width + 20.0D, full ? FULL_TEXTURE : NOT_FULL_TEXTURE);
    GL11.glDisable(2960);
    GL11.glClear(1024);
    if (full) {
      DrawUtils.SHAPE.drawRect(x + 8.0D, y, (width - 16.0D) / 2.0D, 8.0D, GOLD_COLOR.toGradient(Color.decode("#FFCB24"), new Vector4f(0.0F, 0.0F, 1.0F, 0.0F)));
      DrawUtils.SHAPE.drawRect(x + 8.0D + (width - 16.0D) / 2.0D, y, (width - 16.0D) / 2.0D, 8.0D, GOLD_COLOR.toGradient(Color.decode("#FFCB24"), new Vector4f(1.0F, 0.0F, 0.0F, 0.0F)));
    } else {
      DrawUtils.SHAPE.drawRect(x + 8.0D, y, width - 16.0D, 8.0D, GOLD_COLOR);
    } 
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
    return DrawUtils.TEXT.getLines(getTooltipWidth(itemStack) - 52.0D, "Chaque pièce d'armure antique peut être fusionnée avec une Legendary Stone dans le Grinder. Cette fusion confère un effet unique, amplifié lorsque l'ensemble de l'armure est complet.", DESCRIPTION_INFO).size() * DESCRIPTION_INFO.getHeight();
  }
  
  public static double getTooltipWidth(@NonNull ItemStack itemStack) {
    if (itemStack == null)
      throw new NullPointerException("itemStack is marked non-null but is null"); 
    return 108.0D + Math.max(NAME_INFO.getWidth(getItemName(itemStack)), 120.0D) + 150.0D;
  }
  
  public static double getTooltipHeight(@NonNull ItemStack itemStack) {
    if (itemStack == null)
      throw new NullPointerException("itemStack is marked non-null but is null"); 
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    ItemAncientArmor item = (ItemAncientArmor)itemStack.func_77973_b();
    boolean full = ItemAncientArmor.isFull((EntityPlayer)entityClientPlayerMP);
    double topMargin = 126.0D;
    topMargin += getDescriptionHeight(itemStack) + 10.0D;
    boolean hasEffect = false;
    for (int i = 0; i < ((EntityPlayer)entityClientPlayerMP).field_71071_by.field_70460_b.length; i++) {
      ItemStack armor = (3 - i == item.field_77881_a) ? itemStack : ((EntityPlayer)entityClientPlayerMP).field_71071_by.field_70460_b[i];
      if (armor != null && armor.func_77973_b() instanceof ItemAncientArmor && ItemAncientArmor.getEffect(armor) != null) {
        hasEffect = true;
        break;
      } 
    } 
    if (hasEffect) {
      topMargin += 16.0D;
      double sectionHeight = 46.0D;
      LegendaryStone.Effect[] effects = new LegendaryStone.Effect[4];
      for (int j = 0; j < ((EntityPlayer)entityClientPlayerMP).field_71071_by.field_70460_b.length; j++) {
        ItemStack armor = (3 - j == item.field_77881_a) ? itemStack : ((EntityPlayer)entityClientPlayerMP).field_71071_by.field_70460_b[j];
        if (armor != null && armor.func_77973_b() instanceof ItemAncientArmor) {
          LegendaryStone.Effect effect = ItemAncientArmor.getEffect(armor);
          effects[3 - j] = effect;
          if (effect != null)
            sectionHeight += 68.0D; 
        } else {
          effects[3 - j] = null;
        } 
      } 
      if (full)
        sectionHeight += 48.0D; 
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\ItemAncientArmorTooltipNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */