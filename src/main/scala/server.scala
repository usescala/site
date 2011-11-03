package usescala.site

class Service extends unfiltered.filter.Plan {
	import unfiltered.request._
	import unfiltered.response._
	import unfiltered.scalate._
	import org.scala_tools.time.Imports._
	import dispatch.url
	
	def intent = {
		case Path(Seg("alive" :: Nil)) => ResponseString("alive")
	  case Path(Seg("hello-xml" :: Nil)) => ResponseString("""<!DOCTYPE html>"""+ HelloXml().content.toString)
	  case req @ Path(Seg("hello-jade" :: Nil)) => Scalate(req, "hello-jade.jade")
	  case req @ Path(Seg("hello-ssp" :: Nil)) => Scalate(req, "hello-ssp.ssp")
	 	case _ => ResponseString("Resource not found")		
	}
}

object Server extends App {
	val port = util.Properties.envOrElse("PORT", "8080").toInt
  unfiltered.jetty.Http(port).filter(new Service).run
}