package fr.paladium.palamod.modules.pillage.network.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.homefinder.common.entities.EntityFakePlayer;
import fr.paladium.palamod.modules.pillage.PPillage;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class ClientHandler implements IMessageHandler<PacketBindSkin, IMessage> {
  public IMessage onMessage(PacketBindSkin message, MessageContext ctx) {
    Entity entity = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_73045_a(message.id);
    if (entity instanceof EntityFakePlayer) {
      EntityFakePlayer fakePlayer = (EntityFakePlayer)entity;
      fakePlayer.playerSkin = PPillage.proxy.bindSkin(FastUUID.toString((Entity)fakePlayer));
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\network\packets\PacketBindSkin$ClientHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */