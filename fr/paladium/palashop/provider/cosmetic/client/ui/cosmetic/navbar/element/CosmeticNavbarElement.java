package fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.element;

import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.utils.list.IndexedElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.NonNull;

public class CosmeticNavbarElement implements IndexedElement {
  private final String id;
  
  private final String name;
  
  private final String factory;
  
  private final Resource resource;
  
  private final Predicate<ICosmetic> filter;
  
  private int index;
  
  public CosmeticNavbarElement(String id, String name, String factory, Resource resource, Predicate<ICosmetic> filter) {
    this.id = id;
    this.name = name;
    this.factory = factory;
    this.resource = resource;
    this.filter = filter;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getFactory() {
    return this.factory;
  }
  
  public Resource getResource() {
    return this.resource;
  }
  
  public Predicate<ICosmetic> getFilter() {
    return this.filter;
  }
  
  public int getIndex() {
    return this.index;
  }
  
  @NonNull
  public CosmeticNavbarElement index(int index) {
    this.index = index;
    return this;
  }
  
  @NonNull
  public CosmeticNavbarElement before(@NonNull CosmeticNavbarElement element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    index(element.getIndex() - 1);
    return this;
  }
  
  @NonNull
  public CosmeticNavbarElement after(@NonNull CosmeticNavbarElement element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    index(element.getIndex() + 1);
    return this;
  }
  
  @NonNull
  public List<ICosmeticClient> filter(@NonNull Collection<ICosmeticClient> cosmetics) {
    if (cosmetics == null)
      throw new NullPointerException("cosmetics is marked non-null but is null"); 
    if (this.filter == null)
      return new ArrayList<>(cosmetics); 
    return (List<ICosmeticClient>)cosmetics.stream().filter(this.filter).collect(Collectors.toList());
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.id });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (!(obj instanceof CosmeticNavbarElement))
      return false; 
    CosmeticNavbarElement other = (CosmeticNavbarElement)obj;
    return Objects.equals(this.id, other.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\clien\\ui\cosmetic\navbar\element\CosmeticNavbarElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */