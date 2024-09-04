package com.library.repository

import com.library.entity.DailyStat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import java.time.LocalDateTime

@ActiveProfiles("test")
@DataJpaTest
class DailyStatRepositoryTest extends Specification {

    @Autowired
    DailyStatRepository dailyStatRepository

    def "저장후 조회가된다"(){
        given:
        def givenQuery = "HTTP"
        when:
        def dailyStat = new DailyStat(givenQuery, LocalDateTime.now())
        def result = dailyStatRepository.saveAndFlush(dailyStat)

        then: "실제 저장이 된다"
        result.id != null
        

    }


}
