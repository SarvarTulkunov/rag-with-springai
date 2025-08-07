package com.example.ragwithspringai.injection;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.reader.pdf.ParagraphPdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderOnVectorDB {

    private static final Logger logger = LoggerFactory.getLogger(DataLoaderOnVectorDB.class);
    private final VectorStore vectorStore;
    private final JdbcClient jdbcClient;

    @Value("classpath:/docs/article_thebeatoct2024.pdf")
    private Resource marketPDF;

    public DataLoaderOnVectorDB(VectorStore vectorStore, JdbcClient jdbcClient) {
        this.vectorStore = vectorStore;
        this.jdbcClient = jdbcClient;
    }

    @PostConstruct
    public void init() {
        Integer count = jdbcClient
                .sql("select count(1) from vector_store")
                .query(Integer.class)
                .single();

        if (count == 0) {
            PdfDocumentReaderConfig config = PdfDocumentReaderConfig
                    .builder()
                    .withPagesPerDocument(1)
                    .build();

            var pdfReader = new ParagraphPdfDocumentReader(marketPDF, config);
            TextSplitter textSplitter = new TokenTextSplitter();
            vectorStore.accept(textSplitter.apply(pdfReader.get()));
            logger.info("VectorStore loaded with data");
        }

    }
}
