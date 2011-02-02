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
    <g:each var="measure" in="${abcSong.measures }">
      <div class="musicBar">
        <g:each var="note" in="${measure.notes }">
           <span class="${note.pitch}">
             <img src="../images/quarterNote.png" alt=""/>
           </span>
        </g:each>
        ||
        <br/>
      </div>
    </g:each>
  </div>
</div>
</body>
