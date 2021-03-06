package com.pushwoosh;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import com.pushwoosh.internal.platform.AndroidPlatformModule;
import com.pushwoosh.internal.preference.PreferenceArrayListValue;
import com.pushwoosh.internal.preference.PreferenceIntValue;
import com.pushwoosh.internal.preference.PreferenceValueFactory;
import com.pushwoosh.internal.utils.PWLog;
import com.pushwoosh.internal.utils.i;
import com.pushwoosh.repository.b;
import com.pushwoosh.repository.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class j extends AsyncTask<Void, Void, Void> {
    private final SharedPreferences a = AndroidPlatformModule.getPrefsProvider().provideDefault();
    private final c b;
    private final PreferenceValueFactory c;
    private final PreferenceArrayListValue<String> d;
    private final PreferenceIntValue e;

    public j(@NonNull Context context) {
        this.b = new c(context);
        this.c = new PreferenceValueFactory();
        this.d = this.c.buildPreferenceArrayListValue(this.a, "pushwoosh_showed_local_notificaions", 10);
        this.e = this.c.buildPreferenceIntValue(this.a, "pushwoosh_local_push_request_id", 0);
    }

    private void a() {
        if (this.e.get() != 0) {
            this.b.a(this.e.get());
            this.e.set(0);
        }
    }

    private void b() {
        if (!c()) {
            e();
            g();
        }
    }

    private boolean c() {
        return d().isEmpty() && this.d.get().isEmpty();
    }

    private Set<String> d() {
        SharedPreferences sharedPreferences = this.a;
        if (sharedPreferences == null) {
            return Collections.emptySet();
        }
        try {
            return new HashSet(sharedPreferences.getStringSet("pushwoosh_local_push_ids", new HashSet()));
        } catch (ClassCastException unused) {
            return new HashSet();
        }
    }

    private void e() {
        for (b bVar : f()) {
            this.b.a(bVar);
        }
    }

    private List<b> f() {
        Set<String> d2 = d();
        ArrayList arrayList = new ArrayList();
        for (String str : d2) {
            SharedPreferences sharedPreferences = this.a;
            long j = 0;
            if (sharedPreferences != null) {
                j = sharedPreferences.getLong("pushwoosh_local_push_trigger_at_millis_" + str, 0);
            }
            SharedPreferences sharedPreferences2 = this.a;
            arrayList.add(new b(Integer.parseInt(str), j, i.a(sharedPreferences2, "pushwoosh_local_push_bundle_" + str)));
        }
        this.a.edit().putStringSet("pushwoosh_local_push_ids", new HashSet()).apply();
        return arrayList;
    }

    private void g() {
        for (b bVar : h()) {
            this.b.b(bVar);
        }
    }

    private List<b> h() {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.d.get().iterator();
        while (it.hasNext()) {
            try {
                JSONObject jSONObject = new JSONObject(it.next());
                arrayList.add(new b(jSONObject.getInt("requestId"), jSONObject.getInt("notificationId"), jSONObject.getString("notificationTag")));
            } catch (JSONException e2) {
                PWLog.exception(e2);
            }
        }
        this.d.clear();
        return arrayList;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Void doInBackground(Void... voidArr) {
        a();
        b();
        return null;
    }
}
