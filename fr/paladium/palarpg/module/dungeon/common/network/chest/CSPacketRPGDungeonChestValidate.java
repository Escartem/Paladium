package fr.paladium.palarpg.module.dungeon.common.network.chest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.common.network.SCPacketRPGPlaySound;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonChest;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.dungeon.server.rabbitmq.RBPacketDungeonReward;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CSPacketRPGDungeonChestValidate extends ForgePacket {
  @PacketData
  private CSPacketRPGDungeonChestValidateData data;
  
  public CSPacketRPGDungeonChestValidate() {}
  
  public CSPacketRPGDungeonChestValidate(CSPacketRPGDungeonChestValidateData data) {
    this.data = data;
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    if (this.data == null)
      return; 
    World world = player.field_70170_p;
    if (!world.func_72899_e(this.data.chestX, this.data.chestY, this.data.chestZ))
      return; 
    TileEntity tileEntity = world.func_147438_o(this.data.chestX, this.data.chestY, this.data.chestZ);
    if (!(tileEntity instanceof TileEntityDungeonChest))
      return; 
    TileEntityDungeonChest chest = (TileEntityDungeonChest)tileEntity;
    Optional<RPGDungeonPlayerData.RPGDungeonItem[]> optionalRewards = chest.getRewards(UUIDUtils.toString((Entity)player));
    if (!optionalRewards.isPresent())
      return; 
    RPGDungeonPlayerData playerData = (RPGDungeonPlayerData)RPGPlayer.getData((Entity)player, "dungeon");
    if (playerData == null)
      return; 
    List<RPGDungeonPlayerData.RPGDungeonItem> rewards = new ArrayList<>(Arrays.asList((Object[])optionalRewards.get()));
    rewards.addAll(playerData.getBackpack());
    rewards.removeIf(item -> this.data.deletedItems.contains(UUIDUtils.toString(item.getUniqueId())));
    if (rewards.size() > playerData.getBackpackSize())
      rewards.subList(playerData.getBackpackSize(), rewards.size()).clear(); 
    for (RPGDungeonPlayerData.RPGDungeonItem reward : rewards) {
      if ("ancient".equalsIgnoreCase(reward.getType()))
        (new RBPacketDungeonReward(player.func_70005_c_(), chest.getRoom().getWorld().getDungeon().getName(), reward.getItem())).broadcast(); 
    } 
    playerData.getBackpack().clear();
    playerData.getBackpack().addAll(rewards);
    playerData.sync();
    chest.removeRewards(UUIDUtils.toString((Entity)player));
    (new SCPacketRPGPlaySound("sounds/dungeon/success.ogg", 2.0F)).send(player);
  }
  
  public static class CSPacketRPGDungeonChestValidateData {
    private int chestX;
    
    private int chestY;
    
    private int chestZ;
    
    private List<String> deletedItems;
    
    public CSPacketRPGDungeonChestValidateData() {}
    
    public CSPacketRPGDungeonChestValidateData(int chestX, int chestY, int chestZ, List<String> deletedItems) {
      this.chestX = chestX;
      this.chestY = chestY;
      this.chestZ = chestZ;
      this.deletedItems = deletedItems;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\chest\CSPacketRPGDungeonChestValidate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */