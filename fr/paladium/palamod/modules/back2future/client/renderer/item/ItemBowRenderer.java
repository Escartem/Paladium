package fr.paladium.palamod.modules.back2future.client.renderer.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class ItemBowRenderer implements IItemRenderer {
  private final RenderItem renderItem = new RenderItem();
  
  public boolean handleRenderType(ItemStack stack, IItemRenderer.ItemRenderType type) {
    ItemStack usingItem = (Minecraft.func_71410_x()).field_71439_g.func_71011_bu();
    return (type == IItemRenderer.ItemRenderType.INVENTORY && usingItem != null && usingItem == stack);
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack stack, IItemRenderer.ItemRendererHelper helper) {
    return false;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object... data) {
    TextureAtlasSprite textureAtlasSprite;
    TextureManager textureManager = Minecraft.func_71410_x().func_110434_K();
    ResourceLocation resource = textureManager.func_130087_a(stack.func_94608_d());
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    ItemStack usingItem = entityClientPlayerMP.func_71011_bu();
    int useRemaining = entityClientPlayerMP.func_71052_bv();
    ItemBow bow = (ItemBow)stack.func_77973_b();
    IIcon icon = bow.getIcon(stack, 0);
    if (usingItem != null && usingItem == stack) {
      int charge = stack.func_77988_m() - useRemaining;
      if (charge >= 18) {
        icon = bow.func_94599_c(2);
      } else if (charge > 13) {
        icon = bow.func_94599_c(1);
      } else if (charge > 0) {
        icon = bow.func_94599_c(0);
      } 
    } 
    if (icon == null)
      textureAtlasSprite = ((TextureMap)textureManager.func_110581_b(resource)).func_110572_b("missingno"); 
    OpenGLHelper.pushMatrix();
    textureManager.func_110577_a(resource);
    OpenGLHelper.colour(bow.func_82790_a(stack, 0));
    OpenGLHelper.disableLighting();
    OpenGLHelper.enableAlpha();
    OpenGLHelper.enableBlend();
    OpenGlHelper.func_148821_a(770, 771, 1, 0);
    this.renderItem.func_94149_a(0, 0, (IIcon)textureAtlasSprite, 16, 16);
    OpenGLHelper.enableLighting();
    OpenGLHelper.disableAlpha();
    OpenGLHelper.disableBlend();
    if (stack.hasEffect(0))
      this.renderItem.renderEffect(textureManager, 0, 0); 
    OpenGLHelper.popMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\item\ItemBowRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */