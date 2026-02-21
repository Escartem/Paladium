package fr.paladium.palamod.modules.paladium.client.render;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.client.FMLClientHandler;
import fr.paladium.palamod.modules.paladium.client.model.ModelStatue;
import fr.paladium.palamod.modules.paladium.utils.AbstractSteve;
import fr.paladium.palamod.util.FastUUID;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemStatueRenderer implements IItemRenderer {
  private ModelStatue model = new ModelStatue();
  
  public static final ResourceLocation QUARTZ_BLOCK = new ResourceLocation("textures/blocks/quartz_block_bottom.png");
  
  private Map<String, AbstractClientPlayer> skinList = new HashMap<>();
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
    int metaTest = item.func_77960_j();
    switch (type) {
      case ENTITY:
        renderStatue(0.0F, -0.52F, 0.0F, metaTest, item);
        break;
      case EQUIPPED:
        renderStatue(0.2F, 0.0F, 0.8F, metaTest, item);
        break;
      case EQUIPPED_FIRST_PERSON:
        renderStatue(0.5F, 0.9F, 0.6F, metaTest, item);
        break;
      case INVENTORY:
        renderStatue(1.0F, 0.3F, 1.0F, metaTest, item);
        break;
    } 
  }
  
  public void renderStatue(float x, float y, float z, int metaTest, ItemStack item) {
    GL11.glPushMatrix();
    GL11.glTranslatef(x, y + 0.05F, z);
    GL11.glScalef(0.007F, 0.007F, 0.007F);
    String uuid = null;
    if (item.func_77978_p() != null)
      uuid = item.func_77978_p().func_74779_i("playerUUID"); 
    AbstractSteve steve = null;
    if (uuid != null && !uuid.isEmpty() && !this.skinList.containsKey(uuid)) {
      GameProfile profile = new GameProfile(FastUUID.parseUUID(uuid), "Steve");
      Minecraft.func_71410_x().func_152347_ac().fillProfileProperties(profile, true);
      steve = new AbstractSteve((World)(Minecraft.func_71410_x()).field_71441_e, profile);
      this.skinList.put(uuid, steve);
    } else if (this.skinList.containsKey(uuid)) {
      steve = (AbstractSteve)this.skinList.get(uuid);
    } 
    (FMLClientHandler.instance().getClient()).field_71446_o.func_110577_a(QUARTZ_BLOCK);
    this.model.renderSocle();
    (FMLClientHandler.instance().getClient()).field_71446_o.func_110577_a((steve == null) ? AbstractClientPlayer.field_110314_b : steve
        .func_110306_p());
    this.model.renderSkin();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\render\ItemStatueRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */