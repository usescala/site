resolvers += "Web plugin repo" at "http://siasia.github.com/maven2"

resolvers += Classpaths.typesafeResolver

addSbtPlugin("com.typesafe.startscript" % "xsbt-start-script-plugin" % "0.3.0")

addSbtPlugin("com.github.siasia" % "xsbt-web-plugin" % "0.1.2")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.7.1")

addSbtPlugin("com.zentrope" %% "xsbt-scalate-precompile-plugin" % "1.6")