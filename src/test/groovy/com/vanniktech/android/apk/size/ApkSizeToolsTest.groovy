package com.vanniktech.android.apk.size

import org.junit.After
import org.junit.Before
import org.junit.Test

class ApkSizeToolsTest {
    private Locale defaultLocale

    @Before
    public void setUp() {
        defaultLocale = Locale.default
    }

    @After
    public void tearDown() {
        Locale.setDefault(defaultLocale)
    }

    @Test
    public void convertBytesToMegaBytes() {
        for (final Locale locale : Locale.availableLocales) {
            Locale.setDefault(locale)

            assert '0.01' == ApkSizeTools.convertBytesToMegaBytes(10000)
            assert '655941.39' == ApkSizeTools.convertBytesToMegaBytes(655941390323)
            assert '594.39' == ApkSizeTools.convertBytesToMegaBytes(594390323)
            assert '75.94' == ApkSizeTools.convertBytesToMegaBytes(75939284)
            assert '4.84' == ApkSizeTools.convertBytesToMegaBytes(4843893)
            assert '0.84' == ApkSizeTools.convertBytesToMegaBytes(843893)
        }
    }

    @Test
    public void convertBytesToKiloBytes() {
        for (final Locale locale : Locale.availableLocales) {
            Locale.setDefault(locale)

            assert '7834843.89' == ApkSizeTools.convertBytesToKiloBytes(7834843893)
            assert '4843.89' == ApkSizeTools.convertBytesToKiloBytes(4843893)
            assert '0.65' == ApkSizeTools.convertBytesToKiloBytes(654)
            assert '22.27' == ApkSizeTools.convertBytesToKiloBytes(22267)
            assert '5.64' == ApkSizeTools.convertBytesToKiloBytes(5643)
        }
    }
}