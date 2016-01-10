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

import pers.aron.mapper.UserMapper;

//��ϤCRUD
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
	
	@Test
	public void testAdd(){
		String statement = "pers.aron.userMapper.addUser";
		int num = session.insert(statement, new User("KK",23));
		session.commit();
		System.out.println(num);
		session.close();
	}
	
	@Test
	public void update(){
		String statement = "pers.aron.userMapper.updateUser";
		int num = session.update(statement, new User(4,"KK444",25));
		session.commit();
		System.out.println(num);
		session.close();
	}
	
	@Test
	public void deleteUser(){
		String statement = "pers.aron.userMapper.deleteUser";
		int num = session.delete(statement, 6);
		session.commit();
		System.out.println(num);
		session.close();
	}
	
	@Test
	public void selectUser(){
		String statement = "pers.aron.userMapper.getUser";
		User user = session.selectOne(statement, 1);
		session.commit();
		System.out.println(user);
		session.close();
	}
	
	@Test
	public void selectAll(){
		String statement = "pers.aron.userMapper.getAllUser";
		List<User> list = session.selectList(statement);
		session.commit();
		System.out.println(list);
		session.close();
	}
	
	//��ע��ķ�ʽ��ʵ��
	@Test
	public void testAdd2(){
		UserMapper mapper = session.getMapper(UserMapper.class);
		int num = mapper.add(new User("SS",45));
		session.commit();
		System.out.println(num);
		session.close();
	}
}
