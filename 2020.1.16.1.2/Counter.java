class Counter {

  private static long reloadTime;
  private static long gracePeriodTime;
  
  public long getReloadTime(){
    return reloadTime;
  }
  
  public void setReloadTime(long reloadTime){
    this.reloadTime = reloadTime;
  }
  
  public long getGracePeriodTime(){
    return gracePeriodTime;
  }
  
  public void setGracePeriodTime(long gracePeriodTime){
    this.gracePeriodTime = gracePeriodTime;
  }
  
}