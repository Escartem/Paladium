package fr.paladium.palamod.modules.hunter.networks;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Handler implements IMessageHandler<PacketUpdateHunterCompass, IMessage> {
  public IMessage onMessage(PacketUpdateHunterCompass message, MessageContext ctx) {
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    for (ItemStack item : player.field_71071_by.field_70462_a) {
      if (item != null && 
        item.func_77973_b() == ItemsRegister.HUNTER_COMPASS && 
        item.func_77960_j() == message.type) {
        NBTTagCompound compound = new NBTTagCompound();
        if (item.func_77942_o())
          compound = item.func_77978_p(); 
        compound.func_74768_a("x", message.x);
        compound.func_74768_a("y", message.y);
        compound.func_74768_a("z", message.z);
        item.func_77982_d(compound);
      } 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\networks\PacketUpdateHunterCompass$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */