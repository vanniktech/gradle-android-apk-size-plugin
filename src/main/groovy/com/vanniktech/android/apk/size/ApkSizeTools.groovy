package com.vanniktech.android.apk.size

import java.text.DecimalFormat

final class ApkSizeTools {
    public static String convertBytesToMegaBytes(final long bytes) {
        return new DecimalFormat("#.##").format(bytes / 1000000);
    }

    public static String convertBytesToKiloBytes(final long bytes) {
        return new DecimalFormat("#.##").format(bytes / 1000);
    }

    private ApkSizeTools() {
        throw new AssertionError("No instances")
    }
}