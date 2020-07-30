This repo is a small example of what appears to be a bug in Bazel's kotlin rules. The output I get from running bazel is:

```
petter@MacBook-Pro ~/projects/codegen-repro (master)$ bazelisk build java/...
INFO: Analyzed target //java/com/pettermahlen:repro (0 packages loaded, 0 targets configured).
INFO: Found 1 target...
ERROR: /Users/petter/projects/codegen-repro/java/com/pettermahlen/BUILD.bazel:3:15: Compiling Kotlin to JVM //java/com/pettermahlen:repro { kt: 1, java: 0, srcjars: 0 } failed (Exit 2)
warning: some JAR files in the classpath have the Kotlin Runtime library bundled into them. This may cause difficult to debug problems if there's a different version of the Kotlin Runtime library in the classpath. Consider removing these libraries from the classpath
bazel-out/host/bin/external/maven/v1/https/repo1.maven.org/maven2/org/jetbrains/kotlin/kotlin-reflect/1.3.50/stamped_kotlin-reflect-1.3.50.jar: warning: library has Kotlin runtime bundled into it
bazel-out/host/bin/external/maven/v1/https/repo1.maven.org/maven2/org/jetbrains/kotlin/kotlin-stdlib/1.3.50/stamped_kotlin-stdlib-1.3.50.jar: warning: library has Kotlin runtime bundled into it
exception: java.lang.IllegalStateException: failed to analyze: java.lang.reflect.InvocationTargetException
	at org.jetbrains.kotlin.analyzer.AnalysisResult.throwIfError(AnalysisResult.kt:56)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler.compileModules$cli(KotlinToJVMBytecodeCompiler.kt:182)
	at org.jetbrains.kotlin.cli.jvm.K2JVMCompiler.doExecute(K2JVMCompiler.kt:164)
	at org.jetbrains.kotlin.cli.jvm.K2JVMCompiler.doExecute(K2JVMCompiler.kt:54)
	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:84)
	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:42)
	at org.jetbrains.kotlin.cli.common.CLITool.exec(CLITool.kt:104)
	at io.bazel.kotlin.compiler.BazelK2JVMCompiler.exec(BazelK2JVMCompiler.kt:30)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at io.bazel.kotlin.builder.toolchain.KotlinToolchain$KotlinCliToolInvoker.compile(KotlinToolchain.kt:130)
	at io.bazel.kotlin.builder.tasks.jvm.Compilation_taskKt$runPlugins$1$1$1.invoke(compilation_task.kt:110)
	at io.bazel.kotlin.builder.tasks.jvm.Compilation_taskKt$runPlugins$1$1$1.invoke(compilation_task.kt)
	at io.bazel.kotlin.builder.toolchain.CompilationTaskContext.executeCompilerTask(CompilationTaskContext.kt:122)
	at io.bazel.kotlin.builder.toolchain.CompilationTaskContext.executeCompilerTask$default(CompilationTaskContext.kt:118)
	at io.bazel.kotlin.builder.tasks.jvm.Compilation_taskKt$runPlugins$1.invoke(compilation_task.kt:108)
	at io.bazel.kotlin.builder.tasks.jvm.Compilation_taskKt$runPlugins$1.invoke(compilation_task.kt)
	at io.bazel.kotlin.builder.toolchain.CompilationTaskContext.execute(CompilationTaskContext.kt:146)
	at io.bazel.kotlin.builder.toolchain.CompilationTaskContext.execute(CompilationTaskContext.kt:138)
	at io.bazel.kotlin.builder.tasks.jvm.Compilation_taskKt.runPlugins(compilation_task.kt:103)
	at io.bazel.kotlin.builder.tasks.jvm.KotlinJvmTaskExecutor.execute(KotlinJvmTaskExecutor.kt:42)
	at io.bazel.kotlin.builder.tasks.KotlinBuilder.executeJvmTask(KotlinBuilder.kt:216)
	at io.bazel.kotlin.builder.tasks.KotlinBuilder.apply(KotlinBuilder.kt:119)
	at io.bazel.kotlin.builder.tasks.PersistentWorker.run(BazelWorker.kt:168)
	at io.bazel.kotlin.builder.tasks.BazelWorker.apply(BazelWorker.kt:74)
	at io.bazel.kotlin.builder.KotlinBuilderMain.main(KotlinBuilderMain.java:28)
Caused by: java.lang.reflect.InvocationTargetException
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.jetbrains.kotlin.kapt3.base.AnnotationProcessingKt.doAnnotationProcessing(annotationProcessing.kt:76)
	at org.jetbrains.kotlin.kapt3.base.AnnotationProcessingKt.doAnnotationProcessing$default(annotationProcessing.kt:35)
	at org.jetbrains.kotlin.kapt3.AbstractKapt3Extension.runAnnotationProcessing(Kapt3Extension.kt:230)
	at org.jetbrains.kotlin.kapt3.AbstractKapt3Extension.analysisCompleted(Kapt3Extension.kt:188)
	at org.jetbrains.kotlin.kapt3.ClasspathBasedKapt3Extension.analysisCompleted(Kapt3Extension.kt:99)
	at org.jetbrains.kotlin.cli.jvm.compiler.TopDownAnalyzerFacadeForJVM$analyzeFilesWithJavaIntegration$2.invoke(TopDownAnalyzerFacadeForJVM.kt:96)
	at org.jetbrains.kotlin.cli.jvm.compiler.TopDownAnalyzerFacadeForJVM.analyzeFilesWithJavaIntegration(TopDownAnalyzerFacadeForJVM.kt:106)
	at org.jetbrains.kotlin.cli.jvm.compiler.TopDownAnalyzerFacadeForJVM.analyzeFilesWithJavaIntegration$default(TopDownAnalyzerFacadeForJVM.kt:81)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler$analyze$1.invoke(KotlinToJVMBytecodeCompiler.kt:555)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler$analyze$1.invoke(KotlinToJVMBytecodeCompiler.kt:82)
	at org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport.analyzeAndReport(AnalyzerWithCompilerReport.kt:107)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler.analyze(KotlinToJVMBytecodeCompiler.kt:546)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler.compileModules$cli(KotlinToJVMBytecodeCompiler.kt:177)
	... 26 more
Caused by: com.sun.tools.javac.processing.AnnotationProcessingError: kotlin.jvm.KotlinReflectionNotSupportedError: Kotlin reflection implementation is not found at runtime. Make sure you have kotlin-reflect.jar in the classpath
	at jdk.compiler/com.sun.tools.javac.processing.JavacProcessingEnvironment.callProcessor(JavacProcessingEnvironment.java:992)
	at jdk.compiler/com.sun.tools.javac.processing.JavacProcessingEnvironment.discoverAndRunProcs(JavacProcessingEnvironment.java:896)
	at jdk.compiler/com.sun.tools.javac.processing.JavacProcessingEnvironment$Round.run(JavacProcessingEnvironment.java:1222)
	at jdk.compiler/com.sun.tools.javac.processing.JavacProcessingEnvironment.doProcessing(JavacProcessingEnvironment.java:1334)
	at jdk.compiler/com.sun.tools.javac.main.JavaCompiler.processAnnotations(JavaCompiler.java:1258)
	at jdk.compiler/com.sun.tools.javac.main.JavaCompiler.processAnnotations(JavaCompiler.java:1157)
	... 43 more
Caused by: kotlin.jvm.KotlinReflectionNotSupportedError: Kotlin reflection implementation is not found at runtime. Make sure you have kotlin-reflect.jar in the classpath
	at kotlin.jvm.internal.ClassReference.error(ClassReference.kt:79)
	at kotlin.jvm.internal.ClassReference.getQualifiedName(ClassReference.kt:15)
	at com.squareup.kotlinpoet.ClassNames.get(ClassName.kt:49)
	at com.squareup.moshi.kotlinpoet.classinspector.elements.ElementsClassInspector.<clinit>(ElementsClassInspector.kt:493)
	at com.squareup.moshi.kotlin.codegen.JsonClassCodegenProcessor.process(JsonClassCodegenProcessor.kt:99)
	at org.jetbrains.kotlin.kapt3.base.incremental.IncrementalProcessor.process(incrementalProcessors.kt)
	at org.jetbrains.kotlin.kapt3.base.ProcessorWrapper.process(annotationProcessing.kt:147)
	at jdk.compiler/com.sun.tools.javac.processing.JavacProcessingEnvironment.callProcessor(JavacProcessingEnvironment.java:980)
	... 48 more

Compilation failure: compile phase failed:
warning: some JAR files in the classpath have the Kotlin Runtime library bundled into them. This may cause difficult to debug problems if there's a different version of the Kotlin Runtime library in the classpath. Consider removing these libraries from the classpath
bazel-out/host/bin/external/maven/v1/https/repo1.maven.org/maven2/org/jetbrains/kotlin/kotlin-reflect/1.3.50/stamped_kotlin-reflect-1.3.50.jar: warning: library has Kotlin runtime bundled into it
bazel-out/host/bin/external/maven/v1/https/repo1.maven.org/maven2/org/jetbrains/kotlin/kotlin-stdlib/1.3.50/stamped_kotlin-stdlib-1.3.50.jar: warning: library has Kotlin runtime bundled into it
exception: java.lang.IllegalStateException: failed to analyze: java.lang.reflect.InvocationTargetException
	at org.jetbrains.kotlin.analyzer.AnalysisResult.throwIfError(AnalysisResult.kt:56)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler.compileModules$cli(KotlinToJVMBytecodeCompiler.kt:182)
	at org.jetbrains.kotlin.cli.jvm.K2JVMCompiler.doExecute(K2JVMCompiler.kt:164)
	at org.jetbrains.kotlin.cli.jvm.K2JVMCompiler.doExecute(K2JVMCompiler.kt:54)
	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:84)
	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:42)
	at org.jetbrains.kotlin.cli.common.CLITool.exec(CLITool.kt:104)
	at io.bazel.kotlin.compiler.BazelK2JVMCompiler.exec(BazelK2JVMCompiler.kt:30)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at io.bazel.kotlin.builder.toolchain.KotlinToolchain$KotlinCliToolInvoker.compile(KotlinToolchain.kt:130)
	at io.bazel.kotlin.builder.tasks.jvm.Compilation_taskKt$runPlugins$1$1$1.invoke(compilation_task.kt:110)
	at io.bazel.kotlin.builder.tasks.jvm.Compilation_taskKt$runPlugins$1$1$1.invoke(compilation_task.kt)
	at io.bazel.kotlin.builder.toolchain.CompilationTaskContext.executeCompilerTask(CompilationTaskContext.kt:122)
	at io.bazel.kotlin.builder.toolchain.CompilationTaskContext.executeCompilerTask$default(CompilationTaskContext.kt:118)
	at io.bazel.kotlin.builder.tasks.jvm.Compilation_taskKt$runPlugins$1.invoke(compilation_task.kt:108)
	at io.bazel.kotlin.builder.tasks.jvm.Compilation_taskKt$runPlugins$1.invoke(compilation_task.kt)
	at io.bazel.kotlin.builder.toolchain.CompilationTaskContext.execute(CompilationTaskContext.kt:146)
	at io.bazel.kotlin.builder.toolchain.CompilationTaskContext.execute(CompilationTaskContext.kt:138)
	at io.bazel.kotlin.builder.tasks.jvm.Compilation_taskKt.runPlugins(compilation_task.kt:103)
	at io.bazel.kotlin.builder.tasks.jvm.KotlinJvmTaskExecutor.execute(KotlinJvmTaskExecutor.kt:42)
	at io.bazel.kotlin.builder.tasks.KotlinBuilder.executeJvmTask(KotlinBuilder.kt:216)
	at io.bazel.kotlin.builder.tasks.KotlinBuilder.apply(KotlinBuilder.kt:119)
	at io.bazel.kotlin.builder.tasks.PersistentWorker.run(BazelWorker.kt:168)
	at io.bazel.kotlin.builder.tasks.BazelWorker.apply(BazelWorker.kt:74)
	at io.bazel.kotlin.builder.KotlinBuilderMain.main(KotlinBuilderMain.java:28)
Caused by: java.lang.reflect.InvocationTargetException
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.jetbrains.kotlin.kapt3.base.AnnotationProcessingKt.doAnnotationProcessing(annotationProcessing.kt:76)
	at org.jetbrains.kotlin.kapt3.base.AnnotationProcessingKt.doAnnotationProcessing$default(annotationProcessing.kt:35)
	at org.jetbrains.kotlin.kapt3.AbstractKapt3Extension.runAnnotationProcessing(Kapt3Extension.kt:230)
	at org.jetbrains.kotlin.kapt3.AbstractKapt3Extension.analysisCompleted(Kapt3Extension.kt:188)
	at org.jetbrains.kotlin.kapt3.ClasspathBasedKapt3Extension.analysisCompleted(Kapt3Extension.kt:99)
	at org.jetbrains.kotlin.cli.jvm.compiler.TopDownAnalyzerFacadeForJVM$analyzeFilesWithJavaIntegration$2.invoke(TopDownAnalyzerFacadeForJVM.kt:96)
	at org.jetbrains.kotlin.cli.jvm.compiler.TopDownAnalyzerFacadeForJVM.analyzeFilesWithJavaIntegration(TopDownAnalyzerFacadeForJVM.kt:106)
	at org.jetbrains.kotlin.cli.jvm.compiler.TopDownAnalyzerFacadeForJVM.analyzeFilesWithJavaIntegration$default(TopDownAnalyzerFacadeForJVM.kt:81)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler$analyze$1.invoke(KotlinToJVMBytecodeCompiler.kt:555)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler$analyze$1.invoke(KotlinToJVMBytecodeCompiler.kt:82)
	at org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport.analyzeAndReport(AnalyzerWithCompilerReport.kt:107)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler.analyze(KotlinToJVMBytecodeCompiler.kt:546)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler.compileModules$cli(KotlinToJVMBytecodeCompiler.kt:177)
	... 26 more
Caused by: com.sun.tools.javac.processing.AnnotationProcessingError: kotlin.jvm.KotlinReflectionNotSupportedError: Kotlin reflection implementation is not found at runtime. Make sure you have kotlin-reflect.jar in the classpath
	at jdk.compiler/com.sun.tools.javac.processing.JavacProcessingEnvironment.callProcessor(JavacProcessingEnvironment.java:992)
	at jdk.compiler/com.sun.tools.javac.processing.JavacProcessingEnvironment.discoverAndRunProcs(JavacProcessingEnvironment.java:896)
	at jdk.compiler/com.sun.tools.javac.processing.JavacProcessingEnvironment$Round.run(JavacProcessingEnvironment.java:1222)
	at jdk.compiler/com.sun.tools.javac.processing.JavacProcessingEnvironment.doProcessing(JavacProcessingEnvironment.java:1334)
	at jdk.compiler/com.sun.tools.javac.main.JavaCompiler.processAnnotations(JavaCompiler.java:1258)
	at jdk.compiler/com.sun.tools.javac.main.JavaCompiler.processAnnotations(JavaCompiler.java:1157)
	... 43 more
Caused by: kotlin.jvm.KotlinReflectionNotSupportedError: Kotlin reflection implementation is not found at runtime. Make sure you have kotlin-reflect.jar in the classpath
	at kotlin.jvm.internal.ClassReference.error(ClassReference.kt:79)
	at kotlin.jvm.internal.ClassReference.getQualifiedName(ClassReference.kt:15)
	at com.squareup.kotlinpoet.ClassNames.get(ClassName.kt:49)
	at com.squareup.moshi.kotlinpoet.classinspector.elements.ElementsClassInspector.<clinit>(ElementsClassInspector.kt:493)
	at com.squareup.moshi.kotlin.codegen.JsonClassCodegenProcessor.process(JsonClassCodegenProcessor.kt:99)
	at org.jetbrains.kotlin.kapt3.base.incremental.IncrementalProcessor.process(incrementalProcessors.kt)
	at org.jetbrains.kotlin.kapt3.base.ProcessorWrapper.process(annotationProcessing.kt:147)
	at jdk.compiler/com.sun.tools.javac.processing.JavacProcessingEnvironment.callProcessor(JavacProcessingEnvironment.java:980)
	... 48 more

Target //java/com/pettermahlen:repro failed to build
Use --verbose_failures to see the command lines of failed build steps.
INFO: Elapsed time: 0.401s, Critical Path: 0.29s
INFO: 0 processes.
FAILED: Build did NOT complete successfully
```

Changing `third_party/moshi_kotlin_codegen/BUILD.bazel` to 
```
java_plugin(
    name = "moshi_kotlin_codegen_plugin",
    processor_class = "com.squareup.moshi.kotlin.codegen.JsonClassCodegenProcessor",
    deps = [
      "@maven//:com_squareup_moshi_moshi_kotlin_codegen",
    ],
    generates_api = True,
)

java_library(
    name = "moshi_kotlin_codegen",
    exported_plugins = [":moshi_kotlin_codegen_plugin"],
    visibility = ["//visibility:public"],
    exports = [
    ],
)
```

And the other BUILD.bazel to
```
load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

kt_jvm_library(
    name = "repro",
    srcs = glob([
          "**/*.kt",
    ]),
    deps = [
        "@maven//:com_squareup_moshi_moshi",
        "@maven//:com_squareup_moshi_moshi_kotlin_codegen",
    ],
)
```
means that the annotation processor doesn't run at all.


# when does it work and not?

- Works:
  - local rules_kotlin on current master (https://github.com/bazelbuild/rules_kotlin/commit/d897c946d37f0851295c6c5c588a7140722a5a25), compiler version 1.3.72
  - local rules_kotlin at (https://github.com/bazelbuild/rules_kotlin/commit/53cd4b28108694ec11bd64ea3ad5a52ccd363963), compiler version 1.3.72
  - local rules_kotlin on legacy-1.4.0-rc3, compiler version 1.3.72

- KotlinReflectionNotSupportedError:
  - local rules_kotlin on HEAD (https://github.com/bazelbuild/rules_kotlin/commit/d897c946d37f0851295c6c5c588a7140722a5a25), default compiler version
  - local rules_kotlin after (https://github.com/bazelbuild/rules_kotlin/commit/53cd4b28108694ec11bd64ea3ad5a52ccd363963), default compiler version
  - local rules_kotlin on legacy-1.4.0-rc3, default compiler
  - released version of rules_kotlin with compiler 1.3.72
  - released version of rules_kotlin with default compiler
