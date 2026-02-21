package fr.paladium.palawither.server.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palawither.api.BlocksRegister;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.BlockEvent;

public class ServerWitherReceptacleListener {
  @SubscribeEvent
  public void onPlace(BlockEvent.PlaceEvent event) {
    if (event.block != BlocksRegister.WITHER_RECEPTACLE || event.player.field_71075_bZ.field_75098_d)
      return; 
    if (event.y < 5) {
      event.setCanceled(true);
      event.player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas placer un réceptacle de Wither au niveau de la bedrock."));
      return;
    } 
    Chunk chunk = event.world.func_72938_d(event.x, event.z);
    if (chunk == null || !chunk.field_76636_d)
      return; 
    boolean found = false;
    for (Object tileEntity : chunk.field_150816_i.values()) {
      if (tileEntity instanceof TileEntityWitherReceptacle) {
        if (found) {
          event.setCanceled(true);
          event.player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous ne pouvez pas placer plus d'un réceptacle de Wither par chunk."));
          return;
        } 
        found = true;
      } 
    } 
  }
  
  @SubscribeEvent(receiveCanceled = true, priority = EventPriority.HIGH)
  public void onBreak(BlockEvent.BreakEvent event) {
    if ((event.block != BlocksRegister.WITHER_RECEPTACLE && event.block != Blocks.field_150350_a) || (event.getPlayer()).field_71075_bZ.field_75098_d)
      return; 
    TileEntity tileEntity = event.world.func_147438_o(event.x, event.y, event.z);
    if (!(tileEntity instanceof TileEntityWitherReceptacle))
      return; 
    TileEntityWitherReceptacle receptacle = (TileEntityWitherReceptacle)tileEntity;
    if (receptacle.getHealth() <= 0.0D || !receptacle.isActive())
      return; 
    event.setCanceled(true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\server\listener\ServerWitherReceptacleListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */