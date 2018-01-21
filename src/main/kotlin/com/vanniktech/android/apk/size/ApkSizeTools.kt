package com.vanniktech.android.apk.size

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object ApkSizeTools {
  fun convertBytesToMegaBytes(bytes: Long): String = getDecimalFormat().format(bytes / 1000000.toDouble())

  fun convertBytesToKiloBytes(bytes: Long): String = getDecimalFormat().format(bytes / 1000.toDouble())

  private fun getDecimalFormat(): DecimalFormat = DecimalFormat("#.##", DecimalFormatSymbols(Locale.US))
}
