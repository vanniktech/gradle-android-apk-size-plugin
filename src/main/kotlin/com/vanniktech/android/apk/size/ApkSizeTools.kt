package com.vanniktech.android.apk.size

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

private const val BYTES_TO_KILOBYTES = 1000.toDouble()
private const val BYTES_TO_MEGABYTRES = 1000000.toDouble()

object ApkSizeTools {
  fun convertBytesToMegaBytes(bytes: Long): String = getDecimalFormat().format(bytes / BYTES_TO_MEGABYTRES)

  fun convertBytesToKiloBytes(bytes: Long): String = getDecimalFormat().format(bytes / BYTES_TO_KILOBYTES)

  private fun getDecimalFormat(): DecimalFormat = DecimalFormat("#.##", DecimalFormatSymbols(Locale.US))
}
