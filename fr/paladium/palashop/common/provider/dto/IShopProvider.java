package fr.paladium.palashop.common.provider.dto;

import com.google.gson.JsonElement;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import org.bson.Document;

public interface IShopProvider<T extends fr.paladium.palashop.server.shop.dto.item.IShopItem> extends Comparable<IShopProvider<?>> {
  @NonNull
  String getId();
  
  @NonNull
  Class<T> getItemClass(@NonNull JsonElement paramJsonElement);
  
  <S> ShopElementRenderer<S> getRenderer(@NonNull String paramString, @NonNull S paramS);
  
  int getPriority();
  
  @SideOnly(Side.SERVER)
  @NonNull
  CompletableFuture<Boolean> hasData(@NonNull String paramString);
  
  @SideOnly(Side.SERVER)
  @NonNull
  CompletableFuture<Document> getData(@NonNull String paramString);
  
  @SideOnly(Side.SERVER)
  @NonNull
  CompletableFuture<Void> setData(@NonNull String paramString, @NonNull Document paramDocument);
  
  @SideOnly(Side.SERVER)
  @NonNull
  CompletableFuture<Void> save(@NonNull String paramString);
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\provider\dto\IShopProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */