package com.pushwoosh.inbox.ui.presentation.view.fragment;

import com.pushwoosh.inbox.data.InboxMessage;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "inboxMessage", "Lcom/pushwoosh/inbox/data/InboxMessage;", "invoke"}, k = 3, mv = {1, 1, 9})
/* compiled from: InboxFragment.kt */
final class InboxFragment$onViewCreated$4 extends Lambda implements Function1<InboxMessage, Unit> {
    final /* synthetic */ InboxFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InboxFragment$onViewCreated$4(InboxFragment inboxFragment) {
        super(1);
        this.this$0 = inboxFragment;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(InboxMessage inboxMessage) {
        invoke(inboxMessage);
        return Unit.INSTANCE;
    }

    public final void invoke(@Nullable InboxMessage inboxMessage) {
        if (inboxMessage != null) {
            InboxFragment.access$getInboxPresenter$p(this.this$0).onItemClick(inboxMessage);
        }
    }
}
