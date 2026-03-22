# AGENTS.md

This file provides specialized context and operational guidelines for AI agents working on the Eclipse RCPTT Server project.

## General Information
Refer to [README.md](README.md) for the project overview, high-level architecture (Client/Server/Agent), and basic building instructions.

## Critical Commands
In addition to the basic build in README, use these for validation:
- **Build with Tests:** `mvn clean install -e -Dlicensecheck.skip=true`
- **Run Tests Only:** `mvn verify -e -Dlicensecheck.skip=true`

## Code Style & Conventions
- **OSGi Bundles:** Adhere to Eclipse plugin structures (check `MANIFEST.MF` for dependencies).

## Naming Conventions
- **Bundles:** Use the prefix `org.eclipse.rcptt.cloud.` followed by the module name (e.g., `org.eclipse.rcptt.cloud.server`).
- **Packages:** Mirror the bundle name. Use `.internal` sub-packages for non-API classes (e.g., `org.eclipse.rcptt.cloud.server.internal`).
- **Special Bundles:**
  - `*.ecl.model`: For Ecore/EMF model definitions.
  - `*.ecl.impl`: For ECL command service implementations.
- **Source Folders:** Place handwritten code in `src/` and EMF-generated code in `gen-src/`.
- **POM Artifact IDs:** Must match the bundle symbolic name (the directory name).

## Architecture & Patterns
- **Model:** Ecore models are located in `*.ecl.model` bundles.

## Definition of Done
- All code changes must be verified with `mvn clean install`.
- New features or bug fixes should include corresponding tests (check `rcpttTests` or `tests/` directory).
