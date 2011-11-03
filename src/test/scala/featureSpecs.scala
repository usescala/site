package usescala.site

import dispatch._
import unfiltered.scalatest.jetty._
import unfiltered.jetty._
import unfiltered.scalatest.Hosted
import org.scalatest.{BeforeAndAfterAll, FeatureSpec, GivenWhenThen}

class FeaturesSpecTest extends FeatureSpec with Served with GivenWhenThen with BeforeAndAfterAll  {

  def setup = {
    _.filter(new Service())
  }

  override def beforeAll() {

  }

  override def afterAll() {
    h.shutdown()

  }

  val h = new dispatch.Http

  feature("REST API") {
    scenario("/alive") {
      println("host: " + host)
      h(host / "alive" >- { str =>
        assert(str === "alive")
      })
    }

    scenario("/hello-xml") {
      println("host: " + host)
      h(host / "hello-xml" >- { str =>
        assert(str.contains("XML"),"does not contain('XML')")
      })
    }

    scenario("/hello-ssp") {
      println("host: " + host)
      h(host / "hello-ssp" >- { str =>
        assert(str.contains("SSP"),"does not contain('SSP')")
      })
    }

    scenario("/hello-jade") {
      println("host: " + host)
      h(host / "hello-jade" >- { str =>
        assert(str.contains("Jade"),"does not contain('Jade')")
      })
    }
  }
}
