package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.entity.EntityFakePlayer;
import fr.paladium.palamod.modules.pillage.PPillage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ClientHandler implements IMessageHandler<PacketBindSkin, IMessage> {
  public IMessage onMessage(PacketBindSkin message, MessageContext ctx) {
    Entity entity = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_73045_a(message.id);
    if (entity instanceof EntityFakePlayer) {
      EntityFakePlayer fakePlayer = (EntityFakePlayer)entity;
      ResourceLocation resourceLocation = new ResourceLocation(fakePlayer.getCloneSkin());
      resourceLocation = PPillage.proxy.bindSkin(fakePlayer.getCloneUUID().toString());
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketBindSkin$ClientHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */