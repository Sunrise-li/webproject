package com.taotao.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService{
	@Autowired
	private TbItemParamItemMapper paramItemMapper;

	@Override
	public String getItemParamHtml(Long itemId) {
		//更具商品id查询规格参数
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		//执行查询
		List<TbItemParamItem> list = paramItemMapper.selectByExampleWithBLOBs(example);
		System.out.println(list.get(0).toString());
		if(list == null || list.isEmpty()) {
			return "";
		}
		//取规格参数
		TbItemParamItem item = list.get(0);
		String paramData = item.getParamData();
		//转转成java对象
		System.out.println("---------------->json"+paramData);
		List<Map> mapList = JSON.parseArray(paramData, Map.class);
		System.out.println(mapList.toString());
		//遍历List生成html
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
		sb.append("		<tbody>\n");
		for (Map map : mapList) {
			sb.append("		<tr>\n");
			sb.append("			<th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
			sb.append("		</tr>\n");
			//取规格项
			List<Map> mapList2 = (List<Map>) map.get("params");
			for (Map map2 : mapList2) {
				sb.append("		<tr>\n");
				sb.append("			<td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
				sb.append("			<td>"+map2.get("v")+"</td>\n");
				sb.append("		</tr>\n");
			}
		}
		sb.append("	</tbody>\n");
		sb.append("</table>");
		System.out.println(sb.toString());
		return sb.toString() ;
	}
	
}
