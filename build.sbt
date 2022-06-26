name := """playtoy"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "5.0.2",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.2",
  "mysql" % "mysql-connector-java" % "8.0.15",
)

libraryDependencies += "org.sangria-graphql" %% "sangria" % "2.0.0"
libraryDependencies += "org.sangria-graphql" %% "sangria-relay" % "2.0.0"
libraryDependencies += "org.sangria-graphql" %% "sangria-circe" % "1.3.2"
libraryDependencies += "org.sangria-graphql" %% "sangria-spray-json" % "1.0.2"
libraryDependencies += "org.sangria-graphql" %% "sangria-play-json" % "2.0.1"