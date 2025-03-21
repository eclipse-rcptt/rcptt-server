# Eclipse RCPTT Server Contribution Guide
Eclipse RCPTT Server implements client-server-agent pattern for rapid distributed execution of Rich Client Platform Testing Tool tests.


# Developer resources
- [Search and report issues](https://github.com/eclipse-rcptt/rcptt-server/issues)
  - Be sure to search for existing bugs before you create another one.
- [Get source code](https://github.com/eclipse-rcptt/rcptt-server)
- Sign Eclipse Contributor Agreement (below) and contribute a [Pull Request](https://github.com/eclipse-rcptt/rcptt-server/pulls)

# Build
Run in repository root:
```
mvn clean verify
```
Find main artifacts in:
- products/org.eclipse.rcptt.cloud.client-product/target/products and
- products/products/target/products

# Eclipse Contributor Agreement

Before your contribution can be accepted by the project team contributors must
electronically sign the Eclipse Contributor Agreement (ECA).

* http://www.eclipse.org/legal/ECA.php

Commits that are provided by non-committers must have a Signed-off-by field in
the footer indicating that the author is aware of the terms by which the
contribution has been provided to the project. The non-committer must
additionally have an Eclipse Foundation account and must have a signed Eclipse
Contributor Agreement (ECA) on file.

For more information, please see the Eclipse Committer Handbook:
https://www.eclipse.org/projects/handbook/#resources-commit

# Contact

Contact the project developers via the project's "dev" list.

* https://accounts.eclipse.org/mailing-list/rcptt-dev