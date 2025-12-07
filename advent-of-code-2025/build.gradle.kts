plugins {
	id("application-conventions")
}

kotlin {
	sourceSets.commonMain.dependencies {
		implementation(projects.adventOfCodeCore)
	}
}
