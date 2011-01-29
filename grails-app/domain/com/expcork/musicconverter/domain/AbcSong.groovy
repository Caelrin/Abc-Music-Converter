package com.expcork.musicconverter.domain

import com.expcork.musicconverter.music.Measure;
import com.expcork.musicconverter.music.Note;
import com.expcork.musicconverter.util.NoteLengthUtil;



class AbcSong {
	String title
	String composer
	Key key
	Integer topMeter
	Integer bottomMeter
	Integer noteLength
	String songString
	
	static constraints = {
		composer(nullable: true)
	}
	
	def getMeasures() {
		def measureMatcher = songString =~ /([^|]*)/
		def measures = []
		measureMatcher.each {
			if(it[0] != null && it[0].trim() != "") {
				measures.add new Measure(noteString: it[0].trim(), defaultNoteLength: noteLength)
			}
		}
		measures
	}
	
	def static headerMapping = [
		'T': {song, value -> song.title = value}, 
		'X': {song, value -> },
		'C': {song, value -> song.composer = value},
		'K': {song, value -> song.key = Key.findByKey(value)},
		'M': {song, value -> def valuesMatcher = value =~ /(.)\/?(.)?/
				def bottomNumber
				def topNumber = valuesMatcher[0][1]
				if(topNumber == 'C') {
					song.topMeter = 4
					song.bottomMeter = 4
				} else {
					song.topMeter = topNumber.toInteger()
					song.bottomMeter = valuesMatcher[0][2].toInteger()
				}
			},
		'L': {song, value -> def valuesMatcher = value =~ /(.)\/(.)/
			song.noteLength = valuesMatcher[0][2].toInteger()
		}
	]
	
	def static parseAbc(abcSongString) {
		def abcSong = new AbcSong()
		def matcher = abcSongString =~ /(\s*(.):(.*))|(.*)/
		matcher.each {
			def property = headerMapping[it[2]?.trim()]
			if(property) {
				property(abcSong, it[3])
			}
			if(it[4] != null && it[4].trim() != "") {
				abcSong.songString = it[4].trim()
			}
		}
		fillInMissingDefaults(abcSong)
		abcSong
	}
	
	def static fillInMissingDefaults(abcSong) {
		if(!abcSong.key) {
			abcSong.key = Key.findByKey('C')
		}
		if(!abcSong.topMeter) {
			abcSong.topMeter = 4
			abcSong.bottomMeter = 4
		}
		if(!abcSong.noteLength) {
			abcSong.noteLength = 8
		}
	}

}
