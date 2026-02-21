package fr.paladium.palashop.provider.box.common.network.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palashop.provider.box.common.dto.box.BoxReward;
import fr.paladium.palashop.provider.box.common.entity.EntityBox;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class SCPacketBoxWait extends ForgePacket {
  @PacketData
  private int entityId;
  
  @PacketData
  private SCPacketBoxWaitData data;
  
  public SCPacketBoxWait() {}
  
  public SCPacketBoxWait(int entityId, SCPacketBoxWaitData data) {
    this.entityId = entityId;
    this.data = data;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    long start = System.currentTimeMillis();
    (new Thread(() -> {
          while (System.currentTimeMillis() - start < 30000L) {
            Entity entity = player.field_70170_p.func_73045_a(this.entityId);
            if (!(entity instanceof EntityBox))
              continue; 
            EntityBox box = (EntityBox)entity;
            if (box.getClientState().isActive())
              return; 
            box.getClientState().waiting(this.data.getPlayerName(), this.data.isFast(), this.data.getAvailableRewards(), this.data.getRewards(), this.data.getCurrentSpin());
            return;
          } 
        }"PalaShop/SCPacketBoxWait"))
      
      .start();
  }
  
  public static class SCPacketBoxWaitData {
    private String playerName;
    
    private boolean fast;
    
    private List<BoxReward> availableRewards;
    
    private List<List<BoxReward>> rewards;
    
    private int currentSpin;
    
    public SCPacketBoxWaitData() {}
    
    public SCPacketBoxWaitData(String playerName, boolean fast, List<BoxReward> availableRewards, List<List<BoxReward>> rewards, int currentSpin) {
      this.playerName = playerName;
      this.fast = fast;
      this.availableRewards = availableRewards;
      this.rewards = rewards;
      this.currentSpin = currentSpin;
    }
    
    public String getPlayerName() {
      return this.playerName;
    }
    
    public boolean isFast() {
      return this.fast;
    }
    
    public List<BoxReward> getAvailableRewards() {
      return this.availableRewards;
    }
    
    public List<List<BoxReward>> getRewards() {
      return this.rewards;
    }
    
    public int getCurrentSpin() {
      return this.currentSpin;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\network\client\SCPacketBoxWait.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */