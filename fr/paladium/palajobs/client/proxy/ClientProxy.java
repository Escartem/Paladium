package fr.paladium.palajobs.client.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.helios.Helios;
import fr.paladium.lib.aurelienribon.tweenengine.Tween;
import fr.paladium.lib.aurelienribon.tweenengine.TweenAccessor;
import fr.paladium.palajobs.client.helios.ModuleJobs;
import fr.paladium.palajobs.client.model.boss.ModelJobAlchimistBoss;
import fr.paladium.palajobs.client.model.boss.ModelJobFarmerBoss;
import fr.paladium.palajobs.client.model.boss.ModelJobHunterBoss;
import fr.paladium.palajobs.client.model.boss.ModelJobMinerBoss;
import fr.paladium.palajobs.client.register.KeysRegister;
import fr.paladium.palajobs.client.render.RenderBambooBoat;
import fr.paladium.palajobs.client.render.RenderCustomFish;
import fr.paladium.palajobs.client.render.boss.RenderJobBoss;
import fr.paladium.palajobs.client.render.boss.RenderJobFarmerPlantBoss;
import fr.paladium.palajobs.client.render.boss.RenderJobMinerDigBoss;
import fr.paladium.palajobs.client.ui.lvltokens.accessors.HiddenRewardAccessor;
import fr.paladium.palajobs.client.ui.lvltokens.accessors.RewardButtonAccessor;
import fr.paladium.palajobs.client.ui.lvltokens.nodes.ButtonJobRewardHiddenReward;
import fr.paladium.palajobs.client.ui.lvltokens.nodes.ButtonJobRewardTokenSelector;
import fr.paladium.palajobs.core.CommonProxy;
import fr.paladium.palajobs.core.entity.EntityBambooBoat;
import fr.paladium.palajobs.core.entity.EntityCustomFishHook;
import fr.paladium.palajobs.core.entity.boss.EntityJobAlchimistBoss;
import fr.paladium.palajobs.core.entity.boss.EntityJobFarmerBoss;
import fr.paladium.palajobs.core.entity.boss.EntityJobFarmerPlantBoss;
import fr.paladium.palajobs.core.entity.boss.EntityJobHunterBoss;
import fr.paladium.palajobs.core.entity.boss.EntityJobMinerBoss;
import fr.paladium.palajobs.core.entity.boss.EntityJobMinerDigBoss;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ClientProxy extends CommonProxy {
  public ItemStack moneyIcon;
  
  public ItemStack bossItem;
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    FMLCommonHandler.instance().bus().register(new ClientDebugHandler());
    MinecraftForge.EVENT_BUS.register(new ClientDebugHandler());
    ClientTooltipHandler tooltipHandler = new ClientTooltipHandler();
    FMLCommonHandler.instance().bus().register(tooltipHandler);
    MinecraftForge.EVENT_BUS.register(tooltipHandler);
    RenderingRegistry.registerEntityRenderingHandler(EntityJobMinerBoss.class, (Render)new RenderJobBoss(RenderManager.field_78727_a, (AnimatedGeoModel)new ModelJobMinerBoss()));
    RenderingRegistry.registerEntityRenderingHandler(EntityJobHunterBoss.class, (Render)new RenderJobBoss(RenderManager.field_78727_a, (AnimatedGeoModel)new ModelJobHunterBoss()));
    RenderingRegistry.registerEntityRenderingHandler(EntityJobFarmerBoss.class, (Render)new RenderJobBoss(RenderManager.field_78727_a, (AnimatedGeoModel)new ModelJobFarmerBoss()));
    RenderingRegistry.registerEntityRenderingHandler(EntityJobAlchimistBoss.class, (Render)new RenderJobBoss(RenderManager.field_78727_a, (AnimatedGeoModel)new ModelJobAlchimistBoss()));
    RenderingRegistry.registerEntityRenderingHandler(EntityJobMinerDigBoss.class, (Render)new RenderJobMinerDigBoss(RenderManager.field_78727_a));
    RenderingRegistry.registerEntityRenderingHandler(EntityJobFarmerPlantBoss.class, (Render)new RenderJobFarmerPlantBoss(RenderManager.field_78727_a));
    Tween.registerAccessor(ButtonJobRewardHiddenReward.class, (TweenAccessor)new HiddenRewardAccessor());
    Tween.registerAccessor(ButtonJobRewardTokenSelector.class, (TweenAccessor)new RewardButtonAccessor());
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    RenderingRegistry.registerEntityRenderingHandler(EntityBambooBoat.class, (Render)new RenderBambooBoat());
    RenderingRegistry.registerEntityRenderingHandler(EntityCustomFishHook.class, (Render)new RenderCustomFish());
    KeysRegister.init();
    Helios.getClient().addModule(ModuleJobs.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\proxy\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */