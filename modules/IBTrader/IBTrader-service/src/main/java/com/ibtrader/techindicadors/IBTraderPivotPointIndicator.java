package com.ibtrader.techindicadors;



public class IBTraderPivotPointIndicator {

	private double pivotPoint;
	private double pivotPointResistance1;
	private double pivotPointResistance2;
	private double pivotPointResistance3;
	private double pivotPointSupport1;
	private double pivotPointSupport2;
	private double pivotPointSupport3;
	
	
	public IBTraderPivotPointIndicator(double pivotPoint, double pivotPointResistance1, double pivotPointResistance2,
			double pivotPointResistance3, double pivotPointSupport1, double pivotPointSupport2,
			double pivotPointSupport3) {
		super();
		this.pivotPoint = pivotPoint;
		this.pivotPointResistance1 = pivotPointResistance1;
		this.pivotPointResistance2 = pivotPointResistance2;
		this.pivotPointResistance3 = pivotPointResistance3;
		this.pivotPointSupport1 = pivotPointSupport1;
		this.pivotPointSupport2 = pivotPointSupport2;
		this.pivotPointSupport3 = pivotPointSupport3;
	}
	public double getPivotPoint() {
		return pivotPoint;
	}
	public void setPivotPoint(double pivotPoint) {
		this.pivotPoint = pivotPoint;
	}
	public double getPivotPointResistance1() {
		return pivotPointResistance1;
	}
	public void setPivotPointResistance1(double pivotPointResistance1) {
		this.pivotPointResistance1 = pivotPointResistance1;
	}
	public double getPivotPointResistance2() {
		return pivotPointResistance2;
	}
	public void setPivotPointResistance2(double pivotPointResistance2) {
		this.pivotPointResistance2 = pivotPointResistance2;
	}
	public double getPivotPointResistance3() {
		return pivotPointResistance3;
	}
	public void setPivotPointResistance3(double pivotPointResistance3) {
		this.pivotPointResistance3 = pivotPointResistance3;
	}
	public double getPivotPointSupport1() {
		return pivotPointSupport1;
	}
	public void setPivotPointSupport1(double pivotPointSupport1) {
		this.pivotPointSupport1 = pivotPointSupport1;
	}
	public double getPivotPointSupport2() {
		return pivotPointSupport2;
	}
	public void setPivotPointSupport2(double pivotPointSupport2) {
		this.pivotPointSupport2 = pivotPointSupport2;
	}
	public double getPivotPointSupport3() {
		return pivotPointSupport3;
	}
	public void setPivotPointSupport3(double pivotPointSupport3) {
		this.pivotPointSupport3 = pivotPointSupport3;
	}
			
}
