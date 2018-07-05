package com.taotao.portal.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.taotao.common.pojo.HttpClientUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

@Service
public class ItemServiceImpl implements ItemService{

private static final Class<Byte> Object = null;
	/*	REST_BASE_URL=http://192.168.43.81:8081
	#商品基本信息
	REST_ITEM_BASE_URL=/item/base/
	#商品详情
	REST_ITEM_DESC_URL=/item/desc/
	#商品规格参数
	REST_ITEM_PARAM_URL=/item/param/
*/
	@Value("${REST_BASE_URL}")
	private String  REST_BASE_URL;
	@Value("${REST_ITEM_BASE_URL}")
	private String REST_ITEM_BASE_URL;
	@Value("${REST_ITEM_DESC_URL}")
	private String REST_ITEM_DESC_URL;
	@Value("${REST_ITEM_PARAM_URL}")
	private String REST_ITEM_PARAM_URL;
	@Override
	public TbItem getItemById(Long itemId) {
		//根据商品Id查询数据
		String resultJson = HttpClientUtils.get(REST_BASE_URL+REST_ITEM_BASE_URL+itemId);
		System.out.println(REST_BASE_URL+REST_ITEM_BASE_URL+itemId);
		System.out.println(resultJson);;
		//转换成java对象返回
		TaotaoResult result = JSON.parseObject(resultJson,TaotaoResult.class);
		TbItem item = JSON.parseObject(JSON.toJSONString(result.getData()),TbItem.class);
		return item;	
	}
	/**
	 * 查询商品详情
	 */
	@Override
	public String getItemDesc(Long itemId) {
		String resultJson = HttpClientUtils.get(REST_BASE_URL+REST_ITEM_DESC_URL+itemId);
		System.out.println("REST_BASE_URL+REST_ITEM_DESC_URL----------->"+REST_BASE_URL+REST_ITEM_DESC_URL+itemId);
		TaotaoResult result = JSON.parseObject(resultJson, TaotaoResult.class);
		System.out.println("JSON.toJSONString(result)"+JSON.toJSONString(result));
		System.out.println("JSON.toJSONString(result.getData())----->"+JSON.toJSONString(result.getData()));
		TbItemDesc itemDesc = JSON.parseObject(JSON.toJSONString(result.getData()), TbItemDesc.class);
		return itemDesc.getItemDesc();
	}
	/**
	 * 查询商品规格
	 */
	@Override
	public String getItemParam(Long itemId) {
		String resultJson = HttpClientUtils.get(REST_BASE_URL+REST_ITEM_PARAM_URL+itemId);
		System.out.println("REST_BASE_URL+REST_ITEM_PARAM_URL+itemId"+REST_BASE_URL+REST_ITEM_PARAM_URL+itemId);
		//转换成java对象
		TaotaoResult result = JSON.parseObject(resultJson, TaotaoResult.class);
		TbItemParamItem param = JSON.parseObject(JSON.toJSONString(result.getData()),TbItemParamItem.class);
		if(param != null) {
			System.out.println("paramDate----->"+JSON.toJSONString(param));
			List<Map> mapList = JSON.parseArray(JSON.toJSONString(param.getParamData()),Map.class);
			//遍历List生成html
			StringBuffer sb = new StringBuffer();
			sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
			sb.append("	<tbody>\n");
			for (Map map : mapList) {
				sb.append("		<tr>\n");
				sb.append("			<th class=\"tdTitle\" colspan=\"2\">" + map.get("group") + "</th>\n");
				sb.append("		</tr>\n");
				// 取规格项
				List<Map> mapList2 = (List<Map>) map.get("params");
				for (Map map2 : mapList2) {
					sb.append("		<tr>\n");
					sb.append("			<td class=\"tdTitle\">" + map2.get("k") + "</td>\n");
					sb.append("			<td>" + map2.get("v") + "</td>\n");
					sb.append("		</tr>\n");
				}
			}
			sb.append("	</tbody>\n");
			sb.append("</table>");
			return sb.toString();
		}
		
		return "";
	}
	public static void main(String[] args) {
		String resultJson = HttpClientUtils.get("http://192.168.43.81:8081/rest/item/param/1188043");
		TaotaoResult result = JSON.parseObject(resultJson, TaotaoResult.class);
		TbItemParamItem param = JSON.parseObject(JSON.toJSONString(result.getData()),TbItemParamItem.class);
		System.out.println(JSON.toJSONString(result));
		System.out.println(JSON.toJSONString(param));
	}

}
