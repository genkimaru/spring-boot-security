package com.ccsip.coap.master.repository;


import org.springframework.data.repository.CrudRepository;

import com.ccsip.coap.master.domain.Token;

public interface TokenRepository extends CrudRepository<Token, Long> {

	Token findByPublicKey(String key);

}
