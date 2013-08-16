package com.naonworks.iptms.vo;

public class SbcIdleDBBean extends AbstractBean {
	@Override
	public String getName() {
		return "SbcIdleDBBean";
	}
	
	private int sbcId;
	private int	boardId;
	private String regDateTime;	
	private int subscriberCnt;
	private int sessionCnt;
	private int invCnt;
	private int regCnt;
	private double cpuPer;
	private double memPer;
	private double diskPer;
	private float trafficRxCnt;
	private float trafficTxCnt;
	
	
	public int getSbcId () {
		return sbcId;
	}
	public void setSbcId ( int sbcId ) {
		this.sbcId = sbcId;
	}
	/**
	 * @return the boardId
	 */
	public int getBoardId() {
		return boardId;
	}
	/**
	 * @param boardId the boardId to set
	 */
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getSubscriberCnt () {
		return subscriberCnt;
	}
	public void setSubscriberCnt ( int subscriberCnt ) {
		this.subscriberCnt = subscriberCnt;
	}
	public int getSessionCnt () {
		return sessionCnt;
	}
	public void setSessionCnt ( int sessionCnt ) {
		this.sessionCnt = sessionCnt;
	}
	public int getInvCnt () {
		return invCnt;
	}
	public void setInvCnt ( int invCnt ) {
		this.invCnt = invCnt;
	}
	public int getRegCnt () {
		return regCnt;
	}
	public void setRegCnt ( int regCnt ) {
		this.regCnt = regCnt;
	}
	public double getCpuPer () {
		return cpuPer;
	}
	public void setCpuPer ( double cpuPer ) {
		this.cpuPer = cpuPer;
	}
	public double getMemPer () {
		return memPer;
	}
	public void setMemPer ( double memPer ) {
		this.memPer = memPer;
	}
	public double getDiskPer() {
		return diskPer;
	}
	public void setDiskPer(double diskPer) {
		this.diskPer = diskPer;
	}	
	public float getTrafficRxCnt() {
		return trafficRxCnt;
	}
	public void setTrafficRxCnt(float trafficRxCnt) {
		this.trafficRxCnt = trafficRxCnt;
	}
	public float getTrafficTxCnt() {
		return trafficTxCnt;
	}
	public void setTrafficTxCnt(float trafficTxCnt) {
		this.trafficTxCnt = trafficTxCnt;
	}
	public String getRegDateTime () {
		return regDateTime;
	}
	public void setRegDateTime ( String regDateTime ) {
		this.regDateTime = regDateTime;
	}
}
