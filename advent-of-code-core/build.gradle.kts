plugins {
	id("conventions")
}

kotlin {
	sourceSets.commonMain.dependencies {
		implementation(libs.kotlinxIoCore)
	}
}
