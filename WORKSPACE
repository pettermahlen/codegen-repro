load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

http_archive(
    name = "rules_jvm_external",
    sha256 = "62133c125bf4109dfd9d2af64830208356ce4ef8b165a6ef15bbff7460b35c3a",
    strip_prefix = "rules_jvm_external-3.0",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/3.0.zip",
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    artifacts = [
        "com.squareup.moshi:moshi:1.9.2",
        "com.squareup.moshi:moshi-kotlin-codegen:1.9.2",
    ],
    fetch_sources = True,
    repositories = [
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
    ],
    strict_visibility = True,
    excluded_artifacts = [
    ],
)

# === CONFIGURATION FOR OFFICIAL rules_kotlin RELEASE
#rules_kotlin_version = "legacy-1.4.0-rc3"
#rules_kotlin_sha = "da0e6e1543fcc79e93d4d93c3333378f3bd5d29e82c1bc2518de0dbe048e6598"
#
#http_archive(
#    name = "io_bazel_rules_kotlin",
#    urls = ["https://github.com/bazelbuild/rules_kotlin/releases/download/%s/rules_kotlin_release.tgz" % rules_kotlin_version],
#    sha256 = rules_kotlin_sha,
#)
# === END OF OFFICIAL RELEASE CONFIG

# === CONFIGURATION FOR LOCAL rules_kotlin VERSION
local_repository(
    name = "io_bazel_rules_kotlin",
    path = "../../opensource/rules_kotlin",
)

load("@io_bazel_rules_kotlin//kotlin:dependencies.bzl", "kt_download_local_dev_dependencies")
kt_download_local_dev_dependencies()
# === END OF LOCAL CONFIGURATION

load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kotlin_repositories", "kt_register_toolchains")

KOTLIN_VERSION = "1.3.72"
KOTLINC_RELEASE_SHA="ccd0db87981f1c0e3f209a1a4acb6778f14e63fe3e561a98948b5317e526cc6c"
KOTLINC_RELEASE = {
   "urls": [
      "https://github.com/JetBrains/kotlin/releases/download/v{v}/kotlin-compiler-{v}.zip".format(v = KOTLIN_VERSION),
    ],
    "sha256": KOTLINC_RELEASE_SHA,
}
kotlin_repositories(compiler_release = KOTLINC_RELEASE)

kt_register_toolchains()
