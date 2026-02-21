package fr.paladium.palavoicechat.client.listener;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palaforgeutils.lib.server.internal.event.ServerChangeEvent;
import fr.paladium.palavoicechat.PalaVoiceChatMod;
import fr.paladium.palavoicechat.client.event.RenderPlayerRepoHeadEvent;
import fr.paladium.palavoicechat.client.model.PalaVoiceChatModelPlayer;
import fr.paladium.palavoicechat.client.ui.overlay.UIOverlayVoiceChat;
import fr.paladium.palavoicechat.client.voip.client.IoNettyVoIPClient;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderHandEvent;

public class PalaVoiceChatEntityListener {
  public static final List<ServerType> enabledVoiceServers = Arrays.asList(new ServerType[] { ServerType.RPG, ServerType.WARZONE, ServerType.FACTION, ServerType.FARMLAND, ServerType.MINAGE, ServerType.DIM_MINER });
  
  private boolean isModelReplaced = false;
  
  @SubscribeEvent
  public void onServerTypeChanged(ServerChangeEvent.Post event) {
    if (!enabledVoiceServers.contains(event.getServerType())) {
      if (IoNettyVoIPClient.getInstance().isConnected()) {
        (new Notification(Notification.NotificationType.SUCCESS, "Vous avez été déconnecté du voice chat", "VOICE")).send();
        IoNettyVoIPClient.getInstance().disconnect();
      } 
      UI ui = ZUI.getUI(UIOverlayVoiceChat.class);
      if (ui != null)
        ZUI.close(ui); 
      return;
    } 
    if (PalaVoiceChatMod.getClientProxy().getClientConfig().isVoiceChatEnabled()) {
      if (IoNettyVoIPClient.getInstance().isConnected())
        IoNettyVoIPClient.getInstance().disconnect(); 
      (new Notification(Notification.NotificationType.INFO, "Connexion au voice chat en cours...", "VOICE")).send();
      IoNettyVoIPClient.getInstance().connect();
      if (!ZUI.isOpen(UIOverlayVoiceChat.class))
        ZUI.open((UI)new UIOverlayVoiceChat()); 
    } 
  }
  
  @SubscribeEvent
  public void onPlayerDisconnectFromServer(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
    (new Notification(Notification.NotificationType.SUCCESS, "Vous avez été déconnecté du voice chat", "VOICE")).send();
    IoNettyVoIPClient.getInstance().disconnect();
    UI ui = ZUI.getUI(UIOverlayVoiceChat.class);
    if (ui != null)
      ZUI.close(ui); 
  }
  
  @SubscribeEvent
  public void onRenderPlayerRepoHead(RenderPlayerRepoHeadEvent event) {
    if (!PalaVoiceChatMod.getClientProxy().getClientConfig().isAnimatedPlayerHead())
      return; 
    EntityPlayer player = event.getPlayer();
    if (!IoNettyVoIPClient.getInstance().getTalkCache().isTalking(player))
      return; 
    float amplitude = IoNettyVoIPClient.getInstance().getTalkCache().getAmplitude(player);
    event.setAngle(1.2F * amplitude);
  }
  
  @SubscribeEvent
  public void onClientConnectToServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
    if (this.isModelReplaced)
      return; 
    RenderPlayer renderPlayer = (RenderPlayer)RenderManager.field_78727_a.func_78715_a(EntityPlayer.class);
    PalaVoiceChatModelPlayer main = new PalaVoiceChatModelPlayer(0.0F, false);
    ObfuscationReflectionHelper.setPrivateValue(RendererLivingEntity.class, renderPlayer, main, new String[] { "field_77045_g", "mainModel" });
    renderPlayer.field_77109_a = (ModelBiped)main;
    renderPlayer.field_77108_b = (ModelBiped)new PalaVoiceChatModelPlayer(1.0F, true);
    renderPlayer.field_77111_i = (ModelBiped)new PalaVoiceChatModelPlayer(0.5F, true);
    this.isModelReplaced = true;
  }
  
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onRenderHand(RenderHandEvent e) {
    if (!ForgeEnv.isIDE())
      return; 
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    if (!(entityClientPlayerMP instanceof AbstractClientPlayer))
      return; 
    AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer)entityClientPlayerMP;
    (Minecraft.func_71410_x()).field_71424_I.func_76320_a("loadingOfflineSkin");
    ResourceLocation resourceLocation = AbstractClientPlayer.field_110314_b;
    resourceLocation = AbstractClientPlayer.func_110311_f(abstractClientPlayer.func_70005_c_());
    getDownloadImageSkin(resourceLocation, "DevZeldown".equalsIgnoreCase(abstractClientPlayer.func_70005_c_()) ? "Zeldown" : abstractClientPlayer.func_70005_c_());
    abstractClientPlayer.func_152121_a(MinecraftProfileTexture.Type.SKIN, resourceLocation);
    (Minecraft.func_71410_x()).field_71424_I.func_76319_b();
  }
  
  @SideOnly(Side.CLIENT)
  private ThreadDownloadImageData getDownloadImageSkin(ResourceLocation texture, String playerName) {
    TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
    Object object = texturemanager.func_110581_b(texture);
    if (object == null) {
      object = new ThreadDownloadImageData(null, String.format("https://minotar.net/skin/%s", new Object[] { playerName }), AbstractClientPlayer.field_110314_b, (IImageBuffer)new ImageBufferDownload());
      texturemanager.func_110579_a(texture, (ITextureObject)object);
    } else if (!(object instanceof ThreadDownloadImageData)) {
      return null;
    } 
    return (ThreadDownloadImageData)object;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\client\listener\PalaVoiceChatEntityListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */