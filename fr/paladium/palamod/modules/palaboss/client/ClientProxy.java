package fr.paladium.palamod.modules.palaboss.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palamod.modules.palaboss.client.renderer.RenderArachna;
import fr.paladium.palamod.modules.palaboss.client.renderer.RenderArachnaWeb;
import fr.paladium.palamod.modules.palaboss.client.renderer.RenderAzhur;
import fr.paladium.palamod.modules.palaboss.client.renderer.RenderBeepBoop;
import fr.paladium.palamod.modules.palaboss.client.renderer.RenderGear;
import fr.paladium.palamod.modules.palaboss.client.renderer.RenderIceProjectile;
import fr.paladium.palamod.modules.palaboss.client.renderer.RenderMagma;
import fr.paladium.palamod.modules.palaboss.client.renderer.RenderMorsula;
import fr.paladium.palamod.modules.palaboss.client.renderer.RenderSandstorm;
import fr.paladium.palamod.modules.palaboss.client.renderer.RenderTobalt;
import fr.paladium.palamod.modules.palaboss.client.renderer.RenderUlcan;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityArachna;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityAzhur;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBeepBoop;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityMorsula;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityTobalt;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityUlcan;
import fr.paladium.palamod.modules.palaboss.common.entity.projectiles.EntityArachnaWeb;
import fr.paladium.palamod.modules.palaboss.common.entity.projectiles.EntityGear;
import fr.paladium.palamod.modules.palaboss.common.entity.projectiles.EntityIceProjectile;
import fr.paladium.palamod.modules.palaboss.common.entity.projectiles.EntityMagma;
import fr.paladium.palamod.modules.palaboss.common.entity.projectiles.EntitySandstorm;
import fr.paladium.palamod.modules.palaboss.proxies.CommonProxy;
import net.minecraft.client.renderer.entity.Render;

public class ClientProxy extends CommonProxy {
  public static EntityBossBase boss;
  
  public void preInit(FMLPreInitializationEvent e) {
    super.preInit(e);
    RenderingRegistry.registerEntityRenderingHandler(EntityBeepBoop.class, (Render)new RenderBeepBoop());
    RenderingRegistry.registerEntityRenderingHandler(EntityTobalt.class, (Render)new RenderTobalt());
    RenderingRegistry.registerEntityRenderingHandler(EntityAzhur.class, (Render)new RenderAzhur());
    RenderingRegistry.registerEntityRenderingHandler(EntityUlcan.class, (Render)new RenderUlcan());
    RenderingRegistry.registerEntityRenderingHandler(EntityMorsula.class, (Render)new RenderMorsula());
    RenderingRegistry.registerEntityRenderingHandler(EntityArachna.class, (Render)new RenderArachna());
    RenderingRegistry.registerEntityRenderingHandler(EntityGear.class, (Render)new RenderGear());
    RenderingRegistry.registerEntityRenderingHandler(EntityMagma.class, (Render)new RenderMagma());
    RenderingRegistry.registerEntityRenderingHandler(EntitySandstorm.class, (Render)new RenderSandstorm());
    RenderingRegistry.registerEntityRenderingHandler(EntityArachnaWeb.class, (Render)new RenderArachnaWeb());
    RenderingRegistry.registerEntityRenderingHandler(EntityIceProjectile.class, (Render)new RenderIceProjectile());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */