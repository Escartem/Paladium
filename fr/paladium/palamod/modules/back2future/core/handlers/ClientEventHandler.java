package fr.paladium.palamod.modules.back2future.core.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.blocks.PrismarineBlocks;
import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import fr.paladium.palamod.modules.back2future.client.PrismarineIcon;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldEvent;
import net.minecraftforge.client.event.TextureStitchEvent;

public class ClientEventHandler {
  public static final ClientEventHandler INSTANCE = new ClientEventHandler();
  
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void loadTextures(TextureStitchEvent.Pre event) {
    if (Back2Future.enablePrismarine && 
      event.map.func_130086_a() == 0) {
      PrismarineIcon prismarineIcon = new PrismarineIcon("prismarine_rough");
      if (event.map.setTextureEntry("prismarine_rough", (TextureAtlasSprite)prismarineIcon)) {
        ((PrismarineBlocks)ModBlocks.prismarine).setIcon(0, (IIcon)prismarineIcon);
      } else {
        ((PrismarineBlocks)ModBlocks.prismarine).setIcon(0, event.map
            .func_94245_a("prismarine_rough"));
      } 
    } 
  }
  
  @SubscribeEvent
  public void renderWorldEventPost(RenderWorldEvent.Post event) {}
  
  @SubscribeEvent
  public void renderPlayerEventPre(RenderPlayerEvent.Pre event) {
    if (Back2Future.enableTransparentAmour) {
      OpenGLHelper.enableBlend();
      OpenGLHelper.blendFunc(770, 771);
    } 
  }
  
  @SubscribeEvent
  public void renderPlayerSetArmour(RenderPlayerEvent.SetArmorModel event) {
    if (Back2Future.enableTransparentAmour) {
      OpenGLHelper.enableBlend();
      OpenGLHelper.blendFunc(770, 771);
    } 
  }
  
  @SubscribeEvent
  public void renderPlayerEventPost(RenderPlayerEvent.Post event) {
    if (Back2Future.enableTransparentAmour)
      OpenGLHelper.disableBlend(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\core\handlers\ClientEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */