package com.vanniktech.android.apk.size

import org.junit.Test

import static org.junit.Assert.assertEquals

class ApkSizeToolsTest {
    @Test
    public void testConvertBytesToMegaBytes() {
        Locale defaultLocale = Locale.default;

        for (final Locale locale : Locale.availableLocales) {
            Locale.setDefault(locale);

            assertEquals("0.01", ApkSizeTools.convertBytesToMegaBytes(10000));
            assertEquals("655941.39", ApkSizeTools.convertBytesToMegaBytes(655941390323));
            assertEquals("594.39", ApkSizeTools.convertBytesToMegaBytes(594390323));
            assertEquals("75.94", ApkSizeTools.convertBytesToMegaBytes(75939284));
            assertEquals("4.84", ApkSizeTools.convertBytesToMegaBytes(4843893));
            assertEquals("0.84", ApkSizeTools.convertBytesToMegaBytes(843893));
        }

        Locale.setDefault(defaultLocale);
    }

    @Test
    public void testConvertBytesToKiloBytes() {
        Locale defaultLocale = Locale.default;

        for (final Locale locale : Locale.availableLocales) {
            Locale.setDefault(locale);

            assertEquals("7834843.89", ApkSizeTools.convertBytesToKiloBytes(7834843893));
            assertEquals("4843.89", ApkSizeTools.convertBytesToKiloBytes(4843893));
            assertEquals("0.65", ApkSizeTools.convertBytesToKiloBytes(654));
            assertEquals("22.27", ApkSizeTools.convertBytesToKiloBytes(22267));
            assertEquals("5.64", ApkSizeTools.convertBytesToKiloBytes(5643));
        }

        Locale.setDefault(defaultLocale);
    }
}