package jp.co.kbit.shared.http;

public class HttpResponse {
	private String strResBody;
	private int iStatus;
	
	HttpResponse(
			int iStatus
			,String strResBody
			)
	{
		this.iStatus = iStatus;
		this.strResBody = strResBody;
	}
	
	HttpResponse()
	{
		this.strResBody = "";
		this.iStatus = 999;
	}
	public String getstrResBody() {return this.strResBody;}
	
	public int getiStatus() {return this.iStatus;}
	
	public void setstrResBody(String strResBody) {this.strResBody = strResBody;}
	
	public void setiStatus(int iStatus) {this.iStatus = iStatus; }
	
}
