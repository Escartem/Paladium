package fr.paladium.pet.common.capture;

import java.util.LinkedList;
import java.util.List;

public class CaptureCategory {
  private final long duration;
  
  private final List<CaptureSection> sections;
  
  public long getDuration() {
    return this.duration;
  }
  
  public List<CaptureSection> getSections() {
    return this.sections;
  }
  
  public CaptureCategory(long duration) {
    this.duration = duration;
    this.sections = new LinkedList<>();
  }
  
  public CaptureCategory addSection(CaptureSection section) {
    this.sections.add(section);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\capture\CaptureCategory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */