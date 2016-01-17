package com.vanniktech.android.apk.size

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

final class ApkSizeTools {
    static String convertBytesToMegaBytes(final long bytes) {
        return getDecimalFormat().format(bytes / 1000000)
    }

    static String convertBytesToKiloBytes(final long bytes) {
        return getDecimalFormat().format(bytes / 1000)
    }

    private static DecimalFormat getDecimalFormat() {
        return new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US))
    }

    private ApkSizeTools() {
        throw new AssertionError("No instances")
    }
}