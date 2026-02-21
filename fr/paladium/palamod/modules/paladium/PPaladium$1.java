package fr.paladium.palamod.modules.paladium;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

class null implements X509TrustManager {
  public X509Certificate[] getAcceptedIssuers() {
    return null;
  }
  
  public void checkClientTrusted(X509Certificate[] certs, String authType) {}
  
  public void checkServerTrusted(X509Certificate[] certs, String authType) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\PPaladium$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */