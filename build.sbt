import AssemblyKeys._

name := "base-jetty-project"

organization := "thesmith"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.9.1"

resolvers ++= Seq(
  ScalaToolsSnapshots,
  "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository",
  "Typesafe repo" at "http://repo.typesafe.com/typesafe/releases/"
)

seq(assemblySettings: _*)

seq(webSettings :_*)

jarName in assembly := "app.jar"

test in assembly := {}

port in container.Configuration := 8080

resourceGenerators in Compile <+= (resourceManaged, baseDirectory) map { (managedBase, base) => 
  val webappBase = base / "src" / "main" / "webapp" 
  for { 
    (from, to) <- webappBase ** "*" x rebase(webappBase, managedBase / "main" / "webapp") 
  } yield { 
    Sync.copy(from, to) 
    to 
  } 
}

libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-server" % "8.0.3.v20111011",
  "org.eclipse.jetty" % "jetty-servlets" % "8.0.3.v20111011",
  "org.eclipse.jetty" % "jetty-webapp" % "8.0.3.v20111011" % "container,compile",
  "org.mortbay.jetty" % "servlet-api" % "3.0.pre4",
  "ch.qos.logback" % "logback-classic" % "0.9.29" % "runtime",
  "net.lag" % "configgy" % "2.0.0",
  "org.scala-tools.time" %% "time" % "0.5",
  "net.liftweb" %% "lift-json" % "2.4-M4",
  "org.scalatest" %% "scalatest" % "1.6.1" % "test",
  "junit" % "junit" % "4.8.2" % "test",
  "org.mockito" % "mockito-core" % "1.8.5" % "test"
)


