package fr.paladium.pet.common.network.packet.capture;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.pet.common.block.BlockPetCage;
import fr.paladium.pet.common.capture.CaptureManager;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class CSPacketTrapInteraction extends ForgePacket {
  @PacketData
  int score;
  
  @PacketData
  int x;
  
  @PacketData
  int y;
  
  @PacketData
  int z;
  
  public CSPacketTrapInteraction() {}
  
  public CSPacketTrapInteraction(int score, int x, int y, int z) {
    this.score = score;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void processServer(EntityPlayerMP player) {
    World world = player.field_70170_p;
    if (!CaptureManager.getInstance().isValidScore(this.score))
      return; 
    if (!world.func_72899_e(this.x, this.y, this.z))
      return; 
    Block block = world.func_147439_a(this.x, this.y, this.z);
    if (!(block instanceof BlockPetCage))
      return; 
    BlockPetCage cage = (BlockPetCage)block;
    cage.handlePacket(player, this.score, world, this.x, this.y, this.z);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\capture\CSPacketTrapInteraction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */