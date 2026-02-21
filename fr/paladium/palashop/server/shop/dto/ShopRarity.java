package fr.paladium.palashop.server.shop.dto;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.client.ui.kit.constant.ShopRarityConstant;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.resource.Resource;
import lombok.NonNull;

public enum ShopRarity {
  COMMON, RARE, EPIC, LEGENDARY, MYTHIC, LIMITED, EXCLUSIVE;
  
  @SideOnly(Side.CLIENT)
  @NonNull
  public Color getColor() {
    return ShopRarityConstant.getColor(this);
  }
  
  @SideOnly(Side.CLIENT)
  @NonNull
  public Resource getResource() {
    return ShopRarityConstant.getResource(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dto\ShopRarity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */