package fr.paladium.palashop.common.utils.player;

import com.mojang.authlib.GameProfile;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import java.util.UUID;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class FakeEntityPlayerMP extends EntityOtherPlayerMP {
  private ResourceLocation customSkin;
  
  public FakeEntityPlayerMP(EntityPlayer player) {
    super(player.field_70170_p, new GameProfile(UUID.randomUUID(), player.func_70005_c_()));
    this.field_70173_aa = 41;
  }
  
  public FakeEntityPlayerMP(World world) {
    super(world, new GameProfile(UUID.randomUUID(), UUIDUtils.toString(UUID.randomUUID())));
    this.field_70173_aa = 41;
  }
  
  public FakeEntityPlayerMP(World world, UUID uuid, String name) {
    super(world, new GameProfile(uuid, name));
    this.field_70173_aa = 41;
  }
  
  public ResourceLocation func_110306_p() {
    if (this.customSkin != null)
      return this.customSkin; 
    return super.func_110306_p();
  }
  
  public void setCustomSkin(ResourceLocation customSkin) {
    this.customSkin = customSkin;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\commo\\utils\player\FakeEntityPlayerMP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */