package fr.paladium.palashop.server.pb;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import fr.paladium.palaforgeutils.lib.sidedaction.ServerActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerActionContext;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.PalaShopMod;
import fr.paladium.palashop.api.server.pb.dto.PBData;
import fr.paladium.palashop.api.server.pb.dto.PBType;
import fr.paladium.palashop.api.server.pb.request.PBMutationRequest;
import fr.paladium.palashop.api.server.pb.response.PBHistoryResponse;
import fr.paladium.palashop.api.utils.CompletableCallback;
import fr.paladium.palashop.common.pb.network.SCPacketBuyPB;
import fr.paladium.palashop.common.utils.server.ServerUtils;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class PBManager {
  @ServerAction(secure = false)
  @NonNull
  public static CompletableFuture<Long> get(@NonNull String uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    return ServerActionHook.useServer(context -> {
          String finalUUID = context.isActive() ? UUIDUtils.toString((Entity)context.getPlayer()) : uuid;
          CompletableFuture<Long> future = new CompletableFuture<>();
          PalaShopMod.getServer().getPbAPI().get(finalUUID).enqueue(CompletableCallback.create(future));
          return future;
        }new Object[] { uuid });
  }
  
  @ServerAction(secure = false)
  @NonNull
  public static CompletableFuture<PBData> getData(@NonNull String uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    return ServerActionHook.useServer(context -> {
          String finalUUID = context.isActive() ? UUIDUtils.toString((Entity)context.getPlayer()) : uuid;
          CompletableFuture<Long> seasonFuture = new CompletableFuture<>();
          CompletableFuture<Long> permanentFuture = new CompletableFuture<>();
          PalaShopMod.getServer().getPbAPI().getSeason(finalUUID).enqueue(CompletableCallback.create(seasonFuture));
          PalaShopMod.getServer().getPbAPI().getPermanent(finalUUID).enqueue(CompletableCallback.create(permanentFuture));
          return CompletableFuture.allOf((CompletableFuture<?>[])new CompletableFuture[] { seasonFuture, permanentFuture }, ).thenApplyAsync(());
        }new Object[] { uuid });
  }
  
  @ServerAction(secure = false)
  @NonNull
  public static CompletableFuture<Long> getSeason(@NonNull String uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    return ServerActionHook.useServer(context -> {
          String finalUUID = context.isActive() ? UUIDUtils.toString((Entity)context.getPlayer()) : uuid;
          CompletableFuture<Long> future = new CompletableFuture<>();
          PalaShopMod.getServer().getPbAPI().getSeason(finalUUID).enqueue(CompletableCallback.create(future));
          return future;
        }new Object[] { uuid });
  }
  
  @ServerAction(secure = false)
  @NonNull
  public static CompletableFuture<Long> getPermanent(@NonNull String uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    return ServerActionHook.useServer(context -> {
          String finalUUID = context.isActive() ? UUIDUtils.toString((Entity)context.getPlayer()) : uuid;
          CompletableFuture<Long> future = new CompletableFuture<>();
          PalaShopMod.getServer().getPbAPI().getPermanent(finalUUID).enqueue(CompletableCallback.create(future));
          return future;
        }new Object[] { uuid });
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static CompletableFuture<Boolean> buy(@NonNull String uuid, long value, @NonNull String reason) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (reason == null)
      throw new NullPointerException("reason is marked non-null but is null"); 
    CompletableFuture<Boolean> future = new CompletableFuture<>();
    get(uuid)
      .thenAccept(pb -> {
          if (pb.longValue() < value) {
            future.complete(Boolean.valueOf(false));
            EntityPlayerMP player = PlayerUtils.getPlayer(UUIDUtils.from(uuid));
            if (player == null)
              return; 
            (new SCPacketBuyPB(value - pb.longValue())).send(player);
            return;
          } 
          remove(uuid, reason, value).thenAccept(()).exceptionally(());
        }).exceptionally(throwable -> {
          future.completeExceptionally(throwable);
          return null;
        });
    return future;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static CompletableFuture<PBHistoryResponse> getHistory(@NonNull String uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    CompletableFuture<PBHistoryResponse> future = new CompletableFuture<>();
    PalaShopMod.getServer().getPbAPI().getHistory(uuid).enqueue(CompletableCallback.create(future));
    return future;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static CompletableFuture<Long> mutate(@NonNull String uuid, @NonNull PBMutationRequest mutation) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (mutation == null)
      throw new NullPointerException("mutation is marked non-null but is null"); 
    CompletableFuture<Long> future = new CompletableFuture<>();
    PalaShopMod.getServer().getPbAPI().mutate(uuid, mutation).enqueue(CompletableCallback.create(future));
    return future;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static CompletableFuture<Long> set(@NonNull String uuid, @NonNull String reason, long value) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (reason == null)
      throw new NullPointerException("reason is marked non-null but is null"); 
    return set(uuid, reason, value, PBType.PERMANENT);
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static CompletableFuture<Long> set(@NonNull String uuid, @NonNull String reason, long value, @NonNull PBType type) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (reason == null)
      throw new NullPointerException("reason is marked non-null but is null"); 
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return mutate(uuid, new PBMutationRequest(uuid, reason, ServerUtils.getServerFullName(), (type == PBType.SEASON) ? PBMutationRequest.MutationType.INCREMENT_SEASON : PBMutationRequest.MutationType.INCREMENT_PERMANENT, value));
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static CompletableFuture<Long> add(@NonNull String uuid, @NonNull String reason, long value) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (reason == null)
      throw new NullPointerException("reason is marked non-null but is null"); 
    return add(uuid, reason, value, PBType.SEASON);
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static CompletableFuture<Long> add(@NonNull String uuid, @NonNull String reason, long value, @NonNull PBType type) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (reason == null)
      throw new NullPointerException("reason is marked non-null but is null"); 
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return mutate(uuid, new PBMutationRequest(uuid, reason, ServerUtils.getServerFullName(), (type == PBType.SEASON) ? PBMutationRequest.MutationType.INCREMENT_SEASON : PBMutationRequest.MutationType.INCREMENT_PERMANENT, value));
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static CompletableFuture<Long> remove(@NonNull String uuid, @NonNull String reason, long value) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (reason == null)
      throw new NullPointerException("reason is marked non-null but is null"); 
    return mutate(uuid, new PBMutationRequest(uuid, reason, ServerUtils.getServerFullName(), PBMutationRequest.MutationType.DECREMENT, value));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\pb\PBManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */