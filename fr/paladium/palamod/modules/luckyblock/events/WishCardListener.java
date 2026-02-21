package fr.paladium.palamod.modules.luckyblock.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.wishcard.WishCardManager;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palamod.util.ItemStackSerializer;
import fr.paladium.palamod.util.PlayerUtil;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.bson.Document;

public class WishCardListener {
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onJoin(PlayerEvent.PlayerLoggedInEvent e) {
    if (!e.player.field_70170_p.field_72995_K) {
      EntityPlayer player = e.player;
      PaladiumScheduler.INSTANCE.runTaskAsyncLater(() -> {
            Map<String, List<ItemStack>> all = WishCardManager.get(player.func_70005_c_());
            if (!all.isEmpty()) {
              Map<String, List<String>> allFormatted = new HashMap<>();
              all.forEach(());
              PLuckyBlock.instance.getWishCardDataBaseManager().log((new Document()).append("player", player.func_70005_c_()).append("action", "GET").append("items", allFormatted));
              WishCardManager.clear(player.func_70005_c_());
            } 
            all.forEach(());
          }0L);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\WishCardListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */