package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents;

import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;

public class LittleAlchemistEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Kit du petit alchimiste";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 2500;
  
  private static final String TEXTURE_PATH = "november/little_alchemist";
  
  private static final String PERFORM_MESSAGE = "§aVoici le kit du parfait petit alchimiste !";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aVoici le kit du parfait petit alchimiste !" });
    getRewards().forEach(reward -> InventoryUtils.giveOrDropitems((EntityPlayer)player, reward));
    WorldServer worldServer = (WorldServer)player.field_70170_p;
    worldServer.func_147487_a("explode", x + 0.5D, y + 0.5D, z + 0.5D, 30, 0.5D, 0.5D, 0.5D, 0.1D);
  }
  
  private List<ItemStack> getRewards() {
    List<ItemStack> rewards = new ArrayList<>();
    rewards.add(new ItemStack((Block)BlocksRegister.CAULDRON_BLOCK, 64));
    rewards.add(new ItemStack((Block)BlocksRegister.CAULDRON_CORE, 1));
    for (int i = 0; i < 9; i++)
      rewards.add(new ItemStack((Item)ItemsRegister.BUCKET_ANGELIC, 1)); 
    return rewards;
  }
  
  public String getName() {
    return "Kit du petit alchimiste";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 2500;
  }
  
  public String getTexture() {
    return "november/little_alchemist";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\luckyevents\LittleAlchemistEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */