package fr.paladium.palamod.modules.enderchest.rabbitmq.listener;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.modules.enderchest.PEnderChest;
import fr.paladium.palamod.modules.enderchest.inventory.OfflineInventoryEnderChest;
import fr.paladium.palamod.modules.enderchest.rabbitmq.RabbitListener;
import fr.paladium.palamod.modules.enderchest.rabbitmq.RabbitPacketType;
import fr.paladium.palamod.modules.enderchest.rabbitmq.RabbitQueue;
import fr.paladium.palamod.modules.enderchest.rabbitmq.RabbitService;
import fr.paladium.palamod.modules.enderchest.rabbitmq.packet.SyncRawGetResultPacket;
import fr.paladium.palamod.modules.enderchest.rabbitmq.packet.SyncRawSetPacket;
import fr.paladium.palamod.modules.enderchest.serial.NBTSerializationManager;
import fr.paladium.palamod.util.FastUUID;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class SyncRawGetResultListener extends RabbitListener<SyncRawGetResultPacket> {
  public SyncRawGetResultListener(RabbitService service) throws UnknownHostException {
    super(service, RabbitPacketType.SIMPLE, RabbitQueue.SYNC_RAW_GET_RESULT.getQueueName() + InetAddress.getLocalHost().getHostName(), SyncRawGetResultPacket.class);
  }
  
  public void onReceive(SyncRawGetResultPacket packet) {
    if (packet == null)
      return; 
    UUID uuid = packet.getPlayerUuid();
    String data = packet.getData();
    if (uuid == null || data == null)
      return; 
    if (!PEnderChest.instance.getEnderChests().containsKey(uuid)) {
      if (!PEnderChest.instance.getWaitingSave().containsKey(uuid))
        return; 
      Map.Entry<UUID, List<ItemStack>> entry = (Map.Entry<UUID, List<ItemStack>>)PEnderChest.instance.getWaitingSave().get(uuid);
      save(getPlayer(entry.getKey()), uuid, data, entry.getValue());
      PEnderChest.instance.getWaitingSave().remove(uuid);
      return;
    } 
    UUID playerUuid = (UUID)PEnderChest.instance.getEnderChests().get(uuid);
    PEnderChest.instance.getEnderChests().remove(uuid);
    EntityPlayerMP player = getPlayer(playerUuid);
    if (player == null)
      return; 
    EntityPlayerMP target = getPlayer(uuid);
    if (target != null) {
      player.func_71007_a((IInventory)target.func_71005_bN());
      return;
    } 
    NBTTagCompound targetNbt = NBTSerializationManager.unSerialize(data);
    OfflineInventoryEnderChest inventory = new OfflineInventoryEnderChest(player, uuid, getPlayerName(uuid));
    NBTTagList enderItems = targetNbt.func_150295_c("EnderItems", 10);
    for (int i = 0; i < enderItems.func_74745_c(); i++) {
      NBTTagCompound item = enderItems.func_150305_b(i);
      ItemStack stack = ItemStack.func_77949_a(item);
      inventory.func_70299_a(item.func_74762_e("Slot"), stack);
    } 
    player.func_71007_a((IInventory)inventory);
  }
  
  private void save(EntityPlayerMP editor, UUID target, String data, List<ItemStack> items) {
    if (editor == null)
      return; 
    NBTTagCompound targetNbt = NBTSerializationManager.unSerialize(data);
    NBTTagList newEnderItems = new NBTTagList();
    for (int i = 0; i < items.size(); i++) {
      ItemStack item = items.get(i);
      if (item != null) {
        NBTTagCompound itemNbt = new NBTTagCompound();
        itemNbt.func_74768_a("Slot", i);
        item.func_77955_b(itemNbt);
        newEnderItems.func_74742_a((NBTBase)itemNbt);
      } 
    } 
    targetNbt.func_74782_a("EnderItems", (NBTBase)newEnderItems);
    try {
      (new SyncRawSetPacket(target, InetAddress.getLocalHost().getHostName(), NBTSerializationManager.serialize((NBTBase)targetNbt).toString()))
        .send(PEnderChest.instance.getRabbit());
    } catch (UnknownHostException e) {
      e.printStackTrace();
      (new Notification(Notification.NotificationType.ERROR, "Impossible de sauvegarder", "enderchest")).send(editor);
    } 
  }
  
  public static EntityPlayerMP getPlayer(UUID uuid) {
    if (uuid == null || (MinecraftServer.func_71276_C()).field_71305_c == null)
      return null; 
    for (WorldServer world : (MinecraftServer.func_71276_C()).field_71305_c) {
      if (world.func_152378_a(uuid) != null)
        return (EntityPlayerMP)world.func_152378_a(uuid); 
    } 
    return null;
  }
  
  private String getPlayerName(UUID uuid) {
    String name = FastUUID.toString(uuid);
    if (MinecraftServer.func_71276_C().func_152358_ax().func_152652_a(uuid) != null)
      name = MinecraftServer.func_71276_C().func_152358_ax().func_152652_a(uuid).getName(); 
    return name;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\rabbitmq\listener\SyncRawGetResultListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */