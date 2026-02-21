package fr.paladium.palashop.provider.cosmetic.factory.impl.spray.common.network;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palashop.PalaShopMod;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundCategory;

public class SCPacketSprayCosmeticExecute extends ForgePacket {
  @PacketData
  private int x;
  
  @PacketData
  private int y;
  
  @PacketData
  private int z;
  
  public SCPacketSprayCosmeticExecute() {}
  
  public SCPacketSprayCosmeticExecute(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void processClient() {
    SoundRecord.create(PalaShopMod.getInstance(), "sounds/cosmetic/spray/spray.ogg").category(SoundCategory.PLAYERS).position(this.x, this.y, this.z).attenuation(ISound.AttenuationType.LINEAR).play();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\spray\common\network\SCPacketSprayCosmeticExecute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */