package com.vanniktech.android.apk.size

import org.junit.Test

import static org.junit.Assert.assertEquals

class ApkSizeToolsTest {
    @Test
    public void testConvertBytesToMegaBytes() {
        assertEquals("0.01", ApkSizeTools.convertBytesToMegaBytes(10000));
        assertEquals("594.39", ApkSizeTools.convertBytesToMegaBytes(594390323));
        assertEquals("75.94", ApkSizeTools.convertBytesToMegaBytes(75939284));
        assertEquals("4.84", ApkSizeTools.convertBytesToMegaBytes(4843893));
        assertEquals("0.84", ApkSizeTools.convertBytesToMegaBytes(843893));
    }

    @Test
    public void testConvertBytesToKiloBytes() {
        assertEquals("4843.89", ApkSizeTools.convertBytesToKiloBytes(4843893));
        assertEquals("0.65", ApkSizeTools.convertBytesToKiloBytes(654));
        assertEquals("22.27", ApkSizeTools.convertBytesToKiloBytes(22267));
        assertEquals("5.64", ApkSizeTools.convertBytesToKiloBytes(5643));
    }
}