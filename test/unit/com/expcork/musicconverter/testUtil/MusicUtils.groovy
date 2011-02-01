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

  def static paddyORafferty = """
  X:1
T:Paddy O'Rafferty
C:Trad.
M:6/8
K:D
dff cee|def gfe|dff cee|dfe dBA|dff cee|def gfe|faf gfe|1 dfe dBA:|2 dfe dcB|]
~A3 B3|gfe fdB|AFA B2c|dfe dcB|~A3 ~B3|efe efg|faf gfe|1 dfe dcB:|2 dfe dBA|]
fAA eAA|def gfe|fAA eAA|dfe dBA|fAA eAA|def gfe|faf gfe|dfe dBA:|"""
	
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
