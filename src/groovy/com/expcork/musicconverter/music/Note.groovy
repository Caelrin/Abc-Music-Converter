package com.expcork.musicconverter.music


class Note {
	String pitch
	Integer length
	
	def static pitchMap = [
		'C,' : "c3",
		'D,' : "d3",
		'E,' : "e3",
		'F,' : "f3",
		'G,' : "g3",
		'A,' : "a4",
		'B,' : "b4",
		'C' : "c4",
		'D' : "d4",
		'E' : "e4",
		'F' : "f4",
		'G' : "g4",
		'A' : "a5",
		'B' : "b5",
		'c' : "c5",
		'd' : "d5",
		'e' : "e5",
		'f' : "f5",
		'g' : "g5",
		'a' : "a6",
		'b' : "b6",
		'c\'' : "c6",
		'd\'' : "d6",
		'e\'' : "e6",
		'f\'' : "f6",
		'g\'' : "g6",
		'a\'' : "a7",
		'b\'' : "b7",
		]
	
	def static createFromString(noteString, noteLength) {
		def pitch = pitchMap[noteString]
		new Note(pitch: pitch, length: noteLength)
	}

}
