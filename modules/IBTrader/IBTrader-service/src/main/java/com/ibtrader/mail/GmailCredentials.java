package com.ibtrader.mail;


public class GmailCredentials {
	
	 public GmailCredentials(String userEmail, String clientId, String clientSecret, String accessToken,
				String refreshToken) {
			super();
			this.userEmail = userEmail;
			this.clientId = clientId;
			this.clientSecret = clientSecret;
			this.accessToken = accessToken;
			this.refreshToken = refreshToken;
		}
	 public GmailCredentials(String userEmail, String clientId, String clientSecret) {
			super();
			this.userEmail = userEmail;
			this.clientId = clientId;	
			this.clientSecret = clientSecret;
		}
	
    private String userEmail="";
   
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	private String clientId="";
    private String clientSecret="";
    private String accessToken="";
    private String refreshToken="";
}