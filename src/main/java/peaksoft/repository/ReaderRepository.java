package peaksoft.repository;

import peaksoft.entity.Reader;

import java.util.List;

public interface ReaderRepository {
    String saveReader(Reader reader,Long bookId);
    Reader updateReader(Long id,Reader reader);
    List<Reader> getReaderByBookId(Long bookId);
    String deleteReaderById(Long id);
    Reader getReadersByAuthorId(Long authorId);
}
