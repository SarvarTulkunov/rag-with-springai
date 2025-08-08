# RAG (Retrieval-Augmented Generation) with Spring AI and GPT-4o 🔍

This project demonstrates how to build a **Retrieval-Augmented Generation (RAG)** system using **Spring AI**, **OpenAI's GPT-4o model**, and **PGVector** as a vector store. It supports document ingestion from PDF files and provides two RAG injection modes:
- 🔹 **JSON-based vector storage**
- 🔹 **PostgreSQL-based vector storage** using PGVector

## 🧠 Technologies Used

- [Spring AI](https://docs.spring.io/spring-ai/reference/)
- [OpenAI GPT-4o](https://platform.openai.com/docs/guides/gpt)
- [PostgreSQL + PGVector](https://github.com/pgvector/pgvector)
- [Docker Compose](https://docs.docker.com/compose/)
- [PDFBox or similar](https://pdfbox.apache.org/) for PDF content extraction
- Java 17
- Spring Boot 3.5.4

---

## 📁 Project Structure

```code
src/
├── main/
│ ├── java/
│ │ └── com.example.ragwithspringai/
│ │ ├── injection/ # Configuration classes (OpenAI, PGVector, etc.)
│ │ ├── controller/ # Two REST controllers for JSON and PGVector
│ │ ├── injection/ # Services for embedding, storage, and loading
│ └── resources/
│ ├── application.yml
│ └── docs/ # PDFs to be ingested
├── docker-compose.yml # PGVector container
└── README.md
```


---

## 🚀 Features

- ✅ Extracts text from uploaded PDF documents
- ✅ Generates embeddings using GPT-4o via Spring AI
- ✅ Stores embeddings in:
  - `.json` file (local flat storage)
  - PostgreSQL + PGVector (for scalable vector search)
- ✅ Two REST controllers to handle both storage strategies
- ✅ Simple and extendable RAG querying API
- ✅ Dockerized PostgreSQL with PGVector

---

## 🐳 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/spring-ai-rag.git
cd rag-with-springai
```

### 2. Configure OpenAI API Key
In application.yml or as an environment variable:

```yaml
spring:
  ai:
    openai:
      api-key: ${OPENAI_API_KEY}
```

You can also set it via the terminal:

```bash
export OPENAI_API_KEY=your_openai_key
```

### 3. Start PostgreSQL + PGVector via Docker

```bash
docker-compose up -d
```

👨‍💻 Author
Sarvar Tulkunov
📫 GitHub
