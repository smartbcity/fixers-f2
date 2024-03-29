STORYBOOK_DOCKERFILE	:= infra/docker/storybook/Dockerfile
STORYBOOK_NAME	   	 	:= smartbcity/f2-storybook
STORYBOOK_IMG	    	:= ${STORYBOOK_NAME}:${VERSION}
STORYBOOK_LATEST		:= ${STORYBOOK_NAME}:latest

libs: package-kotlin
docs: package-storybook push-storybook

package-kotlin:
	@gradle clean build publishToMavenLocal publish

package-storybook:
	@docker build --build-arg CI_NPM_AUTH_TOKEN=${CI_NPM_AUTH_TOKEN} -f ${STORYBOOK_DOCKERFILE} -t ${STORYBOOK_IMG} .

push-storybook:
	@docker push ${STORYBOOK_IMG}
