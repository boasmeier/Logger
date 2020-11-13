# Logger

Implementation des Loggers der Gruppe g01 im Modul VSK HS20.

### Configuration
This chapter describes how to configure the logger projects.
#### Logger Server
The server runs using the default arguments without any configuration file.

**Arguments**

| Name | Default | Description |
|---|---|---|
| File structure | `basic` | Sets the format how the log files are structured. Choose from `basic`, `enhanced`, `xml` or `json`. Xml and Json is not implemented yet. |
| Port | 5050 | Sets the port on which the server application runs. |
| Log directory | Desktop (OS dependant) | Sets the path where the log files are stored. |

**Configuration File**

- The configuration file has to be stored in the root directory of the logger project.
- The file name has to be `loggerServerConfig` 

**Example**

```
port=5000
file_type=enhanced
path=C:/temp/
```

### Buildstatus
* [![Build Status](https://jenkins-vsk.el.eee.intern/jenkins/buildStatus/icon?job=g01-logger)](https://jenkins-vsk.el.eee.intern/jenkins/job/g01-logger/)

> Hinweis: Buildstatus nur innerhalb HSLU-Netz (oder per VPN) sichtbar!