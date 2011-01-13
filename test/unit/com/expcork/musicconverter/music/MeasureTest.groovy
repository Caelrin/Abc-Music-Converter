package com.expcork.musicconverter.music

import static org.junit.Assert.*;
import org.junit.Test;
import static com.expcork.musicconverter.testUtil.MusicUtils.*
import grails.test.GrailsUnitTestCase;

class MeasureTest extends GrailsUnitTestCase{
	
	@Test
	void getNotesShouldReturnAllNotesFromC3ToB6WithSongNoteLength() {
		def measure = new Measure(noteString: "C, D, E, F, G, A, B, C D E F G A B c d e f g a b c' d' e' f' g' a' b'")
		
		def noteList = measure.notes
		
		def expectedNotes = createListOfNotesFromC3toB7()
		expectedNotes.eachWithIndex { expectedNote, idx -> 
			assert expectedNote == noteList[idx].pitch
			assert NoteLength.QUARTER_NOTE == noteList[idx].length
		}
	}

}
