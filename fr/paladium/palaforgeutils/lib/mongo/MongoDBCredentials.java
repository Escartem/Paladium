package fr.paladium.palaforgeutils.lib.mongo;

public class MongoDBCredentials {
  private final String host;
  
  private final int port;
  
  private final String user;
  
  private final String password;
  
  public MongoDBCredentials(String host, int port, String user, String password) {
    this.host = host;
    this.port = port;
    this.user = user;
    this.password = password;
  }
  
  public String getHost() {
    return this.host;
  }
  
  public int getPort() {
    return this.port;
  }
  
  public String getUser() {
    return this.user;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public MongoDBCredentials() {
    this.host = "127.0.0.1";
    this.port = 27017;
    this.user = "";
    this.password = "";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\mongo\MongoDBCredentials.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */