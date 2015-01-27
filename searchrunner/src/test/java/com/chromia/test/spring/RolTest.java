package com.chromia.test.spring;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import com.chromia.model.Rol;
import com.chromia.repository.RolDao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class RolTest  {
  
	@Autowired
	private RolDao rDao;
	
  @Test
  public void verifyRolNamesQuery() {
	 System.out.println("hola"); 
	  Rol rol = rDao.getRolById(1);
//	 
//	  System.out.println("Rol : "+ rol.getNombre());
  }
}
