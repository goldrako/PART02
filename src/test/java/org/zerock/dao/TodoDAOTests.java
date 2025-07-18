package org.zerock.dao;


import lombok.Cleanup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.jdbcex.dao.ConnectionUtil;
import org.zerock.jdbcex.dao.TodoDAO;
import org.zerock.jdbcex.domain.TodoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TodoDAOTests {

    private TodoDAO todoDAO;

    @BeforeEach
    public void ready(){
        todoDAO = new TodoDAO();
    }

    @Test
    public void testTime() throws Exception{

        System.out.println("### Current Tiem = " + todoDAO.getTime() );

    }
    /**
     * getTime2 메서드 테스트
     * @throws Exception
     */

    @Test
    public void testgetTime2() throws Exception {
        // getTime2 메서드 호출 결과 가져오기
        String timeResult = todoDAO.getTime2();

        // 결과 출력
        System.out.println("### Current Time = " + timeResult);

        // 결과가 null이 아닌지 확인 (선택적)
        assertNotNull(timeResult);

        // 또는 결과가 현재 날짜/시간 형식인지 확인 (선택적)
        // 예: assertThat(timeResult).matches("\\d{4}-\\d{2}-\\d{2}.*");
    }

    @Test
    public void testInsert() throws Exception {
        TodoVO todoVO = TodoVO.builder()
                .title("Sample Title...")
                .dueDate(LocalDate.of(2021,12,31))
                .build();

        todoDAO.insert(todoVO);
    }


    @Test
    public void testList() throws Exception {

        List<TodoVO> list = todoDAO.selectAll();

        list.forEach(vo -> System.out.println(vo));

    }

    @Test
    public void testSelectOne() throws Exception {

        Long tno = 1L; //반드시 존재하는 번호를 이용

        TodoVO vo = todoDAO.selectOne(tno);

        System.out.println(vo);
    }

    @Test
    public void testUpdateOne() throws Exception {
        TodoVO todoVO = TodoVO.builder()
                .tno(1L)
                .title("Sample Title...")
                .dueDate(LocalDate.of(2021,12,31))
                .finished(true)
                .build();

        todoDAO.updateOne(todoVO);

    }


}
