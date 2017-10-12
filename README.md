# Passbolt API performance test suite
This project contains the necessary files to run a performance test against a 
[passbolt API](https://github.com/passbolt/passbolt_api) the server component of
the open source password manager for teams.

The tests are written in Scala and can be run using gatling.

## Copyright and Licence

Copyright (c) Passbolt SARL (https://www.passbolt.com)

Licensed under GNU Affero General Public License v3.0
For full copyright and license information, please see the LICENSE.txt
Redistributions of files must retain the above copyright notice.

## Getting started

### Prerequisite
You will need:
- Java 8
- Gatling: http://gatling.io/
- A running [passbolt API](https://github.com/passbolt/passbolt_api)

### Setup
_There are several way you can install and run gatling, we will just assume you are 
downloading the zip archive and not using the maven build._

#### Download the gatling zip archive and unzip it
For example for version 2.3.0:
```
wget https://repo1.maven.org/maven2/io/gatling/highcharts/gatling-charts-highcharts-bundle/2.3.0/gatling-charts-highcharts-bundle-2.3.0-bundle.zip
unzip gatling-charts-highcharts-bundle-2.3.0-bundle.zip
cd gatling-charts-highcharts-bundle-2.3.0-bundle
```
#### Edit gatling conf to use passbolt_gatling scenario and data
You can also just point to another target directory this by altering 
the configuration in ```{GATLING_HOME}/conf/gatling.conf.```

For example:
```
directory {
      data = /var/www/passbolt_gatling/data               # Folder where user's data (e.g. files used by Feeders) is located
      bodies = /var/www/passbolt_gatling/bodies           # Folder where bodies are located
      simulations =/var/www/passbolt_gatling/simulations # Folder where the bundle's simulations are located
      results = /var/www/passbolt_gatling/results         # Name of the folder where all reports folder are located
    }
```

(Alternative) You could also replace the user files with a clone of this repository
```
cd {GATLING_HOME}
rm -rf user-files
git clone https://github.com/passbolt/passbolt_gatling.git user-files
```

#### Create a file in conf/application.conf

This file will hold the information specific to your instance such as the baseURL
For example:
```
application {
    baseURL = "http://passbolt.dev"
}
```
### Running a scenario
From gatling root folder, you can run a script calling the healthcheck status api
endpoint with 10 users as follow:
```
./bin/gatling.sh -s healtcheck.Status
```

### Viewing the results
The results will be displayed in the console but also available in /results as html files.
