package com.expcork.musicconverter.controller

import grails.test.ControllerUnitTestCase;
import static org.junit.Assert.*;
import static com.expcork.musicconverter.testUtil.MusicUtils.*
import org.junit.Test;
import com.expcork.musicconverter.domain.AbcSong;
import com.expcork.musicconverter.domain.Key;
import com.expcork.musicconverter.music.NoteLength;


import grails.test.GrailsUnitTestCase;

class AbcSongControllerTest extends ControllerUnitTestCase {
	
	@Test
	void saveShouldSaveIndividualAbcSong() {
		def key = new Key(key: "C")
		mockDomain(AbcSong)
		mockDomain(Key, [key])
		def controller = new AbcSongController()
		controller.params.abcSong = abcSimpleExample
		
		controller.save()
		
		assert "view" == controller.redirectArgs.action
		def abcSong = AbcSong.get(controller.redirectArgs.params.abcSongId)
		assert "Song Title" == abcSong.title
		assert "Composer" == abcSong.composer
		assert key == abcSong.key
		assert 4 == abcSong.topMeter
		assert 4 == abcSong.bottomMeter
		assert 4 == abcSong.noteLength
		assert abcSimpleSong == abcSong.songString
	}
	
	@Test
	void saveShouldSaveSuccessfullyWhenOnlyGivenTitleAndSong() {
		def key = new Key(key: "C")
		mockDomain(AbcSong)
		mockDomain(Key, [key])
		def controller = new AbcSongController()
		controller.params.abcSong = abcMissingAllButTitleExample
		
		controller.save()
		
		assert "view" == controller.redirectArgs.action
		def abcSong = AbcSong.get(controller.redirectArgs.params.abcSongId)
		assert "Missing Song Title" == abcSong.title
		assert abcSimpleSong == abcSong.songString
	}
	
	@Test
	void createShouldRenderCreateScreen() {
		def controller = new AbcSongController()
		
		controller.create()
		
		assert "create" == controller.renderArgs.view
		
	}
	
	@Test
	void viewShouldLoadSongAndHaveTranslatedNotes() {
		def song = new AbcSong(id: 10, songString: abcSimpleSong, noteLength: 4, title: "My random Title")
		mockDomain(AbcSong, [song])
		def controller = new AbcSongController()
		controller.params.abcSongId = "10"
		
		controller.view()
		
		assert "view" == controller.renderArgs.view
		def songDisplayed = controller.renderArgs.model.abcSong
		assert "My random Title" == songDisplayed.title
		assert "d5" == songDisplayed.measures[0].notes[0].pitch
		assert NoteLength.QUARTER_NOTE == songDisplayed.measures[0].notes[0].length
	}

}