class Counter {

  private static long reloadTime;
  private static long gracePeriodTime;
  
  Counter(){
  
  }
  
  public static long getReloadTime(){
    return reloadTime;
  }
  
  public void setReloadTime(long reloadTime){
    this.reloadTime = reloadTime;
  }
  
  public static long getGracePeriodTime(){
    return gracePeriodTime;
  }
  
  public void setGracePeriodTime(long gracePeriodTime){
    this.gracePeriodTime = gracePeriodTime;
  }
  
}