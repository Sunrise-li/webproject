package com.taotao.sso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;

@Service
public class RegisterServiceImpll implements RegisterService {
	
	@Autowired
	private TbUserMapper userMapper;
	
	
	/**
	 * 校验数据
	 */
	@Override
	public TaotaoResult checkData(String param, Integer type) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		//type为类型，可选参数1、2、3分别代表username、phone、email
		switch (type) {
			case 1 :
				criteria.andUsernameEqualTo(param);
				break;
			case 2 :
				criteria.andPhoneEqualTo(param);
				break;
			case 3 :
				criteria.andEmailEqualTo(param);
				break;
			default:
				throw new IllegalArgumentException();
		}
		//执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		//判断结果是否为空
		if(list == null || list.isEmpty()) {
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}

}
