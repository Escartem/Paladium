package fr.paladium.palarpg.module.dungeon.client.ui.chest;

import cpw.mods.fml.common.network.ByteBufUtils;
import fr.paladium.palaforgeutils.lib.packet.utils.IBufSerializable;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonChest;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import io.netty.buffer.ByteBuf;

public class UIDungeonChestData implements IBufSerializable {
  private int chestX;
  
  private int chestY;
  
  private int chestZ;
  
  private RPGDungeonPlayerData.RPGDungeonItem[] items;
  
  private TileEntityDungeonChest.DungeonChestRarity rarity;
  
  public UIDungeonChestData() {}
  
  public UIDungeonChestData(int chestX, int chestY, int chestZ, RPGDungeonPlayerData.RPGDungeonItem[] items, TileEntityDungeonChest.DungeonChestRarity rarity) {
    this.chestX = chestX;
    this.chestY = chestY;
    this.chestZ = chestZ;
    this.items = items;
    this.rarity = rarity;
  }
  
  public int getChestX() {
    return this.chestX;
  }
  
  public int getChestY() {
    return this.chestY;
  }
  
  public int getChestZ() {
    return this.chestZ;
  }
  
  public RPGDungeonPlayerData.RPGDungeonItem[] getItems() {
    return this.items;
  }
  
  public TileEntityDungeonChest.DungeonChestRarity getRarity() {
    return this.rarity;
  }
  
  public void read(ByteBuf buf) {
    this.chestX = buf.readInt();
    this.chestY = buf.readInt();
    this.chestZ = buf.readInt();
    int length = buf.readInt();
    this.items = new RPGDungeonPlayerData.RPGDungeonItem[length];
    for (int i = 0; i < length; i++)
      this.items[i] = new RPGDungeonPlayerData.RPGDungeonItem(UUIDUtils.from(ByteBufUtils.readUTF8String(buf)), ByteBufUtils.readUTF8String(buf), ByteBufUtils.readItemStack(buf), RPGItemRarity.values()[buf.readInt()]); 
    this.rarity = TileEntityDungeonChest.DungeonChestRarity.values()[buf.readInt()];
  }
  
  public void write(ByteBuf buf) {
    buf.writeInt(this.chestX);
    buf.writeInt(this.chestY);
    buf.writeInt(this.chestZ);
    buf.writeInt(this.items.length);
    for (RPGDungeonPlayerData.RPGDungeonItem item : this.items) {
      ByteBufUtils.writeUTF8String(buf, UUIDUtils.toString(item.getUniqueId()));
      ByteBufUtils.writeUTF8String(buf, item.getType());
      ByteBufUtils.writeItemStack(buf, item.getItem());
      buf.writeInt(item.getRarity().ordinal());
    } 
    buf.writeInt(this.rarity.ordinal());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\chest\UIDungeonChest$UIDungeonChestData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */