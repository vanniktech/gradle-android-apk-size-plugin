package com.vanniktech.android.apk.size

import spock.lang.Specification

final class ApkSizeToolsSpec extends Specification {
    Locale defaultLocale

    def 'setup'() {
        defaultLocale = Locale.default
    }

    def 'cleanup'() {
        Locale.setDefault(defaultLocale)
    }

    def 'convert bytes to mega bytes'() {
        expect:
        Locale.availableLocales.each { locale ->
            Locale.setDefault(locale)

            '0.01' == ApkSizeTools.convertBytesToMegaBytes(10000)
            '655941.39' == ApkSizeTools.convertBytesToMegaBytes(655941390323)
            '594.39' == ApkSizeTools.convertBytesToMegaBytes(594390323)
            '75.94' == ApkSizeTools.convertBytesToMegaBytes(75939284)
            '4.84' == ApkSizeTools.convertBytesToMegaBytes(4843893)
            '0.84' == ApkSizeTools.convertBytesToMegaBytes(843893)
        }
    }

    def 'convert bytes to kilo bytes'() {
        expect:
        Locale.availableLocales.each { locale ->
            Locale.setDefault(locale)

            '7834843.89' == ApkSizeTools.convertBytesToKiloBytes(7834843893)
            '4843.89' == ApkSizeTools.convertBytesToKiloBytes(4843893)
            '0.65' == ApkSizeTools.convertBytesToKiloBytes(654)
            '22.27' == ApkSizeTools.convertBytesToKiloBytes(22267)
            '5.64' == ApkSizeTools.convertBytesToKiloBytes(5643)
        }
    }
}
