package fr.paladium.palaforgeutils.lib.command.impl.palagive.manager;

import com.mongodb.client.FindIterable;
import fr.paladium.palaforgeutils.lib.command.impl.palagive.item.ItemGiveGift;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palaforgeutils.lib.task.ATask;
import fr.paladium.palaforgeutils.lib.task.Schedule;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

@Schedule(every = "5s")
class ItemQueueTask extends ATask {
  public ItemQueueTask() {
    super("PalaGiveManager/ItemQueueTask");
  }
  
  public void run() {
    try {
      PlayerSelector.ALL().apply(playerMP -> {
            if (!playerMP.func_70089_S() || !PalaGiveManager.access$000(PalaGiveManager.this, playerMP))
              return; 
            String playerUUID = UUIDUtils.toString((Entity)playerMP);
            Document queueDocument = new Document("target", playerUUID);
            FindIterable<Document> allItemQueue = PalaGiveManager.access$100(PalaGiveManager.this).getCollection("itemQueue").find((Bson)queueDocument);
            if (allItemQueue == null || !allItemQueue.iterator().hasNext())
              return; 
            if (playerMP.field_71071_by.func_70447_i() == -1) {
              playerMP.func_145747_a((new ChatComponentText("§8[§6Paladium§8] §cVous avez des items en attente, merci de faire une place dans votre inventaire.")).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.RED)));
              return;
            } 
            List<ItemStack> itemsToGive = new ArrayList<>();
            allItemQueue.forEach(());
            if (itemsToGive.isEmpty())
              return; 
            CompletableFuture<PalaGiveManager.GiveStatus> isGived = new CompletableFuture<>();
            FMLServerScheduler.getInstance().add(new Runnable[] { () });
            isGived.whenComplete(());
          });
    } catch (Throwable e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\impl\palagive\manager\PalaGiveManager$ItemQueueTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */