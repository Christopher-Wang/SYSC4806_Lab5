package AddressBook.lab;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "buddyinfo", path = "buddyinfo")
public interface BuddyInfoRepository extends PagingAndSortingRepository<AddressBook, Long> {
    BuddyInfo findById(long id);
}

