package com.expcork.musicconverter.music


class Measure {
	String noteString
	int defaultNoteLength
	
	def getNotes() {
		def songStringMatcher = noteString =~ /([CDEFGABcdefgab][,\']?)/
		def notes = []
		songStringMatcher.each {
			notes.add(Note.createFromString(it[1], NoteLength.QUARTER_NOTE))
		}
		
		notes
	}

}
