# API Documentation - Swagger,Swagger2Markup,asciidoc

This project shows how to generate static API Documentation (PDF/HTML) by using following plugin
1. Swagger2Markup
2. asciidoc

#### Usage Guide
Run the following commands to start the Spring boot Application.
> `gradlew clean build`
>
> `java -jar build/libs/sample-api-doc-generator{version}.jar`

Run the following command if you only want to generate the HTML and PDF documentation
> `gradlew clean asciidoctor`

The resulting HTML and PDF documents are created at location `build/asciidoc/html5` and `build/asciidoc/pdf` respectively.

A sample of generated HTML/PDF document is present in `sampleDocument` Folder present in the repository.