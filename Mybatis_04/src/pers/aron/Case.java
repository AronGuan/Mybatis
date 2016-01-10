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

//һ�Զ��ѯ(collection)
public class Case {
	
	private SqlSession session;
	
	@Before
	public void setUp() throws IOException {
		String resource = "config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		//Ĭ�����ֶ��ύ��
		session = factory.openSession();
		//�Զ��ύ
		//session = factory.openSession(true);
	}
	
	//Ƕ�׽��
	@Test
	public void select(){
		String statement = "pers.aron.classMapper.getClass";
		Classes c = session.selectOne(statement, 2);
		session.commit();
		System.out.println(c);
		session.close();
	}
	
	//Ƕ�ײ�ѯ
	@Test
	public void select2(){
		String statement = "pers.aron.classMapper.getClass2";
		Classes c = session.selectOne(statement, 2);
		session.commit();
		System.out.println(c);
		session.close();
	}
}
