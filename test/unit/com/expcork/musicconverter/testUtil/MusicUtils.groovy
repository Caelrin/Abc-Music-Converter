package com.expcork.musicconverter.testUtil

class MusicUtils {
	def static abcSimpleSong = "dff cee|def gfe"
	def static abcSimpleExample = """X:1
T:Song Title
M:4/4
L:1/4
K:C
C:Composer
${abcSimpleSong}
	"""
	
	def static abcMissingAllButTitleExample = """X:1
	T:Missing Song Title
	${abcSimpleSong}
	"""
		
	def static commonTimeExample = """X:1
T:Song Title
M:C
L:1/4
K:C
C:Composer
${abcSimpleSong}
"""
	
	def static createListOfNotesFromC3toB7() {
		def expectedNotes = []
		[3, 4, 5, 6, 7].each {octave ->
			['a', 'b', 'c', 'd', 'e', 'f', 'g'].each {pitch ->
				if(octave == 3) {
					if(pitch == 'a' || pitch == 'b') {
						return
					}
				}
				if(octave == 7) {
					if(pitch != 'a' && pitch != 'b') {
						return
					}
				}
				expectedNotes.add( "${pitch}${octave}")
			}
		}
		
		expectedNotes
	}

}
