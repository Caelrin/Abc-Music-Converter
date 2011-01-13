import com.expcork.musicconverter.domain.Key;

class BootStrap {

    def init = { servletContext ->
		def cKey = new Key(key: "C")
		cKey.save()
		
    }
    def destroy = {
    }
}
