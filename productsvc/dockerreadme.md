with this command line call

mvn goal -Ddockerfile.username=... -Ddockerfile.password=...

Maven Option    What Does that thing Do?
dockerfile.skip    Disables the entire dockerfile plugin; all goals become no-ops.
dockerfile.build.skip    Disables the build goal; it becomes a no-op.
dockerfile.tag.skip    Disables the tag goal; it becomes a no-op.
dockerfile.push.skip    Disables the push goal; it becomes a no-op.
For example to skip the entire dockerfile plugin:

mvn clean package -Ddockerfile.skip

Maven Goals
Goals available for this plugin:

Goal    Description    Default Phase
dockerfile:build    Builds a Docker image from a Dockerfile.    package
dockerfile:tag    Tags a Docker image.    package
dockerfile:push    Pushes a Docker image to a repository.    deploy

REPO
----
Since version 1.3.XX, you can authenticate using config from the pom itself. Just add configuration similar to:

 <plugin>
    <groupId>com.spotify</groupId>
    <artifactId>dockerfile-maven-plugin</artifactId>
    <version>${version}</version>
    <configuration>
        <username>repoUserName</username>
        <password>repoPassword</password>
        <repository>${docker.image.prefix}/${project.artifactId}</repository>
        <buildArgs>
            <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
        </buildArgs>
    </configuration>
</plugin>
or simpler,

 <plugin>
    <groupId>com.spotify</groupId>
    <artifactId>dockerfile-maven-plugin</artifactId>
    <version>${version}</version>
    <configuration>
        <repository>${docker.image.prefix}/${project.artifactId}</repository>
        <buildArgs>
            <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
        </buildArgs>
    </configuration>
</plugin>
with this command line call

mvn goal -Ddockerfile.username=... -Ddockerfile.password=...

