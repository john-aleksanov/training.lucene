package dev.marvel.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.uhighlight.UnifiedHighlighter;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

    private static final Path INDEX_PATH = Paths.get(".index");
    private static final File FILE_DIR = new File("./data");
    private static final String DEFAULT_CONTENT_FIELD = "content";

    private final QueryParser parser;
    private final IndexSearcher searcher;
    private final FileIndexWriter fileIndexWriter;
    private final UnifiedHighlighter highlighter;

    public Main() throws IOException {
        var directoryReader = DirectoryReader.open(FSDirectory.open(INDEX_PATH));
        this.searcher = new IndexSearcher(directoryReader);
        var analyzer = new StandardAnalyzer();
        this.parser = new QueryParser(DEFAULT_CONTENT_FIELD, analyzer);
        this.fileIndexWriter = new FileIndexWriter(analyzer, INDEX_PATH);
        this.highlighter = UnifiedHighlighter.builder(searcher, analyzer).build();
    }

    public static void main(String[] args) throws IOException, ParseException {
        var main = new Main();
        main.run();
    }

    public void run() throws IOException, ParseException {
        fileIndexWriter.addToIndex(FILE_DIR.listFiles());

        var query = parser.parse("lucene index");
        var topDocs = searcher.search(query, 3);

        var highlights = highlighter.highlight(DEFAULT_CONTENT_FIELD, query, topDocs);
        System.out.println(Arrays.toString(highlights));
    }
}
