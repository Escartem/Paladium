package fr.paladium.palavoicechat.client.voip.audio;

import fr.paladium.palavoicechat.PalaVoiceChatMod;
import fr.paladium.palavoicechat.client.config.VoIPClientConfig;
import fr.paladium.palavoicechat.client.voip.client.IoNettyVoIPClient;
import fr.paladium.palavoicechat.client.voip.util.DataLines;
import fr.paladium.palavoicechat.common.voip.packet.impl.SoundPacket;
import fr.paladium.palavoicechat.utils.Utils;
import fr.paladium.palavoicechat.utils.WorldUtils;
import java.util.ArrayList;
import java.util.UUID;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Pair;

public class AudioChannel extends Thread {
  private final UUID uuid;
  
  private final ArrayList<SoundPacket> queue;
  
  private long lastPacketTime;
  
  private SourceDataLine speaker;
  
  private FloatControl gainControl;
  
  private boolean stopped;
  
  public AudioChannel(UUID uuid) {
    this.uuid = uuid;
    this.queue = new ArrayList<>();
    this.lastPacketTime = System.currentTimeMillis();
    this.stopped = false;
    setDaemon(true);
    setName("AudioChannelThread-" + uuid.toString());
    System.out.println("Creating audio channel for " + uuid);
  }
  
  public boolean canKill() {
    return (System.currentTimeMillis() - this.lastPacketTime > 30000L);
  }
  
  public void closeAndKill() {
    System.out.println("Closing audio channel for " + this.uuid);
    if (this.speaker != null)
      this.speaker.close(); 
    this.stopped = true;
  }
  
  public UUID getUUID() {
    return this.uuid;
  }
  
  public void addToQueue(SoundPacket m) {
    this.queue.add(m);
  }
  
  public void run() {
    try {
      AudioFormat af = AudioChannelConfig.getStereoFormat();
      this.speaker = DataLines.getSpeaker();
      this.speaker.open(af);
      this.gainControl = (FloatControl)this.speaker.getControl(FloatControl.Type.MASTER_GAIN);
      while (!this.stopped) {
        if (this.queue.isEmpty()) {
          if (this.speaker.getBufferSize() - this.speaker.available() <= 0 && this.speaker.isActive())
            this.speaker.stop(); 
          try {
            Thread.sleep(10L);
          } catch (InterruptedException interruptedException) {}
          continue;
        } 
        this.lastPacketTime = System.currentTimeMillis();
        SoundPacket soundPacket = this.queue.get(0);
        this.queue.remove(soundPacket);
        if (this.speaker.getBufferSize() - this.speaker.available() <= 0) {
          int outputBufferSize = PalaVoiceChatMod.getClientProxy().getClientConfig().getOutputBufferSize();
          byte[] data = new byte[Math.min(AudioChannelConfig.getDataLength() * 2 * outputBufferSize, this.speaker.getBufferSize() - AudioChannelConfig.getDataLength())];
          this.speaker.write(data, 0, data.length);
        } 
        EntityPlayer player = WorldUtils.getPlayerByUUID((World)(Minecraft.func_71410_x()).field_71441_e, soundPacket.getSender());
        if (player != null) {
          VoIPClientConfig.PlayerVolumeSettings pVolume = PalaVoiceChatMod.getClientProxy().getClientConfig().getVolume(player);
          if (pVolume.isMuted())
            continue; 
          byte[] mono = soundPacket.getData();
          if (!player.func_110124_au().equals((Minecraft.func_71410_x()).field_71439_g.func_110124_au())) {
            float amplitude = Utils.calculateAmplitude(mono);
            IoNettyVoIPClient.getInstance().getTalkCache().updateTalkingWithAmplitude(player.func_110124_au(), amplitude);
          } 
          float distance = player.func_70032_d((Entity)(Minecraft.func_71410_x()).field_71439_g);
          float percentage = 1.0F;
          float fadeDistance = 24.0F;
          float maxDistance = 32.0F;
          if (distance > 24.0F)
            percentage = 1.0F - Math.min((distance - 24.0F) / 8.0F, 1.0F); 
          float voiceChatVolume = (float)PalaVoiceChatMod.getClientProxy().getClientConfig().getVoiceChatVolume();
          float playerVolume = (float)pVolume.getVolume();
          this.gainControl.setValue(Math.min(Math.max(Utils.percentageToDB(percentage * voiceChatVolume * playerVolume), this.gainControl.getMinimum()), this.gainControl.getMaximum()));
          Pair<Float, Float> stereoVolume = player.func_110124_au().equals((Minecraft.func_71410_x()).field_71439_g.func_110124_au()) ? Pair.of(Float.valueOf(1.0F), Float.valueOf(1.0F)) : Utils.getStereoVolume(Utils.getPositionVec((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g), (Minecraft.func_71410_x()).field_71439_g.field_70177_z, Utils.getPositionVec(player));
          byte[] stereo = Utils.convertToStereo(mono, ((Float)stereoVolume.getLeft()).floatValue(), ((Float)stereoVolume.getRight()).floatValue());
          this.speaker.write(stereo, 0, stereo.length);
          this.speaker.start();
        } 
      } 
    } catch (Throwable e) {
      e.printStackTrace();
      if (this.speaker != null) {
        this.speaker.stop();
        this.speaker.flush();
        this.speaker.close();
      } 
    } 
  }
  
  public boolean isClosed() {
    return this.stopped;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\client\voip\audio\AudioChannel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */