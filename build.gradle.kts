import org.jetbrains.kotlin.gradle.plugin.mpp.*
import org.jetbrains.kotlin.konan.target.*

plugins {
	id("conventions")
}

kotlin {
	targets.withType<KotlinNativeTarget> {
		if (HostManager.host == this.konanTarget) {
			binaries {
				executable {
					entryPoint = "dev.buescher.adventofcode.main"
				}
			}
		}
	}

	sourceSets.commonMain.dependencies {
		implementation(projects.adventOfCode2025)
	}
}
