package fr.paladium.palamod.modules.mailbox.client.pojo;

import fr.paladium.palamod.modules.mailbox.client.ui.MailboxResource;

public enum EnumMailType {
  MESSAGE(false, false, true, true, MailboxResource.TYPE_MESSAGE),
  MARKET(true, false, true, false, MailboxResource.TYPE_MARKET),
  REFUND(true, false, true, true, MailboxResource.TYPE_REFUND),
  ITEM(true, false, true, false, MailboxResource.TYPE_ITEM),
  GIFT(true, false, true, false, MailboxResource.TYPE_GIFT),
  INVITATION(false, true, true, true, MailboxResource.TYPE_INVITATION);
  
  private boolean hasItems;
  
  private boolean executeCommand;
  
  private boolean faction;
  
  private boolean minigames;
  
  private MailboxResource resource;
  
  EnumMailType(boolean hasItems, boolean executeCommand, boolean faction, boolean minigames, MailboxResource mailboxResource) {
    this.hasItems = hasItems;
    this.executeCommand = executeCommand;
    this.faction = faction;
    this.minigames = minigames;
    this.resource = mailboxResource;
  }
  
  public boolean isHasItems() {
    return this.hasItems;
  }
  
  public void setHasItems(boolean hasItems) {
    this.hasItems = hasItems;
  }
  
  public boolean isExecuteCommand() {
    return this.executeCommand;
  }
  
  public void setExecuteCommand(boolean executeCommand) {
    this.executeCommand = executeCommand;
  }
  
  public boolean isFaction() {
    return this.faction;
  }
  
  public void setFaction(boolean faction) {
    this.faction = faction;
  }
  
  public boolean isMinigames() {
    return this.minigames;
  }
  
  public void setMinigames(boolean minigames) {
    this.minigames = minigames;
  }
  
  public MailboxResource getResource() {
    return this.resource;
  }
  
  public void setResource(MailboxResource resource) {
    this.resource = resource;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\client\pojo\EnumMailType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */