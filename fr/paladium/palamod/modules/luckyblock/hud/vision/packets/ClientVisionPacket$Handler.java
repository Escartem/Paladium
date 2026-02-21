package fr.paladium.palamod.modules.luckyblock.hud.vision.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.hud.vision.ModuleVision;
import fr.paladium.palamod.util.ItemStackSerializer;
import java.util.Base64;
import net.minecraft.item.ItemStack;

public class Handler implements IMessageHandler<ClientVisionPacket, IMessage> {
  public IMessage onMessage(ClientVisionPacket message, MessageContext ctx) {
    String[] splitted = ClientVisionPacket.access$000(message).split(";");
    int i = 0;
    for (String string : splitted) {
      if (!string.isEmpty());
      ItemStack is = ItemStackSerializer.read(new String(Base64.getDecoder().decode(string)));
      ClientProxy.inventoryVision[i] = is;
      i++;
    } 
    ClientProxy.hasVisionSkill = true;
    ClientProxy.healthVision = ClientVisionPacket.access$100(message);
    ClientProxy.nameVision = ClientVisionPacket.access$200(message);
    ModuleVision.getInstance().setLastUpdate(System.currentTimeMillis());
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\hud\vision\packets\ClientVisionPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */