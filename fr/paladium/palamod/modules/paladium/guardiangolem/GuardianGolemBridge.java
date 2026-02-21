package fr.paladium.palamod.modules.paladium.guardiangolem;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.welsymc.guardiangolem.api.BlocksRegister;
import fr.welsymc.guardiangolem.api.ItemsRegister;
import net.minecraft.block.Block;

public class GuardianGolemBridge {
  public static void init() {
    ItemsRegister.AMETHYST_INGOT = ItemsRegister.AMETHYST_INGOT;
    ItemsRegister.TITANE_INGOT = ItemsRegister.TITANE_INGOT;
    ItemsRegister.PALADIUM_INGOT = ItemsRegister.PALADIUM_INGOT;
    ItemsRegister.PALADIUM_SWORD = ItemsRegister.PALADIUM_SWORD;
    ItemsRegister.ENDIUM_SWORD = ItemsRegister.ENDIUM_SWORD;
    ItemsRegister.PALADIUM_CHESTPLATE = ItemsRegister.PALADIUM_CHESTPLATE;
    ItemsRegister.ORB_SPEED = ItemsRegister.ORB_SPEED;
    ItemsRegister.ORB_HEAL = ItemsRegister.ORB_HEAL;
    ItemsRegister.FINDIUM = ItemsRegister.FINDIUM;
    ItemsRegister.PALADIUM_APPLE = ItemsRegister.PALADIUM_APPLE;
    ItemsRegister.COMPRESSED_PALADIUM = ItemsRegister.COMPRESSED_PALADIUM;
    ItemsRegister.STICK_PALADIUM = ItemsRegister.STICK_PALADIUM;
    BlocksRegister.BLOCK_AMETHYST = BlocksRegister.BLOCK_AMETHYST;
    BlocksRegister.BLOCK_TITANE = BlocksRegister.BLOCK_TITANE;
    BlocksRegister.BLOCK_PALADIUM = BlocksRegister.BLOCK_PALADIUM;
    BlocksRegister.FLUIDS_ANGELICWATER = (Block)BlocksRegister.FLUIDS_ANGELICWATER;
    BlocksRegister.BLOCK_FINDIUM = BlocksRegister.BLOCK_FINDIUM;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\guardiangolem\GuardianGolemBridge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */