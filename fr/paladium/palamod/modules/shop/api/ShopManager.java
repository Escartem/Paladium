package fr.paladium.palamod.modules.shop.api;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.cresus.server.api.async.CresusCallback;
import fr.paladium.cresus.server.managers.CresusManager;
import fr.paladium.cresus.server.responses.CresusResponse;
import fr.paladium.factions.core.utils.JsonUtils;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.command.impl.palagive.manager.PalaGiveManager;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.modules.achievements.types.ShopBuyAchievement;
import fr.paladium.palamod.modules.achievements.types.ShopSellAchievement;
import fr.paladium.palamod.modules.achievements.types.ShopSellCountAchievement;
import fr.paladium.palamod.modules.shop.PShop;
import fr.paladium.palamod.modules.shop.config.ShopConfig;
import fr.paladium.palamod.modules.shop.data.ItemCategory;
import fr.paladium.palamod.modules.shop.data.ShopItem;
import fr.paladium.palamod.modules.shop.data.TransactionHistoryInput;
import fr.paladium.palamod.modules.shop.network.SCPacketShopItemListReply;
import fr.paladium.palamod.modules.shop.network.SCPacketUpdateInventory;
import fr.paladium.palamod.modules.shop.task.ShopDataFetcherTask;
import fr.paladium.palamod.modules.shop.utils.BigBrotherUtils;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import fr.paladium.palamod.util.FastUUID;
import fr.paladium.palapass.common.quest.shop.ShopBuyQuest;
import fr.paladium.palapass.common.quest.shop.ShopSellQuest;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ShopManager {
  private static final long RATE_LIMIT = 500L;
  
  private static ShopManager instance;
  
  private ShopConfig config;
  
  public IShopAPI getApi() {
    return this.api;
  }
  
  private final AtomicReference<Map<String, ShopItem>> items = new AtomicReference<>();
  
  private final Map<UUID, Long> lastAction = new HashMap<>();
  
  private IShopAPI api;
  
  public ShopManager() {
    if (initConfig()) {
      initApi();
      initTasks();
    } 
  }
  
  public void sendItems(final EntityPlayerMP player, ItemCategory category, int at, String match) {
    Map<String, ShopItem> items = this.items.get();
    if (ForgeEnv.isDev()) {
      Map<String, ShopItem> map = new HashMap<>();
      String[] itemsList = { 
          "minecraft:wool/0", "minecraft:wool/1", "minecraft:wool/2", "minecraft:wool/3", "minecraft:wool/4", "minecraft:wool/5", "minecraft:wool/6", "minecraft:wool/7", "minecraft:wool/8", "minecraft:wool/9", 
          "minecraft:wool/10", "minecraft:wool/11", "minecraft:wool/12", "minecraft:wool/13", "minecraft:wool/14", "minecraft:wool/15", "minecraft:obsidian/0", "minecraft:dirt/0", "minecraft:cobblestone/0", "minecraft:log/0", 
          "minecraft:soul_sand/0", "minecraft:quartz/0", "minecraft:dye/0", "minecraft:dye/1", "minecraft:dye/2", "minecraft:dye/3", "minecraft:dye/4", "minecraft:dye/5", "minecraft:dye/6", "minecraft:dye/7", 
          "minecraft:dye/8", "minecraft:dye/9", "minecraft:dye/10", "minecraft:dye/11", "minecraft:dye/12", "minecraft:dye/13", "minecraft:dye/14", "minecraft:dye/15", "minecraft:stick/0", "minecraft:gravel/0", 
          "minecraft:sand/0" };
      Random rand = new Random();
      for (String item : itemsList) {
        ShopItem shopItem = new ShopItem(item, ItemCategory.values()[rand.nextInt((ItemCategory.values()).length)], true, true, rand.nextInt(100), 0.0D);
        map.put(item, shopItem);
      } 
      SCPacketShopItemListReply packet = new SCPacketShopItemListReply((short)map.size(), map, 0.0D);
      PShop.network.sendTo((IMessage)packet, player);
      return;
    } 
    if (items == null) {
      CresusManager.getInstance().getBalanceAsync(player.func_110124_au(), new CresusCallback<Double>() {
            public void onSuccess(Double money) {
              SCPacketShopItemListReply packet = new SCPacketShopItemListReply((short)0, new HashMap<>(), money.doubleValue());
              PShop.network.sendTo((IMessage)packet, player);
            }
            
            public void onFail(Double money, Throwable arg1) {
              ZUIServer.close(player);
              arg1.printStackTrace();
            }
          });
      return;
    } 
    final Map<String, ShopItem> itemsToSend = new HashMap<>();
    int atCount = 0;
    for (Map.Entry<String, ShopItem> entry : items.entrySet()) {
      ShopItem item = entry.getValue();
      if ((category == ItemCategory.ALL || item.getCategory() == category) && (match == null || match.isEmpty() || item.getItem().toLowerCase().contains(match.toLowerCase()))) {
        if (atCount < at) {
          atCount++;
          continue;
        } 
        atCount++;
        itemsToSend.put(entry.getKey(), item);
      } 
    } 
    CresusManager.getInstance().getBalanceAsync(player.func_110124_au(), new CresusCallback<Double>() {
          public void onSuccess(Double money) {
            SCPacketShopItemListReply packet = new SCPacketShopItemListReply((short)itemsToSend.size(), itemsToSend, money.doubleValue());
            PShop.network.sendTo((IMessage)packet, player);
          }
          
          public void onFail(Double money, Throwable arg1) {
            ZUIServer.close(player);
            arg1.printStackTrace();
          }
        });
  }
  
  private boolean initConfig() {
    try {
      this.config = (ShopConfig)JsonUtils.getFileObject("adminshop.json", ShopConfig.class);
      return true;
    } catch (Exception exception) {
      if (!ForgeEnv.isDev()) {
        exception.printStackTrace();
      } else {
        Constants.logger.error("[ShopManager] adminshop.json not found.");
      } 
      return false;
    } 
  }
  
  private void initApi() {
    Retrofit retrofit = (new Retrofit.Builder()).addConverterFactory((Converter.Factory)ScalarsConverterFactory.create()).addConverterFactory((Converter.Factory)GsonConverterFactory.create()).baseUrl(this.config.getApiUrl()).build();
    this.api = (IShopAPI)retrofit.create(IShopAPI.class);
  }
  
  private void initTasks() {
    PaladiumScheduler.INSTANCE.runTaskTimer((Runnable)new ShopDataFetcherTask(this), 0L, 1200L);
  }
  
  public void setItems(Map<String, ShopItem> items) {
    this.items.set(items);
  }
  
  private void addTransaction(EntityPlayerMP player, ShopItem item, int amount, double price, boolean buy) {
    TransactionHistoryInput historyInput = new TransactionHistoryInput();
    historyInput.playerName = player.func_70005_c_();
    historyInput.playerUUID = FastUUID.toString((Entity)player);
    historyInput.item = item.getItem();
    historyInput.amount = amount;
    historyInput.price = price;
    historyInput.date = System.currentTimeMillis();
    historyInput.buy = buy;
    Call<Void> transactionQuery = this.api.addTransaction(historyInput);
    transactionQuery.enqueue(new Callback<Void>() {
          public void onResponse(Call<Void> call, Response<Void> response) {
            if (!response.isSuccessful())
              Constants.logger.error("[AdminShop] Failed to add transaction to history: " + response.message()); 
          }
          
          public void onFailure(Call<Void> call, Throwable throwable) {
            Constants.logger.error("[AdminShop] Failed to add transaction to history:");
            throwable.printStackTrace();
          }
        });
  }
  
  private boolean sanityChecks(EntityPlayerMP player, String itemName, int amount, boolean buy) {
    Map<String, ShopItem> items = this.items.get();
    if (items == null) {
      (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue #SHOP-1", "shop")).send(player);
      return false;
    } 
    ShopItem item = items.get(itemName);
    if (item == null) {
      (new Notification(Notification.NotificationType.ERROR, "L'item " + itemName + " n'existe pas", "shop")).send(player);
      return false;
    } 
    if (amount <= 0) {
      (new Notification(Notification.NotificationType.ERROR, "Montant invalide", "shop")).send(player);
      return false;
    } 
    if (buy) {
      if (!item.isCanBuy() || item.getBuyPrice() <= 0.0D) {
        (new Notification(Notification.NotificationType.ERROR, "L'item " + itemName + " n'est pas achetable", "shop")).send(player);
        return false;
      } 
    } else if (!item.isCanSell() || item.getSellPrice() <= 0.0D) {
      (new Notification(Notification.NotificationType.ERROR, "L'item " + itemName + " n'est pas vendable", "shop")).send(player);
      return false;
    } 
    if (item.toItemStack() == null) {
      (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue #SHOP-2", "shop")).send(player);
      return false;
    } 
    long now = System.currentTimeMillis();
    if (this.lastAction.containsKey(player.func_110124_au())) {
      long diff = now - ((Long)this.lastAction.get(player.func_110124_au())).longValue();
      if (diff <= 500L) {
        (new Notification(Notification.NotificationType.ERROR, "Veuillez patienter entre chaque action", "shop")).send(player);
        return false;
      } 
    } 
    this.lastAction.put(player.func_110124_au(), Long.valueOf(System.currentTimeMillis()));
    return true;
  }
  
  public void buy(final EntityPlayerMP player, final String itemName, final int amount) {
    if (!sanityChecks(player, itemName, amount, true))
      return; 
    if (InventoryUtils.isFullInventory((EntityPlayer)player)) {
      (new Notification(Notification.NotificationType.ERROR, "Votre inventaire est plein", "shop")).send(player);
      return;
    } 
    final ShopItem item = (ShopItem)((Map)this.items.get()).get(itemName);
    double unitPrice = round(item.getBuyPrice(), 2);
    final double price = round(unitPrice * amount, 2);
    final ItemStack stack = item.toItemStack();
    stack.field_77994_a = 1;
    CresusManager.getInstance().hasAsync(player.func_110124_au(), price, new CresusCallback<Boolean>() {
          public void onSuccess(Boolean success) {
            if (!success.booleanValue()) {
              ShopManager.this.sendNotEnoughMoney(player);
              return;
            } 
            CresusManager.getInstance().withdrawPlayerAsync(player.func_110124_au(), price, "[AdminShop] Buy " + stack.func_77977_a() + " x" + amount, new CresusCallback<CresusResponse>() {
                  public void onSuccess(CresusResponse response) {
                    if (!response.transactionSuccess()) {
                      player.func_145747_a((IChatComponent)new ChatComponentText("§cVous §cn'avez §cpas §cassez §cd'argent."));
                      return;
                    } 
                    ItemStack copyStack = stack.func_77946_l();
                    copyStack.field_77994_a = amount;
                    PalaGiveManager.inst().give((EntityPlayer)player, copyStack);
                    player.func_145747_a((IChatComponent)new ChatComponentText("§aVous venez d'acheter §3" + amount + "x" + stack.func_82833_r()));
                    player.func_145747_a((IChatComponent)new ChatComponentText(" §ePrix §8» §7" + price + "$"));
                    player.func_145747_a((IChatComponent)new ChatComponentText(" §eQuantité §8» §7" + amount + ""));
                    ShopBuyAchievement.performCheck((EntityPlayer)player);
                    BigBrotherUtils.buy(player, itemName, amount, price);
                    ShopBuyQuest.trigger((EntityPlayer)player, stack, amount);
                    ShopManager.this.addTransaction(player, item, amount, price, true);
                    (new Notification(Notification.NotificationType.SUCCESS, "Achat effectué de " + amount + "x" + stack.func_82833_r() + " (-" + String.format("%.2f", new Object[] { Double.valueOf(this.this$1.val$price) }) + "$)", "shop")).send(player);
                  }
                  
                  public void onFail(CresusResponse arg0, Throwable arg1) {
                    ShopManager.this.sendNotEnoughMoney(player);
                  }
                });
          }
          
          public void onFail(Boolean arg0, Throwable arg1) {
            (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue #SHOP-6", "shop")).send(player);
          }
        });
  }
  
  public void sell(final EntityPlayerMP player, final String itemName, final int amount) {
    if (!sanityChecks(player, itemName, amount, false))
      return; 
    final ShopItem item = (ShopItem)((Map)this.items.get()).get(itemName);
    double unitPrice = round(item.getSellPrice(), 2);
    final double price = round(unitPrice * amount, 2);
    final ItemStack stack = item.toItemStack();
    stack.field_77994_a = amount;
    int amountInInventory = 0;
    for (ItemStack playerStack : player.field_71071_by.field_70462_a) {
      if (playerStack != null && playerStack.func_77969_a(stack) && playerStack.field_77994_a > 0)
        amountInInventory += playerStack.field_77994_a; 
    } 
    if (amountInInventory < amount) {
      int missingAmount = amount - amountInInventory;
      (new Notification(Notification.NotificationType.ERROR, "Il vous manque " + missingAmount + " " + stack.func_82833_r(), "shop")).send(player);
      player.func_145747_a((IChatComponent)new ChatComponentText("§cVous n'avez pas assez de " + stack.func_82833_r() + "."));
      player.func_145747_a((IChatComponent)new ChatComponentText(" §8» §7Vous en avez §6" + amountInInventory));
      player.func_145747_a((IChatComponent)new ChatComponentText(" §8» §7Il vous en manque §6" + (amount - amountInInventory)));
      return;
    } 
    amountInInventory = 0;
    int tempAmount = amount;
    for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
      ItemStack it = player.field_71071_by.field_70462_a[i];
      if (it != null && tempAmount > 0 && 
        it.func_77969_a(stack)) {
        int v = Math.min(Math.min(64, tempAmount), it.field_77994_a);
        it.field_77994_a -= v;
        if (it.field_77994_a <= 0) {
          player.field_71071_by.func_70299_a(i, null);
          player.field_71071_by.field_70459_e = true;
        } else {
          player.field_71071_by.func_70299_a(i, it.func_77946_l());
          player.field_71071_by.field_70459_e = true;
        } 
        tempAmount -= v;
        amountInInventory += v;
      } 
    } 
    try {
      PShop.network.sendTo((IMessage)new SCPacketUpdateInventory(player.field_71071_by), player);
    } catch (NullPointerException nullPointerException) {}
    if (tempAmount > 0) {
      (new Notification(Notification.NotificationType.ERROR, "Il vous manque " + tempAmount + " " + stack.func_82833_r(), "shop")).send(player);
      player.func_145747_a((IChatComponent)new ChatComponentText("§cVous n'avez pas assez de " + stack.func_82833_r() + "."));
      player.func_145747_a((IChatComponent)new ChatComponentText(" §8» §7Vous en avez §6" + amountInInventory));
      player.func_145747_a((IChatComponent)new ChatComponentText(" §8» §7Il vous en manque §6" + tempAmount));
      return;
    } 
    CresusManager.getInstance().depositPlayerAsync(player.func_110124_au(), price, "[AdminShop] Sell " + stack.func_77977_a() + " x" + amount, new CresusCallback<CresusResponse>() {
          public void onSuccess(CresusResponse response) {
            if (response.transactionSuccess()) {
              player.field_71071_by.field_70459_e = true;
              ShopSellQuest.trigger((EntityPlayer)player, stack, amount);
              BigBrotherUtils.sell(player, itemName, amount, price);
              player.func_145747_a((IChatComponent)new ChatComponentText("§aVous venez de vendre §3" + amount + "x" + stack.func_82833_r() + "."));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §eMontant §8» §7" + price + "$"));
              player.func_145747_a((IChatComponent)new ChatComponentText(" §eQuantité §8» §7" + amount + ""));
              ShopSellAchievement.performCheck((EntityPlayer)player);
              ShopSellCountAchievement.performCheck((EntityPlayer)player, price);
              (new Notification(Notification.NotificationType.SUCCESS, "Vente effectuée de " + amount + "x" + stack.func_82833_r() + " (+" + String.format("%.2f", new Object[] { Double.valueOf(this.val$price) }) + "$)", "shop")).send(player);
              ShopManager.this.addTransaction(player, item, amount, price, false);
            } else {
              (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue #SHOP-3", "shop")).send(player);
            } 
          }
          
          public void onFail(CresusResponse response, Throwable throwable) {
            (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue #SHOP-4", "shop")).send(player);
          }
        });
  }
  
  private void sendNotEnoughMoney(EntityPlayerMP player) {
    player.func_145747_a((IChatComponent)new ChatComponentText("§cVous n'avez pas assez d'argent."));
    (new Notification(Notification.NotificationType.ERROR, "Vous n'avez pas assez d'argent", "shop")).send(player);
  }
  
  private void buyFailRefund(final EntityPlayerMP player, final ItemStack stack, double unitPrice, final double amount, final int fail) {
    CresusManager.getInstance().depositPlayerAsync(player.func_110124_au(), round(unitPrice * fail, 2), "[AdminShop] Buy Fail Refund " + stack.func_77977_a() + " x" + amount + " | Fail x" + fail, new CresusCallback<CresusResponse>() {
          public void onFail(CresusResponse response, Throwable throwable) {
            (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue #SHOP-5", "shop")).send(player);
          }
          
          public void onSuccess(CresusResponse response) {
            player.func_145747_a((IChatComponent)new ChatComponentText("§cVous n'avez reçu que §3" + (amount - fail) + "x " + stack.func_82833_r() + " §ccar votre inventaire était plein."));
          }
        });
  }
  
  public static ShopManager getInstance() {
    if (instance == null)
      instance = new ShopManager(); 
    return instance;
  }
  
  private static double round(double number, int places) {
    BigDecimal bigDecimal = new BigDecimal(Double.toString(number));
    bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
    return bigDecimal.doubleValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\api\ShopManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */