package fr.paladium.palamod.modules.argus;

final class null extends Thread {
  public void run() {
    while (true) {
      try {
        Object o = CurrentGuiScreen.access$000();
        if (o != null)
          CurrentGuiScreen._a.c(o.getClass().getName()); 
      } catch (Exception err) {
        err.printStackTrace();
      } 
      try {
        Thread.sleep(1000L);
      } catch (Exception exception) {}
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\argus\CurrentGuiScreen$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */