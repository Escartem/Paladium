package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data;

import java.util.Arrays;

public class DialogDataBuilder {
  private int id;
  
  private String dialog;
  
  private String[] answers;
  
  public DialogDataBuilder id(int id) {
    this.id = id;
    return this;
  }
  
  public DialogDataBuilder dialog(String dialog) {
    this.dialog = dialog;
    return this;
  }
  
  public DialogDataBuilder answers(String[] answers) {
    this.answers = answers;
    return this;
  }
  
  public DialogData build() {
    return new DialogData(this.id, this.dialog, this.answers);
  }
  
  public String toString() {
    return "DialogData.DialogDataBuilder(id=" + this.id + ", dialog=" + this.dialog + ", answers=" + Arrays.deepToString((Object[])this.answers) + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\data\DialogData$DialogDataBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */