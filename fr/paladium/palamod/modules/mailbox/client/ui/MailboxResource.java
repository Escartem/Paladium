package fr.paladium.palamod.modules.mailbox.client.ui;

import fr.paladium.zephyrui.lib.resource.Resource;
import net.minecraft.util.ResourceLocation;

public enum MailboxResource {
  PATH("palamod:textures/gui/mailbox/"),
  PATH_TYPES("palamod:textures/gui/mailbox/types/"),
  TYPE_GIFT(PATH_TYPES.getTexture() + "type_gift"),
  TYPE_INVITATION(PATH_TYPES.getTexture() + "type_invitation"),
  TYPE_ITEM(PATH_TYPES.getTexture() + "type_item"),
  TYPE_MARKET(PATH_TYPES.getTexture() + "type_market"),
  TYPE_MESSAGE(PATH_TYPES.getTexture() + "type_message"),
  TYPE_REFUND(PATH_TYPES.getTexture() + "type_refund"),
  PALA_ICON(PATH.getTexture() + "pala_icon");
  
  private String texture;
  
  MailboxResource(String texture) {
    this.texture = texture;
  }
  
  public String getTexture() {
    return this.texture;
  }
  
  public void setTexture(String texture) {
    this.texture = texture;
  }
  
  public Resource getResource() {
    return Resource.of(new ResourceLocation(getTexture() + ".png")).nearest();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\clien\\ui\MailboxResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */