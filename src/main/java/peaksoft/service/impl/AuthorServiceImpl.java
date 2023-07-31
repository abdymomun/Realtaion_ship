package peaksoft.service.impl;

import peaksoft.entity.Author;
import peaksoft.repository.AuthorRepository;
import peaksoft.repository.impl.AuthorRepoImpl;
import peaksoft.service.AuthorService;

public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository = new AuthorRepoImpl();
    @Override
    public String saveAuthor(Author author) {
        return authorRepository.saveAuthor(author);
    }

    @Override
    public Author updateAuthor(Long id,Author author) {
        return authorRepository.updateAuthor(id,author);
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.getAuthorById(id);
    }

    @Override
    public Author getAuthorsByPublisherId(Long publisherId) {
        return authorRepository.getAuthorsByPublisherId(publisherId);
    }

    @Override
    public String deleteAuthorById(Long id) {
        return authorRepository.deleteAuthorById(id);
    }

    @Override
    public String assignAuthorToPublisher(Long idPublisher,Long authorId) {
        return authorRepository.assignAuthorToPublisher(idPublisher,authorId);
    }
}
