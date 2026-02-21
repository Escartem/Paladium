package fr.paladium.palamod.modules.luckyblock.gui.may;

import fr.paladium.ariane.lib.dialog.ArianeDialog;
import fr.paladium.palamod.modules.luckyblock.monthly.dialogs.AbstractDialogManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CustomDialog extends ArianeDialog {
  private final String title;
  
  private final String text;
  
  private boolean closeGui;
  
  private List<String> answerOptions;
  
  private Consumer<Integer> callback;
  
  private String expressionTexture;
  
  private Float expressionX;
  
  private Float expressionY;
  
  private Float expressionSizeX;
  
  private Float expressionSizeY;
  
  public String getTitle() {
    return this.title;
  }
  
  public String getText() {
    return this.text;
  }
  
  public boolean isCloseGui() {
    return this.closeGui;
  }
  
  public List<String> getAnswerOptions() {
    return this.answerOptions;
  }
  
  public Consumer<Integer> getCallback() {
    return this.callback;
  }
  
  public String getExpressionTexture() {
    return this.expressionTexture;
  }
  
  public Float getExpressionX() {
    return this.expressionX;
  }
  
  public Float getExpressionY() {
    return this.expressionY;
  }
  
  public Float getExpressionSizeX() {
    return this.expressionSizeX;
  }
  
  public Float getExpressionSizeY() {
    return this.expressionSizeY;
  }
  
  public CustomDialog(String title, String text) {
    super(text, null);
    this.title = title;
    this.text = text;
    this.answerOptions = new ArrayList<>();
    this.closeGui = true;
  }
  
  public CustomDialog setCloseGui() {
    this.closeGui = true;
    return this;
  }
  
  public CustomDialog addOption(String option) {
    this.answerOptions.add(option);
    return this;
  }
  
  public CustomDialog addOptions(String... options) {
    this.answerOptions.addAll(Arrays.asList(options));
    return this;
  }
  
  public boolean hasOptions() {
    return (this.answerOptions.size() > 0);
  }
  
  public CustomDialog setCallback(Consumer<Integer> callback) {
    this.callback = callback;
    return this;
  }
  
  public CustomDialog setExpression(String expressionTexture, float x, float y, float sizeX, float sizeY) {
    this.expressionTexture = expressionTexture;
    this.expressionX = Float.valueOf(x);
    this.expressionY = Float.valueOf(y);
    this.expressionSizeX = Float.valueOf(sizeX);
    this.expressionSizeY = Float.valueOf(sizeY);
    return this;
  }
  
  public boolean hasExpression() {
    return (this.expressionTexture != null);
  }
  
  public static CustomDialog of(AbstractDialogManager manager, String content) {
    return new CustomDialog(manager.getTitle(), content);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\may\CustomDialog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */