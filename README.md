[![Code Tests](https://github.com/BenWalker01/longboi-life/actions/workflows/build-and-upload.yml/badge.svg)](https://github.com/BenWalker01/longboi-life/actions/workflows/build-and-upload.yml)
[![Release](https://github.com/BenWalker01/longboi-life/actions/workflows/stable-release.yml/badge.svg)](https://github.com/BenWalker01/longboi-life/actions/workflows/stable-release.yml)

# longboi-life

*University of York ENG1 Project.*

Short single-player game that allows the player to build their own university campus trying to reach the highest student satisfaction possible.

A [libGDX](https://libgdx.com/) project.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.
- `headless`: Run without the full ui, for testing

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should
be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.

## DevUtils

`/_devUtils/` contains useful scripts (not necessarily in Java) for developing the project.

## Attributions
- [libGDX](https://github.com/libgdx/libgdx) - [Apache Licence 2.0](LICENSE)
- [Game Over](https://opengameart.org/content/game-over-effect-sound) sound effect by [EdenVe](https://opengameart.org/users/edenve) - [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/)
- [Gradle](https://github.com/gradle/gradle) - [Apache Licence 2.0](LICENSE)
- [jcbyte/longboi-life](https://github.com/jcbyte/longboi-life) - [MIT Licence](https://github.com/jcbyte/longboi-life/blob/main/LICENSE)
- [Shade UI](https://github.com/Karthik-Nayak98/shade-ui) - [MIT Licence](https://github.com/Karthik-Nayak98/shade-ui/blob/development/LICENSE)
- [Road Sprites](https://www.pngegg.com/en/png-btgbw)
