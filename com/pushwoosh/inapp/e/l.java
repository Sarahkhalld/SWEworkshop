package com.pushwoosh.inapp.e;

import com.pushwoosh.internal.network.PushRequest;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

public class l extends PushRequest<Void> {
    private String a;

    public l(String str) {
        this.a = str;
    }

    /* access modifiers changed from: protected */
    @Override // com.pushwoosh.internal.network.PushRequest
    public void buildParams(JSONObject jSONObject) throws JSONException {
        jSONObject.put("action", "show");
        jSONObject.put("code", this.a);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        jSONObject.put("timestampUTC", currentTimeMillis);
        jSONObject.put("timestampCurrent", ((long) (Calendar.getInstance().getTimeZone().getRawOffset() / 1000)) + currentTimeMillis);
    }

    @Override // com.pushwoosh.internal.network.PushRequest
    public String getMethod() {
        return "triggerInAppAction";
    }
}
