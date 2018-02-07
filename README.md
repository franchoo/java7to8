# Java 7 -> 8: Yoda Replies Restfully
Simple yet complete [coding challenge](https://goo.gl/WbBvmX) with _Spring Framework_

### Content
**Spring Boot** project implemented on Java 8 using _Gradle_
- Explicit vs. Implicit cycles
- SAM interfaces & lambdas
- Stream (and other functors & monads)
- New date and time API

## End-points _(to implement)_
##### _Max 9 search results_
- ✓ GET /species?q={search}
- ✓ GET /vehicles?q={search}
- ✓ GET /planets?q={search}
- ✓ GET /films?q={search}
##### _Join & filter_
- ✓ GET /species/{species}/in/{planet} ..._returns people of single species of planet_
- ✓ GET /films/{film}/starpilots ..._returns a film's starship pilots (people) with hyperdrive_rating above 1_
- ✓ GET /vehicles/{vehicle}/lifespan ..._returns the vehicle duration period between first and last films_

### Try out
Import the files (_build.gradle_ as a project) in an IDE (like Eclipse or IntelliJ) or with VSCode adding the [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) (also by the shell command `code <project-folder>`), execute via main method or JUnit Test files

Can also be deployed by shell command, in the project path use `./gradlew bootRun`

## Contributors
- franchoo [twitter](https://twitter.com/Franchooo42) [linkedin](https://www.linkedin.com/in/franchoo)
