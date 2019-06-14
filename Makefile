MVN=mvn
CP=cp

#
# Builds an app for heroku deployment
#
heorku:
	$(MVN) -f frontend/pom.xml mvn clean package -P production
	$(CP) -R frontend/dist/ webapp/src/main/resources/public