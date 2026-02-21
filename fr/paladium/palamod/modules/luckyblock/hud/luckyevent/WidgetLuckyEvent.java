package fr.paladium.palamod.modules.luckyblock.hud.luckyevent;

import fr.paladium.helios.client.utils.DrawingContext;
import fr.paladium.helios.module.utils.abstracts.AWidget;
import fr.paladium.helios.module.utils.annotations.Widget;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.luckyblock.gui.animations.Icon;
import fr.paladium.palamod.util.DurationConverter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@Widget(type = RenderGameOverlayEvent.ElementType.TEXT, post = false)
public class WidgetLuckyEvent extends AWidget {
  public void init(DrawingContext context) {
    setBoundingBox(1.0D, 83.0D, 20.0D, 2.0D);
  }
  
  public void draw(DrawingContext context) {
    if (ModuleLuckyEvent.getInstance().getEventMap().isEmpty())
      return; 
    if (ModuleLuckyEvent.getInstance().getCurrentEventIndex() + 1 > ModuleLuckyEvent.getInstance().getEventMap().size())
      ModuleLuckyEvent.getInstance().setCurrentEventIndex(0); 
    if (ModuleLuckyEvent.getInstance().getTickCpt() >= 1000) {
      if (ModuleLuckyEvent.getInstance().getEventMap().size() > ModuleLuckyEvent.getInstance().getCurrentEventIndex() + 1) {
        ModuleLuckyEvent.getInstance().setCurrentEventIndex(ModuleLuckyEvent.getInstance().getCurrentEventIndex() + 1);
      } else {
        ModuleLuckyEvent.getInstance().setCurrentEventIndex(0);
      } 
      ModuleLuckyEvent.getInstance().setTickCpt(0);
    } else {
      ModuleLuckyEvent.getInstance().setTickCpt(ModuleLuckyEvent.getInstance().getTickCpt() + 1);
    } 
    Minecraft mc = context.getMinecraft();
    preDraw(context, ModuleLuckyEvent.getInstance());
    ModuleLuckyEvent.WidgetLuckyEventObject widgetLuckyEventObject = (ModuleLuckyEvent.WidgetLuckyEventObject)((Map.Entry)(new ArrayList<>((Collection)ModuleLuckyEvent.getInstance().getEventMap().entrySet())).get(ModuleLuckyEvent.getInstance().getCurrentEventIndex())).getValue();
    IHeliosLuckyEventWidget displayData = (IHeliosLuckyEventWidget)widgetLuckyEventObject.getEvent().getEvent();
    Icon icon = new Icon(widgetLuckyEventObject.getEvent(), 30, 30, false);
    icon.setPosX((int)getX());
    icon.setPosY((int)getY());
    icon.draw();
    double maxWidth = 0.0D;
    double currentHeight = getY() + 10.0D;
    int fontHeight = GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_BOLD.getFont(), 20) + 5;
    maxWidth = Math.max(maxWidth, GuiUtils.drawStringWithCustomFont(mc, widgetLuckyEventObject.getEvent().getEvent().getName(), getX() + 35.0D, currentHeight, Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 20));
    currentHeight += 5.0D;
    String[] desc = displayData.getDescription();
    for (int i = 0; i < desc.length; i++) {
      currentHeight += fontHeight;
      maxWidth = Math.max(maxWidth, GuiUtils.drawStringWithCustomFont(mc, desc[i], getX(), currentHeight, Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 20));
    } 
    String actionValue = null;
    if (displayData.isTimerType()) {
      Long duration = Long.valueOf(widgetLuckyEventObject.getDuration());
      Long startTime = Long.valueOf(widgetLuckyEventObject.getStartTime());
      if (duration != null && startTime != null) {
        long remainingTime = duration.longValue() - System.currentTimeMillis() - startTime.longValue();
        if (remainingTime <= 0L) {
          ModuleLuckyEvent.getInstance().getEventMap().remove(widgetLuckyEventObject.getEvent().getEvent().getClass().getName());
          return;
        } 
        actionValue = DurationConverter.fromMillisToString(remainingTime);
        if (actionValue.equals("Erreur"))
          actionValue = null; 
      } 
    } else {
      int counter = widgetLuckyEventObject.getCounter();
      if (counter > 0)
        actionValue = String.valueOf(counter); 
    } 
    if (actionValue != null) {
      currentHeight += (fontHeight + 5);
      maxWidth = Math.max(maxWidth, GuiUtils.drawStringWithCustomFont(mc, displayData.getAction(), getX(), currentHeight, Color.ORANGE, Fonts.MONTSERRAT_BOLD.getFont(), 20));
      int actionWidth = GuiUtils.getStringWidth(mc, displayData.getAction(), Fonts.MONTSERRAT_BOLD.getFont(), 20);
      maxWidth = Math.max(maxWidth, (GuiUtils.drawStringWithCustomFont(mc, actionValue, getX() + actionWidth, currentHeight, Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 20) + actionWidth));
    } 
    setRelativeWidth(maxWidth / context.getWidth() * 100.0D);
    setRelativeHeight((currentHeight + fontHeight - getY()) / context.getHeight() * 100.0D);
  }
  
  public boolean canDraw(DrawingContext context) {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\hud\luckyevent\WidgetLuckyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */