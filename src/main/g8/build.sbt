scalaVersion := "$scala_version$"

name := "$name$"
organization := "$organization$"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
enablePlugins(ScalaNativePlugin)

libraryDependencies ++= Seq(
  "de.surfice" %%% "scalanative-cocoa-appkit" % "$scalanative_cocoa_version$"
)

// link with Cocoa
nativeLinkingOptions ++= Seq("-framework","Cocoa")

lazy val bundlePath     = settingKey[File]("Path to the target bundle directory")
lazy val xcodeBuildPath = settingKey[File]("Path to the Xcode build directory")
lazy val prepareBundle  = taskKey[File]("Prepares the app bundle directory (copies files from Xcode build")

bundlePath                 := (crossTarget in Compile).value / (name.value + ".app")
artifactPath in nativeLink := bundlePath.value / "Contents" / "MacOS" / name.value
xcodeBuildPath             := baseDirectory.value / "xcode" / "DerivedData" / "Build" / "Products" / "Debug" / (name.value + ".app")

// (artifactPath in nativeLink) seems to be ignored, so we're overriding the output using -o on the linker
nativeLinkingOptions ++= Seq("-o",(artifactPath in nativeLink).value.getAbsolutePath)

prepareBundle := {
  val binary = (artifactPath in nativeLink).value
  IO.createDirectory(binary.getParentFile)

  val base = xcodeBuildPath.value
  val tgt = bundlePath.value

  val files = ((base ** "*") --- (base / "Contents" / "_CodeSignature" ** "*")).get.tail.map( x =>
      (x, tgt / x.relativeTo(base).get.getPath) )

  IO.copy(files)
  //IO.copyDirectory(xcodeBuildPath.value, bundlePath.value)
  bundlePath.value
}

nativeLink in Compile := {
  (prepareBundle in nativeLink).value
  (nativeLink in Compile).value
}
