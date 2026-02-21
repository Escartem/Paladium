package fr.paladium.pet.client.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.CommonModule;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.entity.EntityDummyPet;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.pet.ElementPosition;
import fr.paladium.pet.common.pet.PetAdditionalData;
import java.util.HashMap;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class PetRenderListener {
  public static HashMap<String, EntityDummyPet> cachedRenderer = new HashMap<>();
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onRenderPlayerSpecialPre(RenderPlayerEvent.Specials.Pre event) {
    EntityPlayer player = event.entityPlayer;
    if ((CommonModule.getInstance().getCombatTag().inFight() && player != (Minecraft.func_71410_x()).field_71439_g) || player.func_70644_a(Potion.field_76441_p))
      return; 
    String username = player.func_70005_c_();
    PetPlayer eep = PetPlayer.get(player);
    if (eep.has() && eep.isVisible()) {
      if (!cachedRenderer.containsKey(username) || cachedRenderer.get(username) == null) {
        try {
          String skinId = eep.getCurrentSkin();
          EntityDummyPet pet = EntityDummyPet.class.getConstructor(new Class[] { World.class }).newInstance(new Object[] { (Minecraft.func_71410_x()).field_71441_e });
          pet.setSkinId(skinId);
          cachedRenderer.put(username, pet);
        } catch (Exception e) {
          e.printStackTrace();
        } 
      } else {
        EntityDummyPet pet = cachedRenderer.get(username);
        if (!Objects.equals(pet.getSkinId(), eep.getCurrentSkin()) && 
          !PetCommonProxy.APRIL_FOOL)
          cachedRenderer.put(username, null); 
      } 
    } else {
      cachedRenderer.put(username, null);
    } 
    if (cachedRenderer.containsKey(username)) {
      EntityDummyPet entity = cachedRenderer.get(username);
      if (entity == null || "none".equals(entity.getSkinId()))
        return; 
      PetAdditionalData data = PetCommonProxy.getInstance().findPet(entity.getSkinId());
      if (data == null)
        return; 
      ElementPosition scaleFactor = data.getScaleFactor();
      ElementPosition shoulderPosition = data.getShoulderPosition();
      double defaultX = shoulderPosition.getX();
      double defaultY = shoulderPosition.getY();
      double defaultZ = shoulderPosition.getZ();
      entity.field_70173_aa = player.field_70173_aa;
      entity.field_70177_z = 0.0F;
      GL11.glPushMatrix();
      GL11.glPushAttrib(1048575);
      GL11.glScaled(scaleFactor.getX(), scaleFactor.getY(), scaleFactor.getZ());
      GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
      GL11.glTranslated(defaultX, defaultY, defaultZ);
      RenderManager.field_78727_a.func_147940_a((Entity)entity, 4.0D, 1.0D, 0.0D, entity.field_70125_A, entity.field_70177_z + 58.5F);
      GL11.glPopAttrib();
      GL11.glPopMatrix();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\client\listener\PetRenderListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */