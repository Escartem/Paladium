package fr.paladium.palarpg.module.equipment.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.loader.cache.RPGItemsCache;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.client.event.TextureStitchEvent;

public class RPGItemTextureListener {
  @SubscribeEvent
  public void onTextureStitchPre(TextureStitchEvent.Pre event) {
    if (event.map.func_130086_a() != 1)
      return; 
    for (IRPGItem data : RPGItemsCache.getAllItems()) {
      if (data.getIcon() != null)
        event.map.setTextureEntry(data.getIcon().func_94215_i(), (TextureAtlasSprite)data.getIcon()); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\client\listener\RPGItemTextureListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */