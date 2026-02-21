package fr.paladium.palarpg.module.dungeon.common.network.room.merchant;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.common.network.SCPacketRPGPlaySound;
import fr.paladium.palarpg.module.dungeon.common.entity.room.merchant.EntityDungeonMerchant;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatMutationType;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.TimedStatCapabilityMutator;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSPacketRPGDungeonRoomMerchantSelect extends ForgePacket {
  @PacketData
  private int entityId;
  
  @PacketData
  private int tradeIndex;
  
  public CSPacketRPGDungeonRoomMerchantSelect() {}
  
  public CSPacketRPGDungeonRoomMerchantSelect(int entityId, int tradeIndex) {
    this.entityId = entityId;
    this.tradeIndex = tradeIndex;
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    Entity entity = player.field_70170_p.func_73045_a(this.entityId);
    if (!(entity instanceof EntityDungeonMerchant))
      return; 
    EntityDungeonMerchant merchant = (EntityDungeonMerchant)entity;
    merchant.setTraded((EntityPlayer)player);
    if (merchant.isMephisto()) {
      EntityDungeonMerchant.IEntityDungeonMerchantTrade[] items = merchant.getItems((EntityPlayer)player);
      if (items == null || this.tradeIndex < 0 || this.tradeIndex >= items.length)
        return; 
      EntityDungeonMerchant.EntityDungeonMerchantMephistoTrade trade = (EntityDungeonMerchant.EntityDungeonMerchantMephistoTrade)items[this.tradeIndex];
      if (trade == null)
        return; 
      Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get((EntityPlayer)player);
      if (!optionalDungeonWorld.isPresent())
        return; 
      DungeonWorld dungeonWorld = optionalDungeonWorld.get();
      if (dungeonWorld.getState() != DungeonWorld.DungeonState.STARTED)
        return; 
      RPGStatPlayerData playerData = (RPGStatPlayerData)RPGPlayer.getData((Entity)player, "stats");
      if (playerData == null)
        return; 
      playerData.getCapabilityByName(trade.getInput().getStat()).addMutator(TimedStatCapabilityMutator.create().setTick(2147483647).setId("DUNGEON_MERCHANT_" + UUIDUtils.randomStringUUID()).setValue(Double.valueOf(-trade.getInput().getValue())).setMutationType(StatMutationType.MULTIPLICATIVE).setSaved(false));
      playerData.getCapabilityByName(trade.getOutput().getStat()).addMutator(TimedStatCapabilityMutator.create().setTick(2147483647).setId("DUNGEON_MERCHANT_" + UUIDUtils.randomStringUUID()).setValue(Double.valueOf(trade.getOutput().getValue())).setMutationType(StatMutationType.MULTIPLICATIVE).setSaved(false));
      playerData.applyAndSync();
      (new SCPacketRPGPlaySound("sounds/dungeon/success.ogg", 2.0F)).send(player);
    } else {
      EntityDungeonMerchant.IEntityDungeonMerchantTrade[] items = merchant.getItems((EntityPlayer)player);
      if (items == null || this.tradeIndex < 0 || this.tradeIndex >= items.length)
        return; 
      EntityDungeonMerchant.EntityDungeonMerchantMammonTrade trade = (EntityDungeonMerchant.EntityDungeonMerchantMammonTrade)items[this.tradeIndex];
      if (trade == null)
        return; 
      RPGDungeonPlayerData playerData = (RPGDungeonPlayerData)RPGPlayer.getData((Entity)player, "dungeon");
      if (playerData == null || !playerData.getBackpack().remove(trade.getInput()))
        return; 
      playerData.addItemToBackpack(trade.getOutput());
      playerData.sync();
      (new SCPacketRPGPlaySound("sounds/dungeon/success.ogg", 2.0F)).send(player);
    } 
    (new SCPacketRPGDungeonRoomMerchantAnimation(this.entityId, "interact")).send(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\room\merchant\CSPacketRPGDungeonRoomMerchantSelect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */