package fr.paladium.palamod.modules.pillage.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.asgard.GlStateManager;
import fr.paladium.palamod.modules.pillage.common.blocks.tileentities.TECoordinateJammer;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

public class ClientEventHandler {
  private static final Minecraft MC = Minecraft.func_71410_x();
  
  public static float partialTicks;
  
  public static long remain;
  
  public static long lastRemain;
  
  @SubscribeEvent
  public void onRenderWorld(RenderWorldLastEvent event) {
    GlStateManager.pushMatrix();
    EntityClientPlayerMP entityClientPlayerMP = MC.field_71439_g;
    double x = ((EntityPlayer)entityClientPlayerMP).field_70142_S + (((EntityPlayer)entityClientPlayerMP).field_70165_t - ((EntityPlayer)entityClientPlayerMP).field_70142_S) * event.partialTicks;
    double y = ((EntityPlayer)entityClientPlayerMP).field_70137_T + (((EntityPlayer)entityClientPlayerMP).field_70163_u - ((EntityPlayer)entityClientPlayerMP).field_70137_T) * event.partialTicks;
    double z = ((EntityPlayer)entityClientPlayerMP).field_70136_U + (((EntityPlayer)entityClientPlayerMP).field_70161_v - ((EntityPlayer)entityClientPlayerMP).field_70136_U) * event.partialTicks;
    GlStateManager.translate(-x, -y, -z);
    MC.field_71441_e.field_147482_g.forEach(o -> {
          if (o instanceof TECoordinateJammer) {
            TECoordinateJammer teCoordinateJammer = (TECoordinateJammer)o;
            if (teCoordinateJammer.getOwner() != null && teCoordinateJammer.getOwner().equals(rootPlayer.func_146103_bH())) {
              GL11.glDepthMask(false);
              GL11.glDisable(3553);
              GL11.glDisable(2896);
              GL11.glDisable(2884);
              GL11.glDisable(3042);
              RenderGlobal.func_147590_a(AxisAlignedBB.func_72330_a(teCoordinateJammer.field_145851_c, teCoordinateJammer.field_145848_d, teCoordinateJammer.field_145849_e, (teCoordinateJammer.field_145851_c + 1), (teCoordinateJammer.field_145848_d + 1), (teCoordinateJammer.field_145849_e + 1)), Color.RED.getRGB());
              GL11.glEnable(3553);
              GL11.glEnable(2896);
              GL11.glEnable(2884);
              GL11.glDisable(3042);
              GL11.glDepthMask(true);
            } 
          } 
        });
    GlStateManager.popMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\client\ClientEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */