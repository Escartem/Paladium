package fr.paladium.palashop.provider.cosmetic.common.network;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.common.event.CosmeticExecuteWheelEvent;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.MinecraftForge;

public class CSPacketExecuteWheelCosmetic extends ForgePacket {
  public CSPacketExecuteWheelCosmetic() {}
  
  public CSPacketExecuteWheelCosmetic(String factory, int index, HitResult target) {
    this.factory = factory;
    this.index = index;
    this.target = target;
  }
  
  private static final Map<String, Map<String, UICosmeticSelectorOverlayCache>> COOLDOWN_MAP = new HashMap<>();
  
  @PacketData
  private String factory;
  
  @PacketData
  private int index;
  
  @PacketData
  private HitResult target;
  
  public void processServer(EntityPlayerMP player) {
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get((Entity)player);
    if (cosmeticPlayer == null) {
      (new Notification(Notification.NotificationType.ERROR, "Impossible de charger vos cosmétiques", "palashop")).send(player);
      return;
    } 
    if (getCooldown((EntityPlayer)player, this.factory) > 0L) {
      (new Notification(Notification.NotificationType.ERROR, "Vous devez attendre avant de pouvoir utiliser ce cosmétique", "palashop")).send(player);
      return;
    } 
    Optional<CosmeticFactory> optionalFactory = CosmeticProvider.getInstance().getFactory(this.factory);
    if (!optionalFactory.isPresent()) {
      (new Notification(Notification.NotificationType.ERROR, "Ce cosmétique n'est plus disponible", "palashop")).send(player);
      return;
    } 
    Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = cosmeticPlayer.getOutfit().get(((CosmeticFactory)optionalFactory.get()).getId());
    if (!optionalEquippedCosmetic.isPresent() || ((CosmeticPlayer.EquippedCosmetic)optionalEquippedCosmetic.get()).getType() != CosmeticPlayer.EquippedCosmetic.EquippedCosmeticType.WHEEL) {
      (new Notification(Notification.NotificationType.ERROR, "Impossible de trouver ce cosmétique", "palashop")).send(player);
      return;
    } 
    CosmeticPlayer.EquippedCosmetic equippedCosmetic = optionalEquippedCosmetic.get();
    Optional<ICosmetic> optionalCosmetic = equippedCosmetic.get(this.index);
    if (!optionalCosmetic.isPresent()) {
      (new Notification(Notification.NotificationType.ERROR, "Impossible de trouver ce cosmétique", "palashop")).send(player);
      return;
    } 
    ICosmetic cosmetic = optionalCosmetic.get();
    if (MinecraftForge.EVENT_BUS.post((Event)new CosmeticExecuteWheelEvent.Pre((Entity)player, cosmetic, this.target)))
      return; 
    setCooldown((EntityPlayer)player, this.factory, equippedCosmetic.getWheelCooldown() / 2L);
    MinecraftForge.EVENT_BUS.post((Event)new CosmeticExecuteWheelEvent.Post((Entity)player, cosmetic, this.target));
  }
  
  public static void setCooldown(EntityPlayer player, String factory, long cooldown) {
    if (cooldown <= 0L)
      return; 
    String uuid = UUIDUtils.toString((Entity)player);
    if (!COOLDOWN_MAP.containsKey(uuid))
      COOLDOWN_MAP.put(uuid, new HashMap<>()); 
    ((Map<String, UICosmeticSelectorOverlayCache>)COOLDOWN_MAP.get(uuid)).put(factory, new UICosmeticSelectorOverlayCache(cooldown));
  }
  
  public static long getCooldown(EntityPlayer player, String factory) {
    String uuid = UUIDUtils.toString((Entity)player);
    if (!COOLDOWN_MAP.containsKey(uuid) || !((Map)COOLDOWN_MAP.get(uuid)).containsKey(factory))
      return 0L; 
    return ((UICosmeticSelectorOverlayCache)((Map)COOLDOWN_MAP.get(uuid)).get(factory)).getRemainingCooldown();
  }
  
  public static class HitResult {
    private final MovingObjectPosition.MovingObjectType type;
    
    private final int blockX;
    
    private final int blockY;
    
    private final int blockZ;
    
    private final int sideHit;
    
    private final Vec3 hitVec;
    
    private final String entityHit;
    
    private final int subHit;
    
    private final Object hitInfo;
    
    public MovingObjectPosition.MovingObjectType getType() {
      return this.type;
    }
    
    public int getBlockX() {
      return this.blockX;
    }
    
    public int getBlockY() {
      return this.blockY;
    }
    
    public int getBlockZ() {
      return this.blockZ;
    }
    
    public int getSideHit() {
      return this.sideHit;
    }
    
    public Vec3 getHitVec() {
      return this.hitVec;
    }
    
    public String getEntityHit() {
      return this.entityHit;
    }
    
    public int getSubHit() {
      return this.subHit;
    }
    
    public Object getHitInfo() {
      return this.hitInfo;
    }
    
    public HitResult(@NonNull MovingObjectPosition mop) {
      if (mop == null)
        throw new NullPointerException("mop is marked non-null but is null"); 
      this.type = mop.field_72313_a;
      this.blockX = mop.field_72311_b;
      this.blockY = mop.field_72312_c;
      this.blockZ = mop.field_72309_d;
      this.sideHit = mop.field_72310_e;
      this.hitVec = mop.field_72307_f;
      this.entityHit = (mop.field_72308_g == null) ? null : UUIDUtils.toString(mop.field_72308_g);
      this.subHit = mop.subHit;
      this.hitInfo = mop.hitInfo;
    }
    
    public String toString() {
      return "HitResult{type=" + this.type + ", x=" + this.blockX + ", y=" + this.blockY + ", z=" + this.blockZ + ", f=" + this.sideHit + ", pos=" + this.hitVec + ", entity=" + this.entityHit + '}';
    }
  }
  
  public static class UICosmeticSelectorOverlayCache {
    private final long cooldown;
    
    private final long lastUse;
    
    public long getCooldown() {
      return this.cooldown;
    }
    
    public long getLastUse() {
      return this.lastUse;
    }
    
    public UICosmeticSelectorOverlayCache(long cooldown) {
      this.cooldown = cooldown;
      this.lastUse = System.currentTimeMillis();
    }
    
    public long getRemainingCooldown() {
      if (this.cooldown <= 0L)
        return 0L; 
      return Math.max(0L, this.lastUse + this.cooldown - System.currentTimeMillis());
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\network\CSPacketExecuteWheelCosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */