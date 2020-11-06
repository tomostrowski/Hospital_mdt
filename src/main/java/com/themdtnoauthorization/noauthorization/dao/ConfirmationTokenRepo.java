package com.themdtnoauthorization.noauthorization.dao;

import com.themdtnoauthorization.noauthorization.entity.ConfirmationToken;
import com.themdtnoauthorization.noauthorization.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepo extends CrudRepository<ConfirmationToken, String> {
    Optional<ConfirmationToken> findByConfirmationToken(String confirmationToken);
    Optional<ConfirmationToken> findByUser(User user);
}
