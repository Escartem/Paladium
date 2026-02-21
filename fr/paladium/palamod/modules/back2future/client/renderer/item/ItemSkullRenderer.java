package fr.paladium.palamod.modules.back2future.client.renderer.item;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import fr.paladium.palamod.modules.back2future.client.renderer.tileentity.TileEntityFancySkullRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class ItemSkullRenderer implements IItemRenderer {
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return (type != IItemRenderer.ItemRenderType.FIRST_PERSON_MAP);
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object... data) {
    GameProfile profile = stack.func_77942_o() ? (profile = getGameProfile(stack)) : null;
    switch (type) {
      case ENTITY:
        renderSkull(-0.25F, -0.5F, -0.5F, stack.func_77960_j(), profile);
        break;
      case EQUIPPED:
        renderSkull(0.5F, 0.0F, 0.0F, stack.func_77960_j(), profile);
        break;
      case EQUIPPED_FIRST_PERSON:
        renderSkull(0.5F, 0.35F, 0.25F, stack.func_77960_j(), profile);
        break;
      case INVENTORY:
        OpenGLHelper.scale(1.5D, 1.5D, 1.5D);
        renderSkull(0.75F, 0.3F, 0.5F, stack.func_77960_j(), profile);
        break;
    } 
  }
  
  private void renderSkull(float x, float y, float z, int meta, GameProfile name) {
    OpenGLHelper.pushMatrix();
    OpenGLHelper.translate(x, y, z);
    TileEntityFancySkullRenderer.instance.renderSkull(0.0F, 0.0F, 0.0F, 0, 0.0F, meta, name);
    OpenGLHelper.popMatrix();
  }
  
  private GameProfile getGameProfile(ItemStack stack) {
    GameProfile profile = null;
    if (stack.func_77942_o()) {
      NBTTagCompound nbt = stack.func_77978_p();
      if (nbt.func_150297_b("SkullOwner", 10)) {
        profile = NBTUtil.func_152459_a(nbt.func_74775_l("SkullOwner"));
      } else if (nbt.func_150297_b("SkullOwner", 8)) {
        profile = new GameProfile(null, nbt.func_74779_i("SkullOwner"));
      } 
    } 
    return profile;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\item\ItemSkullRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */