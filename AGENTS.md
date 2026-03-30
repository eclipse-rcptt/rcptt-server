# AGENTS.md

This file provides specialized context and operational guidelines for AI agents working on the Eclipse RCPTT Server project.

## General Information
Refer to [README.md](README.md) for the project overview and high-level architecture (Client/Server/Agent). See [CONTRIBUTING.md](CONTRIBUTING.md#building) for building instructions.

## Critical Commands
In addition to the basic build in CONTRIBUTING.md, use these for validation:
- **Build with Tests:** `mvn clean install -e -Dlicensecheck.skip=true`
- **Run Tests Only:** `mvn verify -e -Dlicensecheck.skip=true`

## Code Style
- **OSGi Bundles:** Adhere to Eclipse plugin structures (check `MANIFEST.MF` for dependencies).

## Project Structure & Conventions
Project follows the [Tycho Structured Build](https://tycho.eclipseprojects.io/doc/main/StructuredBuild.html) layout for module organization:
- **Root Modules:** Projects are grouped into `bundles/`, `features/`, `products/`, `tests/`, and `maven/`.
- **Source Folders:** Current project convention uses Eclipse-native layouts:
  - `src/`: For handwritten Java source code and resources (as defined in `build.properties`).
  - `gen-src/`: For EMF/Ecore generated code.
- **Bundles:** Use the prefix `org.eclipse.rcptt.cloud.` (e.g., `org.eclipse.rcptt.cloud.server`).
- **Packages:** Mirror the bundle name. Use `.internal` sub-packages for non-API classes.
- **Special Bundles:**
  - `*.ecl.model`: EMF model definitions.
  - `*.ecl.impl`: ECL command implementations.
- **POMs:** Rely on Tycho's "pomless" capabilities where possible; individual module `pom.xml` files should be minimal or omitted if Tycho can derive the configuration.

## Architecture & Patterns
- **Model:** Ecore models are located in `*.ecl.model` bundles.

## Definition of Done
- All code changes must be verified with `mvn clean install`.
- New features or bug fixes should include corresponding tests (check `rcpttTests` or `tests/` directory).
