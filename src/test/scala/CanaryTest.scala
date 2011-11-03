package usescala.site

import org.scalatest.{GivenWhenThen, FeatureSpec}

class CanaryTest extends FeatureSpec with GivenWhenThen {
  feature("This is a canary test") {
    scenario("Canary is alive") (assert(true))
  }
}