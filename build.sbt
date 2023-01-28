import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

name := "courio-core"
ThisBuild / organization := "io.cour"
ThisBuild / version := "2.0.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.4"

ThisBuild / resolvers ++= Seq(
  Resolver.sonatypeOssRepos("releases"),
  Resolver.sonatypeOssRepos("snapshots")
).flatten
ThisBuild / scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

ThisBuild / publishTo := sonatypePublishToBundle.value
ThisBuild / sonatypeProfileName := "io.cour"
ThisBuild / publishMavenStyle := true
ThisBuild / licenses := Seq("MIT" -> url("https://github.com/courio/core/blob/master/LICENSE"))
ThisBuild / sonatypeProjectHosting := Some(xerial.sbt.Sonatype.GitHubHosting("courio", "core", "contact@courio.com"))
ThisBuild / homepage := Some(url("https://courio.com"))
ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/courio/core"),
    "scm:git@github.com:courio/core.git"
  )
)
ThisBuild / developers := List(
  Developer(id="darkfrog", name="Matt Hicks", email="matt@matthicks.com", url=url("http://matthicks.com"))
)

val spiceVersion = "0.0.9-SNAPSHOT"
val scarangoVersion = "3.8.1"

lazy val root = project.in(file("."))
  .aggregate(coreJS, coreJVM)
  .settings(
    publish := {},
    publishLocal := {}
  )

lazy val core = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("core"))
  .settings(
    name := "courio-core",
    libraryDependencies ++= Seq(
      "com.outr" %%% "spice-core" % spiceVersion,
      "com.outr" %%% "scarango-core" % scarangoVersion
    )
  )

lazy val coreJS = core.js
lazy val coreJVM = core.jvm
