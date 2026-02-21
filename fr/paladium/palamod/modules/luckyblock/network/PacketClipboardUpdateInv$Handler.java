package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class Handler implements IMessageHandler<PacketClipboardUpdateInv, IMessage> {
  public IMessage onMessage(PacketClipboardUpdateInv message, MessageContext ctx) {
    ItemStack stackostuff = PacketClipboardUpdateInv.access$000(message);
    if (stackostuff != null && stackostuff.func_77973_b() instanceof fr.paladium.palamod.modules.luckyblock.items.ItemClipboard) {
      ItemStack currentPlayerSlot = (ctx.getServerHandler()).field_147369_b.field_71071_by.func_70448_g();
      if (currentPlayerSlot != null && 
        currentPlayerSlot.func_77969_a(stackostuff)) {
        NBTTagCompound currentTags = currentPlayerSlot.func_77978_p();
        NBTTagCompound newTags = stackostuff.func_77978_p();
        if (currentTags != null && currentTags.func_74764_b("Inventory") && newTags != null) {
          NBTTagList tagList = currentTags.func_150295_c("Inventory", 10);
          newTags.func_74782_a("Inventory", (NBTBase)tagList);
          stackostuff.func_77982_d(newTags);
          stackostuff.field_77994_a = currentPlayerSlot.field_77994_a;
          (ctx.getServerHandler()).field_147369_b.field_71071_by.func_70299_a((ctx.getServerHandler()).field_147369_b.field_71071_by.field_70461_c, stackostuff);
        } 
      } 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketClipboardUpdateInv$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */