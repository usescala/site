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
      case req @ Path(Seg(page :: Nil)) =>  page match {
        case "sponsors" => Scalate(req, "sponsors.jade",("page",page))
        case "meetings" => Scalate(req, "meetings.jade",("page",page))
        case "about" => Scalate(req, "about.jade",("page",page))
        case "alive" => ResponseString("alive")

        //explore pages
        case "hello-xml" => ResponseString("""<!DOCTYPE html>"""+ HelloXml().content.toString)
        case "hello-jade" => Scalate(req, "explore/hello-jade.jade")
        case "hello-jade-layout" => Scalate(req, "explore/hello-jade-layout.jade", ("layout","default.jade"))
        case "hello-ssp" => Scalate(req, "explore/hello-ssp.ssp")
        case "hello-mustache" => Scalate(req, "explore/hello-mustache.mustache",("name","Mustache"))
        case "hello-jade-markdown"  => Scalate(req, "explore/hello-jade-markdown.jade")

        case _ => Scalate(req, "home.ssp",("page",page))
      }
	 	case _ => Redirect("home")
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