package fr.paladium.pet.common.network.packet.capture;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.pet.client.renderer.TileEntityPetCageRenderer;
import fr.paladium.pet.client.renderer.data.CageRenderData;
import fr.paladium.pet.client.renderer.data.CageRenderState;
import fr.paladium.pet.client.renderer.data.IntLocation;
import fr.paladium.pet.client.ui.utils.PetRenderUtils;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.tile.cage.TileEntityPetCage;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BBPacketRequestCageData extends ForgePacket {
  @PacketData
  private IntLocation location;
  
  @PacketData
  private String skinId;
  
  @PacketData
  private CageRenderState state;
  
  public BBPacketRequestCageData() {}
  
  public BBPacketRequestCageData(IntLocation location, String skinId, CageRenderState state) {
    this.location = location;
    this.skinId = skinId;
    this.state = state;
  }
  
  public BBPacketRequestCageData(IntLocation location) {
    this.location = location;
  }
  
  public void processClient() {
    if (TileEntityPetCageRenderer.DATAS.get(this.location) == null)
      return; 
    CageRenderData data = (CageRenderData)TileEntityPetCageRenderer.DATAS.get(this.location);
    if (data.getEntityPet() == null) {
      data.setEntityPet(PetRenderUtils.getPetFromEnum(this.skinId));
    } else {
      data.getEntityPet().setSkinId(this.skinId);
    } 
    data.setState(this.state);
  }
  
  public void processServer(EntityPlayerMP player) {
    if (this.location == null)
      return; 
    World world = player.field_70170_p;
    if (!world.func_72899_e(this.location.getX(), this.location.getY(), this.location.getZ()))
      return; 
    TileEntity tileEntity = world.func_147438_o(this.location.getX(), this.location.getY(), this.location.getZ());
    if (!(tileEntity instanceof TileEntityPetCage))
      return; 
    TileEntityPetCage cage = (TileEntityPetCage)tileEntity;
    PetCommonProxy.getInstance().getNetwork().sendTo((IMessage)new BBPacketRequestCageData(this.location, cage
          .getPet(), CageRenderState.of(cage.getStatus())), player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\capture\BBPacketRequestCageData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */