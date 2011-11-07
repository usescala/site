package usescala.site

import org.fusesource.scalate.servlet.ServletTemplateEngine
import org.fusesource.scalate.layout.DefaultLayoutStrategy
import org.fusesource.scalate.TemplateEngine
import unfiltered.scalate.Scalate

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
    case req @ Path(Seg("hello-jade-layout" :: Nil)) => Scalate(req, "hello-jade-layout.jade", ("layout","default.jade"))
	  case req @ Path(Seg("hello-ssp" :: Nil)) => Scalate(req, "hello-ssp.ssp")
    case req @ Path(Seg("default" :: Nil)) => Scalate(req, "default.jade")
    case req @ Path(Seg("sponsors" :: Nil)) => Scalate(req, "sponsors.jade")
    case req @ Path(Seg("meetings" :: Nil)) => Scalate(req, "meetings.jade")
    case req @ Path(Seg("hello-mustache" :: Nil)) => Scalate(req, "hello-mustache.mustache",("name","Mustache"))
    case req @ Path(Seg("hello-jade-markdown" :: Nil)) => Scalate(req, "hello-jade-markdown.jade")

    case req @ Path(Seg(Nil)) => Scalate(req, "home.ssp")
    /*case req @ Path(Seg("about" :: Nil)) => Scalate(req, "about.jade")

    case req @ Path(Seg("meeting" :: Nil)) => Scalate(req, "meeting.jade")
    */
	 	case _ => ResponseString("Resource not found")		
	}
}

object Server extends App {
	val port = util.Properties.envOrElse("PORT", "8080").toInt
  unfiltered.jetty.Http(port)
   .context("/web") { _.resources(new java.net.URL(getClass().getResource("/web/css"), ".")) }
   .context("/web") { _.resources(new java.net.URL(getClass().getResource("/web/image"), ".")) }
   .context("/web") { _.resources(new java.net.URL(getClass().getResource("/web/script"), ".")) }
   .filter(new Service).run
}