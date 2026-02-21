package fr.paladium.paladiumui.kit.notification;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.notification.NotificationOverlay;
import fr.paladium.zephyrui.lib.opengl.GLHelper;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.List;

@SideOnly(Side.CLIENT)
class Client {
  private static final TextInfo TITLE_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 22.0F).color(Color.WHITE);
  
  private static final TextInfo DESCRIPTION_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 15.0F).color(Color.WHITE);
  
  private static final TextInfo ORIGIN_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 15.0F).color(Color.decode("#EF3926"));
  
  @SideOnly(Side.CLIENT)
  public static void drawNotification(Notification notification, double mouseX, double mouseY, float ticks) {
    NotificationOverlay.getInstance().getSonner().margin(40.0D);
    float opacity = (notification.getPositionIndex() > 2) ? (1.0F - notification.getStackAnimator().getValue() - 2.0F) : 1.0F;
    Color backgroundColor = ColorConstant.BACKGROUND.copyAlpha(opacity);
    Color borderColor = ColorConstant.LIGHT_BACKGROUND.copyAlpha(opacity);
    DrawUtils.SHAPE.drawRect(notification.getX(), notification.getY(), notification.getWidth(), notification.getHeight(), backgroundColor);
    DrawUtils.SHAPE.drawRect(notification.getX() - 15.0D, notification.getY() - 5.0D, 15.0D, notification.getHeight() + 10.0D, backgroundColor);
    DrawUtils.SHAPE.drawRect(notification.getX() + notification.getWidth(), notification.getY() - 5.0D, 15.0D, notification.getHeight() + 10.0D, backgroundColor);
    DrawUtils.SHAPE.drawRect(notification.getX() - 5.0D, notification.getY() - 15.0D, notification.getWidth() + 10.0D, 15.0D, backgroundColor);
    DrawUtils.SHAPE.drawRect(notification.getX() - 5.0D, notification.getY() + notification.getHeight(), notification.getWidth() + 10.0D, 15.0D, backgroundColor);
    DrawUtils.SHAPE.drawRect(notification.getX() - 5.0D, notification.getY(), 5.0D, notification.getHeight(), borderColor);
    DrawUtils.SHAPE.drawRect(notification.getX() + notification.getWidth(), notification.getY(), 5.0D, notification.getHeight(), borderColor);
    DrawUtils.SHAPE.drawRect(notification.getX(), notification.getY() - 5.0D, notification.getWidth(), 5.0D, borderColor);
    DrawUtils.SHAPE.drawRect(notification.getX(), notification.getY() + notification.getHeight(), notification.getWidth(), 5.0D, borderColor);
    double iconSize = 48.0D;
    GLHelper.color(1.0F, 1.0F, 1.0F, opacity);
    DrawUtils.RESOURCE.drawImage(notification.getX() + 15.0D, notification.getY() + notification.dh(2.0D) - 24.0D, 48.0D, 48.0D, ResourceConstant.WARNING);
    GLHelper.popColor();
    TextInfo titleInfo = TITLE_INFO.copy().color(TITLE_INFO.getColor().copyAlpha(opacity));
    TextInfo descriptionInfo = DESCRIPTION_INFO.copy().color(DESCRIPTION_INFO.getColor().copyAlpha(opacity));
    TextInfo originInfo = ORIGIN_INFO.copy().color(ORIGIN_INFO.getColor().copyAlpha(opacity));
    DrawUtils.TEXT.drawText(notification.getX() + 34.0D + 48.0D, notification.getY() + notification.dh(2.0D) - 24.0D - 10.0D, 220.0D, 0.0D, Text.create(Notification.access$000(notification), titleInfo).overflow(TextOverflow.ELLIPSIS), TextMode.OVERFLOW);
    List<String> lines = DrawUtils.TEXT.getLines(300.0D, Notification.access$100(notification), descriptionInfo);
    for (int i = 0; i < Math.min(lines.size(), 2); i++)
      DrawUtils.TEXT.drawText(notification.getX() + 35.0D + 48.0D, notification.getY() + notification.dh(2.0D) - 24.0D + ((lines.size() > 1) ? 20 : 30) + (i * 20), Text.create(lines.get(i), descriptionInfo)); 
    double originWidth = originInfo.getWidth(Notification.access$200(notification));
    DrawUtils.SHAPE.drawRoundedRect(notification.getX() + notification.getWidth() - 15.0D - originWidth - 5.0D, notification.getY() + 15.0D - 5.0D, originWidth + 10.0D, originInfo.getHeight() + 10.0D, originInfo.getColor().copyAlpha(Math.min(0.2F, opacity)), 4.0F);
    DrawUtils.TEXT.drawText(notification.getX() + notification.getWidth() - 15.0D - originWidth / 2.0D, notification.getY() + 15.0D - 5.0D + (originInfo.getHeight() + 10.0D) / 2.0D, Text.create(Notification.access$200(notification), originInfo, Align.CENTER, Align.CENTER));
    if (!notification.isOnTop()) {
      float overlayOpacity = (notification.getFocusAnimator().getValue() > 0.0F) ? (1.0F - notification.getFocusAnimator().getValue()) : Math.min(notification.getStackAnimator().getValue(), 1.0F);
      Color overlayColor = new Color(0.0F, 0.0F, 0.0F, Math.min(opacity, 0.5F * overlayOpacity));
      DrawUtils.SHAPE.drawRect(notification.getX(), notification.getY(), notification.getWidth(), notification.getHeight(), overlayColor);
      DrawUtils.SHAPE.drawRect(notification.getX() - 15.0D, notification.getY() - 5.0D, 15.0D, notification.getHeight() + 10.0D, overlayColor);
      DrawUtils.SHAPE.drawRect(notification.getX() + notification.getWidth(), notification.getY() - 5.0D, 15.0D, notification.getHeight() + 10.0D, overlayColor);
      DrawUtils.SHAPE.drawRect(notification.getX() - 5.0D, notification.getY() - 15.0D, notification.getWidth() + 10.0D, 15.0D, overlayColor);
      DrawUtils.SHAPE.drawRect(notification.getX() - 5.0D, notification.getY() + notification.getHeight(), notification.getWidth() + 10.0D, 15.0D, overlayColor);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\notification\Notification$Client.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */