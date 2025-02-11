package org.hatice.ikplus.repository.usermanagement;

import org.hatice.ikplus.entity.usermanagement.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
