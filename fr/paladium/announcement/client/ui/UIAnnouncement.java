package fr.paladium.announcement.client.ui;

import fr.paladium.announcement.common.pojo.parsed.ContentType;
import fr.paladium.announcement.common.pojo.parsed.PaladiumContent;
import fr.paladium.announcement.common.pojo.parsed.PaladiumPost;
import fr.paladium.announcement.common.type.AnnouncementType;
import fr.paladium.announcement.common.utils.AnnouncementUtils;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.ArrowButtonNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.DrawText;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class UIAnnouncement extends UI {
  private final List<PaladiumPost> posts;
  
  public List<PaladiumPost> getPosts() {
    return this.posts;
  }
  
  private final ResourceLocation optimizationIcon = ResourceUtils.get("announcement", "textures/ui/section/optimization.png");
  
  public ResourceLocation getOptimizationIcon() {
    return this.optimizationIcon;
  }
  
  private final ResourceLocation bugfixIcon = ResourceUtils.get("announcement", "textures/ui/section/bugfix.png");
  
  public ResourceLocation getBugfixIcon() {
    return this.bugfixIcon;
  }
  
  private final ResourceLocation adjustmentIcon = ResourceUtils.get("announcement", "textures/ui/section/adjustement.png");
  
  public ResourceLocation getAdjustmentIcon() {
    return this.adjustmentIcon;
  }
  
  private final ResourceLocation optimizationDotIcon = ResourceUtils.get("announcement", "textures/ui/section/optimization_dot.png");
  
  public ResourceLocation getOptimizationDotIcon() {
    return this.optimizationDotIcon;
  }
  
  private final ResourceLocation bugfixDotIcon = ResourceUtils.get("announcement", "textures/ui/section/bugfix_dot.png");
  
  public ResourceLocation getBugfixDotIcon() {
    return this.bugfixDotIcon;
  }
  
  private final ResourceLocation adjustmentDotIcon = ResourceUtils.get("announcement", "textures/ui/section/adjustement_dot.png");
  
  public ResourceLocation getAdjustmentDotIcon() {
    return this.adjustmentDotIcon;
  }
  
  private String discriminator = "";
  
  public String getDiscriminator() {
    return this.discriminator;
  }
  
  private final AtomicInteger offsetX = new AtomicInteger(0);
  
  public AtomicInteger getOffsetX() {
    return this.offsetX;
  }
  
  private final AtomicInteger pageIndex = new AtomicInteger(0);
  
  public AtomicInteger getPageIndex() {
    return this.pageIndex;
  }
  
  private PaladiumPost currentPost = null;
  
  private ArrowButtonNode leftArrowButtonNode;
  
  private ArrowButtonNode rightArrowButtonNode;
  
  public PaladiumPost getCurrentPost() {
    return this.currentPost;
  }
  
  public ArrowButtonNode getLeftArrowButtonNode() {
    return this.leftArrowButtonNode;
  }
  
  public ArrowButtonNode getRightArrowButtonNode() {
    return this.rightArrowButtonNode;
  }
  
  public UIAnnouncement(@NonNull List<PaladiumPost> posts) {
    if (posts == null)
      throw new NullPointerException("posts is marked non-null but is null"); 
    this.posts = posts;
  }
  
  public void init() {
    this.currentPost = this.posts.get(this.pageIndex.get());
    this.offsetX.set(0);
    BackgroundNode.create(1520.0D, 855.0D)
      .body(body -> {
          TextNode.create(77.0D, 40.0D).text(Text.create("Paladium Posts #" + (this.pageIndex.get() + 1), PaladiumText.HEADER)).attach(body);
          CloseButtonNode.create(1456.0D, 40.0D).onClick(()).attach(body);
          if (this.posts.size() > 1) {
            this.leftArrowButtonNode = (ArrowButtonNode)ArrowButtonNode.Left.create(776.0D, 85.0D, 30.0D).size(30.0D, 30.0D).onClick(()).enabled(()).attach(body);
            this.rightArrowButtonNode = (ArrowButtonNode)ArrowButtonNode.Right.create(816.0D, 85.0D, 30.0D).onClick(()).attach(body);
          } 
          ContainerNode.create(77.0D, 160.0D, 1370.0D, 650.0D).overflow(OverflowProperty.SCROLL).scrollbar((ScrollbarNode)ScrollbarNode.create(1375.0D, 0.0D, 650.0D)).body(()).attach(body);
        }).attach(this);
  }
  
  private void nextPage() {
    if (this.pageIndex.get() < this.posts.size() - 1) {
      this.pageIndex.set(this.pageIndex.get() + 1);
      reload();
      if (this.pageIndex.get() == this.posts.size() - 1)
        this.rightArrowButtonNode.enabled(n -> false); 
    } 
  }
  
  private void previousPage() {
    if (this.pageIndex.get() > 0) {
      this.pageIndex.set(this.pageIndex.get() - 1);
      reload();
      if (this.pageIndex.get() == 0)
        this.leftArrowButtonNode.enabled(n -> false); 
    } 
  }
  
  private void displayContentRecursive(Node parent, List<PaladiumContent> contents, int level) {
    for (Iterator<PaladiumContent> iterator = contents.iterator(); iterator.hasNext(); ) {
      ResourceLocation prefixIcon, iconF;
      TextInfo info;
      int lines;
      double height;
      PaladiumContent content = iterator.next();
      double indent = level * 20.0D;
      switch (content.getType()) {
        case MAIN_TITLE:
        case BIG_TITLE:
        case TITLE:
          prefixIcon = null;
          if ("optimisations".equalsIgnoreCase(content.getContent())) {
            prefixIcon = this.optimizationIcon;
          } else if ("corrections".equalsIgnoreCase(content.getContent())) {
            prefixIcon = this.bugfixIcon;
          } else if ("ajouts et suppressions".equalsIgnoreCase(content.getContent()) || "ajustements".equalsIgnoreCase(content.getContent())) {
            prefixIcon = this.adjustmentIcon;
          } 
          this.discriminator = content.getContent();
          iconF = prefixIcon;
          ContainerNode.create(0.0D, 0.0D, 0.0D, 15.0D)
            .attach(parent);
          ContainerNode.create(indent, 0.0D, parent.getWidth(), 40.0D)
            .body(b -> {
                if (iconF != null) {
                  ImageNode.create(indent, -4.0D).resource(iconF).width(39.0D).attach(b);
                  this.offsetX.set(61);
                } 
                TextNode.create(this.offsetX.get() + indent, parent.getY()).text(Text.create(content.getContent(), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 25.0F).color(Color.WHITE).shadow(0.0F, 4.0F))).attach(b);
              }).attach(parent);
          displayContentRecursive(parent, content.getChildren(), level + 1);
        case PARAGRAPH:
          TextNode.create(indent, 0.0D)
            .text(Text.create("" + content.getContent(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 16.0F).color((level == 0) ? Color.LIGHTGRAY : Color.WHITE).shadow(0.0F, 4.0F)))
            .attach(parent);
          displayContentRecursive(parent, content.getChildren(), level + 1);
        case IMAGE:
          ImageNode.create(indent, 0.0D)
            .resource(content.getContent())
            .width(parent.getWidth())
            .attach(parent);
          displayContentRecursive(parent, content.getChildren(), level + 1);
        case LIST:
          displayContentRecursive(parent, content.getChildren(), level);
        case LIST_ITEM:
          info = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 16.0F).color(Color.WHITE).shadow(0.0F, 4.0F);
          lines = DrawUtils.TEXT.getLines(1231.0D, content.getContent(), info).size();
          height = lines * info.getHeight();
          ContainerNode.create((level * 50 + this.offsetX.get()), 0.0D, (1231 - level * 50), height + 30.0D)
            .body(b -> {
                RectNode.create(0.0D, 0.0D, b.getWidth(), b.getHeight()).color(new Color(26, 26, 32)).attach(b);
                ResourceLocation dotIcon = null;
                if ("optimisations".equalsIgnoreCase(this.discriminator)) {
                  dotIcon = this.optimizationDotIcon;
                } else if ("corrections".equalsIgnoreCase(this.discriminator)) {
                  dotIcon = this.bugfixDotIcon;
                } else if ("ajouts et suppressions".equalsIgnoreCase(this.discriminator) || "ajustements".equalsIgnoreCase(this.discriminator)) {
                  dotIcon = this.adjustmentDotIcon;
                } 
                ResourceLocation dotF = dotIcon;
                if (dotF != null)
                  ImageNode.create(11.0D, 16.0D).resource(dotF).width(13.399999618530273D).attach(b); 
                TextNode.create((11 + ((dotF != null) ? 20 : 0)), 12.0D).text(Text.create(content.getContent(), info)).mode(TextMode.SPLIT).hoverLines(()).width(1190.0D).attach(b);
              }).attach(parent);
          displayContentRecursive(parent, content.getChildren(), level + 1);
      } 
    } 
  }
  
  public static String getTimeAgo(long pastTime, long currentTime) {
    long duration = currentTime - pastTime;
    if (duration < TimeUnit.MINUTES.toMillis(1L)) {
      long seconds = TimeUnit.MILLISECONDS.toSeconds(duration);
      return "Il y a " + seconds + " seconde" + ((seconds > 1L) ? "s" : "");
    } 
    if (duration < TimeUnit.HOURS.toMillis(1L)) {
      long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
      return "Il y a " + minutes + " minute" + ((minutes > 1L) ? "s" : "");
    } 
    if (duration < TimeUnit.DAYS.toMillis(1L)) {
      long hours = TimeUnit.MILLISECONDS.toHours(duration);
      return "Il y a " + hours + " heure" + ((hours > 1L) ? "s" : "");
    } 
    if (duration < TimeUnit.DAYS.toMillis(30L)) {
      long days = TimeUnit.MILLISECONDS.toDays(duration);
      return "Il y a " + days + " jour" + ((days > 1L) ? "s" : "");
    } 
    long months = duration / TimeUnit.DAYS.toMillis(30L);
    return "Il y a " + months + " mois";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\clien\\ui\UIAnnouncement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */