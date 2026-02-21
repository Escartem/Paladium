package fr.paladium.pet.client.renderer;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.pet.client.renderer.data.CageRenderData;
import fr.paladium.pet.client.renderer.data.CageRenderState;
import fr.paladium.pet.client.renderer.data.IntLocation;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.entity.EntityDummyPet;
import fr.paladium.pet.common.entity.EntityPetCage;
import fr.paladium.pet.common.network.packet.capture.BBPacketRequestCageData;
import fr.paladium.pet.common.registry.impl.PetItemRegistry;
import fr.paladium.pet.common.tile.cage.TileEntityPetCage;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityPetCageRenderer extends TileEntitySpecialRenderer {
  public static final HashMap<IntLocation, CageRenderData> DATAS = new HashMap<>();
  
  public static final int CLEAR_CACHE_DISTANCE = 100;
  
  public void func_147500_a(TileEntity tile, double x, double y, double z, float ticks) {
    Minecraft mc = Minecraft.func_71410_x();
    EntityClientPlayerMP entityClientPlayerMP = mc.field_71439_g;
    if (!(tile instanceof TileEntityPetCage))
      return; 
    int metadata = tile.field_145847_g;
    TileEntityPetCage cage = (TileEntityPetCage)tile;
    IntLocation location = new IntLocation(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    CageRenderData data = get(cage, location);
    if (data == null)
      return; 
    CageRenderState state = data.getState();
    EntityDummyPet entityPet = data.getEntityPet();
    EntityPetCage entityCage = data.getEntityCage();
    EntityItem entityBait = data.getItemBait();
    entityPet.field_70173_aa = ((EntityPlayerSP)entityClientPlayerMP).field_70173_aa;
    entityCage.field_70173_aa = ((EntityPlayerSP)entityClientPlayerMP).field_70173_aa;
    entityBait.field_70173_aa = ((EntityPlayerSP)entityClientPlayerMP).field_70173_aa;
    if (data.requestUpdate(System.currentTimeMillis()))
      requestUpdate(cage, location); 
    renderCageInto((float)(x + 0.5D), (float)y, (float)(z + 0.5D), metadata, entityCage);
    if (state == CageRenderState.PET)
      renderPetInto((float)(x + 0.5D), (float)(y + 0.20000000298023224D), (float)(z + 0.5D), entityPet); 
    if (state == CageRenderState.FOOD)
      renderItemStackInto((float)(x + 0.5D), (float)(y + 0.20000000298023224D), (float)(z + 0.5D), entityBait); 
  }
  
  private CageRenderData get(TileEntityPetCage cage, IntLocation location) {
    if (!DATAS.containsKey(location)) {
      DATAS.put(location, CageRenderData.of(cage));
      requestUpdate(cage, location);
    } 
    return DATAS.get(location);
  }
  
  public void requestUpdate(TileEntityPetCage cage, IntLocation location) {
    BBPacketRequestCageData packet = new BBPacketRequestCageData(location);
    PetCommonProxy.getInstance().getNetwork().sendToServer((IMessage)packet);
  }
  
  private void renderItemStackInto(float x, float y, float z, EntityItem item) {
    item.func_92058_a(new ItemStack((Item)PetItemRegistry.BAIT));
    float scale = 1.0F;
    GL11.glPushMatrix();
    GL11.glTranslatef(x, y, z);
    GL11.glScalef(1.0F, 1.0F, 1.0F);
    GL11.glRotatef(-RenderManager.field_78727_a.field_78735_i, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    RenderManager.field_78727_a.func_147940_a((Entity)item, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
    GL11.glPopMatrix();
  }
  
  public void renderCageInto(float x, float y, float z, int metadata, EntityPetCage cage) {
    float scale = 0.85F;
    GL11.glPushMatrix();
    GL11.glScaled(0.8500000238418579D, 0.8500000238418579D, 0.8500000238418579D);
    GL11.glTranslated((x / 0.85F), (y / 0.85F), (z / 0.85F));
    GL11.glRotatef(getRotation(metadata), 0.0F, 1.0F, 0.0F);
    RenderManager.field_78727_a.func_147940_a((Entity)cage, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
    GL11.glPopMatrix();
  }
  
  private void renderPetInto(float x, float y, float z, EntityDummyPet pet) {
    float scale = 0.4F;
    GL11.glPushMatrix();
    GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
    GL11.glTranslated((x / 0.4F), (y / 0.4F), (z / 0.4F));
    GL11.glRotatef(-RenderManager.field_78727_a.field_78735_i, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    RenderManager.field_78727_a.func_147940_a((Entity)pet, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
    GL11.glPopMatrix();
  }
  
  public static float getRotation(int metadata) {
    switch (metadata) {
      case 2:
        return 180.0F;
      case 3:
        return 0.0F;
      case 4:
        return 270.0F;
      case 5:
        return 90.0F;
    } 
    return 180.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\client\renderer\TileEntityPetCageRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */