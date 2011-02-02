package com.expcork.musicconverter.util

import grails.test.GrailsUnitTestCase
import org.junit.Test

class NoteLengthUtilTests extends GrailsUnitTestCase{

  @Test
  void imageNameFromLengthShouldReturnRightStyleForVariousNotes() {
    assert "eighthNote.png" == NoteLengthUtil.imageNameFromLength(8)
    assert "quarterNote.png" == NoteLengthUtil.imageNameFromLength(4)
    assert "dottedQuarterNote.png" == NoteLengthUtil.imageNameFromLength(2.6666666667D)
    assert "halfNote.png" == NoteLengthUtil.imageNameFromLength(2D)
    assert "wholeNote.png" == NoteLengthUtil.imageNameFromLength(1D)
  }

  @Test
  void modifyByAbcStringShouldCorrectlyIdentifyNotes() {
    assertEquals(2.66D, NoteLengthUtil.modifyByAbcString(8, "3"), 0.01D)
  }

}
