package fr.paladium.palashop.server.shop.dto.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.client.ui.impl.store.UIShopStorePage;
import fr.paladium.palashop.client.ui.impl.store.impl.UIShopStoreDefaultPage;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.item.gson.ShopItemGsonAdapter;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import lombok.NonNull;

public class ShopPage {
  public String toString() {
    return "ShopPage(file=" + getFile() + ", id=" + getId() + ", name=" + getName() + ", data=" + getData() + ", uiClass=" + getUiClass() + ", conditions=" + Arrays.deepToString((Object[])getConditions()) + ")";
  }
  
  public ShopPage(String id, String name, Object data) {
    this.id = id;
    this.name = name;
    this.data = data;
  }
  
  private static final Gson GSON = (new GsonBuilder()).registerTypeAdapter(IShopItem.class, new ShopItemGsonAdapter()).create();
  
  @JsonIgnore
  private transient File file;
  
  private final String id;
  
  private final String name;
  
  private final Object data;
  
  private String uiClass;
  
  private String[] conditions;
  
  public File getFile() {
    return this.file;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public Object getData() {
    return this.data;
  }
  
  public String getUiClass() {
    return this.uiClass;
  }
  
  public String[] getConditions() {
    return this.conditions;
  }
  
  @SideOnly(Side.CLIENT)
  public UIShopStorePage create() {
    try {
      Class<?> clazz = (this.uiClass == null || this.uiClass.isEmpty()) ? UIShopStoreDefaultPage.class : Class.forName(this.uiClass);
      if (UIShopStorePage.class.isAssignableFrom(clazz))
        return clazz.getConstructor(new Class[] { ShopPage.class }).newInstance(new Object[] { this }); 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return null;
  }
  
  public <T extends ShopPageData> T getData(@NonNull Class<T> clazz) {
    if (clazz == null)
      throw new NullPointerException("clazz is marked non-null but is null"); 
    if (this.data == null)
      return null; 
    return (T)GSON.fromJson(GSON.toJson(this.data), clazz);
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.id });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    ShopPage other = (ShopPage)obj;
    return Objects.equals(this.id, other.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dto\page\ShopPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */