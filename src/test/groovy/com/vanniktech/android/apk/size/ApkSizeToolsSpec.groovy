package com.vanniktech.android.apk.size

import spock.lang.Specification
import spock.lang.Unroll

final class ApkSizeToolsSpec extends Specification {
    def defaultLocale = Locale.default

    def 'cleanup'() {
        Locale.setDefault defaultLocale
    }

    @Unroll def 'convert bytes to mega bytes with #locale locale'() {
        given:
        Locale.setDefault locale

        expect:
        ApkSizeTools.convertBytesToMegaBytes(10000) == '0.01'
        ApkSizeTools.convertBytesToMegaBytes(655941390323) == '655941.39'
        ApkSizeTools.convertBytesToMegaBytes(594390323) == '594.39'
        ApkSizeTools.convertBytesToMegaBytes(75939284) == '75.94'
        ApkSizeTools.convertBytesToMegaBytes(4843893) == '4.84'
        ApkSizeTools.convertBytesToMegaBytes(843893) == '0.84'

        where:
        locale << Locale.availableLocales
    }

    @Unroll def 'convert bytes to kilo bytes with #locale locale'() {
        given:
        Locale.setDefault locale

        expect:
        ApkSizeTools.convertBytesToKiloBytes(7834843893) == '7834843.89'
        ApkSizeTools.convertBytesToKiloBytes(4843893) == '4843.89'
        ApkSizeTools.convertBytesToKiloBytes(654) == '0.65'
        ApkSizeTools.convertBytesToKiloBytes(22267) == '22.27'
        ApkSizeTools.convertBytesToKiloBytes(5643) == '5.64'

        where:
        locale << Locale.availableLocales
    }
}
