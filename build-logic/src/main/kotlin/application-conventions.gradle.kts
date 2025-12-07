import org.jetbrains.kotlin.gradle.plugin.mpp.*
import org.jetbrains.kotlin.konan.target.*

plugins {
	id("project-conventions")
}

kotlin {
	targets.withType<KotlinNativeTarget> {
		if (HostManager.host == this.konanTarget) {
			binaries {
				executable {
					entryPoint = "main"
				}
			}
		}
	}
}
