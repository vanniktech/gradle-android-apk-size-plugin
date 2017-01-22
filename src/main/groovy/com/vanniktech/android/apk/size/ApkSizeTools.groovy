package com.vanniktech.android.apk.size

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

final class ApkSizeTools {
    static String convertBytesToMegaBytes(long bytes) {
        return getDecimalFormat().format(bytes / 1000000)
    }

    static String convertBytesToKiloBytes(long bytes) {
        return getDecimalFormat().format(bytes / 1000)
    }

    static DecimalFormat getDecimalFormat() {
        return new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US))
    }
}
