package fr.paladium.palamod.mixins.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.common.CommonBridge;
import fr.paladium.palamod.modules.paladium.bridge.ZUIBridge;
import fr.paladium.palamod.modules.paladium.network.CSPacketDisconnect;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMemoryErrorScreen;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MinecraftError;
import net.minecraft.util.ReportedException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Minecraft.class})
public abstract class IMixinMinecraft {
  @Shadow
  private boolean field_71434_R;
  
  @Shadow
  private CrashReport field_71433_S;
  
  @Shadow
  private volatile boolean field_71425_J;
  
  @Shadow
  @Final
  private File field_110446_Y;
  
  @Shadow
  public GameSettings field_71474_y;
  
  @Shadow
  public GuiScreen field_71462_r;
  
  private long lastCrash;
  
  private boolean canClose;
  
  @Inject(method = {"startGame"}, at = {@At(value = "INVOKE", target = "Lnet/minecraftforge/client/ForgeHooksClient;createDisplay()V")})
  private void setNewIcon(CallbackInfo ci) {
    try {
      File file16 = new File(this.field_110446_Y, "icon_16x16.png");
      File file32 = new File(this.field_110446_Y, "icon_32x32.png");
      if (!file16.exists()) {
        URL url16 = new URL("https://pictures.paladium-pvp.fr/minecraft/logo_16x16.png");
        FileUtils.copyURLToFile(url16, file16);
      } 
      if (!file32.exists()) {
        URL url32 = new URL("https://pictures.paladium-pvp.fr/minecraft/logo_32x32.png");
        FileUtils.copyURLToFile(url32, file32);
      } 
      InputStream inputStream = new FileInputStream(file16);
      InputStream inputStream1 = new FileInputStream(file32);
      if (inputStream != null && inputStream1 != null)
        Display.setIcon(new ByteBuffer[] { inputStreamToByteBuffer(inputStream), inputStreamToByteBuffer(inputStream1) }); 
      inputStream.close();
      inputStream1.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  private ByteBuffer inputStreamToByteBuffer(InputStream stream) throws IOException {
    BufferedImage bufferedimage = ImageIO.read(stream);
    int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), (int[])null, 0, bufferedimage.getWidth());
    ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);
    int[] aint1 = aint;
    int i = aint.length;
    for (int j = 0; j < i; j++) {
      int k = aint1[j];
      bytebuffer.putInt(k << 8 | k >> 24 & 0xFF);
    } 
    bytebuffer.flip();
    return bytebuffer;
  }
  
  @Inject(method = {"shutdownMinecraftApplet"}, at = {@At("HEAD")}, cancellable = true)
  private void shutdownMinecraftApplet(CallbackInfo ci) {
    if (CommonBridge.isInFight()) {
      if (!this.field_71434_R) {
        (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("§8[§cPaladium§8] §cIl est interdit de déconnecter en combat !"));
      } else {
        if (this.lastCrash + 5000L > System.currentTimeMillis()) {
          PalaMod.getNetwork().sendToServer((IMessage)new CSPacketDisconnect(this.field_71434_R, CommonBridge.getOpponents(), (this.field_71433_S == null) ? null : this.field_71433_S.func_71501_a()));
          (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("§8[§cPaladium§8] §cPotentiel crash détecté."));
          return;
        } 
        this.lastCrash = System.currentTimeMillis();
        this.field_71434_R = false;
        this.field_71433_S = null;
        (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("§8[§cPaladium§8] §cPotentiel crash détecté."));
      } 
      ci.cancel();
      resume();
    } else if (!this.field_71434_R) {
      if (this.canClose)
        return; 
      if ((Keyboard.isKeyDown(56) || Keyboard.isKeyDown(184)) && Keyboard.isKeyDown(62) && (Minecraft.func_71410_x()).field_71439_g != null) {
        if (!ZUIBridge.IMixinMinecraft.isAltF4PopupOpen())
          ZUIBridge.IMixinMinecraft.openAltF4Popup(() -> {
                this.canClose = true;
                Minecraft.func_71410_x().func_71405_e();
              }() -> this.canClose = false); 
        ci.cancel();
        resume();
      } 
    } 
  }
  
  private void resume() {
    this.field_71425_J = true;
    try {
      while (this.field_71425_J) {
        if (!this.field_71434_R || this.field_71433_S == null) {
          try {
            func_71411_J();
          } catch (OutOfMemoryError outofmemoryerror) {
            Minecraft.func_71410_x().func_71398_f();
            Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiMemoryErrorScreen());
            System.gc();
          } 
          continue;
        } 
        Minecraft.func_71410_x().func_71377_b(this.field_71433_S);
        return;
      } 
    } catch (MinecraftError minecraftError) {
    
    } catch (ReportedException reportedexception) {
      Minecraft.func_71410_x().func_71396_d(reportedexception.func_71575_a());
      Minecraft.func_71410_x().func_71398_f();
      LogManager.getLogger().fatal("Reported exception thrown!", (Throwable)reportedexception);
      Minecraft.func_71410_x().func_71377_b(reportedexception.func_71575_a());
    } catch (Throwable throwable1) {
      CrashReport crashreport = Minecraft.func_71410_x().func_71396_d(new CrashReport("Unexpected error", throwable1));
      Minecraft.func_71410_x().func_71398_f();
      LogManager.getLogger().fatal("Unreported exception thrown!", throwable1);
      Minecraft.func_71410_x().func_71377_b(crashreport);
    } finally {
      Minecraft.func_71410_x().func_71405_e();
    } 
  }
  
  @Overwrite
  public int func_90020_K() {
    return (this.field_71462_r instanceof fr.paladium.palamod.modules.paladium.client.gui.palamenu.ban.UIDinoGame) ? 30 : this.field_71474_y.field_74350_i;
  }
  
  @Shadow
  public abstract void func_71411_J();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\IMixinMinecraft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */