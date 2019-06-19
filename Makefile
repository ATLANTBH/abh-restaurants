MVN=mvn
CP=cp
HEROKU=heroku
GIT=git

#
# Builds an app for heroku deployment. Make sure you run heroku buildpacks:set heroku/java first
#
heroku:
	$(HEROKU) config:set MAVEN_CUSTOM_GOALS="clean package -P production"
	$(GIT) push heroku master
