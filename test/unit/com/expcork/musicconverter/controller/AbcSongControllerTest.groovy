package com.expcork.musicconverter.controller

import grails.test.ControllerUnitTestCase;
import static org.junit.Assert.*;
import static com.expcork.musicconverter.testUtil.MusicUtils.*
import org.junit.Test;
import com.expcork.musicconverter.domain.AbcSong;
import com.expcork.musicconverter.util.NoteLengthUtil;


import grails.test.GrailsUnitTestCase;

class AbcSongControllerTest extends ControllerUnitTestCase {
	
	@Test
	void saveShouldSaveIndividualAbcSong() {
		mockDomain(AbcSong)
		def controller = new AbcSongController()
		controller.params.abcSong = abcSimpleExample
		
		controller.save()
		
		assert "view" == controller.redirectArgs.action
		def abcSong = AbcSong.get(controller.redirectArgs.params.abcSongId)
		assert "Song Title" == abcSong.title
		assert "Composer" == abcSong.composer
		assert "C" == abcSong.key
		assert 4 == abcSong.topMeter
		assert 4 == abcSong.bottomMeter
		assert 4 == abcSong.noteLength
		assert abcSimpleSong == abcSong.songString
	}
	
	@Test
	void saveShouldSaveSuccessfullyWhenOnlyGivenTitleAndSong() {
		mockDomain(AbcSong)
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
		assert 4 == songDisplayed.measures[0].notes[0].length
	}

    @Test
    void saveShouldBeAbleToSavePaddyORaffertyCorrectly() {
		mockDomain(AbcSong)
		def controller = new AbcSongController()
		controller.params.abcSong = paddyORafferty

		controller.save()

		assert "view" == controller.redirectArgs.action
		def abcSong = AbcSong.get(controller.redirectArgs.params.abcSongId)
        def measures = abcSong.measures
        assert "dfe dBA" == measures[7].noteString
        assert true == measures[7].repeatMeasure
        assert false == measures[7].finalMeasure
        assert "1" == measures[7].differingRepeatString
      assert "dfe dcB" == measures[8].noteString
      assert false == measures[8].repeatMeasure
      assert true == measures[8].finalMeasure
      assert "2" == measures[8].differingRepeatString
    }

}
