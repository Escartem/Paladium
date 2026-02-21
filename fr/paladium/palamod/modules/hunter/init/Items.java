package fr.paladium.palamod.modules.hunter.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.hunter.items.ItemAmulet;
import fr.paladium.palamod.modules.hunter.items.ItemCaptureStone;
import fr.paladium.palamod.modules.hunter.items.ItemCaptureSword;
import fr.paladium.palamod.modules.hunter.items.ItemDolphinNoiseBox;
import fr.paladium.palamod.modules.hunter.items.ItemEndiumNametag;
import fr.paladium.palamod.modules.hunter.items.ItemHunterBackpack;
import fr.paladium.palamod.modules.hunter.items.ItemHunterCompass;
import fr.paladium.palamod.modules.hunter.items.ItemSnailShell;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Items {
  public static void register() {
    ItemsRegister.HUNTER_BACKPACK = (Item)new ItemHunterBackpack();
    ItemsRegister.ENDIUM_NAMETAG = (Item)new ItemEndiumNametag();
    ItemsRegister.CAPTURE_STONE = (Item)new ItemCaptureStone();
    ItemsRegister.CAPTURE_SWORD = (Item)new ItemCaptureSword();
    ItemsRegister.HUNTER_AMULET = (Item)new ItemAmulet(ItemAmulet.Type.HUNTER);
    ItemsRegister.SEEING_AMULET = (Item)new ItemAmulet(ItemAmulet.Type.VOYANCE);
    ItemsRegister.DISCRETION_AMULET = (Item)new ItemAmulet(ItemAmulet.Type.DISCRETION);
    ItemsRegister.COMBAT_AMULET = (Item)new ItemAmulet(ItemAmulet.Type.COMBAT);
    ItemsRegister.MAGMA_AMULET = (Item)new ItemAmulet(ItemAmulet.Type.MAGMA);
    ItemsRegister.DAEMON_AMULET = (Item)new ItemAmulet(ItemAmulet.Type.DAEMON);
    ItemsRegister.SNAIL_SHELL = (Item)new ItemSnailShell();
    ItemsRegister.HUNTER_COMPASS = (Item)new ItemHunterCompass();
    ItemsRegister.TURTLE_SCALES = (new Item()).func_77637_a((CreativeTabs)Registry.HUNTER_TAB).func_77655_b("turtle_scales").func_111206_d("palamod:turtle_scales");
    ItemsRegister.PANDA_DROOL = (new Item()).func_77637_a((CreativeTabs)Registry.HUNTER_TAB).func_77655_b("panda_drool").func_111206_d("palamod:panda_drool");
    ItemsRegister.PARROT_FEATHER = (new Item()).func_77637_a((CreativeTabs)Registry.HUNTER_TAB).func_77655_b("parrot_feather").func_111206_d("palamod:parrot_feather");
    ItemsRegister.CRAB_PLIERS = (new Item()).func_77637_a((CreativeTabs)Registry.HUNTER_TAB).func_77655_b("crab_pliers").func_111206_d("palamod:crab_pliers");
    ItemsRegister.TUSK = (new Item()).func_77637_a((CreativeTabs)Registry.HUNTER_TAB).func_77655_b("tusk").func_111206_d("palamod:tusk");
    ItemsRegister.SNAKE_VENOM = (new Item()).func_77637_a((CreativeTabs)Registry.HUNTER_TAB).func_77655_b("snake_venom").func_111206_d("palamod:snake_venom");
    ItemsRegister.MEDUSE_HOOK = (new Item()).func_77637_a((CreativeTabs)Registry.HUNTER_TAB).func_77655_b("meduse_hook").func_111206_d("palamod:meduse_hook");
    ItemsRegister.GOAT_SHOE = (new Item()).func_77637_a((CreativeTabs)Registry.HUNTER_TAB).func_77655_b("goat_shoe").func_111206_d("palamod:goat_shoe");
    ItemsRegister.DOLPHIN_NOISE_BOX = (new ItemDolphinNoiseBox()).func_77637_a((CreativeTabs)Registry.HUNTER_TAB).func_77655_b("dolphin_noise_box").func_111206_d("palamod:dolphin_noise_box");
    GameRegistry.registerItem(ItemsRegister.HUNTER_BACKPACK, ItemsRegister.HUNTER_BACKPACK
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_NAMETAG, ItemsRegister.ENDIUM_NAMETAG
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CAPTURE_STONE, ItemsRegister.CAPTURE_STONE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CAPTURE_SWORD, ItemsRegister.CAPTURE_SWORD
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.HUNTER_AMULET, ItemsRegister.HUNTER_AMULET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SEEING_AMULET, ItemsRegister.SEEING_AMULET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.DISCRETION_AMULET, ItemsRegister.DISCRETION_AMULET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.COMBAT_AMULET, ItemsRegister.COMBAT_AMULET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.MAGMA_AMULET, ItemsRegister.MAGMA_AMULET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.DAEMON_AMULET, ItemsRegister.DAEMON_AMULET
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TURTLE_SCALES, ItemsRegister.TURTLE_SCALES
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PANDA_DROOL, ItemsRegister.PANDA_DROOL
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PARROT_FEATHER, ItemsRegister.PARROT_FEATHER
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.CRAB_PLIERS, ItemsRegister.CRAB_PLIERS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.TUSK, ItemsRegister.TUSK.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SNAKE_VENOM, ItemsRegister.SNAKE_VENOM
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.MEDUSE_HOOK, ItemsRegister.MEDUSE_HOOK
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.GOAT_SHOE, ItemsRegister.GOAT_SHOE
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.DOLPHIN_NOISE_BOX, ItemsRegister.DOLPHIN_NOISE_BOX
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.HUNTER_COMPASS, ItemsRegister.HUNTER_COMPASS
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.SNAIL_SHELL, ItemsRegister.SNAIL_SHELL
        .func_77658_a());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\init\Items.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */