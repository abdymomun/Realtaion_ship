package peaksoft.service.impl;

import peaksoft.entity.Publisher;
import peaksoft.repository.PublisherRepository;
import peaksoft.repository.impl.PublisherRepoImpl;
import peaksoft.service.PublisherService;

import java.util.List;

public class PublisherServiceImpl implements PublisherService {
  private   PublisherRepository publisherRepository = new PublisherRepoImpl();

    @Override
    public Publisher savePublisher(Publisher publisher) {
        return publisherRepository.savePublisher(publisher);
    }

    @Override
    public Publisher getPublisherById(Long id) {
        return publisherRepository.getPublisherById(id);
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepository.getAllPublishers();
    }

    @Override
    public Publisher updatePublisher(Publisher publisher, Long id) {
        return publisherRepository.updatePublisher(publisher,id);
    }

    @Override
    public String deletePublisherByName(String name) {
        return publisherRepository.deletePublisherByName(name);
    }
}
