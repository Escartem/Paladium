package fr.paladium.palamod.modules.paladium.common.network;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import fr.paladium.palamod.modules.paladium.common.blocks.TileEntityTypeMachine;
import fr.paladium.palamod.modules.paladium.utils.FileUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ServerPacketEntriesHandler {
  @SubscribeEvent
  public void serverPacketEvent(FMLNetworkEvent.ServerCustomPacketEvent event) {
    EntityPlayerMP player = ((NetHandlerPlayServer)event.handler).field_147369_b;
    FMLProxyPacket packet = event.packet;
    if (packet != null) {
      if (packet.channel().equals("DecodiumType"))
        handleBookNameUpdate(packet.payload(), player); 
      if (packet.channel().equals("DecodiumTypeFlag"))
        handleBookFlagUpdate(packet.payload()); 
      if (packet.channel().equals("DecodiumTypeDelete"))
        handleBookDeletion(packet.payload()); 
      if (packet.channel().equals("DecodiumTypeUpdate"))
        handleTypsetUpdate(packet.payload(), player.field_70170_p); 
    } 
  }
  
  private void handleTypsetUpdate(ByteBuf packet, World world) {
    int x = packet.readInt();
    int y = packet.readInt();
    int z = packet.readInt();
    if (!world.func_72899_e(x, y, z))
      return; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (tile != null && tile instanceof TileEntityTypeMachine) {
      TileEntityTypeMachine typeTile = (TileEntityTypeMachine)tile;
      typeTile.booklistset();
    } 
  }
  
  private void handleBookDeletion(ByteBuf packet) {
    String bookname = ByteBufUtils.readUTF8String(packet);
    boolean isServer = packet.readBoolean();
    FileUtil util = new FileUtil();
    if (util.deleteBook(!isServer, bookname)) {
      FMLLog.info(bookname + " has been deleted Forever!", new Object[0]);
    } else {
      FMLLog.warning("Deletion of " + bookname + " failed", new Object[0]);
    } 
  }
  
  private void handleBookFlagUpdate(ByteBuf packet) {
    String bookname = ByteBufUtils.readUTF8String(packet);
    boolean newFlag = packet.readBoolean();
    boolean isServer = packet.readBoolean();
    FileUtil util = new FileUtil();
    if (util.updatePublicFlag(!isServer, bookname, newFlag)) {
      String fl = "private";
      if (newFlag)
        fl = "public"; 
    } else {
      FMLLog.warning("Updating book flag for " + bookname + " failed", new Object[0]);
    } 
  }
  
  private void handleBookNameUpdate(ByteBuf packet, EntityPlayerMP player) {
    String bookname = ByteBufUtils.readUTF8String(packet);
    int x = packet.readInt();
    int y = packet.readInt();
    int z = packet.readInt();
    updateTypeMachine(x, y, z, bookname, player);
  }
  
  private void updateTypeMachine(int x, int y, int z, String book, EntityPlayerMP player) {
    World world = player.field_70170_p;
    if (!world.func_72899_e(x, y, z))
      return; 
    TileEntity tile = world.func_147438_o(x, y, z);
    if (tile != null) {
      TileEntityTypeMachine typetile = (TileEntityTypeMachine)tile;
      typetile.setBookname(book);
    } 
  }
  
  private boolean checkIfValidPacketItem(String input) {
    String[] validPacketItems = { "item.AtlasBook", "item.bigbook", "item.RecipeBook", "item.clipboard", "item.BiblioRedBook", "item.SlottedBook", "item.BiblioWayPointCompass" };
    for (int i = 0; i < validPacketItems.length; i++) {
      if (validPacketItems[i].equals(input))
        return true; 
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\network\ServerPacketEntriesHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */