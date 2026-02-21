package fr.paladium.palamod.modules.back2future.client.renderer.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
class TimedBannerTexture {
  public long time;
  
  public ResourceLocation texture;
  
  private TimedBannerTexture() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\tileentity\TileEntityBannerRenderer$TimedBannerTexture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */