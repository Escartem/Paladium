package fr.paladium.palamod.modules.luckyblock.luckyevents.black;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.util.ItemStackSerializer;
import java.util.Base64;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;

public class Cosmetico extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.field_70170_p.func_72838_d((Entity)new EntityItem(player.field_70170_p, x, y, z, ItemStackSerializer.read(new String(Base64.getDecoder().decode("MTpjb21tb246aXRlbS5jb3NtZXRpY3M6MCN7ZGlzcGxheTp7TmFtZToiwqc2UEFMQVJBRSBDT0xPUlMiLH0sdXJsOiJodHRwczovL2kuaW1ndXIuY29tL0dORkk3RDYucG5nIix9")))));
  }
  
  public String getName() {
    return "Cosmetico";
  }
  
  public int getRarity() {
    return 5000;
  }
  
  public String getTexture() {
    return "cosmetico";
  }
  
  public boolean isBad() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\black\Cosmetico.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */