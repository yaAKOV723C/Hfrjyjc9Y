// 代码生成时间: 2025-10-12 19:29:42
package com.collaboration.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.collaboration.repository.DocumentRepository;
import com.collaboration.model.Document;

@Service
public class DocumentCollaborationService {

    @Autowired
    private DocumentRepository documentRepository;

    // Save a new document to the repository
    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    // Get a document by its ID
    public Document getDocument(Long id) {
        return documentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Document not found with ID: " + id));
    }

    // Update an existing document
    public Document updateDocument(Long id, Document documentDetails) {
        Document document = getDocument(id);
        document.setName(documentDetails.getName()); // Example field, update as necessary
        document.setContent(documentDetails.getContent()); // Example field, update as necessary
        return documentRepository.save(document);
    }

    // Delete a document by its ID
    public void deleteDocument(Long id) {
        if (!documentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Document not found with ID: " + id);
        }
        documentRepository.deleteById(id);
    }

    // Additional methods can be added to support collaboration features

    // Example method to handle document sharing
    public boolean shareDocument(Long documentId, String userId) {
        // Logic to share document with user
        return true; // Simplified for this example
    }

    // Error handling is managed through ResponseStatusException as shown above
    // More sophisticated error handling could be implemented using a global exception handler
}