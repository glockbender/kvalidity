plugins {
    alias(libs.plugins.kotlinMultiplatform).apply(true)
    alias(libs.plugins.detekt).apply(true)
    alias(libs.plugins.ktfmtGradle).apply(true)
    alias(libs.plugins.binaryCompatibility).apply(true)
}

group = "com.prvz"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    detektPlugins(libs.detektFormatting)
}

detekt {
    autoCorrect = true
    config = files("$rootDir/config/detekt.yaml")
}

ktfmt { dropboxStyle() }


kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinResult)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
        val nativeMain by getting
        val nativeTest by getting
    }
}
