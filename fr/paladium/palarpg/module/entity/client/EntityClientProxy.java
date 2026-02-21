package fr.paladium.palarpg.module.entity.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palarpg.module.entity.client.loader.RPGEntityModelLoader;
import fr.paladium.palarpg.module.entity.client.renderer.RPGMobEntityRenderer;
import fr.paladium.palarpg.module.entity.client.renderer.RPGProjectileRenderer;
import fr.paladium.palarpg.module.entity.common.EntityCommonProxy;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.common.entity.projectile.RPGProjectile;
import net.minecraft.client.renderer.entity.Render;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimatableModel;
import software.bernie.geckolib3.core.controller.AnimationController;

public class EntityClientProxy extends EntityCommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    AnimationController.addModelFetcher(t -> (t instanceof RPGMobEntity) ? (IAnimatableModel)((RPGMobEntity)t).getLindwormModel() : null);
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    RenderingRegistry.registerEntityRenderingHandler(RPGMobEntity.class, (Render)new RPGMobEntityRenderer());
    RenderingRegistry.registerEntityRenderingHandler(RPGProjectile.class, (Render)new RPGProjectileRenderer());
    RPGEntityModelLoader.load(EntityCommonProxy.CONFIG_DIRECTORY);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\client\EntityClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */