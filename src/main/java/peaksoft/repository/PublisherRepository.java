package peaksoft.repository;

import peaksoft.entity.Publisher;

import java.util.List;

public interface PublisherRepository {
    //Publisher кошуу учун
   Publisher savePublisher(Publisher publisher) ;
    //деген метод тузуп, сакталган Publisher ди кайтаруу керек.
   // Издательстводо
  Publisher  getPublisherById(Long id);
  List<Publisher>getAllPublishers();
  //(аты боюнча сорттоп чыгаруу),
    Publisher updatePublisher(Publisher publisher,Long id);
    String deletePublisherByName(String name);
   // (издательствону очургондо, ага тиешелуу китептер жана авторлор  очпошу керек), методдорун тузуп, ишке ашыруу.


}
