---

### File 2: `docs/Setup_Instructions.md`
**Path:** Create a folder named `docs` in your project root, and place this file inside it.

```markdown
# Setup Instructions - LearnTrack

## 1. Environment Requirements & JDK Version
* **JDK Version Used:** Java 17 (Compatible with JDK 11+)
* **Development Environment:** Visual Studio Code / Terminal / Command Prompt

## 2. Hello World Verification & Compilation Steps

### Verification of JDK Setup
Before running the application, ensure Java is properly configured in your system environment path:
```bash
java -version
javac -version

**To compile and run the project manually from the project root directory:

1.Create a build directory:

Bash
mkdir -p bin
Compile all source code files:

2.Bash
javac -d bin $(find src -name "*.java")
Run the application entry point:

3.Bash
java -cp bin com.airtribe.learntrack.Main
Running these commands initializes the interactive console menu for managing students, courses, and enrollments.