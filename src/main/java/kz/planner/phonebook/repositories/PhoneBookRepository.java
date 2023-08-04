package kz.planner.phonebook.repositories;

import kz.planner.phonebook.models.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneBookRepository extends JpaRepository<PhoneBook, Long> {

}
