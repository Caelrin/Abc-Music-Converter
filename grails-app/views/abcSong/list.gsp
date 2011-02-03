<head>
  <meta name="layout" content="main" />
</head>
<body>
  <div class="songList">
    <g:each in="${abcSongs}" var="song">
      <div class="song">
        <g:link controller="abcSong" action="view" params="[abcSongId: song.id]">${song.title}</g:link>
      </div>
    </g:each>
  </div>
</body>