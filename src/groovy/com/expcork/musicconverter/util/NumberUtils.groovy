package com.expcork.musicconverter.util

import java.text.NumberFormat
import java.text.DecimalFormat

class NumberUtils {

	def static toLongOrNull(string) {
		string.toLong()
	}

  def static toTwoDecimalString(number) {
    def decimalFormat = new DecimalFormat("0.00")
    "${decimalFormat.format(number)}"
  }
}
