package fr.paladium.palamod.modules.miner.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.miner.item.ItemAmethystExcavator;
import fr.paladium.palamod.modules.miner.item.ItemBuilderWand;
import fr.paladium.palamod.modules.miner.item.ItemCobbleBreakerUpgrade;
import fr.paladium.palamod.modules.miner.item.ItemGodPickaxe;
import fr.paladium.palamod.modules.miner.item.ItemGodPickaxeUpgrade;
import fr.paladium.palamod.modules.miner.item.ItemHardnessPotion;
import fr.paladium.palamod.modules.miner.item.ItemMagnet;
import fr.paladium.palamod.modules.miner.item.ItemMoulaStone;
import fr.paladium.palamod.modules.miner.item.ItemPaladiumExcavator;
import fr.paladium.palamod.modules.miner.item.ItemSealedXpBottle;
import fr.paladium.palamod.modules.miner.item.ItemSpawnerFinder;
import fr.paladium.palamod.modules.miner.item.ItemTitaneExcavator;
import fr.paladium.palamod.modules.paladium.common.logics.cobblebreaker.CobbleBreakerUpgrade;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Items {
  public static void register() {
    ItemsRegister.SPAWNER_FINDER = (Item)new ItemSpawnerFinder();
    ItemsRegister.BUILDER_WAND = (Item)new ItemBuilderWand();
    ItemsRegister.AMETHYST_EXCAVATOR = (Item)new ItemAmethystExcavator();
    ItemsRegister.TITANE_EXCAVATOR = (Item)new ItemTitaneExcavator();
    ItemsRegister.PALADIUM_EXCAVATOR = (Item)new ItemPaladiumExcavator();
    ItemsRegister.COBBLEBREAKER_AMETHYST_UPGRADE = (Item)new ItemCobbleBreakerUpgrade("cobblebreaker_amethyst_upgrade", CobbleBreakerUpgrade.AMETHYST);
    ItemsRegister.COBBLEBREAKER_TITANE_UPGRADE = (Item)new ItemCobbleBreakerUpgrade("cobblebreaker_titane_upgrade", CobbleBreakerUpgrade.TITANE);
    ItemsRegister.COBBLEBREAKER_PALADIUM_UPGRADE = (Item)new ItemCobbleBreakerUpgrade("cobblebreaker_paladium_upgrade", CobbleBreakerUpgrade.PALADIUM);
    ItemsRegister.MAGNET = (Item)new ItemMagnet();
    ItemsRegister.HARDNESS_POTION = (Item)new ItemHardnessPotion();
    ItemsRegister.SEALED_XP_BOTTLE = (Item)new ItemSealedXpBottle();
    ItemsRegister.GOD_PICKAXE = (Item)new ItemGodPickaxe();
    ItemsRegister.GOD_PICKAXE_AUTOSMELT = (Item)new ItemGodPickaxeUpgrade(ItemGodPickaxe.Upgrades.AUTO_SMELT);
    ItemsRegister.GOD_PICKAXE_BIGHOLE = (Item)new ItemGodPickaxeUpgrade(ItemGodPickaxe.Upgrades.BIG_HOLE);
    ItemsRegister.MOULA_STONE = (Item)new ItemMoulaStone();
    ItemsRegister.INVOKER_STONE = (new Item()).func_77637_a((CreativeTabs)Registry.MINER_TAB).func_111206_d("palamod:invockerstone").func_77655_b("invocker_stone");
    GameRegistry.registerItem(ItemsRegister.SPAWNER_FINDER, ItemsRegister.SPAWNER_FINDER.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.BUILDER_WAND, ItemsRegister.BUILDER_WAND.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.AMETHYST_EXCAVATOR, ItemsRegister.AMETHYST_EXCAVATOR.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TITANE_EXCAVATOR, ItemsRegister.TITANE_EXCAVATOR.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_EXCAVATOR, ItemsRegister.PALADIUM_EXCAVATOR.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.COBBLEBREAKER_AMETHYST_UPGRADE, ItemsRegister.COBBLEBREAKER_AMETHYST_UPGRADE.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.COBBLEBREAKER_TITANE_UPGRADE, ItemsRegister.COBBLEBREAKER_TITANE_UPGRADE.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.COBBLEBREAKER_PALADIUM_UPGRADE, ItemsRegister.COBBLEBREAKER_PALADIUM_UPGRADE.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.MAGNET, ItemsRegister.MAGNET.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.HARDNESS_POTION, ItemsRegister.HARDNESS_POTION.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SEALED_XP_BOTTLE, ItemsRegister.SEALED_XP_BOTTLE.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.GOD_PICKAXE, ItemsRegister.GOD_PICKAXE.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.GOD_PICKAXE_AUTOSMELT, ItemsRegister.GOD_PICKAXE_AUTOSMELT.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.GOD_PICKAXE_BIGHOLE, ItemsRegister.GOD_PICKAXE_BIGHOLE.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.MOULA_STONE, ItemsRegister.MOULA_STONE.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.INVOKER_STONE, ItemsRegister.INVOKER_STONE.func_77658_a());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\init\Items.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */