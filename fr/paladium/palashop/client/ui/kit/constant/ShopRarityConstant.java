package fr.paladium.palashop.client.ui.kit.constant;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class ShopRarityConstant {
  private static final Map<ShopRarity, String> NAME_MAP = new HashMap<>();
  
  private static final Map<ShopRarity, Color> COLOR_MAP = new HashMap<>();
  
  private static final Map<ShopRarity, EnumChatFormatting> CHAT_COLOR_MAP = new HashMap<>();
  
  private static final Map<ShopRarity, Resource> RESOURCE_MAP = new HashMap<>();
  
  static {
    NAME_MAP.put(ShopRarity.COMMON, "Commun");
    NAME_MAP.put(ShopRarity.RARE, "Rare");
    NAME_MAP.put(ShopRarity.EPIC, "Épique");
    NAME_MAP.put(ShopRarity.LEGENDARY, "Légendaire");
    NAME_MAP.put(ShopRarity.MYTHIC, "Mythique");
    NAME_MAP.put(ShopRarity.LIMITED, "Limité");
    NAME_MAP.put(ShopRarity.EXCLUSIVE, "Exclusif");
    CHAT_COLOR_MAP.put(ShopRarity.COMMON, EnumChatFormatting.GREEN);
    CHAT_COLOR_MAP.put(ShopRarity.RARE, EnumChatFormatting.BLUE);
    CHAT_COLOR_MAP.put(ShopRarity.EPIC, EnumChatFormatting.DARK_PURPLE);
    CHAT_COLOR_MAP.put(ShopRarity.LEGENDARY, EnumChatFormatting.RED);
    CHAT_COLOR_MAP.put(ShopRarity.MYTHIC, EnumChatFormatting.DARK_BLUE);
    CHAT_COLOR_MAP.put(ShopRarity.LIMITED, EnumChatFormatting.YELLOW);
    CHAT_COLOR_MAP.put(ShopRarity.EXCLUSIVE, EnumChatFormatting.LIGHT_PURPLE);
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
      COLOR_MAP.put(ShopRarity.COMMON, Color.decode("#3DCC35"));
      COLOR_MAP.put(ShopRarity.RARE, Color.decode("#419CE3"));
      COLOR_MAP.put(ShopRarity.EPIC, Color.decode("#8E3DE3"));
      COLOR_MAP.put(ShopRarity.LEGENDARY, Color.decode("#E3484B"));
      COLOR_MAP.put(ShopRarity.MYTHIC, Color.decode("#3F58E3"));
      COLOR_MAP.put(ShopRarity.LIMITED, Color.decode("#FDD247"));
      COLOR_MAP.put(ShopRarity.EXCLUSIVE, Color.decode("#E341B5"));
      RESOURCE_MAP.put(ShopRarity.COMMON, Resource.of(new ResourceLocation("palashop", "textures/rarity/common.png")));
      RESOURCE_MAP.put(ShopRarity.RARE, Resource.of(new ResourceLocation("palashop", "textures/rarity/rare.png")));
      RESOURCE_MAP.put(ShopRarity.EPIC, Resource.of(new ResourceLocation("palashop", "textures/rarity/epic.png")));
      RESOURCE_MAP.put(ShopRarity.LEGENDARY, Resource.of(new ResourceLocation("palashop", "textures/rarity/legendary.png")));
      RESOURCE_MAP.put(ShopRarity.MYTHIC, Resource.of(new ResourceLocation("palashop", "textures/rarity/mythic.png")));
      RESOURCE_MAP.put(ShopRarity.LIMITED, Resource.of(new ResourceLocation("palashop", "textures/rarity/limited.png")));
      RESOURCE_MAP.put(ShopRarity.EXCLUSIVE, Resource.of(new ResourceLocation("palashop", "textures/rarity/exclusive.png")));
    } 
  }
  
  public static String getName(@NonNull ShopRarity rarity) {
    if (rarity == null)
      throw new NullPointerException("rarity is marked non-null but is null"); 
    return NAME_MAP.get(rarity);
  }
  
  public static Color getColor(@NonNull ShopRarity rarity) {
    if (rarity == null)
      throw new NullPointerException("rarity is marked non-null but is null"); 
    return COLOR_MAP.get(rarity);
  }
  
  public static EnumChatFormatting getChatColor(@NonNull ShopRarity rarity) {
    if (rarity == null)
      throw new NullPointerException("rarity is marked non-null but is null"); 
    return CHAT_COLOR_MAP.get(rarity);
  }
  
  public static Resource getResource(@NonNull ShopRarity rarity) {
    if (rarity == null)
      throw new NullPointerException("rarity is marked non-null but is null"); 
    return RESOURCE_MAP.get(rarity);
  }
  
  public enum ShopRaritySoundType {
    HOVER, TOKEN;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\kit\constant\ShopRarityConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */