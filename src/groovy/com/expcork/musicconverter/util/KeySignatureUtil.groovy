package com.expcork.musicconverter.util

class KeySignatureUtil {

   def static keyMap = [
       "C": [],
       "D": [[note: "c5", type: "sharp"], [note: "f5", type: "sharp"]],
       "G": [[note: "f5", type: "sharp"]]
   ]

  def static createKeySignature(key) {
    keyMap[key]
  }
}
