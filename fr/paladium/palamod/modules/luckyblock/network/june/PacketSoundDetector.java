package fr.paladium.palamod.modules.luckyblock.network.june;

import com.google.common.collect.HashBiMap;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.lang.reflect.Field;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;

public class PacketSoundDetector implements IMessage {
  public Double x;
  
  public Double y;
  
  public Double z;
  
  public PacketSoundDetector(Double x, Double y, Double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public PacketSoundDetector() {}
  
  public void fromBytes(ByteBuf buf) {
    if (buf.isReadable()) {
      this.x = Double.valueOf(buf.readDouble());
      this.y = Double.valueOf(buf.readDouble());
      this.z = Double.valueOf(buf.readDouble());
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    if (this.x != null) {
      buf.writeDouble(this.x.doubleValue());
      buf.writeDouble(this.y.doubleValue());
      buf.writeDouble(this.z.doubleValue());
    } 
  }
  
  public static class Handler implements IMessageHandler<PacketSoundDetector, IMessage> {
    public IMessage onMessage(PacketSoundDetector message, MessageContext ctx) {
      if (message.x != null)
        (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("Source de son la plus forte : " + message.x + " " + message.y + " " + message.z)); 
      try {
        ISound sound = getClosestSound();
        if (sound != null) {
          String soundStr = sound.func_147650_b().func_110623_a().replace(".", " ");
          (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("Source de son la plus proche : " + soundStr + " > " + sound.func_147649_g() + " " + sound.func_147654_h() + " " + sound.func_147651_i()));
        } 
      } catch (Exception exception) {}
      return null;
    }
    
    private ISound getClosestSound() throws Exception {
      Minecraft mc = Minecraft.func_71410_x();
      SoundHandler soundHandler = mc.func_147118_V();
      Field sndManagerField = SoundHandler.class.getDeclaredField("sndManager");
      sndManagerField.setAccessible(true);
      SoundManager soundManager = (SoundManager)sndManagerField.get(soundHandler);
      Field playingSoundsField = SoundManager.class.getDeclaredField("playingSounds");
      playingSoundsField.setAccessible(true);
      Vec3 playerPos = Vec3.func_72443_a(mc.field_71439_g.field_70165_t, mc.field_71439_g.field_70163_u + mc.field_71439_g.func_70047_e(), mc.field_71439_g.field_70161_v);
      float closestSoundDistance = Float.MAX_VALUE;
      ISound closestSound = null;
      for (Map.Entry<String, ISound> entry : (Iterable<Map.Entry<String, ISound>>)((HashBiMap)playingSoundsField.get(soundManager)).entrySet()) {
        ISound sound = entry.getValue();
        Vec3 soundPos = Vec3.func_72443_a(sound.func_147649_g(), sound.func_147654_h(), sound.func_147651_i());
        float soundDistance = (float)soundPos.func_72438_d(playerPos);
        if (soundDistance < closestSoundDistance) {
          closestSoundDistance = soundDistance;
          closestSound = sound;
        } 
      } 
      return closestSound;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\june\PacketSoundDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */