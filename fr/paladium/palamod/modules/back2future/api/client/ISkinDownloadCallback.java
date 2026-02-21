package fr.paladium.palamod.modules.back2future.api.client;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public interface ISkinDownloadCallback extends SkinManager.SkinAvailableCallback {
  void func_152121_a(MinecraftProfileTexture.Type paramType, ResourceLocation paramResourceLocation);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\api\client\ISkinDownloadCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */