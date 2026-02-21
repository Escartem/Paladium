package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.events;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.lib.apollon.utils.GlStateManager;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.blocks.ModelParrotPlush;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;

public class ParrotEventHandler {
  private final ModelParrotPlush model = new ModelParrotPlush();
  
  private final float scale = 0.6F;
  
  private final ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/parrot_plush.png");
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onRenderPlayerSpecialPre(RenderPlayerEvent.Pre event) {
    EntityPlayer entity = event.entityPlayer;
    if (!MonthlyUtils.hasParrotEffect((EntityLivingBase)entity))
      return; 
    Minecraft.func_71410_x().func_110434_K().func_110577_a(this.texture);
    GL11.glPushMatrix();
    GL11.glScaled(this.scale, this.scale, this.scale);
    rotate(180.0F, 180.0F, 0.0F);
    GL11.glTranslated(-0.9D, -1.55D, -0.1D);
    this.model.renderAll();
    GL11.glPopMatrix();
  }
  
  public void rotate(float x, float y, float z) {
    GlStateManager.rotate(z, 0.0F, 0.0F, 1.0F);
    GlStateManager.rotate(y, 0.0F, 1.0F, 0.0F);
    GlStateManager.rotate(x, 1.0F, 0.0F, 0.0F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\events\ParrotEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */