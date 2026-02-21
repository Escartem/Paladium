package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.modules.spellsv2.entity.EntityGhost;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class Handler implements IMessageHandler<PacketClientUseMentalis, IMessage> {
  public IMessage onMessage(PacketClientUseMentalis message, MessageContext ctx) {
    if (message.active) {
      Constants.logger.info("mentalis contains player : !" + ClientManager.getMentalis()
          .containsKey((Minecraft.func_71410_x()).field_71439_g.func_110124_au()));
      if (!ClientManager.getMentalis().containsKey((Minecraft.func_71410_x()).field_71439_g.func_110124_au())) {
        EntityGhost ghost = null;
        Constants.logger.info("Entities size : " + 
            (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_72872_a(EntityGhost.class, 
              AxisAlignedBB.func_72330_a((Minecraft.func_71410_x()).field_71439_g.field_70165_t - 10.0D, 
                (Minecraft.func_71410_x()).field_71439_g.field_70163_u - 10.0D, 
                (Minecraft.func_71410_x()).field_71439_g.field_70161_v - 10.0D, 
                (Minecraft.func_71410_x()).field_71439_g.field_70165_t + 10.0D, 
                (Minecraft.func_71410_x()).field_71439_g.field_70163_u + 10.0D, 
                (Minecraft.func_71410_x()).field_71439_g.field_70161_v + 10.0D))
            .size());
        for (Object obj : (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_72872_a(EntityGhost.class, 
            
            AxisAlignedBB.func_72330_a((Minecraft.func_71410_x()).field_71439_g.field_70165_t - 10.0D, 
              (Minecraft.func_71410_x()).field_71439_g.field_70163_u - 10.0D, 
              (Minecraft.func_71410_x()).field_71439_g.field_70161_v - 10.0D, 
              (Minecraft.func_71410_x()).field_71439_g.field_70165_t + 10.0D, 
              (Minecraft.func_71410_x()).field_71439_g.field_70163_u + 10.0D, 
              (Minecraft.func_71410_x()).field_71439_g.field_70161_v + 10.0D))) {
          Constants.logger.info(obj);
          if (obj instanceof EntityGhost) {
            EntityGhost g = (EntityGhost)obj;
            if (g.owner != null) {
              if (g.owner.equals((Minecraft.func_71410_x()).field_71439_g.func_70005_c_())) {
                ghost = g;
                continue;
              } 
              Constants.logger.info(g.owner + " != " + 
                  (Minecraft.func_71410_x()).field_71439_g.func_70005_c_());
              continue;
            } 
            Constants.logger.info("g.owner is null");
          } 
        } 
        if (ghost != null) {
          ClientManager.addMentalis((Minecraft.func_71410_x()).field_71439_g.func_110124_au(), ghost);
          (Minecraft.func_71410_x()).field_71451_h = (EntityLivingBase)ghost;
          (Minecraft.func_71410_x()).field_71474_y.field_74320_O = 1;
        } else {
          (Minecraft.func_71410_x()).field_71439_g
            .func_145747_a((IChatComponent)new ChatComponentText("§cUne erreur est survenue."));
        } 
      } 
    } else {
      ClientManager.removeMentalis((Minecraft.func_71410_x()).field_71439_g.func_110124_au());
      (Minecraft.func_71410_x())
        
        .field_71451_h = (EntityLivingBase)(Minecraft.func_71410_x()).field_71439_g.func_130014_f_().func_73045_a((Minecraft.func_71410_x()).field_71439_g.func_145782_y());
      (Minecraft.func_71410_x()).field_71474_y.field_74320_O = 0;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientUseMentalis$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */