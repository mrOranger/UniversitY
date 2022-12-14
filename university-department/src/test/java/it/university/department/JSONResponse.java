package it.university.department;

import java.time.LocalDate;

import org.json.JSONException;
import org.json.JSONObject;

public final class JSONResponse {
	
	public final static JSONObject generateResponse(int code, String message) throws JSONException {
		return new JSONObject().put("code", code).put("message", message).put("date", LocalDate.now());
	}
	
}
