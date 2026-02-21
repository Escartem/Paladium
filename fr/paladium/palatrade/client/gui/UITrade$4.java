package fr.paladium.palatrade.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.field.TextFieldNode;
import fr.paladium.palatrade.common.network.CSUpdateFieldsTradePacket;
import fr.paladium.palatrade.lib.odin.modules.packet.OdinPacketModule;

class null extends TextFieldNode {
  null(double x0, double x1, double x2, double x3) {
    super(x0, x1, x2, x3);
  }
  
  public void onKeyTyped(char arg0, int arg1) {
    if (UITrade.access$000(UITrade.this))
      return; 
    String before = getText();
    super.onKeyTyped(arg0, arg1);
    if (getText().isEmpty())
      setText("0"); 
    if (getText().equals(before))
      return; 
    OdinPacketModule.getInstance().getNetwork().sendToServer((IMessage)new CSUpdateFieldsTradePacket(UITrade.access$100(UITrade.this).isChecked(), Double.parseDouble(UITrade.access$200(UITrade.this).getText()), Double.parseDouble(UITrade.access$300(UITrade.this).getText()), Double.parseDouble(UITrade.access$400(UITrade.this).getText())));
  }
  
  public boolean isValidText(String text) {
    return super.isValidText(text);
  }
  
  public boolean isFocused() {
    return (!UITrade.access$000(UITrade.this) && super.isFocused());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\client\gui\UITrade$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */