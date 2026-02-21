package fr.paladium.palaconfiguration.server.config;

import fr.paladium.palaconfiguration.ConfigurationLogger;
import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;

@ConfigFile(path = "test.json", blocking = true)
public class TestConfiguration implements IConfig {
  private final String text;
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof TestConfiguration))
      return false; 
    TestConfiguration other = (TestConfiguration)o;
    if (!other.canEqual(this))
      return false; 
    Object this$text = getText(), other$text = other.getText();
    return !((this$text == null) ? (other$text != null) : !this$text.equals(other$text));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof TestConfiguration;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $text = getText();
    return result * 59 + (($text == null) ? 43 : $text.hashCode());
  }
  
  public String toString() {
    return "TestConfiguration(text=" + getText() + ")";
  }
  
  public TestConfiguration(String text) {
    this.text = text;
  }
  
  public String getText() {
    return this.text;
  }
  
  public TestConfiguration() {
    this.text = null;
  }
  
  public boolean isValid() {
    if (this.text == null) {
      ConfigurationLogger.error("Invalid configuration: text is null");
      return false;
    } 
    return true;
  }
  
  public void onLoaded() {}
  
  public void onReloaded() {}
  
  public void onFailed() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\config\TestConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */