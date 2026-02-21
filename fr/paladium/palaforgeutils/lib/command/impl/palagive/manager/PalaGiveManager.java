package fr.paladium.palaforgeutils.lib.command.impl.palagive.manager;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.command.impl.palagive.item.ItemGiveGift;
import fr.paladium.palaforgeutils.lib.config.PalaForgeConfigManager;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.palaforgeutils.lib.mongo.MongoDBCredentials;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palaforgeutils.lib.task.ATask;
import fr.paladium.palaforgeutils.lib.task.Schedule;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public final class PalaGiveManager {
  private static final String DATABASE_NAME = "palagive";
  
  private static final String LOGS_COLLECTION = "logs";
  
  private static final String ITEMQUEUE_COLLECTION = "itemQueue";
  
  private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(0, 5, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
  
  private static PalaGiveManager instance;
  
  private static Object syncManager = null;
  
  private static Method getSyncState = null;
  
  private MongoDatabase mongoDB;
  
  private ItemQueueTask itemQueueThread;
  
  private String serverIdentifier;
  
  public static PalaGiveManager inst() {
    if (instance == null)
      instance = new PalaGiveManager(); 
    return instance;
  }
  
  public void init() {
    this.itemQueueThread = new ItemQueueTask();
    this.itemQueueThread.start();
    MongoDBCredentials mongoCreds = PalaForgeConfigManager.getInstance().getMongoDBCredentials();
    if (mongoCreds == null)
      throw new RuntimeException("[PalaGiveManager] MongoDBCredentials is not initialized, please check the configuration file."); 
    try {
      this.serverIdentifier = InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
      throw new RuntimeException("[PalaGiveManager] Unable to retrieve server identifier", e);
    } 
    String mongoUri = "mongodb://";
    if (mongoCreds.getUser() != null && !mongoCreds.getUser().isEmpty()) {
      mongoUri = mongoUri + mongoCreds.getUser() + ":" + mongoCreds.getPassword() + "@" + mongoCreds.getHost() + ":" + mongoCreds.getPort() + "/" + "palagive";
    } else {
      mongoUri = mongoUri + mongoCreds.getHost() + ":" + mongoCreds.getPort() + "/" + "palagive";
    } 
    MongoClient mongoClient = MongoClients.create(mongoUri);
    this.mongoDB = mongoClient.getDatabase("palagive");
    if (this.mongoDB == null)
      throw new RuntimeException("[PalaGiveManager] MongoDB is not initialized, please check the configuration file or database."); 
    List<String> collections = (List<String>)this.mongoDB.listCollectionNames().into(new ArrayList());
    if (!collections.contains("logs"))
      this.mongoDB.createCollection("logs"); 
    if (!collections.contains("itemQueue"))
      this.mongoDB.createCollection("itemQueue"); 
    if (syncManager == null)
      try {
        Class<?> syncServer = Class.forName("be.zeldown.syncserver.SyncServer");
        Object syncInstance = syncServer.getDeclaredField("instance").get((Object)null);
        Field managerField = syncServer.getDeclaredField("manager");
        managerField.setAccessible(true);
        syncManager = managerField.get(syncInstance);
      } catch (Throwable e) {
        e.printStackTrace();
        return;
      }  
    if (getSyncState == null)
      try {
        getSyncState = syncManager.getClass().getMethod("getState", new Class[] { UUID.class });
      } catch (Throwable e) {
        e.printStackTrace();
        return;
      }  
  }
  
  @NonNull
  public CompletableFuture<List<Document>> dump(@NonNull String playerUUID) {
    if (playerUUID == null)
      throw new NullPointerException("playerUUID is marked non-null but is null"); 
    CompletableFuture<List<Document>> future = new CompletableFuture<>();
    EXECUTOR_SERVICE.submit(() -> {
          try {
            List<Document> logs = (List<Document>)this.mongoDB.getCollection("logs").find((Bson)new Document("target", playerUUID)).into(new ArrayList());
            future.complete(logs);
          } catch (Exception e) {
            future.completeExceptionally(e);
          } 
        });
    return future;
  }
  
  @NonNull
  public CompletableFuture<DeleteResult> clear(@NonNull String playerUUID) {
    if (playerUUID == null)
      throw new NullPointerException("playerUUID is marked non-null but is null"); 
    CompletableFuture<DeleteResult> future = new CompletableFuture<>();
    EXECUTOR_SERVICE.submit(() -> future.complete(this.mongoDB.getCollection("itemQueue").deleteMany((Bson)new Document("target", playerUUID))));
    return future;
  }
  
  @NonNull
  public CompletableFuture<Void> give(@NonNull EntityPlayer player, @NonNull ItemStack stack) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (stack == null)
      throw new NullPointerException("stack is marked non-null but is null"); 
    return give(UUIDUtils.toString((Entity)player), stack);
  }
  
  @NonNull
  public CompletableFuture<Void> give(@NonNull EntityPlayer player, @NonNull List<ItemStack> items) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (items == null)
      throw new NullPointerException("items is marked non-null but is null"); 
    return give(UUIDUtils.toString((Entity)player), items);
  }
  
  @NonNull
  public CompletableFuture<Void> give(@NonNull OfflinePlayer player, @NonNull ItemStack stack) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (stack == null)
      throw new NullPointerException("stack is marked non-null but is null"); 
    return give(player.getUuidString(), stack);
  }
  
  @NonNull
  public CompletableFuture<Void> give(@NonNull OfflinePlayer player, @NonNull List<ItemStack> items) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (items == null)
      throw new NullPointerException("items is marked non-null but is null"); 
    return give(player.getUuidString(), items);
  }
  
  @NonNull
  public CompletableFuture<Void> give(@NonNull String playerUUID, @NonNull ItemStack stack) {
    if (playerUUID == null)
      throw new NullPointerException("playerUUID is marked non-null but is null"); 
    if (stack == null)
      throw new NullPointerException("stack is marked non-null but is null"); 
    return give(playerUUID, Arrays.asList(new ItemStack[] { stack }));
  }
  
  @NonNull
  public CompletableFuture<Void> give(@NonNull String playerUUID, @NonNull List<ItemStack> items) {
    if (playerUUID == null)
      throw new NullPointerException("playerUUID is marked non-null but is null"); 
    if (items == null)
      throw new NullPointerException("items is marked non-null but is null"); 
    if (this.mongoDB == null) {
      if (FMLCommonHandler.instance().getSide() == Side.SERVER)
        for (Object playerObj : (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b) {
          if (!(playerObj instanceof EntityPlayerMP))
            continue; 
          EntityPlayer player = (EntityPlayer)playerObj;
          if (!UUIDUtils.toString(player.func_110124_au()).equals(playerUUID))
            continue; 
          InventoryUtils.giveOrDropitems(player, ItemGiveGift.create(items));
        }  
      return CompletableFuture.completedFuture(null);
    } 
    CompletableFuture<Void> future = new CompletableFuture<>();
    if (items.isEmpty()) {
      future.complete(null);
      return future;
    } 
    EXECUTOR_SERVICE.submit(() -> {
          List<Document> documents = new ArrayList<>();
          for (int i = 0; i < items.size(); i++) {
            ItemStack copyStack = ((ItemStack)items.get(i)).func_77946_l();
            if (copyStack.field_77994_a > 0)
              documents.add((new Document("target", playerUUID)).append("item", ItemStackSerializer.write64(copyStack)).append("count", Integer.valueOf(copyStack.field_77994_a)).append("created", Long.valueOf(System.currentTimeMillis())).append("updated", Long.valueOf(System.currentTimeMillis()))); 
          } 
          try {
            this.mongoDB.getCollection("itemQueue").insertMany(documents);
            future.complete(null);
          } catch (Exception e) {
            future.completeExceptionally(e);
          } 
        });
    return future;
  }
  
  @NonNull
  private String getInventoryString(@NonNull EntityPlayerMP player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    NBTTagList inventory = player.field_71071_by.func_70442_a(new NBTTagList());
    NBTTagList newInventory = new NBTTagList();
    for (int i = 0; i < inventory.func_74745_c(); i++) {
      NBTTagCompound item = inventory.func_150305_b(i);
      byte tagId = item.func_150299_b("id");
      if (tagId == 2 || tagId == 3 || tagId == 4 || tagId == 5 || tagId == 6 || tagId == 8) {
        ItemStack it = ItemStack.func_77949_a(item);
        NBTTagCompound comp1 = new NBTTagCompound();
        comp1.func_74768_a("Slot", item.func_74762_e("Slot"));
        comp1.func_74778_a("Item", it.toString());
        if (it.func_77942_o())
          comp1.func_74782_a("Nbt", (NBTBase)it.field_77990_d); 
        newInventory.func_74742_a((NBTBase)comp1);
      } 
    } 
    return newInventory.toString();
  }
  
  @NonNull
  private CompletableFuture<Void> logGive(@NonNull String playerUUID, @NonNull GiveStatus status) {
    if (playerUUID == null)
      throw new NullPointerException("playerUUID is marked non-null but is null"); 
    if (status == null)
      throw new NullPointerException("status is marked non-null but is null"); 
    CompletableFuture<Void> future = new CompletableFuture<>();
    EXECUTOR_SERVICE.submit(() -> {
          Document logDocument = (new Document("target", playerUUID)).append("item", ItemStackSerializer.write64(status.getItemStack())).append("itemReadable", status.getItemStack().toString() + (status.getItemStack().func_77942_o() ? (status.getItemStack()).field_77990_d.toString() : "")).append("inventoryBefore", status.getInventoryBefore()).append("inventoryAfter", status.getInventoryAfter()).append("given", Integer.valueOf(status.getGivenCount())).append("server", this.serverIdentifier).append("created", Long.valueOf(System.currentTimeMillis()));
          try {
            this.mongoDB.getCollection("logs").insertOne(logDocument);
            future.complete(null);
          } catch (Exception e) {
            future.completeExceptionally(e);
          } 
        });
    return future;
  }
  
  private boolean isPlayerSyncLoaded(EntityPlayerMP player) {
    if (ForgeEnv.isIDE())
      return true; 
    if (syncManager == null || getSyncState == null) {
      System.out.println("[PalaGiveManager] SyncManager or getSyncState method is not available, skipping item queue processing.");
      return false;
    } 
    try {
      int ordinalState = ((Enum)getSyncState.invoke(syncManager, new Object[] { player.func_110124_au() })).ordinal();
      return (ordinalState == 3);
    } catch (Throwable e) {
      e.printStackTrace();
      return false;
    } 
  }
  
  private class GiveStatus {
    private final ItemStack itemStack;
    
    private final int givenCount;
    
    private String inventoryBefore;
    
    private String inventoryAfter;
    
    public void setInventoryBefore(String inventoryBefore) {
      this.inventoryBefore = inventoryBefore;
    }
    
    public void setInventoryAfter(String inventoryAfter) {
      this.inventoryAfter = inventoryAfter;
    }
    
    public String toString() {
      return "PalaGiveManager.GiveStatus(itemStack=" + getItemStack() + ", givenCount=" + getGivenCount() + ", inventoryBefore=" + getInventoryBefore() + ", inventoryAfter=" + getInventoryAfter() + ")";
    }
    
    public ItemStack getItemStack() {
      return this.itemStack;
    }
    
    public int getGivenCount() {
      return this.givenCount;
    }
    
    public String getInventoryBefore() {
      return this.inventoryBefore;
    }
    
    public String getInventoryAfter() {
      return this.inventoryAfter;
    }
    
    public GiveStatus(ItemStack stack, int givenCount) {
      this.itemStack = stack;
      this.givenCount = givenCount;
    }
  }
  
  @Schedule(every = "5s")
  private class ItemQueueTask extends ATask {
    public ItemQueueTask() {
      super("PalaGiveManager/ItemQueueTask");
    }
    
    public void run() {
      try {
        PlayerSelector.ALL().apply(playerMP -> {
              if (!playerMP.func_70089_S() || !PalaGiveManager.this.isPlayerSyncLoaded(playerMP))
                return; 
              String playerUUID = UUIDUtils.toString((Entity)playerMP);
              Document queueDocument = new Document("target", playerUUID);
              FindIterable<Document> allItemQueue = PalaGiveManager.this.mongoDB.getCollection("itemQueue").find((Bson)queueDocument);
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
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\impl\palagive\manager\PalaGiveManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */