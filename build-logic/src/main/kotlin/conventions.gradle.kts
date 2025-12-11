import org.jetbrains.kotlin.konan.target.*

plugins {
	kotlin("multiplatform")
}

repositories {
	mavenCentral()
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll(
			"-opt-in", "kotlin.ExperimentalUnsignedTypes",
			"-opt-in", "kotlin.time.ExperimentalTime",
		)
	}

	jvm()

	linuxX64()
	linuxArm64()
	mingwX64()

	if (HostManager.hostIsMac) {
		macosX64()
		macosArm64()
	}

	sourceSets.commonTest.dependencies {
		implementation(kotlin("test"))
	}
}
