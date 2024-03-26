# Lucene Text Indexing and Search

## Overview

This project demonstrates a simple yet powerful implementation of text indexing, searching, and highlighting using Apache Lucene, a
high-performance, full-featured text search engine library written entirely in Java. It's designed to provide an introduction to using
Lucene for full-text search capabilities in applications. See [task.md](./task.md) for a series of tasks that were the foundation for this
project.

### Features

- **Indexing:** Processes multiple text files, adding their content to a Lucene index for efficient searching.
- **Searching:** Implements querying of the index with a combination of terms, returning the top 3 matching documents.
- **Highlighting:** Generates highlighted snippets from the search results, emphasizing the query terms found in the documents.

## Setup and Configuration

### Prerequisites

- Java JDK 11 or newer
- Gradle (to manage dependencies and run the project)

### Dependencies

The project uses Lucene version 9.10.0 for its core functionality, including indexing, querying, and highlighting capabilities. The specific
Lucene modules used are:

- `lucene-core`: Core library providing the indexing and search technology.
- `lucene-queryparser`: Supports parsing of query strings into Lucene's query object model.
- `lucene-highlighter`: Provides text highlighting functionality.

### Project Structure

- `src/main/java/dev/marvel/lucene/Main.java`: The main class that orchestrates indexing, searching, and highlighting.
- `src/main/java/dev/marvel/lucene/FileIndexWriter.java`: A helper class responsible for indexing text files into Lucene's index format.
- `build.gradle`: The build configuration file specifying the project's dependencies and other Gradle settings.
- `data`: The text files to be indexed.

## Running the Project

1. **Build the Project**: Run `gradle build` to compile the project and download the necessary dependencies.
2. **Run the Application**: Execute `gradle run` to start the indexing and searching process. The application will index the documents found
   in `./data`, perform a search based on a predefined query, and output the top 3 matching documents with highlighted snippets.

## Implementation Details

### Indexing

The `FileIndexWriter` class takes text files from the specified directory and indexes their content using Lucene. Each document is indexed
with fields for the file name, path, and content, facilitating rich querying capabilities.

### Searching

The `Main` class constructs a Lucene query using the `QueryParser`, searches the index for matching documents, and ranks them based on
relevance. It demonstrates basic query syntax and usage, retrieving the top 3 documents that best match the query terms.

### Highlighting

Using Lucene's `UnifiedHighlighter`, the application generates highlighted snippets from the search results, emphasizing the terms that
contributed to the document's relevance. This feature showcases how to extract and display key pieces of information from documents in the
context of search queries.
