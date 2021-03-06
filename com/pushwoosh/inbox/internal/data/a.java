package com.pushwoosh.inbox.internal.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.pushwoosh.inbox.data.InboxMessage;
import com.pushwoosh.inbox.data.InboxMessageType;
import java.util.Date;

public class a implements InboxMessage {
    private final b a;

    public a(b bVar) {
        this.a = bVar;
    }

    /* renamed from: a */
    public int compareTo(@NonNull InboxMessage inboxMessage) {
        if (inboxMessage instanceof a) {
            return this.a.compareTo(((a) inboxMessage).a);
        }
        return -1;
    }

    public b a() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        b bVar = this.a;
        return bVar != null ? bVar.equals(aVar.a) : aVar.a == null;
    }

    @Override // com.pushwoosh.inbox.data.InboxMessage
    @Nullable
    public String getBannerUrl() {
        return this.a.k();
    }

    @Override // com.pushwoosh.inbox.data.InboxMessage
    @NonNull
    public String getCode() {
        return this.a.a();
    }

    @Override // com.pushwoosh.inbox.data.InboxMessage
    @Nullable
    public String getImageUrl() {
        return this.a.h();
    }

    @Override // com.pushwoosh.inbox.data.InboxMessage
    @NonNull
    public String getMessage() {
        return this.a.g();
    }

    @Override // com.pushwoosh.inbox.data.InboxMessage
    @NonNull
    public Date getSendDate() {
        return com.pushwoosh.inbox.internal.c.a.a(this.a.d());
    }

    @Override // com.pushwoosh.inbox.data.InboxMessage
    @Nullable
    public String getTitle() {
        return this.a.f();
    }

    @Override // com.pushwoosh.inbox.data.InboxMessage
    @NonNull
    public InboxMessageType getType() {
        return com.pushwoosh.inbox.c.a.a(this.a.j());
    }

    public int hashCode() {
        b bVar = this.a;
        if (bVar != null) {
            return bVar.hashCode();
        }
        return 0;
    }

    @Override // com.pushwoosh.inbox.data.InboxMessage
    public boolean isActionPerformed() {
        return this.a.o();
    }

    @Override // com.pushwoosh.inbox.data.InboxMessage
    public boolean isRead() {
        return this.a.n();
    }
}
