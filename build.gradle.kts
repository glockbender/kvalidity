plugins {
    alias(libs.plugins.kotlinMultiplatform).apply(true)
    alias(libs.plugins.detekt).apply(true)
//    alias(libs.plugins.ktfmtGradle).apply(true)
//    alias(libs.plugins.binaryCompatibility).apply(true)
    alias(libs.plugins.mokoResourcesPlugin).apply(true)
    alias(libs.plugins.kotestMultiplatform).apply(true)
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

//ktfmt { kotlinLangStyle() }

multiplatformResources {
    multiplatformResourcesPackage = "com.prvz.kvalidity"
//    multiplatformResourcesClassName = "bundled"
}

kotlin {
    explicitApi()
    jvm {
        jvmToolchain(11)
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(IR) {
        // Chrome should be installed
        browser()
//        browser {
//            // temp disabling tests for js
//            testTask {
//                enabled = false
//            }
//            commonWebpackConfig {
//                cssSupport {
//                    enabled.set(true)
//                }
//            }
//        }
    }
    ios()
    iosSimulatorArm64()

    targets.all {
        compilations.all {
            kotlinOptions {
                verbose = true
            }
        }
    }

    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.mokoResources)
                implementation(kotlin("stdlib"))
                implementation(libs.kotlinCoroutines)
                implementation(libs.kotlinResult)
                implementation(libs.kmmUuid)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotestAssertionsCore)
                implementation(libs.kotestFrameworkEngine)
                implementation(libs.kotestFrameworkDatatest)
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(libs.kotestRunnerJunit5)
            }
        }
        val jsMain by getting
        val jsTest by getting

        val iosMain by getting
        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)

        val iosTest by getting
        val iosSimulatorArm64Test by getting
        iosSimulatorArm64Test.dependsOn(iosTest)
    }
}
