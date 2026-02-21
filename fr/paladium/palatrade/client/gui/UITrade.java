package fr.paladium.palatrade.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftTextCallToActionNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import fr.paladium.lib.apollon.nodes.field.TextFieldNode;
import fr.paladium.lib.apollon.nodes.label.TextNodeLabel;
import fr.paladium.lib.apollon.nodes.slot.SlotNode;
import fr.paladium.lib.apollon.ui.UIContainer;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.Validator;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.lib.apollon.utils.text.TextOverflow;
import fr.paladium.lib.apollon.utils.validators.ValidatorInteger;
import fr.paladium.palatrade.client.gui.node.CheckboxNode;
import fr.paladium.palatrade.common.container.ContainerTrade;
import fr.paladium.palatrade.common.container.inventory.InventoryTrade;
import fr.paladium.palatrade.common.network.CSCloseTradePacket;
import fr.paladium.palatrade.common.network.CSUpdateFieldsTradePacket;
import fr.paladium.palatrade.common.network.CSValidateTradePacket;
import fr.paladium.palatrade.lib.odin.modules.packet.OdinPacketModule;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class UITrade extends UIContainer {
  private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("palatrade", "textures/gui/background.png");
  
  private static final ResourceLocation TEXTFIELD_XP_TEXTURE = new ResourceLocation("palatrade", "textures/gui/input_xp.png");
  
  private static final ResourceLocation TEXTFIELD_XP_FOCUSED_TEXTURE = new ResourceLocation("palatrade", "textures/gui/input_xp_focused.png");
  
  private static final ResourceLocation TEXTFIELD_PB_TEXTURE = new ResourceLocation("palatrade", "textures/gui/input_pb.png");
  
  private static final ResourceLocation TEXTFIELD_PB_FOCUSED_TEXTURE = new ResourceLocation("palatrade", "textures/gui/input_pb_focused.png");
  
  private static final ResourceLocation TEXTFIELD_MONEY_TEXTURE = new ResourceLocation("palatrade", "textures/gui/input_money.png");
  
  private static final ResourceLocation TEXTFIELD_MONEY_FOCUSED_TEXTURE = new ResourceLocation("palatrade", "textures/gui/input_money_focused.png");
  
  private static final ResourceLocation SUCCESS_TEXTURE = new ResourceLocation("palatrade", "textures/gui/success.png");
  
  private static final ResourceLocation ERROR_TEXTURE = new ResourceLocation("palatrade", "textures/gui/error.png");
  
  private static final Color TOP_COLOR = Color.decode("#FF847B");
  
  private static final Color LEFT_COLOR = Color.decode("#DD2B1D");
  
  private static final Color RIGHT_COLOR = Color.decode("#DD2B1D");
  
  private static final Color BOTTOM_COLOR = Color.decode("#92130C");
  
  private static final Color FILL_COLOR = Color.decode("#EF3926");
  
  private static final Color GREEN_TOP_COLOR = Color.decode("#7FFF7B");
  
  private static final Color GREEN_LEFT_COLOR = Color.decode("#57BE13");
  
  private static final Color GREEN_RIGHT_COLOR = Color.decode("#57BE13");
  
  private static final Color GREEN_BOTTOM_COLOR = Color.decode("#34920C");
  
  private static final Color GREEN_FILL_COLOR = Color.decode("#25E251");
  
  private String target;
  
  private boolean targetValidated;
  
  private boolean targetErrored;
  
  private long targetErroredTick;
  
  private final IInventory playerInv;
  
  private final InventoryTrade tradeInv;
  
  private MinecraftTextCallToActionNode validate;
  
  private boolean validated;
  
  private boolean errored;
  
  private long erroredTick;
  
  private CheckboxNode teleportField;
  
  private CheckboxNode teleportTarget;
  
  private TextFieldNode xpField;
  
  private TextFieldNode moneyField;
  
  private TextFieldNode pbField;
  
  private TextFieldNode xpTarget;
  
  private TextFieldNode moneyTarget;
  
  private TextFieldNode pbTarget;
  
  private TextNodeLabel playerLabel;
  
  private TextNodeLabel targetLabel;
  
  private TextNodeLabel playerStatus;
  
  private TextNodeLabel targetStatus;
  
  private TextNodeLabel error;
  
  private TexturedNodeButton pbHelp;
  
  public UITrade(String target, IInventory playerInv, InventoryTrade tradeInv) {
    super(null, "palatrade", (Container)new ContainerTrade(playerInv, tradeInv));
    this.target = target;
    this.playerInv = playerInv;
    this.tradeInv = tradeInv;
    this.erroredTick = -1L;
    this.targetErroredTick = -1L;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    int i;
    for (i = 0; i < 21; i++)
      addNode((ANode)(new SlotNode((IInventory)this.tradeInv, i, (width(16.35F) + (i % 7) * width(4.166F)), (height(29.259F) + (i / 7) * height(7.407F)), height(6.57F), height(6.57F))).setTexture("palatrade:textures/gui/slot").setHoveredTexture("palatrade:textures/gui/slot_hover")); 
    for (i = 21; i < 42; i++) {
      int index = i - 21;
      addNode((ANode)(new SlotNode((IInventory)this.tradeInv, i, (width(54.58F) + (index % 7) * width(4.166F)), (height(29.259F) + (index / 7) * height(7.407F)), height(6.57F), height(6.57F))).setTexture("palatrade:textures/gui/slot").setHoveredTexture("palatrade:textures/gui/slot"));
    } 
    for (int y = 0; y < 3; y++) {
      for (int j = 0; j < 9; j++)
        addNode((ANode)(new SlotNode(this.playerInv, j + y * 9 + 9, (width(31.4F) + j * width(4.166F)), (height(53.79F) + y * height(7.407F)), height(6.57F), height(6.57F))).setTexture("palatrade:textures/gui/slot").setHoveredTexture("palatrade:textures/gui/slot_hover")); 
    } 
    for (int x = 0; x < 9; x++)
      addNode((ANode)(new SlotNode(this.playerInv, x, (width(31.4F) + x * width(4.166F)), (height(53.79F) + 3.0F * height(7.407F)), height(6.57F), height(6.57F))).setTexture("palatrade:textures/gui/slot").setHoveredTexture("palatrade:textures/gui/slot_hover")); 
    this.validated = false;
    this.validate = new MinecraftTextCallToActionNode(width(39.16F), height(87.03F), width(21.614F), "valider l'échange".toUpperCase());
    this.validate.setTOP_COLOR(GREEN_TOP_COLOR);
    this.validate.setLEFT_COLOR(GREEN_LEFT_COLOR);
    this.validate.setRIGHT_COLOR(GREEN_RIGHT_COLOR);
    this.validate.setBOTTOM_COLOR(GREEN_BOTTOM_COLOR);
    this.validate.setFILL_COLOR(GREEN_FILL_COLOR);
    this.validate.setCallback(node -> {
          setValidate(!this.validated);
          OdinPacketModule.getInstance().getNetwork().sendToServer((IMessage)new CSValidateTradePacket(this.validated));
        });
    addNode((ANode)this.validate);
    this.playerLabel = new TextNodeLabel(width(22.55F), height(16.796F), this.field_146297_k.field_71439_g.func_70005_c_(), Fonts.PIXEL_NES.getFont(), 50, Color.WHITE);
    this.playerLabel.setWidth(width(10.0F));
    this.playerLabel.setTextOverflow(TextOverflow.ELLIPSIS);
    addNode((ANode)this.playerLabel);
    this.targetLabel = new TextNodeLabel(width(60.78F), height(16.796F), this.target, Fonts.PIXEL_NES.getFont(), 50, Color.WHITE);
    this.targetLabel.setWidth(width(10.0F));
    this.targetLabel.setTextOverflow(TextOverflow.ELLIPSIS);
    addNode((ANode)this.targetLabel);
    this.playerStatus = new TextNodeLabel((width(22.55F) + GuiUtils.getStringWidth(this.field_146297_k, this.field_146297_k.field_71439_g.func_70005_c_(), Fonts.PIXEL_NES.getFont(), 50) + width(1.0F)), height(16.796F), "", Fonts.PIXEL_NES.getFont(), 20, Color.WHITE);
    this.playerStatus.setWidth(width(10.0F));
    this.playerStatus.setTextOverflow(TextOverflow.ELLIPSIS);
    addNode((ANode)this.playerStatus);
    this.targetStatus = new TextNodeLabel((width(60.78F) + GuiUtils.getStringWidth(this.field_146297_k, this.target, Fonts.PIXEL_NES.getFont(), 50) + width(1.0F)), height(16.796F), "", Fonts.PIXEL_NES.getFont(), 50, Color.WHITE);
    this.targetStatus.setWidth(width(10.0F));
    this.targetStatus.setTextOverflow(TextOverflow.ELLIPSIS);
    addNode((ANode)this.targetStatus);
    this.error = new TextNodeLabel(0.0D, height(84.0F), "", Fonts.PIXEL_NES.getFont(), 20, FILL_COLOR);
    this.error.setTextAlign(TextAlign.CENTER);
    this.error.setWidth(this.field_146294_l);
    addNode((ANode)this.error);
    this.teleportField = new CheckboxNode(width(22.55F), height(20.0F), height(2.0F), height(2.0F), node -> OdinPacketModule.getInstance().getNetwork().sendToServer((IMessage)new CSUpdateFieldsTradePacket(this.teleportField.isChecked(), Double.parseDouble(this.xpField.getText()), Double.parseDouble(this.moneyField.getText()), Double.parseDouble(this.pbField.getText())))) {
        public boolean isHovered(int mouseX, int mouseY) {
          return (super.isHovered(mouseX, mouseY) && UITrade.this.validated);
        }
      };
    addNode((ANode)this.teleportField);
    addNode((ANode)new TextNodeLabel(width(24.5F), height(20.4F), "téléportation", Fonts.PIXEL_NES.getFont(), 30, Color.WHITE));
    this.teleportTarget = new CheckboxNode(width(60.78F), height(20.0F), height(2.0F), height(2.0F), node -> {
        
        });
    this.teleportTarget.setEnabled(false);
    addNode((ANode)this.teleportTarget);
    addNode((ANode)new TextNodeLabel(width(62.73F), height(20.4F), "téléportation", Fonts.PIXEL_NES.getFont(), 30, Color.WHITE));
    this.xpField = new TextFieldNode(width(25.0F), height(23.61F), width(7.5F), height(3.425F)) {
        public void onKeyTyped(char arg0, int arg1) {
          if (UITrade.this.validated)
            return; 
          String before = getText();
          super.onKeyTyped(arg0, arg1);
          if (getText().isEmpty())
            setText("0"); 
          if (getText().equals(before))
            return; 
          OdinPacketModule.getInstance().getNetwork().sendToServer((IMessage)new CSUpdateFieldsTradePacket(UITrade.this.teleportField.isChecked(), Double.parseDouble(UITrade.this.xpField.getText()), Double.parseDouble(UITrade.this.moneyField.getText()), Double.parseDouble(UITrade.this.pbField.getText())));
        }
        
        public boolean isValidText(String text) {
          return super.isValidText(text);
        }
        
        public boolean isFocused() {
          return (!UITrade.this.validated && super.isFocused());
        }
      };
    this.xpField.setFont(Fonts.PIXEL_NES.getFont());
    this.xpField.setFontSize(20);
    this.xpField.setCursorColor(Color.WHITE);
    this.xpField.setValidator((Validator)new ValidatorInteger(2147483647));
    this.xpField.setText("0");
    addNode((ANode)this.xpField);
    this.moneyField = new TextFieldNode(width(36.85F), height(18.796F), width(7.5F), height(3.425F)) {
        public void onKeyTyped(char arg0, int arg1) {
          if (UITrade.this.validated)
            return; 
          String before = getText();
          super.onKeyTyped(arg0, arg1);
          if (getText().isEmpty())
            setText("0"); 
          if (getText().equals(before))
            return; 
          OdinPacketModule.getInstance().getNetwork().sendToServer((IMessage)new CSUpdateFieldsTradePacket(UITrade.this.teleportField.isChecked(), Double.parseDouble(UITrade.this.xpField.getText()), Double.parseDouble(UITrade.this.moneyField.getText()), Double.parseDouble(UITrade.this.pbField.getText())));
        }
        
        public boolean isValidText(String text) {
          return super.isValidText(text);
        }
        
        public boolean isFocused() {
          return (!UITrade.this.validated && super.isFocused());
        }
      };
    this.moneyField.setFont(Fonts.PIXEL_NES.getFont());
    this.moneyField.setFontSize(20);
    this.moneyField.setCursorColor(Color.WHITE);
    this.moneyField.setValidator((Validator)new ValidatorInteger(2147483647));
    this.moneyField.setText("0");
    addNode((ANode)this.moneyField);
    this.pbField = new TextFieldNode(width(36.85F), height(23.61F), width(7.5F), height(3.425F)) {
        public void onKeyTyped(char arg0, int arg1) {
          if (UITrade.this.validated)
            return; 
          String before = getText();
          super.onKeyTyped(arg0, arg1);
          if (getText().isEmpty())
            setText("0"); 
          if (getText().equals(before))
            return; 
          OdinPacketModule.getInstance().getNetwork().sendToServer((IMessage)new CSUpdateFieldsTradePacket(UITrade.this.teleportField.isChecked(), Double.parseDouble(UITrade.this.xpField.getText()), Double.parseDouble(UITrade.this.moneyField.getText()), Double.parseDouble(UITrade.this.pbField.getText())));
        }
        
        public boolean isValidText(String text) {
          return super.isValidText(text);
        }
        
        public boolean isFocused() {
          return (!UITrade.this.validated && super.isFocused());
        }
      };
    this.pbField.setFont(Fonts.PIXEL_NES.getFont());
    this.pbField.setFontSize(20);
    this.pbField.setCursorColor(Color.WHITE);
    this.pbField.setValidator((Validator)new ValidatorInteger(2147483647));
    this.pbField.setText("0");
    addNode((ANode)this.pbField);
    this.xpTarget = new TextFieldNode(width(63.23F), height(23.61F), width(7.5F), height(3.425F)) {
        public boolean isFocused() {
          return false;
        }
        
        public void onKeyTyped(char arg0, int arg1) {}
      };
    this.xpTarget.setFont(Fonts.PIXEL_NES.getFont());
    this.xpTarget.setFontSize(20);
    this.xpTarget.setText("0");
    addNode((ANode)this.xpTarget);
    this.moneyTarget = new TextFieldNode(width(75.08F), height(18.796F), width(7.5F), height(3.425F)) {
        public boolean isFocused() {
          return false;
        }
        
        public void onKeyTyped(char arg0, int arg1) {}
      };
    this.moneyTarget.setFont(Fonts.PIXEL_NES.getFont());
    this.moneyTarget.setFontSize(20);
    this.moneyTarget.setText("0");
    addNode((ANode)this.moneyTarget);
    this.pbTarget = new TextFieldNode(width(75.08F), height(23.61F), width(7.5F), height(3.425F)) {
        public boolean isFocused() {
          return false;
        }
        
        public void onKeyTyped(char arg0, int arg1) {}
      };
    this.pbTarget.setFont(Fonts.PIXEL_NES.getFont());
    this.pbTarget.setFontSize(20);
    this.pbTarget.setText("0");
    addNode((ANode)this.pbTarget);
    this.pbHelp = new TexturedNodeButton(width(84.11F), height(24.0F), width(1.458F), height(2.685F)) {
        public void draw(Minecraft mc, int mouseX, int mouseY) {
          double pbValue = Double.parseDouble(UITrade.this.pbTarget.getText().split(" ")[0]);
          if (pbValue <= 0.0D) {
            setHovers(new ArrayList());
            return;
          } 
          List<String> hovers = new ArrayList<>();
          hovers.add("Vous gagnez 90% de §c" + pbValue + " §rsoit §c" + String.format("%.2f", new Object[] { Double.valueOf(pbValue * 0.9D) }));
          setHovers(hovers);
          super.draw(mc, mouseX, mouseY);
        }
        
        public List<String> getHovers() {
          List<String> hovers = new ArrayList<>();
          return hovers;
        }
      };
    this.pbHelp.setHoverFont(Fonts.MONTSERRAT_BOLD.getFont());
    this.pbHelp.setTexture("palatrade:textures/gui/help");
    addNode((ANode)this.pbHelp);
    addNode((ANode)new MinecraftCloseNode(width(87.0F), height(8.0F)));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    GuiUtils.drawImageTransparent(0.0D, 0.0D, BACKGROUND_TEXTURE, this.field_146294_l, this.field_146295_m);
    GuiUtils.renderPlayerHead(this.field_146297_k.field_71439_g.func_70005_c_(), width(16.35F), height(18.796F), (width(4.53125F) / 18.0F));
    GuiUtils.drawImageTransparent(width(22.55F), height(23.61F), this.xpField.isFocused() ? TEXTFIELD_XP_FOCUSED_TEXTURE : TEXTFIELD_XP_TEXTURE, width(10.468F), height(3.425F), false);
    GuiUtils.drawImageTransparent(width(34.4F), height(18.796F), this.moneyField.isFocused() ? TEXTFIELD_MONEY_FOCUSED_TEXTURE : TEXTFIELD_MONEY_TEXTURE, width(10.468F), height(3.425F), false);
    GuiUtils.drawImageTransparent(width(34.4F), height(23.61F), this.pbField.isFocused() ? TEXTFIELD_PB_FOCUSED_TEXTURE : TEXTFIELD_PB_TEXTURE, width(10.468F), height(3.425F), false);
    GuiUtils.renderPlayerHead(this.target, width(54.58F), height(18.796F), (width(4.53125F) / 18.0F));
    GuiUtils.drawImageTransparent(width(60.78F), height(23.61F), TEXTFIELD_XP_TEXTURE, width(10.468F), height(3.425F), false);
    GuiUtils.drawImageTransparent(width(72.630005F), height(18.796F), TEXTFIELD_MONEY_TEXTURE, width(10.468F), height(3.425F), false);
    GuiUtils.drawImageTransparent(width(72.630005F), height(23.61F), TEXTFIELD_PB_TEXTURE, width(10.468F), height(3.425F), false);
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    super.postDraw(mouseX, mouseY, ticks);
    GL11.glTranslated(0.0D, 0.0D, 500.0D);
    if (this.validated) {
      GuiUtils.drawImageTransparent(width(16.35F), height(29.259F), SUCCESS_TEXTURE, width(28.5F), height(21.38F));
    } else if (this.errored) {
      GuiUtils.drawImageTransparent(width(16.35F), height(29.259F), ERROR_TEXTURE, width(28.5F), height(21.38F));
    } 
    if (this.targetValidated) {
      GuiUtils.drawImageTransparent(width(54.58F), height(29.259F), SUCCESS_TEXTURE, width(28.5F), height(21.38F));
    } else if (this.targetErrored) {
      GuiUtils.drawImageTransparent(width(54.58F), height(29.259F), ERROR_TEXTURE, width(28.5F), height(21.38F));
    } 
    GL11.glTranslated(0.0D, 0.0D, -500.0D);
  }
  
  public void fixedUpdate() {
    if (this.targetErroredTick >= 0L)
      if (--this.targetErroredTick < 0L) {
        setTargetError(false, "", -1L);
      } else if (this.targetErroredTick % 10L == 0L) {
        this.targetErrored = !this.targetErrored;
      }  
    if (this.erroredTick >= 0L)
      if (--this.erroredTick < 0L) {
        setError(false, "", -1L);
      } else if (this.erroredTick % 10L == 0L) {
        this.errored = !this.errored;
      }  
  }
  
  public void func_146281_b() {
    OdinPacketModule.getInstance().getNetwork().sendToServer((IMessage)new CSCloseTradePacket());
    super.func_146281_b();
  }
  
  public void updateTarget(boolean teleport, double xp, double money, double pb) {
    this.teleportTarget.setChecked(teleport);
    this.xpTarget.setText(String.valueOf((int)xp));
    this.moneyTarget.setText(String.valueOf((int)money));
    this.pbTarget.setText(String.valueOf((int)pb) + " (" + String.format("%.2f", new Object[] { Double.valueOf(pb * 0.9D) }) + ")");
  }
  
  public void updateOwn(boolean teleport, double xp, double money, double pb) {
    this.teleportField.setChecked(teleport);
    this.xpField.setText(String.valueOf((int)xp));
    this.moneyField.setText(String.valueOf((int)money));
    this.pbField.setText(String.valueOf((int)pb));
  }
  
  public void setTargetValidate(boolean validated) {
    this.targetStatus.setTextColor(GREEN_FILL_COLOR);
    this.targetStatus.text = validated ? "validé".toUpperCase() : "";
    this.targetValidated = validated;
  }
  
  public void setTargetError(boolean errored, String reason, long tick) {
    this.targetStatus.setTextColor(FILL_COLOR);
    this.targetStatus.text = errored ? "erreur".toUpperCase() : "";
    this.error.text = reason;
    this.targetErrored = errored;
    this.targetErroredTick = tick;
  }
  
  public void setValidate(boolean validated) {
    if (!validated) {
      this.validate.setText("valider l'échange".toUpperCase());
      this.validate.setTOP_COLOR(GREEN_TOP_COLOR);
      this.validate.setLEFT_COLOR(GREEN_LEFT_COLOR);
      this.validate.setRIGHT_COLOR(GREEN_RIGHT_COLOR);
      this.validate.setBOTTOM_COLOR(GREEN_BOTTOM_COLOR);
      this.validate.setFILL_COLOR(GREEN_FILL_COLOR);
    } else {
      this.validate.setText("modifier l'échange".toUpperCase());
      this.validate.setTOP_COLOR(TOP_COLOR);
      this.validate.setLEFT_COLOR(LEFT_COLOR);
      this.validate.setRIGHT_COLOR(RIGHT_COLOR);
      this.validate.setBOTTOM_COLOR(BOTTOM_COLOR);
      this.validate.setFILL_COLOR(FILL_COLOR);
    } 
    this.playerStatus.setTextColor(GREEN_FILL_COLOR);
    this.playerStatus.text = validated ? "validé".toUpperCase() : "";
    this.validated = validated;
    ((ContainerTrade)getContainer()).setValidated(validated);
    for (int i = 0; i < 21; i++)
      ((SlotNode)getSlots().get(i)).setHoveredTexture(validated ? "palatrade:textures/gui/slot" : "palatrade:textures/gui/slot_hover"); 
  }
  
  public void setError(boolean errored, String reason, long tick) {
    this.playerStatus.setTextColor(FILL_COLOR);
    this.playerStatus.text = errored ? "erreur".toUpperCase() : "";
    this.error.text = reason;
    this.errored = errored;
    this.erroredTick = tick;
  }
  
  public void setTarget(String target) {
    this.target = target;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\client\gui\UITrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */