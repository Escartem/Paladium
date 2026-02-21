package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.data.MarchPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class StarRenderListener {
  private static final ResourceLocation STAR_BACKGROUND = new ResourceLocation("palamod", "textures/ui/march/head_in_stars/head_in_stars.png");
  
  @SubscribeEvent
  public void onRender(RenderGameOverlayEvent.Post event) {
    Minecraft minecraft = Minecraft.func_71410_x();
    EntityClientPlayerMP entityClientPlayerMP = minecraft.field_71439_g;
    if (entityClientPlayerMP == null)
      return; 
    ItemStack helmetStack = ((EntityPlayer)entityClientPlayerMP).field_71071_by.field_70460_b[3];
    if (!entityClientPlayerMP.func_82165_m(PotionsRegister.STAR.getPotionId()) && (helmetStack == null || helmetStack
      .func_77973_b() != ItemsRegister.STAR_HELMET))
      return; 
    ScaledResolution resolution = new ScaledResolution(minecraft, minecraft.field_71443_c, minecraft.field_71440_d);
    GuiUtils.drawImageTransparent(0.0D, 0.0D, STAR_BACKGROUND, resolution.func_78326_a(), resolution.func_78328_b());
  }
  
  @SubscribeEvent
  public void onWarStarTick(RenderPlayerEvent.Specials.Post event) {
    MarchPlayer marchPlayer = MarchPlayer.get(event.entityPlayer);
    if (marchPlayer != null && marchPlayer.getWarStarTimestamp() >= System.currentTimeMillis())
      for (int i = 0; i < 100; i++) {
        double offsetX = (Math.random() - 0.5D) * 2.0D;
        double offsetY = Math.random() * 2.0D;
        double offsetZ = (Math.random() - 0.5D) * 2.0D;
        double particleRed = Math.random();
        double particleGreen = Math.random();
        double particleBlue = Math.random();
        event.entity.field_70170_p.func_72869_a("reddust", event.entity.field_70165_t + offsetX, event.entity.field_70163_u + offsetY + ((Minecraft.func_71410_x()).field_71439_g.func_70005_c_().equalsIgnoreCase(event.entity.func_70005_c_()) ? -2 : false), event.entity.field_70161_v + offsetZ, particleRed, particleGreen, particleBlue);
      }  
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\listener\StarRenderListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */