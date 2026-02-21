package fr.paladium.palamod.modules.back2future.core.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.client.model.ModelGolem;
import fr.paladium.palamod.modules.back2future.client.renderer.block.BlockChestRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.block.BlockChorusFlowerRender;
import fr.paladium.palamod.modules.back2future.client.renderer.block.BlockChorusPlantRender;
import fr.paladium.palamod.modules.back2future.client.renderer.block.BlockDoorRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.block.BlockEndRodRender;
import fr.paladium.palamod.modules.back2future.client.renderer.block.BlockSlimeBlockRender;
import fr.paladium.palamod.modules.back2future.client.renderer.entity.ArmourStandRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.entity.EndermiteRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.entity.HuskRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.entity.LingeringEffectRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.entity.LingeringPotionRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.entity.NewSnowGolemRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.entity.PlacedEndCrystalRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.entity.RabbitRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.entity.RenderBoulder;
import fr.paladium.palamod.modules.back2future.client.renderer.entity.RenderCustomFallingBlock;
import fr.paladium.palamod.modules.back2future.client.renderer.entity.RenderGolem;
import fr.paladium.palamod.modules.back2future.client.renderer.entity.StrayOverlayRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.entity.StrayRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.entity.VillagerZombieRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.item.ItemBannerRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.item.ItemBowRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.item.ItemSkullRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.tileentity.TileEntityBannerRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.tileentity.TileEntityEndRodRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.tileentity.TileEntityFancySkullRenderer;
import fr.paladium.palamod.modules.back2future.client.renderer.tileentity.TileEntityNewBeaconRenderer;
import fr.paladium.palamod.modules.back2future.core.handlers.ClientEventHandler;
import fr.paladium.palamod.modules.back2future.entities.EntityArmourStand;
import fr.paladium.palamod.modules.back2future.entities.EntityEndermite;
import fr.paladium.palamod.modules.back2future.entities.EntityGolem;
import fr.paladium.palamod.modules.back2future.entities.EntityHusk;
import fr.paladium.palamod.modules.back2future.entities.EntityLingeringEffect;
import fr.paladium.palamod.modules.back2future.entities.EntityLingeringPotion;
import fr.paladium.palamod.modules.back2future.entities.EntityNewSnowGolem;
import fr.paladium.palamod.modules.back2future.entities.EntityPlacedEndCrystal;
import fr.paladium.palamod.modules.back2future.entities.EntityRabbit;
import fr.paladium.palamod.modules.back2future.entities.EntityStray;
import fr.paladium.palamod.modules.back2future.entities.EntityZombieVillager;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityBoulder;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityCustomFallingBlock;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityBanner;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityEndRod;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityNewBeacon;
import java.lang.reflect.Method;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.util.JsonException;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
  private static Method f_loadShader;
  
  public void registerEvents() {
    super.registerEvents();
    FMLCommonHandler.instance().bus().register(ClientEventHandler.INSTANCE);
    MinecraftForge.EVENT_BUS.register(ClientEventHandler.INSTANCE);
  }
  
  public void registerRenderers() {
    registerItemRenderers();
    registerBlockRenderers();
    registerEntityRenderers();
  }
  
  public static void sobelShader() {
    try {
      loadShader((Minecraft.func_71410_x()).field_71460_t, new ResourceLocation("shaders/post/sobel.json"));
    } catch (Throwable e) {
      e.printStackTrace();
    } 
  }
  
  public static void loadShader(EntityRenderer entity, ResourceLocation p_175069_1_) {
    try {
      entity
        .field_147707_d = new ShaderGroup(Minecraft.func_71410_x().func_110434_K(), Minecraft.func_71410_x().func_110442_L(), Minecraft.func_71410_x().func_147110_a(), p_175069_1_);
      entity.field_147707_d.func_148026_a((Minecraft.func_71410_x()).field_71443_c, 
          (Minecraft.func_71410_x()).field_71440_d);
    } catch (JsonException e) {
      e.printStackTrace();
    } 
  }
  
  public static void clearShader() {
    (Minecraft.func_71410_x()).field_71460_t.field_147707_d = null;
  }
  
  private void registerItemRenderers() {
    if (Back2Future.enableBanners)
      MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(ModBlocks.banner), (IItemRenderer)new ItemBannerRenderer()); 
    if (Back2Future.enableFancySkulls)
      MinecraftForgeClient.registerItemRenderer(Items.field_151144_bL, (IItemRenderer)new ItemSkullRenderer()); 
    if (Back2Future.enableBowRendering)
      MinecraftForgeClient.registerItemRenderer((Item)Items.field_151031_f, (IItemRenderer)new ItemBowRenderer()); 
  }
  
  private void registerBlockRenderers() {
    if (Back2Future.enableSlimeBlock)
      RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockSlimeBlockRender()); 
    if (Back2Future.enableDoors)
      RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockDoorRenderer()); 
    if (Back2Future.enableBanners)
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBanner.class, (TileEntitySpecialRenderer)new TileEntityBannerRenderer()); 
    if (Back2Future.enableFancySkulls)
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySkull.class, (TileEntitySpecialRenderer)new TileEntityFancySkullRenderer()); 
    if (Back2Future.enableChorusFruit) {
      RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockEndRodRender());
      RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockChorusFlowerRender());
      RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockChorusPlantRender());
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEndRod.class, (TileEntitySpecialRenderer)new TileEntityEndRodRenderer());
    } 
    if (Back2Future.enableColourfulBeacons)
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNewBeacon.class, (TileEntitySpecialRenderer)new TileEntityNewBeaconRenderer()); 
    RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockChestRenderer());
  }
  
  private void registerEntityRenderers() {
    if (Back2Future.enableArmourStand)
      RenderingRegistry.registerEntityRenderingHandler(EntityArmourStand.class, (Render)new ArmourStandRenderer()); 
    if (Back2Future.enableEndermite)
      RenderingRegistry.registerEntityRenderingHandler(EntityEndermite.class, (Render)new EndermiteRenderer()); 
    if (Back2Future.enableRabbit)
      RenderingRegistry.registerEntityRenderingHandler(EntityRabbit.class, (Render)new RabbitRenderer()); 
    if (Back2Future.enableLingeringPotions) {
      RenderingRegistry.registerEntityRenderingHandler(EntityLingeringPotion.class, (Render)new LingeringPotionRenderer());
      RenderingRegistry.registerEntityRenderingHandler(EntityLingeringEffect.class, (Render)new LingeringEffectRenderer());
    } 
    RenderingRegistry.registerEntityRenderingHandler(EntityHusk.class, (Render)new HuskRenderer());
    RenderingRegistry.registerEntityRenderingHandler(EntityStray.class, (Render)new StrayRenderer());
    RenderingRegistry.registerEntityRenderingHandler(EntityStray.class, (Render)new StrayOverlayRenderer());
    RenderingRegistry.registerEntityRenderingHandler(EntityGolem.class, (Render)new RenderGolem((ModelBase)new ModelGolem(), 1.0F));
    RenderingRegistry.registerEntityRenderingHandler(EntityBoulder.class, (Render)new RenderBoulder());
    RenderingRegistry.registerEntityRenderingHandler(EntityCustomFallingBlock.class, (Render)new RenderCustomFallingBlock());
    if (Back2Future.enableVillagerZombies)
      RenderingRegistry.registerEntityRenderingHandler(EntityZombieVillager.class, (Render)new VillagerZombieRenderer()); 
    if (Back2Future.enableDragonRespawn)
      RenderingRegistry.registerEntityRenderingHandler(EntityPlacedEndCrystal.class, (Render)new PlacedEndCrystalRenderer()); 
    if (Back2Future.enableShearableGolems)
      RenderingRegistry.registerEntityRenderingHandler(EntityNewSnowGolem.class, (Render)new NewSnowGolemRenderer()); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\core\proxy\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */