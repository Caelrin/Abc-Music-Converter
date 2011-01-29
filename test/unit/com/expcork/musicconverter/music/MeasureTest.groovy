package com.expcork.musicconverter.music

import static org.junit.Assert.*;
import org.junit.Test;

import com.expcork.musicconverter.util.NoteLengthUtil;

import static com.expcork.musicconverter.testUtil.MusicUtils.*
import grails.test.GrailsUnitTestCase;

class MeasureTest extends GrailsUnitTestCase{
	
	@Test
	void getNotesShouldReturnAllNotesFromC3ToB6WithSongNoteLength() {
		def measure = new Measure(noteString: "C, D, E, F, G, A, B, C D E F G A B c d e f g a b c' d' e' f' g' a' b'", defaultNoteLength: 4)
		
		def noteList = measure.notes
		
		def expectedNotes = createListOfNotesFromC3toB7()
		expectedNotes.eachWithIndex { expectedNote, idx -> 
			assert expectedNote == noteList[idx].pitch
			assert 4 == noteList[idx].length
		}
	}
	
	@Test
	void getNotesShouldUseDefaultNotLength() {
		def measure = new Measure(noteString: "C, D", defaultNoteLength: 8)
		
		def noteList = measure.notes
		
		assert 8 == noteList[0].length
	}
	
	@Test
	void getNotesShouldTranslateNotesDependingOnNumbersAfterNote() {
		def measure = new Measure(noteString: "C/4 C/2 C2 C4 C8", defaultNoteLength: 8)
		
		def noteList = measure.notes
		
		assert 32 == noteList[0].length
		assert 16 == noteList[1].length
		assert 4 == noteList[2].length
		assert 2 == noteList[3].length
		assert 1 == noteList[4].length
	}

}
