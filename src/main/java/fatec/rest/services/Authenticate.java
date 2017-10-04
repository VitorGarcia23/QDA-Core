package fatec.rest.services;

import nl.flotsam.xeger.Xeger;

public class Authenticate {
	private String regex = "[A-Nh-z0-9]{50}[A-Xb-z0-9]{50}";
	private Xeger xeger;
	
	public Authenticate() {		
		xeger = new Xeger(regex);
	}
	
	public String getToken() {
		return xeger.generate();
	}
	
	public boolean authorize(String token) {
		if (token == null || token.trim() == "") {
			return false;
		}
		
		return token.matches(regex);
	}
}
