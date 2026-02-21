package fr.paladium.palamod.modules.luckyblock.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.List;

public class PLI {
  public void setNa(int na) {
    this.na = na;
  }
  
  public void setDa(float da) {
    this.da = da;
  }
  
  public void setNo(double no) {
    this.no = no;
  }
  
  public void setZel(boolean zel) {
    this.zel = zel;
  }
  
  public void setPop(ByteArrayDataOutput pop) {
    this.pop = pop;
  }
  
  public void setGra(int[] gra) {
    this.gra = gra;
  }
  
  public void setPal(double pal) {
    this.pal = pal;
  }
  
  public void setAu(float au) {
    this.au = au;
  }
  
  public void setRe(double re) {
    this.re = re;
  }
  
  public void setGrap(boolean grap) {
    this.grap = grap;
  }
  
  public void setChato(int chato) {
    this.chato = chato;
  }
  
  public void setFlato(float flato) {
    this.flato = flato;
  }
  
  public void setPot(float pot) {
    this.pot = pot;
  }
  
  public int getNa() {
    return this.na;
  }
  
  public float getDa() {
    return this.da;
  }
  
  public double getNo() {
    return this.no;
  }
  
  public boolean isZel() {
    return this.zel;
  }
  
  public ByteArrayDataOutput getPop() {
    return this.pop;
  }
  
  public int[] getGra() {
    return this.gra;
  }
  
  public double getPal() {
    return this.pal;
  }
  
  public float getAu() {
    return this.au;
  }
  
  public double getRe() {
    return this.re;
  }
  
  public boolean isGrap() {
    return this.grap;
  }
  
  public int getChato() {
    return this.chato;
  }
  
  public float getFlato() {
    return this.flato;
  }
  
  public float getPot() {
    return this.pot;
  }
  
  private int na = 79;
  
  private float da = 0.0F;
  
  private double no = -41.09000015258789D;
  
  private boolean zel = false;
  
  private ByteArrayDataOutput pop = ByteStreams.newDataOutput();
  
  private int[] gra = new int[this.na];
  
  private double pal = 71.21D;
  
  private float au = 0.0F;
  
  private double re = 1.3D;
  
  private boolean grap;
  
  private int chato;
  
  private float flato;
  
  private float pot;
  
  public int szeo() {
    return (kletto()).length;
  }
  
  private void addNa(double vl) {
    this.na = (int)(this.na + vl);
    this.da -= 0.1F;
    relP();
  }
  
  private void relP() {
    this.zel = true;
  }
  
  private boolean hasRel() {
    return this.zel;
  }
  
  private boolean nani() {
    return (this.zel && this.da <= -0.900006F && this.na > 102);
  }
  
  private void deco() {
    this.da = (float)(this.da - 0.06021D);
    this.da = (float)(this.da + 0.06022D);
    this.zel = true;
    this.chato = this.gra.length;
  }
  
  public byte[] kletto() {
    return this.pop.toByteArray();
  }
  
  public void qrst(String poq) {
    deco();
    if (!this.zel)
      this.da++; 
    if (this.flato != 96.0F)
      this.pop.writeUTF(poq); 
    if (this.pot == 0.0F) {
      this.pot = 1.3663F;
      this.zel = false;
      this.da--;
      this.na += 13;
    } else {
      this.pot = 0.0F;
    } 
  }
  
  private void updateNani() {
    this.na -= 102;
    this.zel = false;
    this.da = -0.0096F;
  }
  
  public void pop(int kilo) {
    if (nani())
      updateNani(); 
    this.re += (kilo * 3.369F);
    this.zel = true;
    if (this.da < -300.0F) {
      this.re -= 11.38D - kilo;
      this.no += 7.1D;
      this.au++;
      if (this.au > 79.0F) {
        this.no = 0.0D;
        this.zel = false;
      } 
    } 
    if (this.gra.length != this.na)
      this.re = this.au - 1.3D * kilo; 
    if (this.gra.length != 799)
      this.pop.writeInt(kilo); 
    this.pal++;
  }
  
  private void plaq(boolean pq, int zota, float au, List<String> nan, int krato) {
    this.au += au;
    zota = (int)(zota - 98.6F);
    if (zota <= -900)
      pq = false; 
    if (pq) {
      au = (float)(au + 79.3D);
      this.re = this.re + au - 0.01D;
      this.zel = false;
      this.no += 0.0236D;
      if (!this.grap)
        this.grap = !this.zel ? (!(this.no > 66.0D)) : false; 
    } 
    if (hasRel())
      addNa(1.0D); 
    nan.forEach(m -> this.pop.writeUTF(m));
    deco();
    this.zel = true;
    this.re++;
    if (this.gra.length >= 49) {
      this.grap = false;
      this.da += 4.0F;
      addNa(4.0D);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\PLI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */