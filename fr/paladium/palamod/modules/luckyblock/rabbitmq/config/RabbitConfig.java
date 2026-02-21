package fr.paladium.palamod.modules.luckyblock.rabbitmq.config;

public class RabbitConfig {
  public void setHost(String host) {
    this.host = host;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public void setPort(int port) {
    this.port = port;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof RabbitConfig))
      return false; 
    RabbitConfig other = (RabbitConfig)o;
    if (!other.canEqual(this))
      return false; 
    if (getPort() != other.getPort())
      return false; 
    Object this$host = getHost(), other$host = other.getHost();
    if ((this$host == null) ? (other$host != null) : !this$host.equals(other$host))
      return false; 
    Object this$password = getPassword(), other$password = other.getPassword();
    if ((this$password == null) ? (other$password != null) : !this$password.equals(other$password))
      return false; 
    Object this$username = getUsername(), other$username = other.getUsername();
    return !((this$username == null) ? (other$username != null) : !this$username.equals(other$username));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof RabbitConfig;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + getPort();
    Object $host = getHost();
    result = result * 59 + (($host == null) ? 43 : $host.hashCode());
    Object $password = getPassword();
    result = result * 59 + (($password == null) ? 43 : $password.hashCode());
    Object $username = getUsername();
    return result * 59 + (($username == null) ? 43 : $username.hashCode());
  }
  
  public String toString() {
    return "RabbitConfig(host=" + getHost() + ", password=" + getPassword() + ", port=" + getPort() + ", username=" + getUsername() + ")";
  }
  
  public String getHost() {
    return this.host;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public int getPort() {
    return this.port;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  private String host = "localhost";
  
  private String password = "guest";
  
  private int port = 5672;
  
  private String username = "guest";
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\rabbitmq\config\RabbitConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */