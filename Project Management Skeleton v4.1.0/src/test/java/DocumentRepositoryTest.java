import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import abeczkowska.model.Document;
import abeczkowska.persistence.Persistable;
import abeczkowska.repository.DocumentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DocumentRepositoryTest {
    private DocumentRepository documentRepository;

    @BeforeEach
    public void setUp() {
        documentRepository = new DocumentRepository();
    }

    @Test
    public void testCreateDocument() {
        // Tworzenie nowego dokumentu
        Document document = new Document();
        document.setTitle("Test sgd");
        document.setDescription("Tgsrgn");
        document.setCreatorId(1);
        document.setProjectId(2);
        document.setTopic("erfd");
        document.setContent("gesfd");

        // Wywołanie metody create
        Document createdDocument = (Document) documentRepository.create(document);

        // Sprawdzanie rezultatów
        assertNotNull(createdDocument);
        assertNotNull(createdDocument.getId());
        assertEquals("Test sgd", createdDocument.getTitle());
        assertEquals("Tgsrgn", createdDocument.getDescription());
        assertEquals(1, createdDocument.getCreatorId());
        assertEquals(2, createdDocument.getProjectId());
        assertEquals("erfd", createdDocument.getTopic());
        assertEquals("gesfd", createdDocument.getContent());


    }


    @Test
    public void testFindById() {
        Document document = (Document) documentRepository.findById(1);

        //assertNotNull(document);
        assertEquals("Tytuł dokumentu", document.getTitle());
    }

    @Test
    public void testFindAll() {
        List<? extends Persistable> documents = documentRepository.findAll();

        assertNotNull(documents);
        assertTrue(documents.size() > 0);
    }

    @Test
    public void testUpdateDocument() {
        Document document = new Document();
        document.setTitle("Updated Title");
        document.setDescription("Updated Description");
        document.setCreatorId(1);
        document.setProjectId(2);
        document.setTopic("Updated Topic");
        document.setContent("Updated Content");

        documentRepository.update(document, 1);

        Document updatedDocument = (Document) documentRepository.findById(1);

        assertNotNull(updatedDocument);
        assertEquals("Updated Title", updatedDocument.getTitle());
        assertEquals("Updated Description", updatedDocument.getDescription());
        assertEquals("Updated Topic", updatedDocument.getTopic());
        assertEquals("Updated Content", updatedDocument.getContent());
    }

    @Test
    public void testDeleteDocument() {
        Document document = (Document) documentRepository.findById(1);
        documentRepository.delete(document);

        Document deletedDocument = (Document) documentRepository.findById(1);

        assertNull(deletedDocument);
    }

    @Test
    public void testDeleteById() {
        documentRepository.deleteById(1);

        Document deletedDocument = (Document) documentRepository.findById(1);

        assertNull(deletedDocument);
    }
}
