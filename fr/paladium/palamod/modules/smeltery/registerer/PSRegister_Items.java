package fr.paladium.palamod.modules.smeltery.registerer;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.modules.smeltery.items.ItemGrinderModifier;
import fr.paladium.palamod.modules.smeltery.items.ItemPatern;
import fr.paladium.palamod.modules.smeltery.items.ItemToolPart;
import fr.paladium.palamod.modules.smeltery.items.tools.ItemAmethystHammer;
import fr.paladium.palamod.modules.smeltery.items.tools.ItemPaladiumHammer;
import fr.paladium.palamod.modules.smeltery.items.tools.ItemSmithHammer;
import fr.paladium.palamod.modules.smeltery.items.tools.ItemTitaneHammer;
import fr.paladium.palamod.modules.smeltery.items.weapons.ItemAmethystBroadsword;
import fr.paladium.palamod.modules.smeltery.items.weapons.ItemAmethystFastsword;
import fr.paladium.palamod.modules.smeltery.items.weapons.ItemPaladiumBroadsword;
import fr.paladium.palamod.modules.smeltery.items.weapons.ItemPaladiumFastsword;
import fr.paladium.palamod.modules.smeltery.items.weapons.ItemTitaneBroadsword;
import fr.paladium.palamod.modules.smeltery.items.weapons.ItemTitaneFastsword;
import net.minecraft.item.Item;

public class PSRegister_Items {
  public static ItemPatern PATERN_AXE;
  
  public static ItemPatern PATERN_HAMMER;
  
  public static ItemPatern PATERN_SHOVEL;
  
  public static ItemPatern PATERN_BROADSWORD;
  
  public static ItemPatern PATERN_FASTSWORD;
  
  public static ItemPatern PATERN_PICKAXE;
  
  public static ItemPatern PATERN_SWORD;
  
  public static ItemPatern PATERN_INGOT;
  
  public static ItemPatern PATERN_BLOCK;
  
  public static ItemPatern PATERN_SOCKET;
  
  public static ItemToolPart HEAD_FASTSWORD;
  
  public static ItemToolPart HEAD_BROADSWORD;
  
  public static ItemToolPart HEAD_PICKAXE;
  
  public static ItemToolPart HEAD_SWORD;
  
  public static ItemToolPart HEAD_HAMMER;
  
  public static ItemToolPart HEAD_AXE;
  
  public static ItemToolPart HEAD_SHOVEL;
  
  public static ItemGrinderModifier MODIFIER_SMELT;
  
  public static ItemGrinderModifier MODIFIER_FORTUNE;
  
  public static ItemGrinderModifier MODIFIER_SPEED;
  
  public static ItemGrinderModifier MODIFIER_DAMAGE;
  
  public static ItemGrinderModifier MODIFIER_FLAME;
  
  public static ItemGrinderModifier MODIFIER_KNOCKBACK;
  
  public static ItemGrinderModifier MODIFIER_AUTOREPAIR;
  
  public static ItemGrinderModifier MODIFIER_MOREUPGRADE;
  
  public static ItemPaladiumHammer HAMMER_PALADIUM;
  
  public static ItemTitaneHammer HAMMER_TITANE;
  
  public static ItemAmethystHammer HAMMER_AMETHYST;
  
  public static ItemSmithHammer HAMMER_SMITH;
  
  public static ItemPaladiumBroadsword BROADSWORD_PALADIUM;
  
  public static ItemTitaneBroadsword BROADSWORD_TITANE;
  
  public static ItemAmethystBroadsword BROADSWORD_AMETHYST;
  
  public static ItemPaladiumFastsword FASTSWORD_PALADIUM;
  
  public static ItemTitaneFastsword FASTSWORD_TITANE;
  
  public static ItemAmethystFastsword FASTSWORD_AMETHYST;
  
  public static void init() {
    PATERN_AXE = new ItemPatern("patern.axe", "axe", ItemPatern.AXE);
    PATERN_HAMMER = new ItemPatern("patern.hammer", "hammer", ItemPatern.HAMMER);
    PATERN_SHOVEL = new ItemPatern("patern.shovel", "shovel", ItemPatern.SHOVEL);
    PATERN_BROADSWORD = new ItemPatern("patern.broadsword", "broadsword", ItemPatern.BROADSWORD);
    PATERN_FASTSWORD = new ItemPatern("patern.fastsword", "fastsword", ItemPatern.FASTSWORD);
    PATERN_PICKAXE = new ItemPatern("patern.pickaxe", "pickaxe", ItemPatern.PICKAXE);
    PATERN_SWORD = new ItemPatern("patern.sword", "sword", ItemPatern.SWORD);
    PATERN_INGOT = new ItemPatern("patern.ingot", "ingot", ItemPatern.INGOT);
    PATERN_BLOCK = new ItemPatern("patern.block", "block", 8);
    PATERN_SOCKET = new ItemPatern("patern.socket", "socket", ItemPatern.SOCKET);
    GameRegistry.registerItem((Item)PATERN_AXE, PATERN_AXE.func_77658_a());
    GameRegistry.registerItem((Item)PATERN_HAMMER, PATERN_HAMMER.func_77658_a());
    GameRegistry.registerItem((Item)PATERN_SHOVEL, PATERN_SHOVEL.func_77658_a());
    GameRegistry.registerItem((Item)PATERN_BROADSWORD, PATERN_BROADSWORD.func_77658_a());
    GameRegistry.registerItem((Item)PATERN_FASTSWORD, PATERN_FASTSWORD.func_77658_a());
    GameRegistry.registerItem((Item)PATERN_PICKAXE, PATERN_PICKAXE.func_77658_a());
    GameRegistry.registerItem((Item)PATERN_SWORD, PATERN_SWORD.func_77658_a());
    GameRegistry.registerItem((Item)PATERN_INGOT, PATERN_INGOT.func_77658_a());
    GameRegistry.registerItem((Item)PATERN_BLOCK, PATERN_BLOCK.func_77658_a());
    GameRegistry.registerItem((Item)PATERN_SOCKET, PATERN_SOCKET.func_77658_a());
    HEAD_FASTSWORD = new ItemToolPart("head_fastsword", "toolpart.head_fastsword");
    HEAD_BROADSWORD = new ItemToolPart("head_broadsword", "toolpart.head_broadsword");
    HEAD_PICKAXE = new ItemToolPart("head_pickaxe", "toolpart.head_pickaxe");
    HEAD_SWORD = new ItemToolPart("head_sword", "toolpart.head_sword");
    HEAD_HAMMER = new ItemToolPart("head_hammer", "toolpart.head_hammer");
    HEAD_AXE = new ItemToolPart("head_axe", "toolpart.head_axe");
    HEAD_SHOVEL = new ItemToolPart("head_shovel", "toolpart.head_shovel");
    GameRegistry.registerItem((Item)HEAD_FASTSWORD, HEAD_FASTSWORD.func_77658_a());
    GameRegistry.registerItem((Item)HEAD_BROADSWORD, HEAD_BROADSWORD.func_77658_a());
    GameRegistry.registerItem((Item)HEAD_PICKAXE, HEAD_PICKAXE.func_77658_a());
    GameRegistry.registerItem((Item)HEAD_SWORD, HEAD_SWORD.func_77658_a());
    GameRegistry.registerItem((Item)HEAD_HAMMER, HEAD_HAMMER.func_77658_a());
    GameRegistry.registerItem((Item)HEAD_AXE, HEAD_AXE.func_77658_a());
    GameRegistry.registerItem((Item)HEAD_SHOVEL, HEAD_SHOVEL.func_77658_a());
    MODIFIER_SMELT = new ItemGrinderModifier("modifier.smelt", "smelt");
    MODIFIER_FORTUNE = new ItemGrinderModifier("modifier.fortune", "fortune");
    MODIFIER_SPEED = new ItemGrinderModifier("modifier.speed", "speed");
    MODIFIER_DAMAGE = new ItemGrinderModifier("modifier.damage", "damage");
    MODIFIER_FLAME = new ItemGrinderModifier("modifier.flame", "flame");
    MODIFIER_KNOCKBACK = new ItemGrinderModifier("modifier.knockback", "knockback");
    MODIFIER_AUTOREPAIR = new ItemGrinderModifier("modifier.autorepair", "autorepair");
    MODIFIER_MOREUPGRADE = new ItemGrinderModifier("modifier.moreupgrade", "moreupgrade");
    GameRegistry.registerItem((Item)MODIFIER_SMELT, MODIFIER_SMELT.func_77658_a());
    GameRegistry.registerItem((Item)MODIFIER_FORTUNE, MODIFIER_FORTUNE.func_77658_a());
    GameRegistry.registerItem((Item)MODIFIER_SPEED, MODIFIER_SPEED.func_77658_a());
    GameRegistry.registerItem((Item)MODIFIER_DAMAGE, MODIFIER_DAMAGE.func_77658_a());
    GameRegistry.registerItem((Item)MODIFIER_FLAME, MODIFIER_FLAME.func_77658_a());
    GameRegistry.registerItem((Item)MODIFIER_KNOCKBACK, MODIFIER_KNOCKBACK.func_77658_a());
    GameRegistry.registerItem((Item)MODIFIER_AUTOREPAIR, MODIFIER_AUTOREPAIR.func_77658_a());
    GameRegistry.registerItem((Item)MODIFIER_MOREUPGRADE, MODIFIER_MOREUPGRADE.func_77658_a());
    HAMMER_PALADIUM = new ItemPaladiumHammer();
    HAMMER_TITANE = new ItemTitaneHammer();
    HAMMER_AMETHYST = new ItemAmethystHammer();
    HAMMER_SMITH = new ItemSmithHammer();
    GameRegistry.registerItem((Item)HAMMER_PALADIUM, HAMMER_PALADIUM.func_77658_a());
    GameRegistry.registerItem((Item)HAMMER_TITANE, HAMMER_TITANE.func_77658_a());
    GameRegistry.registerItem((Item)HAMMER_AMETHYST, HAMMER_AMETHYST.func_77658_a());
    GameRegistry.registerItem((Item)HAMMER_SMITH, HAMMER_SMITH.func_77658_a());
    BROADSWORD_PALADIUM = new ItemPaladiumBroadsword();
    BROADSWORD_TITANE = new ItemTitaneBroadsword();
    BROADSWORD_AMETHYST = new ItemAmethystBroadsword();
    GameRegistry.registerItem((Item)BROADSWORD_PALADIUM, BROADSWORD_PALADIUM.func_77658_a());
    GameRegistry.registerItem((Item)BROADSWORD_TITANE, BROADSWORD_TITANE.func_77658_a());
    GameRegistry.registerItem((Item)BROADSWORD_AMETHYST, BROADSWORD_AMETHYST.func_77658_a());
    FASTSWORD_PALADIUM = new ItemPaladiumFastsword();
    FASTSWORD_TITANE = new ItemTitaneFastsword();
    FASTSWORD_AMETHYST = new ItemAmethystFastsword();
    GameRegistry.registerItem((Item)FASTSWORD_PALADIUM, FASTSWORD_PALADIUM.func_77658_a());
    GameRegistry.registerItem((Item)FASTSWORD_TITANE, FASTSWORD_TITANE.func_77658_a());
    GameRegistry.registerItem((Item)FASTSWORD_AMETHYST, FASTSWORD_AMETHYST.func_77658_a());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\registerer\PSRegister_Items.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */