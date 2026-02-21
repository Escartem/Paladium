package fr.paladium.palashop.provider.cosmetic.common.dto.behavior;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;

public abstract class CosmeticBehavior<T extends ICosmetic> {
  private final String id;
  
  private final List<String> factories;
  
  public String getId() {
    return this.id;
  }
  
  public List<String> getFactories() {
    return this.factories;
  }
  
  public CosmeticBehavior(@NonNull String id, @NonNull String... factories) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (factories == null)
      throw new NullPointerException("factories is marked non-null but is null"); 
    this.id = id;
    this.factories = Arrays.asList(factories);
    FMLCommonHandler.instance().bus().register(this);
    MinecraftForge.EVENT_BUS.register(this);
  }
  
  @NonNull
  public final List<T> getApplicableList(@NonNull Entity entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    List<T> applicableList = new ArrayList<>();
    if (this.factories.isEmpty())
      return applicableList; 
    CosmeticPlayer cosmeticPlayer = CosmeticPlayer.get(entity);
    if (cosmeticPlayer == null)
      return applicableList; 
    for (String factory : this.factories) {
      Optional<CosmeticPlayer.EquippedCosmetic> optionalEquippedCosmetic = cosmeticPlayer.getOutfit().get(factory);
      if (!optionalEquippedCosmetic.isPresent())
        continue; 
      for (ICosmetic cosmetic : ((CosmeticPlayer.EquippedCosmetic)optionalEquippedCosmetic.get()).getCosmetics()) {
        if (isApplicable(cosmetic))
          applicableList.add((T)cosmetic); 
      } 
    } 
    return applicableList;
  }
  
  public final boolean isApplicable(ICosmetic cosmetic) {
    return (cosmetic != null && cosmetic.getBehaviors() != null && cosmetic.getBehaviors().contains(this.id) && this.factories.contains(cosmetic.getFactory().getId()));
  }
  
  @SideOnly(Side.CLIENT)
  public final boolean isSelf(@NonNull Entity entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    return entity.func_110124_au().equals((Minecraft.func_71410_x()).field_71439_g.func_110124_au());
  }
  
  public final void unsafeRender(@NonNull Entity entity, @NonNull ICosmetic cosmetic) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    onRender(entity, (T)cosmetic);
  }
  
  public final void unsafeTick(@NonNull Entity entity, @NonNull ICosmetic cosmetic) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    onTick(entity, (T)cosmetic);
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.factories, this.id });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    CosmeticBehavior other = (CosmeticBehavior)obj;
    return (Objects.equals(this.factories, other.factories) && Objects.equals(this.id, other.id));
  }
  
  public abstract void onRender(@NonNull Entity paramEntity, @NonNull T paramT);
  
  public abstract void onTick(@NonNull Entity paramEntity, @NonNull T paramT);
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\dto\behavior\CosmeticBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */