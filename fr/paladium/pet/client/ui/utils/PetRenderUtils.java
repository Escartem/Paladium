package fr.paladium.pet.client.ui.utils;

import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.pet.common.entity.EntityDummyPet;
import fr.paladium.pet.common.entity.EntityPetCage;
import java.nio.FloatBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class PetRenderUtils {
  private static final FloatBuffer COLOR_BUFFER = GLAllocation.func_74529_h(16);
  
  public static EntityDummyPet getPetFromEnum(String skin) {
    try {
      EntityDummyPet pet = EntityDummyPet.class.getConstructor(new Class[] { World.class }).newInstance(new Object[] { (Minecraft.func_71410_x()).field_71441_e });
      pet.setSkinId(skin);
      return pet;
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public static EntityPetCage getCageFromEnum() {
    try {
      return EntityPetCage.class.getConstructor(new Class[] { World.class }).newInstance(new Object[] { (Minecraft.func_71410_x()).field_71441_e });
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public static void drawPetOnUI(float x, float y, float scale, float mouseX, float mouseY, EntityDummyPet entity, float rotate) {
    if (entity == null)
      return; 
    Render render = RenderManager.field_78727_a.func_78713_a((Entity)entity);
    if (!(render instanceof fr.paladium.pet.client.renderer.PetGeoRenderer))
      return; 
    GuiUtils.renderEntity(x, y, scale, mouseX, mouseY, (EntityLivingBase)entity);
  }
  
  private static FloatBuffer setColorBuffer(float red, float green, float blue, float alpha) {
    COLOR_BUFFER.clear();
    COLOR_BUFFER.put(red).put(green).put(blue).put(alpha);
    COLOR_BUFFER.flip();
    return COLOR_BUFFER;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\u\\utils\PetRenderUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */