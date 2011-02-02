package com.expcork.musicconverter.util

import static com.expcork.musicconverter.util.NumberUtils.*

class NoteLengthUtil {
	
	def static modifyByAbcString(baseLength, abcString) {
      if(abcString == null || abcString.trim() == "") {return baseLength}
      def useDivide = (abcString[0] == "/")
      def numberRegex = abcString =~ /\/?([0-9]*)/
      def numberModifier = NumberUtils.toLongOrNull(numberRegex[0][1])
		if(useDivide) {
			baseLength * numberModifier
		} else {
			baseLength / numberModifier		
		}
		
	}

  def static imageNameFromLength(length) {
    def lengthAsString = toTwoDecimalString(length).toString()
    ["8.00": "eighthNote.png",
    "4.00": "quarterNote.png",
    "2.67": "dottedQuarterNote.png",
    "2.00": "halfNote.png",
    "1.00": "wholeNote.png"].get(lengthAsString)
  }
	
}
