package fr.paladium.pet.client.renderer;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.paladium.pet.client.PetClientProxy;
import fr.paladium.pet.client.ui.utils.PetRenderUtils;
import fr.paladium.pet.common.entity.EntityPetCage;
import fr.paladium.pet.common.registry.impl.PetBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class PetCageRenderInventory implements ISimpleBlockRenderingHandler {
  public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
    if (block == PetBlockRegistry.PET_CAGE) {
      EntityPetCage cage = PetRenderUtils.getCageFromEnum();
      if (cage == null)
        return; 
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, -0.55F, 0.0F);
      RenderManager.field_78727_a.func_147940_a((Entity)cage, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
      GL11.glPopMatrix();
    } 
  }
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    return false;
  }
  
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }
  
  public int getRenderId() {
    return PetClientProxy.cageRenderId;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\client\renderer\PetCageRenderInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */