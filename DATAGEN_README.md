Running data generation

Lowk when I run ts program I get this...
  Working directory '.../build/datagen' does not exist.

To avoid this, the Gradle build now ensures `build/datagen` exists before the run task executes
Use the following command in a Windows cmd.exe shell in the project root:

```
gradlew.bat runDataGen
```

or to build the project after datagen:

```
gradlew.bat runDataGen build
```