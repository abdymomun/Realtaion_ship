package peaksoft.service.impl;

import peaksoft.entity.Reader;
import peaksoft.repository.ReaderRepository;
import peaksoft.repository.impl.ReaderRepoImpl;
import peaksoft.service.ReaderService;

import java.nio.ByteOrder;
import java.util.List;

public class ReaderServiceImpl implements ReaderService {
    private ReaderRepository repository = new ReaderRepoImpl();

    @Override
    public String saveReader(Reader reader,Long bookId) {
        return repository.saveReader(reader,bookId);
    }

    @Override
    public Reader updateReader(Long id, Reader reader) {
        return repository.updateReader(id,reader);
    }

    @Override
    public List<Reader> getReaderByBookId(Long bookId) {
        return repository.getReaderByBookId(bookId);
    }

    @Override
    public String deleteReaderById(Long id) {
        return repository.deleteReaderById(id);
    }

    @Override
    public Reader getReadersByAuthorId(Long authorId) {
        return repository.getReadersByAuthorId(authorId);
    }
}

