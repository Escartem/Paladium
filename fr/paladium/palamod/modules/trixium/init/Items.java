package fr.paladium.palamod.modules.trixium.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.trixium.items.ItemCloud;
import fr.paladium.palamod.modules.trixium.items.ItemPlayerSaddle;
import fr.paladium.palamod.modules.trixium.items.ItemPocketEnderchest;
import net.minecraft.item.Item;

public class Items {
  public static void register() {
    ItemsRegister.POCKET_ENDERCHEST = (Item)new ItemPocketEnderchest();
    ItemsRegister.CLOUD = (Item)new ItemCloud(BlocksRegister.BLOCK_CLOUD);
    ItemsRegister.PLAYER_SADDLE = (Item)new ItemPlayerSaddle();
    GameRegistry.registerItem(ItemsRegister.POCKET_ENDERCHEST, ItemsRegister.POCKET_ENDERCHEST.func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PLAYER_SADDLE, ItemsRegister.PLAYER_SADDLE.func_77658_a());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\init\Items.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */