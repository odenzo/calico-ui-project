//addSbtPlugin("com.codecommit"     % "sbt-github-packages"      % "0.5.3")
addSbtPlugin("com.github.sbt"     % "sbt-native-packager"      % "1.9.7")  //  https://github.com/sbt/sbt-native-packager
addSbtPlugin("org.scala-js"       % "sbt-scalajs"              % "1.10.0")
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.0.0")
addSbtPlugin("io.spray"           % "sbt-revolver"             % "0.9.1")  // https://github.com/spray/sbt-revolver
addSbtPlugin("com.eed3si9n"       % "sbt-assembly"             % "1.1.0")  // https://github.com/sbt/sbt-assembly
addSbtPlugin("de.gccc.sbt"        % "sbt-jib"                  % "0.10.0") // https://github.com/schmitch/sbt-jib

//addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.20.0") // NPM Bundling
//addSbtPlugin("ch.epfl.scala" % "sbt-web-scalajs-bundler" % "0.20.0") // to integrate with sbt-web instead of scalaxjs bunlder above
