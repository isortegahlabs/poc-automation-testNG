Comando ejecución inicial
mvn -P drivers test


https://maven.apache.org/surefire/maven-surefire-plugin/examples/testng.html
https://testng.org/doc/documentation-main.html

Config log4j2 https://howtodoinjava.com/log4j2/log4j2-properties-example/
https://www.baeldung.com/java-logging-rolling-file-appenders

Singleton
https://refactoring.guru/es/design-patterns/singleton/java/example
https://jarroba.com/patron-singleton-en-java-con-ejemplos/

Static
https://www.adictosaltrabajo.com/2015/09/17/la-directiva-static-en-java/


Locators
https://www.youtube.com/watch?v=2y9EhMGsYug

Selenium grid
https://github.com/SeleniumHQ/selenium/blob/selenium-3.141.59/java/server/src/org/openqa/grid/common/defaults/DefaultNodeWebDriver.json
https://github.com/SeleniumHQ/selenium/blob/selenium-2.53.0/java/server/src/org/openqa/grid/common/defaults/DefaultNode.json

Selenium remote
https://medium.com/expedia-group-tech/getting-started-with-a-dockerized-selenium-grid-on-your-on-prem-data-centers-5b472a2d35a
https://www.browserstack.com/guide/selenium-grid-tutorial#:~:text=What%20is%20Selenium%20Grid%3F,to%20multiple%20registered%20Grid%20nodes.
https://www.lambdatest.com/blog/selenium-grid-setup-tutorial/
https://www.swtestacademy.com/selenium-parallel-tests-grid-testng/
https://www.swtestacademy.com/local-parallel-testing-selenium/
https://github.com/browserstack/testng-browserstack/blob/0dd314db4cf40e9dc59677ddb616780257fb0f5b/src/test/java/com/browserstack/BrowserStackTestNGTest.java#L19
https://bitbar.com/blog/how-to-automate-appium-java-tests-in-parallel-using-testng/


sudo yum install -y git

aws
https://www.jenkins.io/doc/tutorials/tutorial-for-installing-jenkins-on-AWS/
sudo yum update –y
sudo yum install java-1.8.0-openjdk-devel -y
sudo amazon-linux-extras install docker
sudo yum install docker
sudo service docker start
sudo usermod -a -G docker ec2-user

sudo curl -L https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
