package fr.paladium.palamod.modules.alchimiste.common.network.servertoclient;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;

public class Handler implements IMessageHandler<SCSyncExperience, IMessage> {
  public IMessage onMessage(SCSyncExperience message, MessageContext ctx) {
    (Minecraft.func_71410_x()).field_71439_g.field_71106_cc = message.experience;
    (Minecraft.func_71410_x()).field_71439_g.field_71068_ca = message.experienceLevel;
    (Minecraft.func_71410_x()).field_71439_g.field_71067_cb = message.experienceTotal;
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\network\servertoclient\SCSyncExperience$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */