package com.vanniktech.android.apk.size

import spock.lang.Specification
import spock.lang.Unroll

final class ApkSizeToolsSpec extends Specification {
  def defaultLocale = Locale.default
  def apkSizeTools = new ApkSizeTools()

  def "cleanup"() {
    Locale.setDefault(defaultLocale)
  }

  @Unroll "convert bytes to mega bytes with #locale locale"() {
    given:
    Locale.setDefault(locale)

    expect:
    apkSizeTools.convertBytesToMegaBytes(10000) == "0.01"
    apkSizeTools.convertBytesToMegaBytes(655941390323) == "655941.39"
    apkSizeTools.convertBytesToMegaBytes(594390323) == "594.39"
    apkSizeTools.convertBytesToMegaBytes(75939284) == "75.94"
    apkSizeTools.convertBytesToMegaBytes(4843893) == "4.84"
    apkSizeTools.convertBytesToMegaBytes(843893) == "0.84"

    where:
    locale << Locale.availableLocales
  }

  @Unroll "convert bytes to kilo bytes with #locale locale"() {
    given:
    Locale.setDefault(locale)

    expect:
    apkSizeTools.convertBytesToKiloBytes(7834843893) == "7834843.89"
    apkSizeTools.convertBytesToKiloBytes(4843893) == "4843.89"
    apkSizeTools.convertBytesToKiloBytes(654) == "0.65"
    apkSizeTools.convertBytesToKiloBytes(22267) == "22.27"
    apkSizeTools.convertBytesToKiloBytes(5643) == "5.64"

    where:
    locale << Locale.availableLocales
  }
}
