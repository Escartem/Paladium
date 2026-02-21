package fr.paladium.palarpg.module.equipment.client.renderer;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palaforgeutils.lib.tooltip.CustomTooltipNode;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.equipment.client.constant.RPGItemRarityConstant;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGArmorType;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemType;
import fr.paladium.palarpg.module.equipment.common.item.RPGStatApplyType;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemArmor;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemConsumable;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemRune;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemSword;
import fr.paladium.palarpg.module.equipment.common.loader.cache.RPGEquipmentSetCache;
import fr.paladium.palarpg.module.equipment.common.loader.cache.RPGItemsCache;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGEquipmentSetData;
import fr.paladium.palarpg.module.equipment.common.loader.util.ItemStatMutator;
import fr.paladium.palarpg.module.equipment.common.manager.EquipmentRuneManager;
import fr.paladium.palarpg.module.equipment.common.manager.EquipmentSetManager;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import fr.paladium.palarpg.module.stat.client.constant.RPGStatConstant;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.AStatCapability;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatCapabilityMutator;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.TimedStatCapabilityMutator;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.Timeline;
import fr.paladium.zephyrui.lib.animation.tweenengine.Tween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.TextElement;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.opengl.GLHelper;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javax.vecmath.Vector4f;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RPGTooltipNode extends CustomTooltipNode {
  private static final TextInfo ITEM_INFO = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 24.0F, Color.decode("#F6F9E8"));
  
  private static final TextInfo LEVEL_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_REGULAR, 20.0F, Color.decode("#F6F9E8"));
  
  private static final TextInfo LEVEL_INFO_BOLD = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 20.0F, Color.decode("#F6F9E8"));
  
  private static final TextInfo DESC_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 14.0F, Color.WHITE);
  
  private static final TextInfo SET_INFO_BOLD = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 14.0F, Color.WHITE);
  
  private static final TextInfo SET_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 14.0F, Color.WHITE);
  
  private static final TextInfo STAT_VALUE_INFO = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 17.0F, Color.WHITE);
  
  private static final TextInfo STAT_TEXT_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 17.0F, Color.WHITE);
  
  private static final TextInfo DURABILITY_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 14.0F, Color.decode("#707070"));
  
  private static final Color BACKGROUND_COLOR = Color.decode("#0E0E0E");
  
  private static final Color SECTION_BG_COLOR = Color.decode("#171717");
  
  private static final Color BORDER_COLOR = Color.decode("#3A3B3C");
  
  private static final Color SET_PIECE_BG = Color.decode("#2A292A");
  
  private static final Color SET_PIECE_BG_SHADOW = Color.decode("#1B1B1B");
  
  private static final Color SET_PIECE_BG_ACTIVE = Color.decode("#BB37F9");
  
  private static final Color SET_PIECE_BG_SHADOW_ACTIVE = Color.decode("#5C16D2");
  
  private static final List<RPGArmorType> ARMOR_TYPES = Arrays.asList(RPGArmorType.values);
  
  private static final double MAX_SECTION_HEIGHT = 170.0D;
  
  private static final TweenAnimator SET_ANIMATOR = TweenAnimator.create(0.0F);
  
  private static final TweenAnimator RUNE_STAT_ANIMATOR = TweenAnimator.create(0.0F);
  
  private static final TweenAnimator STAT_ANIMATOR = TweenAnimator.create(0.0F);
  
  private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#");
  
  private static ItemStack lastItem = null;
  
  static {
    Collections.reverse(ARMOR_TYPES);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    size(getTooltipWidth(getItem()), getTooltipHeight(getItem()));
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    ItemStack itemStack = getItem();
    IRPGItem rpgItem = IRPGItem.get(itemStack).get();
    String itemName = getItemName(itemStack);
    double width = getTooltipWidth(itemStack);
    double height = getTooltipHeight(itemStack);
    RPGStatPlayerData statPlayer = (RPGStatPlayerData)RPGPlayer.getData("stats");
    RPGProfilePlayerData profile = (RPGProfilePlayerData)RPGPlayer.getData("profile");
    GL11.glEnable(2960);
    GL11.glStencilFunc(519, 1, 255);
    GL11.glStencilOp(7681, 7681, 7681);
    GL11.glColorMask(true, true, true, true);
    drawBackground(mouseX, mouseY, width, height);
    GL11.glStencilFunc(514, 1, 255);
    GL11.glStencilOp(7680, 7680, 7680);
    GL11.glAlphaFunc(516, 0.0F);
    GL11.glTranslated(mouseX - 10.0D, mouseY - 10.0D, 0.0D);
    DrawUtils.SHAPE.drawRect(0.0D, 0.0D, width + 20.0D, 200.0D, Color.gradient(RPGItemRarityConstant.getColor(rpgItem).copyAlpha(0.3F), RPGItemRarityConstant.getColor(rpgItem).copyAlpha(0.0F), new Vector4f(0.0F, 0.0F, 0.0F, 1.0F)));
    GL11.glTranslated(-(mouseX - 10.0D), -(mouseY - 10.0D), 0.0D);
    GL11.glAlphaFunc(516, 0.1F);
    GL11.glDisable(2960);
    GL11.glClear(1024);
    Color levelColor = Color.WHITE;
    int requiredLevel = (rpgItem.getItemData() != null) ? rpgItem.getItemData().getRequiredLevel() : 0;
    if (profile == null || profile.getLevel() < requiredLevel)
      levelColor = Color.RED; 
    drawRarityIcon(mouseX + width / 2.0D - 35.0D, mouseY - 32.5D, rpgItem);
    drawRarityLabel(mouseX + width - 117.0D - 29.0D, mouseY + 40.0D + 30.5D - 21.5D + 2.0D, rpgItem);
    if (requiredLevel > 0.0D) {
      DrawUtils.TEXT.drawText(mouseX + 108.0D, mouseY + 40.0D, Text.create("LV. ", LEVEL_INFO.copy().color(levelColor)).add(Text.create(Integer.valueOf(requiredLevel), LEVEL_INFO_BOLD.copy().color(levelColor))).add(Text.create(" RPG REQUIS", LEVEL_INFO.copy().color(levelColor))));
      DrawUtils.TEXT.drawText(mouseX + 108.0D, mouseY + 64.0D, Text.create(itemName, ITEM_INFO));
    } else {
      DrawUtils.TEXT.drawText(mouseX + 108.0D, mouseY + 40.0D + 30.5D, Text.create(itemName, ITEM_INFO).verticalAlign(Align.CENTER));
    } 
    DrawUtils.ITEM.drawItem(mouseX + 26.0D, mouseY + 40.0D, 61.0D, itemStack, false);
    double topMargin = 126.0D;
    if (rpgItem.getItemData() != null && rpgItem.getItemData().hasDescription()) {
      String description = rpgItem.getItemData().getDescription().trim();
      if (!description.endsWith("."))
        description = description + "."; 
      DrawUtils.TEXT.drawText(mouseX + 26.0D, mouseY + topMargin, width - 52.0D, 0.0D, Text.create(description, DESC_INFO.copy()), TextMode.SPLIT);
      topMargin += getDescriptionHeight(itemStack) + 17.0D;
    } 
    List<ItemStatMutator> mutators = getStats(itemStack);
    if (!mutators.isEmpty()) {
      double sectionHeight = 10.0D + SET_INFO_BOLD.getHeight() + 10.0D + (SET_INFO.getHeight() + 10.0D) * mutators.size();
      sectionHeight = Math.min(sectionHeight, 170.0D);
      DrawUtils.SHAPE.drawRect(mouseX + 26.0D, mouseY + topMargin, width - 52.0D, sectionHeight, SECTION_BG_COLOR);
      DrawUtils.TEXT.drawText(mouseX + 26.0D + 10.0D, mouseY + topMargin + 10.0D, Text.create("Statistiques", SET_INFO_BOLD));
      GL11.glEnable(2960);
      GL11.glStencilFunc(514, 0, 255);
      GL11.glStencilOp(7680, 7680, 7682);
      GL11.glColorMask(false, false, false, false);
      DrawUtils.SHAPE.drawRect(mouseX + 26.0D, mouseY + topMargin + SET_INFO_BOLD.getHeight() + 20.0D, width - 52.0D, sectionHeight - SET_INFO_BOLD.getHeight() - 20.0D, Color.WHITE);
      GL11.glColorMask(true, true, true, true);
      GL11.glStencilFunc(514, 1, 255);
      GL11.glStencilOp(7680, 7680, 7680);
      int offNumStat = mutators.size() - 5;
      if (offNumStat > 0) {
        checkStatAnimator(itemStack, offNumStat);
        GL11.glTranslated(0.0D, -((offNumStat * 22.0D + (offNumStat - 1.0D) * 8.0D) * STAT_ANIMATOR.getValue()), 0.0D);
      } 
      double yOffset = 10.0D;
      for (ItemStatMutator mut : mutators) {
        yOffset += SET_INFO.getHeight() + 10.0D;
        drawStatString(mouseX + 26.0D + 10.0D, mouseY + topMargin + yOffset, statPlayer, mut, true);
      } 
      if (offNumStat > 0)
        GL11.glTranslated(0.0D, (offNumStat * 22.0D + (offNumStat - 1.0D) * 8.0D) * STAT_ANIMATOR.getValue(), 0.0D); 
      GL11.glDisable(2960);
      GL11.glClear(1024);
      topMargin += sectionHeight + 17.0D;
    } 
    int runeCount = getRuneSlotCount(rpgItem);
    if (runeCount > 0) {
      double margin = (width - 52.0D - runeCount * 47.0D) / (runeCount - 1);
      for (int i = 0; i < runeCount; i++) {
        double runeX = i * margin + i * 47.0D;
        DrawUtils.SHAPE.drawRect(mouseX + 26.0D + runeX, mouseY + topMargin, 47.0D, 47.0D, SECTION_BG_COLOR);
        if (itemStack.func_77942_o() && itemStack.func_77978_p().func_74764_b("rune_" + i)) {
          RPGItemRune runeItem = EquipmentRuneManager.getRuneItemByTier(itemStack.func_77978_p().func_74775_l("rune_" + i).func_74762_e("tier"));
          DrawUtils.ITEM.drawItem(mouseX + 26.0D + runeX + 3.525D, mouseY + topMargin + 3.525D, 39.949999999999996D, (Item)runeItem);
        } else {
          Resource emptyRuneIcon = Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/items/rune/rune_placeholder.png")).nearest();
          DrawUtils.RESOURCE.drawImage(mouseX + 26.0D + runeX + 3.525D, mouseY + topMargin + 3.525D, 39.949999999999996D, 39.949999999999996D, emptyRuneIcon);
        } 
      } 
      topMargin += 64.0D;
    } 
    if (!EquipmentRuneManager.isRune(itemStack)) {
      List<ItemStatMutator> list = EquipmentRuneManager.getRuneStats(itemStack);
      if (!list.isEmpty()) {
        double sectionHeight = 10.0D + SET_INFO_BOLD.getHeight() + 10.0D + (SET_INFO.getHeight() + 10.0D) * list.size();
        sectionHeight = Math.min(sectionHeight, 118.0D);
        DrawUtils.SHAPE.drawRect(mouseX + 26.0D, mouseY + topMargin, width - 52.0D, sectionHeight, SECTION_BG_COLOR);
        DrawUtils.TEXT.drawText(mouseX + 26.0D + 10.0D, mouseY + topMargin + 10.0D, Text.create("Runes", SET_INFO_BOLD));
        GL11.glEnable(2960);
        GL11.glStencilFunc(514, 0, 255);
        GL11.glStencilOp(7680, 7680, 7682);
        GL11.glColorMask(false, false, false, false);
        DrawUtils.SHAPE.drawRect(mouseX + 26.0D, mouseY + topMargin + SET_INFO_BOLD.getHeight() + 20.0D, width - 52.0D, sectionHeight - SET_INFO_BOLD.getHeight() - 20.0D, Color.WHITE);
        GL11.glColorMask(true, true, true, true);
        GL11.glStencilFunc(514, 1, 255);
        GL11.glStencilOp(7680, 7680, 7680);
        int offNumStat = list.size() - 3;
        if (offNumStat > 0) {
          checkRuneStatAnimator(itemStack, offNumStat);
          GL11.glTranslated(0.0D, -((offNumStat * 22.0D + (offNumStat - 1) * 8.0D) * RUNE_STAT_ANIMATOR.getValue()), 0.0D);
        } 
        double yOffset = 10.0D;
        for (ItemStatMutator mut : list) {
          yOffset += SET_INFO.getHeight() + 10.0D;
          drawStatString(mouseX + 26.0D + 10.0D, mouseY + topMargin + yOffset, statPlayer, mut, true);
        } 
        if (offNumStat > 0)
          GL11.glTranslated(0.0D, (offNumStat * 22.0D + (offNumStat - 1) * 8.0D) * RUNE_STAT_ANIMATOR.getValue(), 0.0D); 
        GL11.glDisable(2960);
        GL11.glClear(1024);
        topMargin += sectionHeight + 17.0D;
      } 
    } 
    Optional<RPGEquipmentSetData> optSetData = getSet(itemStack);
    if (optSetData.isPresent()) {
      AtomicInteger numStat = new AtomicInteger(0);
      RPGEquipmentSetData set = optSetData.get();
      set.getMutators().forEach((t, mut) -> numStat.addAndGet(mut.size()));
      double sectionHeight = 10.0D + SET_INFO_BOLD.getHeight() + 10.0D + (SET_INFO.getHeight() + 10.0D) * numStat.get();
      sectionHeight = Math.min(sectionHeight, 170.0D);
      DrawUtils.SHAPE.drawRect(mouseX + 26.0D, mouseY + topMargin, width - 52.0D, sectionHeight, SECTION_BG_COLOR);
      DrawUtils.TEXT.drawText(mouseX + 26.0D + 10.0D, mouseY + topMargin + 10.0D, Text.create("Panoplie " + set.getName(), SET_INFO_BOLD));
      double xOffset = 5.0D;
      double size = 25.0D;
      double margin = 3.0D;
      if (RPGItemsCache.getItems(RPGItemType.SWORD).stream().filter(item -> (((RPGItemSword)item).getItemData().getSet() != null && ((RPGItemSword)item).getItemData().getSet().equalsIgnoreCase(set.getId()))).findFirst().isPresent()) {
        ItemStack heldItem = (Minecraft.func_71410_x()).field_71439_g.func_70694_bm();
        Optional<IRPGItem> optRpgItem = IRPGItem.get(heldItem);
        boolean haveSetInHand = false;
        if (optRpgItem.isPresent() && optRpgItem.get() instanceof RPGItemSword) {
          RPGItemSword sword = (RPGItemSword)optRpgItem.get();
          if (sword.getItemData().getSet() != null && sword.getItemData().getSet().equalsIgnoreCase(set.getId()))
            haveSetInHand = true; 
        } 
        Resource swordIcon = Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/equipment/tooltip/set_icon/sword.png")).nearest();
        DrawUtils.SHAPE.drawRect(mouseX + width - 52.0D - xOffset, mouseY + topMargin + 10.0D - 3.0D, 25.0D, 25.0D, haveSetInHand ? SET_PIECE_BG_ACTIVE : SET_PIECE_BG);
        if (!haveSetInHand)
          GLHelper.color(SECTION_BG_COLOR); 
        DrawUtils.RESOURCE.drawImage(mouseX + width - 52.0D - xOffset + 1.0D, mouseY + topMargin + 10.0D - 3.0D, 25.0D, 25.0D, swordIcon);
        GLHelper.popColor();
        DrawUtils.SHAPE.drawRect(mouseX + width - 52.0D - xOffset, mouseY + topMargin + 10.0D + 25.0D - 3.0D, 25.0D, 2.0D, haveSetInHand ? SET_PIECE_BG_SHADOW_ACTIVE : SET_PIECE_BG_SHADOW);
        xOffset += 28.0D;
      } 
      List<RPGItemArmor> armors = (List<RPGItemArmor>)RPGItemsCache.getItems(RPGItemType.ARMOR).stream().filter(item -> (((RPGItemArmor)item).getArmor().getSet() != null && ((RPGItemArmor)item).getArmor().getSet().equalsIgnoreCase(set.getId()))).map(item -> (RPGItemArmor)item).collect(Collectors.toList());
      for (RPGArmorType armorType : ARMOR_TYPES) {
        boolean hasArmor = armors.stream().filter(armor -> (armor.getType() == armorType)).findFirst().isPresent();
        if (hasArmor) {
          ItemStack armor = (Minecraft.func_71410_x()).field_71439_g.field_71071_by.func_70440_f(3 - armorType.ordinal());
          Optional<IRPGItem> optRpgItem = IRPGItem.get(armor);
          boolean haveSetEquipped = false;
          if (optRpgItem.isPresent() && optRpgItem.get() instanceof RPGItemArmor) {
            RPGItemArmor armorItem = (RPGItemArmor)optRpgItem.get();
            if (armorItem.getArmor().getSet() != null && armorItem.getArmor().getSet().equalsIgnoreCase(set.getId()))
              haveSetEquipped = true; 
          } 
          Resource armorIcon = Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/equipment/tooltip/set_icon/" + armorType.name().toLowerCase() + ".png")).nearest();
          DrawUtils.SHAPE.drawRect(mouseX + width - 52.0D - xOffset, mouseY + topMargin + 10.0D - 3.0D, 25.0D, 25.0D, haveSetEquipped ? SET_PIECE_BG_ACTIVE : SET_PIECE_BG);
          if (!haveSetEquipped)
            GLHelper.color(SECTION_BG_COLOR); 
          DrawUtils.RESOURCE.drawImage(mouseX + width - 52.0D - xOffset + 1.0D, mouseY + topMargin + 10.0D - 3.0D, 25.0D, 25.0D, armorIcon);
          GLHelper.popColor();
          DrawUtils.SHAPE.drawRect(mouseX + width - 52.0D - xOffset, mouseY + topMargin + 10.0D + 25.0D - 3.0D, 25.0D, 2.0D, haveSetEquipped ? SET_PIECE_BG_SHADOW_ACTIVE : SET_PIECE_BG_SHADOW);
          xOffset += 28.0D;
        } 
      } 
      GL11.glEnable(2960);
      GL11.glStencilFunc(514, 0, 255);
      GL11.glStencilOp(7680, 7680, 7682);
      GL11.glColorMask(false, false, false, false);
      DrawUtils.SHAPE.drawRect(mouseX + 26.0D, mouseY + topMargin + SET_INFO_BOLD.getHeight() + 20.0D, width - 52.0D, sectionHeight - SET_INFO_BOLD.getHeight() - 20.0D, Color.WHITE);
      GL11.glColorMask(true, true, true, true);
      GL11.glStencilFunc(514, 1, 255);
      GL11.glStencilOp(7680, 7680, 7680);
      int offNumStat = numStat.get() - 5;
      if (offNumStat > 0) {
        checkSetAnimator(itemStack, offNumStat);
        GL11.glTranslated(0.0D, -((offNumStat * 22.0D + (offNumStat - 1.0D) * 8.0D) * SET_ANIMATOR.getValue()), 0.0D);
      } 
      Map<Integer, List<ItemStatMutator>> statsPerPiece = set.getMutators();
      int pieceCount = EquipmentSetManager.getSetPieceCount((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g, set.getId());
      double yOffset = 10.0D;
      for (Map.Entry<Integer, List<ItemStatMutator>> entry : statsPerPiece.entrySet()) {
        for (ItemStatMutator mut : entry.getValue()) {
          yOffset += SET_INFO.getHeight() + 10.0D;
          drawStatString(mouseX + 26.0D + 10.0D, mouseY + topMargin + yOffset, statPlayer, mut, (pieceCount >= ((Integer)entry.getKey()).intValue()));
          Resource pieceIcon = Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/equipment/icon_piece_active.png"));
          Resource pieceIconInactive = Resource.of((ResourceLocation)MCResource.of("palarpg", "textures/equipment/icon_piece_inactive.png"));
          for (int y = 0; y < ((Integer)entry.getKey()).intValue(); y++)
            DrawUtils.RESOURCE.drawScaledImageWidth(mouseX + width - 52.0D - y * 5.0D, mouseY + topMargin + yOffset + 1.0D, 20.0D, (y < pieceCount) ? pieceIcon : pieceIconInactive); 
        } 
      } 
      if (offNumStat > 0)
        GL11.glTranslated(0.0D, (offNumStat * 22.0D + (offNumStat - 1.0D) * 8.0D) * SET_ANIMATOR.getValue(), 0.0D); 
      GL11.glDisable(2960);
      GL11.glClear(1024);
      topMargin += sectionHeight + 17.0D;
    } 
    if ((Minecraft.func_71410_x()).field_71474_y.field_82882_x && itemStack.func_77984_f()) {
      DrawUtils.TEXT.drawText(mouseX + 26.0D + 14.0D, mouseY + topMargin, Text.create("Durability:", DURABILITY_INFO));
      DrawUtils.TEXT.drawText(mouseX + width - 26.0D - 14.0D, mouseY + topMargin, Text.create((itemStack.func_77958_k() - itemStack.func_77960_j()) + "/" + itemStack.func_77958_k(), DURABILITY_INFO).horizontalAlign(Align.END));
    } 
    lastItem = itemStack;
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
  
  private static void checkStatAnimator(ItemStack itemStack, int statCount) {
    if (lastItem != itemStack) {
      STAT_ANIMATOR.clear();
      Timeline timeline = Timeline.createSequence();
      timeline.pushPause(1000.0F);
      timeline.push(Tween.to(STAT_ANIMATOR, 0, statCount * 750.0F).target(1.0F).ease((TweenEquation)TweenEquations.LINEAR));
      timeline.repeatYoyo(-1, 1000.0F);
      STAT_ANIMATOR.setTimeline(timeline);
      STAT_ANIMATOR.start();
    } 
    STAT_ANIMATOR.update();
  }
  
  private static void checkSetAnimator(ItemStack itemStack, int statCount) {
    if (lastItem != itemStack) {
      SET_ANIMATOR.clear();
      Timeline timeline = Timeline.createSequence();
      timeline.pushPause(1000.0F);
      timeline.push(Tween.to(SET_ANIMATOR, 0, statCount * 750.0F).target(1.0F).ease((TweenEquation)TweenEquations.LINEAR));
      timeline.repeatYoyo(-1, 1000.0F);
      SET_ANIMATOR.setTimeline(timeline);
      SET_ANIMATOR.start();
    } 
    SET_ANIMATOR.update();
  }
  
  private static void checkRuneStatAnimator(ItemStack itemStack, int statCount) {
    if (lastItem != itemStack) {
      RUNE_STAT_ANIMATOR.clear();
      Timeline timeline = Timeline.createSequence();
      timeline.pushPause(1000.0F);
      timeline.push(Tween.to(RUNE_STAT_ANIMATOR, 0, statCount * 750.0F).target(1.0F).ease((TweenEquation)TweenEquations.LINEAR));
      timeline.repeatYoyo(-1, 1000.0F);
      RUNE_STAT_ANIMATOR.setTimeline(timeline);
      RUNE_STAT_ANIMATOR.start();
    } 
    RUNE_STAT_ANIMATOR.update();
  }
  
  private static void drawBackground(double x, double y, double width, double height) {
    DrawUtils.SHAPE.drawRect(x, y, width, height, BACKGROUND_COLOR);
    DrawUtils.SHAPE.drawBorder(x, y, width + x, height + y, BACKGROUND_COLOR, 10.0D);
    double borderWidth = 8.0D;
    double littleBorderSize = 11.0D;
    DrawUtils.SHAPE.drawRect(x + 8.0D, y, 11.0D, 8.0D, BORDER_COLOR);
    DrawUtils.SHAPE.drawRect(x, y + 8.0D, 8.0D, 11.0D, BORDER_COLOR);
    DrawUtils.SHAPE.drawRect(x + width - 11.0D - 8.0D, y, 11.0D, 8.0D, BORDER_COLOR);
    DrawUtils.SHAPE.drawRect(x + width - 8.0D, y + 8.0D, 8.0D, 11.0D, BORDER_COLOR);
    DrawUtils.SHAPE.drawRect(x + 8.0D, y + height - 8.0D, 11.0D, 8.0D, BORDER_COLOR);
    DrawUtils.SHAPE.drawRect(x, y + height - 8.0D - 11.0D, 8.0D, 11.0D, BORDER_COLOR);
    DrawUtils.SHAPE.drawRect(x + width - 11.0D - 8.0D, y + height - 8.0D, 11.0D, 8.0D, BORDER_COLOR);
    DrawUtils.SHAPE.drawRect(x + width - 8.0D, y + height - 8.0D - 11.0D, 8.0D, 11.0D, BORDER_COLOR);
    DrawUtils.SHAPE.drawRect(x + 8.0D + 22.0D, y, width - 44.0D - 16.0D, 8.0D, BORDER_COLOR);
    DrawUtils.SHAPE.drawRect(x + 8.0D + 22.0D, y + height - 8.0D, width - 44.0D - 16.0D, 8.0D, BORDER_COLOR);
    DrawUtils.SHAPE.drawRect(x, y + 8.0D + 22.0D, 8.0D, height - 44.0D - 16.0D, BORDER_COLOR);
    DrawUtils.SHAPE.drawRect(x + width - 8.0D, y + 8.0D + 22.0D, 8.0D, height - 44.0D - 16.0D, BORDER_COLOR);
  }
  
  private static void drawRarityIcon(double x, double y, IRPGItem rpgItem) {
    DrawUtils.RESOURCE.drawCenteredImage(x, y, 70.0D, 70.0D, RPGItemRarityConstant.getIcon(rpgItem));
  }
  
  private static void drawRarityLabel(double x, double y, IRPGItem rpgItem) {
    double width = 117.0D;
    Resource tag = RPGItemRarityConstant.getTooltipTag(rpgItem);
    DrawUtils.RESOURCE.drawScaledImageWidth(x, y, 117.0D, tag);
  }
  
  private static void drawStatString(double x, double y, @NonNull RPGStatPlayerData statPlayer, @NonNull ItemStatMutator mut, boolean active) {
    if (statPlayer == null)
      throw new NullPointerException("statPlayer is marked non-null but is null"); 
    if (mut == null)
      throw new NullPointerException("mut is marked non-null but is null"); 
    AStatCapability<Object> capa = statPlayer.getCapabilityByName(mut.getStatName());
    if (capa == null)
      return; 
    StatCapabilityMutator<Object> mutator = mut.getMutator("fake");
    if (mutator == null)
      return; 
    TextElement valueElem = TextElement.create(capa.displayMutator(mutator), STAT_VALUE_INFO.copy().color(active ? RPGStatConstant.getColor(mut.getStatName()) : Color.GRAY));
    TextElement textElem = TextElement.create(" " + mut.getStatName().getFormattedStatName(), STAT_TEXT_INFO.copy().color(active ? Color.WHITE : Color.GRAY)).modifier(TextModifier.UPPER_CASE);
    Text statText = Text.create(new TextElement[] { valueElem }).add(textElem).verticalAlign(Align.CENTER);
    if (mutator instanceof TimedStatCapabilityMutator) {
      TimedStatCapabilityMutator<Object> timed = (TimedStatCapabilityMutator<Object>)mutator;
      statText.add(TextElement.create(" [" + DECIMAL_FORMAT.format((timed.getTick() / 20.0F)) + "s]", STAT_TEXT_INFO.copy().color(Color.GRAY)));
    } 
    DrawUtils.RESOURCE.drawScaledImageWidth(x, y + 1.0D, 20.0D, active ? RPGStatConstant.getIcon(mut.getStatName()) : RPGStatConstant.getGrayscaleIcon(mut.getStatName()));
    DrawUtils.TEXT.drawText(x + 30.0D, y + 10.0D, statText);
  }
  
  private static double getDescriptionHeight(@NonNull ItemStack itemStack) {
    if (itemStack == null)
      throw new NullPointerException("itemStack is marked non-null but is null"); 
    IRPGItem rpgItem = IRPGItem.get(itemStack).get();
    String description = rpgItem.getItemData().getDescription().trim();
    if (!description.endsWith("."))
      description = description + "."; 
    return (rpgItem.getItemData() != null && rpgItem.getItemData().hasDescription()) ? (DrawUtils.TEXT.getLines(getTooltipWidth(itemStack) - 52.0D, description, DESC_INFO).size() * DESC_INFO.getHeight()) : 0.0D;
  }
  
  private static List<ItemStatMutator> getStats(@NonNull ItemStack stack) {
    if (stack == null)
      throw new NullPointerException("stack is marked non-null but is null"); 
    List<ItemStatMutator> stats = new ArrayList<>();
    Optional<IRPGItem> optRpgItem = IRPGItem.get(stack);
    if (!optRpgItem.isPresent())
      return stats; 
    IRPGItem rpgItem = optRpgItem.get();
    if (rpgItem instanceof RPGItemSword) {
      RPGItemSword sword = (RPGItemSword)rpgItem;
      stats.addAll((Collection<? extends ItemStatMutator>)sword.getItemData().getMutators().getOrDefault(RPGStatApplyType.HELD, new ArrayList()));
    } else if (rpgItem instanceof RPGItemArmor) {
      RPGItemArmor armor = (RPGItemArmor)rpgItem;
      stats.addAll((Collection<? extends ItemStatMutator>)armor.getArmor().getMutators().getOrDefault(RPGStatApplyType.WEAR, new ArrayList()));
    } else if (rpgItem instanceof RPGItemConsumable) {
      RPGItemConsumable consumable = (RPGItemConsumable)rpgItem;
      stats.addAll((Collection<? extends ItemStatMutator>)consumable.getItemData().getMutators().getOrDefault(RPGStatApplyType.CONSUMABLE, new ArrayList()));
    } else if (rpgItem instanceof RPGItemRune) {
      RPGItemRune rune = (RPGItemRune)rpgItem;
      stats.addAll(rune.getMutators(stack));
    } 
    return stats;
  }
  
  private static Optional<RPGEquipmentSetData> getSet(@NonNull ItemStack stack) {
    if (stack == null)
      throw new NullPointerException("stack is marked non-null but is null"); 
    Optional<IRPGItem> optRpgItem = IRPGItem.get(stack);
    if (!optRpgItem.isPresent())
      return Optional.empty(); 
    IRPGItem rpgItem = optRpgItem.get();
    if (rpgItem instanceof RPGItemSword) {
      RPGItemSword sword = (RPGItemSword)rpgItem;
      return RPGEquipmentSetCache.getSet(sword.getItemData().getSet());
    } 
    if (rpgItem instanceof RPGItemArmor) {
      RPGItemArmor armor = (RPGItemArmor)rpgItem;
      return RPGEquipmentSetCache.getSet(armor.getArmor().getSet());
    } 
    return Optional.empty();
  }
  
  private static int getRuneSlotCount(@NonNull IRPGItem rpgItem) {
    if (rpgItem == null)
      throw new NullPointerException("rpgItem is marked non-null but is null"); 
    if (rpgItem.getItemData() != null) {
      if (rpgItem instanceof RPGItemSword) {
        RPGItemSword sword = (RPGItemSword)rpgItem;
        return sword.getItemData().getRuneSlot();
      } 
      if (rpgItem instanceof RPGItemArmor) {
        RPGItemArmor armor = (RPGItemArmor)rpgItem;
        return armor.getArmor().getRuneSlot();
      } 
    } 
    return 0;
  }
  
  public static double getTooltipWidth(@NonNull ItemStack itemStack) {
    if (itemStack == null)
      throw new NullPointerException("itemStack is marked non-null but is null"); 
    return 108.0D + Math.max(ITEM_INFO.getWidth(getItemName(itemStack)), 120.0D) + 207.0D;
  }
  
  public static double getTooltipHeight(@NonNull ItemStack itemStack) {
    if (itemStack == null)
      throw new NullPointerException("itemStack is marked non-null but is null"); 
    IRPGItem rpgItem = IRPGItem.get(itemStack).get();
    double topMargin = 126.0D;
    if (rpgItem.getItemData() != null && rpgItem.getItemData().hasDescription())
      topMargin += getDescriptionHeight(itemStack) + 17.0D; 
    List<ItemStatMutator> mutators = getStats(itemStack);
    if (!mutators.isEmpty()) {
      double sectionHeight = 10.0D + SET_INFO_BOLD.getHeight() + 10.0D + (SET_INFO.getHeight() + 10.0D) * mutators.size();
      sectionHeight = Math.min(sectionHeight, 170.0D);
      topMargin += sectionHeight + 17.0D;
    } 
    int runeCount = getRuneSlotCount(rpgItem);
    if (runeCount > 0)
      topMargin += 64.0D; 
    if (!EquipmentRuneManager.isRune(itemStack)) {
      List<ItemStatMutator> list = EquipmentRuneManager.getRuneStats(itemStack);
      if (!list.isEmpty()) {
        double sectionHeight = 10.0D + SET_INFO_BOLD.getHeight() + 10.0D + (SET_INFO.getHeight() + 10.0D) * list.size();
        sectionHeight = Math.min(sectionHeight, 118.0D);
        topMargin += sectionHeight + 17.0D;
      } 
    } 
    Optional<RPGEquipmentSetData> optSetData = getSet(itemStack);
    if (optSetData.isPresent()) {
      AtomicInteger numStat = new AtomicInteger(0);
      ((RPGEquipmentSetData)optSetData.get()).getMutators().forEach((t, mut) -> numStat.addAndGet(mut.size()));
      double sectionHeight = 10.0D + SET_INFO_BOLD.getHeight() + 10.0D + (SET_INFO.getHeight() + 10.0D) * numStat.get();
      sectionHeight = Math.min(sectionHeight, 170.0D);
      topMargin += sectionHeight + 17.0D;
    } 
    if ((Minecraft.func_71410_x()).field_71474_y.field_82882_x && itemStack.func_77984_f())
      topMargin += DURABILITY_INFO.getHeight() + 17.0D; 
    return topMargin + 8.0D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\client\renderer\RPGTooltipNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */