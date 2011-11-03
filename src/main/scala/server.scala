package usescala.site

import org.fusesource.scalate.servlet.ServletTemplateEngine
import org.fusesource.scalate.layout.DefaultLayoutStrategy

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

  val engine = new ServletTemplateEngine()
  engine.layoutStrategy = new DefaultLayoutStrategy(engine,"templates/default.jade")

	val port = util.Properties.envOrElse("PORT", "8080").toInt
  unfiltered.jetty.Http(port)
   .context("/web") { _.resources(new java.net.URL(getClass().getResource("/web/css"), ".")) }
   .context("/web") { _.resources(new java.net.URL(getClass().getResource("/web/image"), ".")) }
   .filter(new Service).run
}