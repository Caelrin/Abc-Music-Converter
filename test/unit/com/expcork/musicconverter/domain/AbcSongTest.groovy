package com.expcork.musicconverter.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import com.expcork.musicconverter.music.NoteLength;
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
	
}
