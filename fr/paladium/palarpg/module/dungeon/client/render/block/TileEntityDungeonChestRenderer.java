package fr.paladium.palarpg.module.dungeon.client.render.block;

import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonChest;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.model.impl.loader.LindwormLoader;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;
import software.bernie.geckolib3.model.impl.model.animation.impl.IdleLindwormAnimationType;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;

public class TileEntityDungeonChestRenderer extends TileEntitySpecialRenderer implements IItemRenderer {
  public static final int RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
  
  private static final Map<TileEntityDungeonChest.DungeonChestRarity, Resource> RARITY_TEXTURES = new HashMap<>();
  
  private final LindwormModel<LindwormAnimatable> model;
  
  public TileEntityDungeonChestRenderer() {
    if (RARITY_TEXTURES.isEmpty())
      for (TileEntityDungeonChest.DungeonChestRarity rarity : TileEntityDungeonChest.DungeonChestRarity.values())
        RARITY_TEXTURES.put(rarity, Resource.of(new ResourceLocation("palarpg", "textures/blocks/dungeon/chest/" + rarity.name().toLowerCase() + ".png")).nearest());  
    this.model = new LindwormModel(LindwormLoader.loadModel(new ResourceLocation("palarpg", "models/blocks/dungeon/chest/model.json")), LindwormLoader.loadAnimation(new ResourceLocation("palarpg", "models/blocks/dungeon/chest/animation.json")), (model, entity) -> (new LindwormAnimatable(model, entity)).addAnimationType(new LindwormAnimationType[] { (LindwormAnimationType)new IdleLindwormAnimationType() }));
  }
  
  public void func_147496_a(World world) {}
  
  public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float ticks) {
    if (!(tileEntity instanceof TileEntityDungeonChest))
      return; 
    TileEntityDungeonChest dungeonChest = (TileEntityDungeonChest)tileEntity;
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y, z + 0.5D);
    GL11.glRotated((-dungeonChest.getRotation() + 180.0F), 0.0D, 1.0D, 0.0D);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(3042);
    GL11.glDisable(2884);
    if (Minecraft.func_71379_u()) {
      GL11.glShadeModel(7425);
    } else {
      GL11.glShadeModel(7424);
    } 
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    Optional<RPGDungeonPlayerData.RPGDungeonItem[]> optionalRewards = dungeonChest.getRewards(UUIDUtils.toString((Entity)entityClientPlayerMP));
    LindwormAnimatable animatable = (LindwormAnimatable)this.model.getOrCreateAnimatable((Entity)entityClientPlayerMP);
    if (!dungeonChest.isFinished()) {
      animatable.forceAnimation("locked", true);
    } else if (animatable.getCurrentAnimation() == null || "locked".equals(animatable.getCurrentAnimation().getName())) {
      animatable.forceAnimation("open", false);
      animatable.getCurrentAnimation().setCallback(a -> animatable.forceAnimation("open_idle", true));
    } else if (!optionalRewards.isPresent() && animatable.getCurrentAnimation() != null && "open_idle".equals(animatable.getCurrentAnimation().getName())) {
      animatable.forceAnimation("close", false);
      animatable.getCurrentAnimation().setCallback(a -> animatable.forceAnimation("idle", true));
    } 
    getTexture(dungeonChest.getRarity()).bind(() -> LindwormRenderer.renderModel(entity, this.model));
    GL11.glTranslated(-(x + 0.5D), -y, -(z + 0.5D));
    GL11.glPopMatrix();
  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    if (type == IItemRenderer.ItemRenderType.INVENTORY) {
      GL11.glScaled(0.75D, 0.75D, 0.75D);
      GL11.glTranslated(0.5D, -0.2D, 0.5D);
      getTexture(TileEntityDungeonChest.DungeonChestRarity.COMMON).bind(() -> LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, this.model));
      GL11.glTranslated(-0.5D, 0.2D, -0.5D);
    } else if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON || type == IItemRenderer.ItemRenderType.EQUIPPED) {
      GL11.glScaled(0.75D, 0.75D, 0.75D);
      GL11.glTranslated(0.75D, 0.25D, 0.75D);
      getTexture(TileEntityDungeonChest.DungeonChestRarity.COMMON).bind(() -> LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, this.model));
      GL11.glTranslated(-0.75D, -0.25D, -0.75D);
    } else {
      getTexture(TileEntityDungeonChest.DungeonChestRarity.COMMON).bind(() -> LindwormRenderer.renderModel((Entity)(Minecraft.func_71410_x()).field_71439_g, this.model));
    } 
  }
  
  @NonNull
  private static Resource getTexture(@NonNull TileEntityDungeonChest.DungeonChestRarity rarity) {
    if (rarity == null)
      throw new NullPointerException("rarity is marked non-null but is null"); 
    return RARITY_TEXTURES.get(rarity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\client\render\block\TileEntityDungeonChestRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */