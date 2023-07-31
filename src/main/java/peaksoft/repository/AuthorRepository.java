package peaksoft.repository;

import peaksoft.entity.Author;

public interface AuthorRepository {
   // Автордо
   String saveAuthor(Author author);
   Author updateAuthor(Long id,Author author);
   Author getAuthorById(Long id);
   Author getAuthorsByPublisherId(Long publisherId);
    //(тиешелуу издательствонун авторлорун чыгарып беруу),
   String deleteAuthorById(Long id);
    //(автор очкондо, авторго тиешелуу издательство очпошу керек, китептер очуш керек),
   String assignAuthorToPublisher(Long idPublisher,Long authorId);
    //(авторду издательствого кошуп коюу(назначить)).
}
