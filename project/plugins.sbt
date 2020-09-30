logLevel := Level.Warn

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += "synergian-repo" at "https://raw.github.com/synergian/wagon-git/releases"

resolvers += Resolver.mavenLocal

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.8")

addSbtPlugin("com.typesafe.sbt" % "sbt-web" % "1.2.2")

libraryDependencies += "ar.com.synergian" % "wagon-git" % "0.2.5"