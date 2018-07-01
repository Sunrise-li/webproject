import java.awt.Container;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbContentMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class TestPageHelper {
	
	public void testPageHelper() {
		//创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml");
		//从spring容器中获得mapper对象
		TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
		TbItemExample example = new TbItemExample();
		//执行查询并分页
		PageHelper.startPage(1, 10);
		List<TbItem> list = mapper.selectByExample(example);
		for (TbItem tbItem : list) {
			System.out.println(tbItem.getTitle());
		}
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		System.out.println("共有商品信息："+total);
	}
	@Test
	public void mybatisTest() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml");
		TbContentCategoryMapper mapper = applicationContext.getBean(TbContentCategoryMapper.class);
		TbContentExample example = new TbContentExample();
		TbContentCategory content = new TbContentCategory();
		content.setId(106L);
		content.setIsParent(true);
		int n = mapper.updateByPrimaryKey(content);
		System.out.println(n);
	}
}
