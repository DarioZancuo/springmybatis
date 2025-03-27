package com.betacom.test;

import com.betacom.dao.ContrattoMapper;
import com.betacom.entity.Contratto;
import com.betacom.entity.Role;
import com.betacom.entity.Tipologia;
import com.betacom.services.implementation.ContrattoImplementation;
import com.betacom.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Date;
import static org.junit.Assert.*;

public class ContrattoTransactionTest {
	
    private ContrattoImplementation contrattoService = new ContrattoImplementation(); 
    private ContrattoMapper contrattoMapper = new ContrattoMapper();

    @Test
    public void testManualRollbackCreateAndAssign() {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession(false);
        try {
            Contratto contratto = new Contratto();
            contratto.setDataAssunzione(new Date(System.currentTimeMillis()));
            contratto.setStipendio(4000.0);
            contratto.setStatus(true);

            Role r = new Role();
            r.setId(1); 
            contratto.setRole(r);

            Tipologia t = new Tipologia();
            t.setId(1); 
            contratto.setTipologia(t);

            Integer idEmployeeFalso = 999999;

            contrattoService.createAndAssign(contratto, idEmployeeFalso);

            fail("L'eccezione doveva essere lanciata");

        } catch (Exception ex) {
            Contratto c = contrattoMapper.getById(999999); 
            assertNull("Il contratto non deve essere salvato a causa del rollback", c);
        } finally {
            session.close();
        }
    }
}
