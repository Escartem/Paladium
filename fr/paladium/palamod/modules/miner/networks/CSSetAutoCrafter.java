package fr.paladium.palamod.modules.miner.networks;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.modules.miner.tileentity.TileEntityAutoCrafter;
import fr.paladium.palamod.modules.miner.utils.AutoCrafterManager;
import fr.paladium.palamod.modules.miner.utils.AutoCrafterRecipe;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class CSSetAutoCrafter implements IMessage {
  public int index;
  
  public int x;
  
  public int y;
  
  public int z;
  
  public CSSetAutoCrafter() {}
  
  public CSSetAutoCrafter(int index, int x, int y, int z) {
    this.index = index;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.index = buf.readInt();
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.index);
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
  }
  
  public static class Handler implements IMessageHandler<CSSetAutoCrafter, IMessage> {
    public IMessage onMessage(CSSetAutoCrafter message, MessageContext ctx) {
      if (!(ctx.getServerHandler()).field_147369_b.field_70170_p.func_72899_e(message.x, message.y, message.z))
        return null; 
      if ((ctx.getServerHandler()).field_147369_b.field_70170_p.func_147438_o(message.x, message.y, message.z) != null) {
        TileEntity te = (ctx.getServerHandler()).field_147369_b.field_70170_p.func_147438_o(message.x, message.y, message.z);
        if (te instanceof TileEntityAutoCrafter) {
          TileEntityAutoCrafter tileEntityAutoCrafter = (TileEntityAutoCrafter)te;
          EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
          if (player.func_70011_f(message.x, message.y, message.z) < 8.0D && 
            player.field_71070_bA instanceof fr.paladium.palamod.modules.miner.container.ContainerAutoCrafter && 
            AutoCrafterManager.getRecipes().size() > message.index) {
            Object key = AutoCrafterManager.getRecipes().keySet().toArray()[message.index];
            AutoCrafterRecipe recipe = (AutoCrafterRecipe)AutoCrafterManager.getRecipes().get(key);
            if (recipe.getResult() == null) {
              (new Notification(Notification.NotificationType.ERROR, "Une erreur est survenue", "autocrafter")).send(player);
              return null;
            } 
            if (!JobsBridge.canUseCraft((EntityPlayer)player, recipe.getResult(), true)) {
              (new Notification(Notification.NotificationType.ERROR, "Vous n'avez pas le niveau requis", "autocrafter")).send(player);
              return null;
            } 
            tileEntityAutoCrafter.setCraftingResult(recipe.getResult());
            tileEntityAutoCrafter.setRecipe(recipe);
            player.field_71135_a.func_147359_a(tileEntityAutoCrafter.func_145844_m());
          } 
        } 
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\networks\CSSetAutoCrafter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */