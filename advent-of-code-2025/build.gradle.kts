plugins {
	id("conventions")
}

kotlin {
	sourceSets.commonMain.dependencies {
		api(projects.adventOfCodeCore)
	}
}
