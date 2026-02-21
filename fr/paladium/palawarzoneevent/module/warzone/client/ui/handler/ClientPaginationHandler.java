package fr.paladium.palawarzoneevent.module.warzone.client.ui.handler;

public class ClientPaginationHandler {
  public void setIndex(int index) {
    this.index = index;
  }
  
  public void setLoading(boolean loading) {
    this.loading = loading;
  }
  
  private int index = 0;
  
  public int getIndex() {
    return this.index;
  }
  
  private boolean loading = false;
  
  public boolean isLoading() {
    return this.loading;
  }
  
  public void reset() {
    this.index = 0;
    this.loading = false;
  }
  
  public void queryData(Runnable runnable) {
    if (isLoading())
      return; 
    setLoading(true);
    runnable.run();
    this.index++;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\clien\\ui\handler\ClientPaginationHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */