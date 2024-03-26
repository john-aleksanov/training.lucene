package dev.marvel.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FileIndexWriter {

    private final Directory directory;
    private final IndexWriterConfig config;

    public FileIndexWriter(Analyzer analyzer, Path indexPath) throws IOException {
        this.directory = FSDirectory.open(indexPath);
        this.config = new IndexWriterConfig(analyzer);
    }

    public void addToIndex(File[] files) throws IOException {
        try (var indexWriter = new IndexWriter(directory, config)) {
            for (var file : files) {
                indexFile(indexWriter, file);
            }
        }
    }

    private void indexFile(IndexWriter writer, File file) throws IOException {
        var doc = new Document();
        doc.add(new TextField("name", file.getName(), Field.Store.YES));
        doc.add(new TextField("path", file.getAbsolutePath(), Field.Store.YES));
        var content = Files.readAllLines(Paths.get(file.getPath())).stream()
            .collect(Collectors.joining(System.lineSeparator()));
        doc.add(new TextField("content", content, Field.Store.YES));
        writer.addDocument(doc);
    }
}
