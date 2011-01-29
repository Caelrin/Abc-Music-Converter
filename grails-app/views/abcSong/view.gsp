<div id="songInformation">
	<div id="songTitle">
		<label>Song Title: ${abcSong.title }</label>
	</div>
	<div id="songComposer">
		<label>Song Composer: ${abcSong.composer }</label>
	</div>
	<div id="songNotes">
		<g:each var="measure" in="${abcSong.measures }">
			<g:each var="note" in="${measure.notes }">
				${note.pitch}&nbsp;${note.length.style} ==
			</g:each>
			<br/>
		</g:each>
	</div>
</div>