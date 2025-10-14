#FROM payara/micro:7.2025.1.Beta1-jdk21
FROM payara/micro:6.2025.9-jdk21
COPY target/jmoordbcoreexampleweb-0.1-SNAPSHOT.war $DEPLOY_DIR
