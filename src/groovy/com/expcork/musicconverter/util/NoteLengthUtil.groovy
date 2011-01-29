package com.expcork.musicconverter.music

enum NoteLength {
	QUARTER_NOTE("quarterNote"),
	EIGTH_NOTE("eigthNote")
	
	private def NoteLength(style) {
		this.style = style
	}
	
	String style
	

}
