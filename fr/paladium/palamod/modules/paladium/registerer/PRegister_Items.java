package fr.paladium.palamod.modules.paladium.registerer;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.blocks.BaseFood;
import fr.paladium.palamod.common.items.BaseItem;
import fr.paladium.palamod.common.items.BaseItemPlants;
import fr.paladium.palamod.common.items.BaseItemSeed;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.luckyblock.items.ItemDisc;
import fr.paladium.palamod.modules.luckyblock.items.ItemFaux;
import fr.paladium.palamod.modules.luckyblock.items.ItemForeuse;
import fr.paladium.palamod.modules.luckyblock.items.ItemPowerHelmet;
import fr.paladium.palamod.modules.luckyblock.items.ItemSpeaker;
import fr.paladium.palamod.modules.luckyblock.items.ItemVisionHelmet;
import fr.paladium.palamod.modules.paladium.common.items.BaseItemApple;
import fr.paladium.palamod.modules.paladium.common.items.BaseItemBerry;
import fr.paladium.palamod.modules.paladium.common.items.BaseItemMoney;
import fr.paladium.palamod.modules.paladium.common.items.BaseItemRing;
import fr.paladium.palamod.modules.paladium.common.items.BaseItemRingEndium;
import fr.paladium.palamod.modules.paladium.common.items.ItemArrowBase;
import fr.paladium.palamod.modules.paladium.common.items.ItemBackpack;
import fr.paladium.palamod.modules.paladium.common.items.ItemBucketBase;
import fr.paladium.palamod.modules.paladium.common.items.ItemChase;
import fr.paladium.palamod.modules.paladium.common.items.ItemChestExplorer;
import fr.paladium.palamod.modules.paladium.common.items.ItemEnchantedPlate;
import fr.paladium.palamod.modules.paladium.common.items.ItemEndiumGauntlet;
import fr.paladium.palamod.modules.paladium.common.items.ItemExtrapolatedBucket;
import fr.paladium.palamod.modules.paladium.common.items.ItemHangGlider;
import fr.paladium.palamod.modules.paladium.common.items.ItemMagicalTool;
import fr.paladium.palamod.modules.paladium.common.items.ItemOrePresent;
import fr.paladium.palamod.modules.paladium.common.items.ItemPaladiumInk;
import fr.paladium.palamod.modules.paladium.common.items.ItemPaladiumKey;
import fr.paladium.palamod.modules.paladium.common.items.ItemPlate;
import fr.paladium.palamod.modules.paladium.common.items.ItemStickBase;
import fr.paladium.palamod.modules.paladium.common.items.ItemStickModeration;
import fr.paladium.palamod.modules.paladium.common.items.ItemUnclaimFinder;
import fr.paladium.palamod.modules.paladium.common.items.ItemVoidStone;
import fr.paladium.palamod.modules.paladium.common.items.ItemVoidStoneMinage;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.SeedPlanterSeedFood;
import fr.paladium.palamod.modules.paladium.common.items.armors.BaseItemArmor;
import fr.paladium.palamod.modules.paladium.common.items.armors.ItemArmorTravel;
import fr.paladium.palamod.modules.paladium.common.items.armors.ItemEndiumArmor;
import fr.paladium.palamod.modules.paladium.common.items.armors.ItemMixedEndiumArmor;
import fr.paladium.palamod.modules.paladium.common.items.armors.ItemRepairableArmor;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.ItemAncientArmor;
import fr.paladium.palamod.modules.paladium.common.items.boost.ItemBoostDoubleXp;
import fr.paladium.palamod.modules.paladium.common.items.boost.ItemBoostMinerFou;
import fr.paladium.palamod.modules.paladium.common.items.decorative.ItemObsidianDoor;
import fr.paladium.palamod.modules.paladium.common.items.eventcommu.HalloweenHat;
import fr.paladium.palamod.modules.paladium.common.items.eventcommu.ItemCandy;
import fr.paladium.palamod.modules.paladium.common.items.eventcommu.ItemChrismasHammer;
import fr.paladium.palamod.modules.paladium.common.items.eventcommu.ItemHalloweenAxe;
import fr.paladium.palamod.modules.paladium.common.items.eventcommu.ItemNoelCosmetic;
import fr.paladium.palamod.modules.paladium.common.items.eventcommu.ItemTankPistol;
import fr.paladium.palamod.modules.paladium.common.items.explosive.BaseItemDynamite;
import fr.paladium.palamod.modules.paladium.common.items.explosive.ItemDynamiteBig;
import fr.paladium.palamod.modules.paladium.common.items.explosive.ItemDynamiteEndium;
import fr.paladium.palamod.modules.paladium.common.items.explosive.ItemDynamiteNinja;
import fr.paladium.palamod.modules.paladium.common.items.potion.ItemPotion;
import fr.paladium.palamod.modules.paladium.common.items.potion.ItemSplashPotion;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.ItemAncientHammer;
import fr.paladium.palamod.modules.paladium.common.items.tools.BaseItemAxe;
import fr.paladium.palamod.modules.paladium.common.items.tools.BaseItemPickaxe;
import fr.paladium.palamod.modules.paladium.common.items.tools.BaseItemShovel;
import fr.paladium.palamod.modules.paladium.common.items.upgrade.ItemBowRangeModifier;
import fr.paladium.palamod.modules.paladium.common.items.upgrade.ItemBowSpeedModifier;
import fr.paladium.palamod.modules.paladium.common.items.weapons.BaseItemSword;
import fr.paladium.palamod.modules.paladium.common.items.weapons.ItemInfernalKnocker;
import fr.paladium.palamod.modules.paladium.common.items.weapons.ItemPaladiumBow;
import fr.paladium.palamod.modules.paladium.common.items.weapons.ItemPaladiumGreenSword;
import fr.paladium.palamod.modules.paladium.common.items.weapons.ItemPotionLauncher;
import fr.paladium.palamod.modules.paladium.common.materials.PArmorMaterial;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import fr.paladium.palamod.modules.paladium.dummy.ItemDummy;
import fr.paladium.palamod.modules.world.PWorld;
import fr.welsymc.guardiangolem.common.utils.CosmeticType;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class PRegister_Items {
  public static void init() {
    ItemsRegister.AMETHYST_INGOT = (new BaseItem("materials/amethyst_ingot")).func_77655_b("amethyst.ingot");
    ItemsRegister.TITANE_INGOT = (new BaseItem("materials/titane_ingot")).func_77655_b("titane.ingot");
    ItemsRegister.PALADIUM_INGOT = (new BaseItem("materials/paladium_ingot")).func_77655_b("paladium.ingot");
    ItemsRegister.PALADIUM_GREEN_INGOT = (new BaseItem("materials/paladium_green_ingot")).func_77655_b("paladium_green.ingot");
    ItemsRegister.ENDIUM_INGOT = (new BaseItem("materials/endium_ingot")).func_77655_b("endium.ingot").func_77625_d(64);
    ItemsRegister.ENDIUM_FRAGMENT = (new BaseItem("materials/endium_fragment")).func_77655_b("endium.fragment").func_77625_d(64);
    ItemsRegister.FINDIUM = (new BaseItem("materials/findium")).func_77655_b("findium");
    ItemsRegister.TRIXIUM = (new BaseItem("materials/trixium")).func_77655_b("trixium");
    ItemsRegister.PB = (new BaseItem("pb")).func_77655_b("Points Boutiques");
    ItemsRegister.money = (new BaseItemMoney(1000)).func_77655_b("npcMoney").func_77637_a((CreativeTabs)Registry.PALADIUM_TAB).func_111206_d("customnpcs:npcMoney");
    ItemsRegister.PLATE = (Item)new ItemPlate("plate");
    ItemsRegister.PLATE_ENCHANTED = (Item)new ItemEnchantedPlate("echanted_plate");
    ItemsRegister.CHASE = (Item)new ItemChase("chase");
    ItemsRegister.PALADIUM_INK = (Item)new ItemPaladiumInk();
    ItemsRegister.PALADIUM_KEY = (Item)new ItemPaladiumKey("paladium_key");
    ItemsRegister.Farmer = (new BaseItem("jobs/farmer")).func_77655_b("jobs.farmer");
    ItemsRegister.Miner = (new BaseItem("jobs/miner")).func_77655_b("jobs.miner");
    ItemsRegister.Hunter = (new BaseItem("jobs/hunter")).func_77655_b("jobs.hunter");
    ItemsRegister.Alchemist = (new BaseItem("jobs/alchemist")).func_77655_b("jobs.alchemist");
    ItemsRegister.Job = (new BaseItem("jobs/job")).func_77655_b("jobs.job");
    ItemsRegister.ENDIUM_NUGGET = (new BaseItem("materials/endium_nugget")).func_77655_b("endium.nugget").func_77625_d(64);
    ItemsRegister.AMETHYST_PICKAXE = (new BaseItemPickaxe(PToolMaterial.amethyst, "amethyst_pickaxe", ItemsRegister.AMETHYST_INGOT)).func_77655_b("amethyst.pickaxe");
    ItemsRegister.AMETHYST_SHOVEL = (new BaseItemShovel(PToolMaterial.amethyst, "amethyst_shovel", ItemsRegister.AMETHYST_INGOT)).func_77655_b("amethyst.shovel");
    ItemsRegister.AMETHYST_AXE = (new BaseItemAxe(PToolMaterial.amethyst, "amethyst_axe", ItemsRegister.AMETHYST_INGOT)).func_77655_b("amethyst.axe");
    ItemsRegister.AMETHYST_SWORD = (new BaseItemSword(PToolMaterial.amethyst, "amethyst_sword", ItemsRegister.AMETHYST_INGOT)).func_77655_b("amethyst.sword");
    ItemsRegister.AMETHYST_HELMET = (new BaseItemArmor(PArmorMaterial.amethyst, 0, "amethyst_helmet", "amethyst_armor", ItemsRegister.AMETHYST_INGOT)).func_77655_b("amethyst.helmet");
    ItemsRegister.AMETHYST_CHESTPLATE = (new BaseItemArmor(PArmorMaterial.amethyst, 1, "amethyst_chestplate", "amethyst_armor", ItemsRegister.AMETHYST_INGOT)).func_77655_b("amethyst.chestplate");
    ItemsRegister.AMETHYST_LEGGINGS = (new BaseItemArmor(PArmorMaterial.amethyst, 2, "amethyst_leggings", "amethyst_armor", ItemsRegister.AMETHYST_INGOT)).func_77655_b("amethyst.leggings");
    ItemsRegister.AMETHYST_BOOTS = (new BaseItemArmor(PArmorMaterial.amethyst, 3, "amethyst_boots", "amethyst_armor", ItemsRegister.AMETHYST_INGOT)).func_77655_b("amethyst.boots");
    ItemsRegister.TITANE_PICKAXE = (new BaseItemPickaxe(PToolMaterial.titane, "titane_pickaxe", ItemsRegister.TITANE_INGOT)).func_77655_b("titane.pickaxe");
    ItemsRegister.TITANE_SHOVEL = (new BaseItemShovel(PToolMaterial.titane, "titane_shovel", ItemsRegister.TITANE_INGOT)).func_77655_b("titane.shovel");
    ItemsRegister.TITANE_AXE = (new BaseItemAxe(PToolMaterial.titane, "titane_axe", ItemsRegister.TITANE_INGOT)).func_77655_b("titane.axe");
    ItemsRegister.TITANE_SWORD = (new BaseItemSword(PToolMaterial.titane, "titane_sword", ItemsRegister.TITANE_INGOT)).func_77655_b("titane.sword");
    ItemsRegister.TITANE_HELMET = (new BaseItemArmor(PArmorMaterial.titane, 0, "titane_helmet", "titane_armor", ItemsRegister.TITANE_INGOT)).func_77655_b("titane.helmet");
    ItemsRegister.TITANE_CHESTPLATE = (new BaseItemArmor(PArmorMaterial.titane, 1, "titane_chestplate", "titane_armor", ItemsRegister.TITANE_INGOT)).func_77655_b("titane.chestplate");
    ItemsRegister.TITANE_LEGGINGS = (new BaseItemArmor(PArmorMaterial.titane, 2, "titane_leggings", "titane_armor", ItemsRegister.TITANE_INGOT)).func_77655_b("titane.leggings");
    ItemsRegister.TITANE_BOOTS = (new BaseItemArmor(PArmorMaterial.titane, 3, "titane_boots", "titane_armor", ItemsRegister.TITANE_INGOT)).func_77655_b("titane.boots");
    ItemsRegister.PALADIUM_PICKAXE = (new BaseItemPickaxe(PToolMaterial.paladium, "paladium_pickaxe", ItemsRegister.PALADIUM_INGOT)).func_77655_b("paladium.pickaxe");
    ItemsRegister.PALADIUM_SHOVEL = (new BaseItemShovel(PToolMaterial.paladium, "paladium_shovel", ItemsRegister.PALADIUM_INGOT)).func_77655_b("paladium.shovel");
    ItemsRegister.PALADIUM_AXE = (new BaseItemAxe(PToolMaterial.paladium, "paladium_axe", ItemsRegister.PALADIUM_INGOT)).func_77655_b("paladium.axe");
    ItemsRegister.PALADIUM_SWORD = (new BaseItemSword(PToolMaterial.paladium, "paladium_sword", ItemsRegister.PALADIUM_INGOT)).func_77655_b("paladium.sword");
    ItemsRegister.WITHER_DUST = (new Item()).func_77637_a((CreativeTabs)Registry.PALADIUM_TAB).func_77655_b("wither_dust").func_111206_d("palamod:wither_dust");
    PotionEffect[] effects = new PotionEffect[4];
    effects[0] = new PotionEffect(Potion.field_76439_r.field_76415_H, 1200, 1, true);
    effects[3] = new PotionEffect(Potion.field_76424_c.field_76415_H, 1200, 0, true);
    int[] arrayOfInt1 = { 8000, 12000, 10000, 7000 };
    ItemsRegister.PALADIUM_HELMET = (new ItemRepairableArmor(PArmorMaterial.paladium, 0, "paladium_helmet", "paladium_armor", ItemsRegister.TITANE_INGOT, effects, arrayOfInt1)).func_77655_b("paladium.helmet");
    ItemsRegister.PALADIUM_CHESTPLATE = (new ItemRepairableArmor(PArmorMaterial.paladium, 1, "paladium_chestplate", "paladium_armor", ItemsRegister.TITANE_INGOT, effects, arrayOfInt1)).func_77655_b("paladium.chestplate");
    ItemsRegister.PALADIUM_LEGGINGS = (new ItemRepairableArmor(PArmorMaterial.paladium, 2, "paladium_leggings", "paladium_armor", ItemsRegister.TITANE_INGOT, effects, arrayOfInt1)).func_77655_b("paladium.leggings");
    ItemsRegister.PALADIUM_BOOTS = (new ItemRepairableArmor(PArmorMaterial.paladium, 3, "paladium_boots", "paladium_armor", ItemsRegister.TITANE_INGOT, effects, arrayOfInt1)).func_77655_b("paladium.boots");
    effects = new PotionEffect[4];
    effects[0] = new PotionEffect(Potion.field_76439_r.field_76415_H, 1200, 1, true);
    effects[3] = new PotionEffect(Potion.field_76424_c.field_76415_H, 1200, 1, true);
    arrayOfInt1 = new int[] { 8000, 12000, 10000, 7000 };
    ItemsRegister.TOURNAMENT_RED_HELMET = (new ItemRepairableArmor(PArmorMaterial.tournament, 0, "tournament_red_helmet", "tournament_red_armor", ItemsRegister.TITANE_INGOT, effects, arrayOfInt1)).func_77655_b("tournament.red.helmet");
    ItemsRegister.TOURNAMENT_RED_CHESTPLATE = (new ItemRepairableArmor(PArmorMaterial.tournament, 1, "tournament_red_chestplate", "tournament_red_armor", ItemsRegister.TITANE_INGOT, effects, arrayOfInt1)).func_77655_b("tournament.red.chestplate");
    ItemsRegister.TOURNAMENT_RED_LEGGINGS = (new ItemRepairableArmor(PArmorMaterial.tournament, 2, "tournament_red_leggings", "tournament_red_armor", ItemsRegister.TITANE_INGOT, effects, arrayOfInt1)).func_77655_b("tournament.red.leggings");
    ItemsRegister.TOURNAMENT_RED_BOOTS = (new ItemRepairableArmor(PArmorMaterial.tournament, 3, "tournament_red_boots", "tournament_red_armor", ItemsRegister.TITANE_INGOT, effects, arrayOfInt1)).func_77655_b("tournament.red.boots");
    ItemsRegister.TOURNAMENT_RED_SWORD = (new ItemPaladiumGreenSword(PToolMaterial.paladium, "tournament_red_sword", ItemsRegister.PALADIUM_GREEN_INGOT)).func_77655_b("tournament.red.sword");
    ItemsRegister.TOURNAMENT_BLUE_HELMET = (new ItemRepairableArmor(PArmorMaterial.tournament, 0, "tournament_blue_helmet", "tournament_blue_armor", ItemsRegister.TITANE_INGOT, effects, arrayOfInt1)).func_77655_b("tournament.blue.helmet");
    ItemsRegister.TOURNAMENT_BLUE_CHESTPLATE = (new ItemRepairableArmor(PArmorMaterial.tournament, 1, "tournament_blue_chestplate", "tournament_blue_armor", ItemsRegister.TITANE_INGOT, effects, arrayOfInt1)).func_77655_b("tournament.blue.chestplate");
    ItemsRegister.TOURNAMENT_BLUE_LEGGINGS = (new ItemRepairableArmor(PArmorMaterial.tournament, 2, "tournament_blue_leggings", "tournament_blue_armor", ItemsRegister.TITANE_INGOT, effects, arrayOfInt1)).func_77655_b("tournament.blue.leggings");
    ItemsRegister.TOURNAMENT_BLUE_BOOTS = (new ItemRepairableArmor(PArmorMaterial.tournament, 3, "tournament_blue_boots", "tournament_blue_armor", ItemsRegister.TITANE_INGOT, effects, arrayOfInt1)).func_77655_b("tournament.blue.boots");
    ItemsRegister.TOURNAMENT_BLUE_SWORD = (new ItemPaladiumGreenSword(PToolMaterial.paladium, "tournament_blue_sword", ItemsRegister.PALADIUM_GREEN_INGOT)).func_77655_b("tournament.blue.sword");
    effects = new PotionEffect[2];
    effects[0] = new PotionEffect(Potion.field_76428_l.field_76415_H, 100, 2, true);
    effects[1] = new PotionEffect(Potion.field_76426_n.field_76415_H, 1500, 0, true);
    ItemsRegister.PALADIUM_APPLE = (new BaseItemApple(100, 0.6F, 10.0F, effects, "paladium_apple")).func_77655_b("paladium.apple");
    ItemsRegister.PALADIUM_GREEN_PICKAXE = (new BaseItemPickaxe(PToolMaterial.paladiumGreen, "paladium_green_pickaxe", ItemsRegister.PALADIUM_GREEN_INGOT)).func_77655_b("paladium.green.pickaxe");
    ItemsRegister.PALADIUM_GREEN_SHOVEL = (new BaseItemShovel(PToolMaterial.paladiumGreen, "paladium_green_shovel", ItemsRegister.PALADIUM_GREEN_INGOT)).func_77655_b("paladium.green.shovel");
    ItemsRegister.PALADIUM_GREEN_AXE = (new BaseItemAxe(PToolMaterial.paladiumGreen, "paladium_green_axe", ItemsRegister.PALADIUM_GREEN_INGOT)).func_77655_b("paladium.green.axe");
    ItemsRegister.PALADIUM_GREEN_SWORD = (new ItemPaladiumGreenSword(PToolMaterial.paladiumGreen, "paladium_green_sword", ItemsRegister.PALADIUM_GREEN_INGOT)).func_77655_b("paladium.green.sword");
    effects = new PotionEffect[4];
    effects[0] = new PotionEffect(Potion.field_76439_r.field_76415_H, 1200, 1, true);
    effects[1] = null;
    effects[2] = new PotionEffect(Potion.field_76422_e.field_76415_H, 1200, 0, true);
    effects[3] = new PotionEffect(Potion.field_76424_c.field_76415_H, 1200, 1, true);
    arrayOfInt1 = new int[] { 8000, 12000, 10000, 7000 };
    ItemsRegister.PALADIUM_GREEN_HELMET = (new ItemRepairableArmor(PArmorMaterial.paladiumGreen, 0, "paladium_green_helmet", "paladium_green_armor", ItemsRegister.PALADIUM_GREEN_INGOT, effects, arrayOfInt1)).func_77655_b("paladium.green.helmet");
    ItemsRegister.PALADIUM_GREEN_CHESTPLATE = (new ItemRepairableArmor(PArmorMaterial.paladiumGreen, 1, "paladium_green_chestplate", "paladium_green_armor", ItemsRegister.PALADIUM_GREEN_INGOT, effects, arrayOfInt1)).func_77655_b("paladium.green.chestplate");
    ItemsRegister.PALADIUM_GREEN_LEGGINGS = (new ItemRepairableArmor(PArmorMaterial.paladiumGreen, 2, "paladium_green_leggings", "paladium_green_armor", ItemsRegister.PALADIUM_GREEN_INGOT, effects, arrayOfInt1)).func_77655_b("paladium.green.leggings");
    ItemsRegister.PALADIUM_GREEN_BOOTS = (new ItemRepairableArmor(PArmorMaterial.paladiumGreen, 3, "paladium_green_boots", "paladium_green_armor", ItemsRegister.PALADIUM_GREEN_INGOT, effects, arrayOfInt1)).func_77655_b("paladium.green.boots");
    ItemsRegister.ENDIUM_PICKAXE = (new BaseItemPickaxe(PToolMaterial.endium, "endium_pickaxe", ItemsRegister.ENDIUM_INGOT)).func_77655_b("endium.pickaxe").func_77625_d(1);
    ItemsRegister.ENDIUM_AXE = (new BaseItemAxe(PToolMaterial.endium, "endium_axe", ItemsRegister.ENDIUM_INGOT)).func_77655_b("endium.axe").func_77625_d(1);
    ItemsRegister.ENDIUM_SWORD = (new BaseItemSword(PToolMaterial.endium, "endium_sword", ItemsRegister.ENDIUM_INGOT)).func_77655_b("endium.sword").func_77625_d(1);
    ItemsRegister.ENDIUM_HELMET = (new ItemEndiumArmor(PArmorMaterial.endium, 0, "endium_helmet", "endium_armor", ItemsRegister.ENDIUM_INGOT)).func_77655_b("endium.helmet").func_77625_d(1);
    ItemsRegister.ENDIUM_CHESTPLATE = (new ItemEndiumArmor(PArmorMaterial.endium, 1, "endium_chestplate", "endium_armor", ItemsRegister.ENDIUM_INGOT)).func_77655_b("endium.chestplate").func_77625_d(1);
    ItemsRegister.ENDIUM_LEGGINGS = (new ItemEndiumArmor(PArmorMaterial.endium, 2, "endium_leggings", "endium_armor", ItemsRegister.ENDIUM_INGOT)).func_77655_b("endium.leggings").func_77625_d(1);
    ItemsRegister.ENDIUM_BOOTS = (new ItemEndiumArmor(PArmorMaterial.endium, 3, "endium_boots", "endium_armor", ItemsRegister.ENDIUM_INGOT)).func_77655_b("endium.boots").func_77625_d(1);
    int[] maxRepair = { 8000, 12000, 10000, 7000 };
    ItemsRegister.MIXED_ENDIUM_HELMET = (new ItemMixedEndiumArmor(PArmorMaterial.mixedEndium, 0, "mixed_endium_helmet", "mixed_endium_armor", ItemsRegister.MIXED_ENDIUM_INGOT, maxRepair)).func_77655_b("mixed_endium.helmet").func_77625_d(1);
    ItemsRegister.MIXED_ENDIUM_CHESTPLATE = (new ItemMixedEndiumArmor(PArmorMaterial.mixedEndium, 1, "mixed_endium_chestplate", "mixed_endium_armor", ItemsRegister.MIXED_ENDIUM_INGOT, maxRepair)).func_77655_b("mixed_endium.chestplate").func_77625_d(1);
    ItemsRegister.MIXED_ENDIUM_LEGGINGS = (new ItemMixedEndiumArmor(PArmorMaterial.mixedEndium, 2, "mixed_endium_leggings", "mixed_endium_armor", ItemsRegister.MIXED_ENDIUM_INGOT, maxRepair)).func_77655_b("mixed_endium.leggings").func_77625_d(1);
    ItemsRegister.MIXED_ENDIUM_BOOTS = (new ItemMixedEndiumArmor(PArmorMaterial.mixedEndium, 3, "mixed_endium_boots", "mixed_endium_armor", ItemsRegister.MIXED_ENDIUM_INGOT, maxRepair)).func_77655_b("mixed_endium.boots").func_77625_d(1);
    ItemsRegister.MIXED_ENDIUM_INGOT = (new Item()).func_77655_b("mixed_endium_ingot").func_111206_d("palamod:mixed_endium_ingot").func_77625_d(64).func_77637_a(CreativeTabs.field_78035_l);
    RegistryUtils.item(new Item[] { ItemsRegister.MIXED_ENDIUM_HELMET, ItemsRegister.MIXED_ENDIUM_CHESTPLATE, ItemsRegister.MIXED_ENDIUM_LEGGINGS, ItemsRegister.MIXED_ENDIUM_BOOTS, ItemsRegister.MIXED_ENDIUM_INGOT });
    ItemsRegister.CHESTEXPLORER = (ItemChestExplorer)(new ItemChestExplorer("chestexplorer")).func_77655_b("chestexplorer");
    ItemsRegister.CHESTEXPLORER.addTile("TileEntityChest");
    ItemsRegister.CHESTEXPLORER.addTile("TitaneChestLogic");
    ItemsRegister.CHESTEXPLORER.addTile("AmethystChestLogic");
    ItemsRegister.CHESTEXPLORER.addTile("ironchest");
    ItemsRegister.VOIDSTONE = (ItemVoidStone)(new ItemVoidStone("voidstone")).func_77655_b("voidstone");
    ItemsRegister.VOIDSTONE_MINAGE = (ItemVoidStoneMinage)(new ItemVoidStoneMinage("voidstone_minage")).func_77655_b("voidstone.minage");
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(Blocks.field_150348_b);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(Blocks.field_150347_e);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(Blocks.field_150346_d);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(Blocks.field_150351_n);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(PWorld.GRANITE_BLOCK, 0);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(ModBlocks.stone, 5);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(ModBlocks.stone, 3);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(ModBlocks.stone, 1);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(Blocks.field_150341_Y);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter((Block)Blocks.field_150349_c);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(Blocks.field_150351_n);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(PWorld.GRANITE_BLOCK, 1);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(PWorld.DIORITE_BLOCK, 0);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(PWorld.DIORITE_BLOCK, 1);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(PWorld.ANDESITE_BLOCK, 0);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(PWorld.ANDESITE_BLOCK, 1);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(PWorld.LIMESTONE_BLOCK, 0);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(PWorld.LIMESTONE_BLOCK, 1);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(PWorld.MARBLE_BLOCK, 0);
    ItemsRegister.VOIDSTONE_MINAGE.addFilter(PWorld.MARBLE_BLOCK, 1);
    ItemsRegister.SMALL_RING = (new BaseItemRing("small_ring", 400)).func_77655_b("ring.small");
    ItemsRegister.MEDIUM_RING = (new BaseItemRing("medium_ring", 1000)).func_77655_b("ring.medium");
    ItemsRegister.BIG_RING = (new BaseItemRing("medium_big", 3200)).func_77655_b("ring.big");
    ItemsRegister.RING_ENDIUM_SMALL = (new BaseItemRingEndium("ring/endium_small", 400)).func_77655_b("ring.endium.small").func_77625_d(1);
    ItemsRegister.RING_ENDIUM_MEDIUM = (new BaseItemRingEndium("ring/endium_medium", 1000)).func_77655_b("ring.endium.medium").func_77625_d(1);
    ItemsRegister.RING_ENDIUM_BIG = (new BaseItemRingEndium("ring/endium_big", 3200)).func_77655_b("ring.endium.big").func_77625_d(1);
    ItemsRegister.STICK_HEAL = new ItemStickBase(15, "healstick", "HealStick", 10, 0, new double[] { 255.0D, 0.0D, 0.0D });
    ItemsRegister.STICK_SPEED = new ItemStickBase(15, "speedstick", "SpeedStick", 15, 1, new double[] { 73.0D, 173.0D, 244.0D });
    ItemsRegister.STICK_STRENGHT = new ItemStickBase(15, "strenghtstick", "StrenghtStick", 15, 2, new double[] { 244.0D, 212.0D, 66.0D });
    ItemsRegister.STICK_JUMP = new ItemStickBase(15, "jumpstick", "JumpStick", 30, 3, new double[] { 244.0D, 143.0D, 66.0D });
    ItemsRegister.STICK_GOD = new ItemStickBase(8, "stickofgod", "StickOfGod", 25, 4, new double[] { 244.0D, 238.0D, 66.0D });
    ItemsRegister.STICK_DAMAGE = new ItemStickBase(16, "damagestick", "DamageStick", 30, 5, new double[] { 255.0D, 0.0D, 0.0D });
    ItemsRegister.STICK_HYPERJUMP = new ItemStickBase(8, "hyperjumpstick", "HyperJumpStick", 40, 6, new double[] { 80.0D, 244.0D, 66.0D });
    ItemsRegister.STICK_TELEPORT = new ItemStickBase(64, "teleportstick", "TeleportStick", 12, 7, new double[] { 80.0D, 244.0D, 66.0D });
    ItemsRegister.STICK_MODERATION = (Item)new ItemStickModeration();
    ItemsRegister.UNCLAIMFINDER = new ItemUnclaimFinder("unclaimfinder", 0);
    ItemsRegister.UNCLAIMFINDER_ORANGE = new ItemUnclaimFinder("unclaimfinder_orange", 1);
    ItemsRegister.UNCLAIMFINDER_ROUGE = new ItemUnclaimFinder("unclaimfinder_rouge", 2);
    ItemsRegister.UNCLAIMFINDER_BLEU = new ItemUnclaimFinder("unclaimfinder_bleu", 3);
    ItemsRegister.PALADIUM_BOW = new ItemPaladiumBow();
    ItemsRegister.INFERNAL_KNOCKER = new ItemInfernalKnocker();
    ItemsRegister.ARROW_POISON = new ItemArrowBase("arrow.poison", "arrow/arrow_poison", 0);
    ItemsRegister.ARROW_SLOWNESS = new ItemArrowBase("arrow.slowness", "arrow/arrow_slowness", 2);
    ItemsRegister.ARROW_SWITCH = new ItemArrowBase("arrow.switch", "arrow/arrow_switch", 3);
    ItemsRegister.ARROW_WITHER = new ItemArrowBase("arrow.wither", "arrow/arrow_wither", 1);
    ItemsRegister.ARROW_FROST = new ItemArrowBase("arrow.frost", "arrow/arrow_frost", 4);
    ItemsRegister.PARTICLE_IRON = (new BaseItem("particle/particle_iron")).func_77655_b("particle.iron");
    ItemsRegister.PARTICLE_GOLD = (new BaseItem("particle/particle_gold")).func_77655_b("particle.gold");
    ItemsRegister.PARTICLE_DIAMOND = (new BaseItem("particle/particle_diamond")).func_77655_b("particle.diamond");
    ItemsRegister.PARTICLE_AMETHYST = (new BaseItem("particle/particle_amethyst")).func_77655_b("particle.amethyst");
    ItemsRegister.PARTICLE_TITANE = (new BaseItem("particle/particle_titane")).func_77655_b("particle.titane");
    ItemsRegister.PARTICLE_PALADIUM = (new BaseItem("particle/particle_paladium")).func_77655_b("particle.paladium");
    ItemsRegister.FRUIT_ORANGEBLUE = (new BaseItemPlants("fruits/orangeblue")).func_77655_b("fruits.orangeblue");
    ItemsRegister.FRUIT_KIWANO = (new BaseItemPlants("fruits/kiwano")).func_77655_b("fruits.kiwano");
    ItemsRegister.FRUIT_CHERVIL = (new BaseItemPlants("fruits/chervil")).func_77655_b("fruits.chervil");
    ItemsRegister.FRUIT_EGGPLANT = (new BaseItemPlants("fruits/eggplants")).func_77655_b("fruits.eggplants");
    ItemsRegister.XP_BERRY = new BaseItemBerry("xp_berry", "fruits/xp_berry");
    ItemsRegister.COMPRESSED_XP_BERRY = new BaseItemBerry("compressed_xp_berry", "fruits/compressed_xp_berry");
    ItemsRegister.SEED_ORANGEBLUE = new BaseItemSeed("seed.orangeblue", "orangeblue", (Block)BlocksRegister.CROPS_ORANGEBLUE, 20);
    ItemsRegister.SEED_KIWANO = new BaseItemSeed("seed.kiwano", "kiwano", (Block)BlocksRegister.CROPS_KIWANO, 15);
    ItemsRegister.SEED_CHERVIL = new BaseItemSeed("seed.chervil", "chervil", (Block)BlocksRegister.CROPS_CHERVIL, 10);
    ItemsRegister.SEED_EGGPLANT = new BaseItemSeed("seed.eggplant", "eggplant", (Block)BlocksRegister.CROPS_EGGPLANT, 5);
    ItemsRegister.SEED_EGGPLANT = new BaseItemSeed("seed.eggplant", "eggplant", (Block)BlocksRegister.CROPS_EGGPLANT, 5);
    ItemsRegister.SEED_ICE = new BaseItemSeed("seed.ice", "ice", (Block)BlocksRegister.CROPS_ICE, 0);
    ItemsRegister.FURNACE_UPGRADE = (new BaseItem("machine/furnace_upgrade")).func_77655_b("furnace_upgrade").func_77625_d(16);
    ItemsRegister.PALAMIXEDCOAL = (new BaseItem("machine/palaMixedCoal")).func_77655_b("palaMixedCoal");
    ItemsRegister.GOLDMIXEDCOAL = (new BaseItem("machine/goldMixedCoal")).func_77655_b("goldMixedCoal");
    ItemsRegister.AMETHYSTMIXEDCOAL = (new BaseItem("machine/amethystMixedCoal")).func_77655_b("amethystMixedCoal");
    ItemsRegister.TITANEMIXEDCOAL = (new BaseItem("machine/titaneMixedCoal")).func_77655_b("titaneMixedCoal");
    ItemsRegister.BOWUPGRADE_EMPTY = (new BaseItem("machine/emptyBowModifier")).func_77655_b("emptyBowModifier").func_77625_d(1);
    ItemsRegister.BOWUPGRADE_RANGEMODIFIER = new ItemBowRangeModifier();
    ItemsRegister.BOWUPGRADE_SPEEDMODIFIER = new ItemBowSpeedModifier();
    ItemsRegister.COMPRESSED_ENDIUM = (new BaseItem("machine/compressed_endium")).func_77655_b("compressed.endium");
    ItemsRegister.COMPRESSED_PALADIUM = (new BaseItem("machine/compressed_paladium")).func_77655_b("compressed.paladium");
    ItemsRegister.COMPRESSED_TITANE = (new BaseItem("machine/compressed_titane")).func_77655_b("compressed.titane");
    ItemsRegister.COMPRESSED_AMETHYST = (new BaseItem("machine/compressed_amethyst")).func_77655_b("compressed.amethyst");
    ItemsRegister.ORB_HEAL = (new BaseItem("machine/orb_heal")).func_77655_b("orb.heal");
    ItemsRegister.ORB_JUMP = (new BaseItem("machine/orb_jump")).func_77655_b("orb.jump");
    ItemsRegister.ORB_KNOCKBACK = (new BaseItem("machine/orb_knockback")).func_77655_b("orb.knockback");
    ItemsRegister.ORB_SPEED = (new BaseItem("machine/orb_speed")).func_77655_b("orb.speed");
    ItemsRegister.ORB_STRENGHT = (new BaseItem("machine/orb_strenght")).func_77655_b("orb.strenght");
    ItemsRegister.ORB_CHAOS = (new BaseItem("orb_chaos")).func_77655_b("orb.chaos");
    ItemsRegister.TRAVEL_LEGGINGS = new ItemArmorTravel(2, "travellegging", "armor/travel_leggings", 1);
    ItemsRegister.TRAVEL_BOOTS = new ItemArmorTravel(3, "travelboots", "armor/travel_boots", 0);
    ItemsRegister.TRAVEL_JUMPCHEST = new ItemArmorTravel(1, "jumpchest", "armor/travel_jumpchest", 2);
    ItemsRegister.TRAVEL_SLIMYHELMET = new ItemArmorTravel(0, "slimyhelmet", "armor/travel_slimy", 3);
    ItemsRegister.TRAVEL_SCUBAHELMET = new ItemArmorTravel(0, "scubahelmet", "armor/travel_scuba", 4);
    ItemsRegister.TRAVEL_HOODHELMET = new ItemArmorTravel(0, "hoodhelmet", "armor/travel_hood", 5);
    ItemsRegister.ANCIENT_HELMET = new ItemAncientArmor(0, "ancient_helmet");
    ItemsRegister.ANCIENT_CHESTPLATE = new ItemAncientArmor(1, "ancient_chestplate");
    ItemsRegister.ANCIENT_LEGGINGS = new ItemAncientArmor(2, "ancient_leggings");
    ItemsRegister.ANCIENT_BOOTS = new ItemAncientArmor(3, "ancient_boots");
    ItemsRegister.ANCIENT_HAMMER = new ItemAncientHammer();
    ItemsRegister.HANGGLIDER = new ItemHangGlider();
    ItemsRegister.BACKPACK = new ItemBackpack();
    ItemsRegister.DISC_MII = (new ItemDisc("miichallenge")).func_77655_b("miichallenge").func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    ItemsRegister.ORE_PRESENT = (Item)new ItemOrePresent();
    ItemsRegister.DYNAMITE = new BaseItemDynamite("dynamite", "explosive/dynamite");
    ItemsRegister.DYNAMITE_NINJA = new ItemDynamiteNinja("dynamite_ninja", "explosive/dynamite_ninja");
    ItemsRegister.DYNAMITE_BIG = new ItemDynamiteBig("dynamite_big", "explosive/dynamite_big");
    ItemsRegister.DYNAMITE_ENDIUM = new ItemDynamiteEndium("dynamite_endium", "explosive/dynamite_endium");
    ItemsRegister.BUCKET_SULFURIC = new ItemBucketBase((Block)BlocksRegister.FLUIDS_SULFURICWATER, "sulfuricwater", "bucket_sulfuric");
    ItemsRegister.BUCKET_ANGELIC = new ItemBucketBase((Block)BlocksRegister.FLUIDS_ANGELICWATER, "angelicwater", "bucket_angelic");
    ItemsRegister.SEEDPLANTER_LVL1 = new SeedPlanterSeedFood("seedplanter_lvl1", 3, 5, null);
    ItemsRegister.SEEDPLANTER_LVL2 = new SeedPlanterSeedFood("seedplanter_lvl2", 5, 10, null);
    ItemsRegister.SEEDPLANTER_LVL3 = new SeedPlanterSeedFood("seedplanter_lvl3", 7, 15, null);
    ItemsRegister.SEEDPLANTER_LVL4 = new SeedPlanterSeedFood("seedplanter_lvl4", 12, 20, null);
    ItemsRegister.SEEDPLANTER_LVL5 = new SeedPlanterSeedFood("seedplanter_lvl5", 12, 17, null);
    ItemsRegister.FOOD_HAM = new BaseFood("ham", 8, 8.0F, true);
    ItemsRegister.FOOD_MARINATED_PORKCHOP = new BaseFood("marinated_porkchop", 8, 8.0F, false);
    ItemsRegister.FOOD_MARINATED_CHICKEN = new BaseFood("marinated_chicken", 8, 8.0F, false);
    ItemsRegister.FOOD_MARINATED_STEAK = new BaseFood("marinated_steak", 8, 8.0F, false);
    ItemsRegister.FOOD_MARINATED_ROTTENFLESH = new BaseFood("marinated_rottenflesh", 8, 8.0F, false);
    ItemsRegister.FOOD_MARINATED_MUTTON = new BaseFood("marinated_mutton", 8, 8.0F, false);
    ItemsRegister.DOUBLE_XP_POTION = new ItemBoostDoubleXp("potion.doublexp", "PotionDoubleXp");
    ItemsRegister.POTION_MINER_FOU = new ItemBoostMinerFou("potion.minerfou", "PotionMinerFou");
    ItemsRegister.OBSIDIAN_ITEM_DOOR = new ItemObsidianDoor();
    ItemsRegister.STICK_PALADIUM = (new BaseItem("stick/paladium")).func_77655_b("stick.paladium");
    ItemsRegister.STICK_TITANE = (new BaseItem("stick/titane")).func_77655_b("stick.titane");
    ItemsRegister.STICK_AMETHYST = (new BaseItem("stick/amethyst")).func_77655_b("stick.amethyst");
    ItemsRegister.POTION_WITHER = new ItemPotion("potion.witherimbue", "PotionWither", new PotionEffect(PRegister_Potions.potionWither.field_76415_H, 2000, 1));
    ItemsRegister.POTION_FIRE = new ItemPotion("potion.fireimbue", "PotionFire", new PotionEffect(PRegister_Potions.potionFire.field_76415_H, 2000, 1));
    ItemsRegister.POTION_POSION = new ItemPotion("potion.poisonimbue", "PotionPoison", new PotionEffect(PRegister_Potions.potionPoison.field_76415_H, 2000, 1));
    ItemsRegister.POTION_SICKNESS = new ItemSplashPotion("potion.sickness", "SicknessPotion", new PotionEffect(Potion.field_76431_k.field_76415_H, 200, 1), 0);
    ItemsRegister.POTION_WEB = new ItemSplashPotion("potion.web", "WebPotion", new PotionEffect(Potion.field_76431_k.field_76415_H, 200, 1), 1);
    ItemsRegister.WITHER_SKULLFRAGMENT = (new BaseItem("WitherSkullFragment")).func_77655_b("witherSkullFragment");
    ItemsRegister.PALADIUM_CORE = (new BaseItem("paladiumCore")).func_77655_b("paladiumCore");
    ItemsRegister.DIAMOND_STRING = (new BaseItem("diamondString")).func_77655_b("diamondString");
    ItemsRegister.WING = (new BaseItem("wing")).func_77655_b("wing");
    ItemsRegister.POTION_LAUNCHER = (Item)new ItemPotionLauncher();
    ItemsRegister.MAGICAL_TOOL = (Item)new ItemMagicalTool();
    ItemsRegister.ENDIUM_GAUNTLET = (Item)new ItemEndiumGauntlet();
    ItemsRegister.EXTRAPOLATED_BUCKET = (Item)new ItemExtrapolatedBucket();
    ItemsRegister.LEGENDARYSTONE_RANDOM = (Item)new LegendaryStone(LegendaryStone.Effect.RANDOM);
    ItemsRegister.LEGENDARYSTONE_TELEPORTATION = (Item)new LegendaryStone(LegendaryStone.Effect.TELEPORTATION);
    ItemsRegister.LEGENDARYSTONE_INVISIBILITY = (Item)new LegendaryStone(LegendaryStone.Effect.INVISIBILITY);
    ItemsRegister.LEGENDARYSTONE_FORTUNE = (Item)new LegendaryStone(LegendaryStone.Effect.FORTUNE);
    ItemsRegister.LEGENDARYSTONE_POWER = (Item)new LegendaryStone(LegendaryStone.Effect.POWER);
    ItemsRegister.LEGENDARYSTONE_JOBS = (Item)new LegendaryStone(LegendaryStone.Effect.JOBS);
    ItemsRegister.LEGENDARYSTONE_CHAOS = (Item)new LegendaryStone(LegendaryStone.Effect.CHAOS);
    ItemsRegister.itemDummy = (Item)new ItemDummy();
    ItemsRegister.SPEAKER = (Item)new ItemSpeaker();
    ItemsRegister.VISION_HELMET = (Item)new ItemVisionHelmet();
    ItemsRegister.POWER_HELMET = (Item)new ItemPowerHelmet();
    ItemsRegister.FAUX = (Item)new ItemFaux();
    ItemsRegister.FOREUSE = (Item)new ItemForeuse();
    GameRegistry.registerItem(ItemsRegister.AMETHYST_INGOT, ItemsRegister.AMETHYST_INGOT
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TITANE_INGOT, ItemsRegister.TITANE_INGOT
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_INGOT, ItemsRegister.PALADIUM_INGOT
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_GREEN_INGOT, ItemsRegister.PALADIUM_GREEN_INGOT
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_INGOT, ItemsRegister.ENDIUM_INGOT
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_FRAGMENT, ItemsRegister.ENDIUM_FRAGMENT
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.FINDIUM, ItemsRegister.FINDIUM.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TRIXIUM, ItemsRegister.TRIXIUM.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_KEY, ItemsRegister.PALADIUM_KEY.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_NUGGET, ItemsRegister.ENDIUM_NUGGET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.AMETHYST_PICKAXE, ItemsRegister.AMETHYST_PICKAXE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.AMETHYST_SHOVEL, ItemsRegister.AMETHYST_SHOVEL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.AMETHYST_AXE, ItemsRegister.AMETHYST_AXE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.AMETHYST_SWORD, ItemsRegister.AMETHYST_SWORD
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.AMETHYST_HELMET, ItemsRegister.AMETHYST_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.AMETHYST_CHESTPLATE, ItemsRegister.AMETHYST_CHESTPLATE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.AMETHYST_LEGGINGS, ItemsRegister.AMETHYST_LEGGINGS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.AMETHYST_BOOTS, ItemsRegister.AMETHYST_BOOTS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TITANE_PICKAXE, ItemsRegister.TITANE_PICKAXE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TITANE_SHOVEL, ItemsRegister.TITANE_SHOVEL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TITANE_AXE, ItemsRegister.TITANE_AXE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TITANE_SWORD, ItemsRegister.TITANE_SWORD
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TITANE_HELMET, ItemsRegister.TITANE_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TITANE_CHESTPLATE, ItemsRegister.TITANE_CHESTPLATE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TITANE_LEGGINGS, ItemsRegister.TITANE_LEGGINGS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TITANE_BOOTS, ItemsRegister.TITANE_BOOTS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_PICKAXE, ItemsRegister.PALADIUM_PICKAXE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_SHOVEL, ItemsRegister.PALADIUM_SHOVEL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_AXE, ItemsRegister.PALADIUM_AXE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_SWORD, ItemsRegister.PALADIUM_SWORD
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_HELMET, ItemsRegister.PALADIUM_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_CHESTPLATE, ItemsRegister.PALADIUM_CHESTPLATE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_LEGGINGS, ItemsRegister.PALADIUM_LEGGINGS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_BOOTS, ItemsRegister.PALADIUM_BOOTS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TOURNAMENT_RED_HELMET, ItemsRegister.TOURNAMENT_RED_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TOURNAMENT_RED_CHESTPLATE, ItemsRegister.TOURNAMENT_RED_CHESTPLATE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TOURNAMENT_RED_LEGGINGS, ItemsRegister.TOURNAMENT_RED_LEGGINGS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TOURNAMENT_RED_BOOTS, ItemsRegister.TOURNAMENT_RED_BOOTS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TOURNAMENT_RED_SWORD, ItemsRegister.TOURNAMENT_RED_SWORD
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TOURNAMENT_BLUE_HELMET, ItemsRegister.TOURNAMENT_BLUE_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TOURNAMENT_BLUE_CHESTPLATE, ItemsRegister.TOURNAMENT_BLUE_CHESTPLATE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TOURNAMENT_BLUE_LEGGINGS, ItemsRegister.TOURNAMENT_BLUE_LEGGINGS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TOURNAMENT_BLUE_BOOTS, ItemsRegister.TOURNAMENT_BLUE_BOOTS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TOURNAMENT_BLUE_SWORD, ItemsRegister.TOURNAMENT_BLUE_SWORD
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_APPLE, ItemsRegister.PALADIUM_APPLE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_GREEN_PICKAXE, ItemsRegister.PALADIUM_GREEN_PICKAXE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_GREEN_SHOVEL, ItemsRegister.PALADIUM_GREEN_SHOVEL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_GREEN_AXE, ItemsRegister.PALADIUM_GREEN_AXE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_GREEN_SWORD, ItemsRegister.PALADIUM_GREEN_SWORD
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_GREEN_HELMET, ItemsRegister.PALADIUM_GREEN_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_GREEN_CHESTPLATE, ItemsRegister.PALADIUM_GREEN_CHESTPLATE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_GREEN_LEGGINGS, ItemsRegister.PALADIUM_GREEN_LEGGINGS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_GREEN_BOOTS, ItemsRegister.PALADIUM_GREEN_BOOTS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_PICKAXE, ItemsRegister.ENDIUM_PICKAXE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_AXE, ItemsRegister.ENDIUM_AXE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_SWORD, ItemsRegister.ENDIUM_SWORD
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_HELMET, ItemsRegister.ENDIUM_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_CHESTPLATE, ItemsRegister.ENDIUM_CHESTPLATE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_LEGGINGS, ItemsRegister.ENDIUM_LEGGINGS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_BOOTS, ItemsRegister.ENDIUM_BOOTS
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.CHESTEXPLORER, ItemsRegister.CHESTEXPLORER
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.VOIDSTONE, ItemsRegister.VOIDSTONE
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.VOIDSTONE_MINAGE, ItemsRegister.VOIDSTONE_MINAGE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SMALL_RING, ItemsRegister.SMALL_RING
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.MEDIUM_RING, ItemsRegister.MEDIUM_RING
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.BIG_RING, ItemsRegister.BIG_RING
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.RING_ENDIUM_SMALL, ItemsRegister.RING_ENDIUM_SMALL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.RING_ENDIUM_MEDIUM, ItemsRegister.RING_ENDIUM_MEDIUM
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.RING_ENDIUM_BIG, ItemsRegister.RING_ENDIUM_BIG
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.STICK_HEAL, ItemsRegister.STICK_HEAL
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.STICK_SPEED, ItemsRegister.STICK_SPEED
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.STICK_STRENGHT, ItemsRegister.STICK_STRENGHT
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.STICK_JUMP, ItemsRegister.STICK_JUMP
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.STICK_GOD, ItemsRegister.STICK_GOD
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.STICK_DAMAGE, ItemsRegister.STICK_DAMAGE
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.STICK_HYPERJUMP, ItemsRegister.STICK_HYPERJUMP
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.STICK_TELEPORT, ItemsRegister.STICK_TELEPORT
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.STICK_MODERATION, ItemsRegister.STICK_MODERATION
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.UNCLAIMFINDER, ItemsRegister.UNCLAIMFINDER
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.UNCLAIMFINDER_ORANGE, ItemsRegister.UNCLAIMFINDER_ORANGE
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.UNCLAIMFINDER_ROUGE, ItemsRegister.UNCLAIMFINDER_ROUGE
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.UNCLAIMFINDER_BLEU, ItemsRegister.UNCLAIMFINDER_BLEU
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.PALADIUM_BOW, ItemsRegister.PALADIUM_BOW
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.INFERNAL_KNOCKER, ItemsRegister.INFERNAL_KNOCKER
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.ARROW_POISON, ItemsRegister.ARROW_POISON
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.ARROW_SLOWNESS, ItemsRegister.ARROW_SLOWNESS
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.ARROW_SWITCH, ItemsRegister.ARROW_SWITCH
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.ARROW_WITHER, ItemsRegister.ARROW_WITHER
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.ARROW_FROST, ItemsRegister.ARROW_FROST
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PARTICLE_IRON, ItemsRegister.PARTICLE_IRON
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PARTICLE_GOLD, ItemsRegister.PARTICLE_GOLD
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PARTICLE_DIAMOND, ItemsRegister.PARTICLE_DIAMOND
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PARTICLE_AMETHYST, ItemsRegister.PARTICLE_AMETHYST
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PARTICLE_TITANE, ItemsRegister.PARTICLE_TITANE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PARTICLE_PALADIUM, ItemsRegister.PARTICLE_PALADIUM
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.FRUIT_ORANGEBLUE, ItemsRegister.FRUIT_ORANGEBLUE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.FRUIT_KIWANO, ItemsRegister.FRUIT_KIWANO
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.FRUIT_CHERVIL, ItemsRegister.FRUIT_CHERVIL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.FRUIT_EGGPLANT, ItemsRegister.FRUIT_EGGPLANT
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.XP_BERRY, ItemsRegister.XP_BERRY
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.COMPRESSED_XP_BERRY, ItemsRegister.COMPRESSED_XP_BERRY
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.SEED_ORANGEBLUE, ItemsRegister.SEED_ORANGEBLUE
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.SEED_KIWANO, ItemsRegister.SEED_KIWANO
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.SEED_CHERVIL, ItemsRegister.SEED_CHERVIL
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.SEED_EGGPLANT, ItemsRegister.SEED_EGGPLANT
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.SEED_ICE, ItemsRegister.SEED_ICE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.FURNACE_UPGRADE, ItemsRegister.FURNACE_UPGRADE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALAMIXEDCOAL, ItemsRegister.PALAMIXEDCOAL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.GOLDMIXEDCOAL, ItemsRegister.GOLDMIXEDCOAL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.AMETHYSTMIXEDCOAL, ItemsRegister.AMETHYSTMIXEDCOAL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TITANEMIXEDCOAL, ItemsRegister.TITANEMIXEDCOAL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.BOWUPGRADE_EMPTY, ItemsRegister.BOWUPGRADE_EMPTY
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.BOWUPGRADE_RANGEMODIFIER, ItemsRegister.BOWUPGRADE_RANGEMODIFIER
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.BOWUPGRADE_SPEEDMODIFIER, ItemsRegister.BOWUPGRADE_SPEEDMODIFIER
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.COMPRESSED_ENDIUM, ItemsRegister.COMPRESSED_ENDIUM
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.COMPRESSED_PALADIUM, ItemsRegister.COMPRESSED_PALADIUM
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.COMPRESSED_TITANE, ItemsRegister.COMPRESSED_TITANE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.COMPRESSED_AMETHYST, ItemsRegister.COMPRESSED_AMETHYST
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ORB_HEAL, ItemsRegister.ORB_HEAL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ORB_JUMP, ItemsRegister.ORB_JUMP
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ORB_KNOCKBACK, ItemsRegister.ORB_KNOCKBACK
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ORB_SPEED, ItemsRegister.ORB_SPEED
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ORB_STRENGHT, ItemsRegister.ORB_STRENGHT
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.TRAVEL_BOOTS, ItemsRegister.TRAVEL_BOOTS
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.TRAVEL_LEGGINGS, ItemsRegister.TRAVEL_LEGGINGS
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.TRAVEL_HOODHELMET, ItemsRegister.TRAVEL_HOODHELMET
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.TRAVEL_JUMPCHEST, ItemsRegister.TRAVEL_JUMPCHEST
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.TRAVEL_SCUBAHELMET, ItemsRegister.TRAVEL_SCUBAHELMET
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.TRAVEL_SLIMYHELMET, ItemsRegister.TRAVEL_SLIMYHELMET
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.ANCIENT_HELMET, ItemsRegister.ANCIENT_HELMET.func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.ANCIENT_CHESTPLATE, ItemsRegister.ANCIENT_CHESTPLATE.func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.ANCIENT_LEGGINGS, ItemsRegister.ANCIENT_LEGGINGS.func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.ANCIENT_BOOTS, ItemsRegister.ANCIENT_BOOTS.func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.ANCIENT_HAMMER, ItemsRegister.ANCIENT_HAMMER.func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.HANGGLIDER, ItemsRegister.HANGGLIDER
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.BACKPACK, ItemsRegister.BACKPACK
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.money, ItemsRegister.money.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.MAGICAL_TOOL, ItemsRegister.MAGICAL_TOOL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_GAUNTLET, ItemsRegister.ENDIUM_GAUNTLET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.DISC_MII, ItemsRegister.DISC_MII
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ORE_PRESENT, ItemsRegister.ORE_PRESENT
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.DYNAMITE, ItemsRegister.DYNAMITE
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.DYNAMITE_NINJA, ItemsRegister.DYNAMITE_NINJA
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.DYNAMITE_BIG, ItemsRegister.DYNAMITE_BIG
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.DYNAMITE_ENDIUM, ItemsRegister.DYNAMITE_ENDIUM
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.BUCKET_SULFURIC, ItemsRegister.BUCKET_SULFURIC
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.BUCKET_ANGELIC, ItemsRegister.BUCKET_ANGELIC
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.SEEDPLANTER_LVL1, ItemsRegister.SEEDPLANTER_LVL1
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.SEEDPLANTER_LVL2, ItemsRegister.SEEDPLANTER_LVL2
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.SEEDPLANTER_LVL3, ItemsRegister.SEEDPLANTER_LVL3
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.SEEDPLANTER_LVL4, ItemsRegister.SEEDPLANTER_LVL4
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.SEEDPLANTER_LVL5, ItemsRegister.SEEDPLANTER_LVL5
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.FOOD_HAM, ItemsRegister.FOOD_HAM
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.FOOD_MARINATED_CHICKEN, ItemsRegister.FOOD_MARINATED_CHICKEN
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.FOOD_MARINATED_MUTTON, ItemsRegister.FOOD_MARINATED_MUTTON
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.FOOD_MARINATED_PORKCHOP, ItemsRegister.FOOD_MARINATED_PORKCHOP
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.FOOD_MARINATED_ROTTENFLESH, ItemsRegister.FOOD_MARINATED_ROTTENFLESH
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.FOOD_MARINATED_STEAK, ItemsRegister.FOOD_MARINATED_STEAK
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.OBSIDIAN_ITEM_DOOR, ItemsRegister.OBSIDIAN_ITEM_DOOR
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.Farmer, ItemsRegister.Farmer.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.Miner, ItemsRegister.Miner.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.Hunter, ItemsRegister.Hunter.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.Alchemist, ItemsRegister.Alchemist
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.Job, ItemsRegister.Job.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PB, ItemsRegister.PB.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.STICK_PALADIUM, ItemsRegister.STICK_PALADIUM
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.STICK_TITANE, ItemsRegister.STICK_TITANE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.STICK_AMETHYST, ItemsRegister.STICK_AMETHYST
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.POTION_FIRE, ItemsRegister.POTION_FIRE
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.POTION_POSION, ItemsRegister.POTION_POSION
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.POTION_WITHER, ItemsRegister.POTION_WITHER
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.POTION_SICKNESS, ItemsRegister.POTION_SICKNESS
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.POTION_WEB, ItemsRegister.POTION_WEB
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.POTION_MINER_FOU, ItemsRegister.POTION_MINER_FOU
        .func_77658_a());
    GameRegistry.registerItem((Item)ItemsRegister.DOUBLE_XP_POTION, ItemsRegister.DOUBLE_XP_POTION
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.WITHER_SKULLFRAGMENT, ItemsRegister.WITHER_SKULLFRAGMENT
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_CORE, ItemsRegister.PALADIUM_CORE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.DIAMOND_STRING, ItemsRegister.DIAMOND_STRING
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.WING, ItemsRegister.WING.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.POTION_LAUNCHER, ItemsRegister.POTION_LAUNCHER
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PLATE, ItemsRegister.PLATE.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PLATE_ENCHANTED, ItemsRegister.PLATE_ENCHANTED
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CHASE, ItemsRegister.CHASE.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_INK, ItemsRegister.PALADIUM_INK
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SPEAKER, ItemsRegister.SPEAKER.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.VISION_HELMET, ItemsRegister.VISION_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.POWER_HELMET, ItemsRegister.POWER_HELMET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.FAUX, ItemsRegister.FAUX.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.FOREUSE, ItemsRegister.FOREUSE.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.WITHER_DUST, ItemsRegister.WITHER_DUST
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.LEGENDARYSTONE_RANDOM, ItemsRegister.LEGENDARYSTONE_RANDOM
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.LEGENDARYSTONE_TELEPORTATION, ItemsRegister.LEGENDARYSTONE_TELEPORTATION
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.LEGENDARYSTONE_INVISIBILITY, ItemsRegister.LEGENDARYSTONE_INVISIBILITY
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.LEGENDARYSTONE_FORTUNE, ItemsRegister.LEGENDARYSTONE_FORTUNE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.LEGENDARYSTONE_POWER, ItemsRegister.LEGENDARYSTONE_POWER
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.LEGENDARYSTONE_JOBS, ItemsRegister.LEGENDARYSTONE_JOBS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.LEGENDARYSTONE_CHAOS, ItemsRegister.LEGENDARYSTONE_CHAOS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.EXTRAPOLATED_BUCKET, ItemsRegister.EXTRAPOLATED_BUCKET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.itemDummy, "Dummy");
    ItemsRegister.HALLOWEEN_HAT = (Item)new HalloweenHat();
    ItemsRegister.JOB_CANDY = (Item)new ItemCandy();
    ItemsRegister.HALLOWEEN_AXE = (Item)new ItemHalloweenAxe();
    ItemsRegister.DISC_NOEL = (new ItemDisc("noel")).func_77655_b("noel").func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    GameRegistry.registerItem(ItemsRegister.HALLOWEEN_AXE, ItemsRegister.HALLOWEEN_AXE.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.JOB_CANDY, ItemsRegister.JOB_CANDY.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.HALLOWEEN_HAT, ItemsRegister.HALLOWEEN_HAT.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.DISC_NOEL, ItemsRegister.DISC_NOEL.func_77658_a());
    ItemsRegister.CHRISMAS_HAMMER = (new ItemChrismasHammer(PToolMaterial.paladium, "chrismas_hammer", ItemsRegister.PALADIUM_INGOT)).func_77655_b("paladium.chrismas_hammer");
    GameRegistry.registerItem(ItemsRegister.CHRISMAS_HAMMER, ItemsRegister.CHRISMAS_HAMMER.func_77658_a());
    ItemsRegister.PISTOL_TANK = (Item)new ItemTankPistol("pistol_tank");
    GameRegistry.registerItem(ItemsRegister.PISTOL_TANK, ItemsRegister.PISTOL_TANK.func_77658_a());
    ItemsRegister.COSMETIC_SKIN_NOEL = (Item)(new ItemNoelCosmetic("cosmetic_skin_noel")).setName("Déguisement de golem de noel").setType(CosmeticType.SKIN);
    GameRegistry.registerItem(ItemsRegister.COSMETIC_SKIN_NOEL, ItemsRegister.COSMETIC_SKIN_NOEL.func_77658_a());
    ChestGenHooks.addItem("villageBlacksmith", new WeightedRandomChestContent(new ItemStack(ItemsRegister.AMETHYST_CHESTPLATE), 1, 1, 2));
    ChestGenHooks.addItem("dungeonChest", new WeightedRandomChestContent(new ItemStack(ItemsRegister.AMETHYST_CHESTPLATE), 1, 1, 2));
    ChestGenHooks.addItem("pyramidDesertyChest", new WeightedRandomChestContent(new ItemStack(ItemsRegister.AMETHYST_CHESTPLATE), 1, 1, 2));
    ChestGenHooks.addItem("strongholdLibrary", new WeightedRandomChestContent(new ItemStack(ItemsRegister.AMETHYST_CHESTPLATE), 1, 1, 2));
    ChestGenHooks.addItem("villageBlacksmith", new WeightedRandomChestContent(new ItemStack(ItemsRegister.AMETHYST_SWORD), 1, 1, 2));
    ChestGenHooks.addItem("dungeonChest", new WeightedRandomChestContent(new ItemStack(ItemsRegister.AMETHYST_SWORD), 1, 1, 2));
    ChestGenHooks.addItem("pyramidDesertyChest", new WeightedRandomChestContent(new ItemStack(ItemsRegister.AMETHYST_SWORD), 1, 1, 2));
    ChestGenHooks.addItem("strongholdLibrary", new WeightedRandomChestContent(new ItemStack(ItemsRegister.AMETHYST_SWORD), 1, 1, 2));
    ChestGenHooks.addItem("villageBlacksmith", new WeightedRandomChestContent(new ItemStack(ItemsRegister.PALADIUM_INGOT), 1, 2, 1));
    ChestGenHooks.addItem("dungeonChest", new WeightedRandomChestContent(new ItemStack(ItemsRegister.PALADIUM_INGOT), 1, 2, 1));
    ChestGenHooks.addItem("pyramidDesertyChest", new WeightedRandomChestContent(new ItemStack(ItemsRegister.PALADIUM_INGOT), 1, 3, 2));
    ChestGenHooks.addItem("strongholdLibrary", new WeightedRandomChestContent(new ItemStack(ItemsRegister.PALADIUM_INGOT), 1, 3, 2));
    ChestGenHooks.addItem("villageBlacksmith", new WeightedRandomChestContent(new ItemStack(ItemsRegister.PALADIUM_SWORD), 1, 1, 2));
    ChestGenHooks.addItem("dungeonChest", new WeightedRandomChestContent(new ItemStack(ItemsRegister.PALADIUM_SWORD), 1, 1, 2));
    ChestGenHooks.addItem("pyramidDesertyChest", new WeightedRandomChestContent(new ItemStack(ItemsRegister.PALADIUM_SWORD), 1, 1, 2));
    ChestGenHooks.addItem("strongholdLibrary", new WeightedRandomChestContent(new ItemStack(ItemsRegister.PALADIUM_SWORD), 1, 1, 2));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\registerer\PRegister_Items.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */