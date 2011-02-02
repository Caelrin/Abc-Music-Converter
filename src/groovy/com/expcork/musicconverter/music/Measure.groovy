package com.expcork.musicconverter.music

import com.expcork.musicconverter.util.NoteLengthUtil;


class Measure {
	String noteString
	int defaultNoteLength
    Boolean repeatMeasure
    Boolean finalMeasure
    String differingRepeatString
	
	def getNotes() {
		def songStringMatcher = noteString =~ /([CDEFGABcdefgab][,\'']?)(\/?[0-9]*)?/
		def notes = []
		songStringMatcher.each {
			Double noteLength = NoteLengthUtil.modifyByAbcString(defaultNoteLength, it[2])
			notes.add(Note.createFromString(it[1], noteLength))
		}
		
		notes
	}

}
