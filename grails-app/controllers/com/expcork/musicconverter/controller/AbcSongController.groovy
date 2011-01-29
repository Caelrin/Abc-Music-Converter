package com.expcork.musicconverter.controller

import com.expcork.musicconverter.domain.AbcSong;
import com.expcork.musicconverter.util.NumberUtils;

class AbcSongController {
	
	def index = {
		
	}
	
	def create = {
		render(view: "create")
	}
	
	def save = {
		def abcSong = AbcSong.parseAbc(params.abcSong)
		abcSong.save()
		redirect(action: "view", params: [abcSongId: abcSong.id])
	}
	
	def view = {
		def abcSongId = NumberUtils.toLongOrNull(params.abcSongId)
		def abcSong = AbcSong.get(abcSongId)
		render(view: "view", model: [abcSong: abcSong])
	}

}
