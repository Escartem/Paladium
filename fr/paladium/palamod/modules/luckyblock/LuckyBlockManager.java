package fr.paladium.palamod.modules.luckyblock;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.modules.luckyblock.hud.vision.ModuleVision;
import fr.paladium.palamod.modules.luckyblock.hud.vision.VisionnedPlayers;
import fr.paladium.palamod.modules.luckyblock.hud.vision.packets.ClientVisionPacket;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

public class LuckyBlockManager {
  public static LuckyBlockManager instance;
  
  private ArrayList<String> last10Chat;
  
  private ArrayList<VisionnedPlayers> visionHelmetData;
  
  public ArrayList<String> getLast10Chat() {
    return this.last10Chat;
  }
  
  public void setLast10Chat(ArrayList<String> last10Chat) {
    this.last10Chat = last10Chat;
  }
  
  public ArrayList<VisionnedPlayers> getVisionHelmetData() {
    return this.visionHelmetData;
  }
  
  public void setVisionHelmetData(ArrayList<VisionnedPlayers> visionHelmetData) {
    this.visionHelmetData = visionHelmetData;
  }
  
  public static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
  
  public LuckyBlockManager() {
    this;
    instance = this;
    this.last10Chat = new ArrayList<>();
    this.visionHelmetData = new ArrayList<>();
  }
  
  public void addChatMessage(String message) {
    if (this.last10Chat.size() < 10) {
      this.last10Chat.add(message);
    } else {
      this.last10Chat.add(this.last10Chat.size(), message);
      this.last10Chat.remove(0);
    } 
  }
  
  public static LuckyBlockManager getInstance() {
    return instance;
  }
  
  public static void setInstance(LuckyBlockManager instance) {
    LuckyBlockManager.instance = instance;
  }
  
  public void updateVisionnedPlayers() {
    for (VisionnedPlayers vP : this.visionHelmetData) {
      if (!vP.isLinked())
        continue; 
      EntityPlayerMP pSender = (EntityPlayerMP)MinecraftServer.func_71276_C().func_71218_a(0).func_152378_a(UUID.fromString(vP.getSender()));
      EntityPlayerMP pVisionned = (EntityPlayerMP)MinecraftServer.func_71276_C().func_71218_a(0).func_152378_a(UUID.fromString(vP.getVisionned()));
      ArrayList<ItemStack> visionnedInventory = new ArrayList<>();
      for (ItemStack is : pVisionned.field_71071_by.field_70460_b)
        visionnedInventory.add(is); 
      for (ItemStack is : pVisionned.field_71071_by.field_70462_a)
        visionnedInventory.add(is); 
      ClientVisionPacket senderPacket = new ClientVisionPacket(pVisionned.func_70005_c_(), visionnedInventory, pVisionned.func_110143_aJ());
      ModuleVision.getInstance().getNetwork().sendTo((IMessage)senderPacket, pSender);
      ArrayList<ItemStack> senderInventory = new ArrayList<>();
      for (ItemStack is : pSender.field_71071_by.field_70460_b)
        senderInventory.add(is); 
      for (ItemStack is : pSender.field_71071_by.field_70462_a)
        senderInventory.add(is); 
      ClientVisionPacket visionnedPacket = new ClientVisionPacket(pSender.func_70005_c_(), senderInventory, pSender.func_110143_aJ());
      ModuleVision.getInstance().getNetwork().sendTo((IMessage)visionnedPacket, pVisionned);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\LuckyBlockManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */