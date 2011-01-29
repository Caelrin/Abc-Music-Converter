package com.expcork.musicconverter.music
;

import static org.junit.Assert.*;

import org.junit.Test;

import com.expcork.musicconverter.testUtil.MusicUtils;
import com.expcork.musicconverter.util.NoteLengthUtil;


import grails.test.GrailsUnitTestCase;

class NoteTest extends GrailsUnitTestCase{
	
	
	@Test
	void getNotesShouldReturnAllNotesFromC3ToB7() {
		def noteListToCreate = ["C,", "D,", "E,", "F,", "G,", "A,", "B,", "C", "D", "E", "F", "G", "A", "B", 
			"c", "d", "e", "f", "g", "a", "b", "c'", "d'", "e'", "f'", "g'", "a'", "b'"]
		def actualNotes = []

		noteListToCreate.each {
			actualNotes.add Note.createFromString(it, 4)
		}		
		
		def expectedNotes = MusicUtils.createListOfNotesFromC3toB7()
		expectedNotes.eachWithIndex { expectedNote, idx -> 
			assert expectedNote == actualNotes[idx].pitch
		}
	}

}
