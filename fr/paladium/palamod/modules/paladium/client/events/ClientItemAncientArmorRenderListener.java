package fr.paladium.palamod.modules.paladium.client.events;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palamixins.accessor.client.model.ModelRendererAccessor;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.ItemAncientArmor;
import fr.paladium.palamod.modules.paladium.common.items.armors.ancient.data.ItemAncientArmorPlayerData;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.file.AnimationFile;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.impl.dto.LindwormTransformProperty;
import software.bernie.geckolib3.model.impl.loader.LindwormLoader;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;

public class ClientItemAncientArmorRenderListener {
  private static final Map<Integer, Long> TELEPORTATION_EFFECT_PLAYERS = new HashMap<>();
  
  public static Map<Integer, Long> getTeleportationEffectPlayers() {
    return TELEPORTATION_EFFECT_PLAYERS;
  }
  
  private static final Map<Integer, ArmorRenderAnchor[]> ANCHOR_MAP = (Map)new HashMap<>();
  
  private static LindwormModel<LindwormAnimatable> model;
  
  static {
    ANCHOR_MAP.put(Integer.valueOf(0), new ArmorRenderAnchor[] { ArmorRenderAnchor.HEAD });
    ANCHOR_MAP.put(Integer.valueOf(1), new ArmorRenderAnchor[] { ArmorRenderAnchor.BODY, ArmorRenderAnchor.LEFT_ARM, ArmorRenderAnchor.RIGHT_ARM });
    ANCHOR_MAP.put(Integer.valueOf(2), new ArmorRenderAnchor[] { ArmorRenderAnchor.LEFT_LEG, ArmorRenderAnchor.RIGHT_LEG });
    ANCHOR_MAP.put(Integer.valueOf(3), new ArmorRenderAnchor[] { ArmorRenderAnchor.LEFT_FOOT, ArmorRenderAnchor.RIGHT_FOOT });
  }
  
  public static LindwormModel<LindwormAnimatable> getModel() {
    if (model == null) {
      GeoModel geoModel = LindwormLoader.loadModel(new ResourceLocation("palamod", "models/armor/ancient/model.json"));
      AnimationFile animationFile = LindwormLoader.loadAnimation(new ResourceLocation("palamod", "models/armor/ancient/animation.json"));
      Resource texture = Resource.of(new ResourceLocation("palamod", "models/armor/ancient/texture.png")).nearest();
      model = new LindwormModel(geoModel, animationFile, texture, (model, entity) -> new LindwormAnimatable(model, entity, 0.0F, new software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType[0]));
    } 
    return model;
  }
  
  @SubscribeEvent
  public void onRenderArmor(RenderPlayerEvent.SetArmorModel event) {
    if (event.stack != null && event.stack.func_77973_b() instanceof ItemAncientArmor) {
      event.result = -2;
      boolean isTexture2DEnabled = GL11.glIsEnabled(3553);
      if (!isTexture2DEnabled)
        GL11.glEnable(3553); 
      EntityPlayer player = event.entityPlayer;
      ItemAncientArmor armor = (ItemAncientArmor)event.stack.func_77973_b();
      LindwormModel<LindwormAnimatable> model = getModel();
      LindwormAnimatable animatable = (LindwormAnimatable)model.getOrCreateAnimatable((Entity)event.entityPlayer);
      if (ItemAncientArmor.isFull(event.entityPlayer) && (animatable.getCurrentAnimation() == null || "not_full".equals(animatable.getCurrentAnimation().getName()))) {
        animatable.forceAnimation("equip", false).setCallback(a -> a.forceAnimation("full", true));
        (Minecraft.func_71410_x()).field_71441_e.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "palamod:ancient_armor_equip", 1.0F, 1.0F, false);
      } else if (!ItemAncientArmor.isFull(event.entityPlayer) && animatable.getCurrentAnimation() != null && "full".equals(animatable.getCurrentAnimation().getName())) {
        animatable.forceAnimation("unequip", false).setCallback(a -> a.forceAnimation("not_full", true));
        (Minecraft.func_71410_x()).field_71441_e.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "palamod:ancient_armor_unequip", 1.0F, 1.0F, false);
      } else if (!ItemAncientArmor.isFull(event.entityPlayer) && animatable.getCurrentAnimation() == null) {
        animatable.forceAnimation("not_full", true);
      } 
      for (ArmorRenderAnchor anchor : (ArmorRenderAnchor[])ANCHOR_MAP.get(Integer.valueOf(armor.field_77881_a))) {
        GL11.glPushMatrix();
        patchArmorTranslations(event.entity, anchor, event.renderer, event.partialRenderTick);
        LindwormRenderer.renderModel(event.entity, model, new LindwormTransformProperty(), bone -> bone.name.equalsIgnoreCase(anchor.name().toLowerCase()));
        GL11.glPopMatrix();
      } 
      if (!isTexture2DEnabled)
        GL11.glDisable(3553); 
    } 
  }
  
  private static void patchArmorTranslations(Entity entity, ArmorRenderAnchor anchor, RenderPlayer playerRenderer, float partialTicks) {
    ModelRenderer modelRenderer = anchor.getModel(playerRenderer.field_77109_a);
    if (modelRenderer == null)
      return; 
    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    if (modelRenderer instanceof ModelRendererAccessor) {
      List<ModelRenderer> parents = new ArrayList<>();
      ModelRenderer m = modelRenderer;
      while (m instanceof ModelRendererAccessor) {
        ModelRenderer p = ((ModelRendererAccessor)m).getParent();
        if (p == null)
          break; 
        parents.add(0, p);
        m = p;
      } 
      for (ModelRenderer pr : parents) {
        GL11.glTranslatef(-pr.field_82906_o, -pr.field_82908_p, pr.field_82907_q);
        GL11.glTranslatef(-pr.field_78800_c / 16.0F, -pr.field_78797_d / 16.0F, pr.field_78798_e / 16.0F);
        GL11.glRotated(Math.toDegrees(pr.field_78808_h), 0.0D, 0.0D, 1.0D);
        GL11.glRotated(-Math.toDegrees(pr.field_78796_g), 0.0D, 1.0D, 0.0D);
        GL11.glRotated(-Math.toDegrees(pr.field_78795_f), 1.0D, 0.0D, 0.0D);
        GL11.glTranslatef(pr.field_78800_c / 16.0F, pr.field_78797_d / 16.0F, -pr.field_78798_e / 16.0F);
      } 
    } 
    GL11.glTranslatef(-modelRenderer.field_82906_o, -modelRenderer.field_82908_p, modelRenderer.field_82907_q);
    GL11.glTranslatef(-modelRenderer.field_78800_c / 16.0F, -modelRenderer.field_78797_d / 16.0F, modelRenderer.field_78798_e / 16.0F);
    GL11.glRotated(Math.toDegrees(modelRenderer.field_78808_h), 0.0D, 0.0D, 1.0D);
    GL11.glRotated(-Math.toDegrees(modelRenderer.field_78796_g), 0.0D, 1.0D, 0.0D);
    GL11.glRotated(-Math.toDegrees(modelRenderer.field_78795_f), 1.0D, 0.0D, 0.0D);
    GL11.glTranslatef(modelRenderer.field_78800_c / 16.0F, modelRenderer.field_78797_d / 16.0F, -modelRenderer.field_78798_e / 16.0F);
    GL11.glTranslatef(0.0F, -1.5F, 0.0F);
    if (entity.func_70093_af())
      GL11.glTranslatef(-anchor.sneakTranslationX / 16.0F, -anchor.sneakTranslationY / 16.0F, anchor.sneakTranslationZ / 16.0F); 
  }
  
  public enum ArmorRenderAnchor {
    NONE,
    HEAD(0.0F, 1.0F, 0.0F),
    BODY,
    LEFT_ARM,
    RIGHT_ARM,
    LEFT_LEG(0.0F, -3.0F, 3.9F),
    RIGHT_LEG(0.0F, -3.0F, 3.9F),
    LEFT_FOOT(0.0F, -3.0F, 3.9F),
    RIGHT_FOOT(0.0F, -3.0F, 3.9F);
    
    ArmorRenderAnchor(float sneakTranslationX, float sneakTranslationY, float sneakTranslationZ) {
      this.sneakTranslationX = sneakTranslationX;
      this.sneakTranslationY = sneakTranslationY;
      this.sneakTranslationZ = sneakTranslationZ;
    }
    
    private float sneakTranslationX;
    
    private float sneakTranslationY;
    
    private float sneakTranslationZ;
    
    public final ModelRenderer getModel(ModelBiped model) {
      switch (this) {
        case HEAD:
          return model.field_78116_c;
        case BODY:
          return model.field_78115_e;
        case LEFT_ARM:
          return model.field_78113_g;
        case RIGHT_ARM:
          return model.field_78112_f;
        case LEFT_LEG:
        case LEFT_FOOT:
          return model.field_78124_i;
        case RIGHT_LEG:
        case RIGHT_FOOT:
          return model.field_78123_h;
      } 
      return null;
    }
  }
  
  @SubscribeEvent
  public void onRenderInvisibleEntity(RenderLivingEvent.Pre event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    if (player == (Minecraft.func_71410_x()).field_71439_g || !ItemAncientArmorPlayerData.get(player).isInvisibilityActive())
      return; 
    event.setCanceled(true);
    event.entity.func_70066_B();
  }
  
  @SubscribeEvent
  public void onRenderInvisibleEntitySpecials(RenderLivingEvent.Specials event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    if (player == (Minecraft.func_71410_x()).field_71439_g || !ItemAncientArmorPlayerData.get(player).isInvisibilityActive())
      return; 
    event.setCanceled(true);
    event.entity.func_70066_B();
  }
  
  @SubscribeEvent
  public void onRenderInvisiblePlayer(RenderPlayerEvent.Pre event) {
    EntityPlayer player = (EntityPlayer)event.entity;
    if (player == (Minecraft.func_71410_x()).field_71439_g || !ItemAncientArmorPlayerData.get(player).isInvisibilityActive())
      return; 
    event.setCanceled(true);
    event.entity.func_70066_B();
  }
  
  @SubscribeEvent
  public void onRenderInvisiblePlayerSpecials(RenderPlayerEvent.Specials.Pre event) {
    EntityPlayer player = (EntityPlayer)event.entity;
    if (player == (Minecraft.func_71410_x()).field_71439_g || !ItemAncientArmorPlayerData.get(player).isInvisibilityActive())
      return; 
    event.setCanceled(true);
    event.entity.func_70066_B();
  }
  
  @SubscribeEvent
  public void onRenderInvisiblePlayerArmor(RenderPlayerEvent.Pre event) {
    EntityPlayer player = (EntityPlayer)event.entity;
    if (player == (Minecraft.func_71410_x()).field_71439_g || !ItemAncientArmorPlayerData.get(player).isInvisibilityActive())
      return; 
    event.setCanceled(true);
    event.entity.func_70066_B();
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onDeath(LivingDeathEvent event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    if (!ItemAncientArmor.hasEffect(player, LegendaryStone.Effect.INVISIBILITY) || ItemAncientArmorPlayerData.get(player).isInvisibilityOnCooldown())
      return; 
    event.setCanceled(true);
    player.func_70606_j(player.func_110138_aP());
  }
  
  @SubscribeEvent
  public void onTeleportationTick(TickEvent.ClientTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    long currentTime = System.currentTimeMillis();
    TELEPORTATION_EFFECT_PLAYERS.entrySet().removeIf(entry -> (currentTime > ((Long)entry.getValue()).longValue()));
  }
  
  @SubscribeEvent
  public void onRenderTeleportationPlayerPre(RenderPlayerEvent.Pre event) {
    if (!TELEPORTATION_EFFECT_PLAYERS.containsKey(Integer.valueOf(event.entityPlayer.func_145782_y())))
      return; 
    GL11.glPushMatrix();
    long startTime = ((Long)TELEPORTATION_EFFECT_PLAYERS.get(Integer.valueOf(event.entityPlayer.func_145782_y()))).longValue();
    long currentTime = System.currentTimeMillis();
    long effectDuration = startTime - currentTime;
    float progress = 1.0F - (float)effectDuration / 1000.0F;
    float blinkIntensity = (float)Math.sin(progress * Math.PI * 20.0D) * 1.0F;
    double offsetX = Math.sin(progress * Math.PI * 15.0D) * blinkIntensity * 0.5D;
    double offsetZ = Math.sin(progress * Math.PI * 18.0D) * blinkIntensity * 0.5D;
    GL11.glTranslated(offsetX, 0.0D, offsetZ);
    spawnTeleportationParticles(event.entityPlayer, progress);
  }
  
  @SubscribeEvent
  public void onRenderTeleportationPlayerPost(RenderPlayerEvent.Post event) {
    if (!TELEPORTATION_EFFECT_PLAYERS.containsKey(Integer.valueOf(event.entityPlayer.func_145782_y())))
      return; 
    GL11.glPopMatrix();
  }
  
  private void spawnTeleportationParticles(EntityPlayer player, float progress) {
    int particleCount = 3;
    for (int i = 0; i < 3; i++) {
      double angle = (System.currentTimeMillis() / 50.0D + i * Math.PI * 2.0D / 3.0D) % 6.283185307179586D;
      double radius = 0.5D + progress * 0.5D;
      double height = Math.random() * 2.0D;
      double x = player.field_70165_t + Math.cos(angle) * radius;
      double y = player.field_70163_u + height - 1.5D;
      double z = player.field_70161_v + Math.sin(angle) * radius;
      player.field_70170_p.func_72869_a("portal", x, y, z, (Math.random() - 0.5D) * 0.5D, (Math.random() - 0.5D) * 0.5D, (Math.random() - 0.5D) * 0.5D);
      if (Math.random() < 0.3D)
        player.field_70170_p.func_72869_a("enchantmenttable", x, y, z, 0.0D, 0.05D, 0.0D); 
    } 
    if (Math.random() < 0.5D) {
      double randomX = player.field_70165_t + (Math.random() - 0.5D) * 1.5D;
      double randomZ = player.field_70161_v + (Math.random() - 0.5D) * 1.5D;
      player.field_70170_p.func_72869_a("smoke", randomX, player.field_70163_u, randomZ, 0.0D, 0.1D, 0.0D);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\events\ClientItemAncientArmorRenderListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */