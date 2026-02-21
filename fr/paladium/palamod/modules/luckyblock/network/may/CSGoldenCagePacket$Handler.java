package fr.paladium.palamod.modules.luckyblock.network.may;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.block.BlockGoldenCage;

public class Handler implements IMessageHandler<CSGoldenCagePacket, IMessage> {
  public IMessage onMessage(CSGoldenCagePacket message, MessageContext ctx) {
    if (ctx.side != Side.SERVER)
      return null; 
    BlockGoldenCage blockCage = (BlockGoldenCage)BlocksRegister.GOLDEN_CAGE;
    blockCage.changeDefaultPet((ctx.getServerHandler()).field_147369_b, CSGoldenCagePacket.access$000(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\may\CSGoldenCagePacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */