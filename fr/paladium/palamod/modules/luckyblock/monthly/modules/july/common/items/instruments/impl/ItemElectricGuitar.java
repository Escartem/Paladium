package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.instruments.impl;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.instruments.ItemInstrument;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects.SoundType;
import fr.paladium.palamod.modules.luckyblock.network.june.PacketPlayEmoteNoPvp;
import net.minecraft.entity.player.EntityPlayer;

public class ItemElectricGuitar extends ItemInstrument {
  public static final String NAME = "electric_guitar";
  
  public static final int MAX_SOUND_ID = 9;
  
  public ItemElectricGuitar() {
    super("electric_guitar", 9, SoundType.GUITAR);
  }
  
  public void playEmote(EntityPlayer player) {
    PalaMod.getNetwork().sendToAllAround((IMessage)new PacketPlayEmoteNoPvp("headbang"), new NetworkRegistry.TargetPoint(player.field_70170_p.field_73011_w.field_76574_g, player.field_70165_t, player.field_70163_u, player.field_70161_v, 24.0D));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\instruments\impl\ItemElectricGuitar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */