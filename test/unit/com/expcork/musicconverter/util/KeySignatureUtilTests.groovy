package com.expcork.musicconverter.util

import grails.test.GrailsUnitTestCase
import org.junit.Test

class KeySignatureUtilTests extends GrailsUnitTestCase{

  @Test
  void createKeySignatureShouldWorkForCommonKeys() {
    assert [] == KeySignatureUtil.createKeySignature("C")
    assert [[note: "c5", type: "sharp"], [note: "f5", type: "sharp"]] == KeySignatureUtil.createKeySignature("D")
    assert [[note: "f5", type: "sharp"]] == KeySignatureUtil.createKeySignature("G")
  }
}
