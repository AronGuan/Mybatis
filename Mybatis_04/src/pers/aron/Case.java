package pers.aron;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import pers.aron.bean.Classes;

//一对多查询(collection)
public class Case {
	
	private SqlSession session;
	
	@Before
	public void setUp() throws IOException {
		String resource = "config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		//默认是手动提交的
		session = factory.openSession();
		//自动提交
		//session = factory.openSession(true);
	}
	
	//嵌套结果
	@Test
	public void select(){
		String statement = "pers.aron.classMapper.getClass";
		Classes c = session.selectOne(statement, 2);
		session.commit();
		System.out.println(c);
		session.close();
	}
	
	//嵌套查询
	@Test
	public void select2(){
		String statement = "pers.aron.classMapper.getClass2";
		Classes c = session.selectOne(statement, 2);
		session.commit();
		System.out.println(c);
		session.close();
	}
}
