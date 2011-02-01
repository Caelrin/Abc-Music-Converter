package com.expcork.musicconverter.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import com.expcork.musicconverter.util.NoteLengthUtil;

import static com.expcork.musicconverter.testUtil.MusicUtils.*


import grails.test.GrailsUnitTestCase;

class AbcSongTest extends GrailsUnitTestCase{
	
	@Test
	void parseAbcSongShouldCreateAbcSongWithProperInformation() {
		def key = new Key(key: "C")
		mockDomain(AbcSong)
		mockDomain(Key, [key])
		
		def abcSong = AbcSong.parseAbc(abcSimpleExample)
		
		assert "Song Title" == abcSong.title
		assert "Composer" == abcSong.composer
		assert key == abcSong.key
		assert 4 == abcSong.topMeter
		assert 4 == abcSong.bottomMeter
		assert 4 == abcSong.noteLength
		assert abcSimpleSong == abcSong.songString
	}
	
	@Test
	void parseAbcSongShouldCreateAbcSongWithProperInformationWhenModeIsCommonTime() {
		def key = new Key(key: "C")
		mockDomain(AbcSong)
		mockDomain(Key, [key])
		
		def abcSong = AbcSong.parseAbc(commonTimeExample)
		
		assert "Song Title" == abcSong.title
		assert "Composer" == abcSong.composer
		assert key == abcSong.key
		assert 4 == abcSong.topMeter
		assert 4 == abcSong.bottomMeter
		assert 4 == abcSong.noteLength
		assert abcSimpleSong == abcSong.songString
	}
	
	@Test
	void parseAbcSongShouldCreateAbcSongWithDefaultKeyMeterAndNoteLengthInformationWhenNotSet() {
		def key = new Key(key: "C")
		mockDomain(AbcSong)
		mockDomain(Key, [key])
		
		def abcSong = AbcSong.parseAbc(abcMissingAllButTitleExample)
		
		assert "Missing Song Title" == abcSong.title
		assert key == abcSong.key
		assert 4 == abcSong.topMeter
		assert 4 == abcSong.bottomMeter
		assert 8 == abcSong.noteLength
		assert abcSimpleSong == abcSong.songString
	}
	
	@Test
	void getMeasuresShouldReturnMeasuresWhenJustUsingSimplePipeDelimitation() {
		def abcSong = new AbcSong(noteLength: 4, songString: "a b c | d e f")
		
		def measures = abcSong.measures
		
		assert "a b c" == measures[0].noteString
		assert 4 == measures[0].defaultNoteLength
		assert "d e f" == measures[1].noteString
		assert 4 == measures[1].defaultNoteLength
	}
  
	@Test
	void getMeasuresShouldRecognizeRepeatMeasures() {
		def abcSong = new AbcSong(noteLength: 4, songString: "a b c :| d e f")

		def measures = abcSong.measures

		assert "a b c" == measures[0].noteString
		assert 4 == measures[0].defaultNoteLength
		assert "d e f" == measures[1].noteString
		assert 4 == measures[1].defaultNoteLength
        assert true == measures[0].repeatMeasure
	}

	@Test
	void getMeasuresShouldRecognizeEndMeasure() {
		def abcSong = new AbcSong(noteLength: 4, songString: "a b c | d e f|]")

		def measures = abcSong.measures

		assert "a b c" == measures[0].noteString
		assert 4 == measures[0].defaultNoteLength
		assert "d e f" == measures[1].noteString
		assert 4 == measures[1].defaultNoteLength
        assert true == measures[1].finalMeasure
	}

	@Test
	void getMeasuresShouldRecognizeRepeatEndMeasure() {
		def abcSong = new AbcSong(noteLength: 4, songString: "a b c | d e f:|]")

		def measures = abcSong.measures

		assert "a b c" == measures[0].noteString
		assert 4 == measures[0].defaultNoteLength
		assert "d e f" == measures[1].noteString
		assert 4 == measures[1].defaultNoteLength
        assert true == measures[1].finalMeasure
        assert true == measures[1].repeatMeasure
	}

	@Test
	void getMeasuresShouldRecognizeMultiRepeatParts() {
		def abcSong = new AbcSong(noteLength: 4, songString: "a b c |1 d e f:|2 e f F:|]")

		def measures = abcSong.measures

		assert "a b c" == measures[0].noteString
		assert 4 == measures[0].defaultNoteLength
      assert "" == measures[0].differingRepeatString
      assert false == measures[0].repeatMeasure
      assert false == measures[0].finalMeasure
      assert "d e f" == measures[1].noteString
      assert 4 == measures[1].defaultNoteLength
      assert true == measures[1].repeatMeasure
      assert "1" == measures[1].differingRepeatString
      assert "e f F" == measures[2].noteString
      assert 4 == measures[2].defaultNoteLength
      assert true == measures[2].repeatMeasure
      assert true == measures[2].finalMeasure
      assert "2" == measures[2].differingRepeatString
    }

}
