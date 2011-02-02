<%@ page import="com.expcork.musicconverter.util.NoteLengthUtil;" %>
<head>
  <meta name="layout" content="main"/>
</head>
<body>

<div id="songInformation">
  <div id="songTitle">
    <label>Song Title: ${abcSong.title}</label>
  </div>
  <div id="songComposer">
    <label>Song Composer: ${abcSong.composer}</label>
  </div>
  <div id="songNotes">
    <div class="musicLine">
      <span class="musicInfo">
        <img src="../images/trebleCleff.png" alt=""/>
      </span>
      <g:each var="measure" status="i" in="${abcSong.measures }">
              <span class="musicBar">
                <g:set var="notes" value="${measure.notes }"/>
                <g:set var="notesLength" value="${notes.size()}"/>
                <g:each var="note" in="${notes}">
                   <span class="${note.pitch}" style="width: ${90/notesLength -3}%">
                     <img src="../images/${NoteLengthUtil.imageNameFromLength(note.length)}" alt=""/>
                   </span>
                </g:each>

                <g:if test="${measure.repeatMeasure}">
                  <img src="../images/repeatIndicator.png" alt=""/>
                </g:if>
                <span class="measureEnds">
                  <g:if test="${measure.repeatMeasure || measure.finalMeasure}">
                    <img src="../images/finalMeasureIndicator.png" alt=""/>
                  </g:if>
                  <g:else>
                    <img src="../images/measureSeparator.png" alt=""/>
                  </g:else>
                </span>
              </span>
          <g:if test="${i != 0 && (i+1)%4 == 0}">
            </div>
             <div class="musicLine">
          </g:if>
        </g:each>
      </div>
    </div>
  </div>
</div>
</body>
