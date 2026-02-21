package fr.paladium.palashop.provider.box.server;

import fr.paladium.palaforgeutils.lib.command.annotated.registry.CommandRegistry;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitNetwork;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palaoracle.core.pojo.PalaOracleEvent;
import fr.paladium.palashop.PalaShopMod;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.ProviderLoadProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModPostInitProviderEvent;
import fr.paladium.palashop.provider.box.BoxProvider;
import fr.paladium.palashop.provider.box.api.IBoxAPI;
import fr.paladium.palashop.provider.box.client.ui.UIShopBoxPage;
import fr.paladium.palashop.provider.box.common.BoxCommonProxy;
import fr.paladium.palashop.provider.box.common.dto.box.Box;
import fr.paladium.palashop.provider.box.common.dto.box.BoxClient;
import fr.paladium.palashop.provider.box.common.entity.EntityBox;
import fr.paladium.palashop.provider.box.common.manager.BoxManager;
import fr.paladium.palashop.provider.box.common.network.rabbit.RBPacketBoxReward;
import fr.paladium.palashop.provider.box.server.command.BoxCommand;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class BoxServerProxy extends BoxCommonProxy {
  public static final Object LOCK = new Object();
  
  private IBoxAPI api;
  
  private Map<String, Box> boxes;
  
  public IBoxAPI getApi() {
    return this.api;
  }
  
  public Map<String, Box> getBoxes() {
    return this.boxes;
  }
  
  @ProviderListener
  public void onModPostInit(ModPostInitProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    initAPI();
    initNetwork();
    initCommand();
    load();
  }
  
  private void initAPI() {
    this.api = (IBoxAPI)PalaShopMod.getServer().createAPI("box", IBoxAPI.class);
  }
  
  private void initNetwork() {
    RabbitNetwork.createNetwork(BoxProvider.getInstance().getId()).registerPacket(RBPacketBoxReward.class);
  }
  
  private void initCommand() {
    CommandRegistry.register(new Class[] { BoxCommand.class });
  }
  
  public void load() {
    long start = System.currentTimeMillis();
    System.out.println("[BoxManager] Loading boxes...");
    synchronized (LOCK) {
      try {
        this.boxes = new HashMap<>();
        ((Set)BoxProvider.getServer().getApi().list().execute().body()).forEach(box -> {
              if (box == null || box.getId() == null || box.getId().isEmpty() || !BoxManager.getBox(box.getId()).isPresent()) {
                System.err.println("[BoxManager] Invalid box found: " + box);
                return;
              } 
              this.boxes.put(box.getId(), box);
            });
        System.out.println("[BoxManager] Loaded " + this.boxes.size() + " boxes in " + (System.currentTimeMillis() - start) + "ms");
      } catch (IOException e) {
        throw new RuntimeException(e);
      } 
    } 
  }
  
  @NonNull
  public Optional<Box> getBox(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (this.boxes == null)
      load(); 
    return Optional.ofNullable(this.boxes.get(id));
  }
  
  public void open(@NonNull EntityBox entity, @NonNull EntityPlayer player, int count, boolean fast) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (entity.getBoxData() == null || entity.getBoxData().getItem() == null) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCette box n'est pas configurée correctement."));
      return;
    } 
    if (entity.isActive()) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCette box est déjà en cours d'ouverture par §b" + entity.getActiveName() + "§c."));
      return;
    } 
    Optional<Box> optionalBox = entity.getBoxData().getBox();
    if (!optionalBox.isPresent()) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCette box n'existe malheureusement plus."));
      return;
    } 
    int keyCount = 0;
    int freeSlots = 0;
    ItemStack boxItem = new ItemStack(entity.getBoxData().getItem());
    for (ItemStack stack : player.field_71071_by.field_70462_a) {
      if (stack == null || stack.func_77973_b() == null) {
        freeSlots++;
      } else if (stack.func_77969_a(boxItem) && stack.func_77960_j() == boxItem.func_77960_j()) {
        keyCount += stack.field_77994_a;
      } 
    } 
    if (freeSlots <= 0) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous n'avez pas assez de place dans votre inventaire pour ouvrir cette box."));
      return;
    } 
    if (keyCount < count)
      count = keyCount; 
    if (count <= 0) {
      ZUIServer.open(UIShopBoxPage.class, (EntityPlayerMP)player, new Object[] { PacketSerialUtils.getGson().toJson(new BoxClient(optionalBox.get(), player)), Integer.valueOf(entity.func_145782_y()) });
      return;
    } 
    if (count > 10)
      count = 10; 
    Box box = optionalBox.get();
    if (box.getNumber().intValue() <= 0) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCette box est vide pour le moment."));
      return;
    } 
    if (count >= 10) {
      PlayerSelector.ALL().apply(p -> SoundUtils.FIREWORK_LAUNCH.playSound(p, p.field_70165_t, p.field_70163_u, p.field_70161_v, 1.0F, 1.0F));
      MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText(""));
      MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §bLe joueur " + player.getDisplayName() + "§r§b a ouvert §c" + count + "§b clés de la box " + entity.getBoxData().getName() + "§r§b !"));
      MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText(""));
    } 
    if (!player.field_71075_bZ.field_75098_d)
      InventoryUtils.removeItems(player, new ItemStack(entity.getBoxData().getItem()), count, false); 
    if (!entity.start(player, (count < 10) ? count : (count + 1), fast)) {
      for (int i = 0; i < count; i++)
        player.field_71071_by.func_70441_a(new ItemStack(entity.getBoxData().getItem())); 
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cUne erreur est survenue lors de l'ouverture de la box."));
    } else {
      try {
        PalaOracleEvent oracleEvent = new PalaOracleEvent(UUIDUtils.toString((Entity)player), "[SHOP] Box opening");
        oracleEvent.addField("timestamp", Long.valueOf(System.currentTimeMillis()));
        oracleEvent.addField("boxId", box.getId());
        oracleEvent.addField("amount", Integer.valueOf(count));
        oracleEvent.addField("serverName", Server.getServerName());
        oracleEvent.addField("serverId", Server.getHostName());
        oracleEvent.capture();
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } 
  }
  
  @ProviderListener
  public void onProviderLoad(ProviderLoadProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST || this.api == null)
      return; 
    load();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\server\BoxServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */