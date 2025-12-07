plugins {
	id("project-conventions")
}

kotlin {
	sourceSets.commonMain.dependencies {
		implementation(libs.kotlinxIoCore)
	}
}
