package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.instruments.impl;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.instruments.ItemInstrument;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects.SoundType;
import net.minecraft.entity.player.EntityPlayer;

public class ItemAccordion extends ItemInstrument {
  public static final String NAME = "accordion";
  
  public static final int MAX_SOUND_ID = 6;
  
  public ItemAccordion() {
    super("accordion", 6, SoundType.ACCORDION);
  }
  
  public void playEmote(EntityPlayer player) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\instruments\impl\ItemAccordion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */