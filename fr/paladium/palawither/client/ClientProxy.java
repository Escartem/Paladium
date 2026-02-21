package fr.paladium.palawither.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import fr.paladium.palawither.api.BlocksRegister;
import fr.paladium.palawither.api.ItemsRegister;
import fr.paladium.palawither.client.listener.ClientWitherListener;
import fr.paladium.palawither.client.render.block.BlockWitherReceptacleRenderer;
import fr.paladium.palawither.client.render.entity.RenderLindwormWither;
import fr.paladium.palawither.client.render.entity.RenderSupremeWither;
import fr.paladium.palawither.client.render.entity.RenderVanillaWither;
import fr.paladium.palawither.client.render.entity.projectile.RenderLindwormWitherProjectile;
import fr.paladium.palawither.client.render.item.ItemWitherHeadRenderer;
import fr.paladium.palawither.client.render.item.ItemWitherTargetLaserSystemRenderer;
import fr.paladium.palawither.common.CommonProxy;
import fr.paladium.palawither.common.entity.EntityBabyWither;
import fr.paladium.palawither.common.entity.EntityPassiveWither;
import fr.paladium.palawither.common.entity.EntityThirstyWither;
import fr.paladium.palawither.common.entity.projectile.EntityBabyWitherProjectile;
import fr.paladium.palawither.common.entity.projectile.EntityPredatorWitherProjectile;
import fr.paladium.palawither.common.entity.projectile.EntitySupremeWitherProjectile;
import fr.paladium.palawither.common.entity.targetable.EntityPredatorWither;
import fr.paladium.palawither.common.entity.targetable.EntitySupremeWither;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import lombok.NonNull;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    initRender();
    initListener();
  }
  
  private void initRender() {
    RenderManager.field_78727_a.field_78729_o.put(EntityWither.class, new RenderVanillaWither());
    RenderingRegistry.registerEntityRenderingHandler(EntityBabyWither.class, (Render)getRender("baby_wither"));
    RenderingRegistry.registerEntityRenderingHandler(EntityPassiveWither.class, (Render)getRender("passive_wither"));
    RenderingRegistry.registerEntityRenderingHandler(EntityThirstyWither.class, (Render)getRender("thirsty_wither"));
    RenderingRegistry.registerEntityRenderingHandler(EntityPredatorWither.class, (Render)getRender("predator_wither"));
    RenderingRegistry.registerEntityRenderingHandler(EntitySupremeWither.class, (Render)new RenderSupremeWither());
    RenderingRegistry.registerEntityRenderingHandler(EntityBabyWitherProjectile.class, (Render)getProjectileRender("baby_wither"));
    RenderingRegistry.registerEntityRenderingHandler(EntityPredatorWitherProjectile.class, (Render)getProjectileRender("predator_wither"));
    RenderingRegistry.registerEntityRenderingHandler(EntitySupremeWitherProjectile.class, (Render)getProjectileRender("supreme_wither"));
    BlockWitherReceptacleRenderer witherReceptacleRenderer = new BlockWitherReceptacleRenderer();
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWitherReceptacle.class, (TileEntitySpecialRenderer)witherReceptacleRenderer);
    MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(BlocksRegister.WITHER_RECEPTACLE), (IItemRenderer)witherReceptacleRenderer);
    FMLCommonHandler.instance().bus().register(witherReceptacleRenderer);
    MinecraftForge.EVENT_BUS.register(witherReceptacleRenderer);
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.PASSIVE_WITHER_HEAD, (IItemRenderer)getHeadRender("passive_wither"));
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.PREDATOR_WITHER_HEAD, (IItemRenderer)getHeadRender("predator_wither"));
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.THIRSTY_WITHER_HEAD, (IItemRenderer)getHeadRender("thirsty_wither"));
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.SUPREME_WITHER_HEAD, (IItemRenderer)getHeadRender("supreme_wither"));
    MinecraftForgeClient.registerItemRenderer(ItemsRegister.WITHER_TARGET_LASER_SYSTEM, (IItemRenderer)new ItemWitherTargetLaserSystemRenderer());
  }
  
  private void initListener() {
    addListener(new Class[] { ClientWitherListener.class });
  }
  
  @NonNull
  private RenderLindwormWither getRender(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    return new RenderLindwormWither(new ResourceLocation("palawither", "models/entities/" + name + "/model.json"), new ResourceLocation("palawither", "models/entities/" + name + "/animation.json"), new ResourceLocation("palawither", "textures/entities/" + name + ".png"));
  }
  
  @NonNull
  private RenderLindwormWitherProjectile getProjectileRender(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    return new RenderLindwormWitherProjectile(new ResourceLocation("palawither", "models/entities/" + name + "/projectile.json"), null, new ResourceLocation("palawither", "textures/entities/" + name + ".png"));
  }
  
  @NonNull
  private ItemWitherHeadRenderer getHeadRender(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    return new ItemWitherHeadRenderer(new ResourceLocation("palawither", "models/items/head/" + name + "/model.json"), new ResourceLocation("palawither", "textures/items/head/" + name + "/texture.png"));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */