# Banter
A peer to peer file sharing tool that facilitates the transfer of Machine Learning Datasets inside a small scale domain.

## Installation
The project uses [Gradle](https://gradle.org/) as it's build tool. So building the application into a jar file requires **gradlew** to be run with the `jar` argument.

```bash
cd Banter
./gradle jar
```
## Usage
After the jar file has been built, the jar file will be stored in `build/libs`. The jar file requires 3 arguments to run:

```bash
java -jar Banter.x.x.jar <mode> <filename/path> <ip.addr.to.comm>
```
**--send/--recv** - Defines whether the program will run in Client or Server mode.

**fileName** - The filename that needs to be sent or the name of the file recieved ( user defined ).

**addr.to.send.from/addr.to.recv.from** - This field requires the source or the destination IP address for eshtablishing the connection.

> Example given:
`java -jar Banter-1.0-NOGUI.jar --send test.txt 10.11.17.18`

This starts the program in Client mode and sends the file **test.txt** which is present in the same directory as the jar file, to the specified IP address 10.11.17.18.
