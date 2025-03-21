# Eclipse RCPTT Server
Eclipse RCPTT Server is a part of [Rich Client Platform Testing Tool Project](https://github.com/eclipse-rcptt/org.eclipse.rcptt).
RCPTT Server implements client-server-agent pattern for rapid distributed execution of [Rich Client Platform Testing Tool](https://github.com/eclipse-rcptt/org.eclipse.rcptt) tests.

- [Terms of Use](https://www.eclipse.org/legal/termsofuse.php)
- [Contribution Guide](CONTRIBUTING.md)

## Client
Client is a CLI or Maven plugin that runs as a build tool. It can be started on a developer workstation or as part of CI. It takes in an RCPTT project, sends it to server, processes returned results.
It is functionally equivalent to RCPTT Runner but is much faster.
### Usage
Create, modify and run a [Maven file](rcpttTests/pom.xml) in an RCPTT project's directory.
Make sure to update:
- path to AUT (project/build/plugins/rcptt-server-maven-plugin/configuration/aut/locations/location)
- server address (project/build/plugins/rcptt-server-maven-plugin/configuration/server)

## Server
Server is a daemon that runs in a cloud infrastructure. It accepts client's requests, splits them into chunks and sends chunks to agents. It does no testing by itself and acts as a load balancer. It also manages AUT downloads and distribution.
### Usage
Download and unzip a server and run the command:

    eclipse -consoleLog -port 5009  &

The port argument defines a port to accept incoming agent connections.
Server also has a web interface on port 5007. Run seerver with `--help` argument to get a full list of options.

## Agent
Agent is a daemon that runs on multiple instances in cloud infrastructure. It runs in UI context, deploys an AUT and executes tests on it per server's request. It can be thought of as a remotely controlled RCPTT Runner. The count of agents connected to a server is limited only by available cloud resources and therefore, a particular RCPTT project can be executed very quickly as load is spread across a multitude of executors.
### Usage
Download and unzip an agent and run the command, replacing server.local with a hostname or IP address of the host running the server:

    eclipse -consoleLog -data /tmp/ws_agent -serverHost server.local -serverPort 5009

Do this for every available platform and architcture. Introduce more agents and hosts to speed up the execution.
