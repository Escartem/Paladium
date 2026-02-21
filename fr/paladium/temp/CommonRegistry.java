package fr.paladium.temp;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class CommonRegistry {
  public static Block colourableGlassGlowing;
  
  public static Block colourable;
  
  public static Block skinnableGlowing;
  
  public static Block armourerBrain;
  
  public static Block colourableGlass;
  
  public static Block skinningTable;
  
  public static Block skinnableChild;
  
  public static Block awBoundingBox6;
  
  public static Block hologramProjector;
  
  public static Block skinnable;
  
  public static Block skinnableChildGlowing;
  
  public static Block armourLibrary;
  
  public static Block colourMixer;
  
  public static Block globalSkinLibrary;
  
  public static Block mannequin;
  
  public static Block doll;
  
  public static Block outfit_maker;
  
  public static Block dyeTable;
  
  public static Block colourableGlowing;
  
  public static Item guideBook;
  
  public static Item soap;
  
  public static Item paintbrush;
  
  public static Item cosmetics;
  
  public static Item equipmentSkin;
  
  public static Item blockMarker;
  
  public static Item armourContainerHead;
  
  public static Item dyeBottle;
  
  public static Item armourContainerFeet;
  
  public static Item equipmentSkinTemplate;
  
  public static Item shadeNoiseTool;
  
  public static Item linkingTool;
  
  public static Item colourNoiseTool;
  
  public static Item hueTool;
  
  public static Item armourersHammer;
  
  public static Item paintRoller;
  
  public static Item blendingTool;
  
  public static Item mannequinTool;
  
  public static Item armourContainerChest;
  
  public static Item wandOfStyle;
  
  public static Item debugTool;
  
  public static Item dodgeTool;
  
  public static Item burnTool;
  
  public static Item armourContainerLegs;
  
  public static Item colourPicker;
  
  public static Item armourContainer;
  
  public static void register() {
    colourableGlassGlowing = (new BasicBlock(Material.field_151592_s)).func_149663_c("colourableGlassGlowing");
    colourable = (new BasicBlock(Material.field_151576_e)).func_149663_c("colourable");
    skinnableGlowing = (new BasicBlock(Material.field_151576_e)).func_149663_c("skinnableGlowing");
    armourerBrain = (new BasicBlock(Material.field_151573_f)).func_149663_c("armourerBrain");
    colourableGlass = (new BasicBlock(Material.field_151592_s)).func_149663_c("colourableGlass");
    skinningTable = (new BasicBlock(Material.field_151575_d)).func_149663_c("skinningTable");
    skinnableChild = (new BasicBlock(Material.field_151576_e)).func_149663_c("skinnableChild");
    awBoundingBox6 = (new BasicBlock(Material.field_151576_e)).func_149663_c("awBoundingBox6");
    hologramProjector = (new BasicBlock(Material.field_151573_f)).func_149663_c("hologramProjector");
    skinnable = (new BasicBlock(Material.field_151576_e)).func_149663_c("skinnable");
    skinnableChildGlowing = (new BasicBlock(Material.field_151576_e)).func_149663_c("skinnableChildGlowing");
    armourLibrary = (new BasicBlock(Material.field_151575_d)).func_149663_c("armourLibrary");
    colourMixer = (new BasicBlock(Material.field_151573_f)).func_149663_c("colourMixer");
    globalSkinLibrary = (new BasicBlock(Material.field_151575_d)).func_149663_c("globalSkinLibrary");
    mannequin = (new BasicBlock(Material.field_151575_d)).func_149663_c("mannequin");
    doll = (new BasicBlock(Material.field_151580_n)).func_149663_c("doll");
    outfit_maker = (new BasicBlock(Material.field_151575_d)).func_149663_c("outfit_maker");
    dyeTable = (new BasicBlock(Material.field_151575_d)).func_149663_c("dyeTable");
    colourableGlowing = (new BasicBlock(Material.field_151575_d)).func_149663_c("colourableGlowing");
    GameRegistry.registerBlock(colourableGlassGlowing, "block.colourableGlassGlowing");
    GameRegistry.registerBlock(colourable, "block.colourable");
    GameRegistry.registerBlock(skinnableGlowing, "block.skinnableGlowing");
    GameRegistry.registerBlock(armourerBrain, "block.armourerBrain");
    GameRegistry.registerBlock(colourableGlass, "block.colourableGlass");
    GameRegistry.registerBlock(skinningTable, "block.skinningTable");
    GameRegistry.registerBlock(skinnableChild, "block.skinnableChild");
    GameRegistry.registerBlock(awBoundingBox6, "block.awBoundingBox6");
    GameRegistry.registerBlock(hologramProjector, "block.hologramProjector");
    GameRegistry.registerBlock(skinnable, "block.skinnable");
    GameRegistry.registerBlock(skinnableChildGlowing, "block.skinnableChildGlowing");
    GameRegistry.registerBlock(armourLibrary, "block.armourLibrary");
    GameRegistry.registerBlock(colourMixer, "block.colourMixer");
    GameRegistry.registerBlock(globalSkinLibrary, "block.globalSkinLibrary");
    GameRegistry.registerBlock(mannequin, "block.mannequin");
    GameRegistry.registerBlock(doll, "block.doll");
    GameRegistry.registerBlock(outfit_maker, "block.outfit_maker");
    GameRegistry.registerBlock(dyeTable, "block.dyeTable");
    GameRegistry.registerBlock(colourableGlowing, "block.colourableGlowing");
    guideBook = (new BasicItem()).func_77655_b("guideBook");
    soap = (new BasicItem()).func_77655_b("soap");
    paintbrush = (new BasicItem()).func_77655_b("paintbrush");
    cosmetics = (new BasicItem()).func_77655_b("cosmetics");
    equipmentSkin = (new BasicItem()).func_77655_b("equipmentSkin");
    blockMarker = (new BasicItem()).func_77655_b("blockMarker");
    armourContainerHead = (new BasicItem()).func_77655_b("armourContainerHead");
    dyeBottle = (new BasicItem()).func_77655_b("dyeBottle");
    armourContainerFeet = (new BasicItem()).func_77655_b("armourContainerFeet");
    equipmentSkinTemplate = (new BasicItem()).func_77655_b("equipmentSkinTemplate");
    shadeNoiseTool = (new BasicItem()).func_77655_b("shadeNoiseTool");
    linkingTool = (new BasicItem()).func_77655_b("linkingTool");
    colourNoiseTool = (new BasicItem()).func_77655_b("colourNoiseTool");
    hueTool = (new BasicItem()).func_77655_b("hueTool");
    armourersHammer = (new BasicItem()).func_77655_b("armourersHammer");
    paintRoller = (new BasicItem()).func_77655_b("paintRoller");
    blendingTool = (new BasicItem()).func_77655_b("blendingTool");
    mannequinTool = (new BasicItem()).func_77655_b("mannequinTool");
    armourContainerChest = (new BasicItem()).func_77655_b("armourContainerChest");
    wandOfStyle = (new BasicItem()).func_77655_b("wandOfStyle");
    debugTool = (new BasicItem()).func_77655_b("debugTool");
    dodgeTool = (new BasicItem()).func_77655_b("dodgeTool");
    burnTool = (new BasicItem()).func_77655_b("burnTool");
    armourContainerLegs = (new BasicItem()).func_77655_b("armourContainerLegs");
    colourPicker = (new BasicItem()).func_77655_b("colourPicker");
    armourContainer = (new BasicItem()).func_77655_b("armourContainer");
    GameRegistry.registerItem(guideBook, "guideBook");
    GameRegistry.registerItem(soap, "soap");
    GameRegistry.registerItem(paintbrush, "paintbrush");
    GameRegistry.registerItem(cosmetics, "item.cosmetics");
    GameRegistry.registerItem(equipmentSkin, "equipmentSkin");
    GameRegistry.registerItem(blockMarker, "blockMarker");
    GameRegistry.registerItem(armourContainerHead, "armourContainerHead");
    GameRegistry.registerItem(dyeBottle, "dyeBottle");
    GameRegistry.registerItem(armourContainerFeet, "armourContainerFeet");
    GameRegistry.registerItem(equipmentSkinTemplate, "equipmentSkinTemplate");
    GameRegistry.registerItem(shadeNoiseTool, "shadeNoiseTool");
    GameRegistry.registerItem(linkingTool, "linkingTool");
    GameRegistry.registerItem(colourNoiseTool, "colourNoiseTool");
    GameRegistry.registerItem(hueTool, "hueTool");
    GameRegistry.registerItem(armourersHammer, "armourersHammer");
    GameRegistry.registerItem(paintRoller, "paintRoller");
    GameRegistry.registerItem(blendingTool, "blendingTool");
    GameRegistry.registerItem(mannequinTool, "mannequinTool");
    GameRegistry.registerItem(armourContainerChest, "armourContainerChest");
    GameRegistry.registerItem(wandOfStyle, "wandOfStyle");
    GameRegistry.registerItem(debugTool, "debugTool");
    GameRegistry.registerItem(dodgeTool, "dodgeTool");
    GameRegistry.registerItem(burnTool, "burnTool");
    GameRegistry.registerItem(armourContainerLegs, "armourContainerLegs");
    GameRegistry.registerItem(colourPicker, "colourPicker");
    GameRegistry.registerItem(armourContainer, "armourContainer");
  }
  
  public static class BasicBlock extends Block {
    public BasicBlock(Material material) {
      super(material);
    }
  }
  
  public static class BasicItem extends Item {}
}


/* Location:              E:\Paladium\!\fr\paladium\temp\CommonRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */