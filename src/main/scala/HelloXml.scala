package usescala.site

import scala.xml._
import org.scala_tools.time.Imports._

case class HelloXml() {
	def content = {
		<html>
			<body>
			  <h1>{"Hello XML"}</h1>
        <p>{new java.util.Date()}</p>
		  </body>
    </html>
	}
}