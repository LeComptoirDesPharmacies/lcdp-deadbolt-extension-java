logLevel := Level.Warn

credentials += Credentials("Artifactory Realm", "lcdp.jfrog.io", System.getenv("JFROG_USERNAME"), System.getenv("JFROG_PASSWORD"))

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += "synergian-repo" at "https://raw.github.com/synergian/wagon-git/releases"

resolvers += "Artifactory dev" at "https://lcdp.jfrog.io/artifactory/sbt-dev/"

resolvers += "Artifactory prod" at "https://lcdp.jfrog.io/artifactory/sbt-release/"

resolvers += Resolver.mavenLocal

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.7")

addSbtPlugin("com.typesafe.sbt" % "sbt-web" % "1.4.4")