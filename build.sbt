import sbt._
import Libs._
import MyCompileOptions.optV3

import scala.Seq
ThisBuild / resolvers += Resolver.mavenLocal
publishMavenStyle               := true
bspEnabled                      := false
scalaJSUseMainModuleInitializer := true

ThisBuild / scalaJSLinkerConfig ~= {
  _.withModuleKind(ModuleKind.ESModule)
}

Test / scalaJSLinkerConfig ~= {
  _.withModuleKind(ModuleKind.CommonJSModule)
}

addCommandAlias("rerun", "feRun ;backend/reStart")
val javart                      = "1.11"
lazy val supportedScalaVersions = List("3.1.1")
ThisBuild / scalaVersion  := "3.1.1"
inThisBuild {

  organization                := "com.odenzo"
  reStart / mainClass         := Some("com.odenzo.webapp.be.BEMain")
  reStart / javaOptions += "-Xmx2g"
  reStartArgs                 := Seq("-x")
  Test / fork                 := false // ScalaJS can't be forked
  Test / parallelExecution    := false
  Test / logBuffered          := false
  scalacOptions ++= Seq("-release", "11")
  Compile / parallelExecution := false
}
ThisBuild / scalacOptions :=
  (CrossVersion.partialVersion(scalaVersion.value) match {
    // case Some((2, n)) if n >= 13 => optsV213 ++ warningsV213 ++ lintersV213
    case Some((3, _)) => optV3 // ++ lintersV3
    case _            => optV3 // ++ lintersV3
  })

lazy val root = project
  .in(file("."))
  .aggregate(frontend.js)
  .settings(name := "calico-ui-project", crossScalaVersions := supportedScalaVersions, doc / aggregate := false)

lazy val frontend = (crossProject(JSPlatform).crossType(CrossType.Pure) in (file("app/frontend")))
  .settings(
    name                            := "frontend",
    // mainClass                       := Some("com.odenzo.investing.fe.LaminarMainIO"),
    // scalaJSMainModuleInitializer    := ModuleIniSome("com.odenzo.investing.fe.LaminarMainIO"),
    scalaJSUseMainModuleInitializer := true,
    //  libraryDependencies ++= Seq(XLib.http4sDomClient.value),
    libraryDependencies ++= Seq(JSLibs.laminar.value),
    libraryDependencies ++= Seq(JSLibs.calico.value)
  )
  .settings(
    libraryDependencies ++=
      Seq(
        XLib.cats.value,
        XLib.catsEffect.value,
        // XLib.catsCollections.value,
        //   XLib.fs2.value,
        //   XLib.monocle.value,
        XLib.pprint.value,
        XLib.scribe.value,
        //   XLib.circeCore.value,
        //   XLib.circeGeneric.value,
        //   XLib.circeParser.value,
        //   XLib.circePointer.value,
        //   XLib.http4sClient.value,
        //   XLib.scodecBits.value,
        "org.scala-js" %%% "scalajs-dom" % "2.1.0" // Laminar 2.0 and Calico 2.1.0
      )
  )

//
lazy val feRun = taskKey[Unit]("Copy JS to Resources and Run WebApp")
feRun := {
  (Compile / compile).value
  (frontend.js / Compile / fastLinkJS).value
  val js    = (frontend.js / Compile / crossTarget).value / "frontend-fastopt" / "main.js"
  val jsmap = (frontend.js / Compile / crossTarget).value / "frontend-fastopt" / "main.js.map"

}
