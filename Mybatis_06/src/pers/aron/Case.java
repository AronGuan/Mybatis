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
import pers.aron.bean.CUser;

//һ�����棬��������
public class Case {
	
	private SqlSessionFactory factory;
	private SqlSession session;
	@Before
	public void setUp() throws IOException {
		String resource = "config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		factory = new SqlSessionFactoryBuilder().build(reader);
		//Ĭ�����ֶ��ύ��
		//�Զ��ύ
		session = factory.openSession(true);
	}

	//һ�����棬session
	@Test
	public void testCacheOne(){
		String statement = "pers.aron.userMapper.getUser";
		CUser user = session.selectOne(statement,1);
		System.out.println(user);
		//�ڶ��δӻ�����ȡ
		user = session.selectOne(statement,1);
		System.out.println(user);
		System.out.println("-------------------");
		//1. �������
		//session.clearCache();
		
		//2.ִ��CUD����,���������
		session.update("pers.aron.userMapper.updateUser",new CUser(1,"Tom",13));
		session.commit();
		user = session.selectOne(statement,1);
		System.out.println(user);
	}
	
	//�������棬��������mapper
	@Test
	public void testCacheTwo(){
		SqlSession session1 = factory.openSession();
		SqlSession session2 = factory.openSession();
		
		String statement = "pers.aron.userMapper.getUser";
		CUser user1 = session1.selectOne(statement,1);
		session1.commit();
		System.out.println(user1);
		
		CUser user2 = session2.selectOne(statement,1);
		
		System.out.println(user2);

	}
}
