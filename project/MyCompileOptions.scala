object MyCompileOptions {

  val optV3 = Seq(
    "-source:3.0",
    //  "-rewrite",
    "-Xmax-inlines",
    "127",
    "-deprecation",    // emit warning and location for usages of deprecated APIs
    "-explain",        // explain errors in more detail
    "-feature",        // emit warning and location for usages of features that should be imported explicitly
    "-indent",         // allow significant indentation.
    "-new-syntax",     // require `then` and `do` in control expressions.
    "-print-lines",    // show source code line numbers.
    "-unchecked",      // enable additional warnings where generated code depends on assumptions
    "-Ykind-projector" // allow `*` as wildcard to be compatible with kind projector (this is not lambda) Type Lambda like functionK? =>>
    //  "-Xfatal-warnings", // fail the compilation if there are any warnings
    //   "-Xmigration"       // warn about constructs whose behavior may have changed since version
  )

}
