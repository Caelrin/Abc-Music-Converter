package com.expcork.musicconverter.util



class NoteLengthUtil {
	
	def static modifyByAbcString(baseLength, abcString) {
		if(abcString == null || abcString.trim() == "") {return baseLength}
      println "${baseLength}>>>>>>>>>>>>>>>>>>>>>> " + abcString
      def useDivide = (abcString[0] == "/")
      def numberRegex = abcString =~ /\/?([0-9]*)/
      def numberModifier = NumberUtils.toLongOrNull(numberRegex[0][1])
		if(useDivide) {
			baseLength * numberModifier
		} else {
			baseLength / numberModifier		
		}
		
	}
	
}
