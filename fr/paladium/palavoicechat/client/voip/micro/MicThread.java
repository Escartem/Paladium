package fr.paladium.palavoicechat.client.voip.micro;

import fr.paladium.palavoicechat.PalaVoiceChatMod;
import fr.paladium.palavoicechat.client.listener.KeyBindHandlerListener;
import fr.paladium.palavoicechat.client.ui.UIVoiceConfig;
import fr.paladium.palavoicechat.client.voip.audio.AudioChannel;
import fr.paladium.palavoicechat.client.voip.audio.AudioChannelConfig;
import fr.paladium.palavoicechat.client.voip.client.IoNettyVoIPClient;
import fr.paladium.palavoicechat.client.voip.util.DataLines;
import fr.paladium.palavoicechat.common.voip.packet.Packet;
import fr.paladium.palavoicechat.common.voip.packet.impl.MicrophonePacket;
import fr.paladium.palavoicechat.common.voip.packet.impl.SoundPacket;
import fr.paladium.palavoicechat.utils.Utils;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class MicThread extends Thread {
  private final TargetDataLine mic;
  
  private boolean running;
  
  private boolean microphoneLocked;
  
  private boolean activating;
  
  private int deactivationDelay;
  
  private byte[] lastBuff;
  
  private boolean wasPTT;
  
  public MicThread() throws LineUnavailableException {
    this.running = true;
    setDaemon(true);
    setName("MicrophoneThread");
    AudioFormat af = AudioChannelConfig.getMonoFormat();
    this.mic = DataLines.getMicrophone();
    this.mic.open(af);
  }
  
  public void run() {
    while (this.running) {
      if (this.microphoneLocked) {
        try {
          Thread.sleep(10L);
        } catch (InterruptedException interruptedException) {}
        continue;
      } 
      if (PalaVoiceChatMod.getClientProxy().getClientConfig().isVoiceChatEnabled()) {
        MicrophoneActivationType type = PalaVoiceChatMod.getClientProxy().getClientConfig().getMicActivationType();
        if (MicrophoneActivationType.PTT.equals(type)) {
          ptt();
          continue;
        } 
        if (MicrophoneActivationType.VOICE.equals(type))
          voice(); 
      } 
    } 
  }
  
  private void voice() {
    this.wasPTT = false;
    if (IoNettyVoIPClient.getInstance().isMuted()) {
      this.activating = false;
      if (this.mic.isActive()) {
        this.mic.stop();
        this.mic.flush();
      } 
      return;
    } 
    int dataLength = AudioChannelConfig.getDataLength();
    this.mic.start();
    if (this.mic.available() < dataLength) {
      try {
        Thread.sleep(10L);
      } catch (InterruptedException interruptedException) {}
      return;
    } 
    byte[] buff = new byte[dataLength];
    while (this.mic.available() >= dataLength)
      this.mic.read(buff, 0, buff.length); 
    float microphoneAmplification = (float)PalaVoiceChatMod.getClientProxy().getClientConfig().getMicrophoneAmplification();
    Utils.adjustVolumeMono(buff, microphoneAmplification);
    double voiceActivationThreshold = PalaVoiceChatMod.getClientProxy().getClientConfig().getVoiceActivationThreshold();
    int offset = Utils.getActivationOffset(buff, voiceActivationThreshold);
    if (this.activating) {
      if (offset < 0) {
        if (this.deactivationDelay >= 2) {
          this.activating = false;
          this.deactivationDelay = 0;
        } else {
          sendAudioPacket(buff);
          this.deactivationDelay++;
        } 
      } else {
        sendAudioPacket(buff);
      } 
    } else if (offset >= 0) {
      if (this.lastBuff != null) {
        int lastPacketOffset = buff.length - offset;
        sendAudioPacket(Arrays.copyOfRange(this.lastBuff, lastPacketOffset, this.lastBuff.length));
      } 
      sendAudioPacket(buff);
      this.activating = true;
    } 
    if (ZUI.isOpen(UIVoiceConfig.class)) {
      float amplitude = Utils.calculateAmplitude(buff);
      IoNettyVoIPClient.getInstance().getTalkCache().updateTalkingWithAmplitude((Minecraft.func_71410_x()).field_71439_g.func_110124_au(), amplitude, 400L);
    } 
    this.lastBuff = buff;
  }
  
  private void ptt() {
    this.activating = false;
    int dataLength = AudioChannelConfig.getDataLength();
    if (!isPTTKeyDown()) {
      if (this.wasPTT) {
        this.mic.stop();
        this.mic.flush();
        this.wasPTT = false;
      } 
      try {
        Thread.sleep(10L);
      } catch (InterruptedException interruptedException) {}
      return;
    } 
    this.wasPTT = true;
    this.mic.start();
    if (this.mic.available() < dataLength) {
      try {
        Thread.sleep(10L);
      } catch (InterruptedException interruptedException) {}
      return;
    } 
    byte[] buff = new byte[dataLength];
    while (this.mic.available() >= dataLength)
      this.mic.read(buff, 0, buff.length); 
    float microphoneAmplification = (float)PalaVoiceChatMod.getClientProxy().getClientConfig().getMicrophoneAmplification();
    Utils.adjustVolumeMono(buff, microphoneAmplification);
    sendAudioPacket(buff);
  }
  
  private void sendAudioPacket(byte[] data) {
    if (IoNettyVoIPClient.getInstance().isTestingMic()) {
      UUID sender = (Minecraft.func_71410_x()).field_71439_g.func_110124_au();
      SoundPacket soundPacket = new SoundPacket(sender, (Minecraft.func_71410_x()).field_71439_g.func_70005_c_(), data);
      AudioChannel sendTo = (AudioChannel)IoNettyVoIPClient.getInstance().getAudioChannels().get(sender);
      if (sendTo == null) {
        AudioChannel ch = new AudioChannel(sender);
        ch.addToQueue(soundPacket);
        ch.start();
        IoNettyVoIPClient.getInstance().getAudioChannels().put(sender, ch);
      } else {
        sendTo.addToQueue(soundPacket);
      } 
      IoNettyVoIPClient.getInstance().getAudioChannels().values().stream().filter(AudioChannel::canKill).forEach(AudioChannel::closeAndKill);
      IoNettyVoIPClient.getInstance().getAudioChannels().entrySet().removeIf(entry -> ((AudioChannel)entry.getValue()).isClosed());
      float f = Utils.calculateAmplitude(data);
      IoNettyVoIPClient.getInstance().getTalkCache().updateTalkingWithAmplitude((Minecraft.func_71410_x()).field_71439_g.func_110124_au(), f, 400L);
      return;
    } 
    IoNettyVoIPClient.getInstance().sendPacket((Packet)new MicrophonePacket(data));
    float amplitude = Utils.calculateAmplitude(data);
    IoNettyVoIPClient.getInstance().getTalkCache().updateTalkingWithAmplitude((Minecraft.func_71410_x()).field_71439_g.func_110124_au(), amplitude);
  }
  
  public TargetDataLine getMic() {
    return this.mic;
  }
  
  public boolean isTalking() {
    return (!this.microphoneLocked && (this.activating || this.wasPTT));
  }
  
  public void setMicrophoneLocked(boolean microphoneLocked) {
    this.microphoneLocked = microphoneLocked;
    this.activating = false;
    this.wasPTT = false;
    this.deactivationDelay = 0;
    this.lastBuff = null;
  }
  
  public void close() {
    this.running = false;
    this.mic.stop();
    this.mic.flush();
    this.mic.close();
  }
  
  private boolean isPTTKeyDown() {
    int keyCode = KeyBindHandlerListener.PTT.func_151463_i();
    return (keyCode == 0) ? false : ((keyCode < 0) ? Mouse.isButtonDown(keyCode + 100) : Keyboard.isKeyDown(keyCode));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\client\voip\micro\MicThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */