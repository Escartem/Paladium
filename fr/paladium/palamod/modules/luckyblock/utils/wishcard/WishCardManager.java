package fr.paladium.palamod.modules.luckyblock.utils.wishcard;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.util.ItemStackSerializer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.bson.Document;

public class WishCardManager {
  public static Map<String, List<ItemStack>> get(String player) {
    player = player.toLowerCase();
    Map<String, List<ItemStack>> all = new HashMap<>();
    Document doc = PLuckyBlock.instance.getWishCardDataBaseManager().find(new Document("_id", player));
    if (doc != null) {
      Map<String, List<String>> senders = (Map<String, List<String>>)doc.get("senders");
      senders.forEach((sender, items) -> {
            List<ItemStack> itemstacks = new ArrayList<>();
            items.forEach(());
            all.put(sender, itemstacks);
          });
    } 
    return all;
  }
  
  public static void add(EntityPlayer player, ItemStack item, String target) {
    target = target.toLowerCase();
    Document query = new Document("_id", target);
    Document c = PLuckyBlock.instance.getWishCardDataBaseManager().find(query);
    Map<String, List<ItemStack>> all = new HashMap<>();
    if (c != null)
      all = get(target); 
    List<ItemStack> items = new ArrayList<>();
    if (all.containsKey(player.func_70005_c_()))
      items = all.get(player.func_70005_c_()); 
    items.add(item);
    all.put(player.func_70005_c_(), items);
    Map<String, List<String>> allFormatted = new HashMap<>();
    all.forEach((name, its) -> {
          List<String> itemsFormatted = new ArrayList<>();
          its.forEach(());
          allFormatted.put(name, itemsFormatted);
        });
    Document document = new Document();
    document.put("_id", target);
    document.put("senders", allFormatted);
    if (c != null) {
      PLuckyBlock.instance.getWishCardDataBaseManager().update(query, document);
    } else {
      PLuckyBlock.instance.getWishCardDataBaseManager().insert(document);
    } 
    PLuckyBlock.instance.getWishCardDataBaseManager()
      .log((new Document()).append("player", player.func_70005_c_())
        .append("action", "SEND").append("receiver", target).append("item", 
          Base64.getEncoder().encodeToString(ItemStackSerializer.write(item).getBytes())));
  }
  
  public static void clear(String player) {
    player = player.toLowerCase();
    Document query = new Document("_id", player);
    PLuckyBlock.instance.getWishCardDataBaseManager().remove(query);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\wishcard\WishCardManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */