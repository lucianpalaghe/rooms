package ro.pss.spring.rooms.facade;

import org.springframework.stereotype.Service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Service
//@Transactional(REQUIRES_NEW)
public @interface Facade {
}
