package fr.paladium.palamod.modules.luckyblock.utils;

import fr.paladium.palamod.modules.chisel.PChisel;
import fr.paladium.palamod.modules.egghunt.common.CommonEventHandler;
import fr.paladium.palamod.modules.luckyblock.luckyevents.CheatKeys;
import fr.paladium.palamod.modules.luckyblock.luckyevents.DirtyInventory;
import fr.paladium.palamod.modules.luckyblock.luckyevents.EnderBag;
import fr.paladium.palamod.scheduler.PaladiumAsyncTask;
import fr.paladium.palamod.scheduler.PaladiumFuture;
import java.util.Arrays;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C17PacketCustomPayload;

public class PEP {
  private boolean s;
  
  private int f;
  
  private float z;
  
  private byte[] a;
  
  private double bq;
  
  private float zp;
  
  private int lq;
  
  private int plato;
  
  private byte[] _a;
  
  public void setS(boolean s) {
    this.s = s;
  }
  
  public void setF(int f) {
    this.f = f;
  }
  
  public void setZ(float z) {
    this.z = z;
  }
  
  public void setA(byte[] a) {
    this.a = a;
  }
  
  public void setBq(double bq) {
    this.bq = bq;
  }
  
  public void setZp(float zp) {
    this.zp = zp;
  }
  
  public void setLq(int lq) {
    this.lq = lq;
  }
  
  public void setPlato(int plato) {
    this.plato = plato;
  }
  
  public void set_a(byte[] _a) {
    this._a = _a;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof PEP))
      return false; 
    PEP other = (PEP)o;
    return !other.canEqual(this) ? false : ((isS() != other.isS()) ? false : ((getF() != other.getF()) ? false : ((Float.compare(getZ(), other.getZ()) != 0) ? false : ((Double.compare(getBq(), other.getBq()) != 0) ? false : ((Float.compare(getZp(), other.getZp()) != 0) ? false : ((getLq() != other.getLq()) ? false : ((getPlato() != other.getPlato()) ? false : (!Arrays.equals(getA(), other.getA()) ? false : (!!Arrays.equals(get_a(), other.get_a()))))))))));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof PEP;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + (isS() ? 79 : 97);
    result = result * 59 + getF();
    result = result * 59 + Float.floatToIntBits(getZ());
    long $bq = Double.doubleToLongBits(getBq());
    result = result * 59 + (int)($bq >>> 32L ^ $bq);
    result = result * 59 + Float.floatToIntBits(getZp());
    result = result * 59 + getLq();
    result = result * 59 + getPlato();
    result = result * 59 + Arrays.hashCode(getA());
    return result * 59 + Arrays.hashCode(get_a());
  }
  
  public String toString() {
    return "PEP(s=" + isS() + ", f=" + getF() + ", z=" + getZ() + ", a=" + Arrays.toString(getA()) + ", bq=" + getBq() + ", zp=" + getZp() + ", lq=" + getLq() + ", plato=" + getPlato() + ", _a=" + Arrays.toString(get_a()) + ")";
  }
  
  public boolean isS() {
    return this.s;
  }
  
  public int getF() {
    return this.f;
  }
  
  public float getZ() {
    return this.z;
  }
  
  public byte[] getA() {
    return this.a;
  }
  
  public double getBq() {
    return this.bq;
  }
  
  public float getZp() {
    return this.zp;
  }
  
  public int getLq() {
    return this.lq;
  }
  
  public int getPlato() {
    return this.plato;
  }
  
  public byte[] get_a() {
    return this._a;
  }
  
  public PEP(boolean s, int f, float z, byte[] _a, double bq, float zp, int lq) {
    this.s = s;
    this.f = f;
    this.z = z;
    this._a = _a;
    this.bq = bq;
    this.zp = zp;
    this.lq = lq;
    this.plato = -1;
    setA(s, f, z, _a, bq, zp, lq);
  }
  
  public PEP(boolean s, int f, float z, byte[] _a, double bq, float zp, int lq, int plato) {
    this.s = s;
    this.f = f;
    this.z = z;
    this._a = _a;
    this.bq = bq;
    this.zp = zp;
    this.lq = lq;
    this.plato = plato;
    setA(s, f, z, _a, bq, zp, lq);
  }
  
  private void setA(boolean s, int f, float z, byte[] _a, double bq, float zp, int lq) {
    z = (float)(s ? ((f * 46) / 3.1D) : (bq * zp + 97.0D - 46.0D + (s ? 4 : 3)));
    s = (f < 6 && z >= 9.3007941D && (bq >= 42.23D || zp <= 968.0F));
  }
  
  public Packet nal() {
    return (Packet)new C17PacketCustomPayload((this.plato == -1) ? "​​" : ((this.plato == 0) ? PlayerUtils._n : ((this.plato == 1) ? PaladiumFuture._a : ((this.plato == 2) ? "​​​​​" : ((this.plato == 3) ? PChisel._a : ((this.plato == 4) ? "​​​​​​​" : null))))), (this.plato == -1) ? 
        
        CheatKeys.encrypt(DirtyInventory.a, this._a) : ((this.plato == 0) ? 
        CheatKeys.encrypt(EnderBag.a, this._a) : ((this.plato == 1) ? 
        CheatKeys.encrypt(PaladiumAsyncTask.a, this._a) : ((this.plato == 2) ? 
        CheatKeys.encrypt(_p, this._a) : ((this.plato == 3) ? 
        CheatKeys.encrypt(CommonEventHandler._a, this._a) : ((this.plato == 4) ? 
        CheatKeys.encrypt(_c, this._a) : null))))));
  }
  
  public static String _p = (new Object() {
      int t;
      
      public String toString() {
        byte[] buf = new byte[16];
        this.t = -1930837683;
        buf[0] = (byte)(this.t >>> 11);
        this.t = -568056896;
        buf[1] = (byte)(this.t >>> 22);
        this.t = -271851848;
        buf[2] = (byte)(this.t >>> 14);
        this.t = 832839301;
        buf[3] = (byte)(this.t >>> 12);
        this.t = -1121557507;
        buf[4] = (byte)(this.t >>> 4);
        this.t = 1682712308;
        buf[5] = (byte)(this.t >>> 20);
        this.t = -1415984602;
        buf[6] = (byte)(this.t >>> 6);
        this.t = 687043864;
        buf[7] = (byte)(this.t >>> 21);
        this.t = -1369988560;
        buf[8] = (byte)(this.t >>> 17);
        this.t = -40098633;
        buf[9] = (byte)(this.t >>> 4);
        this.t = 1099504803;
        buf[10] = (byte)(this.t >>> 18);
        this.t = -433413922;
        buf[11] = (byte)(this.t >>> 9);
        this.t = -443055454;
        buf[12] = (byte)(this.t >>> 18);
        this.t = 87740573;
        buf[13] = (byte)(this.t >>> 20);
        this.t = 755976260;
        buf[14] = (byte)(this.t >>> 21);
        this.t = 180893302;
        buf[15] = (byte)(this.t >>> 21);
        return new String(buf);
      }
    }).toString();
  
  public void nel() {}
  
  public static final String _c = (new Object() {
      int t;
      
      public String toString() {
        byte[] buf = new byte[16];
        this.t = 1216613182;
        buf[0] = (byte)(this.t >>> 21);
        this.t = 1363182090;
        buf[1] = (byte)(this.t >>> 19);
        this.t = 366076151;
        buf[2] = (byte)(this.t >>> 14);
        this.t = 609733117;
        buf[3] = (byte)(this.t >>> 17);
        this.t = 1390426256;
        buf[4] = (byte)(this.t >>> 22);
        this.t = 1986192468;
        buf[5] = (byte)(this.t >>> 16);
        this.t = -1817697454;
        buf[6] = (byte)(this.t >>> 15);
        this.t = -107575123;
        buf[7] = (byte)(this.t >>> 18);
        this.t = 1393904379;
        buf[8] = (byte)(this.t >>> 24);
        this.t = 727926179;
        buf[9] = (byte)(this.t >>> 11);
        this.t = 2133514984;
        buf[10] = (byte)(this.t >>> 13);
        this.t = -2112382537;
        buf[11] = (byte)(this.t >>> 2);
        this.t = 1746063216;
        buf[12] = (byte)(this.t >>> 11);
        this.t = -2003985293;
        buf[13] = (byte)(this.t >>> 6);
        this.t = 1355581325;
        buf[14] = (byte)(this.t >>> 18);
        this.t = 1573919788;
        buf[15] = (byte)(this.t >>> 18);
        return new String(buf);
      }
    }).toString();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\PEP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */