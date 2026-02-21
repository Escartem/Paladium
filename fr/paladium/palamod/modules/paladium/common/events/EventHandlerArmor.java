package fr.paladium.palamod.modules.paladium.common.events;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.hunter.items.ItemAmulet;
import fr.paladium.palamod.modules.hunter.utils.AmuletHelper;
import fr.paladium.palamod.modules.paladium.common.items.armors.ItemArmorTravel;
import fr.paladium.palamod.modules.paladium.network.data.PaladiumPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.Frustrum;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

public class EventHandlerArmor {
  private float partialTicks = 0.0F;
  
  @SubscribeEvent
  public void onLiving(LivingEvent.LivingUpdateEvent event) {
    if (event.entityLiving instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)event.entityLiving;
      if (player.func_82169_q(3) == null || !(player.func_82169_q(3).func_77973_b() instanceof ItemArmorTravel))
        player.field_70138_W = 0.5F; 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent(priority = EventPriority.NORMAL)
  public void onRender(RenderWorldLastEvent event) {
    this.partialTicks = event.partialTicks;
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent(priority = EventPriority.NORMAL)
  public void nameTag(RenderLivingEvent.Specials.Pre event) {
    EntityLivingBase entityLivingBase = event.entity;
    if (entityLivingBase instanceof EntityPlayer) {
      ItemStack helmet = ((EntityPlayer)entityLivingBase).field_71071_by.func_70440_f(3);
      if ((helmet != null && helmet.func_77973_b() instanceof ItemArmorTravel && ((ItemArmorTravel)helmet.func_77973_b()).getType() == 5) || AmuletHelper.hasAmulet((EntityPlayer)event.entity, ItemAmulet.Type.VOYANCE) || (helmet != null && helmet.func_77973_b() instanceof fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.ItemPerfectHelmet)) {
        PaladiumPlayer playerCosmetiques = PaladiumPlayer.get((Entity)(Minecraft.func_71410_x()).field_71439_g);
        if (!playerCosmetiques.isShowHoodHelmet()) {
          Minecraft mc = Minecraft.func_71410_x();
          EntityLivingBase cameraEntity = mc.field_71451_h;
          Vec3 renderingVector = cameraEntity.func_70666_h(this.partialTicks);
          Frustrum frustrum = new Frustrum();
          double viewX = cameraEntity.field_70142_S + (cameraEntity.field_70165_t - cameraEntity.field_70142_S) * this.partialTicks;
          double viewY = cameraEntity.field_70137_T + (cameraEntity.field_70163_u - cameraEntity.field_70137_T) * this.partialTicks;
          double viewZ = cameraEntity.field_70136_U + (cameraEntity.field_70161_v - cameraEntity.field_70136_U) * this.partialTicks;
          frustrum.func_78547_a(viewX, viewY, viewZ);
          if (entityLivingBase == mc.field_71439_g || !entityLivingBase.func_145770_h(renderingVector.field_72450_a, renderingVector.field_72448_b, renderingVector.field_72449_c) || (!((Entity)entityLivingBase).field_70158_ak && !frustrum.func_78546_a(((Entity)entityLivingBase).field_70121_D)) || !entityLivingBase.func_70089_S() || !entityLivingBase.func_70685_l((Entity)cameraEntity) || entityLivingBase.func_82150_aj() || entityLivingBase.func_98034_c((EntityPlayer)mc.field_71439_g))
            event.setCanceled(true); 
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\events\EventHandlerArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */