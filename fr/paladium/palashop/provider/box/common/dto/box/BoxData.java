package fr.paladium.palashop.provider.box.common.dto.box;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.resource.MCResource;
import fr.paladium.palashop.provider.box.BoxProvider;
import fr.paladium.palashop.provider.box.client.render.item.ItemBoxKeyLindwormRenderer;
import fr.paladium.palashop.provider.box.common.item.ItemBoxKey;
import fr.paladium.palashop.provider.box.common.item.ItemBoxKeyLindworm;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.impl.loader.LindwormLoader;
import software.bernie.geckolib3.model.impl.model.LindwormModel;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimationType;
import software.bernie.geckolib3.model.impl.model.animation.impl.IdleLindwormAnimationType;

public class BoxData {
  private final String id;
  
  private final String name;
  
  private final BoxResource resource;
  
  private Item item;
  
  private float entityWidth;
  
  private float entityHeight;
  
  private float overlaySize;
  
  private BoxData(String id, String name, BoxResource resource) {
    this.entityWidth = 1.0F;
    this.entityHeight = 1.0F;
    this.overlaySize = 4.0F;
    this.id = id;
    this.name = name;
    this.resource = resource;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public BoxResource getResource() {
    return this.resource;
  }
  
  public Item getItem() {
    return this.item;
  }
  
  public float getEntityWidth() {
    return this.entityWidth;
  }
  
  public float getEntityHeight() {
    return this.entityHeight;
  }
  
  public float getOverlaySize() {
    return this.overlaySize;
  }
  
  @NonNull
  public static BoxData create(@NonNull String id, @NonNull String name) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    return new BoxData(id, name, new BoxResource());
  }
  
  @NonNull
  public BoxData item(@NonNull Item item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    this.item = item;
    return this;
  }
  
  @NonNull
  public BoxData item(@NonNull String unlocalizedName, @NonNull String textureName) {
    if (unlocalizedName == null)
      throw new NullPointerException("unlocalizedName is marked non-null but is null"); 
    if (textureName == null)
      throw new NullPointerException("textureName is marked non-null but is null"); 
    this.item = (Item)new ItemBoxKey(this.id, unlocalizedName, textureName);
    return this;
  }
  
  @NonNull
  public BoxData item(@NonNull String unlocalizedName, @NonNull ResourceLocation assets) {
    if (unlocalizedName == null)
      throw new NullPointerException("unlocalizedName is marked non-null but is null"); 
    if (assets == null)
      throw new NullPointerException("assets is marked non-null but is null"); 
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
      ResourceLocation texture = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/item/texture.png");
      ResourceLocation model = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/item/model.json");
      ResourceLocation animation = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/item/animation.json");
      this.item = (Item)new ItemBoxKeyLindworm(this.id, unlocalizedName, Resource.of(texture).nearest(), new LindwormModel(LindwormLoader.loadModel(model), LindwormLoader.loadAnimation(animation), (m, e) -> new LindwormAnimatable(m, e, 0.0F, new LindwormAnimationType[] { (LindwormAnimationType)new IdleLindwormAnimationType() })));
    } else {
      this.item = (Item)new ItemBoxKeyLindworm(this.id, unlocalizedName);
    } 
    return this;
  }
  
  @NonNull
  public BoxData entitySize(float width, float height) {
    this.entityWidth = width;
    this.entityHeight = height;
    return this;
  }
  
  @NonNull
  public BoxData overlaySize(float size) {
    this.overlaySize = size;
    return this;
  }
  
  @NonNull
  public BoxData assets(@NonNull ResourceLocation assets) {
    if (assets == null)
      throw new NullPointerException("assets is marked non-null but is null"); 
    if (FMLCommonHandler.instance().getSide() != Side.CLIENT)
      return this; 
    this.resource.create(assets);
    return this;
  }
  
  @NonNull
  public BoxData build() {
    if (this.item == null)
      throw new IllegalStateException("Item must be set before building BoxData"); 
    this.resource.build();
    if (this.item instanceof ItemBoxKeyLindworm && FMLCommonHandler.instance().getSide() == Side.CLIENT)
      MinecraftForgeClient.registerItemRenderer(this.item, (IItemRenderer)new ItemBoxKeyLindwormRenderer()); 
    return this;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public Optional<Box> getBox() {
    return BoxProvider.getServer().getBox(this.id);
  }
  
  public static class BoxResource implements IBoxResource {
    private ResourceLocation model;
    
    private ResourceLocation animation;
    
    private ResourceLocation texture;
    
    private ResourceLocation thumbnail;
    
    private ResourceLocation item;
    
    public ResourceLocation getModel() {
      return this.model;
    }
    
    public ResourceLocation getAnimation() {
      return this.animation;
    }
    
    public ResourceLocation getTexture() {
      return this.texture;
    }
    
    public ResourceLocation getThumbnail() {
      return this.thumbnail;
    }
    
    public ResourceLocation getItem() {
      return this.item;
    }
    
    public BoxData.BoxSoundResource getSound() {
      return this.sound;
    }
    
    public BoxData.BoxOverlayOpeningResource getOpeningOverlay() {
      return this.openingOverlay;
    }
    
    public BoxData.BoxOverlayWaitingResource getWaitingOverlay() {
      return this.waitingOverlay;
    }
    
    public LindwormModel<IAnimatable> getLindwormModel() {
      return this.lindwormModel;
    }
    
    public Resource getThumbnailResource() {
      return this.thumbnailResource;
    }
    
    public Resource getItemResource() {
      return this.itemResource;
    }
    
    private final BoxData.BoxSoundResource sound = new BoxData.BoxSoundResource();
    
    private final BoxData.BoxOverlayOpeningResource openingOverlay = new BoxData.BoxOverlayOpeningResource();
    
    private final BoxData.BoxOverlayWaitingResource waitingOverlay = new BoxData.BoxOverlayWaitingResource();
    
    @SideOnly(Side.CLIENT)
    private transient LindwormModel<IAnimatable> lindwormModel;
    
    @SideOnly(Side.CLIENT)
    private transient Resource thumbnailResource;
    
    @SideOnly(Side.CLIENT)
    private transient Resource itemResource;
    
    public void create(@NonNull ResourceLocation assets) {
      if (assets == null)
        throw new NullPointerException("assets is marked non-null but is null"); 
      this.model = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/model.json");
      this.animation = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/animation.json");
      this.texture = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/texture.png");
      this.thumbnail = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/thumbnail.png");
      this.item = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/item.png");
      this.sound.create(assets);
      this.openingOverlay.create(assets);
      this.waitingOverlay.create(assets);
    }
    
    public void build() {
      if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
        if (this.model == null)
          throw new IllegalStateException("Model must be set before building BoxData"); 
        if (this.texture == null)
          throw new IllegalStateException("Texture must be set before building BoxData"); 
        if (!MCResource.of(this.model).exists())
          throw new IllegalArgumentException("Model file does not exist: " + this.model); 
        if (!MCResource.of(this.texture).exists())
          throw new IllegalArgumentException("Texture file does not exist: " + this.texture); 
        if (this.animation != null && !MCResource.of(this.animation).exists())
          this.animation = null; 
        if (!MCResource.of(this.thumbnail).exists())
          throw new IllegalArgumentException("Thumbnail file does not exist: " + this.thumbnail); 
        if (this.item == null)
          throw new IllegalStateException("Item thumbnail must be set before building BoxData"); 
        if (!MCResource.of(this.item).exists())
          throw new IllegalArgumentException("Item thumbnail does not exist: " + this.item); 
        this.lindwormModel = new LindwormModel(LindwormLoader.loadModel(this.model), (this.animation != null) ? LindwormLoader.loadAnimation(this.animation) : null, Resource.of(this.texture).nearest(), (m, e) -> (new LindwormAnimatable(m, e, 0.0F, new LindwormAnimationType[0])).addAnimationType(new LindwormAnimationType[] { (LindwormAnimationType)new IdleLindwormAnimationType() }));
        this.thumbnailResource = Resource.of(this.thumbnail).nearest();
        this.itemResource = Resource.of(this.item).nearest();
      } 
      if (this.openingOverlay == null)
        throw new IllegalStateException("Opening overlay must be set before building BoxData"); 
      this.openingOverlay.build();
      if (this.waitingOverlay == null)
        throw new IllegalStateException("Waiting overlay must be set before building BoxData"); 
      this.waitingOverlay.build();
    }
  }
  
  public static class BoxOverlayWaitingResource implements IBoxResource {
    private ResourceLocation background;
    
    private ResourceLocation foreground;
    
    @SideOnly(Side.CLIENT)
    private transient Resource backgroundResource;
    
    @SideOnly(Side.CLIENT)
    private transient Resource foregroundResource;
    
    public ResourceLocation getBackground() {
      return this.background;
    }
    
    public ResourceLocation getForeground() {
      return this.foreground;
    }
    
    public Resource getBackgroundResource() {
      return this.backgroundResource;
    }
    
    public Resource getForegroundResource() {
      return this.foregroundResource;
    }
    
    public void create(@NonNull ResourceLocation assets) {
      if (assets == null)
        throw new NullPointerException("assets is marked non-null but is null"); 
      this.background = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/overlay/waiting/background.png");
      this.foreground = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/overlay/waiting/foreground.png");
    }
    
    public void build() {
      if (FMLCommonHandler.instance().getSide() != Side.CLIENT)
        return; 
      if (this.background == null)
        throw new IllegalStateException("Background must be set before building BoxOverlayResource"); 
      if (this.foreground == null)
        throw new IllegalStateException("Foreground must be set before building BoxOverlayResource"); 
      if (!MCResource.of(this.background).exists())
        throw new IllegalArgumentException("Background file does not exist: " + this.background); 
      if (!MCResource.of(this.foreground).exists())
        throw new IllegalArgumentException("Foreground file does not exist: " + this.foreground); 
      this.backgroundResource = Resource.of(this.background).linear();
      this.foregroundResource = Resource.of(this.foreground).linear();
    }
  }
  
  public static class BoxOverlayOpeningResource implements IBoxResource {
    private ResourceLocation cursor;
    
    private ResourceLocation background;
    
    private ResourceLocation foreground;
    
    @SideOnly(Side.CLIENT)
    private transient Resource cursorResource;
    
    @SideOnly(Side.CLIENT)
    private transient Resource backgroundResource;
    
    @SideOnly(Side.CLIENT)
    private transient Resource foregroundResource;
    
    public ResourceLocation getCursor() {
      return this.cursor;
    }
    
    public ResourceLocation getBackground() {
      return this.background;
    }
    
    public ResourceLocation getForeground() {
      return this.foreground;
    }
    
    public Resource getCursorResource() {
      return this.cursorResource;
    }
    
    public Resource getBackgroundResource() {
      return this.backgroundResource;
    }
    
    public Resource getForegroundResource() {
      return this.foregroundResource;
    }
    
    public void create(@NonNull ResourceLocation assets) {
      if (assets == null)
        throw new NullPointerException("assets is marked non-null but is null"); 
      this.cursor = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/overlay/opening/cursor.png");
      this.background = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/overlay/opening/background.png");
      this.foreground = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/overlay/opening/foreground.png");
    }
    
    public void build() {
      if (FMLCommonHandler.instance().getSide() != Side.CLIENT)
        return; 
      if (this.cursor == null)
        throw new IllegalStateException("Cursor must be set before building BoxOverlayResource"); 
      if (this.background == null)
        throw new IllegalStateException("Background must be set before building BoxOverlayResource"); 
      if (this.foreground == null)
        throw new IllegalStateException("Foreground must be set before building BoxOverlayResource"); 
      if (!MCResource.of(this.cursor).exists())
        throw new IllegalArgumentException("Cursor file does not exist: " + this.cursor); 
      if (!MCResource.of(this.background).exists())
        throw new IllegalArgumentException("Background file does not exist: " + this.background); 
      if (!MCResource.of(this.foreground).exists())
        throw new IllegalArgumentException("Foreground file does not exist: " + this.foreground); 
      this.cursorResource = Resource.of(this.cursor).linear();
      this.backgroundResource = Resource.of(this.background).linear();
      this.foregroundResource = Resource.of(this.foreground).linear();
    }
  }
  
  public static class BoxSoundResource implements IBoxResource {
    private ResourceLocation idle;
    
    private ResourceLocation open;
    
    private ResourceLocation close;
    
    private ResourceLocation broadcast;
    
    public void setIdle(ResourceLocation idle) {
      this.idle = idle;
    }
    
    public void setOpen(ResourceLocation open) {
      this.open = open;
    }
    
    public void setClose(ResourceLocation close) {
      this.close = close;
    }
    
    public void setBroadcast(ResourceLocation broadcast) {
      this.broadcast = broadcast;
    }
    
    public ResourceLocation getIdle() {
      return this.idle;
    }
    
    public ResourceLocation getOpen() {
      return this.open;
    }
    
    public ResourceLocation getClose() {
      return this.close;
    }
    
    public ResourceLocation getBroadcast() {
      return this.broadcast;
    }
    
    public BoxData.BoxSoundFastResource getFast() {
      return this.fast;
    }
    
    public BoxData.BoxSoundSpinResource getSpin() {
      return this.spin;
    }
    
    public BoxData.BoxSoundActionResource getAction() {
      return this.action;
    }
    
    private final BoxData.BoxSoundFastResource fast = new BoxData.BoxSoundFastResource();
    
    private final BoxData.BoxSoundSpinResource spin = new BoxData.BoxSoundSpinResource();
    
    private final BoxData.BoxSoundActionResource action = new BoxData.BoxSoundActionResource();
    
    public void create(@NonNull ResourceLocation assets) {
      if (assets == null)
        throw new NullPointerException("assets is marked non-null but is null"); 
      this.idle = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/idle.ogg");
      this.open = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/open.ogg");
      this.close = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/close.ogg");
      this.broadcast = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/broadcast.ogg");
      this.fast.create(assets);
      this.spin.create(assets);
      this.action.create(assets);
    }
    
    public void build() {
      if (FMLCommonHandler.instance().getSide() != Side.CLIENT)
        return; 
      if (this.idle == null)
        throw new IllegalStateException("Idle sound must be set before building BoxSoundResource"); 
      if (this.open == null)
        throw new IllegalStateException("Open sound must be set before building BoxSoundResource"); 
      if (this.close == null)
        throw new IllegalStateException("Close sound must be set before building BoxSoundResource"); 
      if (this.broadcast == null)
        throw new IllegalStateException("Broadcast sound must be set before building BoxSoundResource"); 
      if (!MCResource.of(this.idle).exists())
        throw new IllegalArgumentException("Idle sound file does not exist: " + this.idle); 
      if (!MCResource.of(this.open).exists())
        throw new IllegalArgumentException("Open sound file does not exist: " + this.open); 
      if (!MCResource.of(this.close).exists())
        throw new IllegalArgumentException("Close sound file does not exist: " + this.close); 
      if (!MCResource.of(this.broadcast).exists())
        throw new IllegalArgumentException("Broadcast sound file does not exist: " + this.broadcast); 
    }
  }
  
  public static class BoxSoundFastResource implements IBoxResource {
    private Map<ShopRarity, ResourceLocation> sounds;
    
    public void setSounds(Map<ShopRarity, ResourceLocation> sounds) {
      this.sounds = sounds;
    }
    
    public Map<ShopRarity, ResourceLocation> getSounds() {
      return this.sounds;
    }
    
    public void create(@NonNull ResourceLocation assets) {
      if (assets == null)
        throw new NullPointerException("assets is marked non-null but is null"); 
      this.sounds = new HashMap<>();
      for (ShopRarity rarity : ShopRarity.values()) {
        ResourceLocation sound = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/fast/" + rarity.name().toLowerCase() + ".ogg");
        if (MCResource.of(sound).exists())
          this.sounds.put(rarity, sound); 
      } 
    }
    
    public void build() {
      if (FMLCommonHandler.instance().getSide() != Side.CLIENT)
        return; 
      if (this.sounds == null || this.sounds.isEmpty())
        throw new IllegalStateException("Sounds must be set before building BoxSoundIdleResource"); 
      if (!this.sounds.containsKey(ShopRarity.COMMON))
        throw new IllegalStateException("Common rarity sound must be set before building BoxSoundIdleResource"); 
      if (!MCResource.of(this.sounds.get(ShopRarity.COMMON)).exists())
        throw new IllegalArgumentException("Common rarity sound file does not exist: " + this.sounds.get(ShopRarity.COMMON)); 
    }
    
    public ResourceLocation getSound(@NonNull ShopRarity rarity) {
      if (rarity == null)
        throw new NullPointerException("rarity is marked non-null but is null"); 
      return this.sounds.getOrDefault(rarity, this.sounds.get(ShopRarity.COMMON));
    }
  }
  
  public static class BoxSoundSpinResource implements IBoxResource {
    private ResourceLocation click;
    
    private ResourceLocation riser;
    
    private Map<ShopRarity, ResourceLocation> sounds;
    
    public void setClick(ResourceLocation click) {
      this.click = click;
    }
    
    public void setRiser(ResourceLocation riser) {
      this.riser = riser;
    }
    
    public void setSounds(Map<ShopRarity, ResourceLocation> sounds) {
      this.sounds = sounds;
    }
    
    public ResourceLocation getClick() {
      return this.click;
    }
    
    public ResourceLocation getRiser() {
      return this.riser;
    }
    
    public Map<ShopRarity, ResourceLocation> getSounds() {
      return this.sounds;
    }
    
    public void create(@NonNull ResourceLocation assets) {
      if (assets == null)
        throw new NullPointerException("assets is marked non-null but is null"); 
      this.click = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/spin/click.ogg");
      this.riser = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/spin/riser.ogg");
      this.sounds = new HashMap<>();
      for (ShopRarity rarity : ShopRarity.values()) {
        ResourceLocation sound = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/spin/" + rarity.name().toLowerCase() + ".ogg");
        if (MCResource.of(sound).exists())
          this.sounds.put(rarity, sound); 
      } 
    }
    
    public void build() {
      if (FMLCommonHandler.instance().getSide() != Side.CLIENT)
        return; 
      if (this.click == null)
        throw new IllegalStateException("Click sound must be set before building BoxSoundSpinResource"); 
      if (this.riser == null)
        throw new IllegalStateException("Riser sound must be set before building BoxSoundSpinResource"); 
      if (this.sounds == null || this.sounds.isEmpty())
        throw new IllegalStateException("Sounds must be set before building BoxSoundIdleResource"); 
      if (!this.sounds.containsKey(ShopRarity.COMMON))
        throw new IllegalStateException("Common rarity sound must be set before building BoxSoundIdleResource"); 
      if (!MCResource.of(this.click).exists())
        throw new IllegalArgumentException("Click sound file does not exist: " + this.click); 
      if (!MCResource.of(this.riser).exists())
        throw new IllegalArgumentException("Riser sound file does not exist: " + this.riser); 
      if (!MCResource.of(this.sounds.get(ShopRarity.COMMON)).exists())
        throw new IllegalArgumentException("Common rarity sound file does not exist: " + this.sounds.get(ShopRarity.COMMON)); 
    }
    
    public ResourceLocation getSound(@NonNull ShopRarity rarity) {
      if (rarity == null)
        throw new NullPointerException("rarity is marked non-null but is null"); 
      return this.sounds.getOrDefault(rarity, this.sounds.get(ShopRarity.COMMON));
    }
  }
  
  public static class BoxSoundActionResource implements IBoxResource {
    private Map<ShopRarity, ResourceLocation> soundsByRarity;
    
    private Map<BoxReward.Type, ResourceLocation> soundsByType;
    
    public void setSoundsByRarity(Map<ShopRarity, ResourceLocation> soundsByRarity) {
      this.soundsByRarity = soundsByRarity;
    }
    
    public void setSoundsByType(Map<BoxReward.Type, ResourceLocation> soundsByType) {
      this.soundsByType = soundsByType;
    }
    
    public Map<ShopRarity, ResourceLocation> getSoundsByRarity() {
      return this.soundsByRarity;
    }
    
    public Map<BoxReward.Type, ResourceLocation> getSoundsByType() {
      return this.soundsByType;
    }
    
    public void create(@NonNull ResourceLocation assets) {
      if (assets == null)
        throw new NullPointerException("assets is marked non-null but is null"); 
      this.soundsByRarity = new HashMap<>();
      for (ShopRarity rarity : ShopRarity.values()) {
        ResourceLocation sound = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/action/item/" + rarity.name().toLowerCase() + ".ogg");
        if (MCResource.of(sound).exists())
          this.soundsByRarity.put(rarity, sound); 
      } 
      this.soundsByType = new HashMap<>();
      for (BoxReward.Type type : BoxReward.Type.values()) {
        ResourceLocation sound = new ResourceLocation(assets.func_110624_b(), assets.func_110623_a() + "/sound/action/boost/" + type.name().toLowerCase() + ".ogg");
        if (MCResource.of(sound).exists())
          this.soundsByType.put(type, sound); 
      } 
    }
    
    public void build() {
      if (FMLCommonHandler.instance().getSide() != Side.CLIENT)
        return; 
      if (this.soundsByRarity == null || this.soundsByRarity.isEmpty())
        throw new IllegalStateException("Sounds must be set before building BoxSoundIdleResource"); 
      if (!this.soundsByRarity.containsKey(ShopRarity.COMMON))
        throw new IllegalStateException("Common rarity sound must be set before building BoxSoundIdleResource"); 
      if (this.soundsByType == null || this.soundsByType.isEmpty())
        throw new IllegalStateException("Sounds by type must be set before building BoxSoundActionResource"); 
      if (!MCResource.of(this.soundsByRarity.get(ShopRarity.COMMON)).exists())
        throw new IllegalArgumentException("Common rarity sound file does not exist: " + this.soundsByRarity.get(ShopRarity.COMMON)); 
    }
    
    public ResourceLocation getSound(@NonNull ShopRarity rarity) {
      if (rarity == null)
        throw new NullPointerException("rarity is marked non-null but is null"); 
      return this.soundsByRarity.getOrDefault(rarity, this.soundsByRarity.get(ShopRarity.COMMON));
    }
    
    public ResourceLocation getSound(@NonNull BoxReward.Type type) {
      if (type == null)
        throw new NullPointerException("type is marked non-null but is null"); 
      return this.soundsByType.get(type);
    }
  }
  
  private static interface IBoxResource {
    void create(@NonNull ResourceLocation param1ResourceLocation);
    
    void build();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\dto\box\BoxData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */