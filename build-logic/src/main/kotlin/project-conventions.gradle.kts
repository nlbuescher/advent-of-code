import org.jetbrains.kotlin.konan.target.*

plugins {
	kotlin("multiplatform")
}

repositories {
	mavenCentral()
}

kotlin {
	linuxX64()
	linuxArm64()
	mingwX64()

	if (HostManager.hostIsMac) {
		macosX64()
		macosArm64()
	}
}
