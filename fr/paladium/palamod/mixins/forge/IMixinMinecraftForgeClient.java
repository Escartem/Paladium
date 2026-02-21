package fr.paladium.palamod.mixins.forge;

import com.google.common.collect.Maps;
import fr.paladium.palamod.modules.luckyblock.renders.RenderGadgeto;
import java.util.IdentityHashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({MinecraftForgeClient.class})
public class IMixinMinecraftForgeClient {
  @Shadow
  private static IdentityHashMap<Item, IItemRenderer> customItemRenderers;
  
  private static IdentityHashMap<Item, IItemRenderer> gadgetoRenderers = Maps.newIdentityHashMap();
  
  @Overwrite
  public static IItemRenderer getItemRenderer(ItemStack stack, IItemRenderer.ItemRenderType type) {
    if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("gadgeto") && stack.field_77990_d
      .func_74767_n("gadgeto")) {
      IItemRenderer renderer = gadgetoRenderers.get(stack.func_77973_b());
      if (renderer != null && renderer.handleRenderType(stack, type))
        return renderer; 
      if (renderer == null) {
        RenderGadgeto renderGadgeto = new RenderGadgeto();
        gadgetoRenderers.put(stack.func_77973_b(), renderGadgeto);
        return (IItemRenderer)renderGadgeto;
      } 
    } else {
      IItemRenderer renderer = customItemRenderers.get(stack.func_77973_b());
      if (renderer != null && renderer.handleRenderType(stack, type))
        return renderer; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\forge\IMixinMinecraftForgeClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */