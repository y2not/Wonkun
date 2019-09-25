package com.example.demo.order.model;

import com.example.demo.order.idutil.BeanUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/* TODO 2019-08-30
 *  Concatenate Date and Database Sequence(ex. Oracle sequence) by repository service
 *   and
 *   need to make Common Utility for all custom id generator
 * */


@Slf4j
@Data
@Service
public class OrderIDGenerator  implements IdentifierGenerator {

    private static final String sequencename="sequence_orderid";
    private static final String schemaname="world";


    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        long iSeq = 0;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddMMmmss");

            try {
                iSeq = getNextVal();
            }catch (Exception ex) {
                ex.printStackTrace();
            }

            String sSeq=StringUtils.leftPad( Long.toString(iSeq), 5,"0" );

            // TODO: 2019-08-30
            log.debug("\n------------------------------------------------------------");
            log.debug("\n" + Long.parseLong(simpleDateFormat.format(new Date())) + sSeq );
            log.debug("\n------------------------------------------------------------");
            return Long.parseLong(simpleDateFormat.format(new Date()) + sSeq )    ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Autowired
    public DataSource dataSource;
    @Autowired
    public NamedParameterJdbcTemplate nJdbcTemplate;
    @Autowired
    public JdbcTemplate jdbcTemplate;

    private void setJDBCTemplete(){
        this.dataSource = (DataSource) BeanUtils.getBean(DataSource.class);
        this.nJdbcTemplate= new NamedParameterJdbcTemplate(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        log.debug("\n======================================================");
        log.debug("\ndatasource:" + this.dataSource);
        log.debug("\nnJdbcTemplate:" + this.nJdbcTemplate);
        log.debug("\njdbcTemplate:" + this.jdbcTemplate);
        log.debug("\n======================================================");
    }
    private void printJDBCTemplete(){
        log.debug("\n======================================================");
        log.debug("\ndatasource:" + this.dataSource);
        log.debug("\nnJdbcTemplate:" + this.nJdbcTemplate);
        log.debug("\njdbcTemplate:" + this.jdbcTemplate);
        log.debug("\n======================================================");
    }

    /**
     * 이거 하나면 됨....
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public long getNextVal() throws Exception {
        setJDBCTemplete();
        printJDBCTemplete();
        String query="  select nextval(trim('" + sequencename + "')) as nextValue from dual ";
        log.debug("\n" + query  );
        return  jdbcTemplate.queryForObject(query, Long.class);
    }


}


/*

CREATE TABLE `sequences` (
  `name` varchar(32) NOT NULL,
  `currval` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE DEFINER=`root`@`localhost` FUNCTION `nextval`(the_name varchar(32)) RETURNS bigint(20) unsigned
    MODIFIES SQL DATA
    DETERMINISTIC
BEGIN
     DECLARE ret BIGINT UNSIGNED;
     UPDATE sequences SET currval=currval+1 WHERE name=the_name;
     SELECT currval INTO ret FROM sequences WHERE name=the_name limit 1;
     RETURN ret;
 END

INSERT INTO `world`.`sequences`
(`name`)
VALUES
(<sequence_orderid>);



 */
