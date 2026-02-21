package fr.paladium.palajobs.core.registry;

import fr.paladium.palajobs.core.jobs.AbstractJob;
import fr.paladium.palajobs.core.jobs.type.AlchemistJob;
import fr.paladium.palajobs.core.jobs.type.FarmerJob;
import fr.paladium.palajobs.core.jobs.type.HunterJob;
import fr.paladium.palajobs.core.jobs.type.MinerJob;
import java.util.ArrayList;
import java.util.List;

public class JobRegistry {
  public void setJobs(List<AbstractJob> jobs) {
    this.jobs = jobs;
  }
  
  public void setAlchemistJob(AlchemistJob alchemistJob) {
    this.alchemistJob = alchemistJob;
  }
  
  public void setFarmerJob(FarmerJob farmerJob) {
    this.farmerJob = farmerJob;
  }
  
  public void setHunterJob(HunterJob hunterJob) {
    this.hunterJob = hunterJob;
  }
  
  public void setMinerJob(MinerJob minerJob) {
    this.minerJob = minerJob;
  }
  
  public List<AbstractJob> getJobs() {
    return this.jobs;
  }
  
  public AlchemistJob getAlchemistJob() {
    return this.alchemistJob;
  }
  
  public FarmerJob getFarmerJob() {
    return this.farmerJob;
  }
  
  public HunterJob getHunterJob() {
    return this.hunterJob;
  }
  
  public MinerJob getMinerJob() {
    return this.minerJob;
  }
  
  private List<AbstractJob> jobs = new ArrayList<>();
  
  private AlchemistJob alchemistJob;
  
  private FarmerJob farmerJob;
  
  private HunterJob hunterJob;
  
  private MinerJob minerJob;
  
  private static JobRegistry instance;
  
  public void registerJobs() {
    (this.farmerJob = FarmerJob.builder().build()).register();
    (this.hunterJob = HunterJob.builder().build()).register();
    (this.minerJob = MinerJob.builder().build()).register();
    (this.alchemistJob = AlchemistJob.builder().build()).register();
  }
  
  public static void register(AbstractJob job) {
    instance.getJobs().add(job);
  }
  
  public static JobRegistry getInstance() {
    if (instance == null)
      instance = new JobRegistry(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\registry\JobRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */