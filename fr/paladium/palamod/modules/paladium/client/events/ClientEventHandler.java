package fr.paladium.palamod.modules.paladium.client.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import fr.paladium.palamod.modules.addons.network.MessageP;
import fr.paladium.palamod.modules.paladium.client.ImagePositionData;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustrum;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public class ClientEventHandler {
  public static Map<String, String> events;
  
  public static List<ImagePositionData> images = Collections.synchronizedList(new ArrayList<>());
  
  private float partialTicks = 0.0F;
  
  @SubscribeEvent
  public void onRenderWorldLast(RenderWorldLastEvent event) {
    this.partialTicks = event.partialTicks;
  }
  
  @SubscribeEvent
  public void onRenderNameTag(RenderLivingEvent.Specials.Pre e) {
    if (!(e.entity instanceof net.minecraft.entity.player.EntityPlayer)) {
      Minecraft mc = Minecraft.func_71410_x();
      EntityLivingBase cameraEntity = mc.field_71451_h;
      Frustrum frustrum = new Frustrum();
      Vec3 renderingVector = cameraEntity.func_70666_h(this.partialTicks);
      double viewX = cameraEntity.field_70142_S + (cameraEntity.field_70165_t - cameraEntity.field_70142_S) * this.partialTicks;
      double viewY = cameraEntity.field_70137_T + (cameraEntity.field_70163_u - cameraEntity.field_70137_T) * this.partialTicks;
      double viewZ = cameraEntity.field_70136_U + (cameraEntity.field_70161_v - cameraEntity.field_70136_U) * this.partialTicks;
      frustrum.func_78547_a(viewX, viewY, viewZ);
      if (e.entity.func_145770_h(renderingVector.field_72450_a, renderingVector.field_72448_b, renderingVector.field_72449_c) && (e.entity.field_70158_ak || frustrum
        .func_78546_a(e.entity.field_70121_D)) && e.entity
        .func_70089_S() && 
        !e.entity.func_70685_l((Entity)cameraEntity) && e.entity instanceof EntityLiving && ((EntityLiving)e.entity)
        
        .func_94056_bM())
        e.setCanceled(true); 
    } 
  }
  
  @SubscribeEvent
  public void renderImageOnWorld(RenderWorldLastEvent event) {
    if (images.isEmpty())
      return; 
    float partialTicks = event.partialTicks;
    Minecraft minecraft = Minecraft.func_71410_x();
    EntityLivingBase entityLivingBase = minecraft.field_71451_h;
    assert entityLivingBase != null;
    double d0 = ((Entity)entityLivingBase).field_70142_S + (((Entity)entityLivingBase).field_70165_t - ((Entity)entityLivingBase).field_70142_S) * partialTicks;
    double d1 = ((Entity)entityLivingBase).field_70137_T + (((Entity)entityLivingBase).field_70163_u - ((Entity)entityLivingBase).field_70137_T) * partialTicks;
    double d2 = ((Entity)entityLivingBase).field_70136_U + (((Entity)entityLivingBase).field_70161_v - ((Entity)entityLivingBase).field_70136_U) * partialTicks;
    for (ImagePositionData image : images) {
      if (image.getTexture() == null)
        continue; 
      Vec3 pos1 = image.getPos1();
      Vec3 pos2 = image.getPos2();
      double distance = pos1.func_72438_d(Vec3.func_72443_a(((Entity)entityLivingBase).field_70165_t, ((Entity)entityLivingBase).field_70163_u, ((Entity)entityLivingBase).field_70161_v));
      if (distance <= (minecraft.field_71474_y.field_151451_c << 4) && distance >= -(minecraft.field_71474_y.field_151451_c << 4)) {
        double x1 = pos1.field_72450_a;
        double y1 = pos1.field_72448_b;
        double z1 = pos1.field_72449_c;
        double x2 = pos2.field_72450_a;
        double y2 = pos2.field_72448_b;
        double z2 = pos2.field_72449_c;
        if (y1 == y2) {
          renderImage(image.getTexture(), d0, d1, d2, new double[][] { { x1, y1, z2 }, { x2, y1, z2 }, { x2, y1, z1 }, { x1, y1, z1 } });
          continue;
        } 
        renderImage(image.getTexture(), d0, d1, d2, new double[][] { { x1, y2, z2 }, { x2, y2, z1 }, { x2, y1, z1 }, { x1, y1, z2 } });
      } 
    } 
  }
  
  private void renderImage(Resource texture, double d0, double d1, double d2, double[][] vertices) {
    texture.bind(() -> {
          GL11.glPushMatrix();
          GL11.glEnable(3042);
          GL11.glEnable(2832);
          GL14.glBlendEquation(32774);
          GL11.glBlendFunc(770, 771);
          Tessellator tessellator = Tessellator.field_78398_a;
          tessellator.func_78373_b(-d0, -d1, -d2);
          tessellator.func_78369_a(255.0F, 255.0F, 255.0F, 255.0F);
          GL11.glCullFace(1028);
          tessellator.func_78382_b();
          tessellator.func_78374_a(vertices[0][0], vertices[0][1], vertices[0][2], 1.0D, 1.0D);
          tessellator.func_78374_a(vertices[1][0], vertices[1][1], vertices[1][2], 0.0D, 1.0D);
          tessellator.func_78374_a(vertices[2][0], vertices[2][1], vertices[2][2], 0.0D, 0.0D);
          tessellator.func_78374_a(vertices[3][0], vertices[3][1], vertices[3][2], 1.0D, 0.0D);
          tessellator.func_78381_a();
          GL11.glCullFace(1029);
          tessellator.func_78382_b();
          tessellator.func_78374_a(vertices[0][0], vertices[0][1], vertices[0][2], 0.0D, 1.0D);
          tessellator.func_78374_a(vertices[1][0], vertices[1][1], vertices[1][2], 1.0D, 1.0D);
          tessellator.func_78374_a(vertices[2][0], vertices[2][1], vertices[2][2], 1.0D, 0.0D);
          tessellator.func_78374_a(vertices[3][0], vertices[3][1], vertices[3][2], 0.0D, 0.0D);
          tessellator.func_78381_a();
          tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
          GL11.glPopMatrix();
        });
  }
  
  @SubscribeEvent
  public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
    images.clear();
  }
  
  @SubscribeEvent
  public void getMessage(ClientChatReceivedEvent event) {
    if (event.message.func_150260_c().startsWith("paladium_images`")) {
      event.setCanceled(true);
      String message = event.message.func_150260_c().replace("paladium_images`", "");
      MessageP str = MessageP.fromString(message);
      if (str.getId() == 0)
        images.clear(); 
      if (str.getId() == 1) {
        Vec3 pos1 = Vec3.func_72443_a(Double.parseDouble(str.getValue("x1")), Double.parseDouble(str.getValue("y1")), Double.parseDouble(str.getValue("z1")));
        Vec3 pos2 = Vec3.func_72443_a(Double.parseDouble(str.getValue("x2")), Double.parseDouble(str.getValue("y2")), Double.parseDouble(str.getValue("z2")));
        String url = new String(Base64.getDecoder().decode(str.getValue("image")));
        if (url.indexOf("paladium-pvp.fr") == -1 && url.indexOf("paladium.dev") == -1 && url.indexOf("i.imgur.com") == -1)
          url = "https://i.imgur.com/zHhWPeb.png"; 
        images.add(new ImagePositionData(pos1, pos2, url));
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\events\ClientEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */