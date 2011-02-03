<%@ page import="com.expcork.musicconverter.util.KeySignatureUtil;" %>
<span class="musicInfo">
  <img src="../images/trebleCleff.png" alt="" class="trebleSignature"/>
  <span class="keySignature">
    <g:each var="keyNote" in="${KeySignatureUtil.createKeySignature(abcSong.key)}">
       <span class="${keyNote.note}-key" >
          <img src="../images/${keyNote.type}.png" alt=""/>
       </span>
    </g:each>
  </span>
  <span class="meter">
    ${abcSong.topMeter }
    ${abcSong.bottomMeter }
  </span>
</span>