package androidx.core.os;

import android.os.Build;
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.Size;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

/* access modifiers changed from: package-private */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class LocaleListHelper {
    private static final Locale EN_LATN = LocaleHelper.forLanguageTag("en-Latn");
    private static final Locale LOCALE_AR_XB = new Locale("ar", "XB");
    private static final Locale LOCALE_EN_XA = new Locale("en", "XA");
    private static final int NUM_PSEUDO_LOCALES = 2;
    private static final String STRING_AR_XB = "ar-XB";
    private static final String STRING_EN_XA = "en-XA";
    @GuardedBy("sLock")
    private static LocaleListHelper sDefaultAdjustedLocaleList = null;
    @GuardedBy("sLock")
    private static LocaleListHelper sDefaultLocaleList = null;
    private static final Locale[] sEmptyList = new Locale[0];
    private static final LocaleListHelper sEmptyLocaleList = new LocaleListHelper(new Locale[0]);
    @GuardedBy("sLock")
    private static Locale sLastDefaultLocale = null;
    @GuardedBy("sLock")
    private static LocaleListHelper sLastExplicitlySetLocaleList = null;
    private static final Object sLock = new Object();
    private final Locale[] mList;
    @NonNull
    private final String mStringRepresentation;

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Locale get(int i) {
        if (i >= 0) {
            Locale[] localeArr = this.mList;
            if (i < localeArr.length) {
                return localeArr[i];
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isEmpty() {
        return this.mList.length == 0;
    }

    /* access modifiers changed from: package-private */
    @IntRange(from = 0)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int size() {
        return this.mList.length;
    }

    /* access modifiers changed from: package-private */
    @IntRange(from = -1)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int indexOf(Locale locale) {
        int i = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i >= localeArr.length) {
                return -1;
            }
            if (localeArr[i].equals(locale)) {
                return i;
            }
            i++;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocaleListHelper)) {
            return false;
        }
        Locale[] localeArr = ((LocaleListHelper) obj).mList;
        if (this.mList.length != localeArr.length) {
            return false;
        }
        int i = 0;
        while (true) {
            Locale[] localeArr2 = this.mList;
            if (i >= localeArr2.length) {
                return true;
            }
            if (!localeArr2[i].equals(localeArr[i])) {
                return false;
            }
            i++;
        }
    }

    public int hashCode() {
        int i = 1;
        int i2 = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i2 >= localeArr.length) {
                return i;
            }
            i = (i * 31) + localeArr[i2].hashCode();
            i2++;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int i = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i < localeArr.length) {
                sb.append(localeArr[i]);
                if (i < this.mList.length - 1) {
                    sb.append(',');
                }
                i++;
            } else {
                sb.append("]");
                return sb.toString();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public String toLanguageTags() {
        return this.mStringRepresentation;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    LocaleListHelper(@NonNull Locale... localeArr) {
        if (localeArr.length == 0) {
            this.mList = sEmptyList;
            this.mStringRepresentation = "";
            return;
        }
        Locale[] localeArr2 = new Locale[localeArr.length];
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < localeArr.length; i++) {
            Locale locale = localeArr[i];
            if (locale == null) {
                throw new NullPointerException("list[" + i + "] is null");
            } else if (!hashSet.contains(locale)) {
                Locale locale2 = (Locale) locale.clone();
                localeArr2[i] = locale2;
                sb.append(LocaleHelper.toLanguageTag(locale2));
                if (i < localeArr.length - 1) {
                    sb.append(',');
                }
                hashSet.add(locale2);
            } else {
                throw new IllegalArgumentException("list[" + i + "] is a repetition");
            }
        }
        this.mList = localeArr2;
        this.mStringRepresentation = sb.toString();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    LocaleListHelper(@NonNull Locale locale, LocaleListHelper localeListHelper) {
        int i;
        if (locale != null) {
            if (localeListHelper == null) {
                i = 0;
            } else {
                i = localeListHelper.mList.length;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    i2 = -1;
                    break;
                } else if (locale.equals(localeListHelper.mList[i2])) {
                    break;
                } else {
                    i2++;
                }
            }
            int i3 = (i2 == -1 ? 1 : 0) + i;
            Locale[] localeArr = new Locale[i3];
            localeArr[0] = (Locale) locale.clone();
            if (i2 == -1) {
                int i4 = 0;
                while (i4 < i) {
                    int i5 = i4 + 1;
                    localeArr[i5] = (Locale) localeListHelper.mList[i4].clone();
                    i4 = i5;
                }
            } else {
                int i6 = 0;
                while (i6 < i2) {
                    int i7 = i6 + 1;
                    localeArr[i7] = (Locale) localeListHelper.mList[i6].clone();
                    i6 = i7;
                }
                for (int i8 = i2 + 1; i8 < i; i8++) {
                    localeArr[i8] = (Locale) localeListHelper.mList[i8].clone();
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i9 = 0; i9 < i3; i9++) {
                sb.append(LocaleHelper.toLanguageTag(localeArr[i9]));
                if (i9 < i3 - 1) {
                    sb.append(',');
                }
            }
            this.mList = localeArr;
            this.mStringRepresentation = sb.toString();
            return;
        }
        throw new NullPointerException("topLocale is null");
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static LocaleListHelper getEmptyLocaleList() {
        return sEmptyLocaleList;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static LocaleListHelper forLanguageTags(@Nullable String str) {
        if (str == null || str.isEmpty()) {
            return getEmptyLocaleList();
        }
        String[] split = str.split(",", -1);
        Locale[] localeArr = new Locale[split.length];
        for (int i = 0; i < localeArr.length; i++) {
            localeArr[i] = LocaleHelper.forLanguageTag(split[i]);
        }
        return new LocaleListHelper(localeArr);
    }

    private static String getLikelyScript(Locale locale) {
        if (Build.VERSION.SDK_INT < 21) {
            return "";
        }
        String script = locale.getScript();
        return !script.isEmpty() ? script : "";
    }

    private static boolean isPseudoLocale(String str) {
        return STRING_EN_XA.equals(str) || STRING_AR_XB.equals(str);
    }

    private static boolean isPseudoLocale(Locale locale) {
        return LOCALE_EN_XA.equals(locale) || LOCALE_AR_XB.equals(locale);
    }

    @IntRange(from = 0, to = 1)
    private static int matchScore(Locale locale, Locale locale2) {
        if (locale.equals(locale2)) {
            return 1;
        }
        if (!locale.getLanguage().equals(locale2.getLanguage()) || isPseudoLocale(locale) || isPseudoLocale(locale2)) {
            return 0;
        }
        String likelyScript = getLikelyScript(locale);
        if (!likelyScript.isEmpty()) {
            return likelyScript.equals(getLikelyScript(locale2)) ? 1 : 0;
        }
        String country = locale.getCountry();
        if (country.isEmpty() || country.equals(locale2.getCountry())) {
            return 1;
        }
        return 0;
    }

    private int findFirstMatchIndex(Locale locale) {
        int i = 0;
        while (true) {
            Locale[] localeArr = this.mList;
            if (i >= localeArr.length) {
                return Integer.MAX_VALUE;
            }
            if (matchScore(locale, localeArr[i]) > 0) {
                return i;
            }
            i++;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
        if (r6 < Integer.MAX_VALUE) goto L_0x0021;
     */
    private int computeFirstMatchIndex(Collection<String> collection, boolean z) {
        int i;
        Locale[] localeArr = this.mList;
        if (localeArr.length == 1) {
            return 0;
        }
        if (localeArr.length == 0) {
            return -1;
        }
        if (z) {
            i = findFirstMatchIndex(EN_LATN);
            if (i == 0) {
                return 0;
            }
        }
        i = Integer.MAX_VALUE;
        for (String str : collection) {
            int findFirstMatchIndex = findFirstMatchIndex(LocaleHelper.forLanguageTag(str));
            if (findFirstMatchIndex == 0) {
                return 0;
            }
            if (findFirstMatchIndex < i) {
                i = findFirstMatchIndex;
            }
        }
        if (i == Integer.MAX_VALUE) {
            return 0;
        }
        return i;
    }

    private Locale computeFirstMatch(Collection<String> collection, boolean z) {
        int computeFirstMatchIndex = computeFirstMatchIndex(collection, z);
        if (computeFirstMatchIndex == -1) {
            return null;
        }
        return this.mList[computeFirstMatchIndex];
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Locale getFirstMatch(String[] strArr) {
        return computeFirstMatch(Arrays.asList(strArr), false);
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getFirstMatchIndex(String[] strArr) {
        return computeFirstMatchIndex(Arrays.asList(strArr), false);
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Locale getFirstMatchWithEnglishSupported(String[] strArr) {
        return computeFirstMatch(Arrays.asList(strArr), true);
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getFirstMatchIndexWithEnglishSupported(Collection<String> collection) {
        return computeFirstMatchIndex(collection, true);
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getFirstMatchIndexWithEnglishSupported(String[] strArr) {
        return getFirstMatchIndexWithEnglishSupported(Arrays.asList(strArr));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static boolean isPseudoLocalesOnly(@Nullable String[] strArr) {
        if (strArr == null) {
            return true;
        }
        if (strArr.length > 3) {
            return false;
        }
        for (String str : strArr) {
            if (!(str.isEmpty() || isPseudoLocale(str))) {
                return false;
            }
        }
        return true;
    }

    @NonNull
    @Size(min = 1)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static LocaleListHelper getDefault() {
        Locale locale = Locale.getDefault();
        synchronized (sLock) {
            if (!locale.equals(sLastDefaultLocale)) {
                sLastDefaultLocale = locale;
                if (sDefaultLocaleList == null || !locale.equals(sDefaultLocaleList.get(0))) {
                    sDefaultLocaleList = new LocaleListHelper(locale, sLastExplicitlySetLocaleList);
                    sDefaultAdjustedLocaleList = sDefaultLocaleList;
                } else {
                    return sDefaultLocaleList;
                }
            }
            return sDefaultLocaleList;
        }
    }

    @NonNull
    @Size(min = 1)
    static LocaleListHelper getAdjustedDefault() {
        LocaleListHelper localeListHelper;
        getDefault();
        synchronized (sLock) {
            localeListHelper = sDefaultAdjustedLocaleList;
        }
        return localeListHelper;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static void setDefault(@NonNull @Size(min = 1) LocaleListHelper localeListHelper) {
        setDefault(localeListHelper, 0);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static void setDefault(@NonNull @Size(min = 1) LocaleListHelper localeListHelper, int i) {
        if (localeListHelper == null) {
            throw new NullPointerException("locales is null");
        } else if (!localeListHelper.isEmpty()) {
            synchronized (sLock) {
                sLastDefaultLocale = localeListHelper.get(i);
                Locale.setDefault(sLastDefaultLocale);
                sLastExplicitlySetLocaleList = localeListHelper;
                sDefaultLocaleList = localeListHelper;
                if (i == 0) {
                    sDefaultAdjustedLocaleList = sDefaultLocaleList;
                } else {
                    sDefaultAdjustedLocaleList = new LocaleListHelper(sLastDefaultLocale, sDefaultLocaleList);
                }
            }
        } else {
            throw new IllegalArgumentException("locales is empty");
        }
    }
}
