package fr.paladium.palamod.modules.paladium.utils;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class AbstractSteve extends AbstractClientPlayer {
  public AbstractSteve(World world, GameProfile profile) {
    super(world, profile);
  }
  
  public boolean func_70003_b(int i, String s) {
    return false;
  }
  
  public ChunkCoordinates func_82114_b() {
    return new ChunkCoordinates(0, 255, 0);
  }
  
  public ChunkCoordinates getCommandSenderPosition() {
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_70070_b(float par1) {
    return 15728880;
  }
  
  public boolean func_82150_aj() {
    return true;
  }
  
  public void func_145747_a(IChatComponent var1) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\utils\AbstractSteve.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */