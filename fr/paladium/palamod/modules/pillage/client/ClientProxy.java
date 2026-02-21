package fr.paladium.palamod.modules.pillage.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.betternei.api.BNEIAPI;
import fr.paladium.betternei.api.craft.factory.ICraftFactory;
import fr.paladium.helios.module.coordinates.ModuleCoordinates;
import fr.paladium.palamod.modules.homefinder.client.render.RenderFakePlayer;
import fr.paladium.palamod.modules.homefinder.common.entities.EntityFakePlayer;
import fr.paladium.palamod.modules.pillage.client.betternei.SlimeObsiRecipeFactory;
import fr.paladium.palamod.modules.pillage.client.render.RenderEffectPrimedTNT;
import fr.paladium.palamod.modules.pillage.client.render.RenderSpongeSheep;
import fr.paladium.palamod.modules.pillage.common.CommonProxy;
import fr.paladium.palamod.modules.pillage.common.blocks.tileentities.TECoordinateJammer;
import fr.paladium.palamod.modules.pillage.common.effects.EntityTNTEffectPrimed;
import fr.paladium.palamod.modules.pillage.common.entities.EntitySpongeSheep;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
  public static int inventoryID;
  
  public void preInit() {
    super.preInit();
    MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    FMLCommonHandler.instance().bus().register(new ClientEventHandler());
    BNEIAPI.registerCraftFatory((ICraftFactory)new SlimeObsiRecipeFactory());
  }
  
  public void init() {
    super.init();
    ModuleCoordinates.getInstance().addHandler((x, y, z) -> {
          for (Object o : (Minecraft.func_71410_x()).field_71441_e.field_147482_g) {
            if (o instanceof TECoordinateJammer) {
              TECoordinateJammer te = (TECoordinateJammer)o;
              if (te.isPlayerInRange((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g)) {
                x = MathHelper.func_76128_c((Minecraft.func_71410_x()).field_71439_g.field_70165_t / 2.0D) + te.getFakeX();
                z = MathHelper.func_76128_c((Minecraft.func_71410_x()).field_71439_g.field_70161_v / 2.0D) + te.getFakeZ();
              } 
            } 
          } 
          return new int[] { x, y, z };
        });
  }
  
  public void registerRenders() {
    inventoryID = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerEntityRenderingHandler(EntityTNTEffectPrimed.class, (Render)new RenderEffectPrimedTNT());
    RenderingRegistry.registerEntityRenderingHandler(EntitySpongeSheep.class, (Render)new RenderSpongeSheep());
    RenderingRegistry.registerEntityRenderingHandler(EntityFakePlayer.class, (Render)new RenderFakePlayer());
  }
  
  public ResourceLocation bindSkin(String playerUUID) {
    ResourceLocation resourcelocation = getLocationSkin(playerUUID);
    getDownloadImageSkin(resourcelocation, playerUUID);
    return resourcelocation;
  }
  
  private void getDownloadImageSkin(ResourceLocation path, String playerUUID) {
    TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
    ITextureObject object = texturemanager.func_110581_b(path);
    if (object == null) {
      ThreadDownloadImageData threadDownloadImageData = new ThreadDownloadImageData(null, String.format("https://minotar.net/skin/%s", new Object[] { StringUtils.func_76338_a(playerUUID) }), AbstractClientPlayer.field_110314_b, (IImageBuffer)new ImageBufferDownload());
      texturemanager.func_110579_a(path, (ITextureObject)threadDownloadImageData);
    } 
  }
  
  public ResourceLocation getLocationSkin(String playerUUID) {
    return new ResourceLocation("skins/" + StringUtils.func_76338_a(playerUUID));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */